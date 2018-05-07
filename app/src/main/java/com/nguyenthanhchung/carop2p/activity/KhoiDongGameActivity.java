package com.nguyenthanhchung.carop2p.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.nguyenthanhchung.carop2p.fragment.KiLucFragment;
import com.nguyenthanhchung.carop2p.fragment.MainMenuFragment;
import com.nguyenthanhchung.carop2p.fragment.SettingFragment;
import com.nguyenthanhchung.carop2p.fragment.ThucHienFragment;

public class KhoiDongGameActivity extends AppCompatActivity implements MainGameActivityCallBacks {
    MediaPlayer background_song;
    MediaPlayer button_click_sound;
    FrameLayout frameLayout;
    FrameLayout menuContainer;
    FragmentTransaction fragmentTransaction;
    HuongDanFragment huongDanFragment;
    ThucHienFragment thucHienFragment;
    SettingFragment settingFragment;
    KiLucFragment kiLucFragment;
    MainMenuFragment mainMenuFragment;
    boolean isMenuOpened = false;
    boolean isSettingOpened = false;
    boolean isInfoOpened = false;
    boolean isGuideOpened = false;
    boolean isBestOpened = false;
    Button btnPlay;
    Button btnMenu;
    boolean doubleBackToExitPressedOnce = false;
    int isSound = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_khoi_dong_game);
        background_song = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.background_sound);
        background_song.setLooping(true);
        button_click_sound = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.button_click);
        button_click_sound.setLooping(false);
        addControls();
        //Init button
        btnPlay = findViewById(R.id.btnPlay);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMenu();
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_click_sound.start();
                //code switch to play game screen here
                Intent intent = new Intent(getApplicationContext(), WiFiDirectActivity.class);
                startActivity(intent);
            }
        });
        String name = MySharedPreferences.getStringSharedPreferences(this, "Player", "name");
        if ("".equals(name)) {
            MySharedPreferences.saveStringSharedPreferences(this, "Player", "name", "Player");
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
                doubleBackToExitPressedOnce = false;
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

    private void addControls() {
        // add fragment main menu
        fragmentTransaction = getFragmentManager().beginTransaction();
        mainMenuFragment = MainMenuFragment.newInstance("MainMenu");
        fragmentTransaction.replace(R.id.menuContainer, mainMenuFragment);
        fragmentTransaction.hide(mainMenuFragment);
        fragmentTransaction.commit();

    }

    public void turnOnBackGroundSong() {
        if (background_song != null && background_song.isPlaying()) {
            turnOffBackGroundSong();
        }
        background_song = MediaPlayer.create(KhoiDongGameActivity.this, R.raw.background_sound);
        background_song.setLooping(true);
        background_song.start();
    }

    public void turnOffBackGroundSong() {
        if (background_song != null && background_song.isPlaying()) {
            background_song.stop();
            background_song.release();
            background_song = null;
        }

    }

    public void showGuide() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        huongDanFragment = HuongDanFragment.newInstance("HuongDan");
        fragmentTransaction.add(R.id.frameContent, huongDanFragment, "huongdan");
        fragmentTransaction.commit();

        btnPlay.setVisibility(View.INVISIBLE);
        isGuideOpened = true;
        if (isInfoOpened) {
            hideInfo();
        } else {
            if (isSettingOpened) {
                hideSetting();
            } else if (isBestOpened) {
                hideKiLuc();
            }
        }
        //code switch to guide game screen here
    }

    public void hideGuide() {

        if (isGuideOpened) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(huongDanFragment);
            fragmentTransaction.commit();
            isGuideOpened = false;
        }

    }

    public void hideInfo() {

        if (isInfoOpened) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(thucHienFragment);
            fragmentTransaction.commit();
            isInfoOpened = false;
        }
    }

    public void showInfo() {
        //code switch to show info screen here
        fragmentTransaction = getFragmentManager().beginTransaction();
        thucHienFragment = ThucHienFragment.newInstance("ThucHien");
        fragmentTransaction.add(R.id.frameContent, thucHienFragment, "thuchien");
        fragmentTransaction.commit();
        btnPlay.setVisibility(View.INVISIBLE);
        isInfoOpened = true;
        if (isSettingOpened) {
            hideSetting();
        } else {
            if (isGuideOpened) {
                hideGuide();
            } else if (isBestOpened) {
                hideKiLuc();
            }
        }
    }


    public void showMenu() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(mainMenuFragment);
        fragmentTransaction.commit();
        btnMenu.setVisibility(View.INVISIBLE);
        isMenuOpened = true;
    }

    public void hideMenu() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(mainMenuFragment);
        fragmentTransaction.commit();
        btnMenu.setVisibility(View.VISIBLE);
        isMenuOpened = false;
    }

    public void showSetting() {

        fragmentTransaction = getFragmentManager().beginTransaction();
        settingFragment = SettingFragment.newInstance("Setting");
        fragmentTransaction.add(R.id.frameContent, settingFragment, "setting");
        fragmentTransaction.commit();

        btnPlay.setVisibility(View.INVISIBLE);
        isSettingOpened = true;
        if (isInfoOpened) {
            hideInfo();
        } else {
            if (isGuideOpened) {
                hideGuide();
            } else if (isBestOpened) {
                hideKiLuc();
            }
        }

    }

    private void hideSetting() {
        if (isSettingOpened) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(settingFragment);
            fragmentTransaction.commit();
            isSettingOpened = false;
        }
    }

    public void showKiLuc() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        kiLucFragment = KiLucFragment.newInstance("KiLuc");
        fragmentTransaction.add(R.id.frameContent, kiLucFragment, "kiLuc");
        fragmentTransaction.commit();

        btnPlay.setVisibility(View.INVISIBLE);
        isBestOpened = true;
        if (isInfoOpened) {
            hideInfo();
        } else {
            if (isGuideOpened) {
                hideGuide();
            } else if (isSettingOpened) {
                hideSetting();
            }
        }

    }

    private void hideKiLuc() {
        if (isBestOpened) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(kiLucFragment);
            fragmentTransaction.commit();
            isBestOpened = false;
        }
    }

    private void setSound() {
        isSound = MySharedPreferences.getIntergerSharedPreferences(this, "Setting", "sound");
        if (isSound >= 0) {
            turnOnBackGroundSong();
        } else {
            turnOffBackGroundSong();
        }
    }


    @Override
    public void onMsgFromFragmentToMainGame(String sender, String strValue) {
        if (sender == null || strValue == null) return;
        if (sender.equals("MainMenu")) {
            if (strValue.equals("close")) {
                if (isMenuOpened) {
                    btnPlay.setVisibility(View.VISIBLE);
                    hideMenu();
                    hideSetting();
                    hideInfo();
                    hideGuide();
                    hideKiLuc();
                }
            } else if (strValue.equals("setting")) {
                if (isSettingOpened) {
                    btnPlay.setVisibility(View.VISIBLE);
                    hideSetting();
                } else {
                    showSetting();
                    isInfoOpened = false;
                    isGuideOpened = false;
                    isBestOpened = false;
                }
            } else if (strValue.equals("info")) {
                if (isInfoOpened) {
                    btnPlay.setVisibility(View.VISIBLE);
                    hideInfo();
                } else {
                    showInfo();
                    isSettingOpened = false;
                    isGuideOpened = false;
                    isBestOpened = false;

                }
            } else if (strValue.equals("guide")) {
                if (isGuideOpened) {
                    btnPlay.setVisibility(View.VISIBLE);
                    hideGuide();
                } else {
                    showGuide();
                    isSettingOpened = false;
                    isInfoOpened = false;
                    isBestOpened = false;
                }
            } else if (strValue.equals("best")) {
                if (isBestOpened) {
                    btnPlay.setVisibility(View.VISIBLE);
                    hideKiLuc();
                } else {
                    showKiLuc();
                    isSettingOpened = false;
                    isInfoOpened = false;
                    isGuideOpened = false;
                }
            }

        } else {
            if (sender.equals("SettingFragment")) {
                if (strValue.equals("sound")) {
                    setSound();
                }
            }
        }
    }
}
