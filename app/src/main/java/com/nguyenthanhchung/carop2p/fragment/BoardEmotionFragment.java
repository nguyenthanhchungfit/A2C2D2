package com.nguyenthanhchung.carop2p.fragment;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nguyenthanhchung.carop2p.FragmentCallBacks;
import com.nguyenthanhchung.carop2p.activity.MainGameActivity;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.adapter.ImageEmotionAdapter;
import com.nguyenthanhchung.carop2p.model.ImageEmotion;

import java.util.ArrayList;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class BoardEmotionFragment extends Fragment implements FragmentCallBacks{
    MainGameActivity main;
    Context context = null;
    GridView gvBoardEmotion;
    ImageEmotionAdapter adapter;
    ArrayList<ImageEmotion> listEmotion;
    ImageButton btnCloseEmotionBoard;

    public static BoardEmotionFragment newInstance(String strArgs){
        BoardEmotionFragment fragment = new BoardEmotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("BoardEmotion", strArgs);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            context = getActivity();
            main = (MainGameActivity)getActivity();
        }catch (IllegalStateException e){
            throw  new IllegalStateException("Main game must implements Callback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(
                R.layout.layout_board_emotion_fragment, null);
        btnCloseEmotionBoard = layout.findViewById(R.id.btnCloseEmotionBoard);
        gvBoardEmotion = layout.findViewById(R.id.gvBoardEmotion);
        initListImageEmotion();
        adapter = new ImageEmotionAdapter(main, R.layout.layout_adapter_gv_emotion, listEmotion);
        gvBoardEmotion.setAdapter(adapter);

        //Event
        gvBoardEmotion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos = ((Integer)position).toString();
                Toast.makeText(main, pos, Toast.LENGTH_SHORT).show();
            }
        });

        btnCloseEmotionBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragmentToMainGame("EmotionBoard", "close");
            }
        });
        return layout;
    }

    private void initListImageEmotion() {
        listEmotion = new ArrayList<>();
        listEmotion.add(new ImageEmotion(R.drawable.emo1));
        listEmotion.add(new ImageEmotion(R.drawable.emo2));
        listEmotion.add(new ImageEmotion(R.drawable.emo3));
        listEmotion.add(new ImageEmotion(R.drawable.emo4));
        listEmotion.add(new ImageEmotion(R.drawable.emo5));
        listEmotion.add(new ImageEmotion(R.drawable.emo6));
        listEmotion.add(new ImageEmotion(R.drawable.emo7));
        listEmotion.add(new ImageEmotion(R.drawable.emo8));
        listEmotion.add(new ImageEmotion(R.drawable.emo9));
        listEmotion.add(new ImageEmotion(R.drawable.emo10));
        listEmotion.add(new ImageEmotion(R.drawable.emo11));
        listEmotion.add(new ImageEmotion(R.drawable.emo12));
        listEmotion.add(new ImageEmotion(R.drawable.emo13));
        listEmotion.add(new ImageEmotion(R.drawable.emo14));
        listEmotion.add(new ImageEmotion(R.drawable.emo15));
        listEmotion.add(new ImageEmotion(R.drawable.emo16));
        listEmotion.add(new ImageEmotion(R.drawable.emo17));
        listEmotion.add(new ImageEmotion(R.drawable.emo18));
        listEmotion.add(new ImageEmotion(R.drawable.emo19));
        listEmotion.add(new ImageEmotion(R.drawable.emo20));
        listEmotion.add(new ImageEmotion(R.drawable.emo21));
        listEmotion.add(new ImageEmotion(R.drawable.emo22));
        listEmotion.add(new ImageEmotion(R.drawable.emo23));
        listEmotion.add(new ImageEmotion(R.drawable.emo24));
        listEmotion.add(new ImageEmotion(R.drawable.emo25));
        listEmotion.add(new ImageEmotion(R.drawable.emo26));
        listEmotion.add(new ImageEmotion(R.drawable.emo27));
        listEmotion.add(new ImageEmotion(R.drawable.emo28));
        listEmotion.add(new ImageEmotion(R.drawable.emo29));
        listEmotion.add(new ImageEmotion(R.drawable.emo30));
    }

    @Override
    public void onMsgFromFragToMain(String strValue) {

    }
}
