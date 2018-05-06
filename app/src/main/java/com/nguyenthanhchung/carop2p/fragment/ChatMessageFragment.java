package com.nguyenthanhchung.carop2p.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyenthanhchung.carop2p.FragmentCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;

/**
 * Created by Nguyen Thanh Chung on 2018-05-06.
 */

public class ChatMessageFragment extends Fragment implements FragmentCallBacks{
    private WiFiDirectActivity main;
    private Context context;
    TextView txtMessage;
    LinearLayout background = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            context = getActivity();
            main = (WiFiDirectActivity)getActivity();
        }catch (IllegalStateException e){
            throw  new IllegalStateException("MainGame must implements Callbacks");
        }
    }

    public static ChatMessageFragment newInstance(String strArgs){
        ChatMessageFragment fragment = new ChatMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("chat", strArgs);
        fragment.setArguments(bundle);
        return  fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.layout_bubble_chat_fragment,
                null);
        txtMessage = layout.findViewById(R.id.txtMessage);
        background = layout.findViewById(R.id.contentWithBackground);
        return layout;
    }


    public void setMessage(String message){
        if(message != null){
            txtMessage.setText(message);
        }
    }

    public void setBackground(int ID){
        background.setBackgroundResource(ID);
    }

    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }
}
