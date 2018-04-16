/**
 * Created by nikita on 12/4/18.
 */
(function(){'use strict'})();
angular.module($snaphy.getModuleName())
//Define your services here....
    .factory('HelperService', ['$state', 'LoginServices', '$q', "$timeout", "Database", 'SnaphyTemplate', 'DetailViewResource', "$rootScope",
        function($state, LoginServices, $q, $timeout, Database, SnaphyTemplate, DetailViewResource, $rootScope)
        {
            /**
             * This method initialize the method for service provider screen..
             * On importing this service this method should always be called at first in all controller..
             * @returns {Promise}
             */
            var initialize = function () {
                return $q(function (resolve, reject) {
                    settings.get().config.isUserLoaded = false;
                    if (settings.get()) {
                        if (settings.get().config) {
                            if (!settings.get().config.employee) {
                                LoginServices.addUserDetail.get()
                                    .then(function (userObj) {
                                        settings.get().config.employee = userObj;
                                        settings.get().config.isUserLoaded = true;
                                        var schoolService = Database.getDb("schoolPanel", "School");
                                        return schoolService.findOne({
                                            filter: {
                                                where: {
                                                    id: userObj.id
                                                }
                                            }
                                        }, function (school) {
                                            if (school) {
                                                settings.get().config.employee.school = school;
                                            }
                                        });
                                    })
                                    .then(function (school) {
                                        resolve(settings.get().config.employee);
                                    })
                                    .catch(function (err) {
                                        settings.get().config.isUserLoaded = false;
                                        //Call for error object
                                        reject(err);
                                    });
                            } else {
                                settings.get().config.isUserLoaded = true;
                                resolve(settings.get().config.employee);
                            }
                        } else {
                            reject(new Error("Settings object not present"));
                        }
                    } else {
                        reject(new Error("Settings object not present"));
                    }
                });
            };


            //load a tab
            var changeTab = function (Obj) {
                var options;
                if (Obj.config.stateOptions) {
                    options = Obj.config.stateOptions;
                }
                //Change state..
                $state.go(Obj.config.stateName, options);
            };


            /**
             * Get the settings of current active tab
             * @returns {*}
             */
            var getActiveTabSettings = function () {
                if (!settings.get()) {
                    return {};
                }
                var activeTab = settings.get().config.currentActiveTab;
                if (settings.get().tabs) {
                    if (settings.get().tabs) {
                        if (settings.get().tabs[activeTab]) {
                            return settings.get().tabs[activeTab];
                        }
                    }
                }

                return {};
            };


            /**
             * Highlight the current active state tab
             */
            var setCurrentState = function () {
                var stateDetail = $state.current;
                for (var key in settings.get().tabs) {
                    if (settings.get().tabs.hasOwnProperty(key)) {
                        if (stateDetail.name) {
                            if (settings.get().tabs[key].config.stateName === stateDetail.name) {
                                (function (key) {
                                    settings.get().tabs[key].config.active = true;
                                    settings.get().config.currentActiveTab = key;
                                })(key);
                            } else {
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
            var initSettings = function () {
                var settings = {
                    config: {
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
                            isMonthlyReports: false,
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "student",
                                "modelName": "Student",
                                "action": {
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },

                                beforeSaveHook: [
                                    //Here data is employee data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "dashboard",
                                stateOptions: {},
                                active: false,
                                columnName: "added",
                                modelSetting: {}
                            }
                        },
                        schoolProfile: {
                            load: function(){
                                changeTab(settings.tabs.schoolProfile);
                            },
                            data: {},
                            title : "School",
                            relationDetail: {
                                "relationName": "schoolProfile",
                                "modelName": "School",
                                "action": {
                                    create: false,
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },
                                schema: window.STATIC_DATA.schema.School,
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "schoolProfile",
                                stateOptions: {},
                                active: false
                            }
                        },
                        addStudent: {
                            load: function(){
                                changeTab(settings.tabs.addStudent);
                            },
                            data: {},
                            title: "Add Student",
                            relationDetail: {
                                "relationName": "addStudent",
                                "modelName": "Student",
                                "action": {
                                    create: false,
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },
                                schema: window.STATIC_DATA.schema.Student,
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "addStudent",
                                stateOptions: {},
                                active: false
                            }
                        },
                        addBus: {
                            load: function(){
                                changeTab(settings.tabs.addBus);
                            },
                            data: {},
                            title : "Add Bus",
                            relationDetail: {
                                "relationDetail": "addBus",
                                "modelName": "Bus",
                                "action": {
                                    create: false,
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },
                                schema: window.STATIC_DATA.schema.Bus,
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "addBus",
                                stateOptions: {},
                                active: false
                            }
                        },
                        busList: {
                            load: function(){
                                changeTab(settings.tabs.busList);
                            },
                            data: {},
                            title : "Bus List",
                            relationDetail: {
                                "relationDetail": "busList",
                                "modelName": "Bus",
                                "action": {
                                    create: false,
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },
                                schema: window.STATIC_DATA.schema.Bus,
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "busList",
                                stateOptions: {},
                                active: false
                            }
                        },
                        queries:{
                            load : function(){
                                changeTab(settings.tabs.queries);
                            },
                            data: {},
                            title : "Queries to Autobox",
                            relationDetail: {

                            },
                            config: {
                                stateName: "query",
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
            var configSettings = function () {
                var settings;
                return {
                    get: function () {
                        if (!settings) {
                            this.reset();
                        }
                        return settings;
                    },
                    reset: function () {
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