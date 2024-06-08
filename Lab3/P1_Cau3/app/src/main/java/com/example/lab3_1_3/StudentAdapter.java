package com.example.lab3_1_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context mContext;
    private List<Student> mStudent;
    public StudentAdapter(Context mContext, List<Student> mStudent){
        this.mContext = mContext;
        this.mStudent = mStudent;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View studentView = inflater.inflate(R.layout.item_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        Student student = mStudent.get(position);
        holder.student.setText(student.getMssv() + " - " + student.getFullName() + " - " + student.getMaLop());
    }
    @Override
    public int getItemCount() {
        return mStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView student;
        public ViewHolder(View view){
            super(view);
            student = view.findViewById(R.id.tvStudent);
        }
    }
}
