'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	const Promise = require("bluebird");
	const async = require("async");
	const afterSave = {

		addColorIds: function(sheetRowObj, callback) {
            const Color = server.models["Color"];
            const Car = server.models["Car"];
            let colorList = [];
            var series = [];
			/* Car.hasAndBelongsToMany(Color);
			 Color.hasAndBelongsToMany(Car);*/
            if (sheetRowObj.Car.data) {
                if (sheetRowObj.Car.results) {
                    if (sheetRowObj.Car.results.color1) {
                        Color.findOne({
                            where: {
                                name: sheetRowObj.Car.results.color1
                            }
                        })
                        /*    .then(function (color) {
                                if (color) {
                                    //colorList.push(color);
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);

                                    //return connectData("colors", "car", sheetRowObj.Car.results, color, callback);
                                }

                            })
                            .then(function () {
                            	if(sheetRowObj.Car.results.color2){
                            		return Color.findOne({
										where: {
											name: sheetRowObj.Car.results.color2
										}
									});
								}
                            })
							.then(function(color){
								if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
								}
							})
							.then(function(){
                                if(sheetRowObj.Car.results.color3){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color3
                                        }
                                    });
                                }
							})
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color4){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color4
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color5){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color5
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color6){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color6
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color7){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color7
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color8){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color8
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color9){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color9
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color10){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color10
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color11){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color11
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color12){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color12
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color13){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color13
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color14){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color14
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color15){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color15
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color16){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color16
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color17){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color17
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color18){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color18
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color19){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color19
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color20){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color20
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
                            .then(function(){
                                if(sheetRowObj.Car.results.color21){
                                    return Color.findOne({
                                        where: {
                                            name: sheetRowObj.Car.results.color21
                                        }
                                    });
                                }
                            })
                            .then(function(color){
                                if(color){
                                    return connectData("colors", "Car", sheetRowObj.Car.results, color);
                                }
                            })
							.then(function () {
								callback(null);
                            })*/
							.then(function(color){
								if(color){
									colorList.push(color);
								}
							 if(sheetRowObj.Car.results.color2){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color2
							 }
							 });
							 }
							 })
								.then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color3){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color3
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color4){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color4
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color5){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color5
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color6){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color6
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color7){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color7
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color8){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color8
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color9){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color9
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color10){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color10
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color11){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color11
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color12){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color12
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color13){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color13
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color14){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color14
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color15){
							 return Color.findOne({
							 where: {
							 name: sheetRowObj.Car.results.color15
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color16){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color16
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color17){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color17
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color18){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color18
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color19){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color19
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color20){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color20
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 if(sheetRowObj.Car.results.color21){
							 return Color.findOne({
							 where:{
							 name: sheetRowObj.Color.results.color21
							 }
							 });
							 }
							 })
							 .then(function(color){
							 if(color){
							 colorList.push(color);
							 }
							 colorList.forEach(function(colorInstance){
							 series.push(
							 connectData("colors", "Car", sheetRowObj.Car.results, colorInstance)
							 .then(function(){
							 	callback(null);
								 })
							 );
							 });

							Promise.all(series).then(function(){
								callback(null);
							})
							 })

                            .catch(function (error) {
                                callback(error);
                            });
                    } else {
                        callback(null);
                    }
                } else {
                    callback(null);
                }
            }
        }
	};



	const connectData = function(relationName, modelName, mainModelInstance, relatedModelInstance){
		return new Promise(function (resolve, reject) {
            const relatedModel = server.models["Color"];
            mainModelInstance[relationName].add(relatedModelInstance)
                .then(function(savedData){

                    //Now save the instance of data in the dataInstance
                    /**
                     * NOW DO SOMETHING HERE TOO...
                     * Now add this values to each models..
                     *by inserting a _ character as suffix..
                     */


                    //console.log("Related model name", relationName+"_", mainModelInstance[relationName+"_"]);
                    if(mainModelInstance[relationName+"_"] instanceof Array){
                        mainModelInstance[relationName+"_"] =  {};
                    }
                    mainModelInstance[relationName+"_"] = mainModelInstance[relationName+"_"] || {};
                    //first check if related data is already not present..
                    //store data like object..
                    /**
                     * {
             *      id342: true,
             *      id232: true
             * }
                     * @type {boolean}
                     */
                    mainModelInstance[relationName + "_"][relatedModelInstance.id] = true;
                    //Now just updating the given relation attribute in the model..
                    //persistedModel.updateAttribute(name, value, callback)
                    return mainModelInstance.updateAttribute(relationName + "_", mainModelInstance[relationName + "_"]);
                })
				.then(function (updatedData) {
					resolve();
                   /* var relatedModelRelationName;
                    var relatedModelRelationProp;
					//Now the related model name..relation name
					var relatedModelRelationObj = relatedModel.definition.settings.relations;
					for(var relatedRelationName in relatedModelRelationObj){
						if(relatedModelRelationObj.hasOwnProperty(relatedRelationName)){
							var relatedRelationProp = relatedModelRelationObj[relatedRelationName];
							if(relatedRelationProp.model === modelName){
								relatedModelRelationName = relatedRelationName;
								relatedModelRelationProp = relatedRelationProp;
								break;
							}
						}
					}*/

					/*if(relatedModelRelationName) {
						//If relation value is in instance of array the change it to object..
						if (relatedModelRelationName[relatedModelRelationName + "_"] instanceof Array) {
							relatedModelRelationName[relatedModelRelationName + "_"] = {};
						}

						relatedModelInstance[relatedModelRelationName + "_"] = relatedModelInstance[relatedModelRelationName + "_"] || {};
						//Now add data to this model too..
						relatedModelInstance[relatedModelRelationName + "_"][mainModelInstance.id] = true;

						//Now updating the property.. of the related value..
						relatedModelInstance.updateAttribute(relatedModelRelationName + "_", relatedModelInstance[relatedModelRelationName + "_"], function (err, value) {
							if (err) {
								console.error(err);
								callback(err);
							} else {
								console.info("saving related value", value);
								//Do nothing.. return async callback..success..
								callback();
							}
						});
					}*/

                })
                .catch(function(err){
                    reject(err);
                });
        });
	};

	return {
		afterSave: afterSave
	};
};