'use strict';

angular.module($snaphy.getModuleName())

//Controller for schoolPanelControl ..
.controller('schoolPanelControl', ['$scope', 'HelperService', '$stateParams', 'Database', '$rootScope', 'InitTableService', '$timeout',
    function($scope, HelperService, $stateParams, Database, $rootScope, InitTableService, $timeout) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('schoolPanel', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        $rootScope.settings             = HelperService.settings.get();
        $scope.getActiveTabSettings     = HelperService.getActiveTabSettings;
        $scope.setCurrentState          = HelperService.setCurrentState;
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        $scope.init = function(){
            var userObj;
            HelperService.initialize()
                .then(function (user) {
                    userObj = user;
                    return $scope.setCurrentState();
                })
                .then(function () {

                    if($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail){
                        var modelName = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail.modelName;
                        $scope.tableViewInit  = InitTableService.tableViewInit($scope, modelName, null);
                        var tableInstance = $scope.tableViewInit($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail);
                        //Adding Total Number of Rows..
                        tableInstance.getCache().settings.totalNumberOfRows = 15;
                        $scope.relationDetail = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail;
                    }
                    if($rootScope.settings.config.currentActiveTab === "schoolProfile"){
                        return $scope.getActiveTabSettings().getSchoolData(userObj);
                    }
                })
                .then(function () {
                    //school data loaded;

                })
                .catch(function (error) {
                    console.error(error);
                });
        };

    }//controller function..


]);