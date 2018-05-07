package com.nguyenthanhchung.carop2p.activity;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nguyenthanhchung.carop2p.Helper.MySharedPreferences;
import com.nguyenthanhchung.carop2p.MainGameActivityCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.fragment.BoardEmotionFragment;
import com.nguyenthanhchung.carop2p.fragment.BoardGameFragment;
import com.nguyenthanhchung.carop2p.fragment.ChatMessageFragment;
import com.nguyenthanhchung.carop2p.fragment.EndGameFragment;
import com.nguyenthanhchung.carop2p.fragment.PlayerFragment;
import com.nguyenthanhchung.carop2p.handler.ActionListenerHandler;
import com.nguyenthanhchung.carop2p.handler.WiFiDirectReceiver;
import com.nguyenthanhchung.carop2p.model.PackageData;
import com.nguyenthanhchung.carop2p.model.Player;
import com.nguyenthanhchung.carop2p.model.TypePackage;

import java.util.ArrayList;

public class WiFiDirectActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener, MainGameActivityCallBacks {

    private static final String TAG = "WiFiDirectActivity";
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    public WiFiDirectReceiver mReceiver;
    //Arrays with devices'
    private ArrayList<String> devicesNames;
    private ArrayList<String> devicesAddress;
    private ArrayList<WifiP2pDevice> devicesList;
    Context context;
    private String opponentsName;
    private ArrayAdapter<String> hybridAdapter;
    private static WiFiDirectActivity thisActivity;
    private ListView deviceListView;
    private Button btn;


    FragmentTransaction fragmentTransaction;
    BoardGameFragment boardGameFragment;
    BoardEmotionFragment emotionBoardFragmet;
    PlayerFragment mainPlayerFragment, friendPlayerFragment;
    ChatMessageFragment mainChatMessageFragment, friendChatMessageFragment;
    EndGameFragment endGameFragment;
    ImageButton btnOpenEmotionBoard;
    ImageButton btnSendMessage;
    boolean isOpenedEmotionBoard = false;

    RelativeLayout layoutGame;
    MediaPlayer background_song;


    // Player
    final Player mainPlayer = new Player(); // Người chơi hiện tại của máy
    final Player secondPlayer = new Player();   // Người chơi với mình

    int isSound = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_direct);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        context = this.getApplicationContext();
        devicesNames = new ArrayList<>();
        devicesAddress = new ArrayList<>();

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), this);

        hybridAdapter = new ArrayAdapter<String>(this,
                R.layout.item,
                R.id.cheese_name,
                devicesNames
        );


        deviceListView = (ListView) findViewById(R.id.list_view);
        deviceListView.setAdapter(hybridAdapter);

