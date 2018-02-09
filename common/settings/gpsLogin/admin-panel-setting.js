/**
 * Created by robins on 2/12/15.
 */
'use strict';
/*jslint browser: true*/
/*$snaphy*/
//This is the setting file of the plugin..TO be configured according to the user needs..
var settings = {
  "defaultTemplate": false,
  "loginName": "Autobox",
  "loginTitle": "Welcome Dealer, please login.",
  "registerTitle": "Please fill the following details to register.",
  "forgotPasswordTitle": "Please provide your account’s email and we will send you instructions to reset password.",
  "onLoginRedirectState": 'trackVehicle',
  "403ErrorRouteState": "403Error",
  "loginState": "login",
  "registerState": "register",
  "forgotPassState": "forgotPass",
  "adminRole": 'admin',
  "employeeRole": "dealer"
};


//Now adding settings to the main index file..
$snaphy.addSettings('login', settings);
