'use strict';
var IncomingForm = require('formidable');

var fs = require("fs");
module.exports = function( server, databaseObj, helper, packageObj) {

	const init = function (importDataFunc) {
		const Container = server.models[packageObj.upload.containerModel];
		modifyContainerUpload(server, Container, packageObj.upload, packageObj, importDataFunc);
	};

	const modifyContainerUpload = function (app, Container, config, packageObj, importDataFunc) {
		//Get the dataSource object..
		//const FileDataSource = config.fileDataSource;
		Container.beforeRemote('upload', function (ctx, res, next) {
			//Start the file uploading process..
			uploadFile(app, ctx.req, ctx.res, config, packageObj, {}, importDataFunc, function (err, filePath) {
				if (err) {
					console.log(err);
					ctx.res.status(500).send(err);
				}else {
					console.log("DATA UPLOADED");
					return ctx.res.send({});
					//next();
				}
			});

		});
	}; //modifyContainerUpload files..



	const uploadFile = function(app, req, res, config, packageObj, options, importDataFunc, cb) {
		//console.log("Now uploading files to S3");
		const storageService = app.dataSources[config.fileDataSource].connector;
		if (!cb && 'function' === typeof options) {
			cb = options;
			options = {};
		}
		if (storageService.getFilename && !options.getFilename) {
			options.getFilename = storageService.getFilename;
		}
		if (storageService.acl && !options.acl) {
			options.acl = storageService.acl;
		}
		if (storageService.allowedContentTypes && !options.allowedContentTypes) {
			options.allowedContentTypes = storageService.allowedContentTypes;
		}
		if (storageService.maxFileSize && !options.maxFileSize) {
			options.maxFileSize = storageService.maxFileSize;
		}
		return handler(app, storageService.client, req, res, config, packageObj, options, importDataFunc, cb);
	};



	/**
	 * Custom handler for handling the amazon upload type
	 * @param  {Object}   app             loopback app type object
	 * @param  {Object}   provider        Provider type either filesystem | Amazon S3 etc
	 * @param  {Object}   req             Request object
	 * @param  {Object}   res             Response Object
	 * @param  {Object}   config          Plugin Config of PackageObj of snaphy
	 * @param  {Object}   packageObj      Settings of PackageObj
	 * @param  {Object}   options         Extra options for storing file description or details.
	 * @param  {Function}   importDataFunc Function for importing document in serverafter upload.
	 * @param  {Function} cb              Callback function. arguments (err, file)
	 */
	var handler = function(app, provider, req, res, config, packageObj, options, importDataFunc, cb) {
		let form = new IncomingForm(options);
		let fields = {};
		let files = [];

		form
			.on('field', function(field, value) {
				fields[field] = value;
			})
			.on('file', function(field, file) {
				const fileName = renameFile(file, req);
				uploadImageToCloud(app, file, fileName, importDataFunc, cb);
			})
			.on('end', function(name, file) {
				//console.log("END-> File fetched\n");
			});
		form.parse(req);
	};


	var uploadImageToCloud = function(app, path, fileName, importDataFunc, callback) {
		var filePath = "/tmp/" + fileName;
		fs.renameSync(path.path, filePath);

		importDataFunc(filePath, function (error, data) {
			if(error){
				callback(error);
			}else{
				callback(error, data);
			}
			deleteLocalFile(filePath);
		});
	}; //uploadImageToCloud..





	var deleteLocalFile = function(path) {
		fs.unlink(path, function(err) {
			if (err) {
				console.error("Error deleting image from the path.");
			} else {
				console.log('successfully deleted ' + path);
			}

		});
	};



	var renameFile = function (file, req){
		//var fileExtension = file.name.split(/\.$/).pop();
		var fileExtension;
		//var container = file.container;
		var time = new Date().getTime();
		var userId = getUserId(req);

		var UUID = guid();
		//Now preparing the file name..
		//customerId_time_orderId.extension
		//Pattern for detecting the file extension
		var pattern = /^.+\/(.+)$/;
		if(!fileExtension){
			var extension = pattern.exec(file.type);
			try {
				if (extension.length) {
					var DOCXTypePatt = /^application\/vnd\.(.+)$/; //http://stackoverflow.com/questions/4212861/what-is-a-correct-mime-type-for-docx-pptx-etc
					var MSWord = /^application\/msword$/;
					if(DOCXTypePatt.test(file.type)){
						//In case of mobile upload..
						fileExtension = "xlsx";
					}else if(MSWord.test(file.type)){
						//In case of mobile upload..
						fileExtension = "doc";
					}else{
						//In case of mobile upload..
						let fileName = file.name;
						if(/^.+\.pdf$/.test(fileName)){
							fileExtension = "pdf";
						}else if(/^.+\.docx$/.test(fileName)){
							fileExtension = "docx";
						}else if(/^.+\.doc$/.test(fileName)){
							fileExtension = "doc";
						}else if(/^.+\.jpg$|^.+\.png$/.test(fileName)){
							fileExtension = "image";
						}else{
							fileExtension = extension[1];
						}

					}

				} else {
					var err = new Error("Error: File Extension not found");
					console.error("Error: File Extension not found");
					return err;
				}
			} catch (err) {
				console.error(err);
				return err;
			}

			if (fileExtension === 'jpeg') {
				fileExtension = "jpg";
			}

		}

		/*if(fileExtension !== "jpg" || fileExtension !== "png" || fileExtension !== "gif"){
		 fileExtension = "jpg";
		 }*/

		var NewFileName = '' + userId + '_' + time + '_' + UUID + '.' + fileExtension;

		//And the file name will be saved as defined..
		return NewFileName;
	}


	function getUserId(req){
		var userId;
		try{
			//var query = req.query;
			userId = req.accessToken.userId;
		}
		catch(err){
			//TODO Remove this to support only login user upload..
			userId = guid();
			console.error("Got error accessing user information from accesstoken in helper.js file in fileUpload");
		}

		return userId;
	}


	function guid() {
		function s4() {
			return Math.floor((1 + Math.random()) * 0x10000)
				.toString(16)
				.substring(1);
		}
		return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
			s4() + '-' + s4() + s4() + s4();
	}








	return{
		init: init
	};
};
