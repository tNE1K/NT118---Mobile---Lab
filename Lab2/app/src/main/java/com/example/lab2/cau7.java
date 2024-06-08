package com.example.lab2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cau7 extends AppCompatActivity {

    EditText employeeID;
    EditText employeeName;
    CheckBox cbManager;
    Button btnAddEmployee;
    RecyclerView rvShow;
    Employee_c4 employee;
    ArrayList<Employee_c4> employees;
    EmployeeAdapter_c7 e_adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvShow = findViewById(R.id.rv_Employee);
        employeeID = findViewById(R.id.txtID);
        employeeName = findViewById(R.id.txtName);
        cbManager = findViewById(R.id.checkBox);
        btnAddEmployee = findViewById(R.id.btnAdd);
        employees = new ArrayList<>();
        e_adapter = new EmployeeAdapter_c7(this, employees);
        rvShow.setAdapter(e_adapter);
        rvShow.setLayoutManager(new LinearLayoutManager(this));

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee();
            }
        });


    }
    public void addNewEmployee(){
        String id = employeeID.getText().toString();
        String name = employeeName.getText().toString();
        if(cbManager.isChecked() == true){
            employee = new Employee_c4(id, name, cbManager.isChecked());
        }
        else{
            employee = new Employee_c4(id, name, cbManager.isChecked());
        }
        employees.add(employee);
        e_adapter.notifyDataSetChanged();
    }
}