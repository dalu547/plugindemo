package cordova.plugin.plugindemo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class PluginDemo extends CordovaPlugin {

    private Timer mTimer;
    private TimerTask mTt;
    private Handler mTimerHandler = new Handler();
    private CallbackContext gCallbackContext;
    private String callbackId;
    private String TAG = "PluginDemo";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("init")){
            this.init(callbackContext);
            return true;
        }
        else if (action.equals("coolMethod")) {
            String message = args.getString(0);
            // this.coolMethod(message, callbackContext);
            this.startTimer(message, callbackContext);
            return true;
        }else if (action.equals("startTimer")) {
            String message = args.getString(0);
            this.startTimer(message, callbackContext);
            return true;
        }else if (action.equals("stopTimer")) {
            String message = args.getString(0);
            this.stopTimer(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        Log.d(TAG,"coolMethod");

        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void init(CallbackContext callbackContext){
        Log.d(TAG,"init");

        gCallbackContext = callbackContext;
        callbackId = callbackContext.getCallbackId();
    }

    private void initRegistration(){

        Log.d(TAG,"initRegistration");
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "Update message to component");
        pluginResult.setKeepCallback(true);
        gCallbackContext.sendPluginResult(pluginResult);
//      gCallbackContext.success("Update message to component");

    }

    private void stopTimer(String message, CallbackContext callbackContext){
        if(mTimer != null){
            mTimer.cancel();
            mTimer.purge();
        }
    }

    private void startTimer(String message, CallbackContext callbackContext){
        mTimer = new Timer();
        mTt = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run(){
                        Log.d("Timer","Timer tiggered");
                        
                        //Plugin result prepare and send.
                        initRegistration();

                    }
                });
            }
        };

        mTimer.schedule(mTt, 1, 1*30*1000);
    }

}
