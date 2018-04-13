package com.nguyenthanhchung.carop2p.activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.nguyenthanhchung.carop2p.MainGameActivityCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.fragment.BoardGameFragment;

public class MainGameActivity extends AppCompatActivity implements MainGameActivityCallBacks {

    FragmentTransaction fragmentTransaction;
    BoardGameFragment boardGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_game);

        addControls();

    }

    private void addControls() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        boardGameFragment = BoardGameFragment.newInstance("boardGame");
        fragmentTransaction.replace(R.id.fragmentBoardGame, boardGameFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onMsgFromFragmentToMainGame(String sender, String strValue) {

    }
}
