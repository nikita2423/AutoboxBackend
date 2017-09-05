'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
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

	const Excel = require('exceljs');
	const Promise = require("bluebird");
	const _ = require("lodash");
	const async = require("async");
	const {beforeSave} = require("./beforeSave")(server, databaseObj, helper, packageObj);
	const {afterSave} = require("./afterSave")(server, databaseObj, helper, packageObj);
	const uploadFile = require("./uploadFile")(server, databaseObj, helper, packageObj);

	const init = function(){
		uploadFile.init(importData);
		//importData();
	};

	const {sheetColNumber} = packageObj.constants;

	//Method to import file to a path..
	const importData = function (filePath, callback) {
		// read from a file
		let workbook = new Excel.Workbook();
		workbook.xlsx.readFile(filePath)
			.then(function(fileData) {
				const sheetConfig = {};
				fileData.eachSheet(function(worksheet, sheetId) {
					//Model save data.. storage. .
					sheetConfig[worksheet.name] = sheetConfig[worksheet.name] || [];
					//Get workSheet settings
					processWorkSheet(worksheet, sheetConfig);
				});
				//Now save sheets to server..
				saveSheetsToServer(sheetConfig, callback);
			})
			.catch(function (error) {
				console.error(error);
				callback(error);
			});
	};


	/**
	 * Save final prepared sheets to database server..
	 * @param sheetConfig
	 * @param callback
	 */
	const saveSheetsToServer = function (sheetConfig, callback) {
		const multipleSheetsSeries = [];
		//Now saving sheets to the server..
		for(let sheetName in sheetConfig){
			//console.log("\n Saving data for Sheet: " + sheetName);
			if(sheetConfig.hasOwnProperty(sheetName)){
				multipleSheetsSeries.push( function (callback) {
					const sheetArray = sheetConfig[sheetName];
					const series = [];
					sheetArray.forEach(function (sheetRowObj) {
						series.push(function (callback) {
							saveSheetRowToServer(sheetRowObj, callback);
						});
					});
					//Now return the series..
					async.waterfall(series, function (error, dataLength) {
						if(error){
							callback(error);
						}else{
							//console.log("\tSheet: " + sheetName + "  Data saved");
							callback(null);
						}
					});
				});
			}
		}

		async.waterfall(multipleSheetsSeries, function (error, data) {
			if(error){
				console.error(error);
				callback(error);
			}else{
				//console.log("Data saved");
				callback(null, "DataImport: Done file uploaded. data saved");
			}
		});
	};

	/**
	 * Saving a row of sheet to server..
	 * @param sheetRowObj
	 * {
		  "MODEL NAME":{
				instance: "MODEL INSTANCE",
				where:{},
				data:{
					//Data which is going to be saved
				},
				results:{
					//server results
				},
				beforeSave:[]
		  }
	    }
	 * @param callback
	 */
	const saveSheetRowToServer = function(sheetRowObj, callback){
		const series = [];
		for(let modelName in sheetRowObj){
			if(sheetRowObj.hasOwnProperty(modelName)){
				series.push(function (callback) {
					const modelObj = sheetRowObj[modelName];
					if(modelObj){
						const beforeSaveSeries = [];
						if(modelObj.beforeSave){
							if(modelObj.beforeSave.length){
								//Now run each before save..
								modelObj.beforeSave.forEach(function (beforeSaveMethodName) {
									if(beforeSave){
										if(beforeSave[beforeSaveMethodName]){
											beforeSaveSeries.push(function (callback) {
												//Now call the method..
												beforeSave[beforeSaveMethodName](sheetRowObj, callback);
											});
										}
									}
								});
							}
						}

						if(beforeSaveSeries.length){
							//Wait for async call
							async.waterfall(beforeSaveSeries, function (error, data) {
								if(error){
									callback(error);
								}else{
									saveModelToServer(sheetRowObj, modelObj, modelName, function(error, data){
										if(error){
											callback(error);
										}else{
											//Call after save if any..present..
											const afterSaveSeries = [];
											if(modelObj.afterSave){
												if(modelObj.afterSave.length){
													//Now run each before save..
													modelObj.afterSave.forEach(function (afterSaveMethodName) {
														if(afterSave){
															if(afterSave[afterSaveMethodName]){
																afterSaveSeries.push(function (callback) {
																	//Now call the method..
																	afterSave[afterSaveMethodName](sheetRowObj, callback);
																});
															}
														}
													});
												}
											}

											//Call after save if any..
											if(afterSaveSeries){
												if(afterSaveSeries.length){
													//Saving modelObj data..
													async.waterfall(afterSaveSeries, function (error, data) {
														if(error){
															callback(error);
														}else{
															callback(null);
														}
													});

												}else{
													callback(null);
												}
											}else{
												callback(null);
											}
										}
									});
								}
							});
						}else{
							//Just save..data
							if(modelObj.instance){
								saveModelToServer(sheetRowObj, modelObj, modelName, callback);
							}else{
								callback(new Error("Instance of model " + modelName + " not present"));
							}
						}
					}
				});
			}
		}

		async.waterfall(series, function (error, data) {
			if(error){
				callback(error);
			}else{
				callback(null);
			}
		});
	};



	/**
	 * Save each model to server..
	 * @param sheetRowObj
	 * @param modelObj
	 * @param modelName
	 * @param callback
	 */
	const saveModelToServer = function (sheetRowObj, modelObj, modelName, callback) {
		//Now if
		if(_.isEmpty(modelObj.where)){
			//Create data..
			modelObj.instance.create(modelObj.data)
				.then(function (dataObj) {
					addSheetDataToResults(sheetRowObj, modelName, dataObj);
					//console.log(modelName + " Data saved successfully");
					callback(null);
				})
				.catch(function (error) {
					console.log(error);
					callback(error);
				});
		}else{
			//Find first and update if present..
			modelObj.instance.findOne({
				where: modelObj.where
			})
				.then(function (dataObj) {
					if(dataObj){
						//console.log(modelObj.data);
						modelObj.data.id = dataObj.id ;
					}
					//Return promise object..
					return modelObj.instance.upsert(modelObj.data);
				})
				.then(function (dataObj) {
					addSheetDataToResults(sheetRowObj, modelName, dataObj);
					//console.log(modelName + " Data updated successfully");
					callback(null);
				})
				.catch(function (error) {
					console.log(error);
					callback(error);
				});
		}
	};


	const addSheetDataToResults = function (sheetRowObj, modelName, results) {
		if(sheetRowObj){
			if(sheetRowObj[modelName]){
				if(results){
					sheetRowObj[modelName].results = results;
				}
			}
		}
	};


	/**
	 * Prepare sheet configuration..
	 * @param workSheetConfig {{}} Configuration file for current working worksheet
	 * @param headerRow {{}} Contains sheet header row object
	 */
	const prepareSheetStr = function(workSheetConfig, headerRow){
		if(workSheetConfig && headerRow){
			headerRow.eachCell(function(cell, colNumber) {
				//For each column value find the respective config..item..
				workSheetConfig.settings.structure.forEach(function (modelStructure) {
					if(modelStructure.properties){
						modelStructure.properties.forEach(function (propertyObj) {
							if(propertyObj.sheetColName && cell.value){
								if(_.toLower(propertyObj.sheetColName).trim() === _.toLower(cell.value.trim())){
									//Now write column index..
									propertyObj[sheetColNumber] = colNumber;
								}
							}
						});
					}
				});
			});

			return workSheetConfig;
		}else{
			console.error("Worksheet or header file not found");
		}
	};


	/**
	 * Process each worksheet
	 * @param worksheet
	 * @param sheetConfig
	 * {
	    "SheetName":
	    [
	        //SheetRow
	        {
				"MODEL NAME":{
					instance: "MODEL INSTANCE",
					where:{},
					data:{
						//Data which is going to be saved
					},
					results:{
						//server results
					},
					beforeSave:[]
				}
			}
		]
	 }
	 */
	const processWorkSheet = function (worksheet, sheetConfig) {
		const worksheetSettings = packageObj.worksheet;
		if(worksheetSettings !== null){
			if(worksheetSettings[worksheet.name]){
				sheetConfig[worksheet.name] = sheetConfig[worksheet.name] || [];
				let workSheetConfig = {
					settings: _.clone(worksheetSettings[worksheet.name]),
					sheetName: worksheet.name
				};

				let isDataValid = true;

				worksheet.eachRow(function (row, rowNumber) {
					//Check if all data are valid or not..
					if(!isDataValid){
						return false;
					}
					//If rowNumber === 1 //prepare sheet structure first..
					if(rowNumber === 1){
						//Prepare worksheet..data..
						prepareSheetStr(workSheetConfig, row);
					}else{
						/*{
							"MODEL NAME":{
								instance: "MODEL INSTANCE",
								where:{},
								data:{
									//Data which is going to be saved
								},
								results:{
									//server results
								},
								beforeSave:[],
								config:{
									rowNumber
								}
							}
						}*/
						let dataObj = {

						};
						//Now start writing data to databases..
						workSheetConfig.settings.structure.forEach(function (structureObj) {
							if(structureObj){
								//Now for each row loop through the settings structure and insert or update..
								//let databaseModel = server[structureObj.model];
								let where = {};
								let modelObj = {};
								if(structureObj.properties){
									structureObj.properties.forEach(function (property) {
										if(property[sheetColNumber] !== undefined){
											if(row.getCell(property[sheetColNumber])){
												const type = row.getCell(property[sheetColNumber]).type;
												//Get column value..
												let columnValue;
												if(type !== undefined){
													if(type === 5){
														//Hyperlink type mail or html..get string value..
														columnValue = row.getCell(property[sheetColNumber]).value.text;
													}else{
														columnValue = row.getCell(property[sheetColNumber]).value;
													}
												}

												if(property.unique){
													where[property.modelProperty] = columnValue;
												}
												modelObj[property.modelProperty] = columnValue;
											}
										}
									});

									dataObj[structureObj.model] = dataObj[structureObj.model] || {};
									dataObj[structureObj.model].instance = server.models[structureObj.model];
									dataObj[structureObj.model].where = where;
									dataObj[structureObj.model].data = modelObj;
									dataObj[structureObj.model].beforeSave = structureObj.beforeSave || [];
									dataObj[structureObj.model].afterSave = structureObj.afterSave || [];
									dataObj[structureObj.model].config = {
										rowNumber: rowNumber,
										parent: sheetConfig
									};
								}
							}
						});
						//Now add data to sheet array for later save..
						sheetConfig[worksheet.name].push(dataObj);
					}
				});
			}
		}
	};




	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init,
		beforeSave: beforeSave,
		afterSave: afterSave
	};
}; //module.exports
