/**
 * Created by robins on 7/9/17.
 */
'use strict';

angular.module($snaphy.getModuleName())

    .run(["$rootScope", "$q", "LoginServices", function($rootScope, $q, LoginServices) {

        //On Product Department Selected..
        $rootScope.$on("onCarBrandSelected", function (event, args) {
            var selectedBrand = args.data ;
            if(!selectedBrand) {
                selectedBrand = null;
            }
            var args_ = {
                where: {
                    brandId: selectedBrand.id
                }
            };
            $rootScope.$broadcast("brandHasBeenLoaded", args_);

            /*//Load data..
            $rootScope.$broadcast("loadProductCategory", args_);
            $rootScope.$broadcast("loadProductCategory", args_);
            $rootScope.$broadcast("loadProductCategory1", args_);
            $rootScope.$broadcast("loadProductCategory2", args_);
*/
        });

    }]);