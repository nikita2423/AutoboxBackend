/**
 * Created by nikita on 25/11/17.
 */

"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const emailPlugin = helper.loadPlugin("email");

    var init = function(){
        sellYourVehicleMethod();
        sendSellVehicleEmailToAdminMethod();
        createInsuranceRenewalMethod();
        createInsuranceRenewalConfirmationMethod();
        sendInsuranceConfirmationEmail();
    };


    const sellYourVehicleMethod = function(){
        const SellVehicle = databaseObj.SellVehicle;
        SellVehicle.sellYourVehicle = sellYourVehicle;
        SellVehicle.remoteMethod("sellYourVehicle", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "vehicleInfoObj", type: "object"
                },
                {
                    arg: "sellVehicleObj", type: "object"
                }
            ],
            returns:{
                arg: "response", type: "object", root: true
            }
        });
    };

    const createInsuranceRenewalMethod = function(){
        const InsuranceRenewal = databaseObj.InsuranceRenewal;
        InsuranceRenewal.createInsuranceRenewal = createInsuranceRenewal;
        InsuranceRenewal.remoteMethod("createInsuranceRenewal", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "insuranceRenewalObj", type:"object"
                }
            ],
            returns: {
                arg: "insuranceRenewalObj", type: "InsuranceRenewal", root: true
            }
        });
    };

    const createInsuranceRenewalConfirmationMethod = function(){
        const InsuranceRenewalConfirmation = databaseObj.InsuranceRenewalConfirmation;
        InsuranceRenewalConfirmation.createInsuranceRenewalConfirmation = createInsuranceRenewalConfirmation;
        InsuranceRenewalConfirmation.remoteMethod("createInsuranceRenewalConfirmation", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "insuranceRenewalId", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });

    };

    /**
     * Use to send the request for sell vehicle..
     * @param ctx
     * @param vehicleInfoObj
     * @param sellVehicleObj
     * @param callback
     */
    const sellYourVehicle = function(ctx, vehicleInfoObj, sellVehicleObj, callback){
        const request = ctx.req;
        let vehicleInfoInstance;
        if(!vehicleInfoObj && !sellVehicleObj){
            callback("Invalid Arguments");
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const SellVehicle = databaseObj.SellVehicle;
                    const VehicleInfo = databaseObj.VehicleInfo;

                    VehicleInfo.create(vehicleInfoObj)
                    .then(function(vehicleInfo){
                        if(vehicleInfo){
                            vehicleInfoInstance = vehicleInfo;
                            return SellVehicle.create({
                                registrationNumber : sellVehicleObj.registrationNumber,
                                kilometers : sellVehicleObj.kilometers,
                                yearOfPurchase : sellVehicleObj.yearOfPurchase,
                                vehicleInfoId : vehicleInfo.id,
                                trimData : sellVehicleObj.trimData,
                                customerId : customerId
                            });
                        } else{
                            throw new Error("Error in creating vehicle info");
                        }
                    })
                        .then(function(sellVehicle){
                            if(sellVehicle){
                                callback(null, {response: "success"});
                                //send Email or notification
                            }
                        })
                        .catch(function(error){
                            vehicleInfoInstance.destroy();
                            callback(error);
                        });
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };


    const sendSellVehicleEmailToAdminMethod = function(){
        const SellVehicle = databaseObj.SellVehicle;
        SellVehicle.observe("after save", function(ctx, next){
            if(ctx.isNewInstance){
                const instance = ctx.instance;
                const sellVehicleObj = instance.toJSON();

                process.nextTick(function(){
                    databaseObj.Customer.findById(sellVehicleObj.customerId)
                        .then(function(customer){
                            if(customer){
                                sellVehicleObj.customer = customer;
                                return databaseObj.VehicleInfo.findById(sellVehicleObj.vehicleInfoId);
                            }
                        })
                        .then(function(vehicleInfo){
                            if(vehicleInfo){
                                sellVehicleObj.vehicleInfo = vehicleInfo;
                            }
                        })
                        .then(function(){
                            const subject = packageObj.admin.sell_vehicle_subject;
                            const to = [];
                            const from = packageObj.from;
                            to.push("sitaram.ankilla@truebil.com");
                            emailPlugin.adminEmail.sellvehicle(from, to, subject, sellVehicleObj, function (err, send) {
                                if(err){
                                    console.log(err);
                                } else{
                                    console.log("Email send Successfully for Sell Vehicle to admin");
                                }
                            });
                        })
                        .catch(function(error){
                            console.log(error);
                        });
                });
            }
            next();
        });
    };

    /**
     * Use to create Insurance Renewal for vehicle
     * @param ctx
     * @param insuranceRenewalObj
     * @param callback
     */
    const createInsuranceRenewal = function(ctx, insuranceRenewalObj, callback){
        const request = ctx.req;
        const InsuranceRenewal = databaseObj.InsuranceRenewal;
        if(!insuranceRenewalObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        InsuranceRenewal.create({
                            yearOfPurchase : insuranceRenewalObj.yearOfPurchase,
                            typeOfInsurance : insuranceRenewalObj.typeOfInsurance,
                            noClaimBonus : insuranceRenewalObj.noClaimBonus,
                            carModelId : insuranceRenewalObj.carModelId,
                            vehicleDetailId : insuranceRenewalObj.vehicleDetailId,
                            customerId : customerId,
                            modelName : insuranceRenewalObj.modelName
                        })
                            .then(function(insuranceRenewal){
                                if(insuranceRenewal){
                                    callback(null, insuranceRenewal);
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else{
                        callback(new Error("User not valid"));
                    }
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    /**
     * Use to send the request for insurance renewal confirmation
     * @param ctx
     * @param insuranceRenewalId
     * @param callback
     */
    const createInsuranceRenewalConfirmation = function(ctx, insuranceRenewalId, callback){
        const request = ctx.req;
        if(!insuranceRenewalId){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request){
                if(request.accessToken){
                    if(request.accessToken.userId){
                        const customerId = request.accessToken.userId;
                        const InsuranceRenewalConfirmation = databaseObj.InsuranceRenewalConfirmation;
                        InsuranceRenewalConfirmation.create({
                            insuranceRenewalId : insuranceRenewalId,
                            customerId : customerId
                        })
                            .then(function(insuranceRenewalConfirmation){
                                if(insuranceRenewalConfirmation){
                                    //send Email
                                    callback(null, insuranceRenewalConfirmation);
                                }
                            })
                            .catch(function(error){
                                callback(error);
                            });
                    } else{
                        callback(new Error("User not valid"));
                    }
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };


    const sendInsuranceConfirmationEmail = function(){
        const InsuranceRenewalConfirmation = databaseObj.InsuranceRenewalConfirmation;
        InsuranceRenewalConfirmation.observe("before save", function(ctx, next){
            if(ctx.isNewInstance){
                const instance = ctx.instance || ctx.data;
                const insuranceRenewalConfirmObj = instance.toJSON();
                process.nextTick(function(){
                    databaseObj.InsuranceRenewal.findById(insuranceRenewalConfirmObj.insuranceRenewalId)
                        .then(function(insuranceRenewal){
                            if(insuranceRenewal){
                                insuranceRenewalConfirmObj.insuranceRenewal = insuranceRenewal;
                            }
                            return databaseObj.Customer.findById(insuranceRenewalConfirmObj.customerId);
                        })
                        .then(function(customer){
                            if(customer){
                                insuranceRenewalConfirmObj.customer = customer;
                            }
                        })
                        .then(function(){
                            const subject = packageObj.admin.insurance_renewal_request_arrived;
                            const to = [];
                            const from = packageObj.from;
                            to.push("sales@autoboxapp.in");
                            emailPlugin.adminEmail.insuranceRenewalEmail(from, to, subject, insuranceRenewalConfirmObj, function (err, send) {
                                if(err){
                                    console.log(err);
                                } else{
                                    console.log("Email send Successfully for Insurance Renewal to admin");
                                }
                            });
                        });
                });
            }
            next();
        });
    };



    return {
        init: init
    };
};