/**
 * Created by robins on 28/8/17.
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
                                        return workshopService.findOne({
                                            filter:{
                                                where: {
                                                    dealerId: userObj.id
                                                },
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
                            isMonthlyReports: false,
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
                            },
                            //List of widgets..
                            widgets: function(){
                                return [
                                    //All Time
                                    {
                                        label: "Total quote requested",
                                        model: "CustomerQuote",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "cityId": "$user.cityId",
                                                "currentBrandId": "$user.brandId"
                                            },
                                            dateProp: 'added'
                                        }
                                    },
                                    {
                                        label: "Total quote replied",
                                        model: "QuoteReply",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "dealerId": "$user.id"
                                            },
                                            dateProp: 'added'
                                        }
                                    },
                                    {
                                        label: "Total Sold Via Autobox",
                                        model: "CustomerQuote",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "cityId": "$user.cityId",
                                                "currentBrandId": "$user.brandId",
                                                "soldViaAutobox": "yes"
                                            },
                                            dateProp: 'added'
                                        }
                                    },
                                    {
                                        label: "Total GPS Tracker",
                                        model: "CustomerQuote",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "cityId": "$user.cityId",
                                                "currentBrandId": "$user.brandId",
                                                "gpsTracker": "yes"
                                            },
                                            dateProp: 'added'
                                        }
                                    },
                                    {
                                        label: "Total Dash Camera",
                                        model: "CustomerQuote",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "cityId": "$user.cityId",
                                                "currentBrandId": "$user.brandId",
                                                "dashCamera": "yes"
                                            },
                                            dateProp: 'added'
                                        }
                                    },
                                    {
                                        label: "Total Test Drive",
                                        model: "CustomerQuote",
                                        icon:"si-user-following",
                                        propObj: {
                                            type: "$allTime",
                                            where:{
                                                "cityId": "$user.cityId",
                                                "currentBrandId": "$user.brandId",
                                                "testDrive": "yes"
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
                                active: false,
                                dateClass:["col-md-4", "clearfix"],
                                labelClass:["col-md-2"],
                                columnName: "added",
                                modelSetting:{

                                },
                                label: "Filter by date"
                            }
                        },
                        monthlyReports: {
                            load: function () {
                                changeTab(settings.tabs.monthlyReports);
                            },
                            data: {},
                            isMonthlyReports: true,
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
                                "where": {
                                    "added": {
                                        "between": [moment.utc("2017-08-01", "YYYY-MM-DD").format("YYYY-MM-DDTHH:mm:ss.SSS[Z]"), moment.utc("2017-08-30", "YYYY-MM-DD").format("YYYY-MM-DDTHH:mm:ss.SSS[Z]")]
                                    }
                                },
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ]
                             },
                            getFilteredQuotes: getFilteredQuotes,
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
                            isMonthlyReports: false,
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
                                ]
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
                                        console.log("before hook data", data);
                                    }
                                ]
                            },
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "customerCallMessage",
                                stateOptions: {},
                                active: false
                            }
                        },
                        quoteReply:{
                            data: {},
                            form: {},
                            displayData: displayData,
                            isDataLoaded: false,
                            relationDetail: {
                                "relationName": "quoteReply",
                                "modelName": "QuoteReply",
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){

                                    }
                                ]
                            },
                            validations: {
                                rules : {

                                },
                                messages: {

                                }
                            },
                            schema : window.STATIC_DATA.schema.QuoteReply,
                            saveForm: function (formSchema, formData, formModel) {
                                if(settings.tabs.quoteReply.config.dealerId && settings.tabs.quoteReply.config.customerQuoteId){
                                    formModel.dealerId        = settings.tabs.quoteReply.config.dealerId;
                                    formModel.customerQuoteId = settings.tabs.quoteReply.config.customerQuoteId;
                                    DetailViewResource.saveForm(formSchema, formData, formModel)
                                        .then(function (data) {

                                        })
                                        .catch(function (error) {

                                        });
                                }
                            },
                            config: {
                                stateName: "demo",
                                stateOptions: {},
                                active: false,
                                dealerId: "",
                                customerQuoteId: ""
                            }
                        },
                        manageWorkshopProfile: {
                            load: function () {
                                changeTab(settings.tabs.manageWorkshopProfile);
                            },
                            data: {},
                            form: {},
                            title: "Workshop Profile",
                            loadArea: function () {
                                var val = $rootScope.$broadcast("areaLoaded", {
                                    where: {
                                        cityId: settings.config.employee.cityId
                                    }
                                });
                            },
                            //Contains the current model detail..
                            relationDetail: {
                                "relationName": "workshop",
                                "modelName": "Workshop",
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){
                                
                                    }
                                ]
                            },
                            schema : modifySchema(window.STATIC_DATA.schema.Workshop),
                            saveForm: function (formSchema, formData, formModel) {
                                formModel.brandId = settings.config.employee.brandId;
                                formModel.areaId = settings.config.employee.areaId;
                                formModel.cityId = settings.config.employee.cityId;
                                DetailViewResource.saveForm(formSchema, formData, formModel)
                                    .then(function (data) {

                                    })
                                    .catch(function (error) {

                                    });
                            },
                            getWorkshopData : getWorkshopData,
                            validations: {
                                rules : {
                                    "dealershipName": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "30"
                                      },
                                      "consultantName": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "30"
                                      },
                                      "contact": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "email": {
                                        "required": true,
                                        "email":true
                                      },
                                      "latitude": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "longitude": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "workshopStatus": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "gmemail": {
                                        "required": true,
                                        "email":true
                                      },
                                      "address": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "50"
                                      },
                                      "opening": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "closing": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "area": {
                                          "required": true,
                                          "valueNotEquals":""
                                      }
                                },
                                messages: {
                                    "dealershipName": {
                                        "required": "Showroom name is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 30"
                                      },
                                    "consultantName": {
                                        "required": "Consultant name is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 30"
                                      },
                                      "contact": {
                                        "required": "Phone Number is required",
                                        "valueNotEquals": "Enter your phone Number"
                                      },
                                      "email": {
                                        "required": "Email is required",
                                        "email": "Email must be valid."
                                      },
                                      "latitude": {
                                        "required": "Latitude is required",
                                        "valueNotEquals": "Enter correct latitude of showroom"
                                      }, 
                                      "longitude": {
                                        "required": "Longitude is required",
                                        "valueNotEquals": "Enter correct langitude of showroom"
                                      },
                                      "workshopStatus": {
                                        "required": "Status is required",
                                        "valueNotEquals": "Status must be one of the value"
                                      },
                                      "gmemail": {
                                        "required": "Emails is required",
                                        "valueNotEquals": "Email must be valid"
                                      },
                                      "address": {
                                        "required": "Address is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 50"
                                      },
                                      "opening": {
                                        "required": "Showroom opening time is required",
                                        "valueNotEquals": "Enter showroom opening time"
                                      },
                                      "closing": {
                                        "required": "Showroom closing time is required",
                                        "valueNotEquals": "Enter showroom closing time"
                                      },
                                      "area": {
                                        "required": "Area is required",
                                        "valueNotEquals": "Area should one of the selected values"
                                      }
                                }
                            },
                            config: {
                                stateName: "manageWorkshopProfile",
                                stateOptions: {},
                                active: false,
                                tableId : "WorkshopForm"
                            }
                        },
                        manageShowroomProfile: {
                            load: function () {
                                changeTab(settings.tabs.manageShowroomProfile);
                            },
                            data: {},
                            form: {},
                            title: "Showroom Profile",
                            loadArea: function () {
                                var val = $rootScope.$broadcast("areaLoaded", {
                                    where: {
                                        cityId: settings.config.employee.cityId
                                    }
                                });
                            },
                            saveForm: function (formSchema, formData, formModel) {
                                formModel.brandId = settings.config.employee.brandId;
                                formModel.areaId = settings.config.employee.areaId;
                                formModel.cityId = settings.config.employee.cityId;
                                DetailViewResource.saveForm(formSchema, formData, formModel)
                                    .then(function (data) {

                                    })
                                    .catch(function (error) {

                                    });
                            },
                            relationDetail: {
                                "relationName": "showroom",
                                "modelName": "Showroom"
                            },
                            getShowroomData: getShowroomData,
                            schema: modifySchema(window.STATIC_DATA.schema.Showroom),
                            validations: {
                                rules : {
                                    "showroomName": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "30"
                                      },
                                      "salesConsultantName": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "30"
                                      },
                                      "conatct": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "email": {
                                        "required": true,
                                        "email":true
                                      },
                                      "latitude": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "longitude": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "showroomStatus": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "gmemail": {
                                        "required": true,
                                        "email":true
                                      },
                                      "address": {
                                        "required": true,
                                        "minlength": "2",
                                        "maxlength": "50"
                                      },
                                      "opening": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "closing": {
                                        "required": true,
                                        "valueNotEquals": ""
                                      },
                                      "area": {
                                          "required": true,
                                          "valueNotEquals":""
                                      }
                                },
                                messages: {
                                    "showroomName": {
                                        "required": "Showroom name is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 30"
                                      },
                                    "salesConsultantName": {
                                        "required": "Consultant name is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 30"
                                      },
                                      "conatct": {
                                        "required": "Phone Number is required",
                                        "valueNotEquals": "Enter your phone Number"
                                      },
                                      "email": {
                                        "required": "Email is required",
                                        "email": "Email must be valid."
                                      },
                                      "latitude": {
                                        "required": "Latitude is required",
                                        "valueNotEquals": "Enter correct latitude of showroom"
                                      }, 
                                      "longitude": {
                                        "required": "Longitude is required",
                                        "valueNotEquals": "Enter correct langitude of showroom"
                                      },
                                      "showroomStatus": {
                                        "required": "Status is required",
                                        "valueNotEquals": "Status must be one of the value"
                                      },
                                      "gmemail": {
                                        "required": "Emails is required",
                                        "valueNotEquals": "Email must be valid"
                                      },
                                      "address": {
                                        "required": "Address is required",
                                        "minlength": "Length should be atleast 2",
                                        "maxlength": "Length can't exceed 50"
                                      },
                                      "opening": {
                                        "required": "Showroom opening time is required",
                                        "valueNotEquals": "Enter showroom opening time"
                                      },
                                      "closing": {
                                        "required": "Showroom closing time is required",
                                        "valueNotEquals": "Enter showroom closing time"
                                      },
                                      "area": {
                                        "required": "Area is required",
                                        "valueNotEquals": "Area should one of the selected values"
                                      }
                                }
                            },
                            config: {
                                stateName: "manageShowroomProfile",
                                stateOptions: {},
                                active: false,
                                tableId: "ShowRoomForm"
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
                                ]
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
                            form: {},
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
                            },
                            schema : window.STATIC_DATA.schema.Feedback,
                            sendFeedback: sendFeedback,
                            /*validations: fetchValidationObj('appUser'),*/
                            config: {
                                stateName: "feedback",
                                stateOptions: {},
                                active: false
                            }
                        },
                        replyCustomerMessage: {
                            data:{},
                            form:{},
                            relationDetail: {
                                "relationName": "replyCustomerMessage",
                                "modelName": "CustomerMessage",
                                beforeSaveHook: [
                                    //Here data going to be saved..
                                    function(data){

                                    }
                                ]
                            },
                            schema : window.STATIC_DATA.schema.CustomerMessage,
                            sendReplyToCustomer: sendReplyToCustomer,
                            config: {
                                stateName: "demo1",
                                stateOptions: {},
                                active: false,
                                dealerId: "",
                                customerMessageId: ""
                            }
                        }
                    }
                };
                return settings;
            };



            var modifySchema = function (schema_) {
                var schema = schema_;
                var newSchema =  angular.copy(schema);
                for(var key in newSchema.container){
                    if(newSchema.container.hasOwnProperty(key)){
                        if(newSchema.container[key].schema){
                            if(newSchema.container[key].schema.length){
                                newSchema.container[key].schema.forEach(function (obj) {
                                   if(obj.templateOptions){
                                       obj.templateOptions.disabled = true;
                                       obj.templateOptions.readonly = true;
                                       obj.templateOptions.readOnly = true;
                                   }
                                   if(obj.type === "objectValue"){
                                       if(obj.templateOptions){
                                           if(obj.templateOptions.fields){
                                               obj.templateOptions.fields.forEach(function (nested_obj) {
                                                   if(nested_obj.templateOptions){
                                                       nested_obj.templateOptions.disabled = true;
                                                   }
                                               });
                                           }
                                       }

                                   }
                                });
                            }
                        }
                    }
                }
                console.log(newSchema);
                return newSchema;
            };


            var getFilteredQuotes = function(){
                return $q(function(resolve, reject){
                    $('#dataClass').load(document.URL +  ' #dataClass');
                    var fromDate = moment.utc(document.getElementById("fromId").value, "YYYY-MM-DD").toDate();
                    var toDate = moment.utc(document.getElementById("toId").value, "YYYY-MM-DD").toDate();
                    console.log("filterFrom", fromDate);
                    console.log("filterTo", toDate);
                    var customerQuote = Database.getDb("dealerPanel", "CustomerQuote");
                    var brandId = settings.get().config.employee.brandId;
                    var cityId = settings.get().config.employee.cityId;
                    customerQuote.find({
                        filter: {
                            where: {
                                cityId: cityId,
                                currentBrandId: brandId,
                                added: {
                                    between: [fromDate, toDate]

                                }
                            }
                        }
                    }, function(customerQuoteList){
                        if(customerQuoteList){
                            if(customerQuoteList.length){
                                resolve();
                            }
                        }
                    }, function(error){
                        reject(error);
                    });
                });
            };

            /**
             * display data
             */
            var displayData = function (customerQuoteId, dealerId) {
              return $q(function (resolve, reject) {
                  var quoteReply = Database.getDb("dealerPanel", "QuoteReply");
                  settings.get().tabs.quoteReply.isDataLoaded = false;
                  quoteReply.findOne({
                      filter: {
                          where: {
                              customerQuoteId: customerQuoteId,
                              dealerId: dealerId
                          }

                      }
                  }, function(quoteReply){
                      //angular.copy(settings.get().tabs.quoteReply.data, quoteReply);
                      angular.copy(quoteReply, settings.get().tabs.quoteReply.data);
                      //console.log("Display data", settings.get().tabs.quoteReply.data);
                      settings.get().tabs.quoteReply.isDataLoaded = true;
                      resolve(quoteReply);
                  }, function(error){
                      settings.get().tabs.quoteReply.isDataLoaded = true;
                      reject(error);
                  });
              });
            };

            /**
             * Sending feedback to admin
             * @returns {*}
             */
            var sendFeedback = function(){
                console.log("Inside Feedback");
                var Feedback = Database.getDb("dealerPanel", "Feedback");
                return $q(function(resolve, reject){
                    initialize()
                        .then(function(){
                            return setCurrentState();
                        })
                        .then(function(){
                            return getActiveTabSettings();
                        })
                        .then(function(){
                            var message = document.getElementById("feedbackTextArea").value;
                            return Feedback.create({
                                message: message,
                                dealerId: settings.get().config.employee.id
                            });
                        })
                        .then(function(feedback){
                            if(feedback){
                                console.log("Feedback send Successfully");
                                SnaphyTemplate.notify({
                                    message: "Feedback send Successfully",
                                    type: 'success',
                                    icon: 'fa fa-check',
                                    align: 'right'
                                });
                                resolve();
                            }
                        })
                        .catch(function(error){
                            SnaphyTemplate.notify({
                                message: "Error in sending feedback",
                                type: 'danger',
                                icon: 'fa fa-times',
                                align: 'right'

                            });
                            reject(error);
                        });

                });
            };


            /**
             * Send Reply for Customer Message
             * @returns {*}
             */
            var sendReplyToCustomer = function(){
                var CustomerMessage = Database.getDb("dealerPanel", "CustomerMessage");
                return $q(function(resolve, reject){
                    initialize()
                        .then(function(){
                            return setCurrentState();
                        })
                        .then(function(){
                            return getActiveTabSettings();
                        })
                        .then(function(){
                            var customerMessageId = settings.get().tabs.replyCustomerMessage.config.customerMessageId;
                            CustomerMessage.findOne({
                                filter: {
                                    where: {
                                        id: customerMessageId
                                    }
                                }
                            }, function(customerMessage){
                                console.log("customerMessage", customerMessage);
                                var replyMessage = document.getElementById("replyTextArea").value;
                                if(replyMessage){
                                    var callTime = "";
                                    if(customerMessage.time){
                                        callTime = customerMessage.time;
                                    }
                                    SnaphyTemplate.notify({
                                        message: "Reply send Successfully",
                                        type: 'success',
                                        icon: 'fa fa-check',
                                        align: 'right'
                                    });
                                     return CustomerMessage.upsert({
                                        firstName: customerMessage.firstName,
                                        lastName: customerMessage.lastName,
                                        type: customerMessage.type? customerMessage.type:"",
                                        added: customerMessage.added,
                                        status: customerMessage.status?customerMessage.status:"",
                                        mobileNumber: customerMessage.mobileNumber?customerMessage.mobileNumber:"",
                                        message: customerMessage.message? customerMessage.message:"",
                                        subject: customerMessage.subject?customerMessage.subject:"",
                                        userType: customerMessage.userType?customerMessage.userType:"",
                                        replyMessage: replyMessage,
                                        time: callTime,
                                        id: customerMessage.id,
                                        customerId : customerMessage.customerId? customerMessage.customerId:"",
                                        dealerId: customerMessage.dealerId? customerMessage.dealerId:"",
                                        customerQuoteId: customerMessage.customerQuoteId? customerMessage.customerQuoteId:""
                                    });

                                }
                            }, function(error){
                                SnaphyTemplate.notify({
                                    message: "Error in sending reply",
                                    type: 'danger',
                                    icon: 'fa fa-times',
                                    align: 'right'

                                });
                                reject(error);
                            });
                        })
                        .then(function(customerMessage){
                            if(customerMessage){
                                SnaphyTemplate.notify({
                                    message: "Reply send Successfully",
                                    type: 'success',
                                    icon: 'fa fa-check',
                                    align: 'right'
                                });
                                resolve();
                            }
                        })
                        .catch(function(error){
                            SnaphyTemplate.notify({
                                message: "Error in sending reply",
                                type: 'danger',
                                icon: 'fa fa-times',
                                align: 'right'

                            });
                            reject(error);
                        });
                });
            }


            /**
             * Get showroom data on start..
             * @param user
             * @return {*}
             */
            var getShowroomData = function (user) {
                return $q(function (resolve, reject) {
                    var showroomService = Database.getDb("dealerPanel", "Showroom");
                    showroomService.findOne({
                        filter:{
                            where:{
                                brandId: user.brandId,
                                areaId: user.areaId,
                                cityId: user.cityId,
                                dealerId: user.id
                            },
                            include:["city", "area", "brand"]
                        }
                    }, function (data) {
                        //Copy data..
                        angular.copy(data, settings.get().tabs.manageShowroomProfile.data);
                        if(settings.get().tabs.manageShowroomProfile.data){
                            if(settings.get().tabs.manageShowroomProfile.data.timings){
                                if(settings.get().tabs.manageShowroomProfile.data.timings.opening){
                                    settings.get().tabs.manageShowroomProfile.data.timings.opening = moment.utc(settings.get().tabs.manageShowroomProfile.data.timings.opening).toDate();
                                }
                                if(settings.get().tabs.manageShowroomProfile.data.timings.closing){
                                    settings.get().tabs.manageShowroomProfile.data.timings.closing = moment.utc(settings.get().tabs.manageShowroomProfile.data.timings.closing).toDate();
                                }
                            }
                        }
                        resolve(data);
                    }, function (error) {
                        reject(error);
                    });
                });
            };


            /**
             * 
             * @param {*} 
             * @param user 
             */
            var getWorkshopData = function(user) {
                return $q(function(resolve, reject) {
                    var workshopService = Database.getDb("dealerPanel", "Workshop");
                    workshopService.findOne({
                        filter:{
                            where : {
                                brandId: user.brandId,
                                areaId: user.areaId,
                                cityId: user.cityId,
                                dealerId: user.id
                            },
                            include: ["city", "area", "brand"]
                        }
                    }, function(data) {
                            angular.copy(data, settings.get().tabs.manageWorkshopProfile.data);
                            if(settings.get().tabs.manageWorkshopProfile.data){
                                if(settings.get().tabs.manageWorkshopProfile.data.timings){
                                    if(settings.get().tabs.manageWorkshopProfile.data.timings.opening){
                                        settings.get().tabs.manageWorkshopProfile.data.timings.opening = moment.utc(settings.get().tabs.manageWorkshopProfile.data.timings.opening).toDate();
                                    }
                                    if(settings.get().tabs.manageWorkshopProfile.data.timings.closing){
                                        settings.get().tabs.manageWorkshopProfile.data.timings.closing = moment.utc(settings.get().tabs.manageWorkshopProfile.data.timings.closing).toDate();
                                    }
                                }
                            }
                            resolve(data);
                    }, function(error) {
                            reject(error);
                    });
                });
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