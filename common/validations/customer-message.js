/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Customermessage, server, helper) =>
{
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const {validate} = require("../helper/usefullMethods");
    const STATUS = ["pending", "contacted"];
    const USERTYPE = ["dealer", "customer"];
    const CONTACTTYPE = ["call", "message"];

    Customermessage.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.message){
            instance.message = _.capitalize(trim(instance.message));
            const check = isLength(instance.message, {min:3, max:1000});
            if(!check){
                return next(new Error("Message should be between 3 to 1000"));
            }
        } else{
            instance.message = "NA";
        }

        if(instance.subject){
            instance.subject = _.capitalize(trim(instance.subject));
            const check = isLength(instance.subject, {min:3, max:500});
            if(!check){
                return next(new Error("Subject should be between 3 to 500"));
            }
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }

        if(instance.userType){
            instance.userType = instance.userType.toString().toLowerCase().trim();
            if(USERTYPE.indexOf(instance.userType) === -1){
                return next(new Error("User type is not valid"));
            }
        }

        if(instance.type){
            instance.type = instance.type.toString().toLowerCase().trim();
            if(CONTACTTYPE.indexOf(instance.type) === -1){
                return next(new Error("Contact Type is not valid"));
            }
        }

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }

        if(!validate(instance, currentInstance, "dealerId")){
            return next(new Error("Dealer is required"));
        }

        if(!validate(instance, currentInstance, "customerQuoteId")){
            return next(new Error("Customer Quote is required"));
        }

        next();

    });
};
