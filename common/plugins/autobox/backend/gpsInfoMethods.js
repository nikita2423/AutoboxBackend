/**
 * Created by nikita on 20/9/17.
 */
"use strict";
module.exports = function( server, databaseObj, helper, packageObj) {

    const async = require("async");
    const process = require("process");
    const send = helper.loadPlugin("smsService");

    var init = function(){
        createGpsPacketDataMethod();
        createGpsTrackerInfoMethod();
        findAllGpsInfoMethod();
        deleteGpsInfoMethod();
        fetchGpsInfoDetailsMethod();
        findAllGpsNotificationMethod();
        findAllGpsPacketDataInfoMethod();
        fetchGpsNotificationDataMethod();
        updateGpsNotificationDataMethod();
        resetGpsTrackerInfoPinMethod();
        findGpsPacketDataMethod();
        sendGpsActivationSms();
        resendGpsActivationSmsMethod();
        findAllGpsTrackerMethod();
    };

    const createGpsPacketDataMethod = function(){
        const GpsPacketData = databaseObj.GpsPacketData;
        GpsPacketData.createGpsPacketData = createGpsPacketData;
        GpsPacketData.remoteMethod('createGpsPacketData', {
            accepts: [
                {
                    arg: "gpsPacketDataObj", type: "object"
                }
            ],
            returns: {
                arg: "data", type: "object", root: true
            }
        });
    };

    const createGpsTrackerInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.saveGpsTrackerInfo = saveGpsTrackerInfo;
        GpsTrackerInfo.remoteMethod('saveGpsTrackerInfo', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "gpsTrackerInfoObj", type: "object"
                }
            ],
            returns: {
                arg: "data", type: "object", root: true
            }
        });
    };

    const findAllGpsInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.findAll = findAllGpsInfo;
        GpsTrackerInfo.remoteMethod('findAll', {
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
                },
                {
                    arg: "lastDate", type: "string"
                }
            ],
            returns: {
                arg: "data", type: "object", root: true
            }
        });
    };

    const deleteGpsInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.deleteGpsInfo = deleteGpsInfo;
        GpsTrackerInfo.remoteMethod('deleteGpsInfo', {
            accepts:[
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "gpsInfoId", type: "string"
                }
            ],
            returns: {
                arg: "response", type: "object", root: true
            }
        });
    };

    const fetchGpsInfoDetailsMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.fetchGpsInfoDetail = fetchGpsInfoDetail;
        GpsTrackerInfo.remoteMethod('fetchGpsInfoDetail', {
            accepts : [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: 'deviceIMEI', type: "string"
                }
            ],
            returns: {
                arg: "gpsTrackerInfoObj", type: "object", root : true
            }
        });
    };

    const findAllGpsNotificationMethod = function(){
        const GpsNotification = databaseObj.GpsNotification;
        GpsNotification.findAll = findAllGpsNotification;
        GpsNotification.remoteMethod('findAll', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg: "lastDate", type: "string"
                },
                {
                    arg: "deviceIMEI", type: "string"
                },
                {
                    arg: "limit", type: "number"
                }
            ],
            returns: {
                arg: "gpsPacketDataObj", type: "object", root: true
            }
        });
    };

    const findAllGpsPacketDataInfoMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.findAllGpsPacketDataInfo = findAllGpsPacketDataInfo;
        GpsTrackerInfo.remoteMethod('findAllGpsPacketDataInfo', {
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
                arg: "gpsPacketDataObj", type: "object", root: true
            }
        });
    };

    const fetchGpsNotificationDataMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.fetchGpsNotificationData = fetchGpsNotificationData;
        GpsTrackerInfo.remoteMethod('fetchGpsNotificationData', {
            accepts: [
                {
                    arg: 'ctx',
                    type: 'object',
                    http: {
                        source: 'context'
                    }
                },
                {
                    arg:"deviceIMEI", type: "string"
                }
            ],
            returns: {
                arg: "gpsTrackerInfoObj", type: "GpsTrackerInfo", root: true
            }
        });
    };

    const updateGpsNotificationDataMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.updateGpsNotificationData = updateGpsNotificationData;
        GpsTrackerInfo.remoteMethod('updateGpsNotificationData', {
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
                },
                {
                    arg: "gpsTrackerNotification", type: "object"
                }
            ],
            returns: {
                arg:  "gpsTrackerInfo", type: "GpsTrackerInfo", root : true
            }
        });
    };

    const resetGpsTrackerInfoPinMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.resetGpsTrackerInfoPin = resetGpsTrackerInfoPin;
        GpsTrackerInfo.remoteMethod('resetGpsTrackerInfoPin', {
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
                },
                {
                    arg: "oldGpsCode", type: "string"
                },
                {
                    arg: "newGpsCode", type: "string"
                }
            ],
            returns: {
                arg: 'response', type: 'object', root : true
            }
        });
    };

    const findGpsPacketDataMethod = function(){
        const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
        GpsTrackerInfo.findGpsPacketData = findGpsPacketData;
        GpsTrackerInfo.remoteMethod('findGpsPacketData', {
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
            returns:{
                arg:'response', type: 'object', root:true
            }
        });
    };

    const createGpsPacketData = function(gpsPacketDataObj, callback){
        const GpsPacketData = databaseObj.GpsPacketData;
        if(!gpsPacketDataObj){
            callback(new Error("Invalid Arguments"));
        } else{
            GpsPacketData.create(gpsPacketDataObj)
                .then(function(gpsPacketData){
                    if(gpsPacketData){
                        callback(null, {response: "success"});
                        server.logger.info("Gps Packet Data Successfully created");
                    }
                })
                .catch(function(error){
                    callback(error);
                    server.logger.error("Error in creating Gps Packet Data");
                });
        }
    };


   const saveGpsTrackerInfo = function(ctx, gpsTrackerInfoObj, callback){
     const request = ctx.req;
     if(!gpsTrackerInfoObj){
         callback(new Error("Invalid Arguments"));
     } else{
         if(request.accessToken){
             if(request.accessToken.userId){
                 const customerId = request.accessToken.userId;
                 const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                 var count = 0;
                 let gpsTrackerInfoInstance;
                 GpsTrackerInfo.find({
                     where: {
                         deviceIMEI: gpsTrackerInfoObj.deviceIMEI
                     }
                 })
                     .then(function(gpsTrackerInfoList){
                         if(gpsTrackerInfoList){
                             if(gpsTrackerInfoList.length){
                                 if(gpsTrackerInfoList[0].gpsPassword.toString() === gpsTrackerInfoObj.gpsPassword.toString()){
                                     gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                         if(JSON.stringify(gpsTrackerInfo.customerId) === JSON.stringify(customerId)){
                                             count = count + 1;
                                             gpsTrackerInfoInstance = gpsTrackerInfo;
                                         }
                                     });
                                     if(count>0){
                                         //upsert
                                         return GpsTrackerInfo.upsert({
                                             deviceIMEI : gpsTrackerInfoInstance.deviceIMEI,
                                             gpsPassword : gpsTrackerInfoInstance.gpsPassword,
                                             registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                             gpsTrackerSimNumber : gpsTrackerInfoObj.gpsTrackerSimNumber,
                                             modelName : gpsTrackerInfoObj.modelName,
                                             status : gpsTrackerInfoInstance.status,
                                             added: gpsTrackerInfoInstance.added,
                                             updated: gpsTrackerInfoInstance.updated,
                                             customerId: gpsTrackerInfoInstance.customerId,
                                             vehicleDetailId : gpsTrackerInfoObj.vehicleDetailId,
                                             id : gpsTrackerInfoInstance.id
                                         });
                                     } else{
                                         //create
                                         return GpsTrackerInfo.create({
                                             deviceIMEI : gpsTrackerInfoObj.deviceIMEI,
                                             registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                             gpsTrackerSimNumber : gpsTrackerInfoObj.gpsTrackerSimNumber,
                                             modelName : gpsTrackerInfoObj.modelName,
                                             gpsPassword : gpsTrackerInfoObj.gpsPassword,
                                             vehicleDetailId : gpsTrackerInfoObj.vehicleDetailId,
                                             customerId : customerId
                                         });
                                     }
                                 } else{
                                     //unauthorized
                                   callback(new Error("Pin doesn't match"));
                                 }
                             } else{
                                 //create
                                 return GpsTrackerInfo.create({
                                     deviceIMEI : gpsTrackerInfoObj.deviceIMEI,
                                     registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                     gpsTrackerSimNumber : gpsTrackerInfoObj.gpsTrackerSimNumber,
                                     modelName : gpsTrackerInfoObj.modelName,
                                     gpsPassword : gpsTrackerInfoObj.gpsPassword,
                                     vehicleDetailId : gpsTrackerInfoObj.vehicleDetailId,
                                     customerId : customerId
                                 });
                             }
                         } else{
                             //create
                             return GpsTrackerInfo.create({
                                 deviceIMEI : gpsTrackerInfoObj.deviceIMEI,
                                 registrationNumber : gpsTrackerInfoObj.registrationNumber,
                                 gpsTrackerSimNumber : gpsTrackerInfoObj.gpsTrackerSimNumber,
                                 modelName : gpsTrackerInfoObj.modelName,
                                 gpsPassword : gpsTrackerInfoObj.gpsPassword,
                                 vehicleDetailId : gpsTrackerInfoObj.vehicleDetailId,
                                 customerId : customerId
                             });
                         }
                     })
                     .then(function(gpsTrackerInfo){
                         if(gpsTrackerInfo){
                             callback(null, {response: "success"});
                         } else{
                             callback(null, {response: "failure"});
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

   const findAllGpsInfo = function(ctx, filter, lastDate, callback){
       const request = ctx.req;
       lastDate = !lastDate ? new Date():new Date(lastDate);
       let limit;
       if(!lastDate && !filter){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                   limit = filter.limit;
                   GpsTrackerInfo.find({
                       limit : limit,
                       where: {
                           customerId : customerId,
                           status: "active"
                       }
                   })
                       .then(function(gpsTrackerInfoList){
                           if(gpsTrackerInfoList){
                               if(gpsTrackerInfoList.length){
                                   const gpsTrackerInfo = gpsTrackerInfoList[gpsTrackerInfoList.length - 1];
                                   lastDate = gpsTrackerInfo.added;
                               }
                           }
                           callback(null, {
                               data: gpsTrackerInfoList,
                               cursor : lastDate
                           });
                       })
                       .catch(function(error){
                           callback(error);
                       });
               }
           }
       }
   };

   const deleteGpsInfo = function(ctx, gpsInfoId, callback){
       const request = ctx.req;
       if(!gpsInfoId){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                   GpsTrackerInfo.findById(gpsInfoId)
                       .then(function(gpsTrackerInfo){
                           if(gpsTrackerInfo){
                               return gpsTrackerInfo.updateAttribute("status", "inactive");
                           }
                       })
                       .then(function(gpsTrackerInfo){
                           if(gpsTrackerInfo){
                               callback(null, {response:"success"});
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

   const fetchGpsInfoDetail = function(ctx, deviceIMEI, callback){
     const request = ctx.req;
     if(!deviceIMEI){
         callback(new Error("Invalid Arguments"));
     } else{
         if(request.accessToken){
             if(request.accessToken.userId){
                 const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                 GpsTrackerInfo.findOne({
                     where: {
                         deviceIMEI : deviceIMEI
                     }
                 })
                     .then(function(gpsTrackerInfo){

                     })
             }
         }
     }
   };

   const findAllGpsNotification = function(ctx, lastDate, deviceIMEI, limit, callback){
       lastDate = !lastDate ? new Date():new Date(lastDate);
       const request = ctx.req;
       if(!deviceIMEI && !limit){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const GpsNotification = databaseObj.GpsNotification;
                   GpsNotification.find({
                       limit: limit,
                       where: {
                           deviceIMEI : deviceIMEI,
                           added: {
                               lt : lastDate
                           },
                           customerId : customerId
                       },
                       order: "added DESC"
                   })
                       .then(function(gpsNotificationList){
                           if(gpsNotificationList){
                               if(gpsNotificationList.length){
                                   const gpsNotification = gpsNotificationList[gpsNotificationList.length - 1];
                                   lastDate = gpsNotification.added;
                               }
                               callback(null, {
                                   data: gpsNotificationList,
                                   cursor: lastDate
                               })
                           } else{
                               throw new Error("Gps Notification not found");
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

   const findAllGpsPacketDataInfo = function(ctx, deviceIMEI, callback){
       const request = ctx.req;
       if(request.accessToken){
           if(request.accessToken.userId){
               const GpsPacketData = databaseObj.GpsPacketData;
               GpsPacketData.findOne({
                   where: {
                       deviceIMEI : deviceIMEI
                   },
                   order: 'added DESC'
               })
                   .then(function(gpsPacketData){
                       if(gpsPacketData){
                           callback(null, {data: gpsPacketData});
                       }else{
                           callback(null, {});
                       }

                   })
                   .catch(function(error){
                       callback(error);
                   });
              /* GpsTrackerInfo.find({
                   where: {
                       customerId : customerId,
                       status: 'active'
                   }
               })
                   .then(function(gpsTrackerInfoList){
                       if(gpsTrackerInfoList){
                           if(gpsTrackerInfoList.length){
                               gpsTrackerInfoList.forEach(function(gpsTrackerInfo){
                                   if(gpsTrackerInfo){
                                       promises.push(function(callback){
                                           GpsPacketData.findOne({
                                               where: {
                                                   deviceIMEI : gpsTrackerInfo.deviceIMEI
                                               },
                                               order: 'added DESC'
                                           })
                                               .then(function(gpsPacketData){
                                                   if(gpsPacketData){
                                                       gpsPacketDataList.push(gpsPacketData);
                                                   }
                                                   callback(null);
                                               })
                                               .catch(function(error){
                                                   callback(error);
                                               })
                                       })
                                   }
                               })
                           }
                       }
                       async.series(promises, function(error){
                           if(error){
                               callback(error);
                           } else{
                               callback(null, gpsPacketDataList);
                           }
                       })
                   })
                   .catch(function(error){
                       callback(error);
                   })*/
           } else{
               callback(new Error("User not valid"));
           }
       } else{
           callback(new Error("User not valid"));
       }
   };

   const fetchGpsNotificationData = function(ctx, deviceIMEI, callback){
       const request = ctx.req;
       if(!deviceIMEI){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                   GpsTrackerInfo.findOne({
                       where: {
                           deviceIMEI : deviceIMEI,
                           status: "active",
                           customerId : customerId
                       }
                   })
                       .then(function(gpsTrackerInfo){
                           if(gpsTrackerInfo){
                               callback(null, gpsTrackerInfo);
                           } else{
                               throw new Error("Gps Tracker Info not found");
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

   const updateGpsNotificationData = function(ctx, deviceIMEI, gpsTrackerNotification, callback){
       const request = ctx.req;
       if(!deviceIMEI){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request.accessToken){
               if(request.accessToken.userId){
                   const customerId = request.accessToken.userId;
                   const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                   GpsTrackerInfo.findOne({
                       where: {
                           deviceIMEI : deviceIMEI,
                           customerId : customerId
                       }
                   })
                       .then(function(gpsTrackerInfo){
                           if(gpsTrackerInfo){
                               return gpsTrackerInfo.updateAttribute("gpsTrackerNotification", gpsTrackerNotification)
                           }else{
                               throw new Error("GpsTrackerInfo not found");
                           }
                       })
                       .then(function(gpsTrackerInfo){
                           if(gpsTrackerInfo){
                               callback(null, gpsTrackerInfo);
                           } else{
                               throw new Error("Not able to update the data");
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

   const resetGpsTrackerInfoPin = function(ctx, deviceIMEI, oldGpsCode, newGpsCode, callback){
     const request = ctx.req;
     let promises = [];
     if(!deviceIMEI && !oldGpsCode && !newGpsCode){
         callback(new Error("Invalid Arguments"));
     } else{
         if(request.accessToken){
             if(request.accessToken.userId){
                 const customerId = request.accessToken.userId;
                 const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                 GpsTrackerInfo.findOne({
                     where: {
                         deviceIMEI : deviceIMEI
                     }
                 })
                     .then(function(gpsTrackerInfo){
                         if(gpsTrackerInfo){
                             if(gpsTrackerInfo.gpsPassword === oldGpsCode){
                                 //user can change the pin..
                                 return GpsTrackerInfo.find({
                                     where: {
                                         deviceIMEI : deviceIMEI
                                     }
                                 })
                             } else{
                                 //user can't change the pin..
                                 throw new Error("Old Gps Code is incorrect");
                             }
                         }
                     })
                     .then(function(gspTrackerInfoList){
                         if(gspTrackerInfoList){
                             if(gspTrackerInfoList.length){
                                 gspTrackerInfoList.forEach(function(gpsTrackerInfo){
                                     if(gpsTrackerInfo){
                                         promises.push(function(callback){
                                             gpsTrackerInfo.updateAttribute('gpsPassword', newGpsCode)
                                                 .then(function(gpsTrackerInfo){
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
                                        callback(error);
                                    } else{
                                        callback(null, {response: "success"});
                                    }
                                 })
                             } else{
                                 callback(null, {});
                             }
                         } else{
                             callback(null, {});
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


   const findGpsPacketData = function(ctx, deviceIMEI, callback){
       const request = ctx.req;
       const GpsPacketData = databaseObj.GpsPacketData;
       if(!deviceIMEI){
           callback(new Error("Invalid Arguments"));
       } else{
           if(request){
               if(request.accessToken){
                   if(request.accessToken.userId){
                       GpsPacketData.findOne({
                           where: {
                               deviceIMEI : deviceIMEI
                           }
                       })
                           .then(function(gpsPacketData){
                               if(gpsPacketData){
                                   callback(null, {response:"present"});
                               } else{
                                   callback(null, {response: "notpresent"});
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
           } else{
               callback(new Error("User not valid"));
           }
       }
   };

   const sendGpsActivationSms = function(){
       const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
       GpsTrackerInfo.observe("after save", function(ctx, next){
           if(ctx.isNewInstance){
               const instance = ctx.instance || ctx.data;
               const gpsTrackerObj = instance.toJSON();
               process.nextTick(function(){
                   const serialNumber = gpsTrackerObj.deviceIMEI;
                   const simNumber = "+91" + gpsTrackerObj.gpsTrackerSimNumber;
                   const message = "set$" + serialNumber + packageObj.gpsActivationText;
                   send.send(message, simNumber, function(error){
                       if(error){
                           server.logger.error(error);
                       } else{
                           server.logger.info("Gps Request send successfully");
                       }
                   })
               })
           }
           next();

       })
   };

   const resendGpsActivationSmsMethod = function(){
       const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
       GpsTrackerInfo.resendGpsActivationSms = resendGpsActivationSms;
       GpsTrackerInfo.remoteMethod('resendGpsActivationSms', {
           accepts:[
               {
                   arg: 'ctx',
                   type: 'object',
                   http: {
                       source: 'context'
                   }
               },
               {
                   arg: 'gpsTrackerInfoObj', type:'object'
               }
           ],
           returns:{
               arg: "response", type: "object", root: true
           }
       });
   };

    /**
     * Use to resend gps activation message
     * @param ctx
     * @param gpsTrackerInfo
     * @param callback
     */
   const resendGpsActivationSms = function(ctx, gpsTrackerInfo, callback){
     const request = ctx.req;
     if(!gpsTrackerInfo){
         callback(new Error("Invalid Arguments"));
     } else{
         if(request){
             if(request.accessToken){
                 if(request.accessToken.userId){
                     const serialNumber = gpsTrackerInfo.deviceIMEI;
                     const simNumber = "+91" + gpsTrackerInfo.gpsTrackerSimNumber;
                     const message = "set$" + serialNumber + packageObj.gpsActivationText;
                     send.send(message, simNumber, function(error){
                         if(error){
                             server.logger.error(error);
                             callback(error);
                         } else{
                             server.logger.info("Gps Request send successfully");
                             callback(null, {response:"success"});
                         }
                     })
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
     * To find all Gps Tracker associated with particular customer
     */
   const findAllGpsTrackerMethod = function(){
       const Customer = databaseObj.Customer;
       Customer.findAllGpsTracker = findAllGpsTracker;
       Customer.remoteMethod("findAllGpsTracker", {
           accepts:[
               {
                   arg: 'ctx',
                   type: 'object',
                   http: {
                       source: 'context'
                   }
               }
           ],
           returns:{
               arg: 'trackerList', type:"object", root : true
           }
       })
   };


   const findAllGpsTracker = function(ctx, callback){
     const request = ctx.req;
     let vehicleTrackerList = [];
     let busTrackerList = [];
     if(request){
         if(request.accessToken){
             if(request.accessToken.userId){
                 const customerId = request.accessToken.userId;
                 const GpsTrackerInfo = databaseObj.GpsTrackerInfo;
                 const TrackBusVehicle = databaseObj.TrackBusVehicle;
                 GpsTrackerInfo.find({
                     where: {
                         customerId : customerId,
                         status: "active"
                     }
                 })
                     .then(function(gpsTrackerList){
                         if(gpsTrackerList){
                             if(gpsTrackerList.length){
                                 vehicleTrackerList.push(gpsTrackerList);
                             }
                         }
                         return TrackBusVehicle.find({
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
                     })
                     .then(function(busVehicleList){
                         if(busVehicleList){
                             if(busVehicleList.length){
                                 busTrackerList.push(busVehicleList);
                             }
                         }
                     })
                     .then(function(){
                         callback(null, {
                             vehicleTracker: vehicleTrackerList,
                             busTracker : busTrackerList
                         })
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
     } else{
         callback(new Error("User not valid"));
     }
   };



    return {
        init: init
    };
};