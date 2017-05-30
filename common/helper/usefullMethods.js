(function(){"use strict";})();
const Promise = require("bluebird");

/**
 * Return the Error object with message
 * @param  {String}  message       Error message
 * @return {Object} Error object.
 */
const getError = function(message){
    message = typeof message === "string" ? message.trim() : "";
    return new Error(message);
};


function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
        s4() + '-' + s4() + s4() + s4();
}



/**
 * Validate presense of a properties by checking for value in both currentInstance and instance
 * @param  {Object} instance        loopback ctx.instance object.
 * @param  {Object} currentInstance loopback ctx.currentInstance object occurs in the case of beforeSave of updateAttributes method.
 * @param  {String} prop            Properties name which you want to validate
 * @return {Boolean}                True means the validation is true and vice versa.
 */
const validate = function(instance, currentInstance, prop){
    if(instance[prop] === undefined){
        if(currentInstance){
            if(!currentInstance[prop]){
                return false
            }
        }else{
            return false;
        }
    }

    return true;

};


module.exports = {
  getError: getError,
  validate: validate,
  guid: guid
};