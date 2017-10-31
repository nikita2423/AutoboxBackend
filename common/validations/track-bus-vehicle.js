'use strict';

module.exports = function(Trackbusvehicle, server, helper) {

    Trackbusvehicle.observe("before save", function(ctx, next){
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
                busVicinity : "on"
            };
        }
        if(!instance.busNotification){
            instance.busNotification = "normal";
        }
        next();
    });

};
