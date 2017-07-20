/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Emergency, server, helper) =>
{
    Emergency.validatesUniquenessOf('name');
    Emergency.validatesUniquenessOf('emergencyNumber');
    const {
        isLength,
        trim
    } = require('validator');
    const _ = require('lodash');

    Emergency.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.emergencyNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(instance.name){
            instance.name = toTitleCase(instance.name.toString().trim());
            const check = isLength(instance.name,{min: 2, max: 500});
            if(!check){
                return next(new Error("Emergency length should be between 2 to 500"));
            }
        }

        if(!instance.latlong){
            return next(new Error("LatLong is required"));
        }

        if(!instance.cityId){
            return next(new Error("City is required"));
        }

        if(!instance.emergencyCategoryId){
            return next(new Error("Emergency Category is required"));
        }

        if(!instance.areaId){
            return next(new Error("Area is required"));
        }

        next();


    });

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }
};
