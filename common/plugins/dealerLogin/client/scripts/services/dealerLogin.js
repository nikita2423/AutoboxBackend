'use strict';
/*jslint browser: true*/
/*global $, jQuery, angular, $snaphy , redirectOtherWise*/

var loginState        = $snaphy.loadSettings('login', "loginState");
var registerState     = $snaphy.loadSettings('login', "registerState");
var forgotPassState   = $snaphy.loadSettings('login', "forgotPassState");


angular.module($snaphy.getModuleName())
//Define your services here..
//Service for implementing login related functionality..
    .factory('LoginServices', ['Database', '$location', 'LoopBackAuth', '$injector', '$q', "$window",
        function(Database, $location, LoopBackAuth, $injector, $q, $window) {
            //Set redirect otherwise state name..
            //First use the value from the route/login global routeOtherWise value ....
            var redirectOtherWise_ = redirectOtherWise || 'dashboard';

            //get the user service..
            var UserService = Database.getDb('dealerLogin', 'User'),
            SnaphyACL = Database.getDb('dealerLogin', 'SnaphyACL'),
            //UserDetail is an object will contain the current logged user info.
            userDetail = null;

            /**
             * Method for checking if page is authenticated or not. Fetch the current user info if user is authenticated.
             * @param success
             * @param error
             */
            var authenticatePage = function(success, error) {
                if (!UserService.isAuthenticated()) {
                    //Calling the promise error..
                    //$location.nextAfterLogin = $location.path();
                    error();
                } else {
                    success();
                    console.log("Checking for logged confirmation.");
                    //Will send an 401  error..already..
                    getLoggedDetails();
                }

            };

            /**
             * Method to check if the user is admin or not
             * @param success
             * @param error
             */
            var isAdmin = function(success, error){
                UserService.isAdmin(function(value){
                    if(value.isAdmin){
                        //Current user is admin user..
                        success();
                    }else{
                        //Current user is not admin user..
                        error();
                    }
                }, function(){
                    console.log("Error checking isAdmin method");
                    error();
                });
            };


            /**
             *  Creating memoization method for storing user details....
             * @type {{get, getRoles, getACl, set, setRoles, setAcl}}
             */
            var addUserDetail = (function(){
                var user;
                var roles;
                var aclListObj = {};
                return {
                    /**
                     * Get the user. Returns promise object.
                     * @returns {Promise}
                     */
                    get: function(){
                        return $q(function(resolve, reject){
                            if(user){
                                resolve(user);
                            }else{
                                UserService.getCurrent(function(userObj){
                                    //Adding user detail to userService..
                                    addUserDetail.set(userObj);
                                    addUserDetail.getRoles()
                                        .then(function () {
                                            resolve(userObj);
                                        })
                                        .catch(function (error) {
                                            reject(error);
                                        });

                                }, function(err){
                                    reject(err);
                                });
                            }
                        });
                    },
                    getRoles: function () {
                        return $q(function (resolve, reject) {
                            if(roles){
                                resolve(roles);
                            }else{
                                //First check if database is present globally..
                                var STATIC_DATA = $window.STATIC_DATA;
                                if(STATIC_DATA.acl){
                                    roles = STATIC_DATA.acl;
                                    resolve(roles);
                                }else{
                                    UserService.getAuthorisedRoles({}, {}, function (rolesList) {
                                        if(rolesList){
                                            if(rolesList.roles.length){
                                                addUserDetail.setRoles(rolesList.roles);
                                                resolve(rolesList.roles);
                                            }else{
                                                addUserDetail.setRoles(null);
                                                resolve(null);
                                            }

                                        }else {
                                            addUserDetail.setRoles(null);
                                            resolve(null);
                                        }

                                    }, function (err) {
                                        reject(err);
                                    });
                                }
                            }
                        })
                    },
                    getACl: function () {
                        return $q(function (resolve, reject) {
                            addUserDetail.getRoles()
                                .then(function (rolesList) {

                                    if(rolesList){
                                        if(rolesList.length){
                                            SnaphyACL.find({
                                                filter:{
                                                    where:{
                                                        role:{
                                                            inq: rolesList
                                                        }
                                                    }
                                                }
                                            }, function (aclObjList) {
                                                aclListObj = {};
                                                if(aclObjList){
                                                    if(aclObjList.length){
                                                        aclObjList.forEach(function (acl) {
                                                            aclListObj[acl.model] = acl;
                                                        });
                                                    }
                                                }
                                                addUserDetail.setAcl(aclListObj);
                                                resolve(aclListObj);
                                            }, function (error) {
                                                reject(error);
                                            });
                                        }
                                    }
                                })
                                .catch(function (error) {
                                    console.error(error);
                                    reject(error);
                                });

                        })
                    },
                    set: function(userObj){
                        user = userObj;
                    },
                    setRoles: function (roleList) {
                        roles = roleList;
                    },
                    setAcl: function (acl) {
                        aclListObj = acl;
                    }
                };
            })();





            /**
             * For getting the current logged user details from the server.
             * @param success
             * @param error
             */
            var getLoggedDetails = function() {
                UserService.getCurrent(function(user){
                    //Adding user detail to userService..
                    addUserDetail.set(user);
                }, function(){
                    console.error("401 error occured. User login expired!");
                });
            };




            //For adding an employee..
            var register = function(username, email, password, success, faliure){
                UserService.create({}, {username: username, email: email, password: password}, function(value){
                    success(value);
                }, function(RespHeader){
                    faliure(RespHeader);
                });
            };


            /**
             * For getting the current logged user details from the server.
             * @param success
             * @param error
             */
            var getLoggedDetails = function() {
                UserService.getCurrent(function(user){
                    //Adding user detail to userService..
                    userDetail = user;
                }, function(){
                    console.error("401 error occured. User login expired!");
                });
            };




            



            /**
             * For logging out
             */
            var logout = function() {
                UserService.logout(
                    //Successs
                    function() {
                        var $state = $injector.get("$state");
                        $state.go(loginState);
                    },

                    //Error..
                    function() {
                        //Now clearing the loopback auth values..
                        LoopBackAuth.clearUser();
                        LoopBackAuth.clearStorage();
                    });
            };



            return {
                authenticatePage: authenticatePage,
                getLoggedDetails: getLoggedDetails,
                logout: logout,
                userDetail: userDetail,
                redirectOtherWise: redirectOtherWise_,
                isAdmin: isAdmin,
                addUserDetail: addUserDetail,
                register: register
            };
        }//LoginServices
    ]);
