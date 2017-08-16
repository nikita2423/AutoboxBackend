/**
 * Created by snaphy on 23/12/16.
 */
(function(){'use strict'})();
angular.module($snaphy.getModuleName())
//Define your services here....
    .factory('TableViewService', ['DetailViewResource', 'ImageUploadingTracker', 'SnaphyCache', 'SnaphyTemplate', 'TableViewResource', 'Resource', 'Database', '$q',
        function(DetailViewResource, ImageUploadingTracker, SnaphyCache, SnaphyTemplate, TableViewResource, Resource, Database, $q)
        {
            /**
             * Will initialize the tabular data of tableView
             * Utilizes the concept of memoization and closure
             */
            var tableViewInit = function($scope){
                var cache = {};
                return function(relationDetail, relationType){
                    var relationName = relationDetail.relationName;
                    if(relationName){
                        //Reset the data and create a blank object..
                        /**
                         * @param schema optional if provided the adds schema object..
                         */
                        var resetData = function(schema){
                            DetailViewResource.extend(cache[relationName], relationDetail);
                            //Also add the relation type..
                            cache[relationName].relationType = cache[relationName].relationType || relationType;
                            if(schema){
                                //Absolute schema that is fetched from server..
                                cache[relationName].schema = schema;
                            }else{
                                //Absolute schema that is fetched from server..
                                cache[relationName].schema = {};
                            }

                            //Data to be displayed in the table..
                            cache[relationName].displayed = [];
                            if(relationDetail.where){
                                angular.copy(relationDetail.where, cache[relationName].where);
                            }else{
                                //Where object for adding filtering..
                                cache[relationName].where = {};
                            }

                            //Array to store before save hook methods..
                            /**
                             * Example [
                             *   function(data){
                         *      //Here data is object going to be save..
                         *   }
                             * ]
                             * @type {Array}
                             */
                            cache[relationName].beforeSaveHook  = cache[relationName].beforeSaveHook || [];
                            cache[relationName].onSchemaFetched = cache[relationName].onSchemaFetched || [];

                            if(relationDetail.searchId){
                                cache[relationName].where[relationDetail.searchId] = modelId;
                            }

                            //This object all the settings related to current dataContainer of table view.
                            cache[relationName].settings = {
                                filterReset : false,
                                resetPage : false,
                                //tracking if absoluteSchema is fetched or not..
                                schemaFetched: false,
                                isLoading: true,
                                pagesReturned: 0,
                                totalResults: 0,
                                totalNumberOfRows: 20,
                                //Reset the filter for tracking model where query for facilitating the model search filter..
                                watchRelatedModels: {},
                                saveFormData: {},
                                //Creates a backup of data  to be performed while in edit mode..
                                backupData: {},
                                //Inline search data object
                                //Store data of inline search associated with each table header.
                                inlineSearch:{}
                            };
                        };

                        //Start memoization..
                        if(!cache[relationName]){
                            cache[relationName] = {};
                            //These are those data that are not to be deleted on each data reset request..
                            cache[relationName].persistentData = {};
                            cache[relationName].resetFilterList = [];
                            resetData();
                        }


                        /**
                         * For resetting all filter and table on reset button click..
                         */
                        var resetAll = function() {
                            //reset the tracking bar..
                            ImageUploadingTracker.resetTracker();
                            var schema = cache[relationName].schema;
                            //Now reset the data..
                            resetData(schema);

                            //TODO: Uncomment it later..
                            for (var i = 0; i < getCache().resetFilterList.length; i++) {
                                //Now call each method..
                                getCache().resetFilterList[i]();
                            }
                            //Set reset filter state to be true..
                            cache[relationName].settings.filterReset = true;
                            //Now reload the table again..
                            refreshData();
                        };

                        /**
                         * Fetch the cache object..
                         * @returns {*}
                         */
                        var getCache =  function(){
                            return cache[relationName];
                        };



                        //Refresh the data fetched from the table..
                        var refreshData = function(tableState, ctrl) {
                            //--------------------------CALLBACK DEFINED----------------------
                            //Success callback to load data to table..
                            var loadDataToTable = function(result){
                                dataContainer.displayed = result.data;
                                tableState.pagination.numberOfPages = result.numberOfPages; //set the number of pages so the pagination can update
                                dataContainer.settings.pagesReturned = result.numberOfPages;
                                dataContainer.settings.totalResults = result.count;
                                dataContainer.settings.isLoading = false;
                                dataContainer.settings.schemaFetched = true;
                            };

                            //Error callback..
                            var errorCallback = function(httpResp){
                                console.error(httpResp);
                                //Stop the loading bar..
                                dataContainer.settings.isLoading = false;
                                dataContainer.settings.schemaFetched = false;
                                SnaphyTemplate.notify({
                                    message: "Error occured. Please click on the reset button to go back to normal.",
                                    type: 'danger',
                                    icon: 'fa fa-times',
                                    align: 'left'
                                });
                            };
                            //----------------------------------------------------------------


                            //Main container for storing all the data..
                            var dataContainer = cache[relationName];
                            var modelName     = dataContainer.modelName;
                            var searchId      = dataContainer.searchId;

                            if (!dataContainer.persistentData.stCtrl && ctrl) {
                                dataContainer.persistentData.stCtrl = ctrl;
                            }
                            if (!tableState && dataContainer.persistentData.stCtrl) {
                                dataContainer.persistentData.stCtrl.pipe();
                                return;
                            }

                            var pagination = tableState.pagination;
                            var start = tableState.pagination.start || 0; // This is NOT the page number, but the index of item in the list that you want to use to display the table.
                            var number = pagination.number || dataContainer.settings.totalNumberOfRows; // Number of entries showed per page.
                            //If a page is reset state i.e some filter is applied then move back to 1 page..
                            if(dataContainer.settings.resetPage){
                                tableState.pagination.start = 0;
                                start = 0;
                                dataContainer.settings.resetPage = false;
                            }

                            if(dataContainer.settings.filterReset){
                                tableState.pagination.start = 0;
                                start = 0;
                                //Reset the search parameter..
                                tableState.pagination.search = {};
                                //Also reset the search filters
                                tableState.search = {};
                                //Again reset back to false..
                                dataContainer.settings.filterReset = false;
                            }

                            //Add schema from localstorage if  present..
                            if(!$.isEmptyObject(SnaphyCache.getItem(modelName))){
                                dataContainer.schema = SnaphyCache.getItem(modelName);
                            }


                            //If absoluteSchema is not present..
                            if ($.isEmptyObject(dataContainer.schema )) {
                                //First get the schema..
                                Resource.getSchema(modelName, function(schema) {
                                    //Now run on schema fetched hooks..
                                    if(dataContainer.onSchemaFetched){
                                        if(dataContainer.onSchemaFetched.length){
                                            dataContainer.onSchemaFetched.forEach(function(schemaFunc){
                                                schema = schemaFunc(schema);
                                            });
                                        }
                                    }

                                    //Populate the schema..
                                    dataContainer.schema = schema;
                                    //Store the schema to the localstorage..
                                    SnaphyCache.save(modelName, schema);

                                    dataContainer.where = dataContainer.where || {};

                                    if(cache[relationName].relationType === "hasAndBelongsToMany"){
                                        /*Handling special case of hasAndBelongsToMany*/
                                        //Fetch the data from server and populate the table..
                                        fetchData(start, number, tableState, $scope.settings.config.brand.id, dataContainer.where)
                                            .then(loadDataToTable, errorCallback);
                                    }else{
                                        Resource.getPage(start, number, tableState, modelName, schema, dataContainer.where)
                                            .then(loadDataToTable, errorCallback);
                                    }
                                });
                            }else {
                                if (cache[relationName].relationType === "hasAndBelongsToMany") {
                                    /*Handling special case of hasAndBelongsToMany*/
                                    //Fetch the data from server and populate the table..
                                    fetchData(start, number, tableState, $scope.settings.config.brand.id, dataContainer.where)
                                        .then(loadDataToTable, errorCallback);
                                }else {
                                    Resource.getPage(start, number, tableState, modelName, dataContainer.schema, dataContainer.where)
                                        .then(loadDataToTable, errorCallback);
                                }
                            }

                        };



                        //Copy the services method to table View resources..object..
                        var returnObj = TableViewResource(getCache, refreshData, $scope);
                        returnObj.getCache = getCache;
                        returnObj.refreshData = refreshData;
                        returnObj.resetAll = resetAll;
                        return returnObj;
                    } //if relationName
                };
            }; //tableViewInit





            /* HasAndBelongsToMany work area*/
            var fetchData = function(start, number, params, brandId, whereFilter){
                //console.log("Printing whereFilter ", whereFilter);
                var dbService = Database.loadDb("Brand");
                var object = {};
                var filter = {};
                var where = {};
                if(whereFilter){
                    //Now just add new where properties..
                    for(var key in whereFilter){
                        if(whereFilter.hasOwnProperty(key)){
                            where[key] = whereFilter[key];
                        }
                    }
                }

                //where.or = []
                //Prepare filter..
                var skip = start;
                var limit = number;
                var orderBy = [];
                var deferred = $q.defer();
                //track which related model has where filter ..
                var relatedModelWhereFilter = [];


                //Now  prepare the sort and filter and orderBy function..
                if (params.sort) {
                    if (params.sort.predicate) {
                        var sort = params.sort.predicate;
                        var reverse = params.sort.reverse;
                        reverse = reverse ? "DESC" : "ASC";
                        orderBy.push("" + sort + " " + reverse);
                    }
                }

                filter.order = orderBy;
                if (params.search) {
                    if (params.search.predicateObject) {
                        for (var property in params.search.predicateObject) {
                            if (params.search.predicateObject.hasOwnProperty(property)) {
                                if (typeof params.search.predicateObject[property] != 'object') {
                                    var like = params.search.predicateObject[property];
                                    //add to where property..
                                    //where: {title: {like: 'M.+st'}}}
                                    where[property] = {
                                        "like": like
                                    };
                                    //where.or.push({property: {"like" : like} });
                                }else{
                                    //add to realted data..
                                    //{ appUser: {email: "robins"} }
                                    if(filter.include === undefined){
                                        filter.include = [];
                                    }
                                    if(filter.include.length){
                                        for(var i=0; i<filter.include.length; i++){
                                            var includeProp = filter.include[i];
                                            if(includeProp.relation === property){
                                                //Add relation name for tracking..
                                                relatedModelWhereFilter.push(property);
                                                //Now insert the where...
                                                var relationObj = params.search.predicateObject[property];
                                                //var like = params.search.predicateObject[property];
                                                for(var prop in relationObj){
                                                    if(relationObj.hasOwnProperty(prop)){
                                                        var likeValue = relationObj[prop];

                                                        includeProp.scope = includeProp.scope || {};
                                                        includeProp.scope.where = includeProp.scope.where || {};
                                                        // includeProp.scope.where[prop] = {
                                                        //     "like": likeValue
                                                        // };
                                                        includeProp.scope.where[prop] = {
                                                            "like": likeValue
                                                        };
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                filter.skip = skip;
                filter.limit = limit;
                filter.where = where;
                object.filter = filter;
                var filterObj = {};
                filterObj = angular.copy(object, filterObj);
                filterObj.id = brandId;
                dbService['appUsers'](filterObj, function(values) {
                    var whereObj = angular.copy(filter, {});
                    whereObj.id = brandId;
                    dbService['appUsers'].count(whereObj, function(count) {
                        //Now resolve the promise..
                        deferred.resolve({
                            data: values,
                            numberOfPages: Math.ceil(count.count / number),
                            count: count.count
                        });
                    },function(httpResponse) {
                        deferred.reject( httpResponse );
                        //Error counting values
                        console.error(httpResponse);
                    });

                }, function(httpResponse) {
                    deferred.reject( httpResponse );
                    //Error occured..in fetching data..
                    console.error(httpResponse);
                });
                return deferred.promise;
            }; //fetchData method

            /*-----------------------------*/

            return{
                tableViewInit: tableViewInit
            }
        }]);