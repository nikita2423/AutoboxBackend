/**
 * Created by nikita on 23/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {
    const process = require("process");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        storeTrackDealerVehicleMethod();
        getAllTestVehiclesMethod();
        getTestVehicleLocationMethod();

    };

    const storeTrackDealerVehicleMethod = function(){
        const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
        TrackDealerVehicle.storeTrackDealerVehicle = storeTrackDealerVehicle;
        TrackDealerVehicle.remoteMethod('storeTrackDealerVehicle', {
            accepts: [
                {
                    arg: "storeTrackDealerVehicle", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };


    const getAllTestVehiclesMethod = function(){
        const DealerVehicle = databaseObj.DealerVehicle;
        DealerVehicle.getAllTestVehicles = getAllTestVehicles;
        DealerVehicle.remoteMethod('getAllTestVehicles', {
            accepts:[
                {
                    arg: "dealerId", type: "string"
                }
            ],
            returns: {
                arg: "dealerVehicleList", type: ["DealerVehicle"], root : true
            }
        });
    };

    const getTestVehicleLocationMethod = function(){
        const DealerVehicle = databaseObj.DealerVehicle;
        DealerVehicle.getTestVehicleLocation = getTestVehicleLocation;
        DealerVehicle.remoteMethod('getTestVehicleLocation', {
            accepts: [
                {
                    arg: "dealerId", type: "string"
                },
                {
                    arg: "serialNumber", type: "string"
                }
            ],
            returns: {
                arg: "trackVehicle", type: "TrackDealerVehicle", root : true
            }

        });
    };

    const getTestVehicleLocation = function(dealerId, serialNumber, callback){
        const DealerVehicle = databaseObj.DealerVehicle;
        const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
        DealerVehicle.findOne({
            where: {
                serialNumber : serialNumber,
                dealerId : dealerId
            }
        })
            .then(function(dealerVehicle){
                if(dealerVehicle){
                    return TrackDealerVehicle.findOne({
                        where : {
                            deviceIMEI : serialNumber
                        },
                        order:["added DESC"]
                    });
                }
            })
            .then(function(trackDealerVehicle){
                if(trackDealerVehicle){
                    callback(null, trackDealerVehicle);
                } else{
                    callback(null, {});
                }
            })
            .catch(function(error){
                callback(error);
            });
    };


    const getAllTestVehicles = function(dealerId, callback){
        const DealerVehicle = databaseObj.DealerVehicle;
        DealerVehicle.find({
            where: {
                dealerId : dealerId
            },
            include: [{
                relation: "brand",
                scope: {
                    fields: ["name"]
                }
            },
                {
                    relation: "carModel",
                    scope: {
                        fields: ["name"]
                    }
                }]
        })
            .then(function(dealerVehicleList){
                if(dealerVehicleList){
                    if(dealerVehicleList.length){
                        callback(null, dealerVehicleList);
                    } else{
                        callback(null, []);
                    }
                } else{
                    callback(null, []);
                }
            })
            .catch(function(error){
                callback(error);
            });
    };

    const storeTrackDealerVehicle = function(trackDealerVehicleObj, callback){
        if(!trackDealerVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
            const DealerVehicle = databaseObj.DealerVehicle;
            const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
            //console.log("TrackVehicleSerialNumber", trackDealerVehicleObj.deviceIMEI);
            DealerVehicle.findOne({
                where: {
                    serialNumber : trackDealerVehicleObj.deviceIMEI
                }
            })
                .then(function(dealerVehicle){
                    //console.log("DealerVehicle", dealerVehicle);
                    if(dealerVehicle){
                        return TrackDealerVehicle.create({
                            deviceIMEI : trackDealerVehicleObj.deviceIMEI,
                            clientId : trackDealerVehicleObj.clientId,
                            latlng: [trackDealerVehicleObj.latitude, trackDealerVehicleObj.longitude],
                            dealerId : dealerVehicle.dealerId,
                            dealerVehicleId : dealerVehicle.id,
                            latitude : trackDealerVehicleObj.latitude,
                            longitude : trackDealerVehicleObj.longitude
                        });
                    }
                })
                .then(function(trackDealerVehicle){
                    callback(null, {response: "success"});
                })
                .catch(function(error){
                    callback(error);
                });
        }
    };



    return {
        init: init
    };
}