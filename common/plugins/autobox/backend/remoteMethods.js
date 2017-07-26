/**
 * Created by nikita on 24/7/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

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
      const Car = databaseObj.Car;
      Car.findAllModels = findAllModels;
      Car.remoteMethod("findAllModels", {
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
                arg:"fuelList", type: "Object", root: true
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
                arg:"gearboxList", type: "Object", root: true
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
        Breakdown.remoteMethod("findAllBreakdown", {
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
    

    /**
     * To fetch all the Brands
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllBrands = function(ctx, filter, callback){
        const request = ctx.req;
        var lastDate = "";
        if(request.accessToken){
            if(request.accessToken.userId){
                  const Brand = databaseObj.Brand;
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

            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
        var lastDate = "";
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
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
                filter.fields.id = true;
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                                carList.forEach(function (car) {
                                    carIdList.push(car.id);
                                });
                                const CarModel = databaseObj.CarModel;
                                return CarModel.find({
                                    where: {
                                        inq: carIdList
                                    },
                                    limit: 7,
                                    order: "added DESC",
                                    added: {
                                        lt: lastDate
                                    }
                                });
                            }
                        }
                    })
                    .then(function (carModelList) {
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
                        .catch(function (error) {
                            callback(null, error);
                        });
                } else{
                return callback(new Error("User not valid"));
                }
            } else{
            return callback(new Error("User not valid"));
        }
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
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
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
                filter.fields.id = true;
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                                carList.forEach(function (car) {
                                    carIdList.push(car.id);
                                });
                                const Fuel = databaseObj.Fuel;
                                return Fuel.find({
                                    where: {
                                        inq: carIdList
                                    },
                                    limit: 7,
                                    order: "added DESC",
                                    added: {
                                        lt: lastDate
                                    }
                                });
                            }
                        }
                    })
                    .then(function (fuelList) {
                        if(fuelList){
                            if(fuelList.length){
                                const carModel = fuelList[fuelList.length - 1];
                                lastDate = carModel.added;
                            }
                        }
                        callback(null,{
                            fuelList: fuelList,
                            cursor: lastDate
                        });
                    })
                    .catch(function (error) {
                        callback(null, error);
                    });
            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
        var lastDate = "";
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
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
                filter.fields.id = true;
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                                carList.forEach(function (car) {
                                    carIdList.push(car.id);
                                });
                                const GearBox = databaseObj.GearBox;
                                return GearBox.find({
                                    where: {
                                        inq: carIdList
                                    },
                                    limit: 7,
                                    order: "added DESC",
                                    added: {
                                        lt: lastDate
                                    }
                                });
                            }
                        }
                    })
                    .then(function (gearboxList) {
                        if(gearboxList){
                            if(gearboxList.length){
                                const carModel = gearboxList[gearboxList.length - 1];
                                lastDate = carModel.added;
                            }
                        }
                        callback(null,{
                            gearboxList: gearboxList,
                            cursor: lastDate
                        });
                    })
                    .catch(function (error) {
                        callback(null, error);
                    });
            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
        var lastDate = "";
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
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
                filter.fields.id = true;
                Car.find(filter)
                    .then(function(carList) {
                        if (carList) {
                            if (carList.length) {
                                carList.forEach(function (car) {
                                    carIdList.push(car.id);
                                });
                                const Trim = databaseObj.Trim;
                                return Trim.find({
                                    where: {
                                        inq: carIdList
                                    },
                                    limit: 7,
                                    order: "added DESC",
                                    added: {
                                        lt: lastDate
                                    }
                                });
                            }
                        }
                    })
                    .then(function (trimList) {
                        if(trimList){
                            if(trimList.length){
                                const carModel = trimList[trimList.length - 1];
                                lastDate = carModel.added;
                            }
                        }
                        callback(null,{
                            trimList: trimList,
                            cursor: lastDate
                        });
                    })
                    .catch(function (error) {
                        callback(null, error);
                    });
            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
      if(request.accessToken){
          if(request.accessToken.userId){
              const userId = request.accessToken.userId;
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
              filter.fields.id = true;
              Car.findOne(filter)
                  .then(function(car){
                      if(car){
                          const carId = car.id;
                          const Color = databaseObj.Color;
                          return Color.find({
                              where:{
                                  carId: carId
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
                  });
          } else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }
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
      if(request.accessToken){
          if(request.accessToken.userId){
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
          }else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }
    };

    /**
     * save the trending of brand
     * @param ctx
     * @param brandId
     * @returns {*}
     */
    const saveTrendingBrand = function(ctx, brandId, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
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
            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
        if(request.accessToken){
            if(request.accessToken.userId){
                const BreakdownCategory = databaseObj.BreakdownCategory;
                customerLatLong = new Geolocation(lat, lang);
                BreakdownCategory.find()
                    .then(function(breakdownCategoryList){
                        if(breakdownCategoryList){
                            return Promise.all(
                                breakdownCategoryList.forEach(function(breakdownCategory){
                                    if(breakdownCategory){
                                        const categoryId = breakdownCategory.id;
                                        const Breakdown = databaseObj.Breakdown;
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
                                    }else{
                                        callback(new Error("Breakdown Category not found"));
                                    }
                                })
                            )
                        }
                    })

                    .then(function(breakdownList){
                        if(breakdownList){
                            callback(null, breakdownList);
                        }else{
                            callback(null, []);
                        }
                    })

                    .catch(function (error) {
                        if(error){
                            callback(error);
                        }
                    })
            }else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
    const fetchNearestServiceCenter = function(ctx, lat, lang, brandId, callback){
        const request = ctx.req;
        var lastDate = "";
        var customerLatLong;
        if(request.accessToken){
            if(request.accessToken.userId){
                const BreakdownCategory = databaseObj.BreakdownCategory;
                customerLatLong = new Geolocation(lat, lang);
                const filter = {
                    where:{
                        name:"Service Center"
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
                                    }
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

            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }

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
        const emergencyList =[];
        if(request.accessToken){
            if(request.accessToken.userId){
                const EmergencyCategory = databaseObj.EmergencyCategory;
                customerLatLong = new Geolocation(lat, lang);
                EmergencyCategory.find()
                    .then(function(emergencyCategoryList){
                        if(emergencyCategoryList){
                            return Promise.all(
                                emergencyCategoryList.forEach(function(emergencyCategory){
                                    if(emergencyCategory){
                                        const categoryId = emergencyCategory.id;
                                        const Emergency = databaseObj.Emergency;
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
                                    }else{
                                        callback(new Error("Emergency Category not found"));
                                    }
                                })
                            )
                        }
                    })

                    .then(function(emergencyList){
                        if(emergencyList){
                            callback(null, emergencyList);
                        }else{
                            callback(null, []);
                        }
                    })

                    .catch(function (error) {
                        if(error){
                            callback(error);
                        }
                    })
            }else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
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
      if(request.accessToken){
          if(request.accessToken.userId){
              const customerId = request.accessToken.userId;
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
          }else{
              return callback(new Error("User not valid"));
          }
      } else{
          return callback(new Error("User not valid"));
      }
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
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
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
              } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }
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
            if(request.accessToken){
                if(request.accessToken.userId){
                    const ServiceType = databaseObj.ServiceType;
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
                }else{
                    return callback(new Error("User not valid"));
                }
            } else{
                return callback(new Error("User not valid"));
            }
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
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const ServiceBooking = databaseObj.ServiceBooking;
                  ServiceBooking.create({
                      serviceDate: serviceBookingObj.serviceDate,
                      comments: serviceBookingObj.comments,
                      vehiclePickUp: serviceBookingObj.vehiclePickUp,
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
              } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }
      }
    };

    /**
     * to create the customer quote
     * @param ctx
     * @param customerQuoteObj
     * @param callback
     * @returns {*}
     */
    const createCustomerQuote = function(ctx, customerQuoteObj, callback){
      const request = ctx.req;
      if(!customerQuoteObj){
          return callback(new Error("Invalid Arguments"));
      }else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const CustomerQuote = databaseObj.CustomerQuote;
                  CustomerQuote.create({
                      carId: customerQuoteObj.carId,
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

                      .then(function(customerQuote){
                          if(customerQuote){
                              callback(null, customerQuote);
                          }
                      })

                      .catch(function (error) {
                          callback(error);
                      })
              } else{
                  return callback(new Error("User not valid"));
              }
          }else{
              return callback(new Error("User not valid"));
          }
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
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
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
              }
          }
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

    const sendFeedback = function(ctx, feedbackObj, callback){
      const request = ctx.req;
      if(!feedbackObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const userId = request.accessToken.userId;
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
              } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }
      }
    };



    return {
        init: init
    };
};