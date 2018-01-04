
module.exports = function(Dealervehicle, server, helper) {
    const {validate} = require("../helper/usefullMethods");

    Dealervehicle.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();
        } else{
            instance.updated = new Date();
        }

        if(!validate(instance, currentInstance, 'brandId')){
            return next(new Error("Brand is required"));
        }

        if(!validate(instance, currentInstance, 'carModelId')){
            return next(new Error("Car Model is required"));
        }

        if(!validate(instance, currentInstance, 'serialNumber')){
            return next(new Error("Serial Number is required"));
        }

        if(!validate(instance, currentInstance, 'registerNumber')){
            return next(new Error("Register Number is required"));
        }

        if(!validate(instance, currentInstance, 'deviceIMEI')){
            return next(new Error("Device IMEI is required"));
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
                        instance.modelName = carModel.name;
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
                        instance.gearboxName = gearBox.name;
                    }
                    if(instance.trimId){
                        return Trim.findById(instance.trimId);
                    }
                })
                .then(function(trim){
                    if(trim){
                        instance.trimName = trim.name;
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
    });

};
