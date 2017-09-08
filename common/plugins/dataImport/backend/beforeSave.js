'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	const Promise = require("bluebird");
	const moment = require("moment");
	//Method for storing before save..methods..
	//MEthod can be added externally through plugin method attached to beforeSave.
	const beforeSave = {
        /**
         *
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
         @param callback {Function}
         */
        addModelBrandId: function (sheetRowObj, callback) {
            const Brand = server.models["Brand"];
            const CarModel = server.models["CarModel"];
            if (sheetRowObj.Brand.data) {
                if (sheetRowObj.Brand.data.name) {
                    Brand.findOne({
                        where: {
                            name: sheetRowObj.Brand.data.name
                        }
                    })
                        .then(function (brand) {
                            if (brand) {
                                sheetRowObj.CarModel.data.brandId = brand.id;
                                callback(null);
                            }
                        })
                        .catch(function (error) {
                            callback(error);
                        });
                } else {
                    callback(new Error("Brand not found"));
                }
            } else {
                callback(new Error("Brand not found"));
            }
        },

        addTrimDetailIds: function(sheetRowObj, callback){
            const Brand = server.models["Brand"];
            const CarModel = server.models["CarModel"];
            const Fuel = server.models["Fuel"];
            const GearBox = server.models["GearBox"];
            const Trim = server.models["Trim"];
            let brandData, carModelData, fuelData, gearboxData;
            if(sheetRowObj.Brand.data){
                if(sheetRowObj.Brand.data.name){
                    Brand.findOne({
                        where: {
                            name: sheetRowObj.Brand.data.name
                        }
                    })
                        .then(function(brand){
                            if(brand){
                                brandData = brand;
                                if(sheetRowObj.CarModel.data){
                                    if(sheetRowObj.CarModel.data.name){
                                        return CarModel.findOne({
                                            where: {
                                                name: sheetRowObj.CarModel.data.name
                                            }
                                        });
                                    }
                                }
                            }
                        })
                        .then(function(carModel){
                            if(carModel){
                                carModelData = carModel;
                                if(sheetRowObj.Fuel.data){
                                    if(sheetRowObj.Fuel.data.name){
                                        return Fuel.findOne({
                                            where: {
                                                name: sheetRowObj.Fuel.data.name
                                            }
                                        });
                                    }
                                }
                            }
                        })
                        .then(function(fuel){
                            if(fuel){
                                fuelData = fuel;
                                if(sheetRowObj.GearBox.data){
                                    if(sheetRowObj.GearBox.data.name){
                                        return GearBox.findOne({
                                            where: {
                                                name: sheetRowObj.GearBox.data.name
                                            }
                                        });
                                    }
                                }
                            }
                        })
                        .then(function(gearBox){
                            if(gearBox){
                                gearboxData = gearBox;
                            }
                        })
                        .then(function(){
                            if(brandData){
                                sheetRowObj.Trim.data.brandId = brandData.id;
                            }
                            if(carModelData){
                                sheetRowObj.Trim.data.carModelId = carModelData.id;
                            }
                            if(fuelData){
                                sheetRowObj.Trim.data.fuelId = fuelData.id;
                            }
                            if(gearboxData){
                                sheetRowObj.Trim.data.gearBoxId = gearboxData.id;
                            }
                            callback(null);
                        })
                        .catch(function(error){
                            callback(error);
                        });
                }
            }
        },
        addCarDetailIds: function (sheetRowObj, callback){
            const Brand = server.models["Brand"];
            const CarModel = server.models["CarModel"];
            const Fuel = server.models["Fuel"];
            const GearBox = server.models["GearBox"];
            const Trim = server.models["Trim"];
            const Color = server.models["Color"];
            let brandData, carModelData, fuelData, gearboxData, trimData;
            if (sheetRowObj.Brand.data) {
                if (sheetRowObj.Brand.data.name) {
                    Brand.findOne({
                        where: {
                            name: sheetRowObj.Brand.data.name
                        }
                    })
                        .then(function (brand) {
                            if (brand) {
                                brandData = brand;
                                if (sheetRowObj.CarModel.data) {
                                    if (sheetRowObj.CarModel.data.name) {
                                        return CarModel.findOne({
                                            where: {
                                                name: sheetRowObj.CarModel.data.name
                                            }
                                        });
                                    } else {
                                        //callback(new Error("Car Model not found"));
                                    }
                                } else {
                                    //callback(new Error("Car Model not found"));
                                }
                            } else {
                                throw new Error("Brand not found");
                            }
                        })
                        .then(function (carModel) {
                            if (carModel) {
                                carModelData = carModel;
                                if (sheetRowObj.Fuel.data) {
                                    if (sheetRowObj.Fuel.data.name) {
                                        return Fuel.findOne({
                                            where: {
                                                name: sheetRowObj.Fuel.data.name
                                            }
                                        });
                                    } else {
                                        //callback(new Error("Fuel not found"));
                                    }
                                } else {
                                    //callback(new Error("Fuel not found"));
                                }
                            } else {
                                if (sheetRowObj.Fuel.data) {
                                    if (sheetRowObj.Fuel.data.name) {
                                        return Fuel.findOne({
                                            where: {
                                                name: sheetRowObj.Fuel.data.name
                                            }
                                        });
                                    } else {
                                        //callback(new Error("Fuel not found"));
                                    }
                                } else {
                                    //callback(new Error("Fuel not found"));
                                }
                                //throw new Error("Car Model not found");
                            }
                        })
                        .then(function (fuel) {
                            if (fuel) {
                                fuelData = fuel;
                                if (sheetRowObj.GearBox.data) {
                                    if (sheetRowObj.GearBox.data.name) {
                                        return GearBox.findOne({
                                            where: {
                                                name: sheetRowObj.GearBox.data.name
                                            }
                                        });
                                    } else {
                                        // callback(new Error("Gearbox not found"));
                                    }
                                } else {
                                    //callback(new Error("Gearbox not found"));
                                }
                            } else {
                                //throw new Error("Fuel not found");
                                if (sheetRowObj.GearBox.data) {
                                    if (sheetRowObj.GearBox.data.name) {
                                        return GearBox.findOne({
                                            where: {
                                                name: sheetRowObj.GearBox.data.name
                                            }
                                        });
                                    }
                                }
                            }
                        })
                        .then(function (gearbox) {
                            if (gearbox) {
                                gearboxData = gearbox;
                                if (sheetRowObj.Trim.data) {
                                    if (sheetRowObj.Trim.data.name) {
                                        return Trim.findOne({
                                            where: {
                                                name: sheetRowObj.Trim.data.name
                                            }
                                        });
                                    }
                                }
                            } else {
                                if (sheetRowObj.Trim.data) {
                                    if (sheetRowObj.Trim.data.name) {
                                        return Trim.findOne({
                                            where: {
                                                name: sheetRowObj.Trim.data.name
                                            }
                                        });
                                    }
                                }
                            }
                        })
                        .then(function (trim) {
                            if (trim) {
                                trimData = trim;
                            }
                        })
                        .then(function () {
                            if (brandData) {
                                sheetRowObj.Car.data.brandId = brandData.id;
                            }
                            if (carModelData) {
                                if (carModelData.id) {
                                    sheetRowObj.Car.data.carModelId = carModelData.id;
                                }
                            }
                            if (fuelData) {
                                sheetRowObj.Car.data.fuelId = fuelData.id;
                            }
                            if (gearboxData) {
                                sheetRowObj.Car.data.gearBoxId = gearboxData.id;
                            }
                            if (trimData) {
                                sheetRowObj.Car.data.trimId = trimData.id;
                            }
                            callback(null);
                        })
                        .catch(function (error) {
                            callback(error);
                        });
                } else {
                    callback(new Error("Brand not found"));
                }
            } else {
                callback(new Error("Brand not found"));
            }
        },
		addCategoryCityId: function(sheetRowObj, callback){
            const BreakdownCategory = server.models["BreakdownCategory"];
            const Breakdown = server.models["Breakdown"];
            const City = server.models["City"];
            let categoryData, cityData;
            if(sheetRowObj.BreakdownCategory.data){
                if(sheetRowObj.BreakdownCategory.data.name){
                    BreakdownCategory.findOne({
                        where: {
                            name: sheetRowObj.BreakdownCategory.data.name
                        }
                    })
                        .then(function(breakdownCategory){
                            if(breakdownCategory){
                                categoryData = breakdownCategory;
                                if(sheetRowObj.City.data){
                                    if(sheetRowObj.City.data.name){
                                        return City.findOne({
                                            where:{
                                                name: sheetRowObj.City.data.name
                                            }
                                        });
                                    }
                                }
                            } else{
                                throw new Error("Breakdown Category not found");
                            }
                        })
                        .then(function(city){
                            if(city){
                                cityData = city;
                            }
                        })
                        .then(function(){
                            sheetRowObj.Breakdown.data.cityId = cityData.id;
                            sheetRowObj.Breakdown.data.breakdownCategoryId = categoryData.id;
                            callback(null);
                        })
                        .catch(function (error) {
                            if(error){
                                callback(error);
                            }
                        });
                } else{
                    callback(new Error("Breakdown Category not found"));
                }
            } else{
                callback(new Error("Breakdown Category not found"));
            }
        },
		addBreakdownLatLang: function(sheetRowObj, callback){
		    const Breakdown = server.models["Breakdown"];
		    if(sheetRowObj.Breakdown.data){
		        if(sheetRowObj.Breakdown.data.latitude && sheetRowObj.Breakdown.data.longitude){
		            sheetRowObj.Breakdown.data.latlong = [sheetRowObj.Breakdown.data.latitude, sheetRowObj.Breakdown.data.longitude];
                    callback(null);
                }
            }
        },
        addEmergencyCityId: function(sheetRowObj, callback){
		    const EmergencyCategory = server.models["EmergencyCategory"];
		    const Emergency = server.models["Emergency"];
		    const City = server.models["City"];
		    let cityData, emergencyCategoryData;
            if(sheetRowObj.EmergencyCategory.data){
                if(sheetRowObj.EmergencyCategory.data.name){
                    EmergencyCategory.findOne({
                        where: {
                            name: sheetRowObj.EmergencyCategory.data.name
                        }
                    })
                        .then(function(emergencyCategory){
                            if(emergencyCategory){
                                emergencyCategoryData = emergencyCategory;
                                if(sheetRowObj.City.data){
                                    if(sheetRowObj.City.data.name){
                                        return City.findOne({
                                            where:{
                                                name: sheetRowObj.City.data.name
                                            }
                                        });
                                    }
                                }
                            } else{
                                throw new Error("Emergency Category not found");
                            }
                        })
                        .then(function(city){
                            if(city){
                                cityData = city;
                            }
                        })
                        .then(function(){
                            sheetRowObj.Emergency.data.cityId = cityData.id;
                            sheetRowObj.Emergency.data.emergencyCategoryId = emergencyCategoryData.id;
                            callback(null);
                        })
                        .catch(function(error){
                            callback(error);
                        });
                } else{
                    callback(new Error("Emergency Category not found"));
                }
            } else{
                callback(new Error("Emergency Category not found"));
            }
        },

        addEmergencyLatLng: function(sheetRowObj, callback){
            const Emergency = server.models["Emergency"];
            if(sheetRowObj.Emergency.data){
                if(sheetRowObj.Emergency.data.latitude && sheetRowObj.Emergency.data.longitude){
                    sheetRowObj.Emergency.data.latlong = [sheetRowObj.Emergency.data.latitude, sheetRowObj.Emergency.data.longitude];
                    callback(null);
                }
            }
        },

		addCityId: function(sheetRowObj, callback){
            const City = server.models["City"];
            const Area = server.models["Area"];
            if(sheetRowObj.City.data){
                if(sheetRowObj.City.data.name){
                    City.findOne({
                        where: {
                            name: sheetRowObj.City.data.name
                        }
                    })
                        .then(function(city){
                            if(city){
                                sheetRowObj.Area.data.cityId = city.id;
                                callback(null);
                            } else{
                                throw new Error("City not found");
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        });
                } else{
                    callback(new Error("City not found"));
                }
            } else{
                callback(new Error("City not found"));
            }
        },
		addCustomerCityId: function (sheetRowObj, callback) {
			const City = server.models["City"];
			const Area = server.models["Area"];
			let city;
			if(sheetRowObj.Area.data){
				if(sheetRowObj.Area.data.name){
					 City.findOne({
						 where:{
							 name: "Delhi"
						 }
					 })
					 .then(function (_city) {
					     if(_city){
                             city = _city;
                             return Area.findOne({
                                 where:{
                                     name: sheetRowObj.Area.data.name
                                 }
                             });
                         }else{
					         throw new Error("City not found");
                         }
					 })
                     .then(function (area) {
                         if(!area){
                             return Area.create({
                                 name: sheetRowObj.Area.data.name,
                                 cityId: city.id
                             });
                         }else{
                             return area;
                         }

                     })
                     .then(function (area) {
                         if(area){
                             sheetRowObj.Customer.data.areaId = area.id;
                             sheetRowObj.Customer.data.cityId = city.id;
                         }
                         callback(null);
                     })
					 .catch(function (error) {
						 callback(error);
					 });
				}else{
					callback(new Error("Area not found"));
				}
			}else{
                callback(new Error("Area not found"));
			}

        },
        /**
         *
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
         @param callback {Function}
         */
		beforeCustomerSave: function(sheetRowObj, callback){
            const Customer = server.models["Customer"];
            if(sheetRowObj.Customer){
                if(sheetRowObj.Customer.data){
                    if(!sheetRowObj.Customer.data.mobileNumber){
                        if(sheetRowObj.Customer.data.accountNumber){
                            sheetRowObj.Customer.data.mobileNumber = sheetRowObj.Customer.data.accountNumber;
                        }
                    }
                }
             }

            if(sheetRowObj.Customer.data.firstName){
                sheetRowObj.Customer.data.firstName = sheetRowObj.Customer.data.firstName.replace(/\*+/,'');
            }

            if(sheetRowObj.Customer.data.lastName){
                sheetRowObj.Customer.data.lastName = sheetRowObj.Customer.data.lastName.replace(/\*+/,'');
            }


             //Sanitize mobile number..
            sheetRowObj.Customer.data.mobileNumber = sheetRowObj.Customer.data.mobileNumber.replace(/\/\d+$/,'');


            sheetRowObj.Customer.where.mobileNumber = sheetRowObj.Customer.data.mobileNumber;

            if(sheetRowObj.Customer.data.subscription_time){
                sheetRowObj.Customer.data.subscription_time = sheetRowObj.Customer.data.subscription_time || "07:30";
                sheetRowObj.Customer.data.subscription_time = moment.utc(sheetRowObj.Customer.data.subscription_time, "hh:mm").toDate();

            }

            if(sheetRowObj.Customer.data.subscription_startDate){
                sheetRowObj.Customer.data.subscription_startDate = moment.utc(sheetRowObj.Customer.data.subscription_startDate, "DD-MM-YYYY").toDate();
            }


            sheetRowObj.Customer.data.type = "individual";
            sheetRowObj.Customer.data.subscriptionType = "subscription";
            sheetRowObj.Customer.data.added   = moment.utc().toDate();
            sheetRowObj.Customer.data.updated = moment.utc().toDate();



            callback(null);
		}
	};

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }

	return{
		beforeSave: beforeSave
	};
};