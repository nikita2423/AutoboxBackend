/**
 * Created by nikita on 28/11/17.
 */
module.exports = function(Battery, server, helper) {

    const {validate} = require("../helper/usefullMethods");

    Battery.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.batteryNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(!validate(instance, currentInstance, 'customerId')){
            return next(new Error("Customer is required"));
        }

        if(!validate(instance, currentInstance, 'vehicleInfoId')){
            return next(new Error("Vehicle Info is required"));
        }



        next();
    });
};