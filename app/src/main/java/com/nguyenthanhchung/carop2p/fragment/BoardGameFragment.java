package com.nguyenthanhchung.carop2p.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nguyenthanhchung.carop2p.FragmentCallBacks;
import com.nguyenthanhchung.carop2p.activity.MainGameActivity;
import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.activity.WiFiDirectActivity;
import com.nguyenthanhchung.carop2p.adapter.ImageCellAdapter;
import com.nguyenthanhchung.carop2p.image_resource.ImageProvider;
import com.nguyenthanhchung.carop2p.model.CellBoard;

import java.util.ArrayList;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class BoardGameFragment extends Fragment implements FragmentCallBacks {
    WiFiDirectActivity main;
    Context context = null;
    GridView gvBoard;
    ImageCellAdapter adapter;
    //final int MAX_CELLS = 225;
    ArrayList<CellBoard> listImage;
    MediaPlayer check_click;
    boolean flag = false;

    public static BoardGameFragment newInstance(String strArgs){
        BoardGameFragment fragment = new BoardGameFragment();
        Bundle args = new Bundle();
        args.putString("strArgs", strArgs);
        fragment.setArguments(args);
        return fragment;
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.layout_board_game_fragment,
                null);
        gvBoard = layout.findViewById(R.id.gvBoard);
        check_click = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.pop);
        listImage = ImageProvider.getCELL_IMAGE_LIST();
        adapter = new ImageCellAdapter(main, R.layout.layout_adapter_gv_cell, listImage);
        gvBoard.setAdapter(adapter);
        gvBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listImage.get(position).isFilled() == false){
                    CellBoard m_img = listImage.get(position);
                    m_img.setFilled(true);
                    if(flag){   // x
                        check_click.start();
                        m_img.setIdImage(R.drawable.ic_x);
                        flag = false;
                        main.onMsgFromFragmentToMainGame("GameBoardX", ((Integer)position).toString());
                    }else{  // o
                        check_click.start();
                        m_img.setIdImage(R.drawable.ic_o);
                        flag = true;
                        main.onMsgFromFragmentToMainGame("GameBoardO", ((Integer)position).toString());
                    }
                    adapter.notifyDataSetChanged();
                    main.onMsgFromFragmentToMainGame("GameBoard", ((Integer)position).toString());
                }
            }
        });

        return layout;
    }


    @Override
    public void onMsgFromMainToFrag(String strValue) {

    }
}
