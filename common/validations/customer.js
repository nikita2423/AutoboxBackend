module.exports = ( Customer, server, helper) => {
    const SECRET_KEY = "BRANDZOOMR_PASSWORD_@!%#_SNAPHY";
    //AppUser.validatesUniquenessOf('email');
    //Remove email verification..
    delete Customer.validations.email;
    Customer.validatesUniquenessOf('phoneNumber');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const STATUS = ["active", "inactive"];

    Customer.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;
        //Adding password
        instance.password = SECRET_KEY;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.customerNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(instance.firstName){
            instance.firstName = _.capitalize(trim(instance.firstName));
            //Check if the max length 200
            const check = isLength(instance.firstName, {max: 200});
            if(!check){
                return next(new Error("FirstName must not exceed 200 words"));
            }
        }

        if(instance.lastName){
            instance.lastName = _.capitalize(trim(instance.lastName));
            //Check if the max length 200
            const check = isLength(instance.lastName, {max: 200});
            if(!check){
                return next(new Error("LastName must not exceed 200 words"));
            }
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }


       /* if(instance.email){
            instance.email = trim(instance.email);
            //Sanitize an Email..
            instance.email = normalizeEmail(instance.email);
            //Check if the max length 200
            const check = isEmail(instance.email);
            if(!check){
                return next(new Error("Email must be valid"));
            }
        }*/

       /* if(!instance.cityId){
            return next(new Error("City is required"));
        }

        if(!instance.countryId){
            return next(new Error("Country is required"));
        }

        if(!instance.workshopId){
            return next(new Error("Workshop is required"));
        }*/
      if(instance.firstName){
          if(instance.id){
              Customer.findById(instance.id)
                  .then(function(customer){
                      if(customer){
                          if(customer.firstName){
                              instance.registerStatus = "registered";
                              next();
                          } else{
                              instance.registerStatus = "notregistered";
                              next();
                          }
                      }
                  })
                  .catch(function(error){
                      next(error);
                  });
          } else{
              instance.registerStatus = "notregistered";
              next();
          }

      } else{
          next();
      }


    });


};
