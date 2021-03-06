(function() {
    'use strict';
})();

angular.module($snaphy.getModuleName())

/**
 *Directive for defining filters $date
 * */
//$date
    .directive('dealerFilterDate', ['$timeout', function($timeout) {

        return {
            restrict: 'E',
            scope: {
                "modelSettings": "=modelSettings",
                "labelClass": "=labelClass",
                "dateClass": "=dateClass",
                "columnName": "@columnName",
                "label": "@label",
                "parentScope": "=parentScope"
            },
            replace: true,
            template: '<div class="form-group">' +
            '<label ng-class="labelClass" class="col-md-1 control-label" for="example-daterange1">{{label}}</label>' +
            '<div ng-class="dateClass">' +
            '<div class="input-daterange input-group" data-date-format="MM">' +
            '<input class="form-control leftDate" type="text"  name="daterange1" placeholder="Select Month">' +
            '</div>' +
            '</div>' +
            '</div>',
            link: function(scope, iElement, iAttrs) {
                console.log(scope.parentScope);
                //Now add a Reset method to the filter..
                scope.parentScope.addResetMethod(function() {
                    $($(iElement).find('input')).val('');
                });
                //Load Date..

                var dateFormat = "MM";
                var format  = {
                    weekStart: 1,
                    autoclose: true,
                    todayHighlight: true,
                    format: dateFormat
                };

                var element1 = $($(iElement).find('input.leftDate'))[0];
                //load the datepicker
                $(element1).datepicker(format);


                var prevFrom = "",
                    prevTo = "";

                //Now applying date change event of the table..
                $($(iElement).find('.input-daterange')).datepicker().on("changeDate", function() {
                    $timeout(function() {
                        var valuesList = $(iElement).find("input");
                        var from = $(valuesList[0]).val();
                        if(from){
                            from = moment.utc(from, "MM").toDate();
                        }else{
                            from = undefined;
                        }

                        scope.parentScope.getCache().where.and = [];
                        //{"where": {and: [{"epoch_time": {"gte":1450717674}},{"epoch_time": {"lte":1459407675}}]} }
                        //Now push value to the  table..
                        //first clear previous data..
                        clear(scope.columnName);

                        if (from) {
                            var fromDate = {};
                            fromDate[scope.columnName] = {
                                "gte": new Date(from)
                            };
                            scope.parentScope.getCache().where.and.push(fromDate);
                            //prevFrom = from;
                        }
                        //Now redraw the table...
                        scope.parentScope.refreshData();
                    });
                });



                //Clear previous value of column data..
                var clear = function(column) {
                    var delIndex = [];
                    scope.parentScope.getCache().where.and.forEach(function(and, index) {
                        prepareDeleteIndex(and, column, delIndex, index);
                    });

                    delIndex.forEach(function(index) {
                        scope.parentScope.getCache().where.and.splice(index, 1);
                    });
                };


                var prepareDeleteIndex = function(and, column, delIndex, index) {
                    for (var key in and) {
                        if (and.hasOwnProperty(key)) {
                            if (key === column) {
                                delIndex.push(index);
                            }
                        }
                    }
                };
            } //link function..
        }; //return
    }]); //filterDate directive