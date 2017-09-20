/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        sendChauffeurRequestNotification();

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



    return{
        init : init
    };
};