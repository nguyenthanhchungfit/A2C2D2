package com.nguyenthanhchung.carop2p.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nguyenthanhchung.carop2p.callback_interface.FragmentCallBacks;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.KhoiDongGameActivity;

public class MainMenuFragment extends Fragment implements FragmentCallBacks {
    Button btnGuide;
    Button btnInfo;
    Button btnSetting;
    Button btnCancel;
    Button btnBest;
    KhoiDongGameActivity main;
    Context context;
    public static MainMenuFragment newInstance(String strArgs) {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MainMenu", strArgs);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (KhoiDongGameActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("KhoiDong must implement Callback");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.layout_menu_fragment, null);
        btnBest = layout.findViewById(R.id.btnBest);
        btnBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("MainMenu","best");

            }
        });
        btnGuide = layout.findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("MainMenu","guide");
            }
        });
        btnInfo = layout.findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("MainMenu","info");
            }
        });
        btnSetting = layout.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("MainMenu","setting");
            }
        });
        btnCancel = layout.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("MainMenu","close");
            }
        });
        //Event
        return layout;
    }
    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }
}
