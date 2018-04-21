/**
 * Created by nikita on 2/1/18.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const push = helper.loadPlugin("pushService");
    const async = require("async");
    const process = require("process");
    const moment = require("moment");
    const Promise = require("bluebird");


    var init = function(){
        createGeoFenceMethod();
        fetchGeoFenceDataMethod();
        sendGeoFenceNotification_();
        //sendGeoFenceNotification();
    };

    const createGeoFenceMethod = function(){
        const GeoFenceVehicle = databaseObj.GeoFenceVehicle;
        GeoFenceVehicle.createGeoFence = createGeoFence;
        GeoFenceVehicle.remoteMethod("createGeoFence", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "geoFenceObj", type: "object"
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



    const createGeoFence = function(ctx, geoFenceObj, type, callback){
        const request = ctx.req;
        const GeoFenceVehicle = databaseObj.GeoFenceVehicle;
        if(!geoFenceObj || !type){
            callback(new Error("Invalid Arguments"));
        }
        if(request){
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    if(type === "new"){
                        GeoFenceVehicle.create({
                            homeLocation : geoFenceObj.homeLocation,
                            deviceIMEI : geoFenceObj.deviceIMEI,
                            kilometers : geoFenceObj.kilometers,
                            modelName : geoFenceObj.modelName,
                            registerNumber : geoFenceObj.registerNumber,
                            customerId : customerId,
                            vehicleDetailId : geoFenceObj.vehicleDetailId,
                            status : "active",
                            homeLocationAddress : geoFenceObj.homeLocationAddress
                        })
                            .then(function(geoFenceVehicle){
                                callback(null, {response: "success"});
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else if(type === "update"){
                        GeoFenceVehicle.upsert({
                            homeLocation : geoFenceObj.homeLocation,
                            deviceIMEI : geoFenceObj.deviceIMEI,
                            kilometers : geoFenceObj.kilometers,
                            modelName : geoFenceObj.modelName,
                            registerNumber : geoFenceObj.registerNumber,
                            customerId : customerId,
                            vehicleDetailId : geoFenceObj.vehicleDetailId,
                            id : geoFenceObj.id,
                            added: geoFenceObj.added,
                            updated: geoFenceObj.updated,
                            status : "active",
                            homeLocationAddress : geoFenceObj.homeLocationAddress
                        })
                            .then(function(geoFenceVehicle){
                                callback(null, {response:"success"});
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else if(type === "delete"){
                        GeoFenceVehicle.findById(geoFenceObj.id)
                            .then(function(geoFenceVehicle){
                                if(geoFenceVehicle){
                                    return geoFenceVehicle.updateAttribute("status", "inactive");
                                }
                            })
                            .then(function(geoFenceVehicle){
                                if(geoFenceVehicle){
                                    callback(null, geoFenceVehicle);
                                } else{
                                    callback(null,{});
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    }
                } else{
                    callback(new Error("Invalid User"));
                }
            } else{
                callback(new Error("Invalid User"));
            }
        } else{
            callback(new Error("Invalid User"));
        }
    };


    const fetchGeoFenceDataMethod = function(){
        const GeoFenceVehicle = databaseObj.GeoFenceVehicle;
        GeoFenceVehicle.fetchGeoFenceData = fetchGeoFenceData;
        GeoFenceVehicle.remoteMethod("fetchGeoFenceData", {
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
                arg: "geoFenceVehicleObj", type: "GeoFenceVehicle", root : true
            }
        });
    };


    const fetchGeoFenceData = function(ctx, deviceIMEI, callback){
        const request = ctx.req;
        const GeoFenceVehicle = databaseObj.GeoFenceVehicle;
        if(!deviceIMEI){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        GeoFenceVehicle.findOne({
                            where: {
                                deviceIMEI : deviceIMEI,
                                customerId : customerId
                            }
                        })
                            .then(function(geoFenceVehicle){
                                if(geoFenceVehicle){

                                }
                                callback(null, geoFenceVehicle);
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

    const sendGeoFenceNotification = function(){
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
            let gpsFenceInstance;
            let promises = [];
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GeoFenceVehicle.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI
                        }
                    })
                        .then(function(gpsFenceVehicleList){
                            if(gpsFenceVehicleList){
                                if(gpsFenceVehicleList.length){
                                    gpsFenceVehicleList.forEach(function(gpsFenceVehicle){
                                        if(gpsFenceVehicle){
                                            if(gpsFenceVehicle.customerId){
                                                gpsFenceInstance = gpsFenceVehicle;
                                                promises.push(
                                                    databaseObj.Customer.findById(gpsFenceVehicle.customerId)
                                                        .then(function(customer){
                                                            if(customer){
                                                                //Check for car is still in geo fence set by user or not...
                                                                if(gpsFenceVehicle.homeLocation){
                                                                    if(getDistance(gpsPacketDataObj.latitude, gpsPacketDataObj.longitude, gpsFenceVehicle.homeLocation["lat"], gpsFenceVehicle.homeLocation["lng"]) > gpsFenceVehicle.kilometers){
                                                                        //throw notification
                                                                        customerInstance = customer;
                                                                        customerName = customer.firstName;
                                                                        var lastName = customer.lastName? customer.lastName : "";
                                                                        customerName = customerName + " " + lastName;
                                                                        pushFrom = packageObj.companyName;
                                                                        instanceId = gpsPacketDataObj.id;
                                                                        eventType = "Geo Fence";
                                                                        title = "Car is suspected to be moving out of geo fencing";
                                                                        var message = geoFenceMessageFormat(customerName, eventType, title, instanceId);
                                                                        if(customer.id){
                                                                            sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                                if(error){
                                                                                    console.log(error);
                                                                                    // callback(error);
                                                                                } else{
                                                                                    console.log("Notification for geo fencing send successfully");
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
                                                        })
                                                        .then(function(){
                                                           // callback(null);
                                                        })
                                                        .catch(function(error){
                                                           // callback(error);
                                                        })
                                                );
                                            }
                                        }
                                    });

                                    return Promise.all(promises);
                                }
                            }
                        })
                        .then(function(){
                            server.logger.info("Geo Fencing Notification send to all customers");
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        })
                });
            }
            next();
        });
    };

    const sendGeoFenceNotification_ = function(){
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
            let gpsFenceInstance;
            let promises = [];
            let gpsTrackerInfoInstance;
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.GeoFenceVehicle.find({
                        where: {
                            deviceIMEI : gpsPacketDataObj.deviceIMEI
                        }
                    })
                        .then(function(gpsFenceVehicleList){
                            if(gpsFenceVehicleList){
                                if(gpsFenceVehicleList.length){
                                    gpsFenceVehicleList.forEach(function(gpsFenceVehicle){
                                        if(gpsFenceVehicle){
                                            if(gpsFenceVehicle.customerId){
                                                gpsFenceInstance = gpsFenceVehicle;
                                                promises.push(
                                                    databaseObj.Customer.findById(gpsFenceVehicle.customerId)
                                                        .then(function(customer){
                                                            if(customer){
                                                                customerInstance = customer;
                                                                return databaseObj.GpsTrackerInfo.findOne({
                                                                    where: {
                                                                        customerId : customer.id,
                                                                        status : "active",
                                                                        deviceIMEI : gpsPacketDataObj.deviceIMEI
                                                                    }
                                                                });
                                                            }
                                                        })
                                                        .then(function(gpsTrackerInfo){
                                                            if(gpsTrackerInfo){
                                                                gpsTrackerInfoInstance = gpsTrackerInfo;
                                                                //Check for car is still in geo fence set by user or not...
                                                                if(gpsFenceVehicle.homeLocation){
                                                                    if(getDistance(gpsPacketDataObj.latitude, gpsPacketDataObj.longitude, gpsFenceVehicle.homeLocation["lat"], gpsFenceVehicle.homeLocation["lng"]) > gpsFenceVehicle.kilometers){
                                                                        if(gpsFenceVehicle.isNotification){
                                                                            if(gpsPacketDataObj.ignitionStatus === "on" && gpsPacketDataObj.speed > 0){
                                                                                return GpsPacketData.find({
                                                                                    order:['added DESC'],
                                                                                    limit : 2,
                                                                                    where: {
                                                                                        deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                                        ignitionStatus : 'on',
                                                                                        speed:{
                                                                                            gt : 0
                                                                                        },
                                                                                        added: {
                                                                                            gt:moment().subtract(1, 'minute').toDate()
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }
                                                                        }
                                                                    } else{

                                                                    }
                                                                }
                                                            }
                                                        })
                                                        .then(function(gpsPacketDataList){
                                                            if(gpsPacketDataList){
                                                                if(gpsPacketDataList.length){
                                                                    if(gpsPacketDataList.length === 2){
                                                                        const gpsPacketData = gpsPacketDataList[1];
                                                                        console.log("GeoFence Packet Data", gpsPacketData);
                                                                        if(getDistance(gpsPacketData.latitude, gpsPacketData.longitude, gpsFenceVehicle.homeLocation["lat"], gpsFenceVehicle.homeLocation["lng"]) > gpsFenceVehicle.kilometers){
                                                                            //coming inward
                                                                            console.log("inward");
                                                                            gpsFenceVehicle.isGeoFenced = true;
                                                                            return gpsFenceVehicle.save();
                                                                        } else{
                                                                            //going outward
                                                                            console.log("outward");
                                                                            customerName = customerInstance.firstName;
                                                                            var lastName = customerInstance.lastName? customerInstance.lastName : "";
                                                                            customerName = customerName + " " + lastName;
                                                                            pushFrom = packageObj.companyName;
                                                                            instanceId = gpsPacketDataObj.id;
                                                                            eventType = "Geo Fence";
                                                                            title = "Car is suspected to be moving out of geo fencing";
                                                                            var message = geoFenceMessageFormat(customerName, eventType, title, instanceId);
                                                                            if(customerInstance.id && gpsTrackerInfoInstance.gpsTrackerNotification["geoFence"] === "on"){
                                                                                sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                                    if(error){
                                                                                        console.log(error);
                                                                                        // callback(error);
                                                                                    } else{
                                                                                        console.log("Notification for geo fencing send successfully");
                                                                                        gpsFenceVehicle.isGeoFenced = false;
                                                                                        return gpsFenceVehicle.save();

                                                                                    }
                                                                                });
                                                                            } else{
                                                                                gpsFenceVehicle.isGeoFenced = false;
                                                                                return gpsFenceVehicle.save();
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        })
                                                        .then(function(geoFenceVehicle){
                                                            if(geoFenceVehicle){
                                                                gpsFenceVehicle = geoFenceVehicle;
                                                                if(!gpsFenceVehicle.isGeoFenced && gpsTrackerInfoInstance.gpsTrackerNotification["geoFence"] === "on"){
                                                                    return databaseObj.GpsNotification.create({
                                                                        message: title,
                                                                        deviceIMEI : gpsPacketDataObj.deviceIMEI,
                                                                        status: "active",
                                                                        customerId: customerInstance.id
                                                                    });
                                                                }
                                                            }
                                                        })
                                                        .then(function(gpsNotification){
                                                            if(gpsNotification){
                                                                //disable notification
                                                                gpsFenceVehicle.isNotification = false;

                                                            } else if(!gpsFenceVehicle.isGeoFenced){
                                                                //disable notification
                                                                gpsFenceVehicle.isNotification = false;
                                                            } else{
                                                                //enable notification
                                                                gpsFenceVehicle.isNotification = true
                                                            }
                                                            return gpsFenceVehicle.save();
                                                        })
                                                        .then(function(){
                                                            console.log("Geo Fence Complete");
                                                        })
                                                        .catch(function(error){
                                                            // callback(error);
                                                            console.error(error);
                                                        })
                                                );
                                            }
                                        }
                                    });
                                    return Promise.all(promises);
                                }
                            }
                        })
                        .then(function(){
                            server.logger.info("Geo Fencing Notification send to all customers");
                        })
                        .catch(function(error){
                            server.logger.error(error);
                        })
                });
            }
            next();
        });
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

    var geoFenceMessageFormat = function(to, eventType, title, gpsPacketDataId){
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
