/**
 * Created by nikita on 3/8/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        sendCreateQuoteNotification();
    };


    const sendCreateQuoteNotification = function(){
        const CustomerQuote = databaseObj.CustomerQuote;
        CustomerQuote.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const customerQuoteObj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    const Customer = databaseObj.Customer;
                    Customer.findById(customerQuoteObj.customerId)
                        .then(function(customer){
                            if(customer) {
                                customerQuoteObj.customer = customer;
                                return databaseObj.VehicleInfo.findById(customerQuoteObj.vehicleInfo);
                            }
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo) {
                                customerQuoteObj.vehicleInfo = vehicleInfo;
                            }
                        })

                        .then(function () {
                           var name = customerQuoteObj.customer.firstName + " " + customerQuoteObj.customer.lastName;
                           var type = "CustomerQuote";
                           var title = "Your Quote for model " + customerQuoteObj.vehicleInfo.vehicleModel + "has been forwarded to dealers. You can view all the quotes with corresponding dealers on My Quotes Section";
                           var id = customerQuoteObj.id;
                           var from = packageObj.companyName;
                           var message = quoteNotificationFormat(name, type, title, id);
                           if(customerQuoteObj.customer.registrationId){
                               sendNotification(server, message, customerQuoteObj.customer.registrationId, from, function(error){
                                   if(error){
                                       console.log(error);
                                   } else{
                                       console.log("Push Notification for customer quote send successfully!");
                                   }
                               });
                           }


                        })
                        .catch(function(error){
                            console.log(error);
                        });
                });
            }
            next();
        });
    };

    const sendNotification = function(app, message, registrationId, from, callback){
        push.push(app, message, registrationId, from, callback);
    };


    var quoteNotificationFormat = function(To, type, title, customerQuoteId){
        var message = {
            to : To,
            type : type,
            title : title,
            id : customerQuoteId
        };

        return JSON.stringify(message);
    };


    return{
        init : init
    };
};
