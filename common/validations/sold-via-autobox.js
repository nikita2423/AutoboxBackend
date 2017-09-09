/**
 * Created by nikita on 11/8/17.
 */

'use strict';

module.exports = function(Soldviaautobox, server, helper) {

    const TYPE = ["car", "bike"];
    const {trim} = require('validator');
    const {validate} = require("../helper/usefullMethods");

    Soldviaautobox.observe('before save', function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.type){
            instance.type = instance.type.toString().toLowerCase().trim();
            if(TYPE.indexOf(instance.type) === -1){
                return next(new Error("Type is not valid"));
            }
        }

      /*  if(!validate(instance, currentInstance, "vehicleInfoId")){
            return next(new Error("Vehicle Information is required"));
        }*/

        if(!validate(instance, currentInstance, "dealerId")){
            return next(new Error("Dealer Id is required"));
        }
        if(!validate(instance, currentInstance, "customerQuoteId")){
            return next(new Error("Customer Quote Id is required"));
        }
        next();
    });
};

