/**
 * Created by nikita on 21/7/17.
 */
'use strict';
module.exports = (Servicebooking, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const schedule = require('node-schedule');
    const moment = require('moment');
    const push = helper.loadPlugin("pushService");


    Servicebooking.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.bookingNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

      /*  if(instance.comments){
            const check = isLength(instance.comments,{min:3, max:500});
            if(!check){
                return next(new Error("Comments length should be between 3 to 500"));
            }
        }

        if(!instance.vehiclePickup){
            return next(new Error("Vehicle Pick Up is required"));
        }


        if(!validate(instance, currentInstance, "serviceTypeId")){
            return next(new Error("Service Type is required"));
        }*/

        if(!validate(instance, currentInstance, "workshopId")){
            return next(new Error("Workshop is required"));
        }

        if(!validate(instance, currentInstance, "vehicleDetailId")){
            return next(new Error("Vehicle Detail is required"));
        }

        next();

    });

};

