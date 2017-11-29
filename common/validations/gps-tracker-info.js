/**
 * Created by nikita on 20/9/17.
 */

module.exports = function(Gpstrackerinfo, server, helper) {

    const {validate} = require("../helper/usefullMethods");
    Gpstrackerinfo.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }
        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }
        if(!instance.status){
            instance.status = "active";
        }
        if(!instance.gpsTrackerNotification){
            instance.gpsTrackerNotification = {
                hardBraking : "on",
                hardAcceleration : "on",
                gpsDisconnect : "on",
                vehicleTowing : "on",
                overSpeeding: "on",
                accidentDetection: "on"
            };
        }


        next();

    });

};