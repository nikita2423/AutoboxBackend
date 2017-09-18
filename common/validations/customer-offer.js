/**
 * Created by nikita on 16/9/17.
 */

module.exports = function(Customeroffer, server, helper) {

    const {validate} = require("../helper/usefullMethods");
    const process = require("process");
    const STATUS = ["active", "expired"];
    var schedule = require('node-schedule');
    const moment = require("moment");

    const alarmManager =  {
        "dailyHour": 0,
        "dailyMinutes": 1
    };

    Customeroffer.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer id is required"));
        }
        if(!validate(instance, currentInstance, "offerId")){
            return next(new Error("Offer id is required"));
        }

        next();
    });

    var checkCustomerOfferExpiry = function(){
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

            Customeroffer.updateAll(where, {
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

    checkCustomerOfferExpiry();

};
