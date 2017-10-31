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

	const Promise          = require("bluebird");
    const remoteMethods    = require("./remoteMethods")(server, databaseObj, helper, packageObj);
    const pushNotification = require("./pushNotification")(server, databaseObj, helper, packageObj);
    const gpsInfoMethods = require("./gpsInfoMethods")(server, databaseObj, helper, packageObj);
    const chauffeur = require("./chauffeur")(server, databaseObj, helper, packageObj);
    const gpsPushNotification = require("./gpsPushNotification")(server, databaseObj, helper, packageObj);
    const trackDealerVehicleMethod = require("./trackDealerVehicleMethod")(server, databaseObj, helper, packageObj);
    const schoolMethods = require("./schoolMethods")(server, databaseObj, helper, packageObj);
    var speakeasy          = require("speakeasy");
    var moment             = require("moment");
    const SendOtp          = require('sendotp');
    const voucher_codes    = require('voucher-code-generator');
	var secret             = speakeasy.generateSecret({length: 20});
	const loginPlugin      = helper.loadPlugin("login");

    const sendOtp = new SendOtp(packageObj.msgAuthKey);


	var init = function(){
		requestOtpMethod();
		loginWithCodeMethod();
        remoteMethods.init();
        pushNotification.init();
        gpsInfoMethods.init();
        chauffeur.init();
        gpsPushNotification.init();
        trackDealerVehicleMethod.init();
        schoolMethods.init();
	};


    /**
	 * Override the customer quote data according the dealer.
     * @param req
     * @param customerQuoteList
     */
	const overrideCustomerQuoteData = function (req, customerQuoteList) {
		return new Promise(function (resolve, reject) {
			let userId;
			if(req){
				if(req.accessToken){
					if(req.accessToken.userId){
						userId = req.accessToken.userId;
					}
				}
			}

            getAuthorisedRoles(req)
				.then(function (roles) {
					const promiseList = [];
                    if(customerQuoteList){
                        if(customerQuoteList.length){
                            customerQuoteList.forEach(function (customerQuoteItem, index) {
                            	let customerQuoteInstance;
								promiseList.push(new Promise(function (resolve, reject) {
                                    filterCustomerQuote(customerQuoteItem, roles, userId)
										.then(function (customerQuote) {
											customerQuoteInstance = customerQuote;
											//Check for if SoldViaAutobox has the customerQuoteId for this dealerId
											return databaseObj.SoldViaAutobox.findOne({
												where: {
													customerQuoteId : customerQuote.id,
													dealerId: userId
												}
											});

                                        })
										.then(function(soldViaAutobox){
											if(soldViaAutobox){
												customerQuoteInstance.soldViaAutobox = "yes";
                                                customerQuoteInstance[packageObj.EDIT_BUTTON] = packageObj.disableButton.enable;
											} else{
												//customerQuoteInstance.soldViaAutobox = "no";
                                                customerQuoteInstance[packageObj.EDIT_BUTTON] = packageObj.disableButton.disable;
											}
                                            customerQuoteList.splice(index, 1, customerQuoteInstance);
										})
										.then(function () {
											resolve();
                                        })
										.catch(function (error) {
											reject(error);
                                        });
                                }));
                            });
                        }
                    }
					return Promise.all(promiseList);
                })
				.then(function () {
					resolve();
                })
				.catch(function (error) {
					reject(error);
                });
        });
    };


    /**
	 * Filter Individual Customer Quote According to the dealer.
     * @param customerQuote
     * @param roles
     * @param userId
     */
	const filterCustomerQuote = function (customerQuote, roles, userId) {
		return new Promise(function (resolve, reject) {
			if(customerQuote){
                customerQuote = customerQuote.toJSON();
			}

			//Proceed only if the role is of dealer..
			if(roles.indexOf(packageObj.DEALER_ROLE) !== -1){
                if(customerQuote){
                    if(customerQuote.added){
                        //Check if this car is already sold by autobox or not..
                        if(customerQuote.soldViaAutobox === packageObj.soldViaAutobox.yes){
                            //Now check if the. current dealer has sold the car..
							if(customerQuote.dealerId.toString() === userId.toString()){
								//Current dealer has sold the car...allow to dealer to display the customer info..
								//Display all the information..of customer in this case..
								//Do nothing here..
							}else{
                                deleteCustomerQuoteBasicDetails(customerQuote);
								//if some other dealer has sold the car.. then..
								//Display a dummy no button.. and disable all functionality..
                                customerQuote.soldViaAutobox = packageObj.soldViaAutobox.no;
                                customerQuote.gpsTracker     = packageObj.soldViaAutobox.no;
                                customerQuote.dashCamera     = packageObj.soldViaAutobox.no;
								customerQuote[packageObj.EDIT_BUTTON] = packageObj.disableButton.disable;
								customerQuote[packageObj.REPLY_BUTTON] = packageObj.disableButton.disable;
							}
                            resolve(customerQuote);
                        }else{
                            const waitingTimeLimit = moment(customerQuote.added).add(packageObj.WAITING_TIME, "hours");
                            if(!moment().isAfter(waitingTimeLimit)){
                                deleteCustomerQuoteBasicDetails(customerQuote);
                                const QuoteReply = databaseObj.QuoteReply;
                                QuoteReply.findOne({
                                    where:{
                                        dealerId: userId,
                                        customerQuoteId: customerQuote.id
                                    }
                                })
                                    .then(function (quoteReply) {
                                        if(!quoteReply){
                                            ///Niether vehicle is sold yet nor the dealer has replied the quote yet..
                                            //Disable the button dealer cannot send quote after 2 days..
                                            customerQuote[packageObj.EDIT_BUTTON] = packageObj.disableButton.disable;
                                        }else{
                                            //Do nothing here..
                                        }
                                    })
                                    .then(function () {
                                        resolve(customerQuote);
                                    })
                                    .catch(function (error) {
                                        customerQuote[packageObj.EDIT_BUTTON] = packageObj.disableButton.disable;
                                        reject(error);
                                    });
                            }else{
                                //Check if dealer has given the customer quote or not...
								const QuoteReply = databaseObj.QuoteReply;
								QuoteReply.findOne({
									where:{
										dealerId: userId,
										customerQuoteId: customerQuote.id
									}
								})
									.then(function (quoteReply) {
										if(!quoteReply){
                                            deleteCustomerQuoteBasicDetails(customerQuote);
											///Niether vehicle is sold yet nor the dealer has replied the quote yet..
											//Disable the button dealer cannot send quote after 2 days..
                                            customerQuote[packageObj.EDIT_BUTTON] = packageObj.disableButton.disable;
                                            customerQuote[packageObj.REPLY_BUTTON] = packageObj.disableButton.disable;
										}else{
											//Do nothing here..
										}
                                    })
									.then(function () {
                                        resolve(customerQuote);
                                    })
									.catch(function (error) {
										reject(error);
                                    });
                            }
						}
                    }else{
                    	reject(new Error("Added date is required."));
					}
                }else{
                    resolve(customerQuote);
				}
			}else{
				//Dealer is not requesting the data...
				resolve(customerQuote);
			}
        });
    };


    /**
	 * Delete basic details of customer Quote
     * @param customerQuote
     */
	const deleteCustomerQuoteBasicDetails = function (customerQuote) {
		if(customerQuote){
			if(customerQuote.customer){
                //Remote the customer basic details..
                delete customerQuote.customer.phoneNumber;
                delete customerQuote.customer.referralCode;
                delete customerQuote.customer.referralCount;
                delete customerQuote.customer.sosStatus;
                delete customerQuote.customer.googleRefreshToken;
                delete customerQuote.customer.email;
			}
		}

    };


    /**
	 * Get the authorised roles..
     * @param req
     */
	const getAuthorisedRoles = function (req) {
		return new Promise(function (resolve, reject) {
            loginPlugin.getRoles(server, req, function (error, roles) {
				if(error){
					reject(error);
				}else{
					resolve(roles);
				}
            });
        });
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
                arg: 'accessToken', type: 'object', root: true,
                description:
                'The response body contains properties of the AccessToken created on login.\n' +
                'Depending on the value of `include` parameter, the body may contain ' +
                'additional properties:\n\n' +
                '  - `user` - `{User}` - Data of the currently logged in user. (`include=user`)\n\n'
            },
            http: {verb: 'post'}

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
        sendOtp.send(phoneNumber.toString(), packageObj.senderId, code.toString(), function (error, data, response) {
        	if(error){
        		//console.log(error);
        		callback(error);
			} else{
               // console.log(data, response);
                callback(null, "Success");
			}

        });

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
            //console.log("success");
			createAccessToken(phoneNumber,callback);


		} else{
        	//console.log("Token not matches");
        	callback(new Error("Code not matches"));
		}
	};


	const createAccessToken = function(phoneNumber, callback){
		const Customer = databaseObj.Customer;
		const ReferralCode = databaseObj.ReferralCode;
		let customerInstance;
		Customer.findOne({
			where:{
				phoneNumber: phoneNumber
			}
		})
			.then(function(customer){
				if(!customer){
					return Customer.create({
                        phoneNumber : phoneNumber,
                    });
				} else{
					return customer.updateAttribute("phoneNumber", phoneNumber);
				}
			})
			.then(function(customer){
				if(customer){
                    customerInstance = customer;
                    return customer.createAccessToken(31536000);
				}
			})

			.then(function(token){
				if(token) {
                    token.__data.user = customerInstance;
                    //console.log("myToken", token);
                    callback(null, token);
                }else{
					callback(null,{});
				}
			})
			.catch(function(error){
				callback(error);
			});
	};


	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init,
        overrideCustomerQuoteData: overrideCustomerQuoteData
	};
}; //module.exports
