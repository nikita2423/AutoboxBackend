'use strict';

module.exports = function(Sellvehicle,server, helper) {

    const {validate}    = require("../helper/usefullMethods");

    Sellvehicle.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }

        if(!validate(instance, currentInstance, "vehicleInfoId")){
            return next(new Error("Vehicle Info is required"));
        }

        next();

    });

};
