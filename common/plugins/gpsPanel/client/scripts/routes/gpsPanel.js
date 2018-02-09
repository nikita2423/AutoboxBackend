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
        // .state('dashboard', {
        //   url: '/gpsPanel',
        //   templateUrl: '/gpsPanel/views/dashboard.html',
        //   controller: 'gpsPanelControl',
        //     //Only allow anonym users here
        //     data: {
        //         permissions: {
        //             only: [employeeRole],
        //             redirectTo: loginState
        //         }
        //     }
        // })

        //Provide routes in this way..
        // .state('monthlyReports', {
        //   url: '/monthlyReports',
        //   templateUrl: '/gpsPanel/views/dashboard.html',
        //   controller: 'gpsPanelControl',
        //     //Only allow anonym users here
        //     data: {
        //         permissions: {
        //             only: [employeeRole],
        //             redirectTo: loginState
        //         }
        //     }
        // })


        //Provide routes in this way..
        // .state('soldViaAutobox', {
        //   url: '/soldViaAutobox',
        //   templateUrl: '/gpsPanel/views/dashboard.html',
        //   controller: 'gpsPanelControl',
        //     //Only allow anonym users here
        //     data: {
        //         permissions: {
        //             only: [employeeRole],
        //             redirectTo: loginState
        //         }
        //     }
        // })

        // //Provide routes in this way..
        // .state('customerCallMessage', {
        //     url: '/customerCallMessage',
        //     templateUrl: '/gpsPanel/views/customerCallMessage.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })
          
        //   //Provide routes in this way..
        // .state('manageWorkshopProfile', {
        //     url: '/manageWorkshopProfile',
        //     templateUrl: '/gpsPanel/views/manageProfile.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })
          
        //   //Provide routes in this way..
        // .state('manageShowroomProfile', {
        //     url: '/manageShowroomProfile',
        //     templateUrl: '/gpsPanel/views/showroom.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })
          
        //   //Provide routes in this way..
        // .state('orderGPSTracker', {
        //     url: '/orderGPSTracker',
        //     templateUrl: '/gpsPanel/views/orderPanel.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })
          
        //   //Provide routes in this way..
        // .state('orderDashCamera', {
        //     url: '/orderDashCamera',
        //     templateUrl: '/gpsPanel/views/orderPanel.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })
          
        //   .state('feedback', {
        //     url: '/feedback',
        //     templateUrl: '/gpsPanel/views/feedback.html',
        //     controller: 'gpsPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })

        //   .state('dashboard.quoteReply', {
        //     templateUrl: '/gpsPanel/views/quoteReply.html',
        //     controller: 'gpsPanelControl',
        //       params: { dealerId: null,  customerQuoteId: null},
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })

        //   .state('customerCallMessage.replyToCustomer', {
        //      templateUrl: '/gpsPanel/views/replyCustomerMessage.html',
        //       controller: 'gpsPanelControl',
        //       params: { dealerId: null,  customerMessageId: null, status: null, replyMessage: null, customerId: null, customerQuoteId: null},
        //       data: {
        //          permissions: {
        //              only: [employeeRole],
        //              redirectTo: loginState
        //          }
        //       }
        //   })

          .state('addDealerVehicle', {
              url: '/addDealerVehicle',
              templateUrl: '/gpsPanel/views/addVehicle.html',
              controller: 'gpsPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })

          .state('dealerVehicle', {
              url:'/addDealerVehicle/add',
              templateUrl: '/gpsPanel/views/dealerVehicle.html',
              controller: 'gpsPanelControl',
              //Only allow anonym users here
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })

          .state('trackVehicle', {
              url: '/trackVehicle',
              templateUrl: '/gpsPanel/views/trackVehicle.html',
              controller: 'gpsPanelControl',
              //Only allow anonym users here
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })


    }]); //config