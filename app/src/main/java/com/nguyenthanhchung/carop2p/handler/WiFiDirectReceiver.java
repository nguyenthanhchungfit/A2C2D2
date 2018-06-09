package com.nguyenthanhchung.carop2p.handler;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.nguyenthanhchung.carop2p.Game;
import com.nguyenthanhchung.carop2p.Helper.MySharedPreferences;
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.model.PackageData;
import com.nguyenthanhchung.carop2p.model.TypePackage;

import java.util.ArrayList;

public class WiFiDirectReceiver extends BroadcastReceiver implements WifiP2pManager.PeerListListener,WifiP2pManager.ConnectionInfoListener {

    private final String TAG = "WiFiDirectReceiver";
    private boolean isWifiDirectEnabled;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private ArrayList<WifiP2pDevice> wifiDevices;
    private WifiP2pDevice thisDevice;
    private WiFiDirectActivity mActivity;
    private IntentFilter mIntentFilter;

    private Game game;

    public WiFiDirectReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                              WiFiDirectActivity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d(TAG, "Wifi Receiver Intent action= " + action);
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            handleWifiP2pStateChanged(intent);
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
            //obtain a peer from the WifiP2pDeviceList
            handleWifiP2pPeersChanged(intent);
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
            handleWifiP2pConnectionChanged(intent);
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
            handleWifiP2pDeviceChanged(intent);
        }
    }

    private void handleWifiP2pStateChanged(Intent intent) {
        int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
        isWifiDirectEnabled = state == WifiP2pManager.WIFI_P2P_STATE_ENABLED;
        Log.d(TAG, "Wifi Direct state: " + (isWifiDirectEnabled ? "ENABLED" : "DISSABLED "));
        if(!isWifiDirectEnabled){
            mActivity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            Toast.makeText(mActivity.getApplication().getApplicationContext(),"Enable Wifi and then press Back",Toast.LENGTH_LONG).show();
        }
    }

    private void handleWifiP2pPeersChanged(Intent intent) {
        mManager.requestPeers(mChannel, this);
    }

    private void handleWifiP2pConnectionChanged(Intent intent) {
        NetworkInfo info =
                intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
        if (info != null && info.isConnected()) {
            mManager.requestConnectionInfo(mChannel, this);
        } else {
            Log.d(TAG, "No Connection");
        }
    }

    private void handleWifiP2pDeviceChanged(Intent intent) {
        thisDevice = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        if (wifiP2pDeviceList != null &&
                wifiP2pDeviceList.getDeviceList() != null &&
                wifiP2pDeviceList.getDeviceList().size() > 0) {
            wifiDevices = new ArrayList<>(wifiP2pDeviceList.getDeviceList());
            Log.d(TAG, "Peers List updated");
            mActivity.peerAvailable();
        } else {
            wifiDevices = null;
            Log.d(TAG, "No available devices");
        }

    }

    public IntentFilter getIntentFilter() {
        if (mIntentFilter == null) {
            //Intent Filter
            mIntentFilter = new IntentFilter();
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        }
        return mIntentFilter;
    }

    public ArrayList<WifiP2pDevice> getDeviceList() {
        if (wifiDevices != null) {
            return wifiDevices;
        }
        return null;
    }

    public WifiP2pDevice getThisDevice() {
        return thisDevice;
    }

    public void registerReceiver() {
        mActivity.registerReceiver(this, getIntentFilter());
    }

    public void unregisterReceiver() {
        Log.d(TAG,"Unregistering Receiver");
        mActivity.unregisterReceiver(this);
        if(game!=null) {
            game.Close();
            game.cancel(true);
        }
    }

    @Override
    public void onConnectionInfoAvailable(final WifiP2pInfo wifiP2pInfo) {
        Log.d(TAG, "Connection Established");
        boolean isHost = false;
        if (wifiP2pInfo.groupFormed) {
            // mActivity.setPlayers();
            if (wifiP2pInfo.isGroupOwner) {
                //server side
                game = new Game(mActivity);
                isHost = true;

            } else {
                //client side
                game = new Game(mActivity,wifiP2pInfo.groupOwnerAddress);
            }
            game.execute();
            //game.sendMsg("Start");
            mActivity.Show();
            mActivity.setPlayers(isHost);
        }else{
            Log.d(TAG,"Failed to form group");
        }
    }


    public void sendMsg(String msg) {
        if(game!=null) {
            game.sendMsg(msg);
        }else{
            Log.d(TAG,"Game null");
        }
    }
}
