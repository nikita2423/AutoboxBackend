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
          url: '/schoolPanel',
          templateUrl: '/schoolPanel/views/dashboard.html',
          controller: 'schoolPanelControl',
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        });

    }]); //config
