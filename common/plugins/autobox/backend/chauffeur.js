/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        createChauffeurMethod();
        sendChauffeurRequestNotification();
        chauffeurReplyMethod();
        findAllChauffeurMethod();
        assignChauffeurVehicleMethod();
    };


    const createChauffeurMethod = function(){
        const Chauffeur = databaseObj.Chauffeur;
        Chauffeur.createChauffeur = createChauffeur;
        Chauffeur.remoteMethod('createChauffeur', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "chauffeurObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const chauffeurReplyMethod = function(){
      const Chauffeur = databaseObj.Chauffeur;
      Chauffeur.chauffeurReply = chauffeurReply;
      Chauffeur.remoteMethod('chaffeurReply', {
          accepts: [
              {
                  arg: 'ctx',
                  type: 'object',
                  http: {
                      source: 'context'
                  }
              },
              {
                  arg: "chaufferId", type: "string"
              },
              {
                  arg: "status", type: "string"
              }
          ],
          returns: {
              arg: "response", type: "object", root: true
          }
      });
    };

    const findAllChauffeurMethod = function(){
      const Chauffeur = databaseObj.Chauffeur;
      Chauffeur.findAll = findAllChauffeur;
      Chauffeur.remoteMethod("findAll", {
         accepts: [
             {
                 arg: 'ctx',
                 type: 'object',
                 http: {
                     source: 'context'
                 }
             },
             {
                 arg: "filter", type: "object"
             }
         ],
          returns: {
             arg: "chauffeurList", type: ["Chauffeur"], root: true
          }
      });
    };

    const assignChauffeurVehicleMethod = function(){
        const Chauffeur = databaseObj.Chauffeur;
        Chauffeur.assignChauffeurVehicle = assignChauffeurVehicle;
        Chauffeur.remoteMethod("assignChauffeurVehicle", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "chauffeurId", type: "string"
                },
                {
                    arg: "vehicleDetailId", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root : true
            }
        });
    };


    const createChauffeur = function(ctx, chauffeurObj, callback){
        const request = ctx.req;
        let chaffeurName;
        let ownerName;
        let ownerContact;
        let driverId;
        let chauffeurContact;
        if(!chauffeurObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const Chauffeur = databaseObj.Chauffeur;
                    const Customer = databaseObj.Customer;
                    Customer.findById(customerId)
                        .then(function(customer){
                            if(customer){
                                ownerContact = customer.phoneNumber;
                                ownerName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                ownerName = ownerName + " " + lastName;
                                var phoneNumber = chauffeurObj.chauffeurContact;
                                var patt = /\+\d{12,12}/,
                                    match = phoneNumber.toString().match(patt);

                                if (!match) {
                                    chauffeurContact = "+91" + phoneNumber;
                                } else{
                                    chauffeurContact = phoneNumber;
                                }
                                return Customer.findOne({
                                    where: {
                                        phoneNumber: chauffeurContact
                                    }
                                });
                            }
                        })
                        .then(function(customer){
                            if(customer){
                                chaffeurName = customer.firstName;
                                var lastName = customer.lastName? customer.lastName : "";
                                chaffeurName = chaffeurName + " " + lastName;
                                driverId = customer.id;
                                return Chauffeur.create({
                                    chauffeurContact : chauffeurObj.chauffeurContact,
                                    name : chaffeurName,
                                    status: "pending",
                                    message : "",
                                    customerId : customerId,
                                    driverId : driverId,
                                    ownerName: ownerName,
                                    ownerContact: ownerContact
                                });
                            } else{
                                callback(new Error("Customer not found"));
                            }
                        })
                        .then(function(chauffeur){
                            if(chauffeur){
                                callback(null, {response: "success"});
                            }
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
        }
    };


    const sendChauffeurRequestNotification = function(){
        const Chaffeur = databaseObj.Chauffeur;
        Chaffeur.observe("after save", function(ctx, next){
            const instance = ctx.instance;
            const chauffeurObj = instance.toJSON();
            if(ctx.isNewInstance){
                process.nextTick(function(){
                    var name = chauffeurObj.name;
                    var type = "ChauffeurRequest";
                    var from = packageObj.companyName;
                    var title = chauffeurObj.ownerName + " has sent you chauffuer request";
                    var message = chauffeurRequestMessageFormat(name, type, title, chauffeurObj.id);
                    if(chauffeurObj.driverId){
                        sendNotification(server, message, chauffeurObj.driverId, from, function(error){
                            if(error){
                                console.error(error);
                            } else{
                                console.log("Chauffeur Request Notification send successfully");
                            }
                        });
                    }

                });
            }
            next();
        });
    };


    const chauffeurReply = function(ctx, chauffeurId, status, callback){
      const request = ctx.req;
      let title;
      if(!chauffeurId && !status){
          callback(new Error("Invalid Arguments"));
      } else{
          if(request.accessToken){
              if(request.accessToken.userId){
                  const Chauffeur = databaseObj.Chauffeur;
                  Chauffeur.findById(chauffeurId)
                      .then(function(chauffeur){
                          if(chauffeur){
                            return chauffeur.updateAttribute("status", status);
                          }
                      })
                      .then(function(chaffeur){
                          if(chaffeur){
                              var name = chaffeur.ownerName;
                              var type = "ChauffeurReply";
                              var from = packageObj.companyName;
                              if(chaffeur.status === "reject"){
                                  title = chaffeur.name + " has rejected your chauffeur request";
                              } else if(chaffeur.status === "accept"){
                                  title = chaffeur.name + " has accepted your chauffeur request";
                              }
                              var message = chauffeurReplyMessage(name, type, title, chaffeur.id);
                              if(chaffeur.customerId){
                                  sendNotification(server, message, chaffeur.customerId, from, function(error){
                                      if(error){
                                          console.log(error);
                                      } else{
                                          console.log("Reject for chauffeur notification send successfully");
                                      }
                                  });
                              }
                          }
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
      }
    };

    const findAllChauffeur = function(ctx, filter, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const Chauffeur = databaseObj.Chauffeur;
                Chauffeur.find({
                    where: {
                        customerId : customerId,
                        status: "accept"
                    }
                })
                    .then(function(chaufferList){
                        if(chaufferList){
                            callback(null, chaufferList);
                        }
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

    const assignChauffeurVehicle = function(ctx, chauffeurId, vehicleDetailId, callback){
        const request = ctx.req;
        if(!chauffeurId && !vehicleDetailId){
            return callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const Chauffeur = databaseObj.Chauffeur;
                    Chauffeur.findById(chauffeurId)
                        .then(function(chauffeur){
                            if(chauffeur){
                                return chauffeur.updateAttribute("vehicleDetailId", vehicleDetailId);
                            }
                        })
                        .then(function(chauffuer){
                            if(chauffuer){
                                callback(null,{response: "success"});
                            }
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
        }
    };


    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };

    var chauffeurRequestMessageFormat = function(to, eventType, title, chaufferId){
        var message = {
            to : to,
            eventType : eventType,
            title : title,
            id : chaufferId
        }
        return JSON.stringify(message);
    };


    var chauffeurReplyMessage = function(to, eventType, title, id){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : id
        }
        return JSON.stringify(message);
    }

    return {
        init: init
    };
}