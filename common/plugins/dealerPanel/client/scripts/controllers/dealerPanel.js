'use strict';

angular.module($snaphy.getModuleName())

//Controller for dealerPanelControl ..
.controller('dealerPanelControl', ['$scope', 'HelperService', 'LoginServices', 'InitTableService', '$rootScope',
    function($scope, HelperService, LoginServices, InitTableService, $rootScope) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('dealerPanel', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        $rootScope.settings                 = HelperService.settings.get();
        $scope.getActiveTabSettings     = HelperService.getActiveTabSettings;
        $scope.setCurrentState          = HelperService.setCurrentState;


        $scope.init = function(){
            HelperService.initialize()
                .then(function (user) {
                    console.log("User loaded successfully");
                    return $scope.setCurrentState();
                })
                .then(function () {
                    var modelName = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail.modelName;
                    $scope.tableViewInit  = InitTableService.tableViewInit($scope, modelName, null);
                    $scope.relationDetail = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail;
                })
                .catch(function (error) {
                    console.error(error);
                });
        };
    }//controller function..
]);