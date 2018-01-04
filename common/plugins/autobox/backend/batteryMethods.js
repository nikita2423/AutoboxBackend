/**
 * Created by nikita on 28/11/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");


    var init = function(){
        buyBatteryMethod();
        sendRequestBatteryEmailMethod();
    };


    const buyBatteryMethod = function(){
        const Battery = databaseObj.Battery;
        Battery.buyBattery = buyBattery;
        Battery.remoteMethod("buyBattery", {
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
                }
            ],
            returns:{
                arg: "response", type: "object", root: true
            }
        });

    };

    const buyBattery = function(ctx, vehicleInfoObj, callback){
        const request = ctx.req;
        let vehicleInfoInstance;
        if(!vehicleInfoObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const VehicleInfo = databaseObj.VehicleInfo;
                    const Battery = databaseObj.Battery;

                    VehicleInfo.create(vehicleInfoObj)
                        .then(function(vehicleInfo){
                            if(vehicleInfo){
                                vehicleInfoInstance = vehicleInfo;
                                return Battery.create({
                                    customerId : customerId,
                                    vehicleInfoId : vehicleInfo.id
                                });
                            } else{
                                throw new Error("Error in creating vehicle Info");
                            }
                        })
                        .then(function(battery){
                            callback(null, {response: "success"});
                        })
                        .catch(function(error){
                            if(vehicleInfoInstance){
                                vehicleInfoInstance.destroy();
                            }
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

    const sendRequestBatteryEmailMethod = function(){
        const Battery = databaseObj.Battery;
        Battery.observe("after save", function(ctx, next){
            if(ctx.isNewInstance) {
                const instance = ctx.instance;
                const batteryObj = instance.toJSON();

                process.nextTick(function(){
                    databaseObj.Customer.findById(batteryObj.customerId)
                        .then(function(customer){
                            batteryObj.customer = customer;
                            return databaseObj.VehicleInfo.findById(batteryObj.vehicleInfoId);
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo){
                                batteryObj.vehicleInfo = vehicleInfo;
                            }
                        })
                        .then(function(){
                            const subject = packageObj.admin.battery_request_subject;
                            const to = [];
                            const from = packageObj.from;
                            to.push("vikram.dangi@hotmail.com");
                            emailPlugin.adminEmail.buyBattery(from, to, subject, batteryObj, function (err, send) {
                                if(err){
                                    //console.log(err);
                                } else{
                                    //console.log("Email send Successfully for Battery to admin");
                                }
                            });
                        })
                        .catch(function(error){
                            console.log(error);
                        });
                })
            }
            next();
        })
    }

    return {
        init: init
    };
}
