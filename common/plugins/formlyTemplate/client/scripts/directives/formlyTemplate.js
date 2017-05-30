(function(){'use strict';})();
/*jslint browser: true*/
/*global $, jQuery, $snaphy, angular*/
angular.module($snaphy.getModuleName())

    //Autocomplete for selectize search..
    .directive('autocomplete', ['Database', '$timeout', 'SnaphyTemplate', function(Database, $timeout, SnaphyTemplate) {
        // Runs during compile
        return {
            restrict: 'E',
            replace: true,
            transclude: true,
            scope:{
                "modelName"      : "@modelName",
                "searchProperty" : "@searchProperty",
                //Contains the value of the data.. that needs to be updated.
                "value"          : "=value",
                "where"          : "=where",
                "load"           : "=?load", //if init === true then fetched all data on start only.
                "whereValidation": "=whereValidation",
                "onChange"       : "&onChange",
                //To display any additional property to..array of objects..
                /**
                 * [ { name: "amount", "prefix": "Rupee" }  ]
                 */
                "displayProperty": "=?displayProperty"
            },
            template: '<select ng-transclude><option value=""></option></select>' ,
            link: function(scope, iElm, iAttrs, controller) {
                if(!scope.modelName || !scope.searchProperty){
                    console.error("Error >>> searchProperty and modelName attributes are required");
                    return false;
                }
                scope.displayProperty = scope.displayProperty || [];
                scope.placeholder = "Search ".toUpperCase() + scope.modelName.toUpperCase() + " " + scope.searchProperty.toUpperCase();
                $(iElm).attr("placeholder", scope.placeholder);

                var loadSelectize = function(){
                    //Load selectize now..
                    var selectize_ = $(iElm).selectize({
                        maxItems: 1,
                        valueField: 'id',
                        labelField: scope.searchProperty,
                        searchField: scope.searchProperty,
                        delimiter: ',',
                        persist: false,
                        create: false,
                        render: {
                            option: function(item, escape) {

                                var parent = "";

                                if(scope.displayProperty){
                                    scope.displayProperty.forEach(function(prop){
                                        //var element = "<span><strong>";
                                        var element = "<span>";
                                        if(prop.prefix){
                                            element = element + " " + prop.prefix ;
                                        }

                                        if(prop.name){

                                            if(item[prop.name]){
                                                element = element + " " + item[prop.name];
                                            }
                                        }

                                        if(prop.suffix){
                                            element = element + " "  + prop.suffix;
                                        }

                                        //element  = element + "</strong></span>";
                                        element  = element + "</span>";

                                        parent = parent + element;

                                    });
                                }

                                return '<div class="row">' +
                                    (item[scope.searchProperty] ? '<span class="col-md-12" style="text-align: left" ><strong>' + escape(item[scope.searchProperty]) + '</strong></span>' : '') +
                                    (parent ? '<div class="col-md-12" style="text-align: left"><strong>' + parent + '</strong></div>' : '') +
                                    '</div>';

                            }
                        },
                        load: function(query, callback) {
                            if (!query.length) return callback();
                            if(scope.load){
                                return callback();
                            }
                            //Add the where query..
                            addWhereQuery(false, function(err){
                                //Only run if no error found..
                                if(!err){
                                    var that = this;
                                    //Now fetch data from the database.
                                    var dbService = Database.loadDb(scope.modelName);
                                    //Deep copying..
                                    var whereObj = $.extend(true, {}, scope.where);
                                    //Force convert object type in case of null..
                                    whereObj = whereObj ? whereObj: {};
                                    whereObj[scope.searchProperty] = {};

                                    whereObj[scope.searchProperty].like = query;


                                    dbService.find({
                                        filter: {
                                            limit: 5,
                                            where: whereObj
                                        }
                                    }, function(values, headers) {
                                        callback(values);
                                    }, function(httpResp) {
                                        console.log(httpResp);
                                        callback();
                                    });
                                }
                            });

                        }, //load function..

                        onItemRemove: function(value, $item){
                            $timeout(function () {
                                //clear the value..
                                scope.value = "";
                            }, 0);
                        },

                        onItemAdd: function(value, $item){
                            var select = $(iElm).selectize();
                            var selectize = select[0].selectize;
                            //Add this value to the scope.
                            var val = $.map(selectize.items, function(value) {
                                return selectize.options[value];
                            });
                            $timeout(function () {
                                //remove the order attribute of selectize..
                                if(val[0].$order){
                                    delete val[0].$order;
                                }
                                scope.value = val[0];

                                $timeout(function(){
                                    //Call the change functon..
                                    scope.onChange();
                                }, 0);

                            }, 0);

                        }
                    }); //END OF Selectize function..
                }; //loadSelectize


                /**
                 * Addd where query dynamically..
                 * @param silent {Boolean}
                 * @param callback
                 * @returns {*}
                 */
                var addWhereQuery = function(silent, callback){
                    var message = "";
                    //If where query is present..
                    if(scope.where){
                        for(var key in scope.where){
                            if(scope.where.hasOwnProperty(key)){
                                var value = scope.where[key];
                                if(!value){
                                    if(scope.whereValidation){
                                        message = scope.whereValidation[key];
                                        message = message ? message : "Validation error! Some required data need to be add first.";
                                        if(!silent){
                                            notifyError(message);
                                        }
                                        break;
                                    }else{
                                        message = "Validation error! Some required data need to be add first.";
                                        if(!silent){
                                            notifyError(message);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if(message){
                        callback(new Error(message));
                    }else{
                        return callback(null);
                    }
                };


                var notifyError = function(message){
                    //Show the validation message..
                    /*Delete the data from the database..*/
                    SnaphyTemplate.notify({
                        message: message,
                        type: 'danger',
                        icon: 'fa fa-times',
                        align: 'left'
                    });
                };



                //adding items programatically..
                function addValue(item){
                    var select = $(iElm).selectize();
                    var selectize = select[0].selectize;
                    var obj = {};
                    obj = item;
                    obj.id = item.id;
                    obj[scope.searchProperty] = item[scope.searchProperty];

                    selectize.addOption(obj);
                    selectize.addItem(item.id);
                }


                scope.$watch('value', function(){
                    if(!$.isEmptyObject(scope.value)){
                        //check if the selectize has that value in options if not then load it..
                        var select = $(iElm).selectize();
                        var selectize = select[0].selectize;
                        //Add this value to the scope.
                        var val = $.map(selectize.items, function(value) {
                            return selectize.options[value];
                        });
                        if(val.length === 0){
                            //Now check if the model has value or not..
                            if(!$.isEmptyObject(scope.value)){
                                //Now add data
                                addValue(scope.value);
                            }
                        }
                    }
                    else{
                        if(!scope.load){
                            $timeout(function(){
                                var select = $(iElm).selectize();
                                var selectize = select[0].selectize;
                                selectize.clear();
                                selectize.clearOptions();
                                selectize.refreshItems();
                                selectize.clearCache();
                            }, 0);
                        }
                    }

                });



                //Load Data in Advance..
                /**
                 * Load data in advance
                 * @param silent {boolean} If silent is true will not throw any error message.
                 */
                var loadDataInAdvance = function(silent){
                    if(scope.load){
                        //Load all the data at once..
                        //Add the where query..
                        addWhereQuery(silent, function(err){
                            //Only run if no error found..
                            if(!err){
                                //Now fetch data from the database..
                                var dbService = Database.loadDb(scope.modelName);
                                //Deep copying..
                                var whereObj = $.extend(true, {}, scope.where);
                                //Force convert object type in case of null..
                                whereObj = whereObj ? whereObj: {};
                                dbService.find({
                                    filter: {
                                        where: whereObj
                                    }
                                }, function(values) {
                                    if(values){
                                        var select = $(iElm).selectize();
                                        var selectize = select[0].selectize;
                                        var options = angular.copy(values);
                                        options.forEach(function(option){
                                            selectize.addOption(option);
                                        });

                                    }
                                }, function(httpResp) {
                                    console.error(httpResp);
                                });
                            }
                        });
                    }
                }; //LoadDataInAdvance....


                var clearDataValues = function () {
                    var select = $(iElm).selectize();
                    var selectize = select[0].selectize;
                    //Clear selectize previous values
                    selectize.clear(true);
                    selectize.clearOptions();
                    selectize.refreshItems();
                    selectize.clearCache();
                };

                //Init selectize..
                (function(){
                    //Load selectize..
                    loadSelectize();
                    loadDataInAdvance(true);
                })(); //Load Method

            } //LInk  function
        }; //END Return
    }])




    //Autocomplete for multi data add..
    .directive('multiSearch', ['Database', '$timeout', function(Database, $timeout) {
        // Runs during compile
        return {
            restrict: 'E',
            replace: true,
            transclude: true,
            scope:{
                "modelName"      : "@modelName",
                "searchProperty" : "@searchProperty",
                //Contains the value of the data.. that needs to be updated.
                "value"          : "=value"
            },
            template: '<select class="selectize"  ng-transclude><option value=""></option></select>' ,
            link: function(scope, iElm, iAttrs, controller) {
                if(!scope.modelName || !scope.searchProperty){
                    console.error("Error >>> searchProperty and modelName attributes are required");
                    return false;
                }

                scope.placeholder = "Search ".toUpperCase() + scope.modelName.toUpperCase() + " " + scope.searchProperty.toUpperCase();
                $(iElm).attr("placeholder", scope.placeholder);

                var selectize_ = $(iElm).selectize({
                    maxItems: 10,
                    valueField: 'id',
                    labelField: scope.searchProperty,
                    searchField: scope.searchProperty,
                    delimiter: ',',
                    persist: false,
                    create: false,
                    render: {
                        option: function(item, escape) {
                            return '<div class="row">' +
                                (item[scope.searchProperty] ? '<span class="col-md-3 " ><strong>' + escape(item[scope.searchProperty]) + '</strong></span>' : '') +
                                '</div>' + 
                                '<div class="row">' +
                                (item[scope.firstName] ? '<span class="col-md-3 " ><strong>' + escape(item[scope.searchProperty]) + '</strong></span>' : '') +
                                '</div>';
                        }
                    },
                    load: function(query, callback) {
                        if (!query.length) return callback();
                        var that = this;
                        //Now fetch data from the database.
                        var dbService = Database.loadDb(scope.modelName);
                        var whereObj = {};
                        whereObj[scope.searchProperty] = {};
                        whereObj[scope.searchProperty].like = query;

                        dbService.find({
                            filter: {
                                limit: 10,
                                where: whereObj
                            }
                        }, function(values, headers) {
                            callback(values);
                        }, function(httpResp) {
                            console.log(httpResp);
                            callback();
                        });
                    }, //load function..

                    onItemAdd: function(value){
                        var select = $(iElm).selectize();
                        var selectize = select[0].selectize;
                        //Add this value to the scope.
                        var val = $.map(selectize.items, function(value) {
                            return selectize.options[value];
                        });

                        if(scope.value === undefined){
                            scope.value = [];
                        }
                        var indexFound;
                        for(var i=0; i < val.length; i++){
                            if( val[i].id.toString().trim() === value.toString().trim()){
                                indexFound = i;
                                break;
                            }
                        }

                        //only add if the value is not present in the model data..
                        for(var j=0; j<scope.value.length; j++){
                            var element = scope.value[j];
                            if(element.id.toString() ===  value){
                                //dont add value just return..
                                return false;
                            }
                        }

                        //remove the order attribute of selectize..
                        $timeout(function () {
                            if(val[indexFound].$order){
                                delete val[indexFound].$order;
                            }
                            //first check if the data doesnot
                            scope.value.push(val[indexFound]);
                        }, 0);
                    }
                }); //END OF Selectize function..



                var select = $(iElm).selectize();
                var selectize = select[0].selectize;
                selectize.on("item_remove", function(value, $item){
                    //Remove the data from the model too..
                    var index = null;
                    for(var i=0; i<scope.value.length; i++){
                        var element = scope.value[i];
                        if(element.id.toString() === value.toString()){
                            index = i;
                            break;
                        }
                    }

                    if(index !== null){
                        $timeout(function(){
                            //remove the element too...
                            scope.value.splice(index, 1);
                        }, 0);
                    }

                });




                //adding items programatically..
                function addValue(item){
                    var select = $(iElm).selectize();
                    var selectize = select[0].selectize;
                    var obj = {};
                    obj = item;
                    obj.id = item.id;
                    obj[scope.searchProperty] = item[scope.searchProperty];

                    selectize.addOption(obj);
                    selectize.addItem(item.id);
                }

                scope.watchModel = function(){
                    if(scope.value){
                        return scope.value.length;
                    }

                };


                scope.$watch('watchModel()', function(){
                    var select = $(iElm).selectize();
                    var selectize = select[0].selectize;
                    //Add this value to the scope.
                    var val = $.map(selectize.items, function(value) {
                        return selectize.options[value];
                    });
                    if(scope.value !== undefined){
                        if(scope.value.length && val.length === 0){
                            scope.value.forEach(function(columnValue){
                                //Now check if the model has value or not..
                                if(!$.isEmptyObject(columnValue)){
                                    //Now add data
                                    addValue(columnValue);
                                }
                            });
                        }else{
                            if(scope.value.length === 0 && val.length){
                                $timeout(function(){
                                    var select = $(iElm).selectize();
                                    var selectize = select[0].selectize;
                                    selectize.clear();
                                }, 0);
                            }else{
                                if(scope.value.length !== val.length){

                                    val.forEach(function(element){
                                        var matchFound = false;
                                        for(var i=0; i<scope.value.length; i++){
                                            var selectedValue = scope.value[i];
                                            if( element[scope.searchProperty].toString().trim() === selectedValue[scope.searchProperty].toString().trim()){
                                                matchFound = true;
                                                break;
                                            }
                                        }
                                        if(!matchFound){
                                            $timeout(function(){
                                                var select = $(iElm).selectize();
                                                var selectize = select[0].selectize;
                                                selectize.removeItem(element.id);
                                            }, 0);
                                        }
                                    });
                                }
                            }
                        }
                    }
                    else{
                        $timeout(function(){
                            var select = $(iElm).selectize();
                            var selectize = select[0].selectize;
                            selectize.clear();
                        }, 0);
                    }

                });//watch


            } //LInk  function
        }; //END Return
    }])




	/**
     * Directive for controlling date..
	 */
	.directive('snaphyRaLoadDate', ['$timeout', "$rootScope", function ($timeout, $rootScope) {
        return {
            restrict: 'A',
            scope:{
                options: "=?options",
                //array with callback mathod..see below..
                setDates: "&setDates",
                defaultMonth: "&defaultMonth"
            },
            link: function (scope, element) {
                var newDateList = [];
                $timeout(function () {
                   scope.options = scope.options || {
                        weekStart: 1,
                        autoclose: true,
                        todayHighlight: true
                   };

                   var options = angular.copy(scope.options);
                   var defaultDate = scope.defaultMonth();

                   $(element).add('.input-daterange').datepicker(options);
                    var dateMethod = scope.setDates();
                    if(dateMethod){
                        dateMethod(function (dates) {
                            if(dates){
                                //New date clear list..
                                newDateList.length = 0;
                                dates.forEach(function (date) {
                                    var newDate = moment().month(defaultDate.month).set('date', date).format("DD/MM/YYYY");
                                    newDateList.push(newDate);
                                });
                                $(element).datepicker('setDates', newDateList);
                            }else{
                                 $(element).datepicker("setStartDate", moment().month(defaultDate.month).startOf("month").toDate());
                                 $(element).datepicker("setEndDate", moment().month(defaultDate.month).endOf("month").toDate());

                            }

                        });
                    }
                });

                $rootScope.$on("invoiceTabsChanged", function(event, options) {
                    $timeout(function () {
                        scope.options = scope.options || {
                                weekStart: 1,
                                autoclose: true,
                                todayHighlight: true
                            };

                        var options = angular.copy(scope.options);
                        var defaultDate = scope.defaultMonth();

                        $(element).add('.input-daterange').datepicker(options);
                        var dateMethod = scope.setDates();
                        if(dateMethod){
                            dateMethod(function (dates) {
                                if(dates){
                                    //New date clear list..
                                    newDateList.length = 0;
                                    dates.forEach(function (date) {
                                        var newDate = moment().month(defaultDate.month).set('date', date).format("DD/MM/YYYY");
                                        newDateList.push(newDate);
                                    });
                                    $(element).datepicker('setDates', newDateList);
                                }else{
                                    $(element).datepicker("setStartDate", moment().month(defaultDate.month).startOf("month").toDate());
                                    $(element).datepicker("setEndDate", moment().month(defaultDate.month).endOf("month").toDate());
                                }
                            });
                        }
                    });
                });
            }//end of link function..
        };
    }]);

