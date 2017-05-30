'use strict';
module.exports = ( Employee, server, helper) => {
    //Write the employee validation..method here..
    Employee.validatesUniquenessOf('username');
    Employee.validatesUniquenessOf('email');
    Employee.beforeRemote( "login", function( ctx, method, next) {
        if(ctx.req.body.username){
            ctx.req.body.username = ctx.req.body.username.toLowerCase();
        }
        next();
    });

    //Testing custom methods..
    Employee.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;
        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();

        }else{
            instance.updated = new Date();
        }

        if(instance.username){
            instance.username = instance.username.toLowerCase().trim();
        }
    });
};

