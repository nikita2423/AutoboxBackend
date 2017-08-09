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

        if(instance.comments){
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
        }
        if(!validate(instance, currentInstance, "workshopId")){
            return next(new Error("Workshop is required"));
        }
        next();

    });


    var job = schedule.scheduleJob('48 * * * *', function(){
       console.log("Cron job implemented");
    });


    /*var sendReminderForServiceBooking = function(){
        let serviceBookingId;
        Servicebooking.find({
            include: "customer"
        })
            .then(function(serviceBookingList){
                if(serviceBookingList){
                    if(serviceBookingList.length){
                        serviceBookingList.forEach(function(serviceBooking){
                            if(serviceBooking){
                                if(serviceBooking.serviceDate){
                                    var job = schedule.scheduleJob(moment(), function(){
                                        if(moment(serviceBooking.serviceDate) > moment()){
                                            if(moment(serviceBooking).subtract(2, "m") === moment()){
                                                //send reminder for service booking
                                                serviceBookingId = serviceBooking.id;
                                                return server.models["Customer"].findById(serviceBooking.customerId);
                                            }
                                        }
                                    });

                                }
                            }
                        });
                    }
                }
            })
            .then(function(customer){
                if(customer){
                    //send the reminder
                    var name = customer.firstName;
                    var lastName = customer.lastName? customer.lastName: "";
                    name = name + " " + lastName;
                    var type = "ServiceBooking";
                    var title = "Service Booking Reminder";
                    var from = "Autobox";
                    var message = bookingPushFormat(name, type, title, serviceBookingId);
                    if(customer.registrationId){
                        sendNotification(server, message, customer.registrationId, from, function(error){
                            if(error){
                                console.log(error);
                            } else{
                                console.log("Service Booking before 4 hours notification send successfully");
                            }
                        });
                    }
                } else{
                    console.log("No Customer found");
                }
            })
            .catch(function(error){
                console.log(error);
            });
    };*/

   // sendReminderForServiceBooking();


    var sendNotification = function(app, message, registrationId, from, callback){
        push.push(app, message, registrationId, from, callback);
    };


    var bookingPushFormat = function(To, type, title, serviceBookingId){
        var message = {
            to : To,
            type : type,
            title : title,
            id : serviceBookingId
        };

        return JSON.stringify(message);
    };
};

