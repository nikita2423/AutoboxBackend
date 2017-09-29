/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const async = require("async");

    var init = function(){
        createGpsPacketDataMethod();
        createGpsTrackerInfoMethod();
        findAllGpsInfoMethod();
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
                arg: "data", type: "object", root: true
            }
        });
    };

    const createGpsTrackerInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.saveGpsTrackerInfo = saveGpsTrackerInfo;
        GpsTrackerInfo.remoteMethod('saveGpsTrackerInfo', {
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
                arg: "data", type: "object", root: true
            }
        });
    };

    const findAllGpsInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.findAll = findAllGpsInfo;
        GpsTrackerInfo.remoteMethod('findAll', {
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
                arg: "data", type: "object", root: true
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
                            //console.log("gpsInfoObj", resultGpsPacketData);
                            if(resultGpsPacketData){
                                if(!resultGpsPacketData.customerId){
                                    resultGpsPacketData.customerId = customerId;
                                } else{
                                    resultGpsPacketData.customerId = customerId;
                                }
                            }
                            console.log("create customerId", gpsTrackerInfo.customerId);
                            promises.push(function(callback){
                                GpsPacketData.create({
                                    clientId: gpsPacketDataObj.clientId,
                                    deviceIMEI: gpsPacketDataObj.deviceIMEI,
                                    eventCode : gpsPacketDataObj.eventCode,
                                    isStoredPacket : gpsPacketDataObj.isStoredPacket,
                                    eventType : gpsPacketDataObj.eventType,
                                    latlng: [gpsPacketDataObj.latitude, gpsPacketDataObj.longitude],
                                    eventDate : gpsPacketDataObj.eventDate,
                                    gpsStatus : gpsPacketDataObj.gpsStatus,
                                    gmsSignal : gpsPacketDataObj.gmsSignal,
                                    speed : gpsPacketDataObj.speed,
                                    accumulatedDistance : gpsPacketDataObj.accumulatedDistance,
                                    courseInDegree : gpsPacketDataObj.courseInDegree,
                                    satelliteConnected : gpsPacketDataObj.satelliteConnected,
                                    hdop : gpsPacketDataObj.hdop,
                                    voltageEquivalent : gpsPacketDataObj.voltageEquivalent,
                                    digitalInput1 : gpsPacketDataObj.digitalInput1,
                                    caseStatus : gpsPacketDataObj.caseStatus,
                                    isOverSpeedStarted : gpsPacketDataObj.isOverSpeedStarted,
                                    isOverSpeedEnded : gpsPacketDataObj.isOverSpeedEnded,
                                    immobilizerVoilationAlert : gpsPacketDataObj.immobilizerVoilationAlert,
                                    batteryStatus : gpsPacketDataObj.batteryStatus,
                                    digitalInput2 : gpsPacketDataObj.digitalInput2,
                                    ignitionStatus: gpsPacketDataObj.ignitionStatus,
                                    internalBatteryLowAlert: gpsPacketDataObj.internalBatteryLowAlert,
                                    anglePollingBit : gpsPacketDataObj.anglePollingBit,
                                    digitalOutput1Status : gpsPacketDataObj.digitalOutput1Status,
                                    isHarshAccelerationDetected : gpsPacketDataObj.isHarshAccelerationDetected,
                                    isHarshBrakingDetected: gpsPacketDataObj.isHarshBrakingDetected,
                                    externalBatteryVoltage: gpsPacketDataObj.externalBatteryVoltage,
                                    internalBatteryVoltage : gpsPacketDataObj.internalBatteryVoltage,
                                    gpsPacketId : gpsPacketDataObj.gpsPacketId,
                                    customerId : customerId
                                })
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
                               console.log("Notification send Successfully");
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

   const saveGpsTrackerInfo = function(ctx, gpsTrackerInfoObj, callback){
     const request = ctx.req;
     if(!gpsTrackerInfoObj){
         callback(new Error("Invalid Arguments"));
     } else{
         if(request.accessToken){
             if(request.accessToken.userId){
                 const customerId = request.accessToken.userId;
                 const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                 var count = 0;
                 let gpsTrackerInfoInstance;
                 GpsTrackerInfo.find({
                     where: {
                         deviceIMEI: gpsTrackerInfoObj.deviceIMEI
                     }
                 })
                     .then(function(gpsTrackerInfoList){
                         if(gpsTrackerInfoList){
                             if(gpsTrackerInfoList.length){
                                 if(gpsTrackerInfoList[0].gpsPassword.toString() === gpsTrackerInfoObj.gpsPassword.toString()){
                                     gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                         if(JSON.stringify(gpsTrackerInfo.customerId) === JSON.stringify(customerId)){
                                             count = count + 1;
                                             gpsTrackerInfoInstance = gpsTrackerInfo;
                                         }
                                     });
                                     if(count>0){
                                         //upsert
                                         return gpsTrackerInfoInstance.updateAttribute("gpsPassword", gpsTrackerInfoInstance.gpsPassword);
                                     } else{
                                         //create
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
                                     //unauthorized
                                   callback(new Error("Pin doesn't match"));
                                 }
                             } else{
                                 //create
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
                             //create
                             return GpsTrackerInfo.create({
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
                         } else{
                             callback(null, {response: "failure"});
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

   const findAllGpsInfo = function(ctx, filter, lastDate, callback){
       const request = ctx.req;
       lastDate = !lastDate ? new Date():new Date(lastDate);
       let limit;
       if(!lastDate && !filter){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                   limit = filter.limit;
                   GpsTrackerInfo.find({
                       limit : limit,
                       where: {
                           customerId : customerId
                       }
                   })
                       .then(function(gpsTrackerInfoList){
                           if(gpsTrackerInfoList){
                               if(gpsTrackerInfoList.length){
                                   const gpsTrackerInfo = gpsTrackerInfoList[gpsTrackerInfoList.length - 1];
                                   lastDate = gpsTrackerInfo.added;
                               }
                           }
                           callback(null, {
                               data: gpsTrackerInfoList,
                               cursor : lastDate
                           });
                       })
                       .catch(function(error){
                           callback(error);
                       });
               }
           }
       }
   };


    return {
        init: init
    };
}