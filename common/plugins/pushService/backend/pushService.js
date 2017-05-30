module.exports = function (app, notification, application, push, packageJSON) {
  let Notification = notification;
  let Application = application;
  let PushModel = push;

  /**
   * [startPushServer is a method at backend for registering push server and configuring]
   * @return {[type]} [description]
   */
  function startPushServer() {
    PushModel.on('error', function (err) {
      console.error('Push Notification error: ', err.stack);
    });

   
    const snaphyApp = {
      id: packageJSON.PUSH_SERVICE_ID,
      userId: packageJSON.USER_ID,
      name: packageJSON.APP_NAME,

      description: "Snaphy Rest Method for sending push message..",
      pushSettings: {
        apns: {
          certData: packageJSON.APPLE_SETTINGS.apnsCertData,
          keyData: packageJSON.APPLE_SETTINGS.apnsKeyData,
          pushOptions: {
            // Extra options can go here for APN
          },
          feedbackOptions: {
            batchFeedback: true,
            interval: 300
          }
        },
        gcm: {
          serverApiKey: packageJSON.GCM_SERVER_API_KEY
        }
      }
    };



    updateOrCreateApp(function (err, appModel) {
      if (err) {
        throw err;
      }
      console.log('Application id: %j', appModel.id);
    });

    Application.observe("before save", function (ctx, next) {
        const instance = ctx.instance || ctx.data;
        if(instance){
            if(instance.name){
                if(instance.name === snaphyApp.name){
                    instance.id = packageJSON.PUSH_SERVICE_ID;
                }
            }
        }
        next();
    });



    //--- Helper functions ---
    /**
     * [updateOrCreateApp for updating the application settings registered]
     * @param  {Function} cb [Callback function]
     * @return {[type]}      [description]
     */
    function updateOrCreateApp(cb) {
      Application.findOne({
          where: { id: snaphyApp.id }
        },
        function (err, result) {
          if (err) {
            cb(err);
          }else{
	          if (result) {
		          delete snaphyApp.id;
		          result.updateAttributes(snaphyApp, cb);
	          } else {
		          return registerApp(cb);
	          }
          }
        });
    }//updateOrCreate function



    /**
     * [registerApp is a function for registerig the push message implemetation]
     * @param  {Function} cb [Callback function with arguments (err, app)]
     * @return {[type]}      [description]
     */
    function registerApp(cb) {
      console.log('Registering a new Application...');
      // Hack to set the app id to a fixed value so that we don't have to change
      // the client settings

      /*Application.beforeSave = function (next) {
        if (this.name === snaphyApp.name) {
          this.id = packageJSON.PUSH_SERVICE_ID;
        }
        next();
      };
*/
      Application.register(
        snaphyApp.userId,
        snaphyApp.name,
        {
          description: snaphyApp.description,
          pushSettings: snaphyApp.pushSettings
        },
        function (err, app) {
          if (err) {
            return cb(err);
          }
          return cb(null, app);
        }
      );
    }//register App
  }//startPushServer

  startPushServer();
};
