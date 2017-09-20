/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    var init = function(){
        createChauffeurMethod();
        chauffeurReplyMethod();
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


    const createChauffeur = function(ctx, chauffeurObj, callback){
        const request = ctx.req;
        let chaffeurName;
        let ownerName;
        let ownerContact;
        let driverId;
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
                                return Customer.findOne({
                                    where: {
                                        phoneNumber: chauffeurObj.chauffeurContact
                                    }
                                })
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
                                })
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

    const chauffeurReply = function(ctx, chauffeurId, status, callback){
      const request = ctx.req;
      if(!chaufferId && !status){
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
                              var title = chaffeur.name ;
                              if(chaffeur.status === "reject"){

                              } else if(chaffeur.status === "accept"){

                              }

                          }
                      })
              }
          }
      }
    };

    return {
        init: init
    };
}