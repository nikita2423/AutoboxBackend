/**
 * Created by nikita on 25/12/17.
 */
"use strict";

angular.module($snaphy.getModuleName())

.run(["$rootScope", "$q", "Database", "HelperService", function ($rootScope, $q, Database, DealerPanelService) {
    $rootScope.$on("beforeTableLoad", function (event, options) {
        var schema = options.schema;
        var where = options.where;
        var dealer;
        DealerPanelService.initialize()
            .then(function (user) {
                dealer = user;
                if(dealer){
                    if(dealer.showroom){
                        return dealer.showroom;
                    }
                }
                var ShowroomInstance = Database.getDb("dealerPanel", "Showroom");
                return ShowroomInstance.findOne({
                    filter:{
                        where:{
                            dealerId: dealer.id
                        },
                        include:{
                            relation: "cities",
                            fields:["id"]
                        }
                    }
                }).$promise;
            })
            .then(function (showroom) {
                var cityList = [];
                dealer.showroom = showroom;
                if(showroom){
                    if(showroom.cities){
                        if(showroom.cities.length){
                            showroom.cities.forEach(function (city) {
                                cityList.push(city.id);
                            });
                        }
                    }
                }
                where.cityId = {
                    inq: cityList
                };

                if(schema){
                    if(schema.settings){
                        if(schema.settings.tables){
                            if(schema.settings.tables.resetWhenBroadCast){
                                $rootScope.$broadcast(schema.settings.tables.resetWhenBroadCast, schema);
                            }
                        }
                    }
                }
            })
            .catch(function (error) {
                console.error(error);
            });

    });

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

    $rootScope.$on("onCarModelSelected", function(event, args){
        var selectCarModel = args.data;
        if(!selectCarModel){
            selectCarModel = null;
        }
        var args_ = {
            where: {
                brandId: selectCarModel.brandId,
                carModelId: selectCarModel.id
            }
        };
        $rootScope.$broadcast("modelHasBeenLoaded", args_);
    });
}]);