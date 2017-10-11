'use strict';

module.exports = function(Trackdealervehicle, server, helper) {

    Trackdealervehicle.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }
        if(!instance.status){
            instance.status = "active";
        }
        if(!instance.gpsBusNotification){
            instance.gpsBusNotification = {
                hardBraking : "on",
                hardAcceleration : "on",
                gpsDisconnect : "on",
                overSpeeding: "on"
            };
        }
        next();
    });
};
