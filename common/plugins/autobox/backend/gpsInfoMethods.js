/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const async = require("async");

    var init = function(){
        createGpsPacketDataMethod();
        createGpsTrackerInfoMethod();
    };

    const createGpsPacketDataMethod = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.createGpsPacketData = createGpsPacketData;
        GpsPacketData.remoteMethod('createGpsPacketData', {
            accepts: [
                {
                    arg: "gpsPacketDataObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const createGpsTrackerInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.createGpsTrackerInfo = createGpsTrackerInfo;
        GpsTrackerInfo.remoteMethod('createGpsTrackerInfo', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "gpsTrackerInfoObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const createGpsPacketData = function(gpsPacketDataObj, callback){
        const GpsPacketData = databaseObj.GpsPacketData;
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        let resultGpsPacketData;
        resultGpsPacketData = gpsPacketDataObj;
        let promises = [];
        GpsTrackerInfo.find({
            where: {
                deviceIMEI : gpsPacketDataObj.deviceIMEI
            }
        })
            .then(function(gpsTrackerInfoList){
                if(gpsTrackerInfoList){
                    if(gpsTrackerInfoList.length){
                        gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                           // console.log("create customerId", gpsTrackerInfo.customerId);
                            const customerId = gpsTrackerInfo.customerId;
                            //var gpsPacketDataObj_ = gpsPacketDataObj;
                            console.log("gpsInfoObj", resultGpsPacketData);
                            if(resultGpsPacketData){
                                if(!resultGpsPacketData.customerId){
                                    resultGpsPacketData.customerId = customerId;
                                } else{
                                    resultGpsPacketData.customerId = customerId;
                                }
                            }
                            console.log("resultPacketData CustomerId", resultGpsPacketData.customerId);
                            promises.push(function(callback){
                                GpsPacketData.create(resultGpsPacketData)
                                    .then(function(gpsPacketData){
                                        callback(null);
                                    })
                                    .catch(function(error){
                                        callback(error);
                                    });
                            });
                        });
                        async.series(promises, function(error){
                            if(error){
                               callback(error);
                            } else{
                                callback(null, {response: "success"});
                            }
                        });
                    }
                }
            })
            .catch(function(error){
                callback(error);
            });

    };

 /*   const createGpsPacketData = function(gpsPacketDataObj, callback){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.create(gpsPacketDataObj)
            .then(function(gpsPacketData){
                callback(null, {response: "success"});
            })
            .catch(function(error){
                callback(error);
            });
    };*/

    const createGpsTrackerInfo = function(ctx, gpsTrackerInfoObj, callback){
        const request = ctx.req;
        if(!gpsTrackerInfoObj){
            callback(new Error("Invalid Arguments!"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                    GpsTrackerInfo.findOne({
                        where:{
                            deviceIMEI: gpsTrackerInfoObj.deviceIMEI
                        }
                    })
                        .then(function(gpsTrackerInfo){
                            if(gpsTrackerInfo){
                                if(gpsTrackerInfo.gpsPassword.toString() === gpsTrackerInfoObj.gpsPassword.toString()){
                                    if(gpsTrackerInfo.customerId === customerId){
                                        //upsert it
                                        return GpsTrackerInfo.upsert({
                                            deviceIMEI : gpsTrackerInfo.deviceIMEI,
                                            gpsPassword : gpsTrackerInfo.gpsPassword,
                                            registrationNumber : gpsTrackerInfo.registrationNumber,
                                            serialNumber : gpsTrackerInfo.serialNumber,
                                            modelName : gpsTrackerInfo.modelName,
                                            customerId : customerId,
                                            added: gpsTrackerInfo.added,
                                            updated : gpsTrackerInfo.updated,
                                            id: gpsTrackerInfo.id
                                        });
                                    } else{
                                        return GpsTrackerInfo.create({
                                            deviceIMEI : gpsTrackerInfoObj.deviceIMEI,
                                            registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                            serialNumber : gpsTrackerInfoObj.serialNumber,
                                            modelName : gpsTrackerInfoObj.modelName,
                                            gpsPassword : gpsTrackerInfoObj.gpsPassword,
                                            customerId : customerId
                                        });
                                    }
                                } else{
                                    callback(new Error("Gps Password do not match"));
                                }
                            } else{
                                return  GpsTrackerInfo.create({
                                    deviceIMEI : gpsTrackerInfoObj.deviceIMEI,
                                    registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                    serialNumber : gpsTrackerInfoObj.serialNumber,
                                    modelName : gpsTrackerInfoObj.modelName,
                                    gpsPassword : gpsTrackerInfoObj.gpsPassword,
                                    customerId : customerId
                                });
                            }
                        })

                        .then(function(gpsTrackerInfo){
                            if(gpsTrackerInfo){
                                callback(null, {response: "success"});
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
        }
    };

    return {
        init: init
    };
}