/**
 * Created by robins on 2/12/15.
 */
'use strict';
/*jslint browser: true*/
/*global $, jQuery, angular, $snaphy*/
//This is the setting file of the plugin..TO be configured according to the user needs..
var settings = {
  "defaultTemplate": false,
  "loginName": "AutoBox",
  "loginTitle": "Welcome, please login.",
  "registerTitle":"Please fill the following details to create an employee.",
  "forgotPasswordTitle": "Please provide your account’s email and we will send you your password.",
  "onLoginRedirectState": 'dashboard',
  "403ErrorRouteState": "403Error",
  "loginState": "login",
  "registerState": "register",
  "forgotPassState": "forgotPass",
  "adminRole": 'admin',
  "employeeRole": "employee",
    "logout_event_name": "LOGOUT_EVENT",
    "login_event_name": "LOGIN_EVENT",
//Accept username as login or email
    "accept":{
        "login": "username"
    },
    validations: {
        "rules": {
            'login-username': {
                required: true,
                minlength: 3
            },
            'login-email': {
                required: true,
                email: true
            },
            'login-password': {
                required: true,
                minlength: 5
            }
        },
        "messages": {
            'login-username': {
                required: 'Please enter a username',
                minlength: 'Your username must consist of at least 3 characters'
            },
            'login-email': {
                required: "Email is required",
                email: "Email must be valid"
            },
            'login-password': {
                required: 'Please provide a password',
                minlength: 'Your password must be at least 5 characters long'
            }
        }
    }
};


//Now adding settings to the main index file..
$snaphy.addSettings('login', settings);
