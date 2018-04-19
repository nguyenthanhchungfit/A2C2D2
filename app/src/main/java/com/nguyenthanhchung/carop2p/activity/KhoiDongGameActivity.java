package com.nguyenthanhchung.carop2p.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.nguyenthanhchung.carop2p.R;

public class KhoiDongGameActivity extends AppCompatActivity {
    MediaPlayer background_song;
    MediaPlayer button_click_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_khoi_dong_game);
        background_song = MediaPlayer.create(KhoiDongGameActivity.this,R.raw.background_sound);
        background_song.setLooping(true);
        button_click_sound = MediaPlayer.create(KhoiDongGameActivity.this,R.raw.button_click);
        turnOnBackGroundSong();
    }
    @Override
    protected void onPause() {
        super.onPause();
        background_song.release();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(background_song.isPlaying()==false){
            background_song.start();
        }
    }

    public void turnOnBackGroundSong(){
        background_song.start();
    }

    public void turnOffBackGroundSong(View v){
        if(background_song.isPlaying()){
            background_song.stop();
        }
    }

    public void playGame(View view) {
        button_click_sound.start();
        startActivity(new Intent(KhoiDongGameActivity.this, MainGameActivity.class));
        //code switch to play game screen here
    }

    public void guideGame(View view) {
        button_click_sound.start();
        //code switch to guide game screen here
    }

    public void showInfo(View view) {
        button_click_sound.start();
        //code switch to show info screen here
    }

    public void exitGame(View view) {
        button_click_sound.start();
        //code switch to exit screen here
    }
}
