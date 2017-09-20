/**
 * Created by nikita on 20/9/17.
 */

module.exports = function(Gpstrackerinfo,server, helper) {


    Gpstrackerinfo.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(!instance.customerId){
            return next(new Error("Customer is required"));
        }

    });

};