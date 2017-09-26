/**
 * Created by nikita on 25/9/17.
 */
'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {

    const async = require('async');


    var init = function(){
        findAllDealerVehicleMethod();
    };

    const findAllDealerVehicleMethod = function(){
        const DealerVehicle = databaseObj.DealerVehicle;
        DealerVehicle.findAll = findAllDealerVehicle;
        DealerVehicle.remoteMethod('findAll', {
            accepts: [
                {
                    arg: "dealerId", type: "string"
                }
            ],
            returns: {
                arg: "vehicleList", type: ["TrackDealerVehicle"], root: true
            }
        });
    };

    const findAllDealerVehicle = function(dealerId, callback){
        const DealerVehicle = databaseObj.DealerVehicle;
        const TrackDealerVehicle = databaseObj.TrackDealerVehicle;
        let promises = [];
        let vehicleList = [];
        DealerVehicle.find({
            where: {
                dealerId : dealerId
            }
        })
            .then(function(dealerVehicleList){
                if(dealerVehicleList){
                    if(dealerVehicleList.length){
                        dealerVehicleList.forEach(function(dealerVehicle){
                            promises.push(function(callback){
                                TrackDealerVehicle.findOne({
                                    where: {
                                        deviceIMEI : dealerVehicle.deviceIMEI,
                                        added: {
                                            lt : new Date()
                                        }
                                    }
                                })
                                    .then(function(trackDealerVehicle){
                                        if(trackDealerVehicle){
                                            vehicleList.push(trackDealerVehicle);
                                        }
                                        callback(null);
                                    })
                                    .catch(function(error){
                                        callback(error);
                                    });
                            });
                        });

                        async.series(promises, function(error, list){
                            if(error){
                                callback(error);
                            } else{
                                callback(null, vehicleList);
                            }
                        });
                    }
                }
            })
            .catch(function(error){
                callback(error);
            });
    };


    return {
        init: init
    };
};
