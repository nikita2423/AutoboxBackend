/**
 * Created by nikita on 3/8/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const push = helper.loadPlugin("pushService");
    const emailPlugin = helper.loadPlugin("email");
    const send = helper.loadPlugin("smsService");
    const Promise = require("bluebird");
    const async = require("async");

    var init = function(){
        sendCreateQuoteNotification();
        onCustomerSaved();
        onCompletePurchaseMethod();
        sendSOSRequestMethod();
        onOfferCreate();
        onVehicleAddNotification();
        createServiceBookingNotification();
        sendQuoteReplyNotification();
        sendOldTradeCarEmail();
        sendCustomerQuoteEmailToDealer();
        sendHardBrakingAccelerationNotification();
        sendGpsBatteryLowNotification();
        sendEngineStatusNotification();
        sendGpsDeviceStatusNotification();
        sendOverSpeedingNotification();
        sendVehicleTowingNotification();
    };


    const onCompletePurchaseMethod = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.onCompletePurchase = onCompletePurchase;
        CustomerQuote.remoteMethod("onCompletePurchase", {
            accepts:[
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
            returns:{
                arg: "response", type: "Object", root: true
            }
        });
    };


    const sendSOSRequestMethod = function(){
        const Sos = databaseObj.Sos;
        Sos.sendSOSRequest = sendSOSRequest;
        Sos.remoteMethod('sendSOSRequest', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                }
            ],
            returns:{
                arg: "response", type: "Object", root: true
            }
        });
    };


    /**
     * send Notification after Customer Quote is created
     */
    const sendCreateQuoteNotification = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const customerQuoteObj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    const Customer = databaseObj.Customer;
                    Customer.findById(customerQuoteObj.customerId)
                        .then(function(customer){
                            if(customer) {
                                customerQuoteObj.customer = customer;
                                return databaseObj.VehicleInfo.findById(customerQuoteObj.vehicleInfoId);
                            }
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo) {
                                customerQuoteObj.vehicleInfo = vehicleInfo;
                            }
                        })

                        .then(function () {
                           var name = customerQuoteObj.customer.firstName + " " + customerQuoteObj.customer.lastName;
                           var type = "CustomerQuote";
                           var title = "";
                           if(customerQuoteObj.quoteType === "q"){
                               title = "Your Quote for model " + customerQuoteObj.vehicleInfo.vehicleModel + " has been forwarded to dealers. You can view all the quotes with corresponding dealers on My Quotes Section";
                           } else if(customerQuoteObj.quoteType === "t"){
                               title = "Your Test Drive from " + customerQuoteObj.vehicleInfo.vehicleModel + " has been forwarded to the nearest authorised dealership";
                           }
                           var id = customerQuoteObj.id;
                           var from = packageObj.companyName;
                           var message = quoteNotificationFormat(name, type, title, id);
                           if(customerQuoteObj.customer.id){
                               sendNotification(server, message, customerQuoteObj.customer.id, from, function(error){
                                   if(error){
                                       console.log(error);
                                   } else{
                                       console.log("Push Notification for customer quote send successfully!");
                                   }
                               });
                           }
                        })
                        .catch(function(error){
                            console.log(error);
                        });
                });
            }
            next();
        });
    };




    /**
     * Send mail after the customer successfully registered
     */
    const onCustomerSaved = function(){
        const Customer = databaseObj.Customer;
        Customer.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const customerObj = instance.toJSON();
            if(customerObj.registerStatus === "notregistered" && !ctx.isNewInstance && customerObj.sosStatus === "incomplete"){
                process.nextTick(function(){
                    databaseObj.City.findById(customerObj.cityId)
                        .then(function(city){
                            if(city){
                                customerObj.city = city;
                                customerObj.cityName = city.name;
                                //return databaseObj.Country.findById(customerObj.countryId);
                            }
                        })
                        /*.then(function(country){
                            if(country){
                                customerObj.country = country;
                                customerObj.countryName = country.name;
                                return databaseObj.Workshop.findById(customerObj.workshopId)
                            }
                        })
                        .then(function(workshop){
                            if(workshop){
                                customerObj.workshop = workshop;
                                customerObj.serviceCenter = workshop.dealershipName;
                            }
                        })*/
                        .then(function(){
                            var name = customerObj.firstName + " " + customerObj.lastName;
                            var type = "Profile";
                            var title = "Welcome to AutoBox!!";
                            var id = customerObj.id;
                            var pushFrom = packageObj.companyName;
                            var message = welcomeMessageFormat(name, type, title, id);
                            if(customerObj.id) {
                                sendNotification(server, message, customerObj.id, pushFrom, function (error) {
                                    if (error) {
                                        console.log(error);
                                    } else {
                                        console.log("Push Notification for profile send successfully!");
                                    }
                                });
                            }

                            const subject = packageObj.customer.subject;
                            const to = [];
                            const from = packageObj.from;
                            to.push(customerObj.email);
                            emailPlugin.adminEmail.successfulRegistrationForCustomer(from, to, subject, customerObj, function (err, send) {
                                if(err){
                                    console.log(err);
                                } else{
                                    console.log("Email send Successfully");
                                }
                            });
                        })
                        .catch(function(error){
                            console.log(error);
                        });


                });
            }

            next();
        });
    };

    const sendOldTradeCarEmail = function(){
        const OldTradeCar = databaseObj.OldTradeCar;
        OldTradeCar.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const oldTradeVehicleObj = instance.toJSON();
            process.nextTick(function(){
               databaseObj.Customer.findById(oldTradeVehicleObj.customerId)
                   .then(function(customer){
                       if(customer){
                           oldTradeVehicleObj.customer = customer;
                           return databaseObj.Brand.findById(oldTradeVehicleObj.brandId);
                       }
                   })
                   .then(function(brand){
                       if(brand){
                           oldTradeVehicleObj.brand = brand;
                           return databaseObj.CarModel.findById(oldTradeVehicleObj.carModelId);
                       }
                   })
                   .then(function(carModel){
                       if(carModel){
                           oldTradeVehicleObj.carModel = carModel;
                           return databaseObj.Trim.findById(oldTradeVehicleObj.trimId);
                       }
                   })
                   .then(function(trim){
                       if(trim){
                           oldTradeVehicleObj.trim = trim;
                       }
                   })
                   .then(function(){
                       const subject = packageObj.admin.subject_add_old_vehicle;
                       const to = [];
                       const from = packageObj.from;
                       to.push("nikita@snaphy.com");
                       emailPlugin.adminEmail.onOldTradeCarAdded(from, to, subject, oldTradeVehicleObj, function(err, send){
                          if(err){
                              console.log(err);
                          } else{
                              console.log("Email Send Successfully for old trade vehicle");
                          }
                       });
                   })
                   .catch(function(error){
                       console.log(error);
                   });
            });
            next();
        });
    };


    const onCompletePurchase = function(ctx, customerQuoteId, callback){
        let customerQuoteObj;
        let email;
        let firstName;
        let lastName;
      if(!customerQuoteId){
          return callback(new Error("Invalid Arguments"));
      }  else{
          const request = ctx.req;
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  databaseObj.CustomerQuote.findById(customerQuoteId)
                      .then(function(customerQuote){
                          if(customerQuote){
                              return customerQuote.updateAttribute("purchaseStatus", "purchased");
                          } else{
                              callback(new Error("Customer Quote cannot be found"));
                          }
                      })
                      .then(function(customerQuote){
                          if(customerQuote){
                              customerQuoteObj = customerQuote;
                              return databaseObj.Customer.findById(customerId);
                          }
                      })
                      .then(function(customer){
                          if(customer){
                             // customerQuoteObj.customer = customer;
                              email = customer.email;
                              firstName = customer.firstName;
                              lastName = customer.lastName;
                          }
                      })
                      .then(function(){
                          //Send Notification
                          process.nextTick(function(){
                              const name = firstName + " " + lastName;
                              const pushFrom = packageObj.companyName;
                              const type = "CompletePurchase";
                              const title = "Your Car Purchase is completed successfully!";
                              const instanceId = customerQuoteObj.id;
                              const message = completePurchaseNotification(name, type, title, instanceId);
                              if(customerQuoteObj.customerId){
                                  sendNotification(server, message, customerQuoteObj.customerId, pushFrom, function(error){
                                      if(error){
                                          console.log(error);
                                      } else{
                                          console.log("Push Notification for Car Purchase has been successfully");
                                      }
                                  });
                              }
                              //Send email to customer for further instructions..
                              const subject = packageObj.customer.subject_complete_purchase;
                              const to = [];
                              const from = packageObj.from;
                              to.push(email);
                              emailPlugin.adminEmail.onCompletePurchaseForCustomer(from, to, subject,customerQuoteObj, function(err, send){
                                  if(err){
                                      console.log(err);
                                  } else{
                                      console.log("Email send Successfully for complete purchase");

                                  }
                              });
                          });
                          callback(null, {
                              response: "success"
                          });

                      })
                      .catch(function(error){
                          callback(error);
                      });
              }
          }
      }
    };


    const sendSOSRequest = function(ctx, callback){
      const request = ctx.req;
      let customerObj;
      if(request.accessToken){
          if(request.accessToken.userId){
              const customerId = request.accessToken.userId;
              const Customer = databaseObj.Customer;
              const Sos = databaseObj.Sos;
              Customer.findById(customerId)
                  .then(function(customer){
                      if(customer){
                          customerObj = customer;
                          return  Sos.findOne({
                              where: {
                                  customerId : customerId
                              }
                          });
                      }
                  })
                  .then(function(sos){
                      if(sos){
                          //send sms to three contacts
                          sendMessage(sos, function(error){
                              if(error){
                                  callback(error);
                              } else{
                                  const name = customerObj.firstName + " " + customerObj.lastName;
                                  const type = "SOS";
                                  const title = "Request has been successfully sent to all of your contacts";
                                  const pushFrom = packageObj.companyName;
                                  const instanceId = sos.id;
                                  const message = sosNotification(name, type, title, instanceId);
                                  if(customerId){
                                      sendNotification(server, message, customerId, pushFrom, function(error){
                                          if(error){
                                              console.log(error);
                                          } else{
                                              console.log("Push Notification for sos request send successfully!");

                                          }
                                      });
                                  }
                                  callback(null, {
                                      response: "success"
                                  });

                              }
                          });
                      }
                  })
                  .catch(function(error){
                      callback(error);
                  });

          } else{
              callback(new Error("User not valid"));
          }
      } else{
          callback(new Error("User not valid"));
      }
    };


    const sendMessage = function(sos, callback){
        if(sos.contact1){
            if(sos.contact1.firstContactNo){
                var number = sos.contact1.firstContactNo.toString();
                var message = "Contact as soon as possible";
                send.send(message, number, function(error){
                    if(error){
                        console.log("Unable to send request to first contact");
                        callback(error);
                    } else{
                        console.log("Request send to first contact successfully");
                        if(!sos.contact2 && !sos.contact3){
                            callback(null, {
                                response: "success"
                            });
                        }
                    }
                });

                if(sos.contact2){
                    if(sos.contact2.secondContactNo){
                        var number2 = sos.contact2.secondContactNo.toString();
                        var message2 = "Contact as soon as possible";
                        send.send(message2, number2, function(error){
                            if(error){
                                console.log("Unable to send request to second contact");
                                callback(error);
                            } else{
                                console.log("Request send to second contact successfully");
                                if(!sos.contact3){
                                    callback(null, {
                                        response: "success"
                                    });
                                }
                            }
                        });
                    } else{
                        callback(null, {});
                    }
                }

                if(sos.contact3){
                    if(sos.contact3.thirdContactNo){
                        var number3 = sos.contact3.thirdContactNo.toString();
                        var message3 = "Contact as soon as possible";
                        send.send(message3, number3, function(error){
                            if(error){
                                console.log("Unable to send request to third contact");
                                callback(error);
                            } else{
                                console.log("Request send to third contact successfully");
                                callback(null, {
                                    response: "success"
                                });
                            }
                        });
                    } else{
                        callback(null, {});
                    }
                }
            } else{
                callback(new Error("Cannot find any number to contact"));
            }
        } else{
            callback(new Error("Cannot find any number to contact"));
        }
    };

    const onVehicleAddNotification = function(){
        const VehicleDetail = databaseObj.VehicleDetail;
        VehicleDetail.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const vehicleDetailObj = instance.toJSON();
            if(ctx.isNewInstance){
              process.nextTick(function(){
                  databaseObj.Customer.findById(vehicleDetailObj.customerId)
                      .then(function(customer){
                          if(customer){
                             vehicleDetailObj.customer = customer;
                             return databaseObj.VehicleInfo.findById(vehicleDetailObj.vehicleInfoId);
                          }
                      })
                      .then(function(vehicleInfo){
                          if(vehicleInfo){
                              vehicleDetailObj.vehicleInfo = vehicleInfo;
                          }
                      })
                      .then(function(){
                          var name = vehicleDetailObj.customer.firstName + " " + vehicleDetailObj.customer.lastName;
                          const type = "AddVehicle";
                          const title = "Your Vehicle of Model Name " + vehicleDetailObj.vehicleInfo.vehicleModel + " has been saved successfully!";
                          const instanceId = vehicleDetailObj.id;
                          var pushFrom = packageObj.companyName;
                          const message = addVehicleNotificationFormat(name, type, title, instanceId);
                          if(vehicleDetailObj.customerId){
                              sendNotification(server, message, vehicleDetailObj.customerId, pushFrom, function(error){
                                  if(error){
                                      console.log(error);
                                  } else{
                                      console.log("Push Notification for adding vehicle send Successfully");
                                  }
                              });
                          }
                      })
                      .catch(function(error){
                          console.log(error);
                      });
              });
            }

            next();

        });
    };


    const createServiceBookingNotification = function() {
        const ServiceBooking = databaseObj.ServiceBooking;
        ServiceBooking.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const serviceBookingObj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.Customer.findById(serviceBookingObj.customerId)
                        .then(function(customer){
                            if(customer){
                                serviceBookingObj.customer = customer;
                            }
                        })
                        .then(function(){
                            const to = serviceBookingObj.customer.firstName + " " + serviceBookingObj.customer.lastName;
                            const type = "ServiceBooking";
                            const title = "Your Service Booking has been done Successfully";
                            const instanceId = serviceBookingObj.id;
                            var pushFrom = packageObj.companyName;
                            const message = serviceBookingNotificationFormat(to, type, title, instanceId);
                            if(serviceBookingObj.customerId){
                                sendNotification(server, message, serviceBookingObj.customerId, pushFrom, function(error){
                                    if(error){
                                        console.log(error);
                                    } else{
                                        console.log("Push Notification for service booking has been send successfully");
                                    }
                                })
                            }

                        })
                        .catch(function(error){
                            console.log(error);
                        })
                });
            }
            next();
        });
    };

    /*const sendGpsNotification = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.observe("after save", function(ctx, next){
            if(ctx.isNewInstance){
                let customerInstance;
                let eventType = "";
                let title = "";
                const instance = ctx.instance;
                const gpsPacketDataObj = instance.toJSON();
                const GpsPacketData = databaseObj.GpsPacketData;
                process.nextTick(function(){
                    databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                        .then(function(customer){
                            if(customer){
                                customerInstance = customer;
                            }
                        })
                        .then(function(){
                            var name = customerInstance.firstName;
                            var lastName = customerInstance.lastName ? customerInstance.lastName : "";
                            name = name + " " + lastName;
                            if(gpsPacketDataObj.eventType === packageObj.gps.power_fail){
                                eventType = gpsPacketDataObj.eventType;
                                title = "Power failed!";
                                var message = gpsMessageFormat(name, eventType, title, gpsPacketDataObj.id);
                                if(gpsPacketDataObj.customerId){
                                    sendNotification(server, message, gpsPacketDataObj.customerId, packageObj.companyName, function (err) {
                                        if (error) {
                                            console.error(error);
                                        } else {
                                            console.log("Notification for gps has been send Successfully");
                                        }
                                    });
                                }
                            } else if(gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
                                eventType = gpsPacketDataObj.eventType;
                                title = "Harsh Braking has been applied";
                                var message = gpsMessageFormat(name, eventType, title, gpsPacketDataObj.id);
                                if(gpsPacketDataObj.customerId){
                                    sendNotification(server, message, gpsPacketDataObj.customerId, packageObj.companyName, function (err) {
                                        if (error) {
                                            console.error(error);
                                        } else {
                                            console.log("Notification for gps has been send Successfully");
                                        }
                                    });
                                }
                            } else if(gpsPacketDataObj.eventType === packageObj.gps.harsh_acceleration){
                                eventType = gpsPacketDataObj.eventType;
                                title = "Harsh Acceleration has been applied";
                                var message = gpsMessageFormat(name, eventType, title, gpsPacketDataObj.id);
                                if(gpsPacketDataObj.customerId){
                                    sendNotification(server, message, gpsPacketDataObj.customerId, packageObj.companyName, function (err) {
                                        if (error) {
                                            console.error(error);
                                        } else {
                                            console.log("Notification for gps has been send Successfully");
                                        }
                                    });
                                }
                            } else if(gpsPacketDataObj.eventType === packageObj.gps.internal_battery_low){
                                eventType = gpsPacketDataObj.eventType;
                                title = "Battery is less than 35%";
                                var message = gpsMessageFormat(name, eventType, title, gpsPacketDataObj.id);
                                if(gpsPacketDataObj.customerId){
                                    sendNotification(server, message, gpsPacketDataObj.customerId, packageObj.companyName, function (err) {
                                        if (error) {
                                            console.error(error);
                                        } else {
                                            console.log("Notification for gps has been send Successfully");
                                        }
                                    });
                                }
                            } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                                eventType = gpsPacketDataObj.eventType;
                                return GpsPacketData.findOne({
                                    where:{
                                        customerId: customerInstance.id
                                    },
                                    order: ["added DESC"]
                                })
                            }
                        })
                        .then(function(gpsPacketData){
                            if(gpsPacketData){

                            }
                        })
                        .catch(function(error){
                            console.error(error);
                        })
                })
            }
        })
    };*/

    const sendCustomerQuoteEmailToDealer = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.observe("after save", function (ctx, next) {
            if(ctx.isNewInstance){
                const instance = ctx.instance;
                const customerQuoteObj = instance.toJSON();
                let brandInstance;
                let cityInstance;
                process.nextTick(function(){
                    if(customerQuoteObj.quoteType === 'q'){
                        customerQuoteObj.quoteType = "Quote";
                    } else if(customerQuoteObj.quoteType === 't'){
                        customerQuoteObj.quoteType = "Test Drive";
                    }
                    databaseObj.Customer.findById(customerQuoteObj.customerId)
                        .then(function(customer){
                            if(customer){
                                customerQuoteObj.customer = customer;
                                return databaseObj.VehicleInfo.findById(customerQuoteObj.vehicleInfoId)
                            }
                        })
                        .then(function (vehicleInfo) {
                            if(vehicleInfo){
                                customerQuoteObj.vehicleInfo = vehicleInfo;
                                return  databaseObj.City.findById(customerQuoteObj.cityId)
                            }
                        })
                        .then(function(city){
                            if(city){
                                cityInstance = city;
                                customerQuoteObj.city = city;
                                return databaseObj.Brand.findById(customerQuoteObj.currentBrandId);
                            }
                        })
                        .then(function(brand){
                            if(brand){
                                brandInstance = brand;
                                customerQuoteObj.brand = brand;
                                sendEmailToAllDealer(server, databaseObj, packageObj, brandInstance, cityInstance, customerQuoteObj);
                            }
                        })
                        .catch(function(error){
                            console.log(error);
                        });
                })
            }
            next();
        });
    };


    const sendEmailToAllDealer = function(server, databaseObj, packageObj, brand, city, customerQuoteInstance){
        var skip = 0;
        var limit = 10;
        const findDealerList = function(skip){
            //find customer..
            findDealer(server, databaseObj, packageObj, brand, city, skip, limit, function (err, list, cursor) {
                if (err) {
                    reject(error);
                } else {
                    if (cursor === null) {
                        //Stop all data
                        //send push
                        sendEmailToDealerList(list, customerQuoteInstance);
                    } else {
                        sendEmailToDealerList(list, customerQuoteInstance);
                        //Recursive call..
                        findDealerList(cursor);
                    }//else
                }//else
            }); //findDealer
        };//findAllDealerList
        findDealerList(skip);
    };

    const findDealer = function(server, databaseObj, packageObj, brand, city, skip, limit, callback){
        const Dealer = databaseObj.Dealer;
        var cityId = city.id;
        var brandId = brand.id;
        var filter = {
            skip: skip,
            limit : limit,
            where : {
                cityId: cityId,
                brandId: brandId
            }
        };

        Dealer.find(filter, function(error, instanceList){
            if(error){
                callback(error);
            } else{
                if(instanceList){
                    if(instanceList.length < limit){
                        //All data are fetched
                        callback(null, instanceList, null);
                    } else{
                        callback(null, instanceList, skip + limit);
                    }
                } else{
                    callback(error);
                }
            }

        });
    };

    const sendEmailToDealerList = function(dealerList, customerQuoteInstance){
        dealerList.forEach(function(dealer){
            const subject = packageObj.dealer.subject_customer_quote;
            const to = [];
            const from = packageObj.from;
            to.push(dealer.email);
            emailPlugin.adminEmail.quoteGeneratedForDealer(from, to, subject, customerQuoteInstance, function (err, send) {
                if(err){
                    console.log(err);
                } else{
                    console.log("Email send Successfully for Customer Quote to dealer");
                }
            });
        })
    };


    const onOfferCreate = function(){
      const Offer= databaseObj.Offer;
      Offer.observe("after save", function(ctx, next){
          if(ctx.isNewInstance){
              const instance = ctx.instance;
              const offerObj = instance.toJSON();
              const eventType = "Offer";
              const title = offerObj.title;
              const instanceId = offerObj.id;
              process.nextTick(function(){
                  databaseObj.City.findOne(offerObj.cityId)
                      .then(function(city){
                          if(city){
                              sendPushToAllCustomer(server, databaseObj, packageObj, eventType, title, city, instanceId, push, offerObj);
                          }
                      })
                      .catch(function(error){
                         next(error);
                      });
              });
          }

          next();
      });
    };


    const sendPushToAllCustomer = function(server, databaseObj, packageObj, eventType, title, city, instanceId, push, offerObj){
        var skip = 0;
        var limit = 10;
        const findCustomerList = function (skip) {
            //find customer..
            findCustomer(server, databaseObj, packageObj, city, skip, limit, function (err, list, cursor) {
                if (err) {
                    reject(error);
                } else {
                    if (cursor === null) {
                        //Stop all data
                        //send push
                        sendNotificationToCustomerList(list, instanceId, eventType, title, offerObj);
                    } else {
                        sendNotificationToCustomerList(list, instanceId, eventType, title, offerObj);
                        //Recursive call..
                        findCustomerList(cursor);
                    }//else
                }//else
            }); //findCustomer
        }; //findCustomerList
        findCustomerList(skip);
    };


    const sendNotificationToCustomerList = function (customerList, offerId, eventType, title, offerObj) {
        //send push..
        //and recursive call..
        //send push
        var promises = [];
        const CustomerOffer = databaseObj.CustomerOffer;
        customerList.forEach(function (customer) {
            promises.push(function(callback){
                CustomerOffer.create({
                    offerId : offerObj.id,
                    customerId : customer.id,
                    status: "active",
                    expiredOn: offerObj.expiredOn
                })
                    .then(function(customerOffer){
                        if(customerOffer){
                            var name = customer.firstName;
                            var lastName = customer.lastName ? customer.lastName : "";
                            name = name + " " + lastName;
                            var message = getOfferMessageObject(name, eventType, title, offerId);
                            if (customer.id) {
                                //app, message, id, from, callback
                                //Now send push..
                                sendNotification(server, message, customer.id, packageObj.companyName, function (err) {
                                    if (err) {
                                        console.error(error);
                                    } else {
                                        console.log("Offer has been send Successfully");
                                    }
                                });
                            }
                            callback(null);
                        }
                    })
                    .catch(function(error){
                        callback(error);
                    })
            })

        });

        async.series(promises, function(error){
            if(error){
                console.log(error);
            } else{
                console.log("Customer Offer created successfully");
            }
        })

    };



    const findCustomer = function(server, databaseObj, packageObj, city, skip, limit, callback){
      const Customer = databaseObj.Customer;
      var cityId = city.id;
      var filter = {
          skip: skip,
          limit : limit,
          where : {
              cityId: cityId
          }
      };

      Customer.find(filter, function(error, instanceList){
          if(error){
              callback(error);
          } else{
              if(instanceList){
                  if(instanceList.length < limit){
                      //All data are fetched
                      callback(null, instanceList, null);
                  } else{
                      callback(null, instanceList, skip + limit);
                  }
              } else{
                  callback(error);
              }
          }

      });
    };

    const sendQuoteReplyNotification = function(){
        const QuoteReply = databaseObj.QuoteReply;
        let customerObj;
        QuoteReply.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const quoteReplyObj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.CustomerQuote.findById(quoteReplyObj.customerQuoteId)
                        .then(function(customerQuoteObj){
                            if(customerQuoteObj){
                                quoteReplyObj.customerQuote = customerQuoteObj;
                                return databaseObj.Customer.findById(customerQuoteObj.customerId);
                            }
                        })
                        .then(function(customer){
                            if(customer){
                                customerObj = customer;
                            }
                        })
                        .then(function(){
                            const to = customerObj.firstName + " " + customerObj.lastName;
                            const type = "QuoteReply";
                            const title = "Hi, Kindly check the new quote reply received from dealer";
                            var pushFrom = packageObj.companyName;
                            const instanceId = quoteReplyObj.customerQuoteId;
                            const message = quoteReplyMessageFormat(to, type, title, instanceId);
                            if(customerObj.id){
                                sendNotification(server, message, customerObj.id, pushFrom, function(error){
                                    if(error){
                                        console.log(error);
                                    } else{
                                        console.log("Push Notification for Quote Reply has been send Successfully!");
                                    }
                                })
                            }
                        })
                        .catch(function(error){
                            console.log(error);
                        })
                })
            }
            next();
        })
    };

    const sendHardBrakingAccelerationNotification = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    console.log("customerId", gpsPacketDataObj.customerId);
                    databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                        .then(function(customer){
                            if(customer){
                                customerName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                customerName = customerName + " " + lastName;
                            }
                        })
                        .then(function(){
                            var pushFrom = packageObj.companyName;
                            const instanceId = gpsPacketDataObj.id;
                            if(gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
                                eventType = "Harsh Brake";
                                title = "Harsh Brake has been applied";
                            } else if(gpsPacketDataObj.eventType === packageObj.gps.harsh_acceleration){
                                eventType = "Harsh Acceleration";
                                title = "Harsh Acceleration has been applied";
                            }
                            const message = brakeAccelerationMessageFormat(customerName, eventType, title, instanceId);
                            if(gpsPacketDataObj.customerId){
                                sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                    if(error){
                                        console.log(error);
                                    } else{
                                        console.log("Notification for gps has been send successfully");
                                    }
                                })
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                })
            }
            next();
        })
    };

    const sendGpsBatteryLowNotification = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                        .then(function(customer){
                            if(customer){
                                customerName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                customerName = customerName + " " + lastName;
                            }
                        })
                        .then(function(){
                           pushFrom = packageObj.companyName;
                           instanceId = gpsPacketDataObj.id;
                           if(gpsPacketDataObj.eventType === packageObj.gps.internal_battery_low){
                                return databaseObj.GpsPacketData.find({
                                    limit : 2,
                                    order: ["added DESC"]
                                })
                            }
                        })
                        .then(function(gpsPacketData){
                            if(gpsPacketData){
                                if(gpsPacketData[1].internalBatteryLowAlert === false){
                                    //send push notification
                                    eventType = "Low Internal Battery"
                                    var message = lowBatteryGpsMessage(customerName, eventType, title, instanceId);
                                    if(gpsPacketDataObj.customerId){
                                        sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                            if(error){
                                                console.error(error);
                                            } else{
                                                console.log("Notification for Low Battery send successfully");
                                            }
                                        })
                                    }

                                }
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                });
                next();
            }
        })
    };

    const sendEngineStatusNotification = function(){
       const GpsPacketData = databaseObj.GpsPacketData;
       GpsPacketData.observe("after save", function(ctx, next){
           const instance = ctx.instance;
           const gpsPacketDataObj = instance.toJSON();
           let customerName;
           let eventType;
           let title;
           let pushFrom;
           let instanceId;
           if(ctx.isNewInstance){
               process.nextTick(function(){
                   databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                       .then(function(customer){
                           if(customer){
                               customerName = customer.firstName;
                               var lastName = customer.lastName? customer.lastName : "";
                               customerName = customerName + " " + lastName;
                           }
                       })
                       .then(function(){
                           pushFrom = packageObj.companyName;
                           instanceId = gpsPacketDataObj.id;
                           if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                               eventType = "Ignition On";
                               title = "Engine has started";
                           } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_off){
                               eventType = "Ignition Off";
                               title = "Engine has stopped";
                           }
                           var message = engineStatusMessageFormat(customerName, eventType, title, instanceId);
                           if(gpsPacketDataObj.customerId){
                               sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                   if(error){
                                       console.log(error);
                                   } else{
                                       console.log("Notification for engine status send successfully");
                                   }
                               })
                           }
                       })
               })
           }
           next();
       })
    };

    const sendGpsDeviceStatusNotification = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.Customer.findById(gpsPacketDataObj.customer)
                        .then(function(customer){
                            if(customer){
                                customerName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                customerName = customerName + " " + lastName;
                            }
                        })
                        .then(function(){
                            pushFrom = packageObj.companyName;
                            instanceId = gpsPacketDataObj.id;
                            if(gpsPacketDataObj.batteryStatus !== "vehicle"){
                                return GpsPacketData.find({
                                    limit : 2,
                                    order: ["added DESC"]
                                })
                            }
                        })
                        .then(function(gpsPacketData){
                            if(gpsPacketData){
                                if(gpsPacketData[1].batteryStatus === "vehicle"){
                                    //send Notification
                                    eventType = "GPS Status";
                                    title = "Device has been disconnected";
                                    var message = gpsDeviceStatusMessage(customerName, eventType, title, gpsPacketDataObj.id);
                                    if(gpsPacketDataObj.customerId){
                                        sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                            if(error){
                                                console.log(error);
                                            } else{
                                                console.log("Notification for engine status send successfully");
                                            }
                                        })
                                    }
                                }
                            }
                        })
                        .catch(function(error){
                            console.log(error);
                        })
                })
            }
            next();
        })
    };

    const sendOverSpeedingNotification = function(){
      const GpsPacketData = databaseObj.GpsPacketData;
      GpsPacketData.observe("after save", function(ctx, next){
          const instance = ctx.instance;
          const gpsPacketDataObj = instance.toJSON();
          let customerName;
          let eventType;
          let title;
          let pushFrom;
          let instanceId;
          if(ctx.isNewInstance){
              process.nextTick(function(){
                  databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                      .then(function(customer){
                          if(customer){
                              customerName = customer.firstName;
                              var lastName = customer.lastName? customer.lastName : "";
                              customerName = customerName + " " + lastName;
                          }
                      })
                      .then(function(){
                          pushFrom = packageObj.companyName;
                          instanceId = gpsPacketDataObj.id;
                          if(gpsPacketDataObj.eventType === packageObj.gps.over_speed_started){
                              eventType = "Over Speed";
                              title = "Over Speed Started";
                              return GpsPacketData.find({
                                  limit : 2,
                                  order: ["added DESC"]
                              })
                          }
                      })
                      .then(function(gpsPacketData){
                          if(gpsPacketData){
                              if(gpsPacketData[1].isOverSpeedStarted === false){
                                  //send Notification
                                  var message = overSpeedMessageFormat(customerName, eventType, title, gpsPacketDataObj.id);
                                  if(gpsPacketDataObj.customerId){
                                      sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                          if(error){
                                              console.log(error);
                                          } else{
                                              console.log("Notification for Over Speed send Successfully");
                                          }
                                      })
                                  }
                              }
                          }
                      })
              })
          }
          next();
      })
    };

    const sendVehicleTowingNotification = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.Customer.findById(gpsPacketDataObj.customerId)
                        .then(function(customer){
                            if(customer){
                                customerName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                customerName = customerName + " " + lastName;
                            }
                        })
                        .then(function(){
                            pushFrom = packageObj.companyName;
                            instanceId = gpsPacketDataObj.id;
                            if(gpsPacketDataObj.ignitionStatus === "off" && gpsPacketDataObj.speed > 0){
                                return GpsPacketData.find({
                                    limit:2,
                                    order: ["added DESC"]
                                })
                            }
                        })
                        .then(function(gpsPacketDataList){
                            if(gpsPacketDataList){
                                if(gpsPacketDataList.length){
                                    if(gpsPacketData[1].speed === 0){
                                        //send Notification
                                        title = "Your Vehicle is suspected to be towed";
                                        eventType = "Vehicle Towed";
                                        var message = vehicleTowingMessageFormat(customerName, eventType, title, gpsPacketDataObj.id);
                                        if(gpsPacketDataObj.customerId){
                                            sendNotification(server, message, gpsPacketDataObj.customerId, pushFrom, function(error){
                                                if(error){
                                                    console.log(error);
                                                } else{
                                                    console.log("Car Towing Notification send Successfully");
                                                }
                                            })
                                        }
                                    }
                                }

                            }
                        })
                })
            }
            next();
        });
    };

    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };


    var quoteNotificationFormat = function(To, type, title, customerQuoteId){
        var message = {
            to : To,
            type : type,
            title : title,
            id : customerQuoteId
        };

        return JSON.stringify(message);
    };


    var getOfferMessageObject = function(to, eventType, title, offerId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : offerId
        };

        return JSON.stringify(message);
    };

    var welcomeMessageFormat = function(to, eventType, title, customerId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : customerId
        };

        return JSON.stringify(message);
    };

    var addVehicleNotificationFormat = function(to, eventType, title, vehicleDetailId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : vehicleDetailId
        };

        return JSON.stringify(message);
    };

    var serviceBookingNotificationFormat = function(to, eventType, title, serviceBookingId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : serviceBookingId
        };

        return JSON.stringify(message);
    };

    var completePurchaseNotification = function(to, eventType, title, customerQuoteId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : customerQuoteId
        };

        return JSON.stringify(message);
    };

    var sosNotification = function(to, eventType, title, sosId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : sosId
        };

        return JSON.stringify(message);
    };

    var quoteReplyMessageFormat = function(to, eventType, title, customerQuoteId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : customerQuoteId
        }
        return JSON.stringify(message);
    };

  var brakeAccelerationMessageFormat = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

  var lowBatteryGpsMessage = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

  var engineStatusMessageFormat = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

  var gpsDeviceStatusMessage = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

  var overSpeedMessageFormat = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

  var vehicleTowingMessageFormat = function(to, eventType, title, gpsPacketDataId){
      var message = {
          to : to,
          type : eventType,
          title : title,
          id : gpsPacketDataId
      }
      return JSON.stringify(message);
  };

    return{
        init : init
    };
};
