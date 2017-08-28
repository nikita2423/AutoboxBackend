/**
 * Created by robins on 28/8/17.
 */
(function(){'use strict'})();
angular.module($snaphy.getModuleName())
//Define your services here....
    .factory('HelperService', ['$state', 'LoginServices', '$q', "$timeout", "Database", 'SnaphyTemplate',
        function($state, LoginServices, $q, $timeout, Database, SnaphyTemplate)
        {

            /**
             * This method initialize the method for service provider screen..
             * On importing this service this method should always be called at first in all controller..
             * @returns {Promise}
             */
            var initialize = function(){
                return $q(function(resolve, reject){
                    if(settings.get()){
                        if(settings.get().config){
                            if(!settings.get().config.employee){
                                LoginServices.addUserDetail.get()
                                    .then(function(userObj){
                                        settings.get().config.employee = userObj;
                                        settings.get().config.isUserLoaded = true;
                                    })
                                    .then(function(employee){
                                        resolve(settings.get().config.employee);
                                    })
                                    .catch(function(err){
                                        settings.get().config.isUserLoaded = false;
                                        //Call for error object
                                        reject(err);
                                    });
                            }else{
                                settings.get().config.isUserLoaded = true;
                                resolve(settings.get().config.employee);
                            }
                        }else{
                            reject(new Error("Settings object not present"));
                        }
                    }else{
                        reject(new Error("Settings object not present"));
                    }
                });
            };

            //load a tab
            var changeTab = function(Obj){
                var options;
                if(Obj.config.stateOptions){
                    options = Obj.config.stateOptions;
                }
                //Change state..
                $state.go(Obj.config.stateName, options);
            };


            /**
             * Get the settings of current active tab
             * @returns {*}
             */
            var getActiveTabSettings = function(){
                if(!settings.get()) {
                    return {};
                }
                var activeTab = settings.get().config.currentActiveTab;
                if(settings.get().tabs){
                    if(settings.get().tabs){
                        if(settings.get().tabs[activeTab]){
                            return settings.get().tabs[activeTab];
                        }
                    }
                }

                return {};
            };


            /**
             * Highlight the current active state tab
             */
            var setCurrentState = function(){
                var stateDetail = $state.current;
                for(var key in settings.get().tabs) {
                    if (settings.get().tabs.hasOwnProperty(key)) {
                        if(stateDetail.name){
                            if(settings.get().tabs[key].config.stateName === stateDetail.name){
                                (function (key) {
                                    settings.get().tabs[key].config.active = true;
                                    settings.get().config.currentActiveTab = key;
                                })(key);
                            }else{
                                (function (key) {
                                        settings.get().tabs[key].config.active = false;
                                })(key);
                            }
                        }
                    }
                }
            };




            /**
             * Initialize service provider settings..
             */
            var initSettings = function(){
                var settings = {
                    config:{
                        currentActiveTab: "",
                        //contains logged employee object
                        employee: null,
                        isUserLoaded: false
                    },
                    tabs: {
                        dashboard: {
                            load: function () {
                                changeTab(settings.tabs.dashboard);
                            },
                            data: {},
                            title: "Dashboard",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "customerQuote",
                                "modelName": "CustomerQuote",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data is employee data going to be saved..
                                    function(data){

                                    }
                                ]
                                /*onSchemaFetched:[
                                    function(schemaObj){
                                        if(schemaObj){
                                            if(schemaObj.header){
                                                removeArrayProperty(schemaObj.header, 'restaurantTime');
                                            }
                                        }
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ]*/
                            },
                            //List of widgets..
                            widgets: function(){
                                return [
                                    //Todays
                                    {
                                        label: "Today New Bookings",
                                        model: "Booking",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$today",
                                            where:{
                                                serviceProviderId: settings.config.employee.id,
                                                status: "processing"
                                            },
                                            dateProp: 'added'
                                        }
                                    }
                                ];
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "dashboard",
                                stateOptions: {},
                                active: false
                            }
                        },
                        monthlyReports: {
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "tourbooking",
                                "modelName": "Booking",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                "where":{
                                    "typeOfPackage": "tourandpackage"
                                },
                                beforeSaveHook: [
                                    //Here data is employee data going to be saved..
                                    function(data){
                                        //Add brandId to the data object..
                                        if(data){
                                            data.serviceProviderId = settings.config.employee.id;
                                        }
                                    }
                                ],
                                onSchemaFetched:[
                                    function(schemaObj){
                                        if(schemaObj){
                                            if(schemaObj.header){
                                                removeArrayProperty(schemaObj.header, 'restaurantTime');
                                            }
                                        }
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ]
                            },
                            load: function () {
                                changeTab(settings.tabs.tour_booking);
                            },
                            data: {},
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "monthlyReports",
                                stateOptions: {},
                                active: false
                            }
                        }
                    }
                };
                return settings;
            };





            /**
             * Creating memoization method for settings..
             * @returns {function}
             */
            var configSettings = function(){
                var settings;
                return {
                    get: function(){
                        if(!settings){
                            this.reset();
                        }
                        return settings;
                    },
                    reset: function(){
                        settings = initSettings();
                    }
                };

            };

            /**
             * return memoization function..
             * @type {{get, reset}|Function}
             */
            var settings = configSettings();

            return {
                settings: settings,
                //Constructor to be called in each controller...
                initialize: initialize,
                setCurrentState: setCurrentState,
                getActiveTabSettings: getActiveTabSettings

            };
        }]);