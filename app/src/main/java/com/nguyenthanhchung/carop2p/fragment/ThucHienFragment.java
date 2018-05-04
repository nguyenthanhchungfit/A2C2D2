package com.nguyenthanhchung.carop2p.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.adapter.SinhVienAdapter;
import com.nguyenthanhchung.carop2p.model.SinhVien;

import java.util.ArrayList;

public class ThucHienFragment extends ListFragment {
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setListSinhVien();
        adapter = new SinhVienAdapter(getActivity(), R.layout.layout_adapter_student, arraySinhVien);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.layout_thuc_hien_fragment, container, false);
    }


    private void setListSinhVien() {
        arraySinhVien = new ArrayList<SinhVien>();
        arraySinhVien.add(new SinhVien("Lê Tuấn Anh", 1512003, R.drawable.image_1512003));
        arraySinhVien.add(new SinhVien("Nguyễn Trần Tuấn Anh", 1512006, R.drawable.image_1512006));
        arraySinhVien.add(new SinhVien("Nguyễn Thành Chung", 1512042, R.drawable.image_1512042));
        arraySinhVien.add(new SinhVien("Trần Nhựt Cường", 1512055, R.drawable.image_1512055));
        arraySinhVien.add(new SinhVien("Nguyễn Hữu Danh", 1512058, R.drawable.image_1512058));
    }
}