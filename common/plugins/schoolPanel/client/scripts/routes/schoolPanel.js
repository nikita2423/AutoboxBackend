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
          templateUrl: '/schoolPanel/views/studentList.html',
          controller: 'schoolPanelControl',
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        })

        /**
         * Route to open school profile
         */
          .state('schoolProfile', {
          url: '/schoolProfile',
          templateUrl: '/schoolPanel/views/schoolProfile.html',
          controller: 'schoolPanelControl',
          data: {
              permissions: {
                  only: [employeeRole],
                  redirectTo: loginState
              }
          }
      })

          /**
           * Add Student route..
           */
          .state('addStudent', {
              url: '/addStudent',
              templateUrl: '/schoolPanel/views/addStudent.html',
              controller: 'schoolPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })

          /**
           * Add Bus Route
           */
          .state('addBus', {
              url: '/addBus',
              templateUrl: '/schoolPanel/views/addBus.html',
              controller: 'schoolPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })


          /**
           * All Buses belongs to school Route
           */
          .state('busList', {
              url: '/busList',
              templateUrl: '/schoolPanel/views/busList.html',
              controller: 'schoolPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })


          /**
           * Bus Notification History Route
           */
          .state('busHistory', {
              url: '/busHistory',
              templateUrl: '/schoolPanel/views/busHistory.html',
              controller: 'schoolPanelControl',
              params: {busModelId : null},
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })


          /**
           * Query to Autobox route
           */
          .state('query', {
              url: '/query',
              templateUrl: '/schoolPanel/views/query.html',
              controller: 'schoolPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          });







    }]); //config
