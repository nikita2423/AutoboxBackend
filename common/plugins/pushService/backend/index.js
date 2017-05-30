'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	
	var push = require("./pushImplementation")(databaseObj.Application, databaseObj.Notification, databaseObj.Push);

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
		var pushService = require("./pushService")(server, databaseObj.Notification, databaseObj.Application, databaseObj.Push, packageObj);
			
	};


	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init,
		push: push
	};
}; //module.exports
