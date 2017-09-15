/**
 * Created by nikita on 10/8/17.
 */

'use strict';

module.exports = function(Sos, server, helper) {

    const {validate} = require("../helper/usefullMethods");

    Sos.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

       /* if(instance.contact1){
            if(!instance.contact1.firstContactNo){
                return next(new Error("First Contact Number is required"));
            }
        } else{
            return next(new Error("First Contact is required"));
        }*/

        if(!validate(instance, currentInstance, "customerId")){
            return next(new Error("Customer is required"));
        }

        next();

    });

};

