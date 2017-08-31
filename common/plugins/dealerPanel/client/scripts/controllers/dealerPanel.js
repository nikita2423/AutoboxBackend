'use strict';

angular.module($snaphy.getModuleName())

//Controller for dealerPanelControl ..
.controller('dealerPanelControl', ['$scope', 'HelperService', 'LoginServices', 'InitTableService', '$rootScope', "$timeout",
    function($scope, HelperService, LoginServices, InitTableService, $rootScope, $timeout) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('dealerPanel', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        $rootScope.settings                 = HelperService.settings.get();
        $scope.getActiveTabSettings     = HelperService.getActiveTabSettings;
        $scope.setCurrentState          = HelperService.setCurrentState;


        //showroom initializer..
        $scope.initShowRoom = function () {
            //fetch data from js
        };

        $scope.init = function(){
            var userObj;
            HelperService.initialize()
                .then(function (user) {
                    userObj = user;
                    console.log("User loaded successfully");
                    return $scope.setCurrentState();
                })
                .then(function () {
                    if($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail){
                        var modelName = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail.modelName;
                        $scope.tableViewInit  = InitTableService.tableViewInit($scope, modelName, null);
                        $scope.relationDetail = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail;
                    }
                    if($rootScope.settings.config.currentActiveTab === "manageShowroomProfile"){
                        $timeout(function(){
                            $scope.getActiveTabSettings().loadArea();
                        }, 200);
                        return $scope.getActiveTabSettings().getShowroomData(userObj);
                    }
                })
                .then(function () {
                    //show room data loaded..

                })
                .catch(function (error) {
                    console.error(error);
                });
        };
    }//controller function..
]);