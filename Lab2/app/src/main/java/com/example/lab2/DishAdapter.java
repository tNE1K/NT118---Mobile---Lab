package com.example.lab2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DishAdapter extends ArrayAdapter<Dish> {
    Activity context;
    int layout;
    ArrayList<Dish> list;

    public DishAdapter(Activity context, int layout, ArrayList<Dish> list) {
        super(context, layout, list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dish, null, false);
        }
        Dish dish = list.get(position);

        //Get View
        ImageView iv_Dish = (ImageView) convertView.findViewById(R.id.item_dish_iv);
        TextView tv_DishName = (TextView) convertView.findViewById(R.id.tv_DishName);
        tv_DishName.setSelected(true);
        ImageView iv_isPromo = (ImageView) convertView.findViewById(R.id.iv_isPromo);

        if(dish.getName()!=null)
        {
            tv_DishName.setText(dish.getName());
        }
        else tv_DishName.setText("");

        if(dish.getName()!=null)
        {
            iv_Dish.setImageResource(dish.getImg());
            iv_Dish.setVisibility(View.VISIBLE);
        }
        if(dish.isPromotion() == true){
            iv_isPromo.setVisibility(View.VISIBLE);
        }
        else iv_isPromo.setVisibility(View.GONE);

        return convertView;
    }
}
