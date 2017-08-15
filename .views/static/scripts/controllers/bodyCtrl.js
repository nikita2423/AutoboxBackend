'use strict';

/**
 * @ngdoc function
 * @name templateAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the templateAdminApp
 */
angular.module($snaphy.getModuleName())
  .controller('bodyCtrl', ['$scope', '$window', 'TemplateSettings', "$rootScope", function($scope, $window, TemplateSettings, $rootScope){
    //Set the default true value..
    $scope.defaultTemplate = true;

    $scope.templateSettings = TemplateSettings;

    $scope.templateEnabled = function(){
    	$scope.defaultTemplate = $window.$snaphy.getDefaultTemplate();
    	return $scope.defaultTemplate;
    };

      /**
       * Root scope controlling the nofitication system.
        * @type {{unread: {total: number}, main: {class: [*]}, read: {total: number}}}
       */
      $rootScope.notification = {
          unread: {
              total: 0
          },
          main:{
              //By default it btn default..
              class: ["btn", "btn-default"]
          },
          read:{
              total: 0
          }
      };


      //Event listens when a new notification has been popped up..
      $rootScope.$on('setNotification', function(event, args) {
          if(args.unread !== undefined){
              $rootScope.notification.unread.total = args.unread;
          }

          if(args.read !== undefined){
              $rootScope.notification.unread.total = args.read;
          }


          if($rootScope.notification.unread.total === 0){
              $rootScope.notification.main.class = ["btn", "btn-default"]
          }else if($rootScope.notification.unread.total < 5 ){
              $rootScope.notification.main.class = ["btn", "btn-primary"]
          }else{
              $rootScope.notification.main.class = ["btn", "btn-danger"]
          }
      });

  }]); //controller..