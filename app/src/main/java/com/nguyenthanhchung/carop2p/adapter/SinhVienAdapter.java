package com.nguyenthanhchung.carop2p.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.model.SinhVien;

import java.util.List;

/**
 * Created by Heo on 06/04/2018.
 */

public class SinhVienAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<SinhVien> listSinhVien;

    public SinhVienAdapter(Context context, int layout, List<SinhVien> listSinhVien)
    {
        this.context = context;
        this.layout = layout;
        this.listSinhVien = listSinhVien;
    }


    @Override
    public int getCount() {
        return listSinhVien.size(); // luu y
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            view = inflater.inflate(layout, null);
        }
        // anh xa
        TextView tvTen = (TextView) view.findViewById(R.id.textViewTen);
        TextView tvMSSV = (TextView) view.findViewById(R.id.textViewMSSV);
        ImageView imgHinh = (ImageView) view.findViewById(R.id.imgHinh);
        // gan gia tri
        SinhVien sinhVien = listSinhVien.get(i);
        tvTen.setText(sinhVien.getHoTen());
        tvMSSV.setText(sinhVien.getMSSV() + "");
        imgHinh.setImageResource(sinhVien.getHinhAnh());
        return view;
    }
}
