module.exports = ( Customer, server, helper) => {
    const SECRET_KEY = "BRANDZOOMR_PASSWORD_@!%#_SNAPHY";
    //AppUser.validatesUniquenessOf('email');
    //Remove email verification..
    //delete AppUser.validations.email;
    Customer.validatesUniquenessOf('email');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Customer.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;
        //Adding password
        instance.password = SECRET_KEY;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();

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

        if(instance.email){
            instance.email = trim(instance.email);
            //Sanitize an Email..
            instance.email = normalizeEmail(instance.email);
            //Check if the max length 200
            const check = isEmail(instance.email);
            if(!check){
                return next(new Error("Email must be valid"));
            }
        }

        next();
    });


};
