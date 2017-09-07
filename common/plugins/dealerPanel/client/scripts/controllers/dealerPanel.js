'use strict';

angular.module($snaphy.getModuleName())

//Controller for dealerPanelControl ..
.controller('dealerPanelControl', ['$scope', 'HelperService', 'LoginServices', 'InitTableService', '$rootScope', "$timeout", '$state',
    function($scope, HelperService, LoginServices, InitTableService, $rootScope, $timeout, $state) {
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

        //workshop initializer
        $scope.initWorkshop = function() {
            //do something here
        };
        
        
        $scope.quoteReplyInit = function () {
            $scope.settings.tabs.quoteReply.data = {};
            $scope.settings.tabs.quoteReply.form = {};
            $scope.settings.tabs.quoteReply.config.dealerId = "";
            $scope.settings.tabs.quoteReply.config.customerQuoteId = "";

            $scope.settings.tabs.quoteReply.config.customerQuoteId = $state.params.customerQuoteId;
            $scope.settings.tabs.quoteReply.config.dealerId = $state.params.dealerId;
            $scope.settings.tabs.quoteReply.displayData($scope.settings.tabs.quoteReply.config.customerQuoteId);
        };

        $scope.init = function(){
            var userObj;
            HelperService.initialize()
                .then(function (user) {
                    userObj = user;
                    console.log(user.id);
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