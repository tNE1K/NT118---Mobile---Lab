package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Hero> mHeros;

    public HeroAdapter(Context mContext, ArrayList<Hero> mHeros) {
        this.mContext = mContext;
        this.mHeros = mHeros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.item_hero, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hero hero = mHeros.get(position);
        Glide.with(mContext).load(hero.getmImage()).into(holder.mImageHero);
        holder.mTextName.setText(hero.getmName());
    }

    @Override
    public int getItemCount() {
        return mHeros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageHero;
        private TextView mTextName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImageHero = itemView.findViewById(R.id.image_hero);
            mTextName = itemView.findViewById(R.id.text_name);
        }
    }
}
