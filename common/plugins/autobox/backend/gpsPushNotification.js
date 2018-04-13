/**
 * Created by nikita on 21/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const push = helper.loadPlugin("pushService");
    const async = require("async");
    const process = require("process");
    const Promise = require("bluebird");

    var init = function(){
        //gpsTestNotification();
        setGpsNotificationStatusMethod();
        sendGpsNotification();
        /*sendHardBrakingAccelerationNotification_();
         sendGpsBatteryLowNotification_();
         sendEngineStatusNotification_();
         sendGpsDeviceStatusNotification_();
         sendOverSpeedingNotification_();
         sendVehicleTowingNotification_();
         sendAccidentDetectionNotification_();*/
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


    const sendGpsNotification = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let customerInstance;
            let eventType;
            let title;
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
            var promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        customerInstance = customer;
                                                        customerName = customer.firstName;
                                                        var lastName = customer.lastName? customer.lastName : "";
                                                        customerName = customerName + " " + lastName;
                                                        var pushFrom = packageObj.companyName;
                                                        if(gpsPacketDataObj.id){
                                                            const instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType){
                                                                if(gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
                                                                    if(gpsPacketDataObj.ignitionStatus){
                                                                        if(gpsPacketDataObj.ignitionStatus === "on"){
                                                                            //Harsh Braking
                                                                            eventType = "Harsh Brake";
                                                                            title = "Harsh Brake has been applied";
                                                                            modelName = gpsTrackerInfo.modelName;
                                                                            const message = brakeAccelerationMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                            if(customer.id && gpsTrackerInfo.gpsTrackerNotification["hardBraking"] === "on"){
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
                                                                        } else{
                                                                            //Accident Detection
                                                                            return sendAccidentDetectionNotification(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName);
                                                                        }
                                                                    }
                                                                } else if(gpsPacketDataObj.eventType === packageObj.gps.harsh_acceleration){
                                                                    eventType = "Harsh Acceleration";
                                                                    title = "Harsh Acceleration has been applied";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    const message = brakeAccelerationMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                    if(customer.id && gpsTrackerInfo.gpsTrackerNotification["hardAcceleration"] === "on"){
                                                                        sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                            if(error){
                                                                                console.log(error);
                                                                                callback(error);
                                                                            } else{
                                                                                //console.log("Notification for gps has been send successfully");
                                                                                return databaseObj.GpsNotification.create({
                                                                                    message: title,
                                                                                    deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                    status: "active",
                                                                                    customerId: customer.id,
                                                                                    modelName: gpsTrackerInfo.modelName
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                } else if(gpsPacketDataObj.eventType === packageObj.gps.internal_battery_low){
                                                                    //Low Battery Alert
                                                                    return sendGpsBatteryLowNotification(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName);

                                                                } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                                                                    eventType = "Ignition On";
                                                                    title = "Engine has started";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    const message = engineStatusMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                    if(customer.id && customerInstance.gpsTrackerNotification["engineOn"] === "on"){
                                                                        sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                            if(error){
                                                                                console.log(error);
                                                                                callback(error);
                                                                            } else{
                                                                                //console.log("Notification for engine status send successfully");
                                                                                return databaseObj.GpsNotification.create({
                                                                                    message: title,
                                                                                    deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                    status: "active",
                                                                                    customerId: customer.id,
                                                                                    modelName: gpsTrackerInfo.modelName
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_off){
                                                                    eventType = "Ignition Off";
                                                                    title = "Engine has stopped";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    const message = engineStatusMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
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
                                                                                    customerId: customer.id,
                                                                                    modelName: gpsTrackerInfo.modelName
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                } else if(gpsPacketDataObj.eventType === packageObj.gps.over_speed_started && gpsPacketDataObj.ignitionStatus === "on"){
                                                                    //Over Speed Notification..
                                                                    eventType = "Over Speed";
                                                                    title = "Over Speed Started";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    return sendOverSpeedNotification(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName);

                                                                } else if(gpsPacketDataObj.batteryStatus !== "vehicle"){
                                                                    //Device disconnected Notification..
                                                                    return sendGpsDeviceStatusNotification(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName);

                                                                } else if(gpsPacketDataObj.ignitionStatus === "off" && gpsPacketDataObj.speed > 15){
                                                                    //Vehicle Towing Notification..
                                                                    return sendVehicleTowingNotification(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName);

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
                                            });
                                        }
                                    });
                                }
                            }

                            async.series(promises, function (error) {
                                if(error){
                                    server.logger.error(error);
                                } else{
                                    //server.logger.info("Hard Acceleration");
                                }
                            });
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
                });
            }

            next();
        });
    };

    const sendAccidentDetectionNotification = function(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName){
        return new Promise(function(resolve, reject){
            const GpsPacketData = databaseObj.GpsPacketData;
            GpsPacketData.find({
                limit:2,
                order: ["added DESC"]
            })
                .then(function(gpsPacketDataList){
                    if(gpsPacketDataList){
                        if(gpsPacketDataList.length){
                            if(gpsPacketDataList[1].eventType !== packageObj.gps.harsh_braking){
                                //send Notification
                                title = "We suspect of accident occured of your vehicle";
                                eventType = "Accident Detection";
                                modelName = gpsTrackerInfo.modelName;
                                var message = accidentDetectionMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                if(customer.id && gpsTrackerInfo.gpsTrackerNotification["accidentDetection"] === "on"){
                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                        if(error){
                                            server.logger.error(error);
                                            reject(error);
                                        } else{
                                            // server.logger.info("Accident Detection Notification send Successfully");
                                            return databaseObj.GpsNotification.create({
                                                message: title,
                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                status: "active",
                                                customerId: customer.id,
                                                modelName: gpsTrackerInfo.modelName
                                            });
                                        }
                                    });
                                }
                            }
                        }
                    }
                })
                .then(function(gpsNotification){
                    resolve();
                })
                .catch(function(error){
                    reject(error);
                });

        });
    };

    const sendGpsBatteryLowNotification = function(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName){
        return new Promise(function(resolve, reject){
            const GpsPacketData = databaseObj.GpsPacketData;
            GpsPacketData.find({
                limit : 2,
                order: ["added DESC"]
            })
                .then(function(gpsPacketData){
                    if(gpsPacketData){
                        if(gpsPacketData[1].internalBatteryLowAlert === false){
                            //send push notification
                            eventType = "Low Internal Battery";
                            title = "Less than 35% Battery left";
                            modelName = gpsTrackerInfo.modelName;
                            var message = lowBatteryGpsMessage(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                            if(customer.id){
                                sendNotification(server, message, customer.id, pushFrom, function(error){
                                    if(error){
                                        console.error(error);
                                        reject(error);
                                    } else{
                                        // console.log("Notification for Low Battery send successfully");
                                        return databaseObj.GpsNotification.create({
                                            message: title,
                                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                            status: "active",
                                            customerId: customer.id,
                                            modelName: gpsTrackerInfo.modelName
                                        });
                                    }
                                });
                            }

                        }
                    }
                })
                .then(function(gpsPacketData){
                    resolve();
                })
                .catch(function(error){
                    reject(error);
                });
        });
    };


    const sendOverSpeedNotification = function(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName){
        return new Promise(function(resolve, reject){
            const GpsPacketData = databaseObj.GpsPacketData;
            GpsPacketData.find({
                limit : 2,
                order: ["added DESC"]
            })
                .then(function(gpsPacketData){
                    if(gpsPacketData){
                        if(gpsPacketData[1].isOverSpeedStarted === false){
                            //send Notification
                            var message = overSpeedMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                            if(customer.id && gpsTrackerInfo.gpsTrackerNotification["overSpeeding"] === "on"){
                                sendNotification(server, message, customer.id, pushFrom, function(error){
                                    if(error){
                                        console.log(error);
                                        reject(error);
                                    } else{
                                        // console.log("Notification for Over Speed send Successfully");
                                        return databaseObj.GpsNotification.create({
                                            message: title,
                                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                            status: "active",
                                            customerId: customer.id,
                                            modelName: gpsTrackerInfo.modelName
                                        });
                                    }
                                });
                            }
                        }
                    }
                })
                .then(function(gpsNotification){
                    resolve();
                })
                .catch(function(error){
                    reject(error);
                });

        });
    };

    const sendGpsDeviceStatusNotification = function(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName){
        return new Promise(function(resolve, reject){
            const GpsPacketData = databaseObj.GpsPacketData;
            GpsPacketData.find({
                limit : 2,
                order: ["added DESC"]
            })
                .then(function(gpsPacketData){
                    if(gpsPacketData){
                        if(gpsPacketData[1].batteryStatus === "vehicle"){
                            //send Notification
                            eventType = "GPS Status";
                            title = "Device has been disconnected";
                            modelName = gpsTrackerInfo.modelName;
                            var message = gpsDeviceStatusMessage(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                            if(customer.id && gpsTrackerInfo.gpsTrackerNotification["gpsDisconnect"] === "on"){
                                sendNotification(server, message, customer.id, pushFrom, function(error){
                                    if(error){
                                        console.log(error);
                                        reject(error);
                                    } else{
                                        //console.log("Notification for gps Device send successfully");
                                        return databaseObj.GpsNotification.create({
                                            message: title,
                                            deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                            status: "active",
                                            customerId: customer.id,
                                            modelName: gpsTrackerInfo.modelName
                                        });
                                    }
                                });
                            }
                        }
                    }
                })
                .then(function(gpsNotification){
                    resolve();
                })
                .catch(function(error){
                    reject(error);
                });

        });
    };

    const sendVehicleTowingNotification = function(eventType, title, modelName, customer, gpsTrackerInfo, pushFrom, gpsPacketDataObj, customerName){
        return new Promise(function(resolve, reject){
            const GpsPacketData = databaseObj.GpsPacketData;
            GpsPacketData.find({
                limit : 2,
                order: ['added DESC']
            })
                .then(function(gpsPacketDataList){
                    if(gpsPacketDataList){
                        if(gpsPacketDataList.length){
                            if(gpsPacketDataList[1].speed === 0){
                                //send Notification
                                title = "We suspect your vehicle is being towed";
                                eventType = "Vehicle Towed";
                                modelName = gpsTrackerInfo.modelName;
                                var message = vehicleTowingMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                if(customer.id && gpsTrackerInfo.gpsTrackerNotification["nightLock"] === "on"){
                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                        if(error){
                                            server.logger.error(error);
                                            reject(error);
                                        } else{
                                            //  server.logger.info("Car Towing Notification send Successfully");
                                            return databaseObj.GpsNotification.create({
                                                message: title,
                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                status: "active",
                                                customerId: customer.id,
                                                modelName: gpsTrackerInfo.modelName
                                            });
                                        }
                                    });
                                }
                            }
                        }
                    }
                })
                .then(function(gpsNotification){
                    resolve();
                })
                .catch(function(error){
                    reject(error);
                });
        });
    };


    const sendHardBrakingAccelerationNotification_ = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let customerInstance;
            let eventType;
            let title;
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /* if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            // console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            var pushFrom = packageObj.companyName;
                                                            const instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
                                                                eventType = "Harsh Brake";
                                                                title = "Harsh Brake has been applied";
                                                                modelName = gpsTrackerInfo.modelName;
                                                                const message = brakeAccelerationMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                if(customer.id && gpsTrackerInfo.gpsTrackerNotification["hardBraking"] === "on"){
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
                                                                modelName = gpsTrackerInfo.modelName;
                                                                const message = brakeAccelerationMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                if(customer.id && gpsTrackerInfo.gpsTrackerNotification["hardAcceleration"] === "on"){
                                                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            //console.log("Notification for gps has been send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customer.id,
                                                                                modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Hard Acceleration");
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

    const sendGpsBatteryLowNotification_ = function(){
        const GpsPacketData = server.models["GpsPacketData"];
        GpsPacketData.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const gpsPacketDataObj = instance.toJSON();
            let customerName;
            let eventType;
            let title;
            let pushFrom;
            let instanceId;
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /*if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            //console.log("customerIdList",customer.id);
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
                                                                modelName = gpsTrackerInfo.modelName;
                                                                var message = lowBatteryGpsMessage(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                if(customerInstance.id){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.error(error);
                                                                            callback(error);
                                                                        } else{
                                                                            // console.log("Notification for Low Battery send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id,
                                                                                modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Notification for Low Battery send successfully to all customers")
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



    const sendEngineStatusNotification_ = function(){
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
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /* if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                                                                eventType = "Ignition On";
                                                                title = "Engine has started";
                                                                modelName = gpsTrackerInfo.modelName;
                                                                var message = engineStatusMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
                                                                if(customer.id && customerInstance.gpsTrackerNotification["engineOn"] === "on"){
                                                                    sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            //console.log("Notification for engine status send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customer.id,
                                                                                modelName: gpsTrackerInfo.modelName
                                                                            });
                                                                        }
                                                                    })
                                                                }
                                                            } else if(gpsPacketDataObj.eventType === packageObj.gps.ignition_off){
                                                                eventType = "Ignition Off";
                                                                title = "Engine has stopped";
                                                                modelName = gpsTrackerInfo.modelName;
                                                                var message = engineStatusMessageFormat(customerName, eventType, title, modelName, instanceId, gpsPacketDataObj.deviceIMEI);
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
                                                                                customerId: customer.id,
                                                                                modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Notification for engine status send successfully to all customers");
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

    const sendGpsDeviceStatusNotification_ = function(){
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
            let gpsTrackerInfoDataList = [];
            let modelName;
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /*if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            //  console.log("customerIdList",customer.id);
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
                                                                modelName = gpsTrackerInfo.modelName;
                                                                var message = gpsDeviceStatusMessage(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                                                if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["gpsDisconnect"] === "on"){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            //console.log("Notification for gps Device send successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id,
                                                                                modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Notification for gps Device send successfully to all customers");
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

    const sendOverSpeedingNotification_ = function(){
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
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /* if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            // console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.eventType === packageObj.gps.over_speed_started){
                                                                eventType = "Over Speed";
                                                                title = "Over Speed Started";
                                                                modelName = gpsTrackerInfo.modelName;
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
                                                                var message = overSpeedMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                                                if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["overSpeeding"] === "on"){
                                                                    sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                        if(error){
                                                                            console.log(error);
                                                                            callback(error);
                                                                        } else{
                                                                            // console.log("Notification for Over Speed send Successfully");
                                                                            return databaseObj.GpsNotification.create({
                                                                                message: title,
                                                                                deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                status: "active",
                                                                                customerId: customerInstance.id,
                                                                                modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Notification for Over Speed send Successfully to all Customers");
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

    const sendVehicleTowingNotification_ = function(){
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
            let modelName;
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /* if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            //console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.ignitionStatus === "off" && gpsPacketDataObj.speed > 15){
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
                                                                if(gpsPacketDataList[1].speed === 0){
                                                                    //send Notification
                                                                    title = "We suspect your vehicle is being towed";
                                                                    eventType = "Vehicle Towed";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    var message = vehicleTowingMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                                                    if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["nightLock"] === "on"){
                                                                        sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                            if(error){
                                                                                server.logger.error(error);
                                                                                callback(error);
                                                                            } else{
                                                                                //  server.logger.info("Car Towing Notification send Successfully");
                                                                                return databaseObj.GpsNotification.create({
                                                                                    message: title,
                                                                                    deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                    status: "active",
                                                                                    customerId: customerInstance.id,
                                                                                    modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Car Towing Notification send Successfully to all customers");
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


    const sendAccidentDetectionNotification_ = function(){
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
            let modelName;
            let customerIdList = [];
            let gpsTrackerInfoDataList = [];
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
                                            gpsTrackerInfoDataList.push(gpsTrackerInfo);
                                            /* if(gpsTrackerInfo.customerId){
                                             customerIdList.push(gpsTrackerInfo.customerId);
                                             }*/
                                        }
                                    });
                                }
                            }
                        })
                        .then(function(){
                            if(gpsTrackerInfoDataList){
                                if(gpsTrackerInfoDataList.length){
                                    gpsTrackerInfoDataList.forEach(function(gpsTrackerInfo){
                                        if(gpsTrackerInfo){
                                            const Customer = databaseObj.Customer;
                                            promises.push(function(callback){
                                                Customer.findById(gpsTrackerInfo.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                            //console.log("customerIdList",customer.id);
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;
                                                            if(gpsPacketDataObj.ignitionStatus === "off" && gpsPacketDataObj.eventType === packageObj.gps.harsh_braking){
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
                                                                if(gpsPacketDataList[1].eventType !== packageObj.gps.harsh_braking){
                                                                    //send Notification
                                                                    title = "We suspect of accident occured of your vehicle";
                                                                    eventType = "Accident Detection";
                                                                    modelName = gpsTrackerInfo.modelName;
                                                                    var message = accidentDetectionMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                                                    if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["accidentDetection"] === "on"){
                                                                        sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                            if(error){
                                                                                server.logger.error(error);
                                                                                callback(error);
                                                                            } else{
                                                                                // server.logger.info("Accident Detection Notification send Successfully");
                                                                                return databaseObj.GpsNotification.create({
                                                                                    message: title,
                                                                                    deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                    status: "active",
                                                                                    customerId: customerInstance.id,
                                                                                    modelName: gpsTrackerInfo.modelName
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
                                            //server.logger.info("Accident Detection Notification send Successfully to all customers");
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




    var brakeAccelerationMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI

        };
        return JSON.stringify(message);
    };

    var lowBatteryGpsMessage = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        };
        return JSON.stringify(message);
    };

    var engineStatusMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modeName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        };
        return JSON.stringify(message);
    };

    var gpsDeviceStatusMessage = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        };
        return JSON.stringify(message);
    };

    var overSpeedMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        }
        return JSON.stringify(message);
    };

    var vehicleTowingMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        };
        return JSON.stringify(message);
    };

    var accidentDetectionMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
        var message = {
            to : to,
            type : eventType,
            title : title,
            modelName : modelName,
            id : gpsPacketDataId,
            deviceIMEI : deviceIMEI
        };
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