/**
 * Created by nikita on 3/8/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const push = helper.loadPlugin("pushService");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        sendCreateQuoteNotification();
        onCustomerSaved();
        onCompletePurchaseMethod();
    };


    const onCompletePurchaseMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.onCompletePurchase = onCompletePurchase;
        Customer.remoteMethod("onCompletePurchase", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "customerQuoteId", type: "string"
                }
            ],
            returns:{
                arg: "response", type: "Object", root: true
            }
        });
    };

    /**
     * send Notification after Customer Quote is created
     */
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


    /**
     * Send mail after the customer successfully registered
     */
    const onCustomerSaved = function(){
        const Customer = databaseObj.Customer;
        Customer.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const customerObj = instance.toJSON();
            process.nextTick(function(){
                databaseObj.City.findById(customerObj.cityId)
                    .then(function(city){
                        if(city){
                            customerObj.city = city;
                            customerObj.cityName = city.name;
                            return databaseObj.Country.findById(customerObj.countryId)
                        }
                    })
                    .then(function(country){
                        if(country){
                            customerObj.country = country;
                            customerObj.countryName = country.name;
                            return databaseObj.Workshop.findById(customerObj.workshopId)
                        }
                    })
                    .then(function(workshop){
                        if(workshop){
                            customerObj.workshop = workshop;
                            customerObj.serviceCenter = workshop.dealershipName;
                        }
                    })
                    .then(function(){
                        const subject = packageObj.customer.subject;
                        const to = [];
                        const from = packageObj.from;
                        to.push(customerObj.email);
                        emailPlugin.adminEmail.successfulRegistrationForCustomer(from, to, subject, customerObj, function (err, send) {
                            if(err){
                                console.log(err);
                            } else{
                                console.log("Email send Successfully");
                            }
                        });
                    })
                    .catch(function(error){
                        console.log(error);
                    });


            });
            next();
        });
    };


    const onCompletePurchase = function(ctx, customerQuoteId, callback){
        let customerQuoteObj;
        let email;
      if(!customerQuoteId){
          return callback(new Error("Invalid Arguments"));
      }  else{
          const request = ctx.req;
          if(request.accessToken){
              if(request.accessToken.userId){
                  const customerId = request.accessToken.userId;
                  databaseObj.CustomerQuote.findById(customerQuoteId)
                      .then(function(customerQuote){
                          if(customerQuote){
                              customerQuoteObj = customerQuote;
                              return databaseObj.Customer.findById(customerId);
                          } else{
                              callback(new Error("Customer Quote cannot be found"));
                          }
                      })
                      .then(function(customer){
                          if(customer){
                              email = customer.email;
                          }
                      })
                      .then(function(){
                         //Send email to customer for further instructions..
                          const subject = packageObj.customer.subject_complete_purchase;
                          const to = [];
                          const from = packageObj.from;
                          to.push(email);
                         /* if(customerQuoteObj.customer){
                              if(customerQuoteObj.customer.email){
                                  to.push(customerQuoteObj.customer.email);
                              }
                          }*/

                          emailPlugin.adminEmail.onCompletePurchaseForCustomer(from, to, subject,customerQuoteObj, function(err, send){
                             if(err){
                                 console.log(err);
                                 callback(err);
                             } else{
                                 console.log("Email send Successfully for complete purchase");
                                 callback(null, {
                                     response: "success"
                                 });
                             }
                          });

                      })
                      .catch(function(error){
                          callback(error);
                      });
              }
          }
      }
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
