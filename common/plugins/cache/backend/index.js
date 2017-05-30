'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {
	var minify = require('express-minify');
	var compression = require('compression');

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
	var init = function(){
		const mode = packageObj.selectMode;
		const modeObj = packageObj.mode;
		if(modeObj){
			if(modeObj[mode]){
				if(modeObj[mode].compression){
					server.use(compression());
				}

				if(modeObj[mode].minify){
					//Minify the results..
					server.use(minify({
						cache: true,
						js_match: /javascript/,
						css_match: /css/,
						sass_match: /scss/,
						less_match: /less/,
						stylus_match: /stylus/,
						coffee_match: /coffeescript/,
						json_match: /json/,
						blacklist: /(\.min)\.(css|js)$/
					}));
				}
			}
		}


	};

	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init
	};
}; //module.exports
