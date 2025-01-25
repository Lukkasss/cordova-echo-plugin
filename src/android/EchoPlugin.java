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
	Log.d("EchoPlugin", "callbackcontext: " + callbackContext);
        if ("echo".equals(action)) {
	    String elementId = args.getString(0);
            String message = args.getString(1);
	    if (elementId != null && !elementId.isEmpty() && message != null && !message.isEmpty()) {
                // Atualiza o elemento HTML com a mensagem
                updateHtmlElement(elementId, message);

                // Retorna a mensagem para o JavaScript
                callbackContext.success(message);
            } else {
                callbackContext.error("ID do elemento ou mensagem inválidos.");
            }
            return true;
        }
        return false;
    }

    private void updateHtmlElement(String elementId, String message) {
        // Executa um código JavaScript no WebView para atualizar o elemento HTML com a mensagem
        final String jsCode = "javascript:document.getElementById('" + elementId + "').innerText = '" + escapeJavaScriptString(message) + "';";
        webView.loadUrl(jsCode); // Executa o JS no WebView
    }

    private String escapeJavaScriptString(String input) {
        // Escapa caracteres especiais para evitar problemas no código JavaScript
        return input.replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r");
    }
}
