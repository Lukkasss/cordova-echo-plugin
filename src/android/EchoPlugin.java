package com.example.echo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;

public class EchoPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	Log.d("EchoPlugin", "action: " + action);
	Log.d("EchoPlugin", "callbackcontext: " + CallbackContext);
        if ("echo".equals(action)) {
            String message = args.getString(0);
	    Log.d("EchoPlugin", "message received: " + message);
            callbackContext.success(message); // Retorna a mensagem recebida
            return true;
        }
        return false;
    }
}
