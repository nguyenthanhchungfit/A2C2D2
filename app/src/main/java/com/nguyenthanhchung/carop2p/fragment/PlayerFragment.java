package com.nguyenthanhchung.carop2p.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyenthanhchung.carop2p.FragmentCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.callback_interface.FragmentImageCallback;

/**
 * Created by Nguyen Thanh Chung on 2018-04-20.
 */

public class PlayerFragment extends Fragment implements FragmentCallBacks, FragmentImageCallback{
    WiFiDirectActivity main;
    Context context;
    ImageView imgPlayerAVT, imgPlayerSign;
    TextView txtPlayerName;

    public static PlayerFragment newInstance(String args){
        PlayerFragment fragment = new PlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Player", args);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            context = getActivity();
            main = (WiFiDirectActivity)getActivity();
        }catch (IllegalStateException e){
            throw new IllegalStateException("Main game must implement Callback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate
                (R.layout.player_fragment, null);

        imgPlayerAVT = layout.findViewById(R.id.imgPlayerAVT);
        imgPlayerSign = layout.findViewById(R.id.imgPlayerSign);
        txtPlayerName = layout.findViewById(R.id.txtPlayerName);

        return layout;
    }

    public void setImgPlayerAVT(int idImage){
        if(imgPlayerAVT != null){
            imgPlayerAVT.setImageResource(idImage);
        }
    }

    public void setImgPlayerSign(int idImage){
        if(imgPlayerSign != null){
            imgPlayerSign.setImageResource(idImage);
        }
    }

    public void setPlayName(String name){
        if(txtPlayerName != null){
            txtPlayerName.setText(name);
        }
    }

    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }

    @Override
    public void onImageFromMainToFrag(String obj, int idImage) {
        if(obj.equals("avatar")){
            setImgPlayerAVT(idImage);
            changeAVTPlayer();

        }else if(obj.equals("sign")){
            setImgPlayerSign(idImage);
        }
    }

    private void changeAVTPlayer() {
        new CountDownTimer(5000, 5000){
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                setImgPlayerAVT(R.drawable.image_player1);
            }
        }.start();
    }
}