/**
 * Created by nikita on 23/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {
    const process = require("process");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        storeTrackDealerVehicleMethod();

    };

    const storeTrackDealerVehicleMethod = function(){
        const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
        TrackDealerVehicle.storeTrackDealerVehicle = storeTrackDealerVehicle;
        TrackDealerVehicle.remoteMethod('storeTrackDealerVehicle', {
            accepts: [
                {
                    arg: "trackDealerVehicleObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };




    const storeTrackDealerVehicle = function(trackDealerVehicleObj, callback){
        if(!trackDealerVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
            const DealerVehicle = databaseObj.DealerVehicle;
            const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
            DealerVehicle.findOne({
                where: {
                    deviceIMEI : trackDealerVehicleObj.deviceIMEI
                }
            })
                .then(function(dealerVehicle){
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