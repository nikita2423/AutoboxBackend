'use strict';

angular.module($snaphy.getModuleName())

//Controller for schoolPanelControl ..
.controller('schoolPanelControl', ['$scope',
    function($scope) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('schoolPanel', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        
        $scope.init = function () {
            
        }
        
    }//controller function..
]);