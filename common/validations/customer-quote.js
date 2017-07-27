/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Customerquote, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const OWNERSHIPTYPE = ["individual", "corporate"];
    const QUOTETYPE = ["q", "t"];

   Customerquote.observe("before save", function(ctx,next){
       const instance = ctx.instance || ctx.data;
       const currentInstance = ctx.currentInstance;

       if(ctx.isNewInstance){
           instance.added = new Date();
           instance.updated = new Date();
           instance.quoteNumber = Math.floor(100000000 + Math.random() * 900000000);
       }else{
           instance.updated = new Date();
       }

       if(instance.ownershipType){
           instance.ownershipType = instance.ownershipType.toString().toLowerCase().trim();
           if(OWNERSHIPTYPE.indexOf(instance.ownershipType) === -1){
               return next(new Error("OwnershipType is not valid"));
           }
       }

       if(instance.quoteType){
           instance.quoteType = instance.quoteType.toString().toLowerCase().trim();
           if(QUOTETYPE.indexOf(instance.quoteType) === -1){
               return next(new Error("quotetype is not valid"));
           }
       }

       if(!validate(instance, currentInstance, "vehicleInfoId")){
           return next(new Error("Vehicle Info is required"));
       }

       if(!validate(instance, currentInstance, "cityId")){
           return next(new Error("Register City is required"));
       }

       if(!validate(instance, currentInstance, "customerId")){
           return next(new Error("Customer is required"));
       }

       next();



   });
};
