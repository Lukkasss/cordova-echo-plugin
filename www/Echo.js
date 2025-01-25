var exec = require('cordova/exec');

exports.echo = function(elementId, message, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'EchoPlugin', 'echo', [elementId, message]);
};