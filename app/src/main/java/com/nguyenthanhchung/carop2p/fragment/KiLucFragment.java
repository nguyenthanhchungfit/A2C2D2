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
import android.widget.TextView;

import com.nguyenthanhchung.carop2p.FragmentCallBacks;

import android.widget.TextView;

import com.nguyenthanhchung.carop2p.callback_interface.FragmentCallBacks;

import com.nguyenthanhchung.carop2p.Helper.MyFormatHelper;
import com.nguyenthanhchung.carop2p.Helper.MySharedPreferences;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.KhoiDongGameActivity;

/**
 * Created by Tran Nhut Cuong on 06/05/2018.
 */

public class KiLucFragment extends Fragment implements FragmentCallBacks {
    KhoiDongGameActivity main;
    Context context;

    Button btnQuayVe;
    TextView NuocDiNgan;
    TextView ThoiGianNgan;

    public static SettingFragment newInstance(String strArgs) {
        SettingFragment fragment = new SettingFragment();
=======
    TextView NuocDiNgan;
    TextView ThoiGianNgan;

    public static KiLucFragment newInstance(String strArgs) {
        KiLucFragment fragment = new KiLucFragment();
>>>>>>> 319b80a22c5243738a0fc070d7cfebf377f69421
        Bundle bundle = new Bundle();
        bundle.putString("Kiluc", strArgs);
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
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(
                R.layout.layout_kiluc_fragment, null);
        NuocDiNgan = layout.findViewById(R.id.textViewNuocDiNgan);
        ThoiGianNgan = layout.findViewById(R.id.textViewThoiGianNgan);
<<<<<<< HEAD
        btnQuayVe = layout.findViewById(R.id.btnQuayVe);
=======
>>>>>>> 319b80a22c5243738a0fc070d7cfebf377f69421

        NuocDiNgan.setText(Integer.toString(MySharedPreferences.getIntergerSharedPreferences(main, "Kiluc", "nuocdingan")));
        ThoiGianNgan.setText(MyFormatHelper.fotmatTimeRecord(MySharedPreferences.getIntergerSharedPreferences(main, "Kiluc", "thoigianngan")));

<<<<<<< HEAD
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("KilucFragmentClose", "close");
            }
        });
=======

>>>>>>> 319b80a22c5243738a0fc070d7cfebf377f69421

        return layout;
    }

    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }
}
