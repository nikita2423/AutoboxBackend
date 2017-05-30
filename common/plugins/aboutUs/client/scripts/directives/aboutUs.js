(function(){
    'use strict';
})();

/*global angular, $, CKEDITOR, App */
angular.module($snaphy.getModuleName())



    .directive('snaphyCkEditor', ['$timeout', function($timeout) {
        return {
            restrict: 'A',
            link: function(scope, iElement, iAttrs) {

                scope.$parent.getHtmlData = function(){
                    return CKEDITOR.instances[iAttrs.id].getData();
                };

                //console.log(scope);

                $timeout(function(){
                    CKEDITOR.disableAutoInline = true;
                    CKEDITOR.replace(iAttrs.id);

                }); //timeout method..

            } //End of Link function...
        }; // End of return
    }]);
