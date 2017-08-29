/**
 * Created by nikita on 20/7/17.
 */
module.exports = (Car, server, helper) =>
{
    Car.validatesUniquenessOf('carNumber');
    const {validate} = require("../helper/usefullMethods");
    const STATUS = ["active", "inactive"];

    Car.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
            instance.carNumber = Math.floor(100000000 + Math.random() * 900000000);
        }else{
            instance.updated = new Date();
        }

        if(instance.status){
            instance.status = instance.status.toString().toLowerCase().trim();
            if(STATUS.indexOf(instance.status) === -1){
                return next(new Error("Status is not valid"));
            }
        }


        if(!validate(instance, currentInstance, 'brandId')){
            return next(new Error("Brand is required"));
        }
/*

        if(!validate(instance, currentInstance, 'colors_')){
            return next(new Error("Colors is required"));
        }
*/

        if(!validate(instance, currentInstance, 'carModelId')){
            return next(new Error("Car Model is required"));
        }

        if(!validate(instance, currentInstance, 'fuelId')){
            return next(new Error("Fuel is required"));
        }

        if(!validate(instance, currentInstance, 'trimId')){
            return next(new Error("Trim is required"));
        }


        if(!validate(instance, currentInstance, 'gearBoxId')){
            return next(new Error("GearBox is required"));
        }


      /*  if(!instance.colors){
            return next(new Error("Model is required"));
        }*/

      next();

    });
};
