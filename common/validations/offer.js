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
    var schedule = require('node-schedule');
    const moment = require("moment");

    const alarmManager =  {
        "dailyHour": 0,
        "dailyMinutes": 1
    };

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
        if(ctx.isNewInstance) {
            if (!validate(instance, currentInstance, "cityId") && !validate(instance, currentInstance, "areaId") && !validate(instance, currentInstance, "brandId")) {
                return next(new Error("City or Area or Brand is required"));
            }
        }
        next();

    });

    var checkOfferExpiry = function(){
        var rule = new schedule.RecurrenceRule();
        rule.hour = alarmManager.dailyHour || 0;
        rule.minute = alarmManager.dailyMinutes || 1;
        var job = schedule.scheduleJob(rule, function(){
            console.log("Waking up to check the expiry of offers");
            var yesterday = moment().subtract(1, 'days');
            var where = {
                status: "active",
                expiredOn: {
                    lt: moment.utc(yesterday)
                }
            };

            Offer.updateAll(where, {
                status: "expired"
            }, function(error, info){
                if(error){
                    console.log(error);
                } else{
                    console.log(info);
                }
            });





        });
    };

    checkOfferExpiry();
};
