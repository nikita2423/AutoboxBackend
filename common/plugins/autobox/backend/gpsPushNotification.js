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
        //sendGpsNotification();
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