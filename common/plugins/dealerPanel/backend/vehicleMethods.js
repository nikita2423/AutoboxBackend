/**
 * Created by nikita on 25/9/17.
 */
'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {

    const async = require('async');
    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        findAllDealerVehicleMethod();
        orderGpsTrackerEmail();
        getAllTestVehiclesMethod();
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

    const orderGpsTrackerEmail = function(){
        const OrderGpsTracker = databaseObj.OrderGpsTracker;
        OrderGpsTracker.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const orderGpsTrackerOBj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    databaseObj.Dealer.findById(orderGpsTrackerOBj.dealerId)
                        .then(function(dealer){
                            if(dealer){
                                orderGpsTrackerOBj.dealer = dealer;
                            }
                        })
                        .then(function(){
                            //send email
                            const subject = "New GPS Trackers Order Arrived";
                            const to = [];
                            const from = packageObj.from;
                            to.push("vikram.dangi@hotmail.com");
                            to.push("info@autoboxapp.in");
                            emailPlugin.adminEmail.orderingGpsTracker(from, to, subject, orderGpsTrackerOBj, function (err, send) {
                                if(err){
                                    server.logger.error(err);
                                } else{
                                    server.logger.info("Email send Successfully for Ordering Gps Tracker");
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


    return {
        init: init
    };
};
