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
                            status: "active"
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
                                                            customerInstance = customer;
                                                            customerName = customer.firstName;
                                                            var lastName = customer.lastName? customer.lastName : "";
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
                                                                    if(nightLockInstance.timings["startTime"]<= moment().hour() && nightLockInstance.timings["endTime"] >= moment().hour() &&
                                                                        dayList.indexOf(moment().format("E")) === 0){
                                                                        //Throw stolen notification
                                                                        eventType = "Car Stolen";
                                                                        title = "Car is suspected to be stolen";
                                                                        var message = nightLockMessageFormat(customerName, eventType, title, instanceId);
                                                                        if(customer.id){
                                                                            sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                                if(error){
                                                                                    console.log(error);
                                                                                    callback(error);
                                                                                } else{
                                                                                    console.log("Notification for stolen send successfully");
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


    var nightLockMessageFormat = function(to, eventType, title, gpsPacketDataId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : gpsPacketDataId
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