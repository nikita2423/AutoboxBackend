'use strict';

angular.module($snaphy.getModuleName())

//Controller for dataImportControl ..
.controller('dataImportControl', ["$scope", "$stateParams", "Database", "Upload", "$timeout", "$http", "SnaphyTemplate",
    function($scope, $stateParams, Database, Upload, $timeout, $http, SnaphyTemplate) {
        //Checking if default templating feature is enabled..
        //var defaultTemplate = $snaphy.loadSettings("dataImport", "defaultTemplate");
        //$scope.state = $snaphy.loadSettings('dataImport', "state");
        //$snaphy.setDefaultTemplate(defaultTemplate);

        $scope.settings = {
            label: "Click to upload sheet",
	        uploadUrl:"/api/ImportData/documents/upload",
	        containerName: "ImportData",
	        uploadFiles: function($files, $file, $newFiles, $duplicateFiles, $invalidFiles, $event) {
            	var that = this;
		        if ($newFiles === null) {
			        return false;
		        }
				//Add model....
		        var file = $newFiles[0];
		        that.file = file;
		        var errFiles = $invalidFiles;
		        that.errFiles = errFiles && errFiles[0];
		        //Only upload file if it is not a duplicate file..
		        if (file && $duplicateFiles.length === 0 && errFiles.length === 0) {
			        file.upload = Upload.upload({
				        url: that.uploadUrl,
				        data: {
					        file: file,
					        container: that.containerName
				        }
			        });

			        file.upload.then(function(response) {
				        $timeout(function() {

				        });
				        SnaphyTemplate.notify({
					        message: "Data successfully saved to server.",
					        type: 'success',
					        icon: 'fa fa-check',
					        align: 'right'
				        });
			        }, function(response) {
			        	console.log(response);
				        if (response.status > 0) {
				        	var message = "Error saving data to server.";
					        if(response.data){
					        	if(response.data.message){
							        message = response.data.message;
						        }
					        }

				        	SnaphyTemplate.notify({
						        message: message,
						        type: 'danger',
						        icon: 'fa fa-times',
						        align: 'right'
					        });
					        $scope.errorMsg = response.status + ': ' + response.data;
				        }

			        }, function(evt) {
				        $timeout(function() {
					        that.file.progress = Math.min(100, parseInt(100.0 *
						        evt.loaded / evt.total));
					        $(".progress-bar.progress-bar-primary")
						        .each(function() {
							        var $this = jQuery(this);
							        var $random = that.file.progress + '%';
							        $this.css('width', $random);
						        });
				        }, 10);
			        });
		        }
	        }//Upload file..
	    };

        //Use Database.getDb(pluginName, PluginDatabaseName) to get the Database Resource.
    }//controller function..
]);