(function(){'use strict';})();
module.exports = function( server, databaseObj, helper, packageObj) {
	var saveRemoteMethod = require('./saveDb');
	var onDelete = require('./cascadingDelete');
	var _ = require("lodash");
	var modifyHasAndBelongsToMany = require("./modifyHasAndBelongsToMany");
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
		//For loading the raw properties..
		//Just Introduce a remote method in all the given method..
		//run each models in the loop and add a remote method to it.
		var models = server.models();

		models.forEach(function(Model) {
			//refer to https://apidocs.strongloop.com/loopback/#app-models
			addRemoteMethod(server, Model.modelName);
			//Also add save method to each models..
			saveRemoteMethod.addSaveMethod(server, Model.modelName);
			addCaseSensitiveSearch (server, Model.modelName);
			onDelete.onCascadeDelete(server, Model.modelName);
			modifyHasAndBelongsToMany.modifyRelation(server, Model.modelName);
		});

	};


	/**
	 * Add remote methods to the models..
	 * @param app
	 * @param modelName
     */
	var addRemoteMethod = function(app, modelName){
		var modelObj = app.models[modelName];
		/**
		 * ModelObj getSchema remote method..
		 * @param callback
         */
		modelObj.getSchema = function(callback) {
			//Now form the schema and send it to the client..
			let relations = modelObj.definition.settings.relations,
				filters,
				tables,
				widgets;

			const tableObj = helper.getTableJson(modelName);

			if(tableObj){
				if(tableObj.tables){
					tables = tableObj.tables;
				}
				if(tableObj.widgets){
					widgets = tableObj.widgets;
				}
				if(tableObj.filters){
					filters = tableObj.filters;
				}
			}

			/**
			 * Now form the desired schema and return it.
			 */
			var header = addPropToHeader(app, modelName, '', [], false, []),
			//Get template structure..
			schema = generateTemplateStr(app, modelName);
			//Now recursively add relations to the models...
			addNestedModelRelation(app, header, schema, relations, modelName);

			//Now add filters and tables and headers to the model
			schema.header  = header;
			schema.filters = filters;
			schema.tables  = tables;
			schema.widgets  = widgets;

			callback(null, schema);
		};



		modelObj.getAbsoluteSchema = function(callback) {
			//Now form the schema and send it to the client..
			let relations = modelObj.definition.settings.relations,
			filters,
			tables,
			widgets;

			//Load login plugin get roles method..
			const {getAuthorisedRoles} = helper.loadPlugin('login');
			//Find the authorised roles for the data..
			if(getAuthorisedRoles){
				getAuthorisedRoles(server, function(err, roleList){
					if(err){
						console.error("Error occured in fetching roles.", err);
                        callback(err);
					}else{
                        const tableObj = helper.getTableJson(modelName);
                        if(tableObj){
                            if(tableObj.tables){
                                tables = tableObj.tables;
                            }
                            if(tableObj.widgets){
                                widgets = tableObj.widgets;
                            }
                            if(tableObj.filters){
                                filters = tableObj.filters;
                            }
                        }



                        /**
                         * Now form the desired schema and return it.
                         */
                        var header = addPropToHeader(app, modelName, '', [], false, roleList),
                        //Get template structure..
                        schema = generateTemplateStr(app, modelName, null, roleList);


                        //Now recursively add relations to the models...
                        addNestedModelRelation(app, header, schema, relations, modelName, true, roleList);

                        //Now add filters and tables and headers to the model..
                        schema.header  = header;
                        schema.filters = filters;
                        schema.tables  = tables;
                        schema.widgets  = widgets;

                        if(schema.fields){
                            //Sort the fields..
                            schema.fields = sortByPriority(schema.fields);
                        }



                        callback(null, schema);
					}
				});
			}else{
                let err = new Error("getAuthorisedRoles is not defined in login plugin");
                callback(err, null);
            }
		};

		//Now registering the method `getSchema`
		modelObj.remoteMethod(
				'getSchema',
				{
					returns: {arg: 'schema', type: 'object'},
					description: "Send the schema of the model requested."
				}
		);

		//Now registering the method `getAbsoluteSchema` required for robust automata plugin..
		modelObj.remoteMethod(
				'getAbsoluteSchema',
				{
					returns: {arg: 'schema', type: 'object'},
					description: "Send the absolute schema of the model requested."
				}
		);
	};


	var addCaseSensitiveSearch = function(server, modelName){
		var modelObj = server.models[modelName];
		modelObj.observe("access", function (ctx, next) {
			if(ctx.query.where){
				for(var whereProp in ctx.query.where){
					if(ctx.query.where.hasOwnProperty(whereProp)){
						var like = ctx.query.where[whereProp].like;
						if(like){
							var patt= /\/.*\//;
							if(patt.test(like)){
								//Regex already present..
								//do nothing..

							}else{

								var pattern = new RegExp(''+like+'.*', "i"); /* case-insensitive RegExp search */
								//Now modifying the like property..
								ctx.query.where[whereProp].like = pattern;
							}


						}
					}
				}
				next();
			}else{
				next();
			}
		});//observe..
	};

	/**
	 * Sort schemas by priority
	 * @params {[object]} array of object that needs to be sorted in descending order.
 	 */
	var sortByPriority = function(collection){
		//_.sortBy(users, [function(o) { return o.user; }]);
		collection = _.sortBy(collection, [function(obj) {
			if(obj){
				if(obj.templateOptions){
					if(obj.templateOptions.fields){
						//Also sort if any nested fields present..
						obj.templateOptions.fields = sortByPriority(obj.templateOptions.fields);
					}

					if(obj.templateOptions.priority){
						return obj.templateOptions.priority;
					}
				}
			}
			return 0;
		}]).reverse();

		return collection;
	};


    /**
     * Check if the property has allow role or reject role..
     * @param propertyObj Loopback model property obj
     * @param roleList list of roles defined for current logged user..loopback.currentContext
     * @returns {boolean} false if property is allowed and true if it is rejected..
     */
    const checkPropertyAccess = function(propertyObj, roleList){
        if(!roleList){
            roleList = [];
        }

        let rejectProperty = false;
        if(propertyObj.templateOptions){
            if(propertyObj.templateOptions.acl){
                if(propertyObj.templateOptions.acl.reject){
                    let found = _.find(propertyObj.templateOptions.acl.reject, function(rejectedRole) {
                        for(let i=0; i < roleList.length; i++){
                            let userRole = roleList[i];
                            //If current role is in reject role..then reject the role..
                            if(userRole === rejectedRole){
                                return true;
                            }
                        }
                        return false;
                    });

                    //Now check if current role is also present in allow role list
                    if(found){
                        rejectProperty = true;
                        if(propertyObj.templateOptions.acl.allow) {
                            let found = _.find(propertyObj.templateOptions.acl.allow, function(allowedRole) {
                                for(let i=0; i < roleList.length; i++){
                                    let userRole = roleList[i];
                                    //If current role is in reject role..then reject the role..
                                    if(userRole === allowedRole){
                                        return true;
                                    }
                                }
                                return false;
                            });



                            if(found) {
                                rejectProperty = false;
                            }
                        }
                    }
                }
            }
        }



        return rejectProperty;

    };




	//TODO ADD ENTRY FOR NESTED DATA RELATED MODELS NOT DONE AT CLIENT SIDE IN ANGULAR FORMLY.
	/**
	 * Recursive function for generating models schema. and header.
	 * @param app
	 * @param header
	 * @param schema
	 * @param relations
	 * @param rootModelName model name of the root
	 * @param absoluteSchema {boolean} if the request is for absolute schema or getSchema
	 * @param roleList {[string]} list of roles assigned to current logged user..
     */
	var addNestedModelRelation = function(app, header, schema, relations, rootModelName, absoluteSchema, roleList){

		//Now adding  prop of belongTo and hasMany method to the header and schema respectfully...
		for(var relationName in relations){
			if(relations.hasOwnProperty(relationName)){
				var relationObj = relations[relationName];
				var modelName       = relationObj.model;
                //Flag to track if to reject property or accept prop..
                var rejectProperty = checkPropertyAccess(relationObj, roleList);

                if(rejectProperty){
                    //Skip this property..
                    continue;
                }

				//Only add relation if template option in the template option is present..
				if((relationObj.type === 'hasMany' ||  relationObj.type === 'hasAndBelongsToMany' ) && relationObj.templateOptions !== undefined){
					var nestedSchema = {};
					if(relationObj.type === "hasMany"){
						if(relationObj.through){
							nestedSchema.key = relationName;
							//Now cloning the object from templateOptions....
							nestedSchema.templateOptions = Object.assign({}, relationObj.templateOptions);
							if(relationObj.templateOptions.type){
								nestedSchema.type = relationObj.templateOptions.type;
								delete nestedSchema.templateOptions.type;
							}else{
								nestedSchema.type = 'arrayValue';
							}

							//Add model to be searched..
							nestedSchema.templateOptions.model = relationObj.through;

							var throughRelationName;
							var throughSearchId;
							var throughTemplateOptions = {};
							var throughModelObj = app.models[relationObj.through];
							var relatedModelRelationObj = throughModelObj.definition.settings.relations;
							for(var relatedModelRelation in relatedModelRelationObj){
								if(relatedModelRelationObj.hasOwnProperty(relatedModelRelation)){
									var relatedModel = relatedModelRelationObj[relatedModelRelation];
									if(modelName === relatedModel.model){
										throughRelationName = relatedModelRelation;
										if(relatedModel.templateOptions){
											throughTemplateOptions = relatedModel.templateOptions;
										}
									}
									else if(rootModelName === relatedModel.model){
										if(relatedModel.foreignKey){
											throughSearchId = relatedModel.foreignKey;
										}else{
											throughSearchId = rootModelName.toLowerCase() + "Id";
										}
									}
								}
							}
							//Now get nested schema str for the relational models..
							generateTemplateStr(app, relationObj.through, nestedSchema.templateOptions, roleList);

							var belongsToSchemaThrough = {
								type           : "belongsTo",
								key            : throughRelationName,
								templateOptions: throughTemplateOptions
							};

							//Model name of relational data..
							belongsToSchemaThrough.templateOptions.model = relationObj.model;

							if(nestedSchema.templateOptions.fields === undefined){
								nestedSchema.templateOptions.fields = [];
							}

							nestedSchema.templateOptions.fields.push(belongsToSchemaThrough);

							//Also add templateStr for related model of HasManyThrough
							generateTemplateStr(app, relationObj.model, belongsToSchemaThrough.templateOptions, roleList);


							/**
							 * HasManyThrough structure
							 * {
							 * 		relation: 'ingredients',
							 * 		through: 'RecipeIngredient'
							 * }
							 */
							//Push data to hasManyThrough array..
							schema.relations.hasManyThrough.push({
								//Relation of related model in though Model property name
								throughModelRelation: throughRelationName,
								through: relationObj.through,
								whereId:  throughSearchId,
								relationName: relationName
							});
						}else{
							nestedSchema.type = 'repeatSection';
							nestedSchema.key = relationName;
							nestedSchema.templateOptions = relationObj.templateOptions;
							nestedSchema.templateOptions.model = relationObj.model;
							//Now get nested schema str for the relational models..
							generateTemplateStr(app, relationObj.model, nestedSchema.templateOptions, roleList);

							//Now add nestedSchema to the schema object.
							schema.relations.hasMany.push(relationName);

						}
					}
					else{
						nestedSchema.type = 'repeatSection';
						nestedSchema.key = relationName;
						nestedSchema.templateOptions = relationObj.templateOptions;
						nestedSchema.templateOptions.model = relationObj.model;
						//Now get nested schema str for the relational models..
						generateTemplateStr(app, relationObj.model, nestedSchema.templateOptions, roleList);

						//Now add nestedSchema to the schema object.
						schema.relations.hasAndBelongsToMany.push(relationName);
					}
					schema.fields.push(nestedSchema);
				}
				if((relationObj.type === 'hasOne' || relationObj.type === 'belongsTo') && relationObj.templateOptions !== undefined){
					if(absoluteSchema){
						//Now add its properties to the header..
						header = addPropToHeader(app, relationObj.model, relationName,  header, true, roleList);
					}else{
						//Now add its properties to the header..
						header = addPropToHeader(app, relationObj.model, relationName,  header, false, roleList);
					}

					if(relationObj.type === "hasOne"){
						schema.relations.hasOne.push(relationName);
					}else{
						//Add this relation to the schema..
						schema.relations.belongsTo.push(relationName);
					}

					var belongsToSchema = {
						type           : "belongsTo",
						key            : relationName,
						templateOptions: relationObj.templateOptions
					};
					belongsToSchema.templateOptions.model      = relationObj.model;
					belongsToSchema.templateOptions.foreignKey = relationObj.foreignKey === "" ? relationName + 'Id' : relationObj.foreignKey;
					//Now add nested schema to the relational model.
					generateTemplateStr(app, relationObj.model, belongsToSchema.templateOptions, roleList);

					if(belongsToSchema.templateOptions.includeRelatedModel){
						//Now if model-> related model -> related model (belongTo data is requested)
						//If some related mode of related model is requested too.. then in this case.. call this method..
						var relatedModelObj = app.models[relationObj.model];
						var relatedModelRelations = relatedModelObj.definition.settings.relations;
						var relatedHeader = addPropToHeader(app, relationObj.model, '', [], false, roleList);

						belongsToSchema.templateOptions.relations = belongsToSchema.templateOptions.relations || {
							hasMany:[],
							belongsTo:[],
							hasManyThrough:[],
							hasAndBelongsToMany:[],
							hasOne:[]
						};
						//add schema
						addNestedModelRelation(app, relatedHeader, belongsToSchema.templateOptions, relatedModelRelations, relationObj.model);
					}
					//Now add this to the schema..
					schema.fields.push(belongsToSchema);
				}

			}
		}//for in loop..
	};


	/**
	 * Checks if the model has any relations property..
	 * @param app
	 * @param modelName
	 * @returns {boolean}
	 * //TODO THIS METHOD IS NOT NEEDED AND SHOULD BE REMOVED
     */
	var checkModelRelation = function(app, modelName){
		var modelObj = app.models[modelName];
		var relationFound = false;
		for(var relationName in modelObj.definition.settings.relations){
			if(modelObj.definition.settings.relations.hasOwnProperty(relationName)){
				relationFound = true;
				break;
			}
		}
		return relationFound;
	};


	/**
	 * Generate header by adding properties key names.
	 * @param app
	 * @param modelName
	 * @param prefix
	 * @param header
	 * @param absoluteSchema
	 * @param roleList
     * @returns {*|Array}
     */
	var addPropToHeader = function(app, modelName, prefix,  header, absoluteSchema, roleList){
		header = header || [];
        if(!absoluteSchema){
            absoluteSchema = false;
        }
		var modelObj = app.models[modelName],
		modelProperties = modelObj.definition.rawProperties,
		hiddenProperties = modelObj.definition.settings.hidden;
		for(var key in modelProperties){
			if(modelProperties.hasOwnProperty(key)){

				//Add only if template is defined.
				if(modelProperties[key].template !== undefined){
                    //Flag to track if to reject property or accept prop..
                    let rejectProperty = checkPropertyAccess(modelProperties[key].template, roleList);
                    //Only allow if reject prop value is false..
                    if(!rejectProperty){
                        var propIsHidden = false;
                        if(hiddenProperties){
                            //Now checkingif the value is a hidden prop.
                            for(var i=0; i<hiddenProperties.length; i++){
                                var prop = hiddenProperties[i];
                                if(prop ===  key){
                                    propIsHidden = true;
                                    break;
                                }
                            }
                        }
                        if(!propIsHidden){
                            if(prefix === ''){
                                //Add key to the header..
                                header.push(key);
                            }else{
                                if(absoluteSchema){
                                    header.push(prefix + '.' + key);
                                }else{
                                    header.push(prefix + '_' + key);
                                }

                            }

                        }
                    }

				}//if
			}
		}
		return header;
	};


	/**
	 * Generate template structure for data entry schema.
	 * @param app
	 * @param modelName
	 * @param schema
	 * @param roleList {[string]} list of roles for current logged user. Loopback current context.
     * @returns {*}
     */
	var generateTemplateStr = function(app, modelName, schema, roleList){
		if(!schema){
			schema = {};
			schema.model = modelName;
			schema.relations = {
				hasMany:[],
				belongsTo:[],
				hasManyThrough:[],
				hasAndBelongsToMany:[],
				hasOne:[]
			};
		}
		schema.fields   = [];
		const validationModelObj = helper.getValidationObj(modelName);
		//{validationsBackend, complexValidation}

		let
			validationObj,
			complexValidation,
			modelObj    = app.models[modelName],
			modelProperties = modelObj.definition.rawProperties;


		if(validationModelObj){
			if(validationModelObj.validationsBackend){
				validationObj = validationModelObj.validationsBackend;
			}

			if(validationModelObj.complexValidation){
				complexValidation = validationModelObj.complexValidation;
			}
		}



		let newValidationObj = {
			rules:{},
			messages:{}
		};

		for(var propertyName in modelProperties){
			if(modelProperties.hasOwnProperty(propertyName)){
				var propObj = modelProperties[propertyName].template;
				if(propObj !== undefined){
                    //Flag to track if to reject property or accept prop..
                    let rejectProperty = checkPropertyAccess(propObj, roleList);


                    //Add property only if rejectProperty value is false..
                    if(!rejectProperty){
                        propObj.key = propertyName;
                        //also add the validation to the object..
                        try{
                            var validationRules = validationObj.rules[propertyName];
                            var validationMessages = validationObj.messages[propertyName];

                            if(propObj.templateOptions && validationRules){
                                if(propObj.templateOptions.id){
                                    var validationName = propObj.templateOptions.id;
                                    //Get the validation object..
                                    newValidationObj.rules[validationName] = validationRules;
                                    newValidationObj.messages[validationName] = validationMessages;
                                }
                            }
                        }catch(err){
                            // Do nothing
                            // Validation is not defined in the model definition
                        }

                        schema.fields.push(propObj);
                    }
				}
			}
		}//for-in

        //This code is just for adding validation in schema..of relation properties..
        var modelRelation = modelObj.definition.settings.relations;
        for(var relationName in modelRelation){
			if(modelRelation.hasOwnProperty(relationName)){
				var relationObj = modelRelation[relationName].templateOptions;
				if(relationObj !== undefined){
					relationObj.key = relationName;
					//also add the validation to the object..
					try{
						var validationRules_ = validationObj.rules[relationName];
						var validationMessages_ = validationObj.messages[relationName];

						if(relationObj && validationRules_){
							if(relationObj.id){
								var validationName_ = relationObj.id;
								//Get the validation object..
								newValidationObj.rules[validationName_] = validationRules_;
								newValidationObj.messages[validationName_] = validationMessages_;
							}
						}

					}catch(err){
                        console.error(err);
						// Do nothing
						// Validation is not defined in the model definition
					}

				}
			}
		}//for-in


        //Now also add custom validation for facilitating array type validation or other complex validation..
        //Just copy direct validation in this case..
        if(complexValidation){
            if(complexValidation.rules){
                for(var key in complexValidation.rules){
                    if(complexValidation.rules.hasOwnProperty(key)){
                        newValidationObj.rules[key] = complexValidation.rules[key];
                        if(complexValidation.messages[key]){
                            newValidationObj.messages[key] = complexValidation.messages[key];
                        }

                    }
                }
            }
        }


		//Now adding validation obj..
		schema.validations = newValidationObj;
		return schema;
	};




	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init
	};
}; //module.exports
