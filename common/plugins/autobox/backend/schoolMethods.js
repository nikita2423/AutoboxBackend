/**
 * Created by nikita on 1/10/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const process = require("process");
    const async = require("async");
    const push = helper.loadPlugin("pushService");

    var init = function(){
        findAllSchoolMethod();
        findAllBusesMethod();
        saveTrackBusDetailsMethod();
        findAllTrackBusMethod();
        deleteTrackBusVehicleMethod();
        updateTrackBusDetailMethod();
        fetchBusLocationMethod();
        fetchBusNotificationStatusMethod();
        updateBusNotificationMethod();
        sendBusVicinityNotification();

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
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.saveTrackBusDetails = saveTrackBusDetails;
        TrackBusVehicle.remoteMethod('saveTrackBusDetails', {
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
                    arg: "trackVehicleObj", type: "object"
                }
            ],
            returns: {
                arg: "response", type: "object", root : true
            }
        });
    };

    const findAllTrackBusMethod = function(){
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.findAll = findTrackBus;
        TrackBusVehicle.remoteMethod('findAll', {
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
                arg: "trackBusList", type: ["TrackBusVehicle"], root: true
            }
        });
    };

    const deleteTrackBusVehicleMethod = function(){
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.deleteTrackBusVehicle = deleteTrackBusVehicle;
        TrackBusVehicle.remoteMethod('deleteTrackBusVehicle', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "trackBusVehicleId", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        })
    };

    const updateTrackBusDetailMethod = function(){
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.updateTrackBusDetail = updateTrackBusDetail;
        TrackBusVehicle.remoteMethod('updateTrackBusDetail', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "trackBusVehicleObj", type: "object"
                }
            ],
            returns: {
                arg: "trackBusVehicle", type: "TrackBusVehicle", root: true
            }
        })
    };

    const fetchBusLocationMethod = function(){
        const BusModel = databaseObj.BusModel;
        BusModel.fetchBusLocation = fetchBusLocation;
        BusModel.remoteMethod("fetchBusLocation", {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "deviceIMEI", type: "string"
                }
            ],
            returns: {
                arg:  "gpsPacketData", type: "object", root : true
            }
        })
    };

    const fetchBusNotificationStatusMethod = function(){
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.fetchBusNotificationStatus = fetchBusNotificationStatus;
        TrackBusVehicle.remoteMethod("fetchBusNotificationStatus", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "deviceIMEI", type: "string"
                }
            ],
            returns: {
                arg: "trackBusVehicleObj", type: "TrackBusVehicle", root : true
            }
        })
    };

    const updateBusNotificationMethod = function(){
        const TrackBusVehicle = databaseObj.TrackBusVehicle;
        TrackBusVehicle.updateBusNotification = updateBusNotification;
        TrackBusVehicle.remoteMethod("updateBusNotification", {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "deviceIMEI", type: "string"
                },
                {
                    arg: "notificationObj", type: "object"
                }
            ],
            returns: {
                arg: "trackBusVehicleObj", type: "TrackBusVehicle", root : true
            }
        })
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

    const saveTrackBusDetails = function(ctx, gpsCode, busModelId, trackVehicleObj, callback){
        const request = ctx.req;
        if(!gpsCode && !busModelId && !trackVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const BusModel = databaseObj.BusModel;
                   const TrackBusVehicle = databaseObj.TrackBusVehicle;
                   BusModel.findById(busModelId)
                       .then(function(bus){
                           if(bus){
                               if(bus.gpsCode === gpsCode){
                                   return TrackBusVehicle.create({
                                       homeLocation: trackVehicleObj.homeLocation,
                                       homeAddress: trackVehicleObj.homeAddress,
                                       vicinity : trackVehicleObj.vicinity,
                                       gpsCode : gpsCode,
                                       busModelId : busModelId,
                                       customerId : customerId,
                                       deviceIMEI : bus.deviceIMEI
                                   });
                               } else{
                                   throw new Error("Code doesn't match");
                               }
                           } else{
                               throw new Error("Bus not found");
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
                const TrackBusVehicle = databaseObj.TrackBusVehicle;
                TrackBusVehicle.find({
                    where: {
                        customerId : customerId,
                        status: "active"
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

    const deleteTrackBusVehicle = function(ctx, trackBusVehicleId, callback){
        const request = ctx.req;
        if(!trackBusVehicleId){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const TrackBusVehicle = databaseObj.TrackBusVehicle;
                    TrackBusVehicle.findById(trackBusVehicleId)
                        .then(function(trackBusVehicle){
                            if(trackBusVehicle){
                                trackBusVehicle.updateAttribute("status", "inactive");
                            } else{
                                throw new Error("Track Bus not found");
                            }
                        })
                        .then(function(trackBusVehicle){
                            callback(null, {response: "success"});
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const updateTrackBusDetail = function(ctx, trackBusVehicleObj, callback){
        const request = ctx.req;
        if(!trackBusVehicleObj){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const TrackBusVehicle = databaseObj.TrackBusVehicle;
                    TrackBusVehicle.upsert(trackBusVehicleObj)
                        .then(function(trackBusVehicle){
                            if(trackBusVehicle){
                                callback(null, trackBusVehicle);
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const fetchBusLocation = function(ctx, deviceIMEI, callback){
        const request = ctx.req;
        if(!deviceIMEI){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const GpsPacketData = databaseObj.GpsPacketData;
                    GpsPacketData.findOne({
                        where: {
                            deviceIMEI : deviceIMEI
                        },
                        order : ["added DESC"]
                    })
                        .then(function(gpsPacketData){
                            if(gpsPacketData){
                                callback(null, {data: gpsPacketData});
                            } else{
                                callback(null, {});
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const fetchBusNotificationStatus = function(ctx, deviceIMEI, callback){
        const request = ctx.req;
        if(!deviceIMEI){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const TrackBusVehicle = databaseObj.TrackBusVehicle;
                    TrackBusVehicle.findOne({
                        where: {
                            customerId : customerId,
                            status : "active",
                            deviceIMEI : deviceIMEI
                        }
                    })
                        .then(function(trackBusVehicle){
                            if(trackBusVehicle){
                                callback(null, trackBusVehicle);
                            } else{
                                callback(null,{});
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const updateBusNotification = function(ctx, deviceIMEI, notificationObj, callback){
        const request = ctx.req;
        if(!deviceIMEI){
            callback(new Error("Invalid Arguments"));
        } else{
            if(request.accessToken){
                if(request.accessToken.userId){
                    const customerId = request.accessToken.userId;
                    const TrackBusVehicle = databaseObj.TrackBusVehicle;
                    TrackBusVehicle.findOne({
                        where: {
                            customerId : customerId,
                            deviceIMEI : deviceIMEI,
                            status : "active"
                        }
                    })
                        .then(function(trackBusVehicle){
                            if(trackBusVehicle){
                                return trackBusVehicle.updateAttribute("gpsBusNotification", notificationObj);
                            }
                        })
                        .then(function(trackBusVehicle){
                            if(trackBusVehicle){
                                callback(null, trackBusVehicle);
                            } else{
                                callback(null, {});
                            }
                        })
                        .catch(function(error){
                            callback(error);
                        })
                } else{
                    callback(new Error("User not valid"));
                }
            } else{
                callback(new Error("User not valid"));
            }
        }
    };

    const sendBusVicinityNotification = function(){
      const GpsPacketData = databaseObj.GpsPacketData;
      GpsPacketData.observe("after save", function(ctx, next){
          const instance = ctx.instance;
          const gpsPacketdataObj = instance.toJSON();
          let promises = [];
          let gpsLatLang;
          if(ctx.isNewInstance){
              process.nextTick(function(){
                  databaseObj.BusModel.findOne({
                      where: {
                          deviceIMEI : gpsPacketdataObj.deviceIMEI
                      }
                  })
                      .then(function(busModel){
                          if(busModel){
                              return databaseObj.TrackBusVehicle.find({
                                  where: {
                                      busModelId : busModel.id
                                  }
                              })
                          }
                      })
                      .then(function(trackBusVehicleList){
                          if(trackBusVehicleList){
                              if(trackBusVehicleList.length){
                                  const gpsLatitude = gpsPacketdataObj.latitude;
                                  const gpsLongitude = gpsPacketdataObj.longitude;
                                  gpsLatLang = [gpsLatitude, gpsLongitude];
                                  trackBusVehicleList.forEach(function(trackBusVehicle){
                                      if(trackBusVehicle){
                                          promises.push(function(callback){
                                              databaseObj.TrackBusVehicle.findOne({
                                                  where: {
                                                      homeLocation: {
                                                          near: gpsLatLang,
                                                          maxDistance: 1,
                                                          unit: 'kilometers'
                                                      }
                                                  }
                                              })
                                                  .then(function(trackBusVehicle){
                                                      if(trackBusVehicle){
                                                         const to = trackBusVehicle.homeAddress;
                                                         const type = "BusVicinity";
                                                         const title = "Bus in Vicinity";
                                                         const instanceId = trackBusVehicle.id;
                                                         const from = packageObj.companyName;
                                                         var message = busVicinityNotificationFormat(to, type, title, instanceId);
                                                         if(trackBusVehicle.customerId && trackBusVehicle.gpsBusNotification["busVicinity"] === "on"){
                                                             sendNotification(server, message, trackBusVehicle.customerId, from, function(error){
                                                                 if(error){
                                                                     server.logger.error(error);
                                                                 } else{
                                                                     server.logger.info("Bus In Vicinity send Successfully");
                                                                 }
                                                             })
                                                         }
                                                      }
                                                      callback(null);
                                                  })
                                                  .catch(function(error){
                                                      callback(error);
                                                  })
                                          })

                                      }
                                  });

                                  async.series(promises, function(error){
                                      if(error){
                                          server.logger.error(error);
                                      } else{
                                          server.logger.info("Bus Vicinity for all bus send successfully");
                                      }
                                  })
                              }
                          }
                      })
                      .catch(function(error){
                          server.logger.error(error);
                      });
              })
          }
          next();
      })
    };

    var busVicinityNotificationFormat = function(to, eventType, title, id){
        var message = {
            to : to,
            type : eventType,
            title : title,
            id : id
        };
        return JSON.stringify(message);
    };
    const sendNotification = function(app, message, id, from, callback){
        //push.push(app, message, id, from, callback);
        push.notifyByUserId(message, id, from, callback);
    };

    return {
        init: init
    };
};