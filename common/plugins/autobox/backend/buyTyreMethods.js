/**
 * Created by nikita on 19/2/18.
 */
"use strict";

module.exports = function( server, databaseObj, helper, packageObj) {

    var init = function(){
        buyTyreMethod();
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

    return {
        init: init
    };
}