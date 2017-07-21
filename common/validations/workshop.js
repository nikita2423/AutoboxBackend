/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Workshop, server, helper) =>
{
    Workshop.validatesUniquenessOf('workshopNumber');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const STATUS = ["active", "inactive"];

    Workshop.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.workshopNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }


        if(instance.dealershipName){
            instance.dealershipName = toTitleCase(instance.dealershipName.toString().trim());
            const check = isLength(instance.dealershipName, {min: 2, max: 200});
            if(!check){
                return next(new Error("Dealership Name cannot exceed more than 500 words"));
            }
        }

        if(instance.consultantName){
            instance.consultantName = toTitleCase(instance.consultantName.toString().trim());
            const check = isLength(instance.consultantName, {min:3, max:200});
            if(!check){
                return next(new Error("Consultant Name should be between 3 to 200"));
            }
        }

        if(instance.address){
            instance.address = toTitleCase(instance.address.toString().trim());
            const check = isLength(instance.address, {min:3, max:500});
            if(!check){
                return next(new Error("Address should be between 3 to 500"));
            }
        }

        if(!instance.latlong){
            return next(new Error("LatLong is required"));
        }

        if(!instance.timings){
            return next(new Error("Timings is required"));
        } else{
            const openingTime = instance.timings["opening"];
            const closingTime = instance.timings["closing"];
            if(!openingTime || !closingTime){
                return next(new Error("Opening and closing timing is required"));
            }
        }

        if(!instance.areaId){
            return next(new Error("Area is required"));
        }

        if(instance.email){
            instance.email = trim(instance.email);
            //Sanitize an Email..
            instance.email = normalizeEmail(instance.email);
            //Check if the max length 200
            const check = isEmail(instance.email);
            if(!check){
                return next(new Error("Email must be valid"));
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


