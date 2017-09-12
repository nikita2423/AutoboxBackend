/**
 * Created by nikita on 21/7/17.
 */
module.exports = ( Dealer, server, helper) => {
    //const SECRET_KEY = "BRANDZOOMR_PASSWORD_@!%#_SNAPHY";
    //AppUser.validatesUniquenessOf('email');
    //Remove email verification..
    //delete AppUser.validations.email;
    Dealer.validatesUniquenessOf('email');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');
    const {validate} = require("../helper/usefullMethods");
    const STATUS = ["active", "inactive"];

    Dealer.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;
        //Adding password
        //instance.password = SECRET_KEY;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.dealerNumber = Math.floor(100000000 + Math.random() * 900000000);
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

        if(ctx.isNewInstance) {
            if (!instance.password) {
                return next(new Error("Password is required"));
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
        if(!validate(instance, currentInstance, 'brandId')){
            return next(new Error("Brand is required"));
        }

        /* if(!validate(instance, currentInstance, 'showroomId')){
            return next(new Error("Showroom is required"));
        }

        if(!validate(instance, currentInstance, 'workshopId')){
            return next(new Error("Workshop is required"));
        }*/

       /* if(!validate(instance, currentInstance, 'areaId')){
            return next(new Error("Area is required"));
        }*/

        if(!validate(instance, currentInstance, 'cityId')){
            return next(new Error("City is required"));
        }



        next();
    });

};
