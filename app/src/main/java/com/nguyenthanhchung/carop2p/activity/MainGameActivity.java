package com.nguyenthanhchung.carop2p;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.nguyenthanhchung.carop2p.fragment.BoardEmotionFragment;
import com.nguyenthanhchung.carop2p.MainGameActivityCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.fragment.BoardGameFragment;

public class MainGameActivity extends AppCompatActivity implements MainGameActivityCallBacks {

    FragmentTransaction fragmentTransaction;
    BoardGameFragment boardGameFragment;
    BoardEmotionFragment  emotionBoardFragmet;
    ImageButton btnOpenEmotionBoard;
    boolean isOpenedEmotionBoard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_game);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnOpenEmotionBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpenedEmotionBoard == false){
                    showEmotionBoard();
                    isOpenedEmotionBoard = true;
                }
            }
        });
    }

    private void addControls() {

        btnOpenEmotionBoard = findViewById(R.id.btnOpenEmotionBoard);

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
    }




    @Override
    public void onMsgFromFragmentToMainGame(String sender, String strValue) {
        if(sender == null || strValue == null) return;
        if(sender.equals("EmotionBoard")){
            if(strValue.equals("close")){
                if(isOpenedEmotionBoard){
                    hideEmotionBoard();
                    isOpenedEmotionBoard = false;
                }
            }
        }else if(sender.equals("GameBoard")){

        }
    }

    private void showEmotionBoard(){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(emotionBoardFragmet);
        fragmentTransaction.commit();
    }

    private void hideEmotionBoard(){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(emotionBoardFragmet);
        fragmentTransaction.commit();
    }
}
