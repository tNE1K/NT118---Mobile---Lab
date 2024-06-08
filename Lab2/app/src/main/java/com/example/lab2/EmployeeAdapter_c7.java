package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class EmployeeAdapter_c7 extends RecyclerView.Adapter<EmployeeAdapter_c7.ViewHolder>{
    Context  mContext;
    ArrayList<Employee_c4> list;

    public EmployeeAdapter_c7(Context mContext, ArrayList<Employee_c4> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View employeeView =  inflater.inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(employeeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Employee_c4 employee = list.get(position);
        holder.tv_employeeName.setText(employee.getFullName());
        if(employee.isManager() == true)
        {
            holder.tv_employeePosition.setVisibility(View.GONE);
            holder.im_imgManager.setVisibility(View.VISIBLE);
        }
        else{
            holder.tv_employeePosition.setVisibility(View.VISIBLE);
            holder.im_imgManager.setVisibility(View.GONE);
            holder.tv_employeePosition.setText("Staff");
        }

        if(position % 2 == 0)
        {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
        else {
            holder.llParent.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_employeeName;
        TextView tv_employeePosition;
        ImageView im_imgManager;
        LinearLayout llParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_employeeName =itemView.findViewById(R.id.item_employee_name);
            tv_employeePosition = itemView.findViewById(R.id.item_employee_position);
            im_imgManager = itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = itemView.findViewById(R.id.item_employee_ll_parent);
        }
    }
}
