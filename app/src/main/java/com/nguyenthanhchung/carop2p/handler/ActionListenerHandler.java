package com.nguyenthanhchung.carop2p.handler;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class ActionListenerHandler implements WifiP2pManager.ActionListener  {
    private Activity mActivity;
    private String message;

    public ActionListenerHandler(Activity mActivity,String msg){
        this.mActivity = mActivity;
        this.message = msg;
    }

    @Override
    public void onSuccess() {
        Log.d("_", message + " SUCCESS");
    }

    @Override
    public void onFailure(int i) {
        Log.d("_",message+ " FAILURE");

    }
}
