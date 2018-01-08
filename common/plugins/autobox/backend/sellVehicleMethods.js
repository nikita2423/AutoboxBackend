/**
 * Created by nikita on 25/11/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        sellYourVehicleMethod();
        sendSellVehicleEmailToAdminMethod();
    };


    const sellYourVehicleMethod = function(){
        const SellVehicle = databaseObj.SellVehicle;
        SellVehicle.sellYourVehicle = sellYourVehicle;
        SellVehicle.remoteMethod("sellYourVehicle", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "vehicleInfoObj", type: "object"
                },
                {
                    arg: "sellVehicleObj", type: "object"
                }
            ],
            returns:{
                arg: "response", type: "object", root: true
            }
        });
    };

    const sellYourVehicle = function(ctx, vehicleInfoObj, sellVehicleObj, callback){
        const request = ctx.req;
        let vehicleInfoInstance;
        if(!vehicleInfoObj && !sellVehicleObj){
            callback("Invalid Arguments");
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const SellVehicle = databaseObj.SellVehicle;
                    const VehicleInfo = databaseObj.VehicleInfo;

                    VehicleInfo.create(vehicleInfoObj)
                    .then(function(vehicleInfo){
                        if(vehicleInfo){
                            vehicleInfoInstance = vehicleInfo;
                            return SellVehicle.create({
                                registrationNumber : sellVehicleObj.registrationNumber,
                                kilometers : sellVehicleObj.kilometers,
                                yearOfPurchase : sellVehicleObj.yearOfPurchase,
                                vehicleInfoId : vehicleInfo.id,
                                trimData : sellVehicleObj.trimData,
                                customerId : customerId
                            });
                        } else{
                            throw new Error("Error in creating vehicle info");
                        }
                    })
                        .then(function(sellVehicle){
                            if(sellVehicle){
                                callback(null, {response: "success"});
                                //send Email or notification
                            }
                        })
                        .catch(function(error){
                            vehicleInfoInstance.destroy();
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


    const sendSellVehicleEmailToAdminMethod = function(){
        const SellVehicle = databaseObj.SellVehicle;
        SellVehicle.observe("after save", function(ctx, next){
            if(ctx.isNewInstance){
                const instance = ctx.instance;
                const sellVehicleObj = instance.toJSON();

                process.nextTick(function(){
                    databaseObj.Customer.findById(sellVehicleObj.customerId)
                        .then(function(customer){
                            if(customer){
                                sellVehicleObj.customer = customer;
                                return databaseObj.VehicleInfo.findById(sellVehicleObj.vehicleInfoId);
                            }
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo){
                                sellVehicleObj.vehicleInfo = vehicleInfo;
                            }
                        })
                        .then(function(){
                            const subject = packageObj.admin.sell_vehicle_subject;
                            const to = [];
                            const from = packageObj.from;
                            to.push("sales@autoboxapp.in");
                            emailPlugin.adminEmail.sellvehicle(from, to, subject, sellVehicleObj, function (err, send) {
                                if(err){
                                    console.log(err);
                                } else{
                                    console.log("Email send Successfully for Sell Vehicle to admin");
                                }
                            });
                        })
                        .catch(function(error){
                            console.log(error);
                        });
                });
            }
            next();
        });
    };

    return {
        init: init
    };
};