package com.example.lab2;

import android.os.Bundle;
import android.text.EmojiConsistency;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class cau4 extends AppCompatActivity {

    EmployeeAdapter adapter;
    EditText etID;
    EditText etName;
    CheckBox isManager;
    Button btnAdd;
    ListView lv_nhanvien;
    ArrayList employees;
    Employee_c4 employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etID = findViewById(R.id.txtID);
        etName = findViewById(R.id.txtName);
        isManager = findViewById(R.id.checkBox);
        btnAdd = findViewById(R.id.btnAdd);
        lv_nhanvien = findViewById(R.id.lv_NhanVien);
        employees = new ArrayList<Employee_c4>();
        adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lv_nhanvien.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee();
            }
        });

    }

    public void addNewEmployee(){
        String id = etID.getText().toString();
        String name = etName.getText().toString();
        if(isManager.isChecked() == true){
            employee = new Employee_c4(id, name, isManager.isChecked());
        }
        else{
            employee = new Employee_c4(id, name, isManager.isChecked());
        }
        employees.add(employee);
        adapter.notifyDataSetChanged();
    }
}