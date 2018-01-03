/**
 * Created by nikita on 20/7/17.
 */
'use strict'
module.exports = (Showroom, server, helper) =>
{
    Showroom.validatesUniquenessOf('showroomNumber');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const STATUS = ["active", "inactive"];
    const {validate} = require("../helper/usefullMethods");

    Showroom.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.showroomNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }



        if(instance.name){
            instance.name = toTitleCase(instance.name.toString().trim());
            const check = isLength(instance.name, {min: 2, max: 500});
            if(!check){
                return next(new Error("Showroom Name cannot exceed more than 500 words"));
            }
        }

        if(instance.salesConsultantName){
            instance.salesConsultantName = toTitleCase(instance.salesConsultantName.toString().trim());
            const check = isLength(instance.salesConsultantName, {min:3, max:200});
            if(!check){
                return next(new Error("Sales Consultant Name should be between 3 to 200"));
            }
        }

        if(instance.address){
            instance.address = toTitleCase(instance.address.toString().trim());
            const check = isLength(instance.address, {min:3, max:200});
            if(!check){
                return next(new Error("Address should be between 3 to 200"));
            }
        }

        if(!validate(instance, currentInstance, 'latlong')){
            return next(new Error("LatLong is required"));
        }

        if(!validate(instance, currentInstance, 'timings')){
            return next(new Error("Timings is required"));
        } else{
            if(instance.timings){
                const openingTime = instance.timings["opening"];
                const closingTime = instance.timings["closing"];
                if(!openingTime || !closingTime){
                    return next(new Error("Opening and closing timing is required"));
                }
            }

        }

       /* if(!instance.areaId){
            return next(new Error("Area is required"));
        }*/

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

    Showroom.observe("after save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const Dealer = server.models['Dealer'];
        if(instance){
            if(instance.dealerId){
                Dealer.findById(instance.dealerId)
                    .then(function(dealer){
                        if(dealer){
                            if(instance.id){
                                return dealer.updateAttribute("showroomId", instance.id);
                            }
                        }
                    })
                    .then(function(dealer){
                        next();
                    })
                    .catch(function(error){
                        next(error);
                    });
            }else{
                next();
            }
        }else{
            next();
        }
    });


    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }
};

