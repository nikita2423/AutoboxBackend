/**
 * Created by nikita on 25/11/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    var init = function(){
        sellYourVehicleMethod();
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

    return {
        init: init
    };
};