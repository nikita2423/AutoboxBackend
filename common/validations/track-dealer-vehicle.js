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
        next();
    });
};
