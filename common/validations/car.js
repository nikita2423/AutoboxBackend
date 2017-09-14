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

        if(instance.brandId){
            const Brand = server.models["Brand"];
            const CarModel = server.models["CarModel"];
            const Fuel = server.models["Fuel"];
            const GearBox = server.models["GearBox"];
            const Trim = server.models["Trim"];
            Brand.findById(instance.brandId)
                .then(function(brand){
                    if(brand){
                        instance.brandName = brand.name;
                    }
                    if(instance.carModelId){
                        return CarModel.findById(instance.carModelId);
                    }
                })
                .then(function(carModel){
                    if(carModel){
                        instance.carModelName = carModel.name;
                    }
                    if(instance.fuelId){
                        return Fuel.findById(instance.fuelId);
                    }
                })
                .then(function(fuel){
                    if(fuel){
                        instance.fuelName = fuel.name;
                    }
                    if(instance.gearBoxId){
                        return GearBox.findById(instance.gearBoxId);
                    }
                })
                .then(function(gearBox){
                    if(gearBox){
                        instance.gearBoxName = gearBox.name;
                    }
                    if(instance.trimId){
                        return Trim.findById(instance.trimId);
                    }
                })
                .then(function(trim){
                    if(trim){
                        instance.variantName = trim.name;
                    }
                })
                .then(function(){
                    next();
                })
                .catch(function(error){
                    next(error);
                });
        } else{
            next();
        }

       /* if(!validate(instance, currentInstance, 'colors_')){
            return next(new Error("Colors is required"));
        }*/

       /* if(!validate(instance, currentInstance, 'carModelId')){
            return next(new Error("Car Model is required"));
        }*/

        /*if(!validate(instance, currentInstance, 'fuelId')){
            return next(new Error("Fuel is required"));
        }*/

       /* if(!validate(instance, currentInstance, 'trimId')){
            return next(new Error("Trim is required"));
        }*/


       /* if(!validate(instance, currentInstance, 'gearBoxId')){
            return next(new Error("GearBox is required"));
        }*/


      /*  if(!instance.colors){
            return next(new Error("Model is required"));
        }*/



    });
};
