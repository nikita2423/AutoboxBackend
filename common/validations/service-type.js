/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Servicetype, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const STATUS = ["active", "inactive"];

    Servicetype.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.name){
            instance.name = toTitleCase(instance.name.toString().trim());
            const check = isLength(instance.name, {min:3, max:200});
            if(!check){
                return next(new Error("Name length should be between 3 to 200"));
            }
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }

        next();
    });

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }


};
