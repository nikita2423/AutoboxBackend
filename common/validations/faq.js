/**
 * Created by nikita on 11/8/17.
 */

'use strict';

module.exports = function(Faq, server, hepler) {

    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Faq.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(!instance.question){
            return next(new Error("Question is required"));
        } else{
            instance.question = _.capitalize(trim(instance.question));
            const check = isLength(instance.question, {min:3, max:500});
            if(!check){
                return next(new Error("Question Length should be between 3 and 500"));
            }
        }

        if(!instance.answer){
            return next(new Error("Answer is required"));
        } else{
            instance.answer = _.capitalize(instance.answer, {min:3, max:1000});
            const check = isLength(instance.question, {min:3, max:1000});
            if(!check){
                return next(new Error("Answer Length should be between 3 and 1000"));
            }
        }

        next();

    });
};
