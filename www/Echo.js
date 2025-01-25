var exec = require('cordova/exec');

exports.echo = function(message, successCallback, errorCallback) {
    exec(successCallback(a), errorCallback, 'EchoPlugin', 'echo', [message]);
};