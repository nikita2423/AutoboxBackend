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
          url: '/gpsPanel',
          templateUrl: '/gpsPanel/views/dashboard.html',
          controller: 'gpsPanelControl',
            //Only allow anonym users here
            data: {
                permissions: {
                    only: [employeeRole],
                    redirectTo: loginState
                }
            }
        })

        // //Provide routes in this way..
        // .state('monthlyReports', {
        //   url: '/monthlyReports',
        //   templateUrl: '/dealerPanel/views/dashboard.html',
        //   controller: 'dealerPanelControl',
        //     //Only allow anonym users here
        //     data: {
        //         permissions: {
        //             only: [employeeRole],
        //             redirectTo: loginState
        //         }
        //     }
        // })


        // //Provide routes in this way..
        // .state('soldViaAutobox', {
        //   url: '/soldViaAutobox',
        //   templateUrl: '/dealerPanel/views/dashboard.html',
        //   controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/customerCallMessage.html',
        //     controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/manageProfile.html',
        //     controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/showroom.html',
        //     controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/orderPanel.html',
        //     controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/orderPanel.html',
        //     controller: 'dealerPanelControl',
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
        //     templateUrl: '/dealerPanel/views/feedback.html',
        //     controller: 'dealerPanelControl',
        //       //Only allow anonym users here
        //       data: {
        //           permissions: {
        //               only: [employeeRole],
        //               redirectTo: loginState
        //           }
        //       }
        //   })

        //   .state('dashboard.quoteReply', {
        //     templateUrl: '/dealerPanel/views/quoteReply.html',
        //     controller: 'dealerPanelControl',
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
        //      templateUrl: '/dealerPanel/views/replyCustomerMessage.html',
        //       controller: 'dealerPanelControl',
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
              templateUrl: '/dealerPanel/views/addVehicle.html',
              controller: 'dealerPanelControl',
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })

          .state('dealerVehicle', {
              url:'/addDealerVehicle/add',
              templateUrl: '/dealerPanel/views/dealerVehicle.html',
              controller: 'dealerPanelControl',
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
              templateUrl: '/dealerPanel/views/trackVehicle.html',
              controller: 'dealerPanelControl',
              //Only allow anonym users here
              data: {
                  permissions: {
                      only: [employeeRole],
                      redirectTo: loginState
                  }
              }
          })


    }]); //config