'use strict';

module.exports = function(Student, server, helper) {

    Student.validatesUniquenessOf('rollNumber');

    Student.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        } else{
            instance.updated = new Date();
        }



        next();
    });
};
