/**
 * Created by nikita on 21/7/17.
 */
module.exports = (Feedback, server, helper) =>
{
    const {validate} = require("../helper/usefullMethods");
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

  Feedback.observe("before save", function(ctx, next){
      const instance = ctx.instance || ctx.data;
      const currentInstance = ctx.currentInstance;

      if(ctx.isNewInstance){
          instance.added = new Date();
          instance.updated = new Date();
      }else{
          instance.updated = new Date();
      }

      if(instance.subject){
          instance.subject = _.capitalize(trim(instance.subject));
          const check = isLength(instance.subject, {min:3, max:200});
          if(!check){
              return next(new Error("Subject length should be between 3 to 200"));
          }
      }

      if(instance.message){
          instance.message = _.capitalize(trim(instance.message));
          const check = isLength(instance.message, {min:3, max:500});
          if(!check){
              return next(new Error("Message length should be between 3 to 500"));
          }
      }

      if(!validate(instance, currentInstance, "customerId")){
          return next(new Error("Customer is required"));
      }

      next();

  });

};
