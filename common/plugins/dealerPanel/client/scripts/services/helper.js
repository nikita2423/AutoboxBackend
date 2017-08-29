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
                    settings.get().config.isUserLoaded = false;
                    if(settings.get()){
                        if(settings.get().config){
                            if(!settings.get().config.employee){
                                
                                LoginServices.addUserDetail.get()
                                    .then(function(userObj){
                                        settings.get().config.employee = userObj;
                                        settings.get().config.isUserLoaded = true;
                                        var workshopService = Database.getDb("dealerPanel", "Workshop");
                                        return workshopService.findById({id:userObj.workshopId,
                                            filter:{
                                                include:{
                                                    relation: "area",
                                                    "scope":{
                                                        fields:["id", "name"]
                                                    }
                                                }
                                            }
                                        } , function(workshop){
                                              if(workshop){
                                                settings.get().config.employee.workshop = workshop;
                                              }      
                                        });
                                    })
                                    .then(function(workshop){
                                        
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
                                        schema: window.STATIC_DATA.schema.Dealer,
                                        label: "Today New Bookings",
                                        model: "Dealer",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$today",
                                            where:{
                                                brandId: settings.config.employee.brandId
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
                            load: function () {
                                changeTab(settings.tabs.monthlyReports);
                            },
                            data: {},
                            title: "Monthly Reports",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "customerQuoteReport",
                                "modelName": "CustomerQuote",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "monthlyReports",
                                stateOptions: {},
                                active: false
                            }
                        },
                        soldViaAutobox: {
                            load: function () {
                                changeTab(settings.tabs.soldViaAutobox);
                            },
                            data: {},
                            title: "Sold via Autobox",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "customerQuoteViaAutobox",
                                "modelName": "CustomerQuote",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "soldViaAutobox",
                                stateOptions: {},
                                active: false
                            }
                        },
                        customerCallMessage: {
                            load: function () {
                                changeTab(settings.tabs.customerCallMessage);
                            },
                            data: {},
                            title: "Customer Call Message",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "customerCallMessage",
                                "modelName": "CustomerMessage",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "customerCallMessage",
                                stateOptions: {},
                                active: false
                            }
                        },
                        manageWorkshopProfile: {
                            load: function () {
                                changeTab(settings.tabs.manageWorkshopProfile);
                            },
                            data: {},
                            title: "Manage Workshop Profile",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "workshop",
                                "modelName": "Workshop",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "manageWorkshopProfile",
                                stateOptions: {},
                                active: false
                            }
                        },
                        manageShowroomProfile: {
                            load: function () {
                                changeTab(settings.tabs.manageShowroomProfile);
                            },
                            data: {},
                            title: "Manage Showroom Profile",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "showroom",
                                "modelName": "Showroom",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "manageShowroomProfile",
                                stateOptions: {},
                                active: false
                            }
                        },
                        orderGPSTracker: {
                            load: function () {
                                changeTab(settings.tabs.orderGPSTracker);
                            },
                            data: {},
                            title: "Manage Showroom Profile",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "orderGpsTracker",
                                "modelName": "CustomerQuote",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "orderGPSTracker",
                                stateOptions: {},
                                active: false
                            }
                        },
                        orderDashCamera: {
                            load: function () {
                                changeTab(settings.tabs.orderDashCamera);
                            },
                            data: {},
                            title: "Manage Showroom Profile",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "orderGpsTracker",
                                "modelName": "CustomerQuote",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "orderDashCamera",
                                stateOptions: {},
                                active: false
                            }
                        },
                        feedback: {
                            load: function () {
                                changeTab(settings.tabs.feedback);
                            },
                            data: {},
                            title: "Feedback and Help",
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "feedback",
                                "modelName": "Feedback",
                                "action":{
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ],
                                /* onSchemaFetched:[
                                    function(schemaObj){
                                        //delete schemaObj.header;
                                        return schemaObj;
                                    }
                                ] */
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "feedback",
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