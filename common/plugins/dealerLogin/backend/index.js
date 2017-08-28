'use strict';
module.exports = function(server, databaseObj, helper, packageObj) {

  const loopback = helper.getLoopbackObj(),
  User = databaseObj.User,
  Role = server.models.Role,
  RoleMapping = server.models.RoleMapping;
  /**
	 * Here server is the main app object
	 * databaseObj is the mapped database from the package.json file
	 * helper object contains all the helpers methods.
	 * packegeObj contains the packageObj file of your plugin.
	 */

  /**
	 * Initialize the plugin at time of server start.
	 * init method should never have any argument
	 * It is a constructor and is populated once the server starts.
	 * @return {[type]} [description]
	 */
  var init = function() {
    addRole();
    checkLogin();

    //Used to fetch the authorised roles for the users..
    User.getAuthorisedRoles = function(cb) {
      getAuthorisedRoles(server, cb);
    };

    //Now a method for checking if the user exist in the role.
    User.remoteMethod('getAuthorisedRoles', {
      returns: {
        arg: 'roles',
        type: ['string']
      }
    });

  };

  //Define a role for dealer..
  const addRole = function() {
    const Role = databaseObj.Role;
    const User = databaseObj.User;
    Role.registerResolver(packageObj.roles.serviceLoggedUser, function(role, context, cb) {
      function reject(err) {
        if (err) {
          return cb(err, false);
        }
        cb(null, false);
      }

      function accept() {
        cb(null, true);
      }

      var currentContext = loopback.getCurrentContext();
      var userId = null;
      try {
        userId = currentContext.active.accessToken.userId;
      } catch (err) {
        userId = null;
      }

      if (!userId) {
        return reject(); // do not allow anonymous users
      }

      //Now check if the logged in user is an Employee
      User.exists(userId, function(err, exists) {
        if (err) {
          return reject(err);
        } else {
          if (exists) {
            //Accept the staff role..
            return accept();
          } else {
            return reject();
          }
        }
      });
    });
  };

  //handle login hook add signed cookie to users..
  (function () {
      //Run this method only one time..
      if(!server.userContext){
          server.userContext = true;
          // on login set access_token cookie with same ttl as loopback's accessToken
          databaseObj.User.afterRemote('login', function setLoginCookie(context, accessToken, next) {
              var res = context.res;
              var req = context.req;
              if (accessToken != null) {
                  if (accessToken.id != null) {
                      res.cookie('access_token', accessToken.id, {
                          signed: req.signedCookies ? true : false,
                          maxAge: 1000 * accessToken.ttl
                      });
                      //return res.redirect('/');
                  }
              }
              return next();
          });
      }else{
          //Dont add it again..
      }

  })();




  const checkLogin = function() {
      const Dealer = databaseObj.User;
      Dealer.beforeRemote('login', function(ctx, data, next) {
        const request = ctx.req;
        request.body.email = request.body.email.toLowerCase();
        const instance = request.body.email;
        Dealer.find().then(function(dealer) {
          if (dealer) {
            next();
          }
        }).catch(function(error) {
          next(error);
        });
      });
    },

    /**
     * Get the authorised roles of the current logged in users..
     * @param app
     * @param cb {function(error, roles)}
     */
    getAuthorisedRoles = function(app, cb) {
      var currentContext = loopback.getCurrentContext();
      Role = app.models.Role;
      RoleMapping = app.models.RoleMapping;
      //bad documentation loopback..
      //http://stackoverflow.com/questions/28194961/is-it-possible-to-get-the-current-user-s-roles-accessible-in-a-remote-method-in
      //https://github.com/strongloop/loopback/issues/332
      var context;
      try {
        context = {
          principalType: RoleMapping.USER,
          principalId: currentContext.active.accessToken.userId
        };
      } catch (err) {
        console.error("Error >> User not logged in. ");
        context = {
          principalType: RoleMapping.USER,
          principalId: null
        };
      }

      Role.getRoles(context, function(err, roles) {
        if (err) {
          cb(err, null);
        } else {
          cb(null, roles);
        }
      });
    }

  //return all the methods that you wish to provide user to extend this plugin.
  return {init: init};
}; //module.exports
