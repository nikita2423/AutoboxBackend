'use strict';
/*jslint browser: true*/
/*global $, jQuery, $snaphy, angular*/
angular.module($snaphy.getModuleName())
    //Routes are defined using ui.routes
    .config(['$locationProvider', '$stateProvider', '$urlRouterProvider',
        function($locationProvider, $stateProvider, $urlRouterProvider) {
            $locationProvider.html5Mode(false);
            //$urlRouterProvider.otherwise('/');
            var companyInfoList = $snaphy.loadSettings('aboutUs', "companyInfo");
            var companyName = $snaphy.loadSettings('aboutUs', "companyName");
            var loginState    = $snaphy.loadSettings('login', "loginState"),
            employeeRole      = $snaphy.loadSettings('login', "employeeRole");

            if (companyInfoList.length) {
                companyInfoList.forEach(function(info) {
                    var url = companyName? companyName.toLowerCase() : "";
                    url = "/" + url + "/" + info.toLowerCase();
                    $stateProvider
                    //Provide routes in this way..
                        .state(info.toLowerCase(),{
                        url: url,
                        templateUrl: '/aboutUs/views/' + info.toLowerCase() + '.html',
                        controller: 'aboutUsControl',
                        data: {
                            permissions: {
                                only: [employeeRole],
                                redirectTo: loginState
                            }
                        }
                    });

                });
            }
        }
    ]); //config
