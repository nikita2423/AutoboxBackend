/**
 * Created by nikita on 24/7/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const GeoPoint = require("geopoint");
    const _ = require("lodash");
    const Promise = require("bluebird");

    var init = function(){
        findAllBrandMethod();
        findAllModelsMethod();
        findAllFuelMethod();
        findAllGearboxMethod();
        findAllTrimMethod();
        findAllColorsMethod();
        fetchDealerDetailsMethod();
        saveTrendingBrandMethod();
        findAllBreakdownMethod();
        fetchNearestServiceCenterMethod();
        findAllEmergenciesMethod();
        fetchDealersForBrandMethod();
        saveVehicleDetailsMethod();
        sendMessageMethod();
        fetchServiceTypeMethod();
        createServiceBookingMethod();
        createCustomerQuoteMethod();
        findAllCustomerQuoteMethod();
        fetchQuoteReplyFromDealerMethod();
        sendFeedbackMethod();
        addServiceMethod();
        findAllServiceMethod();
    };

    const findAllBrandMethod = function(){
        const Brand = databaseObj.Brand;
        Brand.findAll = findAllBrands;
        Brand.remoteMethod("findAll", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "Object"
                }
            ],
            returns:{
                arg: "brandList", type: "Object", root: true
            }
        });
    };

    const findAllModelsMethod = function(){
      const CarModel = databaseObj.CarModel;
      CarModel.findAllModels = findAllModels;
      CarModel.remoteMethod("findAllModels", {
         accepts: [
             {
                 arg: 'ctx',
                 type: 'object',
                 http: {
                     source: 'context'
                 }
             },
             {
                 arg: "filter", type: "Object"
             }
         ],
          returns:{
             arg:"carModelList", type: "Object", root: true
          }
      });

    };


    const findAllFuelMethod = function(){
        const Car = databaseObj.Car;
        Car.findAllFuel = findAllFuel;
        Car.remoteMethod("findAllFuel", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "Object"
                }
            ],
            returns:{
                arg:"fuelList", type: "array", root: true
            }
        });
    };


    const findAllGearboxMethod = function(){
        const Car = databaseObj.Car;
        Car.findAllGearbox = findAllGearbox;
        Car.remoteMethod("findAllGearbox", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "Object"
                }
            ],
            returns:{
                arg:"gearboxList", type: "array", root: true
            }
        });
    };

    const findAllTrimMethod = function(){
        const Car = databaseObj.Car;
        Car.findAllTrim = findAllTrim;
        Car.remoteMethod("findAllTrim", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "Object"
                }
            ],
            returns:{
                arg:"trimList", type: "Object", root: true
            }
        });
    };

    const findAllColorsMethod = function(){
        const Car = databaseObj.Car;
        Car.findAllColors = findAllColors;
        Car.remoteMethod("findAllColors", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "Object"
                }
            ],
            returns:{
                arg:"colorsList", type: "Object", root: true
            }
        });
    };

    const fetchDealerDetailsMethod = function(){
        const Dealer = databaseObj.Dealer;
        Dealer.fetchDealerDetail = fetchDealerDetail;
        Dealer.remoteMethod("fetchDealerDetail", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "dealerId", type: "Object"
                }
            ],
            returns: {
                arg: "dealerObj", type: "Dealer", root: true
            }
        });
    };

    const saveTrendingBrandMethod = function(){
      const Brand = databaseObj.Brand;
      Brand.saveTrendingBrand = saveTrendingBrand;
      Brand.remoteMethod("saveTrendingBrand", {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg: "brandId", type: "string"
              }
          ],
          returns: {
              arg: "response", type: "object", root: true
          }
      });
    };


    const findAllBreakdownMethod = function(){
        const Breakdown = databaseObj.Breakdown;
        Breakdown.findAll = findAllBreakdown;
        Breakdown.remoteMethod("findAll", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "lat", type: "number"
                },
                {
                    arg: "lang", type: "number"
                }
            ],
            returns: {
                arg: "breakdownList", type: "array", root: true
            }
        });
    };


    const fetchNearestServiceCenterMethod = function(){
        const Breakdown = databaseObj.Breakdown;
        Breakdown.fetchNearestServiceCenter = fetchNearestServiceCenter;
        Breakdown.remoteMethod("fetchNearestServiceCenter", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                  arg: "brandId", type: "string"
                },
                {
                    arg: "lat", type: "number"
                },
                {
                    arg: "lang", type: "number"
                }
            ],
            returns: {
                arg: "breakdown", type: "Breakdown", root: true
            }
        });
    };

    const findAllEmergenciesMethod = function(){
        const Emergency = databaseObj.Emergency;
        Emergency.findAll = findAllEmergencies;
        Emergency.remoteMethod("findAll", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "lat", type: "number"
                },
                {
                    arg: "lang", type: "number"
                }
            ],
            returns: {
                arg: "emergencyList", type: "array", root: true
            }
        });
    };

    const fetchDealersForBrandMethod = function(){
      const Dealer = databaseObj.Dealer;
      Dealer.fetchDealersForBrand = fetchDealersForBrand;
      Dealer.remoteMethod("fetchDealersForBrand", {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg: "brandId", type: "string"
              },
              {
                  arg:"lastDate", type: "string"
              }
          ],
          returns: {
              arg: "dealerList", type: "object", root: true
          }
      });
    };

    const saveVehicleDetailsMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.saveVehicleDetails = saveVehicleDetails;
        VehicleDetail.remoteMethod("saveVehicleDetails", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "vehicleDetailObj", type: "object"
                }
            ],
            returns: {
                arg: "vehicleDetailObj", type: "VehicleDetail", root: true
            }
        });
    };

    const sendMessageMethod = function(){
        const CustomerMessage = databaseObj.CustomerMessage;
        CustomerMessage.sendMessage = sendMessage;
        CustomerMessage.remoteMethod("sendMessage", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "customerMessageObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const fetchServiceTypeMethod = function(){
        const ServiceType = databaseObj.ServiceType;
        ServiceType.fetchServiceType = fetchServiceType;
        ServiceType.remoteMethod("fetchServiceType", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "object"
                }
            ],
            returns: {
                arg: "serviceList", type: "object", root: true
            }
        });
    };

    const createServiceBookingMethod = function(){
        const ServiceBooking = databaseObj.ServiceBooking;
        ServiceBooking.createServiceBooking = createServiceBooking;
        ServiceBooking.remoteMethod("createServiceBooking", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "serviceBookingObj", type: "object"
                }
            ],
            returns: {
                arg: "serviceBookingObj", type: "ServiceBooking", root: true
            }
        });
    };


    const createCustomerQuoteMethod = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.createCustomerQuote = createCustomerQuote;
        CustomerQuote.remoteMethod("createCustomerQuote", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                  arg: "vehicleInfoObj", type: "object"
                },
                {
                    arg: "customerQuoteObj", type: "object"
                }
            ],
            returns: {
                arg: "customerQuoteObj", type: "CustomerQuote", root: true
            }
        });
    };

    const findAllCustomerQuoteMethod = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.findAll = findAllCustomerQuote;
        CustomerQuote.remoteMethod("findAll", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "object"
                }
            ],
            returns: {
                arg: "customerQuoteList", type: "array", root: true
            }
        });
    };

    const fetchQuoteReplyFromDealerMethod = function(){
        const QuoteReply = databaseObj.QuoteReply;
        QuoteReply.fetchQuoteReplyFromDealer = fetchQuoteReplyFromDealer;
        QuoteReply.remoteMethod("fetchQuoteReplyFromDealer", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "object"
                }
            ],
            returns: {
                arg: "quoteReplyList", type: "object", root: true
            }
        });
    };

    const sendFeedbackMethod = function(){
        const Feedback = databaseObj.Feedback;
        Feedback.sendFeedback = sendFeedback;
        Feedback.remoteMethod("sendFeedback", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "feedbackObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const addServiceMethod = function(){
        const ServiceHistory = databaseObj.ServiceHistory;
        ServiceHistory.addService = addService;
        ServiceHistory.remoteMethod("addService", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "serviceObj", type: "object"
                }
            ],
            returns: {
                arg: "serviceObj", type: "object", root: true
            }
        });
    };


    const findAllServiceMethod = function(){
      const ServiceHistory = databaseObj.ServiceHistory;
      ServiceHistory.findAll = findAllService;
      ServiceHistory.remoteMethod("findAll", {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg: "filter", type: "object"
              }
          ],
          returns: {
              arg: "serviceList", type: "object", root: true
          }
      });
    };
    /**
     * To fetch all the Brands
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllBrands = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        //if(request.accessToken){
            //if(request.accessToken.userId){
                  const Brand = databaseObj.Brand;
                  filter = filter || {};
                  filter.where = filter.where || {};
                  if(filter){
                      if(filter.where){
                          if(!filter.where.status){
                              filter.where.status = "active";
                          }
                      }
                  }

                  if(!filter.order){
                      filter.order = ['added DESC', 'trending DESC'];
                  }

                  Brand.find(filter)
                      .then(function (brandList) {
                          if(brandList){
                              if(brandList.length){
                                  const brand = brandList[brandList.length - 1];
                                  lastDate = brand.added;
                              }
                          }

                          callback(null,
                              {brandList: brandList,
                              cursor: lastDate});
                      })
                      .catch(function(error){
                         callback(error);
                      });

         /*   } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };


    /**
     * To fetch all the car models according to brandId
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllModels = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        const carIdList = [];
        //if(request.accessToken){
            //if(request.accessToken.userId){
                const CarModel = databaseObj.CarModel;
                filter = filter || {};
                filter.where = filter.where || {};
                if(filter){
                    if(filter.where){
                        if(!filter.where.status){
                            filter.where.status = "active";
                        }
                    }
                }
                if(filter.where.added){
                    if(!filter.where.added.lt){
                        filter.where.added.lt = new Date();
                    }
                }

                CarModel.find(filter)
                    .then(function(carModelList){
                        if(carModelList){
                            if(carModelList.length){
                                const carModel = carModelList[carModelList.length - 1];
                                lastDate = carModel.added;
                            }
                        }
                        callback(null,{
                            carModelList: carModelList,
                            cursor: lastDate
                        });
                    })
                    .catch(function(error){
                        callback(error);
                    })

            /*    } else{
                return callback(new Error("User not valid"));
                }
            } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * to fetch all the fuel data according to brandId, carModelId
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllFuel = function(ctx, filter, callback){
        const request = ctx.req;
        var lastDate = "";
        let uniqueFuelList = [];
        //if(request.accessToken){
            //if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
                if(filter){
                    if(filter.where){
                        if(!filter.where.status){
                            filter.where.status = "active";
                        }
                    }
                }
                //filter.fields.id = true;
                filter.include = ["fuel"];
                Car.find(filter)
                    .then(function(carList){
                        if(carList){
                            if(carList.length){
                                uniqueFuelList = _.uniqBy(carList, 'fuelId');
                            }
                        }
                        callback(null, uniqueFuelList);
                    })

                    .catch(function(error){
                        callback(error);
                    })
           /* } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * To fetch all the gearbox corresponding to brandId, carModelId, fuelId
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllGearbox = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate ;
        let uniqueGearBoxList = [];
       // if(request.accessToken){
            //if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
                if(filter){
                    if(filter.where){
                        if(!filter.where.status){
                            filter.where.status = "active";
                        }
                    }
                }
               filter.include = ["gearBox"];
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                               uniqueGearBoxList = _.uniqBy(carList, 'gearBoxId');
                            }
                        }
                        callback(null, uniqueGearBoxList);
                    })

                    .catch(function (error) {
                        callback(null, error);
                    });
           /* } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * To find the trim corresponding to brandId, carModelId, fuelId, gearboxId
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllTrim = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        const carIdList = [];
       // if(request.accessToken){
         //   if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
                if(filter){
                    if(filter.where){
                        if(!filter.where.status){
                            filter.where.status = "active";
                        }
                    }
                }
                if(!filter.order){
                    filter.order = "added DESC";
                }
                if(filter.where.added){
                    if(!filter.where.added.lt){
                        filter.where.added.lt = new Date();
                    }
                }
                filter.include = ["trim"];
                //filter.fields.id = true;
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                                const car = carList[carList.length - 1];
                                lastDate = car.added;
                            }
                        }

                        callback(null, {
                            carList: carList,
                            cursor: lastDate
                        });
                    })

                    .catch(function (error) {
                        callback(null, error);
                    });
          /*  } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * To find all the colors of the car.
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllColors = function(ctx, filter, callback){
      const request = ctx.req;
        var lastDate = "";
        const carIdList = [];
      //if(request.accessToken){
          //if(request.accessToken.userId){
              //const userId = request.accessToken.userId;
              const Car = databaseObj.Car;
              filter = filter || {};
              filter.where = filter.where || {};
              if(filter){
                  if(filter.where){
                      if(!filter.where.status){
                          filter.where.status = "active";
                      }
                  }
              }
              //filter.fields.id = true;
              filter.include = ["colors"];


               Car.findOne(filter)
                   .then(function(car){
                       if(car){
                           callback(null, car);
                       } else{
                           callback(new Error("Car not present"));
                       }
                   })
              /*Car.findOne(filter)
                  .then(function(car){
                      if(car){
                          var carId = car.id;
                          const Color = databaseObj.Color;
                          return Color.find({
                              where:{
                                  cars_: {carId:true}
                              },
                              limit: 7,
                              order: "added DESC",
                              added: {
                                  lt: lastDate
                              }
                          });
                      }
                  })

                  .then(function(colorList){
                     if(colorList){
                         if(colorList.length){
                             const color = colorList[colorList.length-1];
                             lastDate = color.added
                         }
                     }

                     callback(null,{
                         colorList: colorList,
                         cursor: lastDate
                     })
                  })*/
              .catch(function(error){
                  callback(error);
        });
        /*  } else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }*/
    };


    /**
     * to fetch the dealer detail
     * @param ctx
     * @param dealerId
     * @param callback
     * @returns {*}
     */
    const fetchDealerDetail = function(ctx, dealerId, callback){
      const request = ctx.req;
      var lastDate = "";
      //if(request.accessToken){
          //if(request.accessToken.userId){
              const Dealer = databaseObj.Dealer;
              Dealer.findById(dealerId)
                  .then(function(dealer){
                      if(dealer){
                          callback(null, dealer);
                      }else{
                          callback("Dealer not found");
                      }
                  })
                  .catch(function (error) {
                      callback(error);
                  })
          /*}else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }*/
    };

    /**
     * save the trending of brand
     * @param ctx
     * @param brandId
     * @returns {*}
     */
    const saveTrendingBrand = function(ctx, brandId, callback){
        const request = ctx.req;
       // if(request.accessToken){
            //if(request.accessToken.userId){
                const Brand = databaseObj.Brand;
                Brand.findById(brandId)
                    .then(function(brand){
                        brand.trending = brand.trending + 1;
                        return brand.updateAttribute("trending", brand.trending)
                    })
                    .then(function(brand){
                        callback(null,{"response": "success"});
                    })
                    .catch(function(error){
                        callback(error);
                    })
        /*    } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * To fetch breakdown nearest to current location
     * @param ctx
     * @param lat
     * @param lang
     * @param callback
     * @returns {*}
     */
    const findAllBreakdown = function(ctx, lat, lang, callback){
        const request = ctx.req;
        var customerLatLong;
        const breakdownList =[];
        let promises = [];
       /* if(request.accessToken){
            if(request.accessToken.userId){*/
                const BreakdownCategory = databaseObj.BreakdownCategory;
                customerLatLong = [lat, lang];
                BreakdownCategory.find()
                    .then(function(breakdownCategoryList){
                        if(breakdownCategoryList){
                                breakdownCategoryList.forEach(function(breakdownCategory){
                                    if(breakdownCategory){
                                        const categoryId = breakdownCategory.id;
                                        const Breakdown = databaseObj.Breakdown;
                                        promises.push(
                                        Breakdown.find({
                                            limit:1,
                                            where: {
                                                breakdownCategoryId : categoryId,
                                                latlong : {
                                                    near: customerLatLong,
                                                    maxDistance: 10,
                                                    unit: 'kilometers'
                                                }
                                            },
                                            include: ["breakdownCategory"]
                                        })
                                            .then(function(breakdown){
                                                if(breakdown){
                                                    breakdownList.push(breakdown);
                                                }
                                            })
                                    );


                                    }else{
                                        callback(new Error("Breakdown Category not found"));
                                    }
                                });
                            Promise.all(promises).then(function(){
                                callback(null, breakdownList);
                            })
                        }
                    })
                    .catch(function (error) {
                        if(error){
                            callback(error);
                        }
                    })
          /*  }else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * to find the nearest service centre
     * @param ctx
     * @param lat
     * @param lang
     * @param brandId
     * @param callback
     * @returns {*}
     */
    const fetchNearestServiceCenter = function(ctx, brandId, lat, lang, callback){
        const request = ctx.req;
        var lastDate = "";
        var customerLatLong;
       // if(request.accessToken){
            //if(request.accessToken.userId){
                const BreakdownCategory = databaseObj.BreakdownCategory;
                customerLatLong = [lat, lang];
                const filter = {
                    where:{
                        name:"Nearest Service Center"
                    }
                };
                BreakdownCategory.find(filter)
                    .then(function(breakdownCategory){
                        if(breakdownCategory){
                            const categoryId = breakdownCategory.id;
                            const Breakdown = databaseObj.Breakdown;
                            return Breakdown.find({
                                limit: 1,
                                where: {
                                    breakdownCategoryId: categoryId,
                                    latlong : {
                                        near: customerLatLong,
                                        maxDistance: 10,
                                        unit: 'kilometers'
                                    },
                                    brandId: brandId
                                }
                            })
                        }
                    })
                    .then(function(breakdown){
                        if(breakdown){
                            callback(null, breakdown);
                        } else{
                            callback(new Error("ServiceCenter not found"));
                        }
                    })
                    .catch(function(error){
                        callback(error);
                    })

          /*  } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/

    };

    /**
     * to fetch all the emergencies
     * @param ctx
     * @param lat
     * @param lang
     * @param callback
     * @returns {*}
     */
    const findAllEmergencies = function(ctx, lat, lang, callback){
      const request = ctx.req;
        var customerLatLong;
        let emergencyList =[];
        let promises = [];
        /*if(request.accessToken){
            if(request.accessToken.userId){*/
                const EmergencyCategory = databaseObj.EmergencyCategory;
                customerLatLong = [lat, lang];
                EmergencyCategory.find()
                    .then(function(emergencyCategoryList){
                        if(emergencyCategoryList){
                                emergencyCategoryList.forEach(function(emergencyCategory){
                                    if(emergencyCategory){
                                        const categoryId = emergencyCategory.id;
                                        const Emergency = databaseObj.Emergency;
                                        promises.push(
                                        Emergency.find({
                                            limit:1,
                                            where: {
                                                emergencyCategoryId : categoryId,
                                                latlong : {
                                                    near: customerLatLong,
                                                    maxDistance: 10,
                                                    unit: 'kilometers'
                                                }
                                            },
                                            include: ["emergencyCategory"]
                                        })
                                            .then(function(emergency){
                                                if(emergency){
                                                    emergencyList.push(emergency);
                                                }
                                            })
                                        );


                                    }else{
                                        callback(new Error("Emergency Category not found"));
                                    }
                                });

                            Promise.all(promises).then(function(){
                                callback(null, emergencyList);
                            })

                        }
                    })

                    .catch(function (error) {
                        if(error){
                            callback(error);
                        }
                    })
           /* }else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }*/
    };

    /**
     * To fetch a list of dealers corresponding to brand
     * @param ctx
     * @param brandId
     * @param lastDate
     * @param callback
     * @returns {*}
     */
    const fetchDealersForBrand = function(ctx, brandId, lastDate, callback){
      const request = ctx.req;
      if(request.accessToken){
          if(request.accessToken.userId){
              const Dealer = databaseObj.Dealer;
              Dealer.find({
                  where: {
                      brandId: brandId,
                      status: "active",
                      added:{
                          lt: lastDate
                      }
                  }
              })
                  .then(function(dealerList){
                      if(dealerList){
                          if(dealerList.length){
                              const dealer = dealerList[dealerList.length - 1];
                              lastDate = dealer.added;
                          }
                      }

                      callback(null, {
                          dealerList: dealerList,
                          cursor: lastDate
                      })
                  })

                  .catch(function(error){
                      callback(error);
                  })
          }else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }
    };

    /**
     * to save the vehicle detail of the customer
     * @param ctx
     * @param vehicleDetailObj
     * @param callback
     * @returns {*}
     */
    const saveVehicleDetails = function(ctx, vehicleDetailObj, callback){
      const request = ctx.req;
      //if(request.accessToken){
          //if(request.accessToken.userId){
              const customerId = "";
              const VehicleDetail = databaseObj.VehicleDetail;
              VehicleDetail.create({
                  workshopName: vehicleDetailObj.workshopName,
                  showroomName: vehicleDetailObj.showroomName,
                  registeredName: vehicleDetailObj.registeredName,
                  registrationNumber: vehicleDetailObj.registrationNumber,
                  showroomId: vehicleDetailObj.showroomId,
                  workshopId: vehicleDetailObj.workshopId,
                  customerId: customerId
              })
                  .then(function(vehicleDetailObj){
                      if(vehicleDetailObj){
                          callback(null, vehicleDetailObj);
                      } else{
                          callback(new Error("Vehicle Detail can't be saved"));
                      }
                  })
                  .catch(function(error){
                      callback(error);
                  })
       /*   }else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }*/
    };

    /**
     * To send the message to dealer/customer related to quote
     * @param ctx
     * @param customerMessageObj
     * @param callback
     * @returns {*}
     */
    const sendMessage = function(ctx, customerMessageObj, callback){
      const request = ctx.req;
      if(!customerMessageObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          //if(request.accessToken){
              //if(request.accessToken.userId){
                  const customerId = "";
                  const CustomerMessage = databaseObj.CustomerMessage;
                  CustomerMessage.create({
                      subject: customerMessageObj.subject,
                      message: customerMessageObj.message,
                      type: customerMessageObj.type,
                      userType: customerMessageObj.userType,
                      dealerId: customerMessageObj.dealerId,
                      customerId: customerId,
                      customerQuoteId: customerMessageObj.customerQuoteId
                  })
                      .then(function(customerMessage){
                          if(customerMessage){
                              callback(null, {
                                  "response": "success"
                              })
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
            /*  } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }*/
      }

    };

    /**
     * To fetch the type of service
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const fetchServiceType = function(ctx, filter, callback){
        const request = ctx.req;
        var lastDate;
        if(!filter){
            return callback(new Error("Wrong arguments"));
        }else{
          /*  if(request.accessToken){
                if(request.accessToken.userId){*/
                    const ServiceType = databaseObj.ServiceType;
                    filter = filter || {};
                    filter.where = filter.where || {};
                    if(filter){
                        if(filter.where){
                            if(filter.where.added){
                                if(!filter.where.added.lt){
                                    filter.where.added.lt = new Date();
                                }
                            }
                        }
                    }
                    ServiceType.find(filter)
                        .then(function(serviceTypeList){
                            if(serviceTypeList){
                                if(serviceTypeList.length){
                                    const serviceType = serviceTypeList[serviceTypeList.length - 1];
                                    lastDate = serviceType.added;
                                }
                            }
                            callback(null, {
                                serviceTypeList: serviceTypeList,
                                cursor: lastDate
                            })
                        })
                        .catch(function(error){
                            callback(error);
                        })
                /*}else{
                    return callback(new Error("User not valid"));
                }
            } else{
                return callback(new Error("User not valid"));
            }*/
        }
    };

    /**
     * to create the service booking
     * @param ctx
     * @param serviceBookingObj
     * @param callback
     * @returns {*}
     */
    const createServiceBooking = function(ctx, serviceBookingObj, callback){
      const request = ctx.req;
      if(!serviceBookingObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          //if(request.accessToken){
              //if(request.accessToken.userId){
                  const customerId = "";
                  const ServiceBooking = databaseObj.ServiceBooking;
                  ServiceBooking.create({
                      serviceDate: serviceBookingObj.serviceDate,
                      comments: serviceBookingObj.comments,
                      vehiclePickup: serviceBookingObj.vehiclePickup,
                      serviceTypeId: serviceBookingObj.serviceTypeId,
                      workshopId: serviceBookingObj.workshopId,
                      customerId: customerId
                  })
                      .then(function(serviceBooking){
                          if(serviceBooking){
                              callback(null, serviceBooking);
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
           /*   } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }*/
      }
    };

    /**
     * to create the customer quote
     * @param ctx
     * @param customerQuoteObj
     * @param callback
     * @returns {*}
     */
    const createCustomerQuote = function(ctx, vehicleInfoObj, customerQuoteObj, callback){
      const request = ctx.req;
      if(!customerQuoteObj){
          return callback(new Error("Invalid Arguments"));
      }else{
          //if(request.accessToken){
             // if(request.accessToken.userId){
                  const customerId = "";
                  const VehicleInfo = databaseObj.VehicleInfo;
                  VehicleInfo.create({
                      colorId : vehicleInfoObj.colorId,
                      brandId: vehicleInfoObj.brandId,
                      carModelId: vehicleInfoObj.carModelId,
                      trimId: vehicleInfoObj.trimId,
                      customerId: customerId,
                      gearBoxId: vehicleInfoObj.gearBoxId,
                      fuelId: vehicleInfoObj.fuelId
                  })
                      .then(function(vehicleInfoObj){
                          if(vehicleInfoObj){
                              const vehicleInfoId = vehicleInfoObj.id;
                              const CustomerQuote = databaseObj.CustomerQuote;
                              return  CustomerQuote.create({
                                  vehicleInfoId: vehicleInfoId,
                                  cityId: customerQuoteObj.cityId,
                                  ownershipType: customerQuoteObj.ownershipType,
                                  isFinance: customerQuoteObj.isFinance,
                                  isInsurance: customerQuoteObj.isInsurance,
                                  isOldVehicleTrade: customerQuoteObj.isOldVehicleTrade,
                                  brandId: customerQuoteObj.brandId,
                                  modelId: customerQuoteObj.modelId,
                                  quoteType: customerQuoteObj.quoteType,
                                  customerId: customerId,
                                  soldViaAutobox: customerQuoteObj.soldViaAutobox,
                                  gpsTracker: customerQuoteObj.gpsTracker,
                                  dashCamera: customerQuoteObj.dashCamera,
                                  testDrive: customerQuoteObj.testDrive
                              })
                          } else{
                              callback(new Error("Vehicle not found"));
                          }
                      })

                      .then(function(customerQuote){
                          if(customerQuote){
                              callback(null, customerQuote);
                          }
                      })

                      .catch(function (error) {
                          callback(error);
                      })
          /*    } else{
                  return callback(new Error("User not valid"));
              }
          }else{
              return callback(new Error("User not valid"));
          }*/
      }
    };

    /**
     * To fetch all the customer quote
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllCustomerQuote = function(ctx, filter, callback){
      const request = ctx.req;
      if(!filter){
          return callback(new Error("Invalid Arguments"));
      }else{
          //if(request.accessToken){
              //if(request.accessToken.userId){
                  const customerId = "";
                  const CustomerQuote = databaseObj.CustomerQuote;
                  CustomerQuote.find({
                       where:{
                           customerId:customerId
                       },
                      include:[{
                           relation: "vehicleInfo",
                           scope: {
                               include: ["color", "brand", "carModel", "trim", "gearBox", "fuel"]
                           }
                      }]
                  })

                      .then(function(customerQuoteList){
                          if(customerQuoteList){
                              if(customerQuoteList.length){
                                  callback(null, customerQuoteList);
                              }
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
             /* } else {
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }*/
      }
    };

    /**
     * to fetch all the quote reply from the dealer corresponding to customerQuoteID
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const fetchQuoteReplyFromDealer = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        if(!filter){
            return callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    if(filter){
                        if(filter.where){
                            if(filter.where.added){
                                if(!filter.where.added.lt){
                                    filter.where.added.lt = new Date();
                                }
                            }
                        }
                    }
                    const QuoteReply = databaseObj.QuoteReply;
                    QuoteReply.find(filter)
                        .then(function(quoteReplyList){
                            if(quoteReplyList){
                                if(quoteReplyList.length){
                                    const quoteReply = quoteReplyList[quoteReplyList.length - 1];
                                    lastDate = quoteReply.added;
                                }
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })

                } else{
                    return callback(new Error("User not valid"));
                }
            } else{
                return callback(new Error("User not valid"));
            }
        }
    };

    /**
     * To send the feedback to the dealer
     * @param ctx
     * @param feedbackObj
     * @param callback
     * @returns {*}
     */
    const sendFeedback = function(ctx, feedbackObj, callback){
      const request = ctx.req;
      if(!feedbackObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          //if(request.accessToken){
              //if(request.accessToken.userId){
                  const userId = "";
                  const Feedback = databaseObj.Feedback;
                  Feedback.create({
                      subject: feedbackObj.subject,
                      message: feedbackObj.message,
                      customerId: userId,
                      dealerId: feedbackObj.dealerId
                  })

                      .then(function(feedbackObj){
                          if(feedbackObj){
                              callback(null, {"response": "success"});
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
             /* } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }*/
      }
    };

    /**
     * to add the service for customer.
     * @param ctx
     * @param serviceObj
     * @param callback
     * @returns {*}
     */
    const addService = function(ctx, serviceObj, callback){
        const request = ctx.req;
        if(!serviceObj){
            return callback(new Error("Invalid Arguments"));
        } else{
            //if(request.accessToken){
                //if(request.accessToken.userId){
                    const customerId = "";
                    const ServiceHistory = databaseObj.ServiceHistory;
                    ServiceHistory.create( {
                        dateOfBooking: serviceObj.dateOfBooking,
                        mileageCompleted: serviceObj.mileageCompleted,
                        charges: serviceObj.charges,
                        customerId: customerId

                    })
                        .then(function(serviceObj){
                           if(serviceObj){
                               callback(null, serviceObj);
                           }
                        })
                    .catch(function(error){
                        callback(error);
                    })
            /*    } else{
                    return callback(new Error("User not valid"));
                }
            } else{
                return callback(new Error("User not valid"));
            }*/
        }
    };

    /**
     * To find All the services booked by customer
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllService = function(ctx, filter, callback){
      const request = ctx.req;
      let lastDate;
      if(!filter){
          return callback(new Error("Invalid Arguments"));
      } else{
          //if(request.accessToken){
              //if(request.accessToken.userId){
                  const customerId = "";
                  if(filter){
                      if(filter.where){
                          if(filter.where.added){
                              if(!filter.where.added.lt){
                                  filter.where.added.lt = new Date();
                              }
                          }
                      }
                  }

                  const ServiceHistory = databaseObj.ServiceHistory;
                  ServiceHistory.find(filter)
                      .then(function(serviceHistoryList){
                          if(serviceHistoryList){
                              if(serviceHistoryList.length){
                                  const serviceHistory = serviceHistoryList[serviceHistoryList.length - 1];
                                  lastDate = serviceHistory.added;
                              }
                          }
                          callback(null, {
                              serviceHistoryList: serviceHistoryList,
                              cursor: lastDate
                          });
                      })
                      .catch(function(error){
                          callback(error);
                      })

            /*      } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }*/
      }
    };



    return {
        init: init
    };
};