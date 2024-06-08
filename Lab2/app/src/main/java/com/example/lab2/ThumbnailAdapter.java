package com.example.lab2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    Activity context;
    int layout;
    ArrayList<Thumbnail> list;

    public ThumbnailAdapter(Activity context, int layout, ArrayList<Thumbnail> list) {
        super(context, layout, list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, null, false);
        }
        Thumbnail thumb = list.get(position);

        TextView tv_Selected = (TextView) convertView.findViewById(R.id.tv_selectedThumbnail);
        if(thumb!=null)
        {
            tv_Selected.setText(thumb.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, null, false);
        }
        Thumbnail thumb = list.get(position);

        TextView tv_thumbnailName = (TextView) convertView.findViewById(R.id.tv_thumbnailName);
        ImageView iv_thumbImg = (ImageView) convertView.findViewById(R.id.iv_thumbImg);
        if(thumb!=null)
        {
            tv_thumbnailName.setText(thumb.getName());
            iv_thumbImg.setImageResource(thumb.getImg());
        }
        return convertView;
    }
}
