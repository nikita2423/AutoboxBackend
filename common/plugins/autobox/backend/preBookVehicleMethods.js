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
        const PreVehicleBooking = databaseObj.PreVehicleBooking;
        PreVehicleBooking.createPreVehicleBooking = createPreVehicleBooking;
        PreVehicleBooking.remoteMethod("createPreVehicleBooking", {
           accepts:[
               {
                   arg: 'ctx',
                   type: 'object',
                   http: {
                       source: 'context'
                   }
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

    const createPreVehicleBooking = function(ctx, preBookVehicleObj, callback){
        const request = ctx.req;
        if(!preBookVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        const PreVehicleBooking = databaseObj.PreVehicleBooking;
                        PreVehicleBooking.create({
                            brandName : preBookVehicleObj.brandName,
                            modelName : preBookVehicleObj.modelName,
                            preBookVehicleModelId : preBookVehicleObj.preBookVehicleModelId,
                            customerId : customerId
                        })
                            .then(function(preVehicleBooking){
                                if(preVehicleBooking){
                                    callback(null, {response: "success"});
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else{
                        callback(new Error("Invalid User"));
                    }
                } else{
                    callback(new Error("Invalid User"));
                }
            } else{
                callback(new Error("Invalid User"));
            }
        }
    };

    const sendPreVehicleBookingEmail = function(){
        const PreVehicleBooking = databaseObj.PreVehicleBooking;
        PreVehicleBooking.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const preVehicleBookingObj = instance.toJSON();
            if(ctx.isNewInstance){
               process.nextTick(function(){
                   databaseObj.Customer.findById(preVehicleBookingObj.customerId)
                       .then(function(customer){
                           if(customer){
                               preVehicleBookingObj.customer = customer;
                           }
                       })
                       .then(function(){
                           //send Email
                           const subject = packageObj.admin.pre_book_vehicle_request;
                           const to = [];
                           const from = packageObj.from;
                           to.push("sales@autoboxapp.in");
                           emailPlugin.adminEmail.preVehicleBooking(from, to, subject, preVehicleBookingObj, function (err, send) {
                               if(err){
                                   console.log(err);
                               } else{
                                   console.log("Email send Successfully for Booking Vehicle to admin");
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