package com.nguyenthanhchung.carop2p.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.model.CellBoard;

import java.util.List;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class ImageCellAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CellBoard> imageList;

    public ImageCellAdapter(Context context, int layout, List<CellBoard> imageList){
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.imgImage = (ImageView) convertView.findViewById(R.id.imgCellBoard);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        CellBoard img = imageList.get(position);
        holder.imgImage.setImageResource(img.getIdImage());
        return convertView;
    }
}
