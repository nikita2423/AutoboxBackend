/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Area, server, helper) =>
{
    //Area.validatesUniquenessOf('name');
    const {
        isLength,
        trim
    } = require('validator');
    const _ = require('lodash');

    Area.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.name){
            instance.name = instance.name.toString().trim();
            const check = isLength(instance.name, {min: 2, max: 500});
            if(!check){
                return next(new Error("Area Name cannot exceed more than 500 words"));
            }
        }
        if(!instance.cityId){
            return next(new Error("City is required"));
        }
        if(!instance.pincode){
            return next(new Error("Pincode is required"));
        }

       /* if(!instance.countryId){
            return next(new Error("Country is required"));
        }*/

        next();

    });


    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }
};

