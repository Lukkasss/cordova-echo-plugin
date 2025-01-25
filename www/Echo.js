var exec = require('cordova/exec');

exports.echo = function(message, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'EchoPlugin', 'echo', [message]);
};