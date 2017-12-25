/**
 * Created by nikita on 18/12/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const Promise = require("bluebird");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        incrementShareAppCountMethod();
        syncContactMethod();
        incrementPointsMethod();

    };

    const incrementShareAppCountMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.incrementShareAppCount = incrementShareAppCount;
        Customer.remoteMethod("incrementShareAppCount", {
           accepts: [
               {
                   arg: 'ctx',
                   type: 'object',
                   http: {
                       source: 'context'
                   }
               },
               {
                   arg: "contactNumber", type: "string"
               }
           ],
            returns: {
               arg: "response", type: "object", root: true
            }
        });
    };

    const incrementShareAppCount = function(ctx, contactNumber, callback){
        const request = ctx.req;
        if(!contactNumber){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                }
            }
        }
    };

    const syncContactMethod = function(){
        const CustomerContact = databaseObj.CustomerContact;
        CustomerContact.syncContact = syncContact;
        CustomerContact.remoteMethod("syncContact", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "contactList", type: ["CustomerContact"]
                }
            ],
            returns: {
                arg: "response", type: "object", root : true
            }
        });
    };



    /**
     * To sync all the android contacts with backend
     * @param ctx
     * @param contactList
     * @param callback
     */
    const syncContact = function(ctx, contactList, callback){
        const request = ctx.req;
        let promises = [];
        if(!contactList){
            callback(new Error("Contact List is required"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId  = request.accessToken.userId;
                    const CustomerContact = databaseObj.CustomerContact;
                    const Customer = databaseObj.Customer;
                    if(contactList){
                        if(contactList.length){
                            contactList.forEach(function(customerContact){
                                (function(customerContact){
                                    promises.push(
                                        new Promise(function(resolve, reject){
                                            if(customerContact){
                                                if(customerContact.contactNumber){
                                                   /* customerContact.contactNumber = customerContact.contactNumber.toString();
                                                    var patt = /\+\d{12,12}/;
                                                    var match = customerContact.contactNumber.match(patt);

                                                    if (!match) {
                                                        customerContact.contactNumber = "+91" + customerContact.contactNumber;
                                                    }*/
                                                }

                                                CustomerContact.findOne({
                                                    where: {
                                                        contactNumber : customerContact.contactNumber,
                                                        customerId : customerId
                                                    }
                                                })
                                                    .then(function(customerContactData){
                                                        if(customerContactData){

                                                        } else{
                                                            return CustomerContact.create({
                                                                name : customerContact.name,
                                                                contactNumber : customerContact.contactNumber,
                                                                customerId : customerId
                                                            });
                                                        }
                                                    })

                                                    .then(function(){
                                                        resolve();
                                                    })
                                                    .catch(function(error){
                                                        reject(error);
                                                    });
                                            } else{
                                                resolve();
                                            }
                                        })
                                    );
                                })(customerContact);
                            });
                            Promise.all(promises)
                                .then(function(){
                                    return Customer.findById(customerId);
                                })
                                .then(function(customer){
                                    if(customer){
                                        return customer.updateAttribute("isContactSynced", true);
                                    }
                                })
                                .then(function(customer){
                                    callback(null, {response: "success"});
                                })
                                .catch(function(error){
                                    callback(error);
                                });
                        } else{
                            callback(null, {response: "Contact List is required"});
                        }
                    } else{
                        callback(null, {response: "Contact List is required"});
                    }
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };


    const incrementPointsMethod = function(){
        const Customer = databaseObj.Customer;
        Customer.incrementPoints = incrementPoints;
        Customer.remoteMethod("incrementPoints", {
            accepts : [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "phoneNumber", type: "string"
                },
            ],
            returns : {
                arg: "response", type: "object", root : true
            }
        });
    };


    const incrementPoints = function(ctx, phoneNumber, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const CustomerContact = databaseObj.CustomerContact;
                const Customer = databaseObj.Customer;
                let promises = [];

                CustomerContact.find({
                    where: {
                        contactNumber : phoneNumber
                    }
                })
                    .then(function(customerContactList){
                        if(customerContactList){
                            if(customerContactList.length){
                                customerContactList.forEach(function(customerContact){
                                    (function(customerContact){
                                        promises.push(
                                            new Promise(function(resolve, reject){
                                                Customer.findById(customerContact.customerId)
                                                    .then(function(customer){
                                                        if(customer){
                                                          customer.earnedPoints = customer.earnedPoints + packageObj.new_install_point;
                                                          return customer.updateAttribute("earnedPoints", customer.earnedPoints);
                                                        }
                                                    })
                                                    .then(function(customer){
                                                        if(customer){
                                                            const to = customer.firstName + " " + customer.lastName? customer.lastName : "";
                                                            const pushFrom = packageObj.companyName;
                                                            const title = "You have earned " + packageObj.new_install_point + "through app install by " + customerContact.name;
                                                            const type = "New Install Points";
                                                            const instanceId = customer.id;
                                                            const message = newAppInstallMessage(to, type, title, instanceId);
                                                            sendNotification(server, message, customer.id, pushFrom, function(error){
                                                                if(error){
                                                                   reject(error);
                                                                } else{
                                                                    resolve("Push Notification for new install send successfully");
                                                                }
                                                            });
                                                        } else{
                                                            resolve();
                                                        }
                                                    })
                                                    .catch(function(error){
                                                        reject(error);
                                                    });
                                            })

                                        );
                                    })(customerContact);
                                });
                                return Promise.all(promises);

                            }
                        }
                    })
                    .then(function(){
                        callback(null, {response: "success"});
                    })
                    .catch(function(error){
                        callback(error);
                    });
            } else{
                callback(new Error("User not valid"));
            }
        } else{
            callback(new Error("User not valid"));
        }
    };

    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };

    var newAppInstallMessage = function(to, eventType, title, customerId){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : customerId
        };
        return JSON.stringify(message);
    };

    return {
        init: init
    };
};
