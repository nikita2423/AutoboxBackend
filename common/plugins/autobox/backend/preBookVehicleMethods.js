/**
 * Created by nikita on 11/1/18.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        createPreVehicleBookingMethod();
        sendPreVehicleBookingEmail();
    };


    const createPreVehicleBookingMethod = function(){
        const PreBookVehicle = databaseObj.PreBookVehicle;
        PreBookVehicle.createPreVehicleBooking = createPreVehicleBooking;
        PreBookVehicle.remoteMethod("createPreVehicleBooking", {
           accepts:[
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
                   arg: "preBookVehicleObj", type: "object"
               }
           ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const createPreVehicleBooking = function(ctx, vehicleInfoObj, preBookVehicleObj, callback){
        const request = ctx.req;
        if(!vehicleInfoObj && !preBookVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        const PreBookVehicle = databaseObj.PreBookVehicle;
                        const VehicleInfo = databaseObj.VehicleInfo;
                        VehicleInfo.create({
                            colorId : vehicleInfoObj.colorId,
                            brandId: vehicleInfoObj.brandId,
                            carModelId: vehicleInfoObj.carModelId,
                            trimId: vehicleInfoObj.trimId,
                            customerId: customerId,
                            gearBoxId: vehicleInfoObj.gearBoxId,
                            fuelId: vehicleInfoObj.fuelId,
                            vehicleModel: vehicleInfoObj.vehicleModel,
                            vehicleType: "car",
                            fuelType: vehicleInfoObj.fuelType,
                            vehicleTrim: vehicleInfoObj.vehicleTrim,
                            vehicleBrand: vehicleInfoObj.vehicleBrand,
                            vehicleGearbox : vehicleInfoObj.vehicleGearbox,
                            vehicleColor: vehicleInfoObj.vehicleColor
                        })
                            .then(function(vehicleInfo){
                                if(vehicleInfo){
                                    return PreBookVehicle.create({
                                        customerName: preBookVehicleObj.customerName,
                                        vehicleInfoId : vehicleInfo.id,
                                        customerId : customerId
                                    });
                                }
                            })
                            .then(function(preBookVehicle){
                                if(preBookVehicle){
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
                    callback(new Error("user not valid"));
                }
            } else{
                callback(new Error("user not valid"));
            }
        }
    };

    const sendPreVehicleBookingEmail = function(){
        const PreBookVehicle = databaseObj.PreBookVehicle;
        PreBookVehicle.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const preBookVehicleObj = instance.toJSON();
            if(ctx.isNewInstance){
               process.nextTick(function(){
                   databaseObj.Customer.findById(preBookVehicleObj.customerId)
                       .then(function(customer){
                           if(customer){
                               preBookVehicleObj.customer = customer;
                               return databaseObj.VehicleInfo.findById(preBookVehicleObj.vehicleInfoId);
                           }
                       })
                       .then(function(vehicleInfo){
                           if(vehicleInfo){
                               preBookVehicleObj.vehicleInfo = vehicleInfo;
                           }
                       })
                       .then(function(){
                           //send Email
                           const subject = packageObj.admin.pre_book_vehicle_request;
                           const to = [];
                           const from = packageObj.from;
                           to.push("sales@autoboxapp.in");
                           emailPlugin.adminEmail.preVehicleBooking(from, to, subject, preBookVehicleObj, function (err, send) {
                               if(err){
                                   //console.log(err);
                               } else{
                                   //console.log("Email send Successfully for Battery to admin");
                               }
                           });
                       })
                       .catch(function(error){
                           console.log(error);
                       })
               });
            }
            next();
        })
    }


    return {
        init: init
    };

}