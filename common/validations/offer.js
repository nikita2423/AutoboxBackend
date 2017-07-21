/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Offer, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    const STATUS = ["active", "expired"];

    Offer.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.title){
            instance.title = _.capitalize(trim(instance.title));
            const check = isLength(instance.title, {min:3, max:200});
            if(!check){
                return next(new Error("Title length should be between 3 to 200"));
            }
        }

        if(instance.description){
            instance.description = _.capitalize(trim(instance.description));
            const check = isLength(instance.title, {min:3, max:1000});
            if(!check){
                return next(new Error("Description length should be between 3 to 1000"));
            }
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }

        if(!validate(instance, currentInstance, "cityId") && !validate(instance, currentInstance, "areaId") && !validate(instance, currentInstance, "brandId")){
            return next(new Error("City or Area or Brand is required"));
        }

        next();

    });
};
