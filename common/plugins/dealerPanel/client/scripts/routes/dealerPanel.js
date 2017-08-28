'use strict';
/*jslint browser: true*/
/*global $, jQuery, $snaphy, angular*/

var loginState  = $snaphy.loadSettings('login', "loginState");



angular.module($snaphy.getModuleName())
  //Routes are defined using ui.routes 
  .config(['$locationProvider', '$stateProvider', '$urlRouterProvider',
    function ($locationProvider, $stateProvider, $urlRouterProvider) {
      $locationProvider.html5Mode(false);
      //$urlRouterProvider.otherwise('/');

      $stateProvider
        //Provide routes in this way..
        .state('dashboard', {
          url: '/dealerPanel',
          templateUrl: '/dealerPanel/views/dashboard.html',
          controller: 'dealerPanelControl',
            //Only allow anonym users here
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        })

        //Provide routes in this way..
        .state('monthlyReports', {
          url: '/monthlyReports',
          templateUrl: '/dealerPanel/views/dashboard.html',
          controller: 'dealerPanelControl',
            //Only allow anonym users here
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        })


        //Provide routes in this way..
        .state('soldViaAutobox', {
          url: '/soldViaAutobox',
          templateUrl: '/dealerPanel/views/dashboard.html',
          controller: 'dealerPanelControl',
            //Only allow anonym users here
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        });

    }]); //config
