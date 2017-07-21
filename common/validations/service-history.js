/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Servicehistory, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Servicehistory.observe("before save", function(ctx,next){
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

        if(!validate(instance, currentInstance, "serviceBookingId")){
            return next(new Error("Service Booking is required"));
        }

        next();
    });
};
