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
            $scope.settings.tabs.quoteReply.displayData($scope.settings.tabs.quoteReply.config.customerQuoteId, $scope.settings.tabs.quoteReply.config.dealerId);
        };

        $scope.replyCustomerMessageInit = function() {
            // dealerId: null,  customerMessageId: null, status: null, replyMessage: null
            $scope.settings.tabs.replyCustomerMessage.data = {
                id: $state.params.customerMessageId,
                status:  $state.params.status,
                replyMessage: $state.params.replyMessage,
                customerId: $state.params.customerId,
                dealerId: $state.params.dealerId,
                customerQuoteId: $state.params.customerQuoteId
            };
            $scope.settings.tabs.replyCustomerMessage.form = {};
            $scope.settings.tabs.replyCustomerMessage.config.dealerId = "";
            $scope.settings.tabs.replyCustomerMessage.config.customerMessageId = "";

            $scope.settings.tabs.replyCustomerMessage.config.customerMessageId = $state.params.customerMessageId;
            $scope.settings.tabs.replyCustomerMessage.config.dealerId = $state.params.dealerId;
            //Fetch the data from backend..first..
            $scope.settings.tabs.replyCustomerMessage.initialize();
        };

        $scope.initMapView = function(){
            $scope.settings.tabs.trackVehicle.initMap();
        };


        /**
         * Track Dealer Vehicle Init.
         */
        $scope.trackDealerVehicleInit = function(){
            $scope.settings.tabs.trackVehicle.findDealerVehicles();
        };



        /**
         * Initialize the function..
         */
        $scope.init = function(){
            var userObj;
            HelperService.initialize()
                .then(function (user) {
                    userObj = user;
                    return $scope.setCurrentState();
                })
                .then(function () {
                    //Feedback
                    $rootScope.settings.tabs.feedback.config.display = true;
                    $rootScope.settings.tabs.feedback.data="";

                    if($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail){
                        var modelName = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail.modelName;
                        $scope.tableViewInit  = InitTableService.tableViewInit($scope, modelName, null);
                        var tableInstance = $scope.tableViewInit($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail);
                        //Adding Total Number of Rows..
                        tableInstance.getCache().settings.totalNumberOfRows = 15;
                        $scope.relationDetail = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail;
                    }
                    if($rootScope.settings.config.currentActiveTab === "manageShowroomProfile"){
                        $timeout(function(){
                            $scope.getActiveTabSettings().loadArea();
                        }, 200);
                        return $scope.getActiveTabSettings().getShowroomData(userObj);
                    } else if($rootScope.settings.config.currentActiveTab === "manageWorkshopProfile"){
                        $timeout(function(){
                            $scope.getActiveTabSettings().loadArea();
                        }, 200);
                        return $scope.getActiveTabSettings().getWorkshopData(userObj);
                    }
                })
                .then(function () {
                    //show room data loaded..

                })
                .catch(function (error) {
                    console.error(error);
                });
        };

        $scope.initCallMessage = function(){
            var userObj;
            HelperService.initialize()
                .then(function(user){
                    userObj = user;
                    console.log(user.id);
                    console.log("User loaded Successfully");
                    return $scope.setCurrentState();
                })
                .then(function(){
                    console.log("tab", $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail);
                    if($rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail){
                        var modelName = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail.modelName;
                        console.log("modelName", modelName);
                      /*  $scope.tableViewInit  = InitTableService.tableViewInit($scope, modelName, null);
                        $scope.relationDetail = $rootScope.settings.tabs[$rootScope.settings.config.currentActiveTab].relationDetail;
*/                    }
                })
                .catch(function(error){
                    console.log(error);
                });
        };


        $scope.pageReload = function(){
            $state.reload('monthlyReports');
        };
    }//controller function..
]);