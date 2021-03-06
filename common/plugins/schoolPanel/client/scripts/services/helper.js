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
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "student",
                                "modelName": "Student",
                                "action": {
                                    create: false,
                                    showHeader: false,
                                    delete: false,
                                    customColumn: false
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
                            form: {},
                            title : "School",
                            schema: loadSchoolSchema(window.STATIC_DATA.schema.School),
                            getSchoolData : getSchoolData,
                            relationDetail: {
                                "relationName": "schoolProfile",
                                "modelName": "School",
                                "action": {
                                    create: false,
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            saveForm: function (formSchema, formData, formModel) {
                                DetailViewResource.saveForm(formSchema, formData, formModel)
                                    .then(function (data) {
                                        console.log("School Data Updated Successfully", data);
                                    })
                                    .catch(function (error) {
                                        console.error("Saved Workshop Data", error);
                                    });
                            },
                            config: {
                                stateName: "schoolProfile",
                                stateOptions: {},
                                active: false,
                                tableId: "SchoolForm"
                            }
                        },
                        addStudent: {
                            load: function(){
                                changeTab(settings.tabs.addStudent);
                            },
                            data: {},
                            form: {},
                            title: "Add Student",
                            schema: window.STATIC_DATA.schema.Student,
                            relationDetail: {
                                "relationName": "addStudent",
                                "modelName": "Student",
                                "action": {
                                    edit: false,
                                    showHeader: false,
                                    delete: false
                                },

                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            saveForm: function (formSchema, formData, formModel) {
                                formModel.schoolId = settings.config.employee.id;
                                DetailViewResource.saveForm(formSchema, formData, formModel)
                                    .then(function (data) {
                                        console.log("Student add successfully");

                                    })
                                    .catch(function (error) {
                                        console.error(error);
                                    });
                            },
                            config: {
                                stateName: "addStudent",
                                stateOptions: {},
                                active: false,
                                tableId : "StudentForm"
                            }
                        },
                        addBus: {
                            load: function(){
                                changeTab(settings.tabs.addBus);
                            },
                            data: {},
                            form: {},
                            schema: window.STATIC_DATA.schema.BusModel,
                            title : "Add Bus",
                            relationDetail: {
                                "relationName": "addBus",
                                "modelName": "BusModel",
                                "action": {
                                    showHeader: false,
                                    delete: false
                                },

                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            saveForm: function (formSchema, formData, formModel) {
                                formModel.schoolId = settings.config.employee.id;
                                DetailViewResource.saveForm(formSchema, formData, formModel)
                                    .then(function (data) {
                                        console.log("Bus add successfully");

                                    })
                                    .catch(function (error) {
                                        console.error(error);

                                    });
                            },
                            config: {
                                stateName: "addBus",
                                stateOptions: {},
                                active: false,
                                tableId: "BusModelForm"
                            }
                        },
                        busList: {
                            load: function(){
                                changeTab(settings.tabs.busList);
                            },
                            data: {},
                            title : "Bus List",
                            schema: window.STATIC_DATA.schema.BusModel,
                            relationDetail: {
                                "relationName": "busList",
                                "modelName": "BusModel",
                                "action": {
                                    create: false,
                                    showHeader: false,
                                    delete: false,
                                    customColumn: false,
                                    customColumnName: "Bus Notification History",
                                    imageSource: "../schoolPanel/images/bus_history.png",
                                    state: "busHistory"
                                },

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
                            data: "",
                            form: {},
                            display: true,
                            title : "Queries to Autobox",
                            schema: window.STATIC_DATA.schema.SchoolQuery,
                            sendQuery : sendQuery,
                            relationDetail: {
                                "relationName": "queryToAutobox",
                                "modelName": "SchoolQuery",
                                "action": {
                                    create: false,
                                    showHeader: false,
                                    delete: false
                                },

                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            config: {
                                stateName: "query",
                                stateOptions: {},
                                active: false,
                                display: true,
                                message: "Our representative will contact you shortly regarding requested query.."
                            }
                        },
                        busNotification: {
                            load : function(){
                                changeTab(settings.tabs.queries);
                            },
                            data: {},
                            title : "Bus Notification",
                            busNumberData : "",
                            schema: window.STATIC_DATA.schema.BusNotification,
                            relationDetail: {
                                "relationName": "busNotification",
                                "modelName": "BusNotification",
                                "action": {
                                    create: false,
                                    showHeader: false,
                                    delete: false,
                                    customColumn: false
                                },

                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function (data) {

                                    }
                                ]
                            },
                            displayData : displayData,
                            config: {
                                stateName: "busHistory",
                                stateOptions: {},
                                active: false,
                                busModelId : "",
                                dateClass: ["col-md-3", "clearfix", "dateDashboard"],
                                labelClass: ["col-md-1", "labelDashboard"],
                                columnName: "added",
                                modelSetting: {},
                                label: "Select Month"

                            }
                        },
                        importStudent: {
                            load : function(){
                                changeTab(settings.tabs.importStudent);
                            },
                            data : {},
                            config: {
                                stateName: "importStudent",
                                stateOptions: {},
                                active: false
                            }
                        }
                    },
                    logout: {
                        logout : logout
                    }
                };
                return settings;
            };


            var displayData = function(busModelId){
                return $q(function(resolve, reject){
                    var BusModel = Database.getDb("schoolPanel", "BusModel");
                    var BusNotification = Database.getDb("schoolPanel", "BusNotification");
                    BusModel.findOne({
                        filter: {
                            where : {
                                id : busModelId
                            }
                        }
                    }, function(data){
                        if(data){
                            settings.get().tabs.busNotification.busNumberData = data.busNumber;
                        }
                        resolve(data);
                    }, function(error){
                        reject(error);
                    });
                });
            };


            var loadSchoolSchema = function(schema_){
                var schema = schema_;
                var newSchema = angular.copy(schema);
                for (var key in newSchema.container) {
                    if (newSchema.container.hasOwnProperty(key)) {
                        if (newSchema.container[key].schema) {
                            if (newSchema.container[key].schema.length) {
                                newSchema.container[key].schema.forEach(function (obj) {
                                    if (obj.type !== "objectValue") {
                                        if(obj.templateOptions){
                                            obj.templateOptions.disabled = true;
                                        }
                                    } else{
                                        if(obj.key === 'latlong'){
                                          if (obj.templateOptions) {
                                             if (obj.templateOptions.fields) {
                                             obj.templateOptions.fields.forEach(function (nested_obj) {
                                             if (nested_obj.templateOptions) {
                                             nested_obj.templateOptions.disabled = true;
                                             }
                                             });
                                             }
                                         }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
                return newSchema;
            };

            /**
             * Logout Function..
             */
            var logout = function(){
                LoginServices.logout();
            };

            /**
             * Send Query to Autobox..
             */
            var sendQuery = function(){
                var SchoolQuery = Database.getDb("schoolPanel", "SchoolQuery");
                var message = angular.copy(settings.get().tabs.queries.data);
                startLoadingBar("#query");
                //Clear the data..
                settings.get().tabs.queries.data = "";
                if(message && settings.get().config.employee.id){
                    SchoolQuery.create({
                        query: message,
                        schoolName :  settings.get().config.employee.school.name,
                        schoolId: settings.get().config.employee.id
                    }, function () {
                        stopLoadingBar("#query");
                        settings.get().tabs.queries.config.display = false;
                        SnaphyTemplate.notify({
                            message: "Feedback send Successfully",
                            type: 'success',
                            icon: 'fa fa-check',
                            align: 'right'
                        });
                    }, function () {
                        stopLoadingBar("#query");
                        settings.get().tabs.queries.data = message;
                        SnaphyTemplate.notify({
                            message: "Something went wrong! Please try again later",
                            type: 'danger',
                            icon: 'fa fa-times',
                            align: 'right'

                        });
                    });
                }else{
                    stopLoadingBar("#query");
                    settings.get().tabs.queries.data = message;
                    SnaphyTemplate.notify({
                        message: "Message cannot be blank",
                        type: 'danger',
                        icon: 'fa fa-times',
                        align: 'right'

                    });
                }
            };

            /**
             * Get the school Data..
             * @param user
             * @returns {*}
             */
            var getSchoolData = function(user){
                return $q(function(resolve, reject){
                    var school = Database.getDb("schoolPanel", "School");
                    school.findOne({
                        filter: {
                            where: {
                                id: user.id
                            }
                        }
                    }, function(data){
                        delete data.$promise;
                        delete data.$resolved;
                        angular.copy(data, settings.get().tabs.schoolProfile.data);
                        if (settings.get().tabs.schoolProfile.data) {
                            if(settings.get().tabs.schoolProfile.data.serviceStartDate){
                                settings.get().tabs.schoolProfile.data.serviceStartDate = moment.utc(settings.get().tabs.schoolProfile.data.serviceStartDate).toDate();
                            }
                            if(settings.get().tabs.schoolProfile.data.serviceEndDate){
                                settings.get().tabs.schoolProfile.data.serviceEndDate = moment.utc(settings.get().tabs.schoolProfile.data.serviceEndDate).toDate();
                            }
                        }
                        resolve(settings.get().tabs.schoolProfile.data);
                    }, function(error){
                        reject(error);
                    });
                });
            };

            var startLoadingBar = function (id) {
                $timeout(function() {
                    //Now hide remove the refresh widget..
                    $(id).addClass('block-opt-refresh');
                }, 200);
            };


            var stopLoadingBar = function (id) {
                $timeout(function() {
                    //Now hide remove the refresh widget..
                    $(id).removeClass('block-opt-refresh');
                }, 200);
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