/**
 * Created by nikita on 10/8/17.
 */

'use strict';

module.exports = function(Currentversion, server, helper) {

   Currentversion.observe('before save', function(ctx, next){
       const instance = ctx.instance || ctx.data;
       const currentInstance = ctx.currentInstance;

       if(ctx.isNewInstance){
           instance.added = new Date();
           instance.updated = new Date();
       }else{
           instance.updated = new Date();
       }

       if(!instance.versionCode){
           return next(new Error("Version code is required"));
       }

       next();
   });
};
