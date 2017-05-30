'use strict';

/**
 * @ngdoc function
 * @name templateAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the templateAdminApp
 */
angular.module($snaphy.getModuleName())
  .controller('bodyCtrl', ['$scope', '$window', 'TemplateSettings', function($scope, $window, TemplateSettings){
    //Set the default true value..
    $scope.defaultTemplate = true;

    $scope.templateSettings = TemplateSettings;

    $scope.templateEnabled = function(){
    	$scope.defaultTemplate = $window.$snaphy.getDefaultTemplate();
    	return $scope.defaultTemplate;
    };

  }]); //controller..