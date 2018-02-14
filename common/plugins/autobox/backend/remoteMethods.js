/**
 * Created by nikita on 24/7/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const GeoPoint = require("geopoint");
    const _ = require("lodash");
    const Promise = require("bluebird");
    const moment = require("moment");
    const process = require("process");
    const async = require("async");

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
        createQuoteReplyMethod();
        fetchQuoteReplyFromDealerMethod();
        sendFeedbackMethod();
        addServiceMethod();
        findAllServiceMethod();
        fetchWorkshopForBrandMethod();
        fetchShowroomForBrandMethod();
        findAllVehiclesMethod();
        saveCustomerMethod();
        storeSOSDataMethod();
        createTestDriveQuoteMethod();
        saveNewVehicleMethod();
        saveExistingVehicleMethod();
        deleteVehicleMethod();
        cancelQuoteMethod();
        findAllOfferMethod();
        offerQueryMethod();
        fetchFeedbackShowroomMethod();
        fetchSosSettingsMethod();
        incrementReferralCountMethod();
        removeSOSMethod();
        findAllCustomerOfferMethod();
        rateDealerExperienceMethod();
        findAllQuoteMessageMethod();
        updateSosDataMethod();
        updateVehicleDetailMethod();

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
                },
                {
                    arg: "lastDate", type: "string"
                }
            ],
            returns:{
                arg: "brandList", type: "Object", root: true
            }
        });
    };

    const findAllModelsMethod = function(){
      const CarModel = databaseObj.CarModel;
      CarModel.findAll = findAllModels;
      CarModel.remoteMethod("findAll", {
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
                arg:"fuelList", type: ["Car"], root: true
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
                arg:"gearboxList", type: ["Car"], root: true
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
                arg:"colorsList", type: "Car", root: true
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
                arg: "breakdownList", type: ["Breakdown"], root: true
            }
        });
    };


    const fetchNearestServiceCenterMethod = function(){
        const Workshop = databaseObj.Workshop;
        Workshop.fetchNearestServiceCenter = fetchNearestServiceCenter;
        Workshop.remoteMethod("fetchNearestServiceCenter", {
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
                arg: "workshop", type: "Workshop", root: true
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
                arg: "emergencyList", type: ["Emergency"], root: true
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

    const fetchWorkshopForBrandMethod = function(){
      const Workshop = databaseObj.Workshop;
      Workshop.fetchWorkshopForBrand = fetchWorkshopForBrand;
      Workshop.remoteMethod("fetchWorkshopForBrand", {
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
              arg: "workshopList", type: "object", root: true
          }
      });
    };

    const fetchShowroomForBrandMethod = function(){
      const Showroom = databaseObj.Showroom;
      Showroom.fetchShowroomForBrand= fetchShowroomForBrand;
      Showroom.remoteMethod("fetchShowroomForBrand", {
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
              arg: "showroomList", type: "object", root: true
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
                },
                {
                    arg: "vehicleInfoObj", type: "object"
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
                arg: "serviceList", type: ["ServiceType"], root: true
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
                },
                {
                    arg: "status", type: "string"
                }
            ],
            returns: {
                arg: "customerQuoteList", type: ["CustomerQuote"], root: true
            }
        });
    };

    const createQuoteReplyMethod = function(){
        const QuoteReply = databaseObj.QuoteReply;
        QuoteReply.createQuoteReply = createQuoteReply;
        QuoteReply.remoteMethod("createQuoteReply", {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http:{
                      source: 'context'
                  }
              },
              {
                  arg: "quoteReplyObj", type: "object"
              }
          ] ,
            returns: {
              arg: "quoteReplyObj", type: "object"
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
                },
                {
                    arg: "lat", type: "number"
                },
                {
                    arg: "lang", type: "number"
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

    const findAllVehiclesMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.findAll = findAllVehicles;
        VehicleDetail.remoteMethod("findAll", {
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
                arg: "vehiclesList", type: "object", root: true
            }
        })
    };


    const saveCustomerMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.saveCustomer = saveCustomer;
        Customer.remoteMethod("saveCustomer", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "customerObj", type: "object"
                }
            ],
            returns: {
                arg: "customerObj", type: "Customer", root: true
            }

        });
    };

    const storeSOSDataMethod = function(){
        const Sos = databaseObj.Sos;
        Sos.storeSosData = storeSosData;
        Sos.remoteMethod("storeSosData", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "sosObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });

    };

    const createTestDriveQuoteMethod = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.createTestDriveQuote = createTestDriveQuote;
        CustomerQuote.remoteMethod("createTestDriveQuote", {
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
                }
            ],
            returns: {
                arg: "customerQuoteObj", type: "CustomerQuote", root: true
            }
        })
    };

    const saveNewVehicleMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.saveNewVehicle = saveNewVehicle;
        VehicleDetail.remoteMethod("saveNewVehicle", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "customerQuoteId", type: "string"
                },
                {
                    arg: "insuranceObj", type: "object"
                },
                {
                    arg: "vehicleDetailObj", type: "object"
                }
            ],
            returns: {
                arg: "vehicleDetailObj", type: "VehicleDetail", root: true
            }
        })
    };


    const saveExistingVehicleMethod = function(){
      const VehicleDetail = databaseObj.VehicleDetail;
      VehicleDetail.saveExistingVehicle = saveExistingVehicle;
      VehicleDetail.remoteMethod("saveExistingVehicle", {
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
                  arg: "insuranceObj", type: "object"
              },
              {
                  arg: "vehicleDetailObj", type: "object"
              }
          ],
          returns: {
              arg: "vehicleDetailObj", type: "VehicleDetail", root: true
          }
      })
    };


    const deleteVehicleMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.deleteVehicle = deleteVehicle;
        VehicleDetail.remoteMethod("deleteVehicle", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "vehicleDetailId", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        })
    };



    const cancelQuoteMethod = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.cancelQuote = cancelQuote;
         CustomerQuote.remoteMethod("cancelQuote", {
             accepts: [
                 {
                     arg: 'ctx',
                     type: 'object',
                     http: {
                         source: 'context'
                     }
                 },
                 {
                     arg: "customerQuoteId", type: "string"
                 }
             ],
             returns: {
                 arg: "response", type: "object", root: true
             }
         })
    };

    const findAllOfferMethod = function(){
        const Offer = databaseObj.Offer;
        Offer.findAll = findAllOffer;
        Offer.remoteMethod("findAll", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "cityId", type: "string"
                }
            ],
            returns: {
                arg: "offerList", type: ["Offer"], root: true
            }
        })
    };

    const offerQueryMethod = function(){
       const OfferQuery = databaseObj.OfferQuery;
       OfferQuery.offerQuery = offerQuery;
       OfferQuery.remoteMethod("offerQuery", {
           accepts: [
               {
                   arg: 'ctx',
                   type: 'object',
                   http: {
                       source: 'context'
                   }
               },
               {
                   arg: "offerQueryObj", type: "object"
               }
           ],
           returns: {
               arg: "offerQueryObj", type: "OfferQuery", root: true
           }
       })
    };


    const fetchFeedbackShowroomMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.fetchFeedbackShowroom = fetchFeedbackShowroom;
        VehicleDetail.remoteMethod("fetchFeedbackShowroom", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                }
            ],
            returns: {
                arg: "showroomList", type: ["VehicleDetail"], root: true
            }
        })
    };

    const fetchSosSettingsMethod = function(){
        const Sos = databaseObj.Sos;
        Sos.fetchSosSettings = fetchSosSettings;
        Sos.remoteMethod('fetchSosSettings', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                }
            ],
            returns: {
                arg: "sosData", type: "Sos", root: true
            }
        })
    };

    const incrementReferralCountMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.incrementReferralCount = incrementReferralCount;
        Customer.remoteMethod('incrementReferralCount', {
            accepts: [
                {
                    arg: "referralCode", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        })
    };


    const removeSOSMethod = function(){
        const Sos = databaseObj.Sos;
        Sos.removeSos = removeSos;
        Sos.remoteMethod('removeSos', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        })
    };

    const findAllCustomerOfferMethod = function(){
      const CustomerOffer = databaseObj.CustomerOffer;
      CustomerOffer.findAll = findAllCustomerOffers;
      CustomerOffer.remoteMethod('findAll', {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg:"lastDate", type: "string"
              }
          ],
          returns: {
              arg: "customerOfferList", type: "object", root: true
          }
      })
    };

    const rateDealerExperienceMethod = function(){
      const DealerRating = databaseObj.DealerRating;
      DealerRating.rateDealerExperience = rateDealerExperience;
      DealerRating.remoteMethod('rateDealerExperience', {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg: "dealerRatingObj", type: "object"
              }
          ],
          returns: {
              arg: "response", type: "object", root: true
          }
      })
    };

    const findAllQuoteMessageMethod = function(){
      const CustomerMessage = databaseObj.CustomerMessage;
      CustomerMessage.findAll = findAllQuoteMessage;
      CustomerMessage.remoteMethod('findAll', {
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
              },
              {
                  arg: "lastDate", type: "string"
              }
          ],
          returns: {
              arg: "customerMessageList", type: "object", root: true
          }
      })
    };

    const updateSosDataMethod = function(){
        const Sos = databaseObj.Sos;
        Sos.updateSosData = updateSosData;
        Sos.remoteMethod('updateSosData', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "sosObj", type: "object"
                }
            ],
            returns: {
                arg: "sosObj", type: "Sos", root: true
            }
        })
    };

    const updateVehicleDetailMethod = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.updateVehicleDetail = updateVehicleDetail;
        VehicleDetail.remoteMethod('updateVehicleDetail', {
            accepts:[
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
                arg: "vehicleDetailObj", type: "VehicleDetail", root : true
            }
        })
    };


    const updateVehicleDetail = function(ctx, vehicleDetailObj, callback){
        const request = ctx.req;
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.upsert(vehicleDetailObj)
            .then(function(vehicleDetail){
                if(vehicleDetail){
                    callback(null, vehicleDetail);
                }
            })
            .catch(function(error){
                callback(error);
            })
    };



    /**
     * To fetch all the Brands
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllBrands = function(ctx, filter, lastDate, callback){
        /*if(!filter.where.added.lt){
            filter.where.added.lt = new Date();
        }*/
        lastDate="";
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                  const Brand = databaseObj.Brand;
                  filter = filter || {};
                  filter.where = filter.where || {};
                  /*if(filter){
                      if(filter.where){
                          if(type === "new"){
                              if(!filter.where.status){
                                  filter.where.status = "active";
                              }
                          }
                      }
                  }*/

                  if(!filter.order){
                      filter.order = ['trending DESC'];
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
        let lastDate;
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
                const CarModel = databaseObj.CarModel;
                filter = filter || {};
                filter.where = filter.where || {};
                /*if(filter){
                    if(filter.where){
                        if(type === "new"){
                            if(!filter.where.status){
                                filter.where.status = "active";
                            }
                        }
                    }
                }*/
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
        let uniqueFuelList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
             /*   if(filter){
                    if(filter.where){
                        if(type === "new"){
                            if(!filter.where.status){
                                filter.where.status = "active";
                            }
                        }
                    }
                }*/
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
        let lastDate ;
        let uniqueGearBoxList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
               /* if(filter){
                    if(filter.where){
                        if(type === "new"){
                            if(!filter.where.status){
                                filter.where.status = "active";
                            }
                        }
                    }
                }*/
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
        let lastDate;
        const carIdList = [];
        if(request.accessToken){
            if(request.accessToken.userId){
                const Car = databaseObj.Car;
                filter = filter || {};
                filter.where = filter.where || {};
                /*if(filter){
                    if(filter.where){
                        if(type === "new"){
                            if(!filter.where.status){
                                filter.where.status = "active";
                            }
                        }
                    }
                }*/
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
        let promises = [];
        if(request.accessToken){
            if(request.accessToken.userId){
                const BreakdownCategory = databaseObj.BreakdownCategory;
                customerLatLong = [lat, lang];
                BreakdownCategory.find()
                    .then(function(breakdownCategoryList){
                        if(breakdownCategoryList){
                                breakdownCategoryList.forEach(function(breakdownCategory){
                                    if(breakdownCategory){
                                        const categoryId = breakdownCategory.id;
                                        const Breakdown = databaseObj.Breakdown;
                                        promises.push(function(callback){
                                            Breakdown.findOne({
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
                                                        callback(null);
                                                    } else{
                                                        callback(null);
                                                    }
                                                })
                                                .catch(function(error){
                                                    callback(error);
                                                })
                                        });
                                    }else{
                                        callback(new Error("Breakdown Category not found"));
                                    }
                                });
                           async.series(promises,function(error, list){
                               if(error){
                                   callback(error);
                               } else{
                                   callback(null, breakdownList);
                               }

                            })
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
    const fetchNearestServiceCenter = function(ctx, brandId, lat, lang, callback){
        const request = ctx.req;
        var customerLatLong;
        if(!brandId && !lat && !lang){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const Workshop = databaseObj.Workshop;
                    customerLatLong = [lat, lang];
                    Workshop.findOne({
                        where: {
                            brandId : brandId,
                            latlong: {
                                near : customerLatLong
                            }
                        }
                    })
                        .then(function(workshop){
                            if(workshop){
                                callback(null, workshop);
                            } else{
                                callback(null, {});
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
       /* const request = ctx.req;
        var lastDate = "";
        var customerLatLong;
        if(request.accessToken){
            if(request.accessToken.userId){
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
                            return Breakdown.findOne({
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

            } else{
                return callback(new Error("User not valid"));
            }
        } else{
            return callback(new Error("User not valid"));
        }
*/
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
        if(request.accessToken){
            if(request.accessToken.userId){
                const EmergencyCategory = databaseObj.EmergencyCategory;
                customerLatLong = [lat, lang];
                EmergencyCategory.find()
                    .then(function(emergencyCategoryList){
                        if(emergencyCategoryList){
                                emergencyCategoryList.forEach(function(emergencyCategory){
                                    if(emergencyCategory){
                                        const categoryId = emergencyCategory.id;
                                        const Emergency = databaseObj.Emergency;
                                        promises.push(function(callback){
                                            Emergency.findOne({
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
                                                        callback(null);
                                                    } else{
                                                        callback(null);
                                                    }
                                                })
                                                .catch(function(error){
                                                    console.error(error);
                                                    callback(error);
                                                })
                                        });

                                    }else{
                                        callback(new Error("Emergency Category not found"));
                                    }
                                });

                            async.series(promises, function(error, list){
                                if(error){
                                    callback(error);
                                } else{
                                    callback(null, emergencyList);
                                }

                            })
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
     * to fetch the list of workshop corresponding to brand
     * @param ctx
     * @param brandId
     * @param lastDate
     * @param callback
     * @returns {*}
     */
    const fetchWorkshopForBrand = function(ctx, brandId, lastDate, callback){
        const request = ctx.req;
        lastDate = !lastDate ? new Date() : new Date(lastDate);
        if(request.accessToken){
            if(request.accessToken.userId){
                const Workshop = databaseObj.Workshop;
                Workshop.find({
                    where: {
                        brandId: brandId,
                        status: "active",
                        added:{
                            lt: lastDate
                        }
                    },
                    order: "added DESC",
                    include: ["brand", "areas", "cities"]
                })
                    .then(function(workshopList){
                        if(workshopList){
                            if(workshopList.length){
                                const workshop = workshopList[workshopList.length - 1];
                                lastDate = workshop.added;
                            }
                        }

                        callback(null, {
                            workshopList: workshopList,
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
     * to fetch the list of showroom corresponding to brand
     * @param ctx
     * @param brandId
     * @param lastDate
     * @param callback
     * @returns {*}
     */
    const fetchShowroomForBrand = function(ctx, brandId, lastDate, callback){
        const request = ctx.req;
        lastDate = !lastDate ? new Date() : new Date(lastDate);
        if(request.accessToken){
            if(request.accessToken.userId){
                const Showroom = databaseObj.Showroom;
                Showroom.find({
                    where: {
                        brandId: brandId,
                        status: "active",
                        added:{
                            lt: lastDate
                        }
                    },
                    include:["areas", "cities"]
                })
                    .then(function(showroomList){
                        if(showroomList){
                            if(showroomList.length){
                                const showroom = showroomList[showroomList.length - 1];
                                lastDate = showroom.added;
                            }
                        }

                        callback(null, {
                            showroomList: showroomList,
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
     * To save the newly purchased vehicle
     * @param ctx
     * @param customerQuoteId
     * @param vehicleDetailObj
     * @param callback
     */
    const saveNewVehicle = function(ctx, customerQuoteId, insuranceObj, vehicleDetailObj, callback){
        const request = ctx.req;
        let vehicleInfoId;
        let insuranceId;
        let dealerId;
        if(!customerQuoteId && !vehicleDetailObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const CustomerQuote = databaseObj.CustomerQuote;
                   const SoldViaAutobox = databaseObj.SoldViaAutobox;
                   CustomerQuote.findById(customerQuoteId)
                       .then(function(customerQuote){
                           if(customerQuote){
                               return databaseObj.VehicleInfo.findById(customerQuote.vehicleInfoId)
                           }
                       })
                       .then(function(vehicleInfo){
                           if(vehicleInfo){
                               vehicleInfoId = vehicleInfo.id;
                               const Insurance = databaseObj.Insurance;
                               const policyEndDate = insuranceObj.policyEndDate ? moment(insuranceObj.policyEndDate, 'DD/MM/YYYY') : new Date();
                               return Insurance.create({
                                   insuranceProvider: insuranceObj.insuranceProvider,
                                   policyEndDate: policyEndDate,
                                   insurancePlanType: insuranceObj.insurancePlanType,
                                   policyNumber:insuranceObj.policyNumber,
                                   vehicleInfoId: vehicleInfoId
                               });

                           }
                       })
                       .then(function(insurance){
                           if(insurance){
                               insuranceId = insurance.id;
                               const Showroom = databaseObj.Showroom;
                               return Showroom.findById(vehicleDetailObj.showroomId);
                           }
                       })
                       .then(function(showroom){
                           if(showroom){
                               dealerId = showroom.dealerId;

                               return SoldViaAutobox.findOne({
                                   where: {
                                       dealerId : dealerId,
                                       customerQuoteId : customerQuoteId,
                                       customerId: customerId
                                   }
                               });

                           }
                       })
                       .then(function(soldViaAutobox){
                           if(!soldViaAutobox){
                               return SoldViaAutobox.create({
                                   dealerId : dealerId,
                                   customerQuoteId : customerQuoteId,
                                   customerId: customerId
                               })
                           }
                       })
                       .then(function(soldViaAutobox){
                           return databaseObj.CustomerQuote.findById(customerQuoteId);
                       })
                       .then(function(customerQuote){
                           if(customerQuote){
                               return customerQuote.updateAttributes({
                                   dealerId : dealerId,
                                   soldViaAutobox: "yes",
                                   isSoldViaAutobox: true
                               });
                               //return customerQuote.updateAttribute("dealerId", dealerId);
                           }
                       })
                       .then(function(customerQuote){
                           if(customerQuote){
                               const VehicleDetail = databaseObj.VehicleDetail;
                               return VehicleDetail.create({
                                   workshopName: vehicleDetailObj.workshopName,
                                   showroomName: vehicleDetailObj.showroomName,
                                   registeredName: vehicleDetailObj.registeredName,
                                   registrationNumber: vehicleDetailObj.registrationNumber,
                                   showroomId: vehicleDetailObj.showroomId,
                                   workshopId: vehicleDetailObj.workshopId,
                                   customerId: customerId,
                                   vehicleInfoId : vehicleInfoId,
                                   insuranceId: insuranceId,
                                   status: "active",
                                   vehicleType: "new"
                               });
                           }
                       })
                       .then(function(vehicleDetail){
                           if(vehicleDetail){
                               callback(null, vehicleDetail);
                           }
                       })
                       .catch(function(error){
                           callback(error);
                       })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }

    };

    const saveExistingVehicle = function(ctx, vehicleInfoObj, insuranceObj, vehicleDetailObj, callback){
      const request = ctx.req;
        let vehicleInfoId;
        let insuranceId;
      if(!vehicleInfoObj && !vehicleDetailObj){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const VehicleInfo = databaseObj.VehicleInfo;
                  VehicleInfo.create({
                      colorId : vehicleInfoObj.colorId,
                      brandId: vehicleInfoObj.brandId,
                      carModelId: vehicleInfoObj.carModelId,
                      trimId: vehicleInfoObj.trimId,
                      customerId: customerId,
                      gearBoxId: vehicleInfoObj.gearBoxId,
                      fuelId: vehicleInfoObj.fuelId,
                      vehicleModel: vehicleInfoObj.vehicleModel,
                      vehicleType: "car",
                      quoteType : "q",
                      fuelType: vehicleInfoObj.fuelType,
                      vehicleTrim: vehicleInfoObj.vehicleTrim,
                      vehicleBrand: vehicleInfoObj.vehicleBrand,
                      vehicleGearbox : vehicleInfoObj.vehicleGearbox,
                      vehicleColor: vehicleInfoObj.vehicleColor
                  })
                      .then(function(vehicleInfo){
                          if(vehicleInfo){
                              vehicleInfoId = vehicleInfo.id;
                              const Insurance = databaseObj.Insurance;
                              const policyEndDate = insuranceObj.policyEndDate ? moment(insuranceObj.policyEndDate, 'DD/MM/YYYY') : new Date();
                              return Insurance.create({
                                  insuranceProvider: insuranceObj.insuranceProvider,
                                  policyEndDate: policyEndDate,
                                  insurancePlanType: insuranceObj.insurancePlanType,
                                  policyNumber:insuranceObj.policyNumber,
                                  vehicleInfoId: vehicleInfoId
                              });

                          }
                      })
                      .then(function(insurance){
                          if(insurance){
                              insuranceId = insurance.id;
                              const VehicleDetail = databaseObj.VehicleDetail;
                              return VehicleDetail.create({
                                  workshopName: vehicleDetailObj.workshopName,
                                  registeredName: vehicleDetailObj.registeredName,
                                  registrationNumber: vehicleDetailObj.registrationNumber,
                                  workshopId: vehicleDetailObj.workshopId,
                                  customerId: customerId,
                                  vehicleInfoId : vehicleInfoId,
                                  insuranceId : insuranceId,
                                  status: "active",
                                  vehicleType: "existing"
                              })
                          }
                      })
                      .then(function(vehicleDetail){
                          if(vehicleDetail){
                              callback(null, vehicleDetail);
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else{
                  callback(new Error("User not valid"));
              }
          } else{
              callback(new Error("User not valid"));
          }
      }
    };

    /**
     * to save the vehicle detail of the customer
     * @param ctx
     * @param vehicleDetailObj
     * @param callback
     * @returns {*}
     */
    const saveVehicleDetails = function(ctx, vehicleDetailObj, vehicleInfoObj, callback){
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
                          return databaseObj.Dealer.findOne({
                              where: {
                                  showroomId: vehicleDetailObj.showroomId
                              }
                          })
                          //callback(null, vehicleDetailObj);
                      } else{
                          callback(new Error("Vehicle Detail can't be saved"));
                      }
                  })
                  .then(function(dealer){
                      if(dealer){
                          return databaseObj.SoldViaAutobox.create({
                              type: vehicleInfoObj.vehicleType,
                              dealerId: dealer.id,
                              vehicleInfoId: vehicleInfoObj.id

                          })
                      } else{
                          callback(new Error("Dealer not present"));
                      }
                  })
                  .then(function(soldViaAutoboxObj){
                      if(soldViaAutoboxObj){
                          databaseObj.CustomerQuote.findOne({
                              where: {
                                  customerId : customerId,
                                  vehicleInfoId : soldViaAutoboxObj.vehicleInfoId
                              }
                          })
                      } else{
                          callback(new Error("Error in creating SoldViaAutobox"))
                      }
                  })
                  .then(function(customerQuote){
                      if(customerQuote){
                           return customerQuote.updateAttribute("soldViaAutobox", "yes")
                      }else{
                          callback(new Error("Customer Quote cannot be found"));
                      }
                  })

                  .then(function(customerQuote){
                      callback(null,{"response": "success" });
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
                      time: moment().format('LT'),
                      userType: customerMessageObj.userType,
                      dealerId: customerMessageObj.dealerId,
                      customerId: customerId,
                      customerQuoteId: customerMessageObj.customerQuoteId,
                      firstName: customerMessageObj.firstName,
                      lastName: customerMessageObj.lastName,
                      mobileNumber: customerMessageObj.mobileNumber
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
                   /* filter = filter || {};
                    filter.where = filter.where || {};
                    if(filter){
                        if(filter.where){
                            if(filter.where.added){
                                if(!filter.where.added.lt){
                                    filter.where.added.lt = new Date();
                                }
                            }
                        }
                    }*/
                    ServiceType.find(filter)
                        .then(function(serviceTypeList){
                            if(serviceTypeList){
                                if(serviceTypeList.length){
                                    /*const serviceType = serviceTypeList[serviceTypeList.length - 1];
                                    lastDate = serviceType.added;*/
                                }
                            }
                            callback(null, serviceTypeList);
                          /*  callback(null, {
                                serviceTypeList: serviceTypeList,
                                cursor: lastDate
                            })*/
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
      let vehicleInfoId;
      if(!serviceBookingObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const VehicleDetail = databaseObj.VehicleDetail;
                  const ServiceBooking = databaseObj.ServiceBooking;
                  VehicleDetail.findById(serviceBookingObj.vehicleDetailId)
                      .then(function(vehicleDetail){
                          if(vehicleDetail){
                              vehicleInfoId = vehicleDetail.vehicleInfoId;
                              return ServiceBooking.create({
                                  workshopId: serviceBookingObj.workshopId,
                                  vehicleDetailId: serviceBookingObj.vehicleDetailId,
                                  vehicleInfoId : vehicleInfoId,
                                  customerId: customerId
                              })
                          }
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
    const createCustomerQuote = function(ctx, vehicleInfoObj, customerQuoteObj, callback){
      const request = ctx.req;
      if(!customerQuoteObj){
          return callback(new Error("Invalid Arguments"));
      }else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const VehicleInfo = databaseObj.VehicleInfo;
                  const CustomerQuote = databaseObj.CustomerQuote;
                  if(customerQuoteObj.isOldVehicleTrade === "yes"){
                      const OldTradeCar = databaseObj.OldTradeCar;
                      OldTradeCar.create({
                          brandId : customerQuoteObj.brandId,
                          carModelId : customerQuoteObj.carModelId,
                          trimId : customerQuoteObj.trimId,
                          miles: customerQuoteObj.miles,
                          customerId : customerId,
                          variantName : customerQuoteObj.oldTradeVariantName ? customerQuoteObj.oldTradeVariantName : ""
                      })
                          .then(function(oldTradeCar){
                              if(oldTradeCar){
                                  return VehicleInfo.create({
                                      colorId : vehicleInfoObj.colorId,
                                      brandId: vehicleInfoObj.brandId,
                                      carModelId: vehicleInfoObj.carModelId,
                                      trimId: vehicleInfoObj.trimId,
                                      customerId: customerId,
                                      gearBoxId: vehicleInfoObj.gearBoxId,
                                      fuelId: vehicleInfoObj.fuelId,
                                      vehicleModel: vehicleInfoObj.vehicleModel,
                                      vehicleType: "car",
                                      quoteType : "q",
                                      fuelType: vehicleInfoObj.fuelType,
                                      vehicleTrim: vehicleInfoObj.vehicleTrim,
                                      vehicleBrand: vehicleInfoObj.vehicleBrand,
                                      vehicleGearbox : vehicleInfoObj.vehicleGearbox,
                                      vehicleColor: vehicleInfoObj.vehicleColor
                                  })
                              }
                          })
                          .then(function(vehicleInfoObj){
                              if(vehicleInfoObj){
                                  const vehicleInfoId = vehicleInfoObj.id;
                                  if(customerQuoteObj.ownershipType === "individual"){
                                      customerQuoteObj.ownershipType = "ind";
                                  } else if(customerQuoteObj.ownershipType === "corporate"){
                                      customerQuoteObj.ownershipType = "cor"
                                  }

                                  return  CustomerQuote.create({
                                      vehicleInfoId: vehicleInfoId,
                                      cityId: customerQuoteObj.cityId,
                                      currentBrandId: vehicleInfoObj.brandId,
                                      ownershipType: customerQuoteObj.ownershipType,
                                      isFinance: customerQuoteObj.isFinance,
                                      isInsurance: customerQuoteObj.isInsurance,
                                      isOldVehicleTrade: customerQuoteObj.isOldVehicleTrade,
                                      brandId: customerQuoteObj.brandId,
                                      carModelId: customerQuoteObj.carModelId,
                                      trimId: customerQuoteObj.trimId,
                                      quoteType: customerQuoteObj.quoteType,
                                      miles: customerQuoteObj.miles,
                                      customerId: customerId,
                                      soldViaAutobox: "no",
                                      gpsTracker: "no",
                                      dashCamera: "no",
                                      testDrive: "no",
                                      status: "active",
                                      purchaseStatus: "notpurchased"
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
                  } else{
                      VehicleInfo.create({
                          colorId : vehicleInfoObj.colorId,
                          brandId: vehicleInfoObj.brandId,
                          carModelId: vehicleInfoObj.carModelId,
                          trimId: vehicleInfoObj.trimId,
                          customerId: customerId,
                          gearBoxId: vehicleInfoObj.gearBoxId,
                          fuelId: vehicleInfoObj.fuelId,
                          vehicleModel: vehicleInfoObj.vehicleModel,
                          vehicleType: "car",
                          quoteType : "q",
                          fuelType: vehicleInfoObj.fuelType,
                          vehicleTrim: vehicleInfoObj.vehicleTrim,
                          vehicleBrand: vehicleInfoObj.vehicleBrand,
                          vehicleGearbox : vehicleInfoObj.vehicleGearbox,
                          vehicleColor: vehicleInfoObj.vehicleColor
                      })
                          .then(function(vehicleInfoObj){
                              if(vehicleInfoObj){
                                  const vehicleInfoId = vehicleInfoObj.id;
                                  if(customerQuoteObj.ownershipType === "individual"){
                                      customerQuoteObj.ownershipType = "ind";
                                  } else if(customerQuoteObj.ownershipType === "corporate"){
                                      customerQuoteObj.ownershipType = "cor"
                                  }

                                  return  CustomerQuote.create({
                                      vehicleInfoId: vehicleInfoId,
                                      cityId: customerQuoteObj.cityId,
                                      currentBrandId: vehicleInfoObj.brandId,
                                      ownershipType: customerQuoteObj.ownershipType,
                                      isFinance: customerQuoteObj.isFinance,
                                      isInsurance: customerQuoteObj.isInsurance,
                                      isOldVehicleTrade: customerQuoteObj.isOldVehicleTrade,
                                      brandId: customerQuoteObj.brandId,
                                      carModelId: customerQuoteObj.carModelId,
                                      trimId: customerQuoteObj.trimId,
                                      quoteType: customerQuoteObj.quoteType,
                                      customerId: customerId,
                                      soldViaAutobox: "no",
                                      gpsTracker: "no",
                                      dashCamera: "no",
                                      testDrive: "no",
                                      status: "active",
                                      purchaseStatus: "notpurchased"
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
                  }

              } else{
                  return callback(new Error("User not valid"));
              }
          }else{
              return callback(new Error("User not valid"));
          }
      }
    };


    const createTestDriveQuote = function(ctx, vehicleInfoObj, callback){
        const request = ctx.req;
        let cityId;
        if(!vehicleInfoObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const VehicleInfo = databaseObj.VehicleInfo;
                    const Customer = databaseObj.Customer;
                    Customer.findById(customerId)
                        .then(function(customer){
                            if(customer){
                                cityId = customer.cityId;
                                return VehicleInfo.create({
                                    brandId: vehicleInfoObj.brandId,
                                    carModelId: vehicleInfoObj.carModelId,
                                    trimId: vehicleInfoObj.trimId,
                                    customerId: customerId,
                                    gearBoxId: vehicleInfoObj.gearBoxId,
                                    fuelId: vehicleInfoObj.fuelId,
                                    vehicleModel: vehicleInfoObj.vehicleModel,
                                    vehicleType: "car",
                                    quoteType : "t",
                                    fuelType: vehicleInfoObj.fuelType,
                                    vehicleTrim: vehicleInfoObj.vehicleTrim,
                                    vehicleBrand: vehicleInfoObj.vehicleBrand,
                                    vehicleGearbox : vehicleInfoObj.vehicleGearbox
                                })
                            }
                        })
                        .then(function(vehicleInfo){
                            const vehicleInfoId = vehicleInfo.id;
                            const CustomerQuote = databaseObj.CustomerQuote;
                            return CustomerQuote.create({
                                vehicleInfoId: vehicleInfoId,
                                quoteType: "t",
                                currentBrandId: vehicleInfoObj.brandId,
                                customerId: customerId,
                                cityId: cityId,
                                status: "active",
                                purchaseStatus: "notpurchased"
                            })
                        })

                        .then(function(customerQuote){
                            if(customerQuote){
                                callback(null, customerQuote);
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })

                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
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
    const findAllCustomerQuote = function(ctx, filter, status, callback){
      const request = ctx.req;
      if(!filter){
          return callback(new Error("Invalid Arguments"));
      }else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  if(!filter.order){
                      filter.order = "added DESC";
                  }
                  const customerId = request.accessToken.userId;
                  const CustomerQuote = databaseObj.CustomerQuote;
                  CustomerQuote.find({
                       where:{
                           customerId:customerId,
                           quoteType: "q",
                           status: "active",
                           purchaseStatus: status
                       },
                       order:"added DESC",
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

                              }
                          }

                          callback(null, customerQuoteList);
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else {
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }
      }
    };

    const createQuoteReply = function(ctx, quoteReplyObj, callback){
        const request = ctx.req;
        if(!quoteReplyObj){
            callback(new Error("Invalid arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const dealerId = request.accessToken.userId;
                    const QuoteReply = databaseObj.QuoteReply;
                    const insurance = quoteReplyObj.insurance? quoteReplyObj.insurance : 0;
                    QuoteReply.create({
                        exShowroomPrice : quoteReplyObj.exShowroomPrice,
                        exchangeBonus : quoteReplyObj.exchangeBonus,
                        insurance : insurance,
                        specialDiscount : quoteReplyObj.specialDiscount,
                        rtoRegistration : quoteReplyObj.rtoRegistration,
                        loyaltyBonus : quoteReplyObj.loyaltyBonus,
                        miscCharges : quoteReplyObj.miscCharges,
                        gst : quoteReplyObj.gst,
                        roadPrice: quoteReplyObj.roadPrice,
                        customerQuoteId : quoteReplyObj.customerQuoteId,
                        dealerId : dealerId
                    })
                        .then(function(quoteReply){
                            if(quoteReply){
                                callback(null, quoteReply);
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("Dealer not found"));
                }
            } else{
                callback(new Error("Dealer not found"));
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
    const fetchQuoteReplyFromDealer = function(ctx, filter, lat, lang, callback){
        const request = ctx.req;
        const QuoteReply = databaseObj.QuoteReply;
        var quoteReplyResultList = [];
        var promises = [];
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
                    filter.include = [{
                        relation: "dealer",
                        scope: {
                            include: [{
                                relation: "showroom",
                                scope: {
                                    include: ["areas", "cities"]
                                }
                            },
                                {
                                    relation: "workshop",
                                    scope: {
                                        include: ["areas", "cities"]
                                    }
                                },
                                {
                                    relation: "brand"
                                }]
                        }
                    }];
                    const customerQuoteId = filter.where.customerQuoteId;
                    QuoteReply.find({
                        where: {
                            customerQuoteId: customerQuoteId
                        },
                        include: [{
                            relation: "dealer",
                            scope: {
                                include: [{
                                    relation: "showroom",
                                    scope: {
                                        include: ["areas", "cities"]
                                    }
                                },
                                    {
                                        relation: "workshop",
                                        scope: {
                                            include: ["areas", "cities"]
                                        }
                                    },
                                    {
                                        relation: "brand"
                                    }]
                            }
                        }]
                    })
                        .then(function(quoteReplyList){
                            if(quoteReplyList){
                                if(quoteReplyList.length){
                                    quoteReplyList.forEach(function(quoteReply){
                                        promises.push(function(callback){
                                            quoteReply.updateAttribute("distance", getDistance(lat, lang, quoteReply.dealer().showroom().latlong["lat"], quoteReply.dealer().showroom().latlong["lng"]))
                                                .then(function(quoteReply){
                                                    if(quoteReply){
                                                        callback(null);
                                                    }
                                                })
                                                .catch(function(error){
                                                    callback(error);
                                                })
                                        })
                                    })
                                }
                                async.series(promises, function(error){
                                    if(error){
                                        callback(error);
                                    } else{
                                        QuoteReply.find(filter)
                                            .then(function(quoteReplyList){
                                                if(quoteReplyList){
                                                    if(quoteReplyList.length){
                                                        const quoteReply = quoteReplyList[quoteReplyList.length - 1];
                                                        lastDate = quoteReply.added;
                                                    }
                                                }
                                                //server.logger.info(quoteReplyList);
                                                callback(null, {
                                                    quoteReplyList: quoteReplyList,
                                                    cursor: lastDate
                                                })
                                            })
                                            .catch(function(error){
                                                //server.logger.error(error);
                                                callback(error);
                                            })
                                    }
                                })
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
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

    var getDistance = function(lat1, lon1, lat2, lon2){
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2-lat1);  // deg2rad below
        var dLon = deg2rad(lon2-lon1);
        var a =
            Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
        ;
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = R * c; // Distance in km
        return d;
    };

    var deg2rad = function(deg){
        return deg * (Math.PI/180)
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
          if(request.accessToken){
              if(request.accessToken.userId){
                  const userId = request.accessToken.userId;
                  const Feedback = databaseObj.Feedback;
                  Feedback.create({
                      subject: "",
                      message: feedbackObj.message,
                      customerId: userId
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
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const ServiceHistory = databaseObj.ServiceHistory;
                    ServiceHistory.create({
                        dateOfBooking: moment(serviceObj.dateOfBooking, "DD/MM/YYYY"),
                        mileageCompleted: serviceObj.mileageCompleted,
                        charges: serviceObj.charges,
                        customerId: customerId,
                        workshopId: serviceObj.workshopId,
                        serviceTypeId: serviceObj.serviceTypeId,
                        vehicleDetailId : serviceObj.vehicleDetailId

                    })
                        .then(function(serviceObj){
                           if(serviceObj){
                               callback(null, serviceObj);
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
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  if(filter){
                      if(filter.where){
                          if(filter.where.added){
                              if(!filter.where.added.lt){
                                  filter.where.added.lt = new Date();
                              }
                          }
                          if(!filter.customerId){
                              filter.customerId = customerId;
                          }
                      }

                      filter.include = ["workshop", "serviceType"];
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

                  } else{
                  return callback(new Error("User not valid"));
              }
          } else{
              return callback(new Error("User not valid"));
          }
      }
    };


    /**
     * To find all the vehicles belongs to the customer
     * @param ctx
     * @param filter
     * @param callback
     * @returns {*}
     */
    const findAllVehicles = function(ctx, filter, callback){
      const request = ctx.req;
      let lastDate;
      if(!filter){
          return callback(new Error("Invalid Arguments"));
      } else {
          if (request.accessToken) {
              if (request.accessToken.userId) {
                  const customerId = request.accessToken.userId;
                  const VehicleDetail = databaseObj.VehicleDetail;
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

                  if(filter.where){
                      if(!filter.where.customerId){
                          filter.where.customerId = customerId;
                      }
                  }

                  if(filter.where){
                      if(!filter.where.status){
                          filter.where.status = "active"
                      }
                  }
                  filter.include = [{
                      relation: "vehicleInfo",
                      scope: {
                          include: ["brand", "carModel", "fuel", "gearBox", "trim", "color"]
                      }
                  },
                      {
                          relation: "workshop",
                          scope: {
                              include: ["areas", "cities"]
                          }
                      },
                      {
                          relation: "insurance"
                      },
                      {
                          relation: "showroom",
                          scope: {
                              include: ["areas", "cities"]
                          }
                      }];

                  VehicleDetail.find(filter)
                      .then(function(vehicleDetailList){
                          if(vehicleDetailList){
                              if(vehicleDetailList.length){
                                  const vehicleDetail = vehicleDetailList[vehicleDetailList.length - 1];
                                  lastDate = vehicleDetail.added;
                              }
                          }
                          callback(null, {
                              vehicleDetailList: vehicleDetailList,
                              cursor: lastDate
                          })
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


    const saveCustomer = function(ctx, customerObj, callback){
      const request = ctx.req;
      if(!customerObj){
          return callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const Customer = databaseObj.Customer;
                  Customer.findById(customerId)
                      .then(function(customer){
                          if(customer){
                              return Customer.upsert({
                                  firstName : customerObj.firstName,
                                  lastName : customerObj.lastName,
                                  email : customerObj.email,
                                  cityId : customerObj.cityId,
                                  countryName : customerObj.countryName,
                                  cityName: customerObj.cityName,
                                  workshopId : customerObj.workshopId,
                                  phoneNumber: customerObj.phoneNumber,
                                  bloodGroup: customerObj.bloodGroup,
                                  id: customerId,
                                  status: "active",
                                  added: customer.added,
                                  sosStatus : "incomplete"

                              });
                          } else{
                              callback(new Error("Customer not found"));
                          }
                      })
                      .then(function(customer){
                          if(customer){
                              callback(null, customer);
                          } else{
                              callback(new Error("Customer cannot be updated"));
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else{
                  callback(new Error("User not valid"));
              }
          } else{
              callback(new Error("User not valid"));
          }
      }
    };


    const storeSosData = function(ctx, sosObj, callback){
        const request = ctx.req;
        if(!sosObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const Sos = databaseObj.Sos;
                    const contact2 =  sosObj.contact2? sosObj.contact2 : {};
                    const contact3 = sosObj.contact3? sosObj.contact3 : {};
                    Sos.create({
                        contact1: sosObj.contact1,
                        contact2: contact2,
                        contact3: contact3,
                        customerId : customerId
                    })
                        .then(function(sos){
                            if(sos){
                                return databaseObj.Customer.findById(customerId);
                                //callback(null, sosObj);
                            }
                        })
                        .then(function(customer){
                            return customer.updateAttribute("sosStatus", "complete");
                        })
                        .then(function(customer){
                            callback(null, {response: "success"});
                        })
                        .catch(function(error){
                            callback(error);
                        })

                }
            }
        }
    };

    const deleteVehicle = function(ctx, vehicleDetailId, callback){
      const request = ctx.req;
      if(!vehicleDetailId){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const VehicleDetail = databaseObj.VehicleDetail;
                  VehicleDetail.findById(vehicleDetailId)
                      .then(function(vehicleDetail){
                          if(vehicleDetail){
                              return vehicleDetail.updateAttribute("status", "inactive");
                          }
                      })
                      .then(function(vehicleDetail){
                          if(vehicleDetail){
                              callback(null, {response: "success"});
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else{
                  callback(new Error("User not valid"));
              }
          } else{
              callback(new Error("User not valid"));
          }
      }
    };

    const cancelQuote = function(ctx, customerQuoteId, callback){
        const request = ctx.req;
        if(!customerQuoteId){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const CustomerQuote = databaseObj.CustomerQuote;
                    CustomerQuote.findById(customerQuoteId)
                        .then(function(customerQuote){
                            if(customerQuote){
                                return customerQuote.updateAttribute("status", "inactive");
                            }
                        })
                        .then(function(customerQuote){
                            if(customerQuote){
                                callback(null, {response: "success"});
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const findAllOffer = function(ctx, cityId, callback){
      const request = ctx.req;
      if(!cityId){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const Offer = databaseObj.Offer;
                  Offer.find({
                      where:{
                          cityId :cityId,
                          status: "active"
                      },
                      include: "dealer"
                  })
                      .then(function(offerList){
                          if(offerList){
                              if(offerList.length){

                              }
                          }
                          callback(null, offerList);
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else{
                  callback(new Error("User not valid"));
              }
          } else{
              callback(new Error("user not valid"));
          }
      }
    };

     const offerQuery = function(ctx, offerQueryObj, callback){
         const request = ctx.req;
         if(!offerQueryObj){
             callback(new Error("Invalid Arguments"));
         } else{
             if(request.accessToken){
                 if(request.accessToken.userId){
                     const customerId = request.accessToken.userId;
                     const OfferQuery = databaseObj.OfferQuery;
                     const subject = offerQueryObj.subject ? offerQueryObj.subject : "";
                     const message = offerQueryObj.message ? offerQueryObj.message : "";
                     const customerContact = offerQueryObj.customerContact ? offerQueryObj.customerContact : "";
                     OfferQuery.create({
                         subject: subject,
                         message: message,
                         customerContact : customerContact,
                         dealerId : offerQueryObj.dealerId,
                         customerId : customerId,
                         queryType : offerQueryObj.queryType
                     })
                         .then(function(offerQuery){
                             if(offerQuery){
                                 callback(null, offerQuery);
                             }
                         })
                         .catch(function(error){
                             callback(error);
                         })
                 } else{
                     callback(new Error("User not valid"));
                 }
             } else{
                 callback(new Error("User not valid"));
             }
         }
     };

     const fetchFeedbackShowroom = function(ctx, callback){
         const request = ctx.req;
         let showroomList = [];
         if(request.accessToken){
             if(request.accessToken.userId){
                 const customerId = request.accessToken.userId;
                 const VehicleDetail = databaseObj.VehicleDetail;
                 VehicleDetail.find({
                     where: {
                         customerId : customerId,
                         vehicleType : "new"
                     },
                     include: "showroom"
                 })
                     .then(function(vehicleDetailList){
                         if(vehicleDetailList){
                             if(vehicleDetailList.length){
                                 showroomList = _.uniqBy(vehicleDetailList, 'showroomId');
                             }
                         }
                         callback(null, showroomList);
                     })
                     .catch(function(error){
                         callback(error);
                     })
             } else{
                 callback(new Error("User not valid"));
             }
         } else{
             callback(new Error("User not valid"));
         }
     };

    const fetchSosSettings = function(ctx, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const Sos = databaseObj.Sos;
                Sos.findOne({
                    where: {
                        customerId : customerId
                    }
                })
                    .then(function(sos){
                        if(sos){
                            callback(null, sos);
                        } else{
                            callback(null, {});
                        }
                    })
                    .catch(function(error){
                        callback(error);
                    })
            } else{
                callback(new Error("User not valid"));
            }
        } else{
            callback(new Error("User not valid"));
        }
    };

    const incrementReferralCount = function(referralCode, callback){
        if(!referralCode){
            callback(new Error("Invalid Arguments"));
        } else{
            const Customer = databaseObj.Customer;
            Customer.findOne({
                where: {
                    referralCode: referralCode
                }
            })
            .then(function(customer){
                if(customer){
                    customer.referralCount = customer.referralCount + 1;
                    return customer.updateAttribute("referralCount", customer.referralCount);
                } else{
                    callback(new Error("Referral Code not found"));
                }
            })
            .then(function(customer){
                if(customer){
                    callback(null, {response:"success"});
                }
            })
            .catch(function(error){
                callback(error);
            })
        }
    };


    const removeSos = function(ctx, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const Sos = databaseObj.Sos;
                Sos.findOne({
                    where: {
                        customerId : customerId
                    }
                })
                    .then(function(sos){
                        if(sos){
                            const sosId = sos.id;
                            return sos.updateAttributes({
                                contact1: {},
                                contact2: {},
                                contact3: {}
                            });
                        }
                    })
                    .then(function(sos){
                        callback(null, {response: "success"});
                    })
                    .catch(function(error){
                        callback(error);
                    })
            } else{
                callback(new Error("User not valid"));
            }
        } else{
            callback(new Error("User not valid"));
        }
    };

    const findAllCustomerOffers = function(ctx, lastDate, callback){
        const request = ctx.req;
        let resultCustomerOfferList = [];
        lastDate = !lastDate ? new Date() : new Date(lastDate);
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const CustomerOffer = databaseObj.CustomerOffer;
                CustomerOffer.find({
                    limit: 10,
                    where: {
                        customerId: customerId,
                        removeStatus: false,
                        added: {
                            lt: lastDate
                        },
                        status: "active"
                    },
                    order: ["added DESC"],
                    include: [{
                        relation: "offer",
                        scope: {
                            include: ["dealer"]
                        }
                    }]
                })
                    .then(function(customerOfferList){
                        if(customerOfferList){
                            if(customerOfferList.length){
                                lastDate = customerOfferList[customerOfferList.length];
                            }
                            customerOfferList.forEach(function(customerOffer){
                                if(customerOffer.offerId){
                                    resultCustomerOfferList.push(customerOffer);
                                }
                            })
                        }
                        callback(null, {
                            data: resultCustomerOfferList,
                            cursor: lastDate
                        })
                    })
                    .catch(function(error){
                        server.logger.error(error);
                        callback(error);
                    })
            } else{
                callback(new Error("User not valid"));
            }
        } else{
            callback(new Error("User not valid"));
        }
    };

    const rateDealerExperience = function(ctx, dealerRatingObj, callback){
      const request = ctx.req;
      let dealerName;
      if(!dealerRatingObj){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const Dealer = databaseObj.Dealer;
                  const DealerRating = databaseObj.DealerRating;
                  Dealer.findById(dealerRatingObj.dealerId)
                      .then(function(dealer){
                          if(dealer){
                              dealerName = dealer.firstName + " " + dealer.lastName
                          }
                          return DealerRating.create({
                              customerId: customerId,
                              dealerId : dealerRatingObj.dealerId,
                              dealerName: dealerName,
                              customerName: dealerRatingObj.customerName,
                              rating: dealerRatingObj.rating
                          })
                      })
                      .then(function(dealerRating){
                          if(dealerRating){
                              callback(null, {response:"success"});
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      })
              } else{
                  callback(new Error("User not valid"));
              }
          } else{
              callback(new Error("User not valid"));
          }
      }
    };

    const findAllQuoteMessage = function(ctx, filter, lastDate, callback){
        lastDate = !lastDate? new Date() : new Date(lastDate);
        let limit;
        let resultMessageList = [];
        const request = ctx.req;
        if(!filter && !lastDate){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    limit = filter.limit;
                    const CustomerMessage = databaseObj.CustomerMessage;
                    CustomerMessage.find({
                        limit : limit,
                        where: {
                            customerId : customerId,
                            added: {
                                lt : lastDate
                            },
                            type: "message",
                            status: "contacted"
                        },
                        include: [
                            {
                                relation: "dealer"
                            },
                            {
                                relation: "customerQuote",
                                scope: {
                                    include: ["vehicleInfo"]
                                }
                            }
                        ]
                    })
                        .then(function(customerMessageList){
                            if(customerMessageList){
                                if(customerMessageList.length){
                                    const customerMessage = customerMessageList[customerMessageList.length - 1];
                                    lastDate = customerMessage.added;
                                    customerMessageList.forEach(function(customerMessage){
                                        if(customerMessage.replyMessage){
                                            resultMessageList.push(customerMessage);
                                        }
                                    })
                                }
                            }
                            callback(null, {
                                data: customerMessageList,
                                cursor: lastDate
                            })
                        })
                        .catch(function(error){
                            callback(error);
                        })

                }else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const updateSosData = function(ctx, sosObj, callback){
        const request = ctx.req;
        if(!sosObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const Sos = databaseObj.Sos;
                    Sos.upsert(sosObj)
                        .then(function(sos){
                            if(sos){
                                callback(null, sos);
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };


    return {
        init: init
    };
};