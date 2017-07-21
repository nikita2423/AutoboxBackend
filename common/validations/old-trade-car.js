/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Oldtradecar, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Oldtradecar.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(!validate(instance, currentInstance, "areaId")){
            return next(new Error("Area is required"));
        }

        if(!validate(instance, currentInstance, "cityId")){
            return next(new Error("Register city is required"));
        }

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }

        if(!validate(instance, currentInstance, "carModelId")){
            return next(new Error("Car Model is required"));
        }

        if(!validate(instance, currentInstance, "brandId")){
            return next(new Error("Brand is required"));
        }

        next();
    });
};
