'use strict';

angular.module($snaphy.getModuleName())

//Controller for dealerPanelControl ..
.controller('dealerPanelControl', ['$scope', 'HelperService', 'LoginServices', 'TableViewService',
    function($scope, HelperService, LoginServices, TableViewService) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('dealerPanel', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        $scope.settings = HelperService.settings.get();


    }//controller function..
]);