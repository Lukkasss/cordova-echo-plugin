package com.example.echo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.webkit.WebView;

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
        // Usa evaluateJavascript para executar código JS diretamente no WebView
        final String jsCode = "document.getElementById('" + elementId + "').innerText = '" + escapeJavaScriptString(message) + "';";
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
		WebView actualWebView = (WebView) webView.getEngine().getView();
            	actualWebView.evaluateJavascript(jsCode, null);
            }
        });
    }

    private String escapeJavaScriptString(String input) {
        // Escapa caracteres especiais para evitar problemas no código JavaScript
        return input.replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r");
    }
}
