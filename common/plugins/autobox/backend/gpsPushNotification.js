/**
 * Created by nikita on 21/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const push = helper.loadPlugin("pushService");
    const async = require("async");
    const process = require("process");

    var init = function(){
        gpsTestNotification();
        setGpsNotificationStatusMethod();
        sendHardBrakingAccelerationNotification();
        sendGpsBatteryLowNotification();
        sendEngineStatusNotification();
        sendGpsDeviceStatusNotification();
        sendOverSpeedingNotification();
        sendVehicleTowingNotification();
    };


    const setGpsNotificationStatusMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.setGpsNotificationStatus = setGpsNotificationStatus;
        Customer.remoteMethod('setGpsNotificationStatus', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "customerNotificationObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }

        });
    };


    const gpsTestNotification = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            let customerIdList = [];
            var promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    //console.log("customerId", gpsPacketDataObj.customerId);
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                           promises.push(function(callback){
                                               Customer.findById(customerId)
                                                   .then(function(customer){
                                                       if(customer) {
                                                           console.log("customerIdList", customer.id);
                                                           customerName = customer.firstName;
                                                           var lastName = customer.lastName ? customer.lastName : "";
                                                           customerName = customerName + " " + lastName;
                                                           var pushFrom = packageObj.companyName;
                                                           const instanceId = gpsPacketDataObj.id;
                                                           eventType = "Default Packet";
                                                           title = "Default Test Packet Arrived";
                                                           const message = brakeAccelerationMessageFormat(customerName, eventType, title, instanceId);
                                                           if (customer.id) {
                                                               console.log("Notification customer Id", customer.id + " " + customerName);
                                                               sendNotification(server, message, customer.id, pushFrom, function (error) {
                                                                   if (error) {
                                                                       console.log(error);
                                                                       callback(error);
                                                                   } else {
                                                                       server.logger.info("Notification for  default packet gps has been send successfully");
                                                                       return databaseObj.GpsNotification.create({
                                                                           message: title,
                                                                           deviceIMEI: gpsPacketDataObj.deviceIMEI,
                                                                           status: "active",
                                                                           customerId: customer.id
                                                                       });
                                                                   }
                                                               });
                                                           }
                                                       }
                                                   })
                                                   .then(function(gpsNotification){
                                                       callback(null);
                                                       server.logger.info("Default Notification created");
                                                   })
                                                   .catch(function(error){
                                                       server.logger.error("Error in default Notification");
                                                       callback(error);
                                                   });
                                           });
                                        }
                                    });
                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error("Error in sending notification for gps default packet");
                                        } else{
                                             server.logger.info("Notification for gps default packet Send Successfully");
                                        }
                                    });
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error("Error in sending notification", error);
                        });
                });
            }
            next();
        });
    };

  const setGpsNotificationStatus = function(ctx, customerNotificationObj, callback){
      const request = ctx.req;
      if(!customerNotificationObj){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  const Customer = databaseObj.Customer;
                  Customer.findById(customerId)
                      .then(function(customer){
                          if(customer){
                              return customer.updateAttribute("gpsTrackerNotification", customerNotificationObj.gpsTrackerNotification);
                          } else{
                              throw new Error("Customer not found");
                          }
                      })
                      .then(function(customer){
                          if(customer){
                              callback(null, {response: "success"});
                          }
                      })
                      .catch(function(error){
                          callback(error);
                      });
              } else{
                  callback(new Error("User not found"));
              }
          } else{
              callback(new Error("User not found"));
          }
      }
  };


    const sendHardBrakingAccelerationNotification = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let customerInstance;
            let eventType;
            let title;
            let customerIdList = [];
            var promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    //console.log("customerId", gpsPacketDataObj.customerId);

                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                            status :"active"
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            var pushFrom = packageObj.companyName;
                                                            const instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
                                                                eventType = "Harsh Brake";
                                                                title = "Harsh Brake has been applied";
                                                                const message = brakeAccelerationMessageFormat(customerName, eventType, title, instanceId);
                                                                if(customer.id && customerInstance.gpsTrackerNotification["hardBraking"] === "on"){
                                                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                        if(error){
                                                                            //console.log(error);
                                                                            server.logger.error(error);
                                                                            callback(error);
                                                                        } else{
                                                                            server.logger.info("Notification for gps has been send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customer.id
                                                                            });
                                                                        }
                                                                    });
                                                                }
                                                            } else if(gpsPacketDataObj.eventType === packageObj.gps.harsh_acceleration){
                                                                eventType = "Harsh Acceleration";
                                                                title = "Harsh Acceleration has been applied";
                                                                const message = brakeAccelerationMessageFormat(customerName, eventType, title, instanceId);
                                                                if(customer.id && customerInstance.gpsTrackerNotification["hardAcceleration"] === "on"){
                                                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            console.log("Notification for gps has been send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customer.id
                                                                            });
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsNotification){
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                    });

                                            })
                                        }
                                    });

                                    async.series(promises, function (error) {
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                           server.logger.info("Hard Acceleration");
                                        }
                                    });
                                }
                            }
                        })
                        .catch(function(error){
                            console.error(error);
                        });
                });
            }
            next();
        });
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
            let customerIdList = [];
            var promises = [];
            let customerInstance;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                            status : "active"
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType === packageObj.gps.internal_battery_low){
                                                                return databaseObj.GpsPacketData.find({
                                                                    limit : 2,
                                                                    order: ["added DESC"]
                                                                })
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsPacketData){
                                                        if(gpsPacketData){
                                                            if(gpsPacketData[1].internalBatteryLowAlert === false){
                                                                //send push notification
                                                                eventType = "Low Internal Battery";
                                                                title = "Less than 35% Battery left";
                                                                var message = lowBatteryGpsMessage(customerName, eventType, title, instanceId);
                                                                if(customerInstance.id){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.error(error);
                                                                            callback(error);
                                                                        } else{
                                                                            console.log("Notification for Low Battery send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id
                                                                            });
                                                                        }
                                                                    })
                                                                }

                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsPacketData){
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                    })
                                            })
                                        }
                                    });

                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                            server.logger.info("Notification for Low Battery send successfully to all customers")
                                        }
                                    })
                                }
                            }
                        })
                        .catch(function(error){
                            console.log(error);
                        });
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
            let customerInstance;
            let promises = [];
            let customerIdList = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                       if(customerId){
                                           const Customer = databaseObj.Customer;
                                           promises.push(function(callback){
                                               Customer.findById(customerId)
                                                   .then(function(customer){
                                                       if(customer){
                                                           customerName = customer.firstName;
                                                           var lastName = customer.lastName? customer.lastName : "";
                                                           customerName = customerName + " " + lastName;
                                                           pushFrom = packageObj.companyName;
                                                           instanceId = gpsPacketDataObj.id;
                                                           if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                                                               eventType = "Ignition On";
                                                               title = "Engine has started";
                                                               var message = engineStatusMessageFormat(customerName, eventType, title, instanceId);
                                                               if(customer.id && customerInstance.gpsTrackerNotification["engineOn"] === "on"){
                                                                   sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                       if(error){
                                                                           console.log(error);
                                                                           callback(error);
                                                                       } else{
                                                                           console.log("Notification for engine status send successfully");
                                                                           return databaseObj.GpsNotification.create({
                                                                               message: title,
                                                                               deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                               status: "active",
                                                                               customerId: customer.id
                                                                           });
                                                                       }
                                                                   })
                                                               }
                                                           } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_off){
                                                               eventType = "Ignition Off";
                                                               title = "Engine has stopped";
                                                               var message = engineStatusMessageFormat(customerName, eventType, title, instanceId);
                                                               if(customer.id  && customerInstance.gpsTrackerNotification["engineOff"] === "on"){
                                                                   sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                       if(error){
                                                                           console.log(error);
                                                                           callback(error);
                                                                       } else{
                                                                           console.log("Notification for engine status send successfully");
                                                                           return databaseObj.GpsNotification.create({
                                                                               message: title,
                                                                               deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                               status: "active",
                                                                               customerId: customer.id
                                                                           });
                                                                       }
                                                                   })
                                                               }
                                                           }
                                                       }
                                                   })
                                                   .then(function(gpsNotification){
                                                       callback(null);
                                                   })
                                                   .catch(function(error){
                                                       callback(error);
                                                   })
                                           })
                                       }
                                    });

                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                            server.logger.info("Notification for engine status send successfully to all customers");
                                        }
                                    })
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
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
            let customerInstance;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            let customerIdList = [];
            let promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                            status : "active"
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.batteryStatus !== "vehicle"){
                                                                return GpsPacketData.find({
                                                                    limit : 2,
                                                                    order: ["added DESC"]
                                                                })
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsPacketData){
                                                        if(gpsPacketData){
                                                            if(gpsPacketData[1].batteryStatus === "vehicle"){
                                                                //send Notification
                                                                eventType = "GPS Status";
                                                                title = "Device has been disconnected";
                                                                var message = gpsDeviceStatusMessage(customerName, eventType, title, gpsPacketDataObj.id);
                                                                if(customerInstance.id && customerInstance.gpsTrackerNotification["gpsDisconnect"] === "on"){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            console.log("Notification for gps Device send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id
                                                                            });
                                                                        }
                                                                    })
                                                                }
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsNotification){
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                    })
                                            })
                                        }
                                    });
                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                            server.logger.info("Notification for gps Device send successfully to all customers");
                                        }
                                    })
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
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
            let customerInstance;
            let title;
            let pushFrom;
            let instanceId;
            let customerIdList = [];
            let promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                            status : "active"
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
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
                                                        }
                                                    })
                                                    .then(function(gpsPacketData){
                                                        if(gpsPacketData){
                                                            if(gpsPacketData[1].isOverSpeedStarted === false){
                                                                //send Notification
                                                                var message = overSpeedMessageFormat(customerName, eventType, title, gpsPacketDataObj.id);
                                                                if(customerInstance.id && customerInstance.gpsTrackerNotification["overSpeeding"] === "on"){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            console.log("Notification for Over Speed send Successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id
                                                                            });
                                                                        }
                                                                    })
                                                                }
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsNotification){
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                    })
                                            })
                                        }
                                    });
                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                            server.logger.info("Notification for Over Speed send Successfully to all Customers");
                                        }
                                    })
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
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
            let customerInstance;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            let customerIdList = [];
            let promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GpsTrackerInfo.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                            status : "active"
                        }
                    })
                        .then(function(gpsTrackerInfoList){
                            if(gpsTrackerInfoList){
                                if(gpsTrackerInfoList.length){
                                    gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            if(gpsTrackerInfo.customerId){
                                                customerIdList.push(gpsTrackerInfo.customerId);
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(customerIdList){
                                if(customerIdList.length){
                                    customerIdList.forEach(function(customerId){
                                        if(customerId){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.ignitionStatus === "off" && gpsPacketDataObj.speed > 0){
                                                                return GpsPacketData.find({
                                                                    limit:2,
                                                                    order: ["added DESC"]
                                                                })
                                                            }
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
                                                                    if(customerInstance.id && customerInstance.gpsTrackerNotification["vehicleTowing"] === "on"){
                                                                        sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                            if(error){
                                                                               server.logger.error(error);
                                                                                callback(error);
                                                                            } else{
                                                                                server.logger.info("Car Towing Notification send Successfully");
                                                                                return databaseObj.GpsNotification.create({
                                                                                    message: title,
                                                                                    deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                    status: "active",
                                                                                    customerId: customerInstance.id
                                                                                });
                                                                            }
                                                                        })
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsNotification){
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                    })
                                            })
                                        }
                                    });

                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                            server.logger.info("Car Towing Notification send Successfully to all customers");
                                        }
                                    })
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
                })
            }
            next();
        });
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

    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };
    return {
        init: init
    };
}