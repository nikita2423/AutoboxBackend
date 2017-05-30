'use strict';
module.exports = function(server, databaseObj, helper, packageObj) {
    const Promise = require('bluebird');
    const adminRole = packageObj.adminRole;

    var adminUserModel = packageObj.adminUser,
        User = databaseObj.User,
        Role = server.models.Role,
        RoleMapping = server.models.RoleMapping,
        loopback = helper.getLoopbackObj(),

        //Create an init method to be executed when the plugin get run for the first time..in memory..
        init = function() {
            /**
             * Permission levels
             * ADMIN -> STATIC ROLE DECLARATION.
             * STAFF -> DYNAMIC ROLE DECLARATION.
             */
            //Now adding user to the method..
            User.create(adminUserModel)
                .then(function(err, users) {
                    if (err) throw err;
                    //Now add role..
                    addRole(Role, users);
                })
                .catch(function(err) {
                    console.info("Login throw error while adding role.\n");
                    var where = {};
                    where.or = [];
                    for (var i = 0; i < adminUserModel.length; i++) {
                        var model = adminUserModel[i];
                        where.or.push({
                            email: model.email
                        });
                    }
                    User.find({
                        where: where
                    }, function(err, users) {
                        if(!err){
                            if (users.length) {
                                //Now add role..
                                addRole(Role, users);
                            }
                        }

                    });
                });

            //TODO MODIFY THIS METHOD TO PROVIDE RUNTIME ACCESS AND MODIFICATION TO USER.
            addStaffResolver();
            hideRestMethods();

            User.isAdmin = function(cb) {
                var currentContext = loopback.getCurrentContext();
                var app = this.app;
                isAdmin(app, currentContext, cb);
            };

            Role.verifyRole = function(role, cb){
                verifyRole(role, cb);
            };



            //Now defigning a method for checking if the user exist in the role.
            User.remoteMethod(
                'isAdmin', {
                    returns: {
                        arg: 'isAdmin',
                        type: 'boolean'
                    }
                }
            );

            //Now a method for checking if the user exist in the role.
            Role.remoteMethod(
                'verifyRole', {
                    accepts: {arg: 'role', type: 'string'},
                    returns: {
                        arg: 'isInRole',
                        type: 'boolean'
                    }
                }
            );



        }, //Init..


        addRole = function(Role, users){
            var i;
            //create the admin role
            Role.findOrCreate(
            {
                where:{
                    id: adminRole,
                    name: adminRole
                }
            },
            {
                id: adminRole,
                name: adminRole
            }, function(err, role) {
                if (err){
                    //role already exists..
                    Role.find({
                        name: adminRole
                    }, function(err, roleList){
                        if(roleList){
                            if(roleList.length){
                                const role = roleList[0];
                                for (i = 0; i < users.length; i++) {
                                    //Making this user an admin.
                                    addUserAdmin(role, users[i].id);
                                } //for loop..
                            }
                        }

                    });
                }else{
                    for (i = 0; i < users.length; i++) {
                        //Making this user an admin.
                        addUserAdmin(role, users[i].id);
                    } //for loop..
                }

            });
        },


        isAdmin = function(app, currentContext, cb) {
            Role = app.models.Role;
            RoleMapping = app.models.RoleMapping;
            //bad documentation loopback..
            //http://stackoverflow.com/questions/28194961/is-it-possible-to-get-the-current-user-s-roles-accessible-in-a-remote-method-in
            //https://github.com/strongloop/loopback/issues/332
            var context;


            try {
                context = {
                    principalType: RoleMapping.USER,
                    principalId: currentContext.active.accessToken.userId
                };
            } catch (err) {
                console.error("Error >> User not logged in. ");
                context = {
                    principalType: RoleMapping.USER,
                    principalId: null
                };
            }



            //Now check the role if the context is admin.
            Role.isInRole(adminRole, context, function(err, InRole) {
                if (err) {
                    return cb(err);
                }

                cb(null, InRole);
            });
        },


        
        /**
         * Internal method for checking if current user in a role with the given loopback..
         * Method to be useful fot plugins..
         * @param role {string} role name
         * @param callback {function(err, isInRole)}
         */
        verifyRole = function(role, callback) {
            Role = server.models.Role;
            RoleMapping = server.models.RoleMapping;
            var currentContext = loopback.getCurrentContext();
            isInRole(server, role, currentContext, callback);
        },


        //Check if a particular user is in role..
        isInRole = function(app, role, currentContext, cb) {
            Role = app.models.Role;
            RoleMapping = app.models.RoleMapping;
            //bad documentation loopback..
            //http://stackoverflow.com/questions/28194961/is-it-possible-to-get-the-current-user-s-roles-accessible-in-a-remote-method-in
            //https://github.com/strongloop/loopback/issues/332
            var context;

            try {
                context = {
                    principalType: RoleMapping.USER,
                    principalId: currentContext.active.accessToken.userId
                };
            } catch (err) {
                console.error("Error >> User not logged in. ");
                context = {
                    principalType: RoleMapping.USER,
                    principalId: null
                };
            }


            //Now check the role if the context is admin.
            Role.isInRole(role, context, function(err, InRole) {
                if (err) {
                    return cb(err);
                }

                cb(null, InRole);
            });
        },




        //TODO ADD GUEST AND CUSTOMER ROLE RESOLVER AND PROVIDE IT FOR CUSTOMER.
        //Method for resolving staff role by user..
        addStaffResolver = function() {
            //Now registering an dynamic role for the user..
            //All user of the employee model  belong to the staff role.
            /**
             * Default User  ACLs.
             DENY EVERYONE *
             ALLOW admin create
             ALLOW OWNER deleteById
             ALLOW EVERYONE login
             ALLOW EVERYONE logout
             ALLOW staff findById
             ALLOW OWNER updateAttributes

             */

             
             //If a users is logged by Employee account the he is a staff.
            Role.registerResolver('staff', function(role, context, cb) {

                function reject(err) {
                    if (err) {
                        return cb(err);
                    }
                    cb(null, false);
                }

                function accept() {
                    cb(null, true);
                }

                var currentContext = loopback.getCurrentContext();
                var userId = null;
                try {
                    userId = currentContext.active.accessToken.userId;
                } catch (err) {
                    userId = null;
                }

                if (!userId) {
                    return reject(); // do not allow anonymous users
                }


                //Now check if the logged in user is an Employee
                User.exists(userId, function(err, exists){
                    if(err){
                        return reject(err);
                    }else{
                        if(exists){
                            //Accept the staff role..
                            return accept();
                        }else{
                            return reject();
                        }
                    }
                });
            });
            
        },




        /**
         * Method for adding static admin role to an user
         * @param adminRoleInstance
         * @param userInstanceId
         */
        addUserAdmin = function(adminRoleInstance, userInstanceId) {
            adminRoleInstance.principals.count({
                principalType: RoleMapping.USER,
                principalId: userInstanceId

            }, function(err, count){
                if(err){
                    console.error(err);

                }else{
                    if(count === 0){
                        //make users an admin
                        adminRoleInstance.principals.create({
                            principalType: RoleMapping.USER,
                            principalId: userInstanceId
                        }, function(err, principal) {
                            if (err) {
                                console.error('Got error while creating static admin role.',  console.error(err));
                            }else{
                                console.info("Static admin role created successfully.");
                            }
                        });
                    }else{
                        console.info("Static admin role updated successfully.");
                    }
                }
            });

        },

        /**
         * Get the authorised roles of the current logged in users..
         * @param app
         * @param cb {function(error, roles)}
         */
        getAuthorisedRoles = function(app, cb){
            var currentContext = loopback.getCurrentContext();
            Role = app.models.Role;
            RoleMapping = app.models.RoleMapping;
            //bad documentation loopback..
            //http://stackoverflow.com/questions/28194961/is-it-possible-to-get-the-current-user-s-roles-accessible-in-a-remote-method-in
            //https://github.com/strongloop/loopback/issues/332
            var context;
            try {
                context = {
                    principalType: RoleMapping.USER,
                    principalId: currentContext.active.accessToken.userId
                };
            } catch (err) {
                console.error("Error >> User not logged in. ");
                context = {
                    principalType: RoleMapping.USER,
                    principalId: null
                };
            }

            Role.getRoles(context, function(err, roles) {
                if(err){
                    cb(err, null);
                }else{
                    cb(null, roles);
                }
            });
        },





        //TODO MODIFY THIS METHOD TO CHANGE IT FROM THIS FUNCTION DYNAMICALLY
        hideRestMethods = function() {
            //Hiding all the rest endpoints except login/logout/create
            Role = server.models.Role;

            Role.disableRemoteMethod("create", true);
            Role.disableRemoteMethod("upsert", true);
            Role.disableRemoteMethod("updateAll", true);
            Role.disableRemoteMethod("updateAttributes", false);

            Role.disableRemoteMethod("find", true);
            Role.disableRemoteMethod("findById", true);
            Role.disableRemoteMethod("findOne", true);

            Role.disableRemoteMethod("deleteById", true);

            Role.disableRemoteMethod("confirm", true);
            Role.disableRemoteMethod("count", true);
            Role.disableRemoteMethod("exists", true);
            Role.disableRemoteMethod("resetPassword", true);

            Role.disableRemoteMethod('__count__accessTokens', false);
            Role.disableRemoteMethod('__create__accessTokens', false);
            Role.disableRemoteMethod('__delete__accessTokens', false);
            Role.disableRemoteMethod('__destroyById__accessTokens', false);
            Role.disableRemoteMethod('__findById__accessTokens', false);
            Role.disableRemoteMethod('__get__accessTokens', false);
            Role.disableRemoteMethod('__updateById__accessTokens', false);
        };


    //Now return the methods that you want other plugins to use
    return {
        init: init,
        hideRestMethods: hideRestMethods,
        addUserAdmin: addUserAdmin,
        isAdmin: isAdmin,
        verifyRole: verifyRole,
        getAuthorisedRoles: getAuthorisedRoles
    };



};
