'use strict';

angular.module($snaphy.getModuleName())

//Controller for pushServiceControl ..
.controller('pushServiceControl', ['$scope', '$stateParams', 'Database', "$http",
    function($scope, $stateParams, Database, $http) {
        //Checking if default templating feature is enabled..
        var defaultTemplate = $snaphy.loadSettings('pushService', "defaultTemplate");
        $snaphy.setDefaultTemplate(defaultTemplate);
        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
        //
        //
        
        $http.get('/api/installations').success(function (data) {
            console.log(data);
            $scope.devices = data;
            $scope.orderProp = 'appId';
        });

        $scope.notify = function (id, msg) {
            $http.post('/notify/' + encodeURIComponent(id), {msg: msg}).success(function (data, status, headers) {
                $scope.status = 'Notification sent: ' + data + ' status: ' + status;
            });
        };

        $scope.delete = function (index, id) {
            $http.delete('/api/installations/' + encodeURIComponent(id)).success(function (data, status, headers) {
            $scope.devices.splice(index, 1);
            $scope.status = 'Record deleted: ' + id + ' status: ' + status;
            });
        };


        $scope.getAction = function (status) {
            return 'Active' === status ? 'Deactivate' : 'Activate';
        };

        $scope.switchStatus = function (device) {
            var newStatus = 'Active' === device.status ? 'Inactive' : 'Active';
            console.log('Setting status to '+newStatus);
            device.status = newStatus;
            $http.put('/api/installations/' + encodeURIComponent(device.id), device ).success(function (data, status, headers) {
                $scope.status = 'Status changed: ' + data + ' status: ' + status;
            });
        };
    }
])



//Controller for customerProfile..
.controller('applicationsPage', ['$scope', '$http', function($scope, $http) {
        $scope.load = function() {
            $http.get('/api/applications').success(function(data) {
                console.log(data);
                $scope.apps = data;
                $scope.orderProp = 'id';
            });
        };

        $scope.create = function() {
            $http.post('/api/applications', {
                name: $scope.name,
                description: $scope.description,
                pushSettings: {
                    apns: {
                        certData: $scope.certData,
                        keyData: $scope.keyData
                    },
                    gcm: {
                        serverApiKey: $scope.gcmKey
                    }
                }
            })
                .success(function(data, status, headers) {
                    // console.log(data, status);
                    $scope.id = 'Application Id: ' + data.id;
                    $scope.restApiKey = 'Application Key: ' + data.restApiKey;
                });
        };

        $scope.delete = function(index, id) {
            $http.delete('/api/applications/' + encodeURIComponent(id)).success(function(data, status, headers) {
                $scope.apps.splice(index, 1);
                $scope.status = 'Application deleted: ' + id + ' status: ' + status;
            });
        };
    }
]);


