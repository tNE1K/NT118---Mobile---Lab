package com.example.lab6_c4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.PagerVH> {

    private final int[] colors = new int[]{
            android.R.color.holo_green_light,
            android.R.color.holo_red_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_purple
    };

    @NonNull
    @Override
    public PagerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_page, parent, false);
        PagerVH viewHolder = new PagerVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PagerVH holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    class PagerVH extends RecyclerView.ViewHolder {

        TextView tvTitle;
        View container;

        PagerVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            container = itemView.findViewById(R.id.container);
        }

        void bind(int position) {
            tvTitle.setText("item " + position);
            container.setBackgroundResource(colors[position]);
        }
    }
}
