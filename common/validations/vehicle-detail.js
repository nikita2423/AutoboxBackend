/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Vehicledetail, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Vehicledetail.observe("before save", function (ctx, next) {
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.registeredName){
            instance.registeredName = toTitleCase(instance.registeredName.toString().trim());
            const check = isLength(instance.registeredName, {min:3, max:300});
            if(!check){
                return next(new Error("Registered Name Length should be between 3 to 100"));
            }
        }

       /* if(!instance.registrationNumber){
            return next(new Error("Registration Number is required"));
        }*/


     /*   if(!validate(instance, currentInstance, "showroomId")){
            return next(new Error("Showroom is required"));
        }*/

        if(!validate(instance, currentInstance, "workshopId")){
            return next(new Error("Workshop is required"));
        }

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }

        next();

    });

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }


};