//        //Demo mẫu send msg, xem trong log
//        btn = findViewById(R.id.btnSend);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mReceiver != null)
//                    mReceiver.sendMsg("13213213132");
//            }
//        });

        //Kết nối với thiết bị khi click
        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int possition, long id) {
                if (devicesList != null) {
                    String address = devicesAddress.get(possition);
                    String name = devicesNames.get(possition);
                    Toast.makeText(context, "Connecting to " + name, Toast.LENGTH_SHORT).show();
                    connectToPeer(devicesList.get(possition));
                }
            }
        });

        thisActivity = this;
        opponentsName = "Opponent";

        layoutGame = findViewById(R.id.layoutGame);
        layoutGame.setVisibility(RelativeLayout.GONE);
        //this.Show();

    }

    private void ShowName() {
        // Set Ten, Set Hinh
        if (mainPlayer.getId() == Boolean.TRUE) {
            mainPlayerFragment.setImgPlayerSign(R.drawable.ic_x);

            friendPlayerFragment.setImgPlayerSign(R.drawable.ic_o);
            friendPlayerFragment.setImgPlayerAVT(R.drawable.image_player2);

            mainPlayerFragment.setImgPlayerBG(R.drawable.frame_avt);
            friendPlayerFragment.setImgPlayerBG(R.drawable.frame_avt_turn);
        } else {
            mainPlayerFragment.setImgPlayerSign(R.drawable.ic_o);

            friendPlayerFragment.setImgPlayerSign(R.drawable.ic_x);
            friendPlayerFragment.setImgPlayerAVT(R.drawable.image_player2);

            mainPlayerFragment.setImgPlayerBG(R.drawable.frame_avt_turn);
            friendPlayerFragment.setImgPlayerBG(R.drawable.frame_avt);
        }
        String name = MySharedPreferences.getStringSharedPreferences(this, "Player", "name");
        mainPlayerFragment.setPlayName(name);
        //friendPlayerFragment.setPlayName("Google");
    }

    private void showEmotionBoard() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(emotionBoardFragmet);
        fragmentTransaction.commit();
    }

    private void hideEmotionBoard() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(emotionBoardFragmet);
        fragmentTransaction.commit();
    }

    private void addEvents() {
        btnOpenEmotionBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpenedEmotionBoard == false) {
                    showEmotionBoard();
                    isOpenedEmotionBoard = true;
                    // Gửi thông điệp tới BoardGameFragment để khóa click
                    boardGameFragment.onMsgFromMainToFrag("emotion_open");
                }
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(WiFiDirectActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.layout_user_input_dialog_box, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder
                        (WiFiDirectActivity.this);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                String text = userInputDialogEditText.getText().toString();
                                if (!text.equals("")) {
                                    showMainChat(text);
                                    PackageData packageData = new PackageData(TypePackage.MSG, text);
                                    sendMsg(packageData);
                                }
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });
    }

    private void addControls() {

        btnOpenEmotionBoard = findViewById(R.id.btnOpenEmotionBoard);
        btnSendMessage = findViewById(R.id.btnSendMessage);

        // add fragment boardgame
        fragmentTransaction = getFragmentManager().beginTransaction();
        boardGameFragment = BoardGameFragment.newInstance("boardGame");
        fragmentTransaction.replace(R.id.fragmentBoardGame, boardGameFragment);
        fragmentTransaction.commit();

        // add fragment emotion board
        fragmentTransaction = getFragmentManager().beginTransaction();
        emotionBoardFragmet = BoardEmotionFragment.newInstance("EmotionBoard");
        fragmentTransaction.replace(R.id.fragmentEmotionBoard, emotionBoardFragmet);
        fragmentTransaction.hide(emotionBoardFragmet);
        fragmentTransaction.commit();

        // add fragment main player
        fragmentTransaction = getFragmentManager().beginTransaction();
        mainPlayerFragment = PlayerFragment.newInstance("MainPlayer");
        fragmentTransaction.replace(R.id.fragmentPlayerChinh, mainPlayerFragment);
        fragmentTransaction.commit();

        // add fragment second player
        fragmentTransaction = getFragmentManager().beginTransaction();
        friendPlayerFragment = PlayerFragment.newInstance("FriendPlayer");
        fragmentTransaction.replace(R.id.fragmentPlayerBan, friendPlayerFragment);
        fragmentTransaction.commit();

        // Main Chat Fragment
        fragmentTransaction = getFragmentManager().beginTransaction();
        mainChatMessageFragment = ChatMessageFragment.newInstance("chat");
        fragmentTransaction.replace(R.id.fragmentMainChat, mainChatMessageFragment);
        fragmentTransaction.hide(mainChatMessageFragment);
        fragmentTransaction.commit();

        // Friend Chat Fragment
        fragmentTransaction = getFragmentManager().beginTransaction();
        friendChatMessageFragment = ChatMessageFragment.newInstance("chat");
        fragmentTransaction.replace(R.id.fragmentFriendChat, friendChatMessageFragment);
        fragmentTransaction.hide(friendChatMessageFragment);
        fragmentTransaction.commit();

        // Endgame Chat Fragment
        fragmentTransaction = getFragmentManager().beginTransaction();
        endGameFragment = EndGameFragment.newInstance("EndGame");
        fragmentTransaction.replace(R.id.fragmentGameEnd, endGameFragment);
        fragmentTransaction.hide(endGameFragment);
        fragmentTransaction.commit();
    }

    public void setPlayers(boolean isHost) {
        if (isHost) {
            mainPlayer.setId(true);
            secondPlayer.setId(false);
            boardGameFragment.onFromMainToFragmentStatePlayer("state", mainPlayer.getId());
        } else {
            mainPlayer.setId(false);
            secondPlayer.setId(true);
            boardGameFragment.onFromMainToFragmentStatePlayer("state", mainPlayer.getId());
        }
    }

    private void showMainChat(String text) {
        mainChatMessageFragment.setBackground(R.drawable.out_message_bg);
        mainChatMessageFragment.setMessage(text);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(mainChatMessageFragment);
        fragmentTransaction.commit();
        new CountDownTimer(4000, 4000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.hide(mainChatMessageFragment);
                fragmentTransaction.commit();
            }
        }.start();
    }

    private void showFriendChat(String text) {
        friendChatMessageFragment.setBackground(R.drawable.in_message_bg);
        friendChatMessageFragment.setMessage(text);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(friendChatMessageFragment);
        fragmentTransaction.commit();
        new CountDownTimer(4000, 4000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.hide(friendChatMessageFragment);
                fragmentTransaction.commit();
            }
        }.start();
    }
    private void showEndGame(String result){
        endGameFragment.onMsgFromMainToFrag(result);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(endGameFragment);
        fragmentTransaction.commit();
    }

    /**
     * Tìm kiếm thiết bị có thể kết nối
     */
    public void peerAvailable() {
        devicesList = mReceiver.getDeviceList();
        if (devicesList != null) {
            //Xóa dữ liệu adapter
            hybridAdapter.clear();
            // Tính toán và cập nhật các thiết bị
            calculateDevices();
            //Báo adapter thay đổi
            hybridAdapter.notifyDataSetChanged();
        }
    }


    /**
     * Tính toán danh sách thiết bị, lấy tên + ip thêm vào devicesNames và devicesAddress
     */
    private void calculateDevices() {
        if (devicesList != null) {
            if (devicesNames != null) devicesNames.clear();
            if (devicesAddress != null) devicesAddress.clear();
            for (int i = 0; i < devicesList.size(); i++) {
                devicesNames.add(devicesList.get(i).deviceName);
                devicesAddress.add(devicesList.get(i).deviceAddress);
            }
        }
    }

    /**
     * Kết nối với thiết bị
     */
    public void connectToPeer(WifiP2pDevice device) {
        if (device != null) {
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = device.deviceAddress;
            opponentsName = device.deviceName;
            config.wps.setup = WpsInfo.PBC;
            mManager.connect(mChannel, config, new ActionListenerHandler(this, "Connection to peer"));
        } else {
            Log.d(TAG, "Can not find that device");
        }
    }

    @Override
    protected void onResume() {
        setSound();
        super.onResume();
        //registerWifiReceiver();
    }

    @Override
    protected void onPause() {
        turnOffBackGroundSong();
        super.onPause();
        //unregisterWifiReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        registerWifiReceiver();
        mManager.discoverPeers(mChannel, new ActionListenerHandler(this, "Discover peers"));

        //findViewById(R.id.helpFAB).setVisibility(View.VISIBLE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //sendMsg("LEFT");
        PackageData packageData = new PackageData(TypePackage.END, "1");
        sendMsg(packageData);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mManager.requestGroupInfo(mChannel, new WifiP2pManager.GroupInfoListener() {
            @Override
            public void onGroupInfoAvailable(WifiP2pGroup group) {
                if (group != null && mManager != null && mChannel != null
                        && group.isGroupOwner()) {
                    mManager.removeGroup(mChannel, new ActionListenerHandler(thisActivity, "Group removal"));
                }
            }
        });
        //mManager.removeGroup(mChannel, new ActionListenerHandler(this, "Group removal"));
        mManager.cancelConnect(mChannel, new ActionListenerHandler(this, "Canceling connect"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mManager.stopPeerDiscovery(mChannel, new ActionListenerHandler(this, "Stop Discovery"));
        }
        unregisterWifiReceiver();
        mManager = null;
        Log.d(TAG, "WifiDirectActivity stopped");
        this.Hide();
    }

    private void setSound() {
        isSound = MySharedPreferences.getIntergerSharedPreferences(this,"Setting","sound");
        if (isSound >= 0) {
            turnOnBackGroundSong();
        } else {
            turnOffBackGroundSong();
        }
    }

    public void turnOnBackGroundSong() {
        if(background_song != null && background_song.isPlaying()){
            turnOffBackGroundSong();
        }
        background_song = MediaPlayer.create(WiFiDirectActivity.this, R.raw.playgame_sound);
        background_song.setLooping(true);
        background_song.start();
    }

    public void turnOffBackGroundSong() {
        if(background_song != null && background_song.isPlaying()){
            background_song.stop();
            background_song.release();
            background_song = null;
        }
    }


    private void registerWifiReceiver() {
        mReceiver = new WiFiDirectReceiver(mManager, mChannel, this);
        mReceiver.registerReceiver();
    }

    private void unregisterWifiReceiver() {
        if (mReceiver != null) {
            mReceiver.unregisterReceiver();
        }
        mReceiver = null;
    }

    /**
     * Nhận dữ liệu từ game để xử lí tác vụ game
     **/
    public void handleIncoming(String msg) {
        Log.d(TAG, "Incoming " + msg);
        PackageData packageData = new PackageData(msg);
        if (packageData.getType() == TypePackage.EMOTI) {
            Integer idImage = Integer.parseInt(packageData.getMsg());
            friendPlayerFragment.onImageFromMainToFrag("avatar", idImage);
        } else if (packageData.getType() == TypePackage.TURN) {
            Integer idCell = Integer.parseInt(packageData.getMsg());
            if (secondPlayer.getId() == Boolean.TRUE) {
                secondPlayer.SetOCo(idCell);
                boardGameFragment.setCellBoard("X", idCell);
            } else {
                secondPlayer.SetOCo(idCell);
                boardGameFragment.setCellBoard("O", idCell);
            }
            mainPlayerFragment.setImgPlayerBG(R.drawable.frame_avt);
            friendPlayerFragment.setImgPlayerBG(R.drawable.frame_avt_turn);
        } else if (packageData.getType() == TypePackage.END) {
            Toast.makeText(this, "Player 2 out the game", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else if (packageData.getType() == TypePackage.NAME) {
            friendPlayerFragment.setPlayName(packageData.getMsg());
        } else if (packageData.getType() == TypePackage.MSG) {
            showFriendChat(packageData.getMsg());
        }
        
    }

    /*Đóng show listView danh sách các thiết bị*/
    public void Show() {
        //deviceListView.setVisibility(View.INVISIBLE);
        layoutGame.setVisibility(RelativeLayout.VISIBLE);
        addControls();
        addEvents();
    }

    public void sendData() {
        PackageData packageData = new PackageData(TypePackage.NAME, MySharedPreferences.getStringSharedPreferences(this, "Player", "name"));
        sendMsg(packageData);
    }

    public void Hide() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            mManager.stopPeerDiscovery(mChannel, new ActionListenerHandler(this, "Stop Discovery"));
//        }
//        mManager.removeGroup(mChannel, new ActionListenerHandler(this, "Group removal"));
//        mManager.cancelConnect(mChannel, new ActionListenerHandler(this, "Canceling connect"));
//        unregisterWifiReceiver();
//        mManager = null;
        if (boardGameFragment != null) {
            layoutGame.setVisibility(RelativeLayout.GONE);
            boardGameFragment.resetCellBoard();
            mainPlayer.TaoLaiBanCo();
            secondPlayer.TaoLaiBanCo();
        }
    }

    /*Gửi msg sang thiết bị khác*/
    public void sendMsg(PackageData packageData) {
        Log.d(TAG, "Sending " + packageData.getType().toString() + " - Data: " + packageData.getMsg());
        mReceiver.sendMsg(packageData.toString());

    }

    //Tạo mới channel;
    @Override
    public void onChannelDisconnected() {
        Log.d(TAG, "WIFI Direct Disconnected, reinitializing");
        reinitialize();
    }

    public void reinitialize() {
        mChannel = mManager.initialize(this, getMainLooper(), this);
        if (mChannel != null) {
            Log.d(TAG, "WIFI Direct reinitialize : SUCCESS");
        } else {
            Log.d(TAG, "WIFI Direct reinitialize : FAILURE");
        }
    }

    /**
     * Nhận dữ liệu từ Fragment và gửi sang thiết bị khác bằng sendMsg
     *
     * @param sender
     * @param strValue
     */
    @Override
    public void onMsgFromFragmentToMainGame(String sender, String strValue) {
        Log.d(TAG, "From: " + sender + " - Value: " + strValue);
        if (sender == null || strValue == null) return;
        if (sender.equals("EmotionBoardClose")) {
            if (strValue.equals("close")) {
                if (isOpenedEmotionBoard) {
                    hideEmotionBoard();
                    isOpenedEmotionBoard = false;
                    // Gửi thông điệp tới BoardGame cho đánh
                    boardGameFragment.onMsgFromMainToFrag("emotion_close");
                }
            }
        } else if (sender.equals("GameBoardImage")) {
            try {
                Integer idImage = Integer.parseInt(strValue);
                PackageData packageData = new PackageData(TypePackage.EMOTI, idImage.toString());
                sendMsg(packageData);
                mainPlayerFragment.onImageFromMainToFrag("avatar", idImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (sender.equals("GameBoard")) {
            //Test
            PackageData packageData = new PackageData(TypePackage.TURN, strValue);
            sendMsg(packageData);
        } else if (sender.equals("GameBoardX")) {
            mainPlayer.SetOCo(Integer.parseInt(strValue));
            PackageData packageData = new PackageData(TypePackage.TURN, strValue);
            sendMsg(packageData);
            if (mainPlayer.KiemTraKetThuc()) {
                showEndGame("Bạn thắng!");
            }
            mainPlayerFragment.setImgPlayerBG(R.drawable.frame_avt_turn);
            friendPlayerFragment.setImgPlayerBG(R.drawable.frame_avt);
        } else if (sender.equals("GameBoardO")) {
            mainPlayer.SetOCo(Integer.parseInt(strValue));
            PackageData packageData = new PackageData(TypePackage.TURN, strValue);
            sendMsg(packageData);
            if (mainPlayer.KiemTraKetThuc()) {
                showEndGame("Bạn thắng!");
            }
            mainPlayerFragment.setImgPlayerBG(R.drawable.frame_avt_turn);
            friendPlayerFragment.setImgPlayerBG(R.drawable.frame_avt);
        } else if (sender.equals("Check")) {
            if (secondPlayer.KiemTraKetThuc()) {
                showEndGame("Bạn thua!");
            }
        } else if (sender.equals("PlayerFragment")) {
            if (strValue.equals("UpdateView")) {
                ShowName();
            }
        }
        else if (sender.equals("EndGame")) {
            if (strValue.equals("OK")) {
                onBackPressed();
            }
        }
    }

}
