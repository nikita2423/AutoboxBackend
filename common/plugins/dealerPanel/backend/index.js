'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	const Promise = require("bluebird");
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

	};


	/**
	 * Load static data to database..
	 * @param req
	 * @param state
	 * @param data
	 */
	const getStaticData = function (req, state, data) {
			return new Promise(function (resolve, reject) {
					//const before = new Date().getTime();
					load(req, data)
							.then(function (databaseList) {
									data["databaseList"] = databaseList;

									return loadAbsoluteSchema(databaseList);
							})
							.then(function (schema) {
									//console.log(new Date().getTime() - before);
									//Add schema..
									data["schema"] = schema;
									resolve();
							})
							.catch(function (error) {
									reject(error);
							});

			});
	};

	/**
	 * Load absolute schema from server..
	 * @param databaseList
	 */
	const loadAbsoluteSchema = function (databaseList) {
		return new Promise(function (resolve, reject) {
				const SCHEMA = {};

			 if(databaseList){
					 if(databaseList.length){
							 const promiseList = [];
							 databaseList.forEach(function (database) {
									 if(database){
											 const dataModel = server.models[database];
											 if(dataModel){
													 if(dataModel.getAbsoluteSchema){
															 (function (dataModel, database) {
																	 promiseList.push(new Promise(function (resolve, reject) {
																			 dataModel.getAbsoluteSchema(function(err, values) {
																					 if(err){
																							 reject(err);
																					 }else{
																							 SCHEMA[database] = values;
																							 resolve();
																					 }
																			 });
																	 }));
															 })(dataModel, database);
													 }
											 }
									 }
							 });

							 Promise.all(promiseList)
									 .then(function () {
											 resolve(SCHEMA);
									 })
									 .catch(function (error) {
											 reject(error);
									 })
					 }else{
							 resolve();
					 }
			 }else{
					 resolve();
			 }
		});
	};



	//Creating a memoization method for all the
	var load = function (request, data) {
			return new Promise(function (resolve, reject) {
					var roles;
					var list = [];
					var databasesList = packageObj.loadDatabases;
					const login = helper.loadPlugin("login");
					login.getRoles(server, request, function (error, rolesList) {
							if(rolesList){
									//TODO: Add ACL to data..
									data.acl = rolesList;
									const SnaphyACL = databaseObj.SnaphyACL;
									if(SnaphyACL){
											getACL(SnaphyACL, rolesList)
													.then(function (aclList) {
															if(databasesList){
																	databasesList.forEach(function (item) {
																			var allowed = true;
																			if(aclList[item]){
																					if(aclList[item].read === "deny"){
																							allowed = false;
																					}
																			}

																			if(allowed){
																					list.push(item);
																			}
																	});
															}
															resolve(list);
													})
													.catch(function (error) {
															reject(error);
													});
									}else {
											resolve(databasesList);
									}

							}else {
									resolve(databasesList);
							}
					});
			});
	};

	/**
	 * Get the ACL data.
	 * @param SnaphyACL
	 * @param rolesList
	 */
	const getACL = function (SnaphyACL, rolesList) {
			return new Promise(function (resolve, reject) {
					SnaphyACL.find({
							where:{
									role:{
											inq: rolesList
									}
							}
					})
							.then(function (acl) {
									const aclList = {};
									if(acl){
											if(acl.length){
													acl.forEach(function (item) {
															if(item.model){
																	aclList[item.model] = item;
															}
													})
											}
									}
									resolve(aclList);
							})
							.catch(function (error) {
									reject(error);
							});
			});
	};




	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init,
		getStaticData: getStaticData
	}
}; //module.exports
