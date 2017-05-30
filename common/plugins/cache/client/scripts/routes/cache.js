'use strict';
/*jslint browser: true*/
/*global $, jQuery, $snaphy, angular*/
angular.module($snaphy.getModuleName())
   //Adding the logic for angular
    //Adding the logic for angular cache..
    .config(["localStorageServiceProvider", function (localStorageServiceProvider) {
        var cachePrefix = $snaphy.loadSettings('cache', "cachePrefix");

        localStorageServiceProvider.setPrefix(cachePrefix);
    }]);
