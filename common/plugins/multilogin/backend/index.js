'use strict';
/*global module, require, return, console */
module.exports = function( server, databaseObj, helper, packageObj) {
    var FB = require('fb');
    var INSTAGRAM_API = require('instagram-node').instagram();
    //var util = require("./utils");
    var https = require('https');
    var request = require('request');
    const {config} = packageObj;


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
    var init = function(){
        const {google, facebook, instagram} = config;
        if(google){
            if(google.login){
                if(google.login.mobile){
                    if(google.login.mobile.enable){
                        //Add google login..
                        addUserGoogleLogin(server, databaseObj, helper, packageObj);
                    }
                }
            }
        }

        if(facebook){
            if(facebook.login){
                if(facebook.login.mobile){
                    if(facebook.login.mobile.enable){
                        //Add facebook login..
                        addUserFbLogin(server, databaseObj, helper, packageObj);
                    }
                }
            }
        }


        if(instagram){
            if(instagram.login){
                if(instagram.login.mobile){
                    if(instagram.login.mobile.enable){
                        //Add Instagram client secret key and secret id..

                        //Add facebook login..
                        addUserInstagramLogin(server, databaseObj, helper, packageObj);
                    }
                }
            }
        }
    };


    //Visit this link for more info. https://developers.google.com/identity/sign-in/web/backend-auth
    var addUserInstagramLogin = function(server, databaseObj, helper, packageObj){
        //Add instagram client secret key and secret id..
        // Every call to `ig.use()` overrides the `client_id/client_secret`
        

        const {method, enable} = config.instagram.login.mobile;
        if(enable){
            var User = databaseObj.User;
            var defaultError = new Error('login failed');
            defaultError.statusCode = 401;
            defaultError.code = 'LOGIN_FAILED';

            User[method] = function(accessToken, userId, callback){
                addUserInstagramManual(accessToken, userId, callback);
            };

            User.remoteMethod(
                method,
                {
                    description: 'Logins a user by authenticating it with instagram',
                    accepts: [
                        { arg: 'accessToken', type: 'string', required: true, http: { source:'form'} },
                        { arg: 'userId', type: 'string', required: true, http: { source:'form'} }
                    ],
                    returns: {
                        arg: 'accessToken', type: 'object', root: true,
                        description:
                        'The response body contains properties of the AccessToken created on login.\n' +
                        'Depending on the value of `include` parameter, the body may contain ' +
                        'additional properties:\n\n' +
                        '  - `user` - `{User}` - Data of the currently logged in user. (`include=user`)\n\n'
                    },
                    http: {verb: 'post'}
                }

            );
        }

    };


    var addUserInstagramManual = function(accessToken, userId, callback){
        var User = databaseObj.User;
        if(accessToken && userId){
            const url = "https://api.instagram.com/v1/users/" + userId + "/?access_token="+ accessToken;

            request(url, function (error, response, data) {
                if (!error && response.statusCode === 200) {
                    if(data){
                        try{
                            data = JSON.parse(data);
                        }
                        catch (err){
                            console.log(err);
                            return callback(defaultError);
                        }

                        data = data.data;
                        
                        //Now create user and login..
                        //createUserOrLogin(data, packageObj, User,  callback);
                        var userData = {};
                        userData.username = data.username;
                        userData.name = data.full_name;
                        userData.firstName = data.full_name;
                    
                        var profileUrl = data.profile_picture;

                        if(profileUrl){
                            userData.profilePic = {
                                url: {
                                    defaultUrl: profileUrl,
                                    unSignedUrl: profileUrl
                                }
                            };
                        }

                        createUserOrLogin(server, userData, packageObj, User, databaseObj, accessToken, userId, "instagram", callback);
                    }else{
                        return callback(defaultError, null);
                    }
                }else{
                    console.log(error); // Show the HTML for the Google homepage.
                    console.error("Error getting data from the instagram server.");
                    // Send error
                    callback(error);
                }
            });
        }else{
            callback(new Error("Access Token is  required"));
        }
    };


    //Visit this link for more info. https://developers.google.com/identity/sign-in/web/backend-auth
    var addUserGoogleLogin = function(server, databaseObj, helper, packageObj){
        const {method, enable} = config.google.login.mobile;
        if(enable){
            var User = databaseObj.User;
            var defaultError = new Error('login failed');
            defaultError.statusCode = 401;
            defaultError.code = 'LOGIN_FAILED';

            User[method] = function(accessToken, callback){
                loginWithGoogleManual(accessToken, callback);
            };

            User.remoteMethod(
                method,
                {
                    description: 'Logins a user by authenticating it with google',
                    accepts: [
                        { arg: 'accessToken', type: 'string', required: true, http: { source:'form'} }
                    ],
                    returns: {
                        arg: 'accessToken', type: 'object', root: true,
                        description:
                        'The response body contains properties of the AccessToken created on login.\n' +
                        'Depending on the value of `include` parameter, the body may contain ' +
                        'additional properties:\n\n' +
                        '  - `user` - `{User}` - Data of the currently logged in user. (`include=user`)\n\n'
                    },
                    http: {verb: 'post'}
                }

            );
        }

    };




    var loginWithGoogleManual = function(accessToken, callback, phoneNumber){
        var User = databaseObj.User;
        if(accessToken){
                //var url = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
                var url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + accessToken;


                request(url, function (error, response, data) {
                    if (!error && response.statusCode === 200) {
                        if(data){
                            try{
                                data = JSON.parse(data);
                            }
                            catch (err){
                                console.log(err);
                                return callback(defaultError);
                            }

                            
                            //Now create user and login..
                            //createUserOrLogin(data, packageObj, User,  callback);
                            var userData = {};
                            userData.email = data.email;
                            userData.name = data.name;
                            userData.firstName = data.given_name;
                            userData.lastName = data.family_name;
                            if(phoneNumber){
                                userData.phoneNumber = phoneNumber;
                            }

                            var profileUrl = data.picture;

                            if(profileUrl){
                                userData.profilePic = {
                                    url: {
                                        defaultUrl:data.picture,
                                        unSignedUrl: data.picture
                                    }
                                };
                            }

                            //console.log(userData);

                            createUserOrLogin(server, userData, packageObj, User, databaseObj, accessToken, data.sub, "google", callback);
                        }else{
                            return callback(defaultError, null);
                        }
                    }else{
                        console.log(error); // Show the HTML for the Google homepage.
                        console.error("Error getting data from the google plus server.");
                        // Send error
                        callback(error);
                    }
                });
        }else{
            callback(new Error("Access Token is  required"));
        }
    };




    var addUserFbLogin = function(server, databaseObj, helper, packageObj){
        const {method, enable} = config.facebook.login.mobile;
        if(enable){
            var User = databaseObj.User;
            //Now defining a method login with access token
            User[method] = function (accessToken, cb) {
                FB.setAccessToken(accessToken);
                FB.api('me', {fields: ['id', 'name', "first_name", "last_name", "email"]}, function (res) {
                    if(!res || res.error) {
                        console.log(!res ? 'error occurred' : res.error);
                        var err = new Error('Invalid Access Token');
                        err.statusCode = 401;
                        cb(err);
                        return;
                    }

                    var profileUrl = "https://graph.facebook.com/"+ res.id +"/picture?width=500&height=500";



                    var userData = {email: res.email, "firstName": res.first_name, "lastName": res.last_name };

                    //console.log(profileUrl);
                    if(profileUrl){
                        userData.profilePic = {
                            url: {
                                defaultUrl:profileUrl,
                                unSignedUrl: profileUrl
                            }
                        };
                    }

                    //Now create user and login..
                    createUserOrLogin(server, userData, packageObj, User, databaseObj, accessToken, res.id, "facebook", cb);
                });
            };


            User.remoteMethod(
                method,
                {
                    description: 'Login a user by authenticating it with an external entity',
                    accepts: [
                        { arg: 'external_access_token', type: 'string', required: true, http: { source:'form'} }
                    ],
                    returns: {
                        arg: 'accessToken', type: 'object', root: true,
                        description:
                        'The response body contains properties of the AccessToken created on login.\n' +
                        'Depending on the value of `include` parameter, the body may contain ' +
                        'additional properties:\n\n' +
                        '  - `user` - `{User}` - Data of the currently logged in user. (`include=user`)\n\n'
                    },
                    http: {verb: 'post'}
                }
            );
        }

    };



    //Create user or login user.....
    /**
     * Create a user if not availaible and then login user finally.
     * @param server loopback app object
     * @param data  {object} {email: dummy@gmail.com, password:3535, firstName: robins, lastName: Gupta, id}
     * @param packageObj
     * @param User {Object} {Loopback User model}
     * @param callback
     * @param databaseObj Snaphy databaseObj model
     * @param thirdPartyAccessToken {String}  Access Token obtained from third party server
     * @param picture {String}  Url for the profile picture..
     * @param type {String}  facebook| google provider name
     * @param thirdPartyId {String}  facebook| google user id
     */
    var createUserOrLogin = function(server, data, packageObj, User, databaseObj, thirdPartyAccessToken, thirdPartyId,  type, callback){
        var defaultError = new Error('login failed');
        defaultError.statusCode = 401;
        defaultError.code = 'LOGIN_FAILED';
        if(data.email || data.username){
            // accessToken is valid, so
            var query = {};

            if(data.email){
                query.email = data.email;
            }else if(data.username) {
                query.username = data.username;
            }

            var password = packageObj.secretKey,
                AccessTokenModel = databaseObj.FacebookAccessToken;

            User.findOne({where: query}, function (err, user){

                if(err){
                    console.log(err);
                    callback(defaultError);
                }else if(!user){
                    //var userData = {email: query.email, password: password, "firstName": data.first_name, "lastName": data.last_name };

                    //console.log(userData);
                    data.password = password;
                

                    // User email not found in the db case, create a new profile and then log him in
                    User.create(data, function(err, user) {
                        if(err){
                            console.error(err);
                            return callback(defaultError);
                        }else{
                            updateAccessTokenModel(server, data, user, AccessTokenModel, thirdPartyAccessToken, thirdPartyId, type,  callback);
                        }
                    });
                }
                else{
                    //update phone number if given.
                    if(data.phoneNumber){
                        //first save the user..
                        user.phoneNumber = data.phoneNumber;
                        user.save({}, function(err, user_){
                            if(err){
                                console.error(err);
                                return callback(err);
                            }else{
                                updateAccessTokenModel(server, data,  user_, AccessTokenModel, thirdPartyAccessToken, thirdPartyId, type,  callback);
                            }
                        });
                    }else{
                        updateAccessTokenModel(server, data,  user, AccessTokenModel, thirdPartyAccessToken, thirdPartyId, type,  callback);
                    }
                }
            });
        }else{

            callback(defaultError);
        }
    };




    var updateAccessTokenModel = function(server, data,  userInstance, AccessTokenModel, thirdPartyAccessToken, thirdPartyId,  type,  callback){
        userInstance.createAccessToken(31536000, function(error, token) {
            if (error) {
                console.error(error);
                return callback(error);
            }else{
                token.__data.user = userInstance;
                //console.log(token);
                callback(null,  token);
                //Now save the user to AccessToken model..
                data = {
                    "id": thirdPartyId,
                    "FbUserId": thirdPartyId,
                    "token": thirdPartyAccessToken,
                    "expires": new Date(token.ttl),
                    "type": type,
                    "userId": userInstance.id
                };
                AccessTokenModel.upsert(data)
                    .then(function(values){
                        console.log("successfully updated third party access token data.");
                    })
                    .catch(function(err){
                        console.error("Error updating access token for " + type );
                        console.error(err);
                    });
            }
        }); //createAccessToken
    };




    //return all the methods that you wish to provide user to extend this plugin.
    return {
        init: init,
        loginWithGoogleManual: loginWithGoogleManual
    };
}; //module.exports