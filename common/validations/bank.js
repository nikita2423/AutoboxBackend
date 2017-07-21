/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Bank, server, helper) =>
{
    Bank.validatesUniquenessOf('ifscCode');
    const {
        isLength,
        trim,
        isEmail,
        normalizeEmail
    } = require('validator');
    const _ = require('lodash');

    Bank.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        }else{
            instance.updated = new Date();
        }

        if(instance.name){
            instance.name = toTitleCase(instance.name.toString().trim());
            const check = isLength(instance.name, {min:3, max: 200});
            if(!check){
                return next(new Error("Bank Name is required"));
            }
        }

        if(instance.branchName){
            instance.branchName = _.capitalize(trim(instance.branchName));
            const check = isLength(instance.branchName, {min:3, max:500});
            if(!check){
                return next(new Error("Branch Name is required"));
            }
        }

        if(instance.ifscCode){
            const check = isLength(instance.ifscCode, {min:3, max:50});
            if(!check){
                return next(new Error("IFSC Code is required"));
            }
        }

    });

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    }

};
