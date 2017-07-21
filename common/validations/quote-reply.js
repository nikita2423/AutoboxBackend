/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Quotereply, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Quotereply.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.replyNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(instance.rtoRegistration){
            const check = isLength(instance.rtoRegistration, {min:3,max:100});
            if(!check){
                return next(new Error("RTO Registration should be between 3 to 100"));
            }
        }

        if(!validate(instance, currentInstance, "customerQuoteId")){
            return next(new Error("Customer Quote is required"));
        }

        if(!validate(instance, currentInstance, "dealerId")){
            return next(new Error("Dealer is required"));
        }

        next();
    });
};

