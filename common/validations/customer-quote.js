/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Customerquote, server, helper) =>
{
    const {validate}    = require("../helper/usefullMethods");
    const schedule      = require('node-schedule');
    const moment        = require('moment');
    const push          = helper.loadPlugin("pushService");
    const autoboxPlugin = helper.loadPlugin("autobox");
    const OWNERSHIPTYPE = ["ind", "cor"];
    const QUOTETYPE     = ["q", "t"];
    let dealerId = "";

    /*Override the custmer quote method for Hiding the Mobile number*/
    Customerquote.afterRemote("find", function (ctx, data, next) {
        if(data){
            autoboxPlugin.overrideCustomerQuoteData(ctx.req, data)
                .then(function () {
                    next();
                })
                .catch(function (error) {
                    console.error(error);
                    next(error);
                });
        }else{
            next();
        }
    });

    Customerquote.beforeRemote('**', function(ctx, data, next){
        const request = ctx.req;
        if(request){
            if(request.accessToken){
                if(request.accessToken.userId){
                    dealerId = request.accessToken.userId;
                }
            }
        }
        next();
    });


   Customerquote.observe("before save", function(ctx,next){
       const instance = ctx.instance || ctx.data;
       const currentInstance = ctx.currentInstance;

       if(ctx.isNewInstance){
           instance.added = new Date();
           instance.updated = new Date();
           instance.quoteNumber = Math.floor(100000000 + Math.random() * 900000000);
       }else{
           instance.updated = new Date();
       }

     /*  const request = ctx.req;
       if(request){
           if(request.accessToken){
               if(request.accessToken.userId){
                   dealerId = request.accessToken.userId;
               }
           }
       }*/

       if(instance.quoteType === "q"){
           if(instance.ownershipType){
               instance.ownershipType = instance.ownershipType.toString().toLowerCase().trim();
               if(OWNERSHIPTYPE.indexOf(instance.ownershipType) === -1){
                   return next(new Error("OwnershipType is not valid"));
               }
           }
       }


       if(instance.quoteType){
           instance.quoteType = instance.quoteType.toString().toLowerCase().trim();
           if(QUOTETYPE.indexOf(instance.quoteType) === -1){
               return next(new Error("quotetype is not valid"));
           }
       }



       if(!validate(instance, currentInstance, "vehicleInfoId")){
           return next(new Error("Vehicle Info is required"));
       }
       if(instance.quoteType === "q"){
           if(!validate(instance, currentInstance, "cityId")){
               return next(new Error("Register City is required"));
           }
       }
       if(!validate(instance, currentInstance, "customerId")){
           return next(new Error("Customer is required"));
       }

       if(!instance.isSoldViaAutobox){
           if(instance.soldViaAutobox){
               if(instance.soldViaAutobox === "yes"){
                   const SoldViaAutobox = server.models["SoldViaAutobox"];
                   if(instance.vehicleInfoId && instance.customerId && dealerId){
                       instance.isSoldViaAutobox = true;
                       instance.dealerId = dealerId;
                       SoldViaAutobox.create({
                           type: "car",
                           vehicleInfoId : instance.vehicleInfoId,
                           customerQuoteId : instance.id,
                           dealerId : dealerId
                       })
                           .then(function(soldViaAutobox){
                               next();
                           })
                           .catch(function(error){
                               return next(error);
                           });
                   } else{
                       next();
                   }
               } else{
                   next();
               }
           } else{
               next();
           }
       } else{
           next();
       }

       //next();
   });

 /*  var sendNotificationAfterVehiclePurchase = function(){
       var rule = new schedule.RecurrenceRule();
       rule.dayOfWeek = 4;
       rule.hour = 4;
       var job = schedule.scheduleJob(rule, function(){
           console.log("Waking up to send the vehicle filling notification");
           var filter = {};
           filter.where = {};
           if(filter.where){
               if(!filter.where.purchaseStatus){
                   filter.where.purchaseStatus = "purchased";
               }
               if(!filter.where.purchaseDate){
                   filter.where.purchaseDate = {};
                   if(!filter.where.purchaseDate.lt){
                       filter.where.purchaseDate.lt = moment().subtract(3, 'w');
                   }
               }
           }
           Customerquote.find(filter)
               .then(function(customerQuoteList){
                   if(customerQuoteList){
                       if(customerQuoteList.length){
                           customerQuoteList.forEach(function(quote){
                               if(quote){
                                   if(quote.customer){
                                       if(quote.customer.registrationId){
                                           //send the notification to customer
                                           var name = quote.customer.firstName;
                                           var lastName = quote.customer.lastName? quote.customer.lastName: "";
                                           name = name + " " + lastName;
                                           var from = "Autobox";
                                           var title = "Please fill out your vehicle Details";
                                           var type = "VehicleDetails";
                                           var message = pushMessageFormat(name, type, title, quote.id);
                                           sendNotification(server, message, quote.customer.registrationId, from, function(error){
                                               if(error){
                                                   console.log(error);
                                               } else{
                                                   console.log("Push Notification send Successfully for vehicle details");
                                               }
                                           });
                                       }
                                   }
                               }
                           });
                       }
                   }
               })
               .catch(function(error){
                   console.log(error);
               });
       });
   };*/

    //sendNotificationAfterVehiclePurchase();

    var sendNotification = function(app, message, registrationId, from, callback){
        push.push(app, message, registrationId, from, callback);
    };

    var pushMessageFormat = function(to, type, title, customerQuoteId){
        var message = {
            to : to,
            type: type,
            title: title,
            customerQuoteId: customerQuoteId
        };
        return JSON.stringify(message);
    };

};
