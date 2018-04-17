'use strict';
/*global angular, $snaphy, $scope, $stateParams */

angular.module($snaphy.getModuleName())

    .controller('loginControl', ['$scope', 'Database', '$rootScope', 'LoginServices', '$injector',
        function($scope, Database, $rootScope, LoginServices, $injector) {
            //Adding title and name..
            $scope.name              = $snaphy.loadSettings('login', 'loginName');
            $scope.title             = $snaphy.loadSettings('login', 'loginTitle');
            $scope.loginState        = $snaphy.loadSettings('login', "loginState");
            $scope.registerState     = $snaphy.loadSettings('login', "registerState");
            $scope.forgotPassState   = $snaphy.loadSettings('login', "forgotPassState");

            var defaultTemplate = $snaphy.loadSettings('login', "defaultTemplate");
            $snaphy.setDefaultTemplate(defaultTemplate);

            var UserService = Database.getDb('schoolLogin', 'User');
            $scope.credentials = {};
            $scope.loginError = false;

            function disableButton(){
                $scope.isClickEnabled = false;
            }

            function enableButton(){
                $scope.isClickEnabled = true;
            }



            //Enable the login button at first..
            enableButton();
            $scope.login = function(loginForm){
                if(!loginForm.validate()){
                    $scope.errorMsg = "Please login by providing the correct data.";
                    //Display login error..
                    $scope.loginError = true;
                    return null;
                }
                if(loginForm.$valid){
                    disableButton();
                    //Now login to the employee ..
                    UserService.login($scope.credentials, function(userDetail){
                        enableButton();
                        $scope.loginError = false;
                        //Add user detail to the database..
                        LoginServices.addUserDetail.set(userDetail.user);
                        //var $state = $injector.get('$state');
                            //redirectTo =  $rootScope.previousState.name ?  $rootScope.previousState.name :  LoginServices.redirectOtherWise;
                        //Redirect to the default State..
                        //$state.go(redirectTo);
                        //Now reload the page after successfull login..
                        window.location.replace("/school");
                    },function(){
                        enableButton();
                        $scope.errorMsg = "Please login by providing correct username and password.";
                        //Display login error..
                        $scope.loginError = true;
                    });


                }


            }; //login function


            $scope.validateForm = {
                "rules":{
                    'login-email': {
                        required: true,
                        email: true
                    },
                    'login-password': {
                        required: true,
                        minlength: 5
                    }
                },
                "messages":{
                    'login-email': {
                        required: 'Email is required',
                        email: 'Email must be valid'
                    },
                    'login-password': {
                        required: 'Please provide a password',
                        minlength: 'Your password must be at least 5 characters long'
                    }
                }
            };

        }//controller function..
    ]);
