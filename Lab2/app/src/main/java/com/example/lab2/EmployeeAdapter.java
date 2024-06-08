package com.example.lab2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee_c4> {
    Activity context;
    int layout;
    ArrayList<Employee_c4> list;

    public EmployeeAdapter(Activity context, int layout, ArrayList<Employee_c4> list) {
        super(context, layout, list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, null, false);
        }
        Employee_c4 employeeC4 = list.get(position);

        //Get view
        TextView tvFullName = (TextView) convertView.findViewById(R.id.item_employee_name);
        TextView tvPosition = (TextView) convertView.findViewById(R.id.item_employee_position);
        ImageView ivManager = (ImageView) convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent = (LinearLayout) convertView.findViewById(R.id.item_employee_ll_parent);

        if(employeeC4.getFullName() != null){
            tvFullName.setText(employeeC4.getFullName());
        }
        else tvFullName.setText("");

        if(employeeC4.isManager() == true){
            ivManager.setVisibility(View.VISIBLE);
            tvPosition.setVisibility(View.GONE);
        }
        else {
            ivManager.setVisibility(View.GONE);
            tvPosition.setVisibility(View.VISIBLE);
            tvPosition.setText("Staff");
        }
        if(position % 2 == 0)
        {
            llParent.setBackgroundResource(R.color.light_blue);
        }
        else {
            llParent.setBackgroundResource(R.color.white);
        }
        return convertView;
    }
}
