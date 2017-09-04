/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Carmodel, server, helper) =>
{
    //Carmodel.validatesUniquenessOf('name');
    Carmodel.validatesUniquenessOf('modelNumber');
    const {
        isLength,
        trim
    } = require('validator');
    const _ = require('lodash');
    const STATUS = ["active", "inactive"];

    Carmodel.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.modelNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(instance.name){
            instance.name = instance.name.toString().trim();
            const check = isLength(instance.name, {min: 2, max: 200});
            if(!check){
                return next(new Error("Model Name cannot exceed more than 200 words"));
            }
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }

        next();

    });

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }
};
