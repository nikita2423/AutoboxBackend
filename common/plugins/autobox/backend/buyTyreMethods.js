/**
 * Created by nikita on 19/2/18.
 */
"use strict";

module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        buyTyreMethod();
        sendBuyTyreEmailToAdmin();
    };

    const buyTyreMethod = function(){
        const BuyTyre = databaseObj.BuyTyre;
        BuyTyre.buyTyre = buyTyre;
        BuyTyre.remoteMethod("buyTyre", {
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
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };


    const buyTyre = function(ctx, vehicleInfoObj, callback){
        const BuyTyre = databaseObj.BuyTyre;
        const VehicleInfo = databaseObj.VehicleInfo;
        let vehicleInfoInstance;
        const request = ctx.req;
        if(!vehicleInfoObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        VehicleInfo.create(vehicleInfoObj)
                            .then(function(vehicleInfo){
                                if(vehicleInfo){
                                    vehicleInfoInstance = vehicleInfo;
                                    return BuyTyre.create({
                                        customerId : customerId,
                                        vehicleInfoId : vehicleInfo.id
                                    });
                                } else{
                                    throw new Error("Error in creating vehicle info");
                                }
                            })
                            .then(function(buyTyre){
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
        }
    };


    const sendBuyTyreEmailToAdmin = function(){
        const BuyTyre = databaseObj.BuyTyre;
        BuyTyre.observe("after save", function(ctx, next){
            if(ctx.isNewInstance){
                const instance = ctx.instance;
                const buyTyreObj = instance.toJSON();
                process.nextTick(function(){
                    databaseObj.Customer.findById(buyTyreObj.customerId)
                        .then(function(customer){
                            buyTyreObj.customer = customer;
                            return databaseObj.VehicleInfo.findById(buyTyreObj.vehicleInfoId);
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo){
                                buyTyreObj.vehicleInfo = vehicleInfo;
                            }
                        })
                        .then(function(){
                            const subject = packageObj.admin.tyre_request_subject;
                            const to = [];
                            const from = packageObj.from;
                            to.push("sales@autoboxapp.in");
                            to.push("info@autoboxapp.in");
                            emailPlugin.adminEmail.buyTyreEmail(from, to, subject, buyTyreObj, function (err, send) {
                                if(err){
                                    console.log(err);
                                } else{
                                    console.log("Email send Successfully for Tyre to admin");
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
    }

    return {
        init: init
    };
}