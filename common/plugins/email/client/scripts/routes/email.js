'use strict';
/*global angular, $snaphy, $*/
angular.module($snaphy.getModuleName())



    //Create state to generate at runTime..
    .run(['runtimeStates', function(runtimeStates) {
        var employeeRole = $snaphy.loadSettings('login', "employeeRole");
        var redirectOtherWise = $snaphy.loadSettings('login', 'onLoginRedirectState');

        var emailModelList = $snaphy.loadSettings('email', "loadEmails");
        emailModelList.forEach(function(emailModel){
            var databasesList = emailModel.assosiatedUsers;

            //Loading states at run time.
            databasesList.forEach(function(stateObj) {
                //Add states at run time..
                runtimeStates.addState(emailModel.model + "__" + stateObj,  {
                    url: '/' + emailModel.model + '/'  +  stateObj.toLowerCase().trim(),
                    templateUrl: '/email/views/email.html',
                    controller: 'emailControl',

                    //Only allow authenticated users here
                    data: {
                        permissions: {
                            only: [employeeRole],
                            redirectTo: redirectOtherWise
                        }
                    }
                });
            });
        });
    }]);
