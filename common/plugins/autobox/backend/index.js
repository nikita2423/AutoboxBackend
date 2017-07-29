'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	/**
	 * Here server is the main app object
	 * databaseObj is the mapped database from the package.json file
	 * helper object contains all the helpers methods.
	 * packegeObj contains the packageObj file of your plugin. 
	 */

	/**
	 * Initialize the plugin at time of server start.
	 * init method should never have any argument
	 * It is a constructor and is populated once the server starts.
	 * @return {[type]} [description]
	 */

    const remoteMethods = require("./remoteMethods")(server, databaseObj, helper, packageObj);

    var speakeasy = require("speakeasy");
    var secret = speakeasy.generateSecret({length: 20});
	var init = function(){
		requestOtpMethod();
		loginWithCodeMethod();
        remoteMethods.init();
	};


	const requestOtpMethod = function(){
		const Customer = databaseObj.Customer;
		Customer.requestOTP = requestOTP;
		Customer.remoteMethod("requestOTP", {
            accepts: {
                arg: 'phoneNumber',
                type: 'string',
                required: true
            },
            returns: {
                arg: 'status',
                type: 'string',
                required: true
            }
		});
	};

	const loginWithCodeMethod = function(){
		const Customer = databaseObj.Customer;
		Customer.loginWithCode = loginWithCode;
		Customer.remoteMethod("loginWithCode", {
			accepts: [
				{
					arg: 'phoneNumber', type: "string"
				},
				{
					arg: 'code', type: "string"
				}
			],
            returns: {
                arg: 'status', type: 'string', root: true,
                description:
                'The response body contains properties of the AccessToken created on login.\n' +
                'Depending on the value of `include` parameter, the body may contain ' +
                'additional properties:\n\n' +
                '  - `user` - `{User}` - Data of the currently logged in user. (`include=user`)\n\n'
            }

        });
	};


	const requestOTP = function(phoneNumber, callback){
        var patt = /\+\d{12,12}/,
            match = phoneNumber.match(patt);

        if (!match) {
            phoneNumber = "+91" + phoneNumber;
        }


        var code = speakeasy.totp({
            secret: secret.base32,
            encoding: 'base32',
            digits: 4,
            step: 55
        });
        console.log('Sending code for verification process : ' + code);
        callback(null, "Success");

	};


	const loginWithCode = function(phoneNumber, code, callback){
        var err = new Error('Sorry, but that verification code does not work!');
        err.statusCode = 401;
        err.code = 'LOGIN_FAILED';


        if (phoneNumber) {
            phoneNumber = phoneNumber.toString();
            var patt = /\+\d{12,12}/;
            var match = phoneNumber.match(patt);

            if (!match) {
                phoneNumber = "+91" + phoneNumber;
            }
        }

        var codeValidates = speakeasy.totp.verify({
            secret: secret.base32,
            encoding: 'base32',
            token: code,
            digits: 4,
            step: 55,
            window: 6
        });

        if(codeValidates){
        	//create Access Token..
            console.log("success");
			/*createAccessToken(phoneNumber, function(error){
				if(error){
					callback(error);
				}
            });*/
            callback(null, "success");

		} else{
        	console.log("Token not matches");
        	callback(new Error("Code not matches"));
		}
	};


/*	const createAccessToken = function(phoneNumber, callback){
		const Customer = databaseObj.Customer;
		Customer.findOne({
			where:{
				phoneNumber: phoneNumber
			}
		})
			.then(function(customer){
				if(customer){
					//Do not create AccessToken
				} else{

				}
			})
	}*/


	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init
	};
}; //module.exports
