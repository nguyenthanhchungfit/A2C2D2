package com.nguyenthanhchung.carop2p.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.nguyenthanhchung.carop2p.R;

import com.nguyenthanhchung.carop2p.fragment.HuongDanFragment;
import com.nguyenthanhchung.carop2p.fragment.ThucHienFragment;


import java.util.ArrayList;

public class KhoiDongGameActivity extends AppCompatActivity {
    MediaPlayer background_song;
    MediaPlayer button_click_sound;
    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;
    HuongDanFragment huongDanFragment;
    ThucHienFragment thucHienFragment;
    Button btnPlay;
    Button btnGuide;
    Button btnInfo;
    Button btnExit;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_khoi_dong_game);
        frameLayout = findViewById(R.id.frameContent);
        frameLayout.setVisibility(View.INVISIBLE);

        background_song = MediaPlayer.create(KhoiDongGameActivity.this,R.raw.background_sound);
        background_song.setLooping(true);
        button_click_sound = MediaPlayer.create(KhoiDongGameActivity.this,R.raw.button_click);
        button_click_sound.setLooping(false);
        //turnOnBackGroundSong();

        //Init button
        btnPlay = findViewById(R.id.btnPlay);
        btnGuide = findViewById(R.id.btnGuide);
        btnInfo = findViewById(R.id.btnInfo);
        btnExit = findViewById(R.id.btnExit);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_click_sound.start();
                //code switch to play game screen here
                Intent intent = new Intent(getApplicationContext(),WiFiDirectActivity.class);
                startActivity(intent);
            }
        });

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
        background_song.stop();
        background_song.release();
        super.onPause();
    }

    @Override
    public void onResume() {
        background_song = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.background_sound);
        background_song.setLooping(true);
        background_song.start();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(background_song.isPlaying()==false){
//            background_song.start();
//        }
    }

//    public void turnOnBackGroundSong(){
//        background_song.start();
//    }

    public void turnOffBackGroundSong(View v){
        if(background_song.isPlaying()){
            background_song.stop();
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
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void showBtn() {
        frameLayout.setVisibility(View.INVISIBLE);
        btnPlay.setVisibility(View.VISIBLE);
        btnGuide.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        btnExit.setVisibility(View.VISIBLE);
    }

}
