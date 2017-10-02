/**
 * Created by nikita on 1/10/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    var init = function(){
        findAllSchoolMethod();
        findAllBusesMethod();
        saveTrackBusDetailsMethod();
        findAllTrackBusMethod();
    };

    const findAllSchoolMethod = function(){
        const School = databaseObj.School;
        School.findAll = findAllSchool;
        School.remoteMethod('findAll', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "object"
                }
            ],
            returns: {
                arg: "data", type: "object", root: true
            }
        });
    };

    const findAllBusesMethod = function(){
        const BusModel = databaseObj.BusModel;
        BusModel.findAll = findAllBuses;
        BusModel.remoteMethod('findAll', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "filter", type: "object"
                }
            ],
            returns: {
                arg : "data", type: "object", root: true
            }
        });
    };

    const saveTrackBusDetailsMethod = function(){
        const TrackBus = databaseObj.TrackBus;
        TrackBus.saveTrackBusDetails = saveTrackBusDetails;
        TrackBus.remoteMethod('saveTrackBusDetails', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "gpsCode", type: "string"
                },
                {
                    arg: "busId", type: "string"
                },
                {
                    arg: "customerObj", type: "object"
                },
                {
                    arg: "trackVehicleObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root : true
            }
        });
    };

    const findAllTrackBusMethod = function(){
        const TrackBus = databaseObj.TrackBus;
        TrackBus.findAll = findTrackBus;
        TrackBus.remoteMethod('findAll', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                }
            ],
            returns: {
                arg: "trackBusList", type: "object", root: true
            }
        });
    };

    const findAllSchool = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        if(!filter){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const School = databaseObj.School;
                    if(filter){
                        if(filter.where){
                            if(filter.where.added){
                                if(!filter.where.added.lt){
                                    filter.where.added.lt = new Date();
                                }
                            }
                        }
                    }
                    School.find(filter)
                        .then(function(schoolList){
                            if(schoolList){
                                if(schoolList.length){
                                    const school = schoolList[schoolList.length - 1];
                                    lastDate = school.added;
                                }
                            }
                            callback(null, {
                                data: schoolList,
                                cursor: lastDate
                            });
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
        }
    };

    const findAllBuses = function(ctx, filter, callback){
        const request = ctx.req;
        let lastDate;
        if(!filter){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const BusModel = databaseObj.BusModel;
                    if(filter){
                        if(filter.where){
                            if(filter.where.added){
                                if(!filter.where.added.lt){
                                    filter.where.added.lt = new Date();
                                }
                            }
                        }
                    }
                    BusModel.find(filter)
                        .then(function(busList){
                            if(busList){
                                if(busList.length){
                                    const bus = busList[busList.length - 1];
                                    lastDate = bus.added;
                                }
                            }
                            callback(null, {
                                data: busList,
                                cursor : lastDate
                            });
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
        }
    };

    const saveTrackBusDetails = function(ctx, gpsCode, busModelId, customerObj, trackVehicleObj, callback){
        const request = ctx.req;
        if(!gpsCode && !busModelId && !customerObj){
            callback(new Error("Invalid Arguments"));
        } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const BusModel = databaseObj.BusModel;
                   const Customer = databaseObj.Customer;
                   const TrackBus = databaseObj.TrackBus;
                   BusModel.findById(busModelId)
                       .then(function(bus){
                           if(bus){
                               if(bus.gpsCode === gpsCode){
                                   return Customer.findById(customerId);
                               } else{
                                   throw new Error("Code doesn't match");
                               }
                           } else{
                               throw new Error("Bus not found");
                           }
                       })
                       .then(function(customer){
                           if(customer){
                               return customer.updateAttributes(customerObj);
                           }
                       })
                       .then(function(customer){
                           if(customer){
                              return TrackBus.create({
                                   homeLocation: trackVehicleObj.homeLocation,
                                   vicinity : trackVehicleObj.vicinity,
                                   gpsCode : gpsCode,
                                   busModelId : busModelId,
                                   customerId : customerId
                               });
                           } else{
                               throw new Error("Customer not found");
                           }
                       })
                       .then(function(trackBus){
                           if(trackBus){
                               callback(null, {response: "success"});
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
        }
    };

    const findTrackBus = function(ctx, callback){
        const request = ctx.req;
        if(request.accessToken){
            if(request.accessToken.userId){
                const customerId = request.accessToken.userId;
                const TrackBus = databaseObj.TrackBus;
                TrackBus.find({
                    where: {
                        customerId : customerId
                    },
                    include: [{
                        relation: "busModel",
                        scope: {
                            include: ["school"]
                        }
                    }]
                })
                    .then(function(trackBusList){
                        if(trackBusList){
                           callback(null, trackBusList);
                        } else{
                            throw new Error("Track Bus not defined");
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
    };

    return {
        init: init
    };
};