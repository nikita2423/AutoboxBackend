/**
 * Created by nikita on 19/9/17.
 */

module.exports = function(Gpspacketdata, server, helper) {

    Gpspacketdata.observe("before save", function(ctx, next){

      const instance = ctx.instance || ctx.data;
      const currentInstance = ctx.currentInstance;

      if(ctx.isNewInstance){
          instance.added = new Date();
          instance.updated = new Date();
      } else{
          instance.updated = new Date();
      }

      //next();

      if(instance.deviceIMEI){
          const GpsTrackerInfo = server.models["GpsTrackerInfo"];
          GpsTrackerInfo.findOne({
              where: {
                  deviceIMEI: instance.deviceIMEI
              }
          })
              .then(function(gpsTrackerInfo){
                  if(gpsTrackerInfo){
                      instance.customerId = gpsTrackerInfo.customerId;
                      next();
                  }
              })
              .catch(function(error){
                  next(error);
              });
      }
  });
};
