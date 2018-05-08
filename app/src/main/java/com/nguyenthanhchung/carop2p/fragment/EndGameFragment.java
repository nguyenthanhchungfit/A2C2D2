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

import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.callback_interface.FragmentCallBacks;

public class EndGameFragment extends Fragment implements FragmentCallBacks {
    WiFiDirectActivity main;
    Context context = null;
    TextView txtResult;
    Button btnOkEndGame;

    public static EndGameFragment newInstance(String strArgs){
        EndGameFragment fragment = new EndGameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("EndGame", strArgs);
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
                R.layout.layout_fragment_endgame, null);
        txtResult = layout.findViewById(R.id.txtResult);
        btnOkEndGame = layout.findViewById(R.id.btnOkEndGame);
        btnOkEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.onMsgFromFragmentToMainGame("EndGame","OK");
            }
        });
        return layout;
    }

    @Override
    public void onMsgFromMainToFrag(String strValue) {
        txtResult.setText(strValue);
    }
}
