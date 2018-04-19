package com.nguyenthanhchung.carop2p.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.model.ImageEmotion;

import java.util.List;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class ImageEmotionAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ImageEmotion> emotionList;

    public ImageEmotionAdapter(Context context, int layout, List<ImageEmotion> emotionList) {
        this.context = context;
        this.layout = layout;
        this.emotionList = emotionList;
    }


    @Override
    public int getCount() {
        return emotionList.size();
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
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.img =  (ImageView)convertView.findViewById(R.id.imgEmotion);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ImageEmotion emotion = emotionList.get(position);
        holder.img.setImageResource(emotion.getIdImage());
        return convertView;
    }
}
