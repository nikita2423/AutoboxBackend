/**
 * Created by nikita on 29/12/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const push = helper.loadPlugin("pushService");
    const async = require("async");
    const process = require("process");
    const moment = require("moment");

    var init = function(){
        createNightLockMethod();
        fetchNightLockDataMethod();
        sendNightLockNotification();
    };

    /**
     * Create Night Lock Method
     */
    const createNightLockMethod = function(){
        const NightLock = databaseObj.NightLock;
        NightLock.createNightLock = createNightLock;
        NightLock.remoteMethod("createNightLock", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "nightLockObj", type: "object"
                },
                {
                    arg: "type", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };


    const createNightLock = function(ctx, nightLockObj, type, callback){
        const request = ctx.req;
        if(!nightLockObj || !type){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const NightLock = databaseObj.NightLock;
                    if(type === "new"){
                        NightLock.create({
                            timings : nightLockObj.timings,
                            days: nightLockObj.days,
                            deviceIMEI : nightLockObj.deviceIMEI,
                            modelName : nightLockObj.modelName,
                            customerName : nightLockObj.customerName,
                            vehicleDetailId : nightLockObj.vehicleDetailId,
                            customerId : customerId,
                            status : "active"
                        })
                            .then(function(nightLock){
                                if(nightLock){
                                    callback(null, {response:"success"});
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else if(type === "update"){
                        NightLock.upsert({
                            timings: nightLockObj.timings,
                            days: nightLockObj.days,
                            deviceIMEI : nightLockObj.deviceIMEI,
                            modelName : nightLockObj.modelName,
                            customerName : nightLockObj.customerName,
                            vehicleDetailId : nightLockObj.vehicleDetailId,
                            customerId : customerId,
                            id : nightLockObj.id,
                            status: "active",
                            added : nightLockObj.added,
                            updated: nightLockObj.updated
                        })
                            .then(function(nightLock){
                                if(nightLock){
                                    callback(null, {response:"success"});
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else if(type === "delete"){
                        NightLock.findById(nightLockObj.id)
                            .then(function(nightLock){
                                if(nightLock){
                                    return nightLock.updateAttribute("status", "inactive");
                                }
                            })
                            .then(function(nightLock){
                                if(nightLock){
                                    callback(null, {response:"success"});
                                } else{
                                    callback(null, {});
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    }
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const fetchNightLockDataMethod = function(){
        const NightLock = databaseObj.NightLock;
        NightLock.fetchNightLockData = fetchNightLockData;
        NightLock.remoteMethod("fetchNightLockData", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "deviceIMEI", type: "string"
                }
            ],
            returns: {
                arg: "nightLockObject", type: "NightLock", root : true
            }
        });
    };

    const fetchNightLockData = function(ctx, deviceIMEI, callback){
        const request = ctx.req;
        if(!deviceIMEI){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        const NightLock = databaseObj.NightLock;
                        NightLock.findOne({
                            where: {
                                deviceIMEI : deviceIMEI,
                                customerId : customerId
                            }
                        })
                            .then(function(nightLock){
                                if(nightLock){

                                }
                                callback(null, nightLock);
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
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const sendNightLockNotification = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        console.log("Night Lock after save");
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
            let nightLockInstance;
            let modelName;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.NightLock.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI
                        }
                    })
                        .then(function(nightLockList){

                            if(nightLockList){
                                if(nightLockList.length){
                                    nightLockList.forEach(function(nightLock){
                                        if(nightLock){
                                            if(nightLock.customerId){
                                                nightLockInstance = nightLock;
                                                customerIdList.push(nightLock.customerId);
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
                                                            console.log("Customer ", customer);
                                                            customerInstance = customer;
                                                            return databaseObj.GpsTrackerInfo.findOne({
                                                                where: {
                                                                    customerId : customer.id,
                                                                    status : "active"
                                                                }
                                                            });


                                                        }
                                                    })
                                                    .then(function(gpsTrackerInfo){
                                                        if(gpsTrackerInfo){
                                                            console.log("GPSTrackerInfo", gpsTrackerInfo);
                                                            customerName = customerInstance.firstName;
                                                            var lastName = customerInstance.lastName? customerInstance.lastName : "";
                                                            customerName = customerName + " " + lastName;
                                                            pushFrom = packageObj.companyName;
                                                            instanceId = gpsPacketDataObj.id;

                                                            if(gpsPacketDataObj.eventType === packageObj.gps.ignition_on){
                                                                if(nightLockInstance){
                                                                    const dayList = [];
                                                                    nightLockInstance.days.forEach(function(day){
                                                                        if(day){
                                                                            dayList.push(day);
                                                                        }
                                                                    });
                                                                    console.log("Day List", dayList);
                                                                    if(nightLockInstance.timings["startTime"]<= moment().hour() && nightLockInstance.timings["endTime"] >= moment().hour()){
                                                                        //Throw stolen notification
                                                                        console.log("Inside Night Lock", gpsTrackerInfo.gpsTrackerNotification["nightLock"]);
                                                                        eventType = "CarStolen";
                                                                        title = "Car is suspected to be stolen";
                                                                        modelName = gpsTrackerInfo.modelName;
                                                                        var message = nightLockMessageFormat(customerName, eventType, title, modelName, instanceId, gpsTrackerInfo.deviceIMEI);
                                                                        if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["nightLock"] === "on"){
                                                                            sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                                if(error){
                                                                                    console.log(error);
                                                                                    callback(error);
                                                                                } else{
                                                                                    console.log("Notification for stolen send successfully");
                                                                                    return databaseObj.GpsNotification.create({
                                                                                        message: title,
                                                                                        deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                        status: "active",
                                                                                        customerId: customerInstance.id,
                                                                                        modelName: gpsTrackerInfo.modelName
                                                                                    });
                                                                                }
                                                                            });
                                                                        }
                                                                    } else{
                                                                        //send engine status notification..
                                                                        eventType = "Ignition On";
                                                                        title = "Engine has started";
                                                                        modelName = gpsTrackerInfo.modelName;
                                                                        const message = engineStatusMessageFormat(customerName, eventType, title, modelName, gpsPacketDataObj.id, gpsPacketDataObj.deviceIMEI);
                                                                        if(customerInstance.id && gpsTrackerInfo.gpsTrackerNotification["engineOn"] === "on"){
                                                                            sendNotification(server, message, customerInstance.id, pushFrom, function(error){
                                                                                if(error){
                                                                                    console.log(error);
                                                                                    callback(error);
                                                                                } else{
                                                                                    //console.log("Notification for engine status send successfully");
                                                                                    return databaseObj.GpsNotification.create({
                                                                                        message: title,
                                                                                        deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                        status: "active",
                                                                                        customerId: customerInstance.id,
                                                                                        modelName: gpsTrackerInfo.modelName
                                                                                    });
                                                                                }
                                                                            });
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    })
                                                    .then(function(gpsNotification){
                                                        console.log("Notification for night lock send successfully");
                                                        callback(null);
                                                    })
                                                    .catch(function(error){
                                                        callback(error);
                                                        console.log(error);
                                                    });
                                            })
                                        }
                                    });

                                    async.series(promises, function(error){
                                        if(error){
                                            server.logger.error(error);
                                        } else{
                                          //  server.logger.info("Notification for stolen car send successfully to all customers");
                                        }
                                    });
                                }
                            }
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        });
                });
            }
            next();
        });
    };


    var nightLockMessageFormat = function(to, eventType, title, modelName, gpsPacketDataId, deviceIMEI){
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


    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };



    return {
        init: init
    };
}