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
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.adapter.ImageEmotionAdapter;
import com.nguyenthanhchung.carop2p.callback_interface.FragmentImageCallback;
import com.nguyenthanhchung.carop2p.image_resource.ImageProvider;
import com.nguyenthanhchung.carop2p.model.ImageEmotion;

import java.util.ArrayList;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class BoardEmotionFragment extends Fragment implements FragmentCallBacks{
    WiFiDirectActivity main;
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
            main = (WiFiDirectActivity)getActivity();
        }catch (IllegalStateException e){
            throw  new IllegalStateException("Main game must implement Callback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(
                R.layout.layout_board_emotion_fragment, null);
        btnCloseEmotionBoard = layout.findViewById(R.id.btnCloseEmotionBoard);
        gvBoardEmotion = layout.findViewById(R.id.gvBoardEmotion);
        listEmotion = ImageProvider.getEMOTION_IMAGE_LIST();
        adapter = new ImageEmotionAdapter(main, R.layout.layout_adapter_gv_emotion, listEmotion);
        gvBoardEmotion.setAdapter(adapter);

        //Event
        gvBoardEmotion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idImage = listEmotion.get(position).getIdImage();
                String pos = ((Integer)idImage).toString();
                main.onMsgFromFragmentToMainGame("GameBoardImage", pos);
            }
        });

        btnCloseEmotionBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragmentToMainGame("EmotionBoardClose", "close");
            }
        });
        return layout;
    }

    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }
}
