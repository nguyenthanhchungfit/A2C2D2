package com.nguyenthanhchung.carop2p.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import com.nguyenthanhchung.carop2p.Helper.MySharedPreferences;

import com.nguyenthanhchung.carop2p.MainGameActivityCallBacks;
import com.nguyenthanhchung.carop2p.R;

import com.nguyenthanhchung.carop2p.fragment.HuongDanFragment;
import com.nguyenthanhchung.carop2p.fragment.SettingFragment;
import com.nguyenthanhchung.carop2p.fragment.ThucHienFragment;


import java.util.ArrayList;

public class KhoiDongGameActivity extends AppCompatActivity implements MainGameActivityCallBacks {
    MediaPlayer background_song;
    MediaPlayer button_click_sound;
    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;
    HuongDanFragment huongDanFragment;
    ThucHienFragment thucHienFragment;
    SettingFragment settingFragment;
    boolean isOpenedSetting = false;
    Button btnPlay;
    Button btnGuide;
    Button btnInfo;
    Button btnExit;
    boolean doubleBackToExitPressedOnce = false;
    Button btnSetting;
    int isSound = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_khoi_dong_game);
        frameLayout = findViewById(R.id.frameContent);
        frameLayout.setVisibility(View.INVISIBLE);

        background_song = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.background_sound);
        background_song.setLooping(true);
        button_click_sound = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.button_click);
        button_click_sound.setLooping(false);
        //turnOnBackGroundSong();

        //Init button
        btnPlay = findViewById(R.id.btnPlay);
        btnGuide = findViewById(R.id.btnGuide);
        btnInfo = findViewById(R.id.btnInfo);
        btnExit = findViewById(R.id.btnExit);
        btnSetting = findViewById(R.id.btnSetting);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_click_sound.start();
                //code switch to play game screen here
                Intent intent = new Intent(getApplicationContext(), WiFiDirectActivity.class);
                startActivity(intent);
            }
        });
        String name = MySharedPreferences.getStringSharedPreferences(this,"Player","name");
        if("".equals(name)){
            MySharedPreferences.saveStringSharedPreferences(this,"Player","name","Player");
        }
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        turnOffBackGroundSong();
        super.onPause();
    }

    @Override
    public void onResume() {
       setSound();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void turnOnBackGroundSong(){
        if(background_song != null && background_song.isPlaying()){
            turnOffBackGroundSong();
        }
        background_song = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.background_sound);
        background_song.setLooping(true);
        background_song.start();
    }

    public void turnOffBackGroundSong(){
        if(background_song != null && background_song.isPlaying()){
            background_song.stop();
            background_song.release();
            background_song = null;
        }

    }

    public void guideGame(View view) {
        button_click_sound.start();
        fragmentTransaction = getFragmentManager().beginTransaction();
        huongDanFragment = new HuongDanFragment();
        fragmentTransaction.add(R.id.frameContent, huongDanFragment, "guide");
        fragmentTransaction.commit();
        hideBtn();
        //code switch to guide game screen here
    }

    public void exitGuide(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        huongDanFragment = (HuongDanFragment) getFragmentManager().findFragmentByTag("guide");
        fragmentTransaction.remove(huongDanFragment);
        fragmentTransaction.commit();
        showBtn();
    }

    public void exitInfo(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        thucHienFragment = (ThucHienFragment) getFragmentManager().findFragmentByTag("info");
        fragmentTransaction.remove(thucHienFragment);
        fragmentTransaction.commit();
        showBtn();
    }

    public void showInfo(View view) {
        button_click_sound.start();
        fragmentTransaction = getFragmentManager().beginTransaction();
        thucHienFragment = new ThucHienFragment();
        fragmentTransaction.add(R.id.frameContent, thucHienFragment, "info");
        fragmentTransaction.commit();
        hideBtn();
        //code switch to show info screen here
    }

    public void exitGame(View view) {
        button_click_sound.start();
        finish();
        //code switch to exit screen here
    }

    public void hideBtn() {
        btnPlay.setVisibility(View.INVISIBLE);
        btnGuide.setVisibility(View.INVISIBLE);
        btnInfo.setVisibility(View.INVISIBLE);
        btnExit.setVisibility(View.INVISIBLE);
        btnSetting.setVisibility(View.INVISIBLE);
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void showBtn() {
        frameLayout.setVisibility(View.INVISIBLE);
        btnPlay.setVisibility(View.VISIBLE);
        btnGuide.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        btnExit.setVisibility(View.VISIBLE);
        btnSetting.setVisibility(View.VISIBLE);
    }

    public void turnOnSetting(View view) {
        button_click_sound.start();
        fragmentTransaction = getFragmentManager().beginTransaction();
        settingFragment = new SettingFragment();
        fragmentTransaction.add(R.id.frameContent, settingFragment, "setting");
        fragmentTransaction.commit();
        hideBtn();
        isOpenedSetting = true;
    }
    private void hideSetting(){
        fragmentTransaction = getFragmentManager().beginTransaction();
        settingFragment = (SettingFragment)getFragmentManager().findFragmentByTag("setting");
        fragmentTransaction.remove(settingFragment);
        fragmentTransaction.commit();
        showBtn();
    }

    private void setSound(){
        isSound = MySharedPreferences.getIntergerSharedPreferences(this,"Setting","sound");
        if(isSound >= 0){
            turnOnBackGroundSong();
        }else{
            turnOffBackGroundSong();
        }
    }

    @Override
    public void onMsgFromFragmentToMainGame(String sender, String strValue) {
        if(sender == null || strValue == null) return;
        if(sender.equals("SettingFragmentClose")){
            if(strValue.equals("close")){
                if(isOpenedSetting){
                    hideSetting();
                    isOpenedSetting = false;
                    //setSound();
                }
            }else if(strValue.equals("sound")){
                setSound();
            }
        }
        else if(sender.equals("GameBoard")){

        }
    }
}
