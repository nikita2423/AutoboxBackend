'use strict';

angular.module($snaphy.getModuleName())

//Controller for robustAutomataControl ..
.controller('robustAutomataControl', ['$scope', '$stateParams', 'Database', 'Resource', '$timeout', 'SnaphyTemplate', '$state', 'ImageUploadingTracker', '$filter', 
    function($scope, $stateParams, Database, Resource, $timeout, SnaphyTemplate, $state, ImageUploadingTracker, $filter) {
        //Checking if default templating feature is enabled..



        //------------------------------------------------------GLOBAL VARIABLE SPACE----------------------------------------------------------
        var ctrl = $scope;
        //Storing an instance of table values..
        $scope.rowListValues = $scope.rowListValues || [];
        //Schema of the database
        $scope.schema = $scope.schema  || {};
        /*Data for save form modal*/
        $scope.saveFormData = $scope.saveFormData || {};
        //Initializing scope //for array..
        //$scope.displayed = $scope.displayed || [];
        //contains backup of the data..
        var backupData = backupData || {};
        var dataFetched = dataFetched || false;
        //get the current state name..
        var currentState = $state.current.name;
        $scope.currentState = currentState;
        var defaultTemplate = $snaphy.loadSettings('robustAutomata', "defaultTemplate");
        $scope.databasesList = $snaphy.loadSettings('robustAutomata', "loadDatabases");
        //Id for tablePanel
        var tablePanelId = $snaphy.loadSettings('robustAutomata', "tablePanelId");
        $snaphy.setDefaultTemplate(defaultTemplate);
        $scope.displayed = [];
        $scope.pagesReturned = null ;
        //Inline search data object
        $scope.inlineSearch = {};
        //Converting camel case to spaces..
        var camelCaseToSpaces = $filter('camelCaseToSpaces');
        var removeSpaces = $filter('removeSpaces');
        //Track filter state..
        var filterReset = false;
        var resetPage = false;
        //Tracking the input plugin one time initialize...like select2, datepicker.
        var inputPluginInitialize = {
            date : false,
            select2: false
        };
        //--------------------------------------------------------------------------------------------------------------------------------------

        $scope.initializePlugin = function(pluginList){
            if(inputPluginInitialize){
                if(pluginList){
                    if(pluginList.length){
                        pluginList.forEach(function(pluginName){
                            if(!inputPluginInitialize[pluginName]){
                                if(pluginName === "select2"){
                                    $('.js-select2').select2();
                                }else if(pluginName === 'datepicker'){
                                    App.initHelpers(['datepicker']);
                                }else{
                                    App.initHelpers([inputPluginInitialize[pluginName]]);
                                }
                                inputPluginInitialize[pluginName] = true;
                            }
                        });
                    }
                }
            }
        }; 



        $scope.toJsDate = function(str) {
            if (!str) {
                return null;
            }
            return new Date(str);
        };




        $scope.getCurrentState = function() {
            return $state.current.name;
        };



        $scope.scroll = function(){
            //Scroll
            $timeout(function(){
                App.layout('side_scroll_off');
            }, 0);
        };



        $scope.goToParentState = function() {
            if ($scope.$parent.currentState) {
                $state.go(removeSpaces($scope.$parent.currentState));
            }
        };



        $scope.checkIfParentState = function() {
            if ($scope.databasesList) {
                for (var i = 0; i < $scope.databasesList.length; i++) {
                    var state = $scope.databasesList[i];
                    if (state.toLowerCase().trim() === $state.current.name.toLowerCase().trim()) {
                        return true;
                    }
                }
            }
            return false;
        };



        $scope.checkType = function(rowObject, columnHeader) {
            var colValue = $scope.getColValue(rowObject, columnHeader);
            return Object.prototype.toString.call(colValue);
        };
        

        $scope.getColValue = function(rowObject, columnHeader) {
            var key = $scope.getKey(rowObject, columnHeader);

            return key !== undefined ? rowObject[key] : null;
        };


        $scope.dateInSeconds = function(rowObject, columnHeader, colKey) {
            var date;
            if(colKey){
                //For related type object..
                var colValue = $scope.getRelationColumnValue(rowObject, columnHeader, colKey);
                date = $scope.toJsDate(colValue);
                if(!date){
                    return null;
                }else{
                    return date.getTime();
                }
            }
            else{
                var val = $scope.getColValue(rowObject, columnHeader);
                date = new Date(val);
                return date.getTime();
            }
        };




        $scope.addWhereQuery = function(model, columnName, filterType, schema){
            resetPage = true;
            $scope.where = $scope.where  || {};
            if(filterType === "select"){
                if(model){
                   $scope.where = prepareWhereQuery($scope.where, filterType, columnName, model);
                    //Now redraw the table..
                   $scope.refreshData();
                }


            }else if (filterType === "number") {
                //console.log("select", columnName, model);
                if(model){
                    $scope.where = prepareWhereQuery($scope.where, filterType, columnName, model);
                    //Now redraw the table..
                    $scope.refreshData();
                }
            }
            else if (filterType === "date") {
                //console.log("select", columnName, model);
                if(model){
                    $scope.where = prepareWhereQuery($scope.where, filterType, columnName, model);
                    //Now redraw the table..
                    $scope.refreshData();
                }


            }else if(/^related.+/.test(filterType)){
                if(model){
                    //First find the data....
                    if(schema.tables){
                        var keyName = columnName.replace(/\./, "_");
                        if(schema.tables[keyName]){
                            //Define a $scope variable for watching query related to..each models.
                            $scope.watchRelatedModels = $scope.watchRelatedModels || {};

                            var tableProp = schema.tables[keyName];
                            var modelName = tableProp.relatedModel;
                            var foreignKey = tableProp.foreignKey;
                            var searchProp = tableProp.propertyName;
                            //Now first find the related values then add where query..
                            var dbService = Database.loadDb(modelName);
                            $scope.watchRelatedModels[modelName] = $scope.watchRelatedModels[modelName] || {};
                            $scope.watchRelatedModels[modelName].filter = $scope.watchRelatedModels[modelName].filter || {};
                            $scope.watchRelatedModels[modelName].filter.where  = $scope.watchRelatedModels[modelName].filter.where || {};
                            $scope.watchRelatedModels[modelName].filter.limit  = 10;
                            $scope.watchRelatedModels[modelName].filter.fields =  { id: true };
                            //Preparing the where query..
                            $scope.watchRelatedModels[modelName].filter.where = prepareWhereQuery($scope.watchRelatedModels[modelName].filter.where, tableProp.type, searchProp, model);

                            dbService.find({
                                filter: $scope.watchRelatedModels[modelName].filter
                            }, function(values){
                                //console.log(values);
                                //get the ids list..
                                if(values){
                                    if(values.length){
                                        //TODO only create if undefined..
                                        
                                        var idList = [];
                                        for(var i=0; i<values.length; i++){
                                            //Collect the ids
                                            var data = values[i];
                                            idList.push(data.id);
                                        }


                                        //now prepare the where query..
                                        if(idList.length){
                                            //NOw remove the duplicates..
                                            idList = arrayUnique(idList);

                                            //PREPARE THE WHERE QUERY..
                                            $scope.where[foreignKey] = {
                                                inq: idList
                                            };
                                            //Now redraw the table..
                                            $scope.refreshData();
                                        }
                                    }else{
                                        //Clear the data list..
                                        $scope.clearData();

                                    }
                                }else{
                                    
                                    //Clear the data..list
                                    $scope.clearData();
                                }
                            }, function(err){
                                console.error(err);
                            });

                        }
                    }

                }

            }

        };



        /**
         * Prepare where query for model. To be used in searching of  models. Used in method addWhereQuery.
         * @param  {Object}          where      Where type object for for filtering the database. example {customerId: "324edfcs"}
         * @param  {String}          searchType "select", "number", "date", "text"
         * @param  {String}          columnName Name of column on which where query is applied.
         * @param  {String or Object} data      Data assosiated with where query.
         * @return {Object}                     Modified "where" type object for for filtering the database. example {customerId: "324edfcs"}
         */
        var prepareWhereQuery = function(where, searchType, columnName, data){
            if(data){
                if(columnName){
                    //Type may be of 3 types "select", "date", "text", "number"
                    if(searchType === "select" || searchType === "number"){
                        where[columnName] = data;
                    }else if(searchType === "date"){
                        //TODO CHANGE HERE TO NOT RESET EVERYTIME..
                        where.and = [];
                        var obj = {};
                        obj[columnName] = {"gte" : new Date(data) };
                        where.and.push(obj);
                    }else{
                        where[columnName] = {
                            like : data
                        };
                    }
                }
                        
                
            }
            
            return where;   
        };



        /**
         * change prop like access_level to access only
         * Get the key or the relationship name.
         * @param rowObject
         * @param columnHeader
         * @returns {*}
         */
        $scope.getKey = function(rowObject, columnHeader) {
            var keyName;
            if (rowObject) {
                if (rowObject[columnHeader] !== undefined) {
                    keyName = columnHeader;
                } else {
                    //Its a relational header properties name... map the header.. replace `customer_name` to name
                    var patt = /\.[A-Z0-9a-z\-_\$]+$/;
                    keyName = columnHeader.replace(patt, '');
                }
            }
            return keyName;
        };



        var convertToUnderScore = function(key){
            if(key){
                return key.replace(/\./, "_");
            }
            return "";
        };



        //Check if to show the text filter..
        $scope.showFilterType = function(header, schema){
            if(schema.tables){
                var keyName = convertToUnderScore(header);
                if(schema.tables[keyName]){
                    var tableProp = schema.tables[keyName];
                    if(!tableProp){
                        return null;
                    }
                    if(tableProp.search !== "related"){
                        return tableProp.search;
                    }else{
                        var type = tableProp['type'];
                        if(type === "date"){
                            return "related.date";
                        }
                        else if( type === "select"){
                            return "related.select";
                        }
                        else if( type === "number"){
                            return "related.number";
                        }else{
                            return "related.text";
                        }
                    }
                }
            }

            return null;
        };


        $scope.getOptions = function(header, schema){
            if(schema.tables){
                var keyName = header.replace(/\./, "_");
                if(schema.tables[keyName]){
                    var tableProp = schema.tables[keyName];
                    if(tableProp.search === "select" || tableProp.type === "select"){
                        if(tableProp.options){
                            return tableProp.options;
                        }
                    }
                }
            }

            return null;
        };

        //Example addInlineFilterResetMethod('#automataTable', 'number', inlineSearch, header)
        $scope.addInlineFilterResetMethod = function(tableId, type, modelObj, columnName){
            if(type === "select" || type === "related.select"){
                var element = $(tableId);
                //Now add a Reset method to the filter..
                $scope.addResetMethod(function(){
                    //console.log("Resetting select");
                    if(modelObj[columnName]){
                        modelObj[columnName] = null;
                    }

                    try{
                        //Now reinitialize the
                        setTimeout(function() {
                            $($(element).find('.js-select2')).select2('val', 'All');
                        }, 0);
                    }
                    catch(e){

                    }
                        
                });
            }else if (type === "text" || type === "number" || type === "date" || type === 'related') {
                $scope.addResetMethod(function(){
                    $timeout(function(){
                        //$($(element).find('input')).val("");
                        //console.log("Resetting ", type);
                        if(modelObj[columnName]){
                            modelObj[columnName] = null;
                        }
                    });
                });
            }else{
                //Do nothing..
            }
        };


        /**
         * change prop like access-level to level only
         * Get the model properties name on the case of belongsTo or hasOne relationships..
         * @param columnHeader
         */
        $scope.getColumnKey = function(columnHeader) {
            //var keyName;
            var patt = /^[A-Z0-9a-z\-_\$]+\./;
            return columnHeader.replace(patt, '');
        };



        //TO be used in tables..
        $scope.getRelationColumnValue = function(rowObject, header, colKey) {
            var colValue = $scope.getColValue(rowObject, header);
            var isBelongToRelation = header !== colKey;
            var hasOneRelationPropName = $scope.getColumnKey(header);
            return (isBelongToRelation) ? colValue[hasOneRelationPropName] : colValue;
        };




        $scope.getRelationColumnType = function(rowObject, header, colKey, initialColumnType) {
            var colValue = $scope.getRelationColumnValue(rowObject, header, colKey);
            var hasOneRelationPropName = $scope.getColumnKey(header);
            var isBelongToRelation = header !== colKey;
            return (isBelongToRelation) ? $scope.checkType(colValue, hasOneRelationPropName) : initialColumnType;
        };



        /**
         * Find model property for the table configuration from the config file
         * @param  {object} configModelTableObj [description]
         * @param  {string} propertyName        [description]
         * @return {object}                     [description]
         */
        $scope.findModelPropertyTableConfig = function(configModelTableObj, propertyName) {
            //Convert dot to underscore..
            var propertyName = convertToUnderScore(propertyName);
            //get the property parameters..
            var ModalpropertyObj = configModelTableObj;
            if (ModalpropertyObj === undefined) {
                return null;
            }
            if (ModalpropertyObj[propertyName] !== undefined) {
                return ModalpropertyObj[propertyName];
            }
            return null;
        };


        /**
         * Return the params for ui-sref for onClick
         * @param params
         * @param rowObject
         * @returns {*}
         */
        $scope.getParams = function(params, rowObject) {
            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    if(rowObject[key]){
                        var searchKey =  params[key];
                        if(searchKey){
                            if(rowObject[searchKey]){
                                params[key] = rowObject[searchKey];
                            }
                        }

                    }
                }
            }
            return params;
        };



        /**
         * Event listener for adding reset button to the filters. To be called when reset button is called..
         */
        var resetFilterList = [];
        $scope.addResetMethod = function(func) {
            resetFilterList.push(func);
        };





        var resetSavedForm = function(form) {
            //TODO POSSIBILITY FOR ERROR
            //reset the tracking bar..
            ImageUploadingTracker.resetTracker();
            $scope.saveFormData = {};
            if (form) {
                form.$setPristine();
            }
            //Also reset the validator..
            //var validator = form.validate();
            //console.log(form);
            //console.log(form.resetForm());
            //validator.resetForm();
            //var validator = $(form).validate();
        };



        $scope.resetSavedForm = resetSavedForm;


        $scope.enableButton = function(form) {
            try {
                if (form.$dirty) {
                    if ($.isEmptyObject(form.$error)) {
                        return true;
                    }
                } else {
                    return false;
                }
            } catch (err) {
                //disable button
                return true;
            }
        };



        /**
         * For resetting all filter on reset button click..
         */
        $scope.resetAll = function() {
            //TODO POSSIBILITY FOR ERROR
            //reset the tracking bar..
            ImageUploadingTracker.resetTracker();

            //Reset the filter for tracking model where query for facilitating the model search filter..
            $scope.watchRelatedModels = {};

            for (var i = 0; i < resetFilterList.length; i++) {
                //Now call each method..
                resetFilterList[i]();
            }
            //Set reset filter state to be true..
            filterReset = true;
            $scope.resetTable();
            //$scope.refreshData();
        };




        /**
         * Initialize the edit form data from editing the form.
         * @param  {[type]} data [description]
         * @return {[type]}           [description]
         */
        $scope.prepareDataForEdit = function(data, form) {
            //console.log(form);
            //First reset the previous data..
            resetSavedForm(form);

            //Firsst create a backup of the the data in case of rollback changes/cancel
            backupData = angular.copy(data);
            $scope.saveFormData = data;
        };


        /**
         * Method for deleting data from database..
         * @param  {[type]} rowObject [description]
         * @return {[type]}           [description]
         */
        $scope.deleteData = function(formStructure, data) {
            //get the model service..
            var baseDatabase = Database.loadDb(formStructure.model);
            $scope.dialog = {
                message: "Do you want to delete the data?",
                title: "Confirm Delete",
                onCancel: function() {
                    /*Do nothing..*/
                    //Reset the dialog bar..
                    $scope.dialog.show = false;
                },
                onConfirm: function() {
                    var mainArrayIndex = getArrayIndex($scope.displayed, data.id);
                    var oldDeletedData = $scope.displayed[mainArrayIndex];
                    //console.log(data);

                    //Reset the disloag bar..
                    $scope.dialog.show = false;
                    baseDatabase.deleteById({
                        id: data.id
                    }, function() {
                        /*Delete the data from the database..*/
                        SnaphyTemplate.notify({
                            message: "Data successfully deleted.",
                            type: 'success',
                            icon: 'fa fa-check',
                            align: 'left'
                        });
                    }, function() {
                        $timeout(function() {
                            //Attach the data again..
                            $scope.displayed.push(oldDeletedData);
                        }, 10);

                        //console.error(respHeader);
                        SnaphyTemplate.notify({
                            message: "Error deleting data.",
                            type: 'danger',
                            icon: 'fa fa-times',
                            align: 'left'
                        });
                    });
                    //Now delete the data..
                    $scope.displayed.splice(mainArrayIndex, 1);
                },
                show: true
            };

        };



        /**
         * For finding array index of the data of array of objects with properties id..
         * @return {[type]} [description]
         */
        var getArrayIndex = function(arrayData, id) {
            for (var i = 0; i < arrayData.length; i++) {
                var element = arrayData[i];
                if (element.id.toString().trim() === id.toString().trim()) {
                    return i;
                }
            }
            return null;
        };

        /**
         * Method  for checking if the automata form is valid.
         * @param  {[type]} schema template schema object with property fields showing all the fields.
         * @return {[type]}        [description]
         */
        $scope.isValid = function(form) {
            try {
                //TODO Removing find alternate for  form.$dirty
                if (form.validate()) {
                    if ($.isEmptyObject(form.$error)) {
                        return true;
                    }
                }
            } catch (err) {
                return false;
            }

            return false;
        };



        //Method for rollbackchanges is error occured..
        $scope.rollBackChanges = function() {
            if (!$.isEmptyObject(backupData)) {
                $scope.displayed.forEach(function(data, index) {
                    if (data.id === backupData.id && !$.isEmptyObject(backupData)) {
                        //rollback changes..
                        $scope.displayed[index] = backupData;
                        //Reset backup data..
                        backupData = {};
                        return false;
                    }
                });
            }
        };

        /**
         * Fetch the header title. Prefer the label if provided first.
         * @param schema
         * @param header
         * @return {string} Title of header.
         */
        $scope.getHeaderTitle = function(schema, header){
            //First convert the header to optimal type..
            var new_header = header.replace(/\./, "_");
            if (schema.tables) {
                if (schema.tables[new_header]) {
                    if (schema.tables[new_header].label) {
                        return schema.tables[new_header].label;
                    }
                }
            }
            return header;
        };




        /**
         * Check if to display the properties of the table or not.
         * schema {
         * 	tables:{
         * 		username:{
         * 			"display": false
         * 		}
         * 	}
         * }
         */
        $scope.displayProperties = function(schema, header) {
            //First convert the header to optimal type..
            header = header.replace(/\./, "_");
            if (schema.tables) {
                if (schema.tables[header]) {
                    if (schema.tables[header].display !== undefined) {
                        if (!schema.tables[header].display) {
                            return false;
                        }
                    }
                }
            }
            return true;
        };



        $scope.resetBackup = function() {
            backupData = {};
            $scope.saveFormData = {};
        };




        /**
         * Model for storing the model structure..
         * @param formStructure
         * @param formData
         * @param formModel
         * @param goBack
         * @param modelInstance refrencing to the id attribute of the  form.
         */
        $scope.saveForm = function(formStructure, formData, formModel, goBack, modelInstance) {
            if(ImageUploadingTracker.isUploadInProgress()){
                SnaphyTemplate.notify({
                    message: "Wait!! Image uploading is in progress. Please wait till the image is uploaded.",
                    type: 'danger',
                    icon: 'fa fa-times',
                    align: 'left'
                });
                return false;
            }

            if (!$scope.isValid(formData)) {
                SnaphyTemplate.notify({
                    message: "Error data is Invalid.",
                    type: 'danger',
                    icon: 'fa fa-times',
                    align: 'left'
                });

                //If edit was going on revert back..
                if (formModel.id) {
                    $scope.rollBackChanges();
                }
            } else {
                //Now save the model..
                var baseDatabase = Database.loadDb(formStructure.model);

                var schema = {
                    "relation": $scope.schema.relations
                };

                var requestData = {
                    data: formModel,
                    schema: schema
                };

                //create a copy of the data..
                var savedData = angular.copy(formModel);
                var positionNewData;
                var update;
                if (formModel.id) {
                    update = true;

                } else {
                    positionNewData = $scope.displayed.length;
                    //First add to the table..
                    $scope.displayed.push(savedData);
                    update = false;
                }


                //Now save||update the database with baseDatabase method.
                baseDatabase.save({}, requestData, function(baseModel) {
                    if (!update) {
                        //Now update the form with id.
                        $scope.displayed[positionNewData].id = baseModel.data.id;
                    }
                    SnaphyTemplate.notify({
                        message: "Data successfully saved.",
                        type: 'success',
                        icon: 'fa fa-check',
                        align: 'left'
                    });
                }, function(respHeader) {
                    //console.log("Error saving data to server");
                    //console.error(respHeader);
                    var message = "Error saving data.";
                    if(respHeader){
                        if(respHeader.data){
                            if(respHeader.data.error){
                                if(respHeader.data.error.message){
                                    message = respHeader.data.error.message;
                                }
                            }
                        }
                    }
                    
                    if (update) {
                        $scope.rollBackChanges();
                    } else {
                        //remove the form added data..
                        if (positionNewData > -1) {
                            $scope.displayed.splice(positionNewData, 1);
                        }
                    }

                    //console.error(respHeader);
                    SnaphyTemplate.notify({
                        message: message,
                        type: 'danger',
                        icon: 'fa fa-times',
                        align: 'left'
                    });
                });

                //Now reset the form..
                resetSavedForm(formData);
                closeModel(goBack, modelInstance);

            }
        }; //saveForm


        // Used in  the automata to get the table values..
        $scope.getTagInfo = function(tableSchema, colKey, rowObject, header) {
            var tableConfig = $scope.findModelPropertyTableConfig(tableSchema, colKey);
            var colValue = $scope.getColValue(rowObject, header);
            return tableConfig.tag[colValue];
        };



        /*Get related data tag info*/
        $scope.getRelatedDataTagInfo = function(tableConfig, colKey, rowObject, header){
            var colVal = $scope.getRelationColumnValue(rowObject, header, colKey);
            if(tableConfig){
                if(tableConfig.tag){
                    var tagData = tableConfig.tag[colVal];
                    return tagData;
                }
            } 
        };


        //Goback or close the model..
        var closeModel = function(goBack, modelInstance) {
            //Reset the image upload if any...
            ImageUploadingTracker.resetTracker();

            if (goBack) {
                if (modelInstance) {
                    //close the model..
                    $(modelInstance).modal('hide');
                } else {
                    //go back to parent state..
                    $scope.goToParentState();
                }
            }
        };




        /**
         * Checking if the data is fetched return a boolean
         * @return {Boolean} [description]
         */
        $scope.isDataFetched = function() {
            if (dataFetched && $scope.schema.header) {
                return true;
            }
            return false;
        };


        //checking if the filters is present in the data..
        $scope.isFilterPresent = function() {
            if ($scope.schema.filters) {
                for (var filterName in $scope.schema.filters) {
                    if ($scope.schema.filters.hasOwnProperty(filterName)) {
                        return true;
                    }
                }
            }
            return false;
        };


        
        //Initialize default names of the current state..
        var init = function() {
            for (var i = 0; i < $scope.databasesList.length; i++) {
                if (currentState.toLowerCase().trim() === $scope.databasesList[i].toLowerCase().trim()) {
                    $scope.currentState = camelCaseToSpaces(currentState);
                    $scope.tableTitle = $scope.currentState + ' ' + 'Data';
                    
                    $scope.title = $scope.currentState + ' Console';
                    $scope.description = "Data management console.";
                    break;
                }
            }
        };



        var getDatabase = function(databaseName, tableState, ctrl){
            if (!$scope.stCtrl && ctrl) {
                $scope.stCtrl = ctrl;
            }
            if (!tableState && $scope.stCtrl) {
                $scope.stCtrl.pipe();
                return;
            }

            $scope.isLoading = true;
            var pagination = tableState.pagination;
            var start = tableState.pagination.start || 0; // This is NOT the page number, but the index of item in the list that you want to use to display the table.
            var number = pagination.number || 10; // Number of entries showed per page.
            //If a page is reset state i.e some filter is applied the move back to 1 page..
            if(resetPage){
                tableState.pagination.start = 0;
                start = 0;
                resetPage = false;
            }
            
            if(filterReset){
                tableState.pagination.start = 0;
                start = 0;
                //Reset the search parameterts..
                tableState.pagination.search = {};
                //Also reset the search filters
                tableState.search = {};
                //Again reset back to false.. 
                filterReset = false; 
            }

            //Add the loading bar..
            if (tablePanelId) {
                $timeout(function() {
                    //Now hide remove the refresh widget..
                    $(tablePanelId).addClass('block-opt-refresh');
                }, 200);
            }
            if ($.isEmptyObject($scope.schema )) {

                //First get the schema..
                Resource.getSchema(databaseName, function(schema) {
                    //Populate the schema..
                    $scope.schema = schema;
                    //console.log(schema);
                    $scope.where = $scope.where || {};

                    Resource.getPage(start, number, tableState, databaseName, schema, $scope.where).then(function(result) {
                        $scope.displayed = result.data;
                        tableState.pagination.numberOfPages = result.numberOfPages; //set the number of pages so the pagination can update
                        $scope.pagesReturned = result.numberOfPages;
                        $scope.totalResults = result.count;
                        $scope.isLoading = false;
                        dataFetched = true;
                        if (tablePanelId) {
                            $timeout(function() {
                                //Now hide remove the refresh widget..
                                $(tablePanelId).removeClass('block-opt-refresh');
                            }, 200);
                        }
                    });
                }, function(httpResp){
                    console.error(httpResp);
                    if (tablePanelId) {
                        $timeout(function() {
                            //Now hide remove the refresh widget..
                            $(tablePanelId).removeClass('block-opt-refresh');
                        }, 200);
                    }
                });
            }else{
                Resource.getPage(start, number, tableState, databaseName, $scope.schema, $scope.where).then(function(result) {
                    $scope.displayed = result.data;
                    tableState.pagination.numberOfPages = result.numberOfPages; //set the number of pages so the pagination can update
                    $scope.pagesReturned = result.numberOfPages;
                    $scope.totalResults = result.count;
                    $scope.isLoading = false;
                    dataFetched = true;
                    if (tablePanelId) {
                        $timeout(function() {
                            //Now hide remove the refresh widget..
                            $(tablePanelId).removeClass('block-opt-refresh');
                        }, 200);
                    }
                },function(httpResp){
                    console.error(httpResp);
                    if (tablePanelId) {
                        $timeout(function() {
                            //Now hide remove the refresh widget..
                            $(tablePanelId).removeClass('block-opt-refresh');
                        }, 200);
                    }

                    //console.error(respHeader);
                    SnaphyTemplate.notify({
                        message: "Error occured. Please click on the reset button to go back to normal.",
                        type: 'danger',
                        icon: 'fa fa-times',
                        align: 'left'
                    });
                });
            }
        };


        //Clear the data showing in the table.
        $scope.clearData = function(){
            $scope.displayed.length = 0;
            $scope.pagesReturned = 0;
            $scope.totalResults = 0;

        };


        $scope.refreshData = function(tableState, ctrl) {
            for (var i = 0; i < $scope.databasesList.length; i++) {
                if (currentState.toLowerCase().trim() === $scope.databasesList[i].toLowerCase().trim()) {
                    getDatabase($scope.databasesList[i], tableState, ctrl);
                    break;
                }
            }
        };

        $scope.resetTable = function(){

            //reset the table filters
            $scope.where = {};
            $scope.refreshData();
        };

        //http://stackoverflow.com/questions/1584370/how-to-merge-two-arrays-in-javascript-and-de-duplicate-items
        /**
         * Method to remove the duplicate entries from an array
         * @return {[type]} [description]
         */
        function arrayUnique(array) {
            var a = array.concat();
            for(var i=0; i<a.length; ++i) {
                for(var j=i+1; j<a.length; ++j) {
                    if(a[i] === a[j])
                        a.splice(j--, 1);
                }
            }

            return a;
        }


        //Only load if the current scope is automata
        init();


    } //controller function..
]);
