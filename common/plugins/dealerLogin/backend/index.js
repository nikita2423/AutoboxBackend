'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {

    const loopback = helper.getLoopbackObj();
	/**
	 * Here server is the main app object
	 * databaseObj is the mapped database from the package.json file
	 * helper object contains all the helpers methods.
	 * packegeObj contains the packageObj file of your plugin. 
	 */

	/**
	 * Initialize the plugin at time of server start.
	 * init method should never have any argument
	 * It is a constructor and is populated once the server starts.
	 * @return {[type]} [description]
	 */
	var init = function(){
       addRole();
	};

	//Define a role for dealer..
	const addRole = function(){
        const Role = databaseObj.Role;
        const User = databaseObj.User;
        Role.registerResolver(packageObj.roles.serviceLoggedUser, function(role, context, cb) {
            function reject(err) {
                if (err) {
                    return cb(err, false);
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
	};


	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init
	};
}; //module.exports
