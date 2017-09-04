'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	const Promise = require("bluebird");
	const afterSave = {
        /*addSubscription: function (sheetRowObj, callback) {
			if(sheetRowObj.Customer){
				if(sheetRowObj.Customer.data){
					const Subscription = server.models["Subscription"];
					Subscription.create({
						productId: "592006f606a9ebfa58966235",
						customerId: sheetRowObj.Customer.data.id,
                        quantity: 1,
                        type: "daily"
					})
						.then(function (subscription) {
							callback(null);
                        })
						.catch(function (error) {
							console.error(error);
							callback(error);
                        });
				}
			}
        }*/
	};

	return {
		afterSave: afterSave
	};
};