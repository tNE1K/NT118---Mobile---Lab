package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class cau3 extends AppCompatActivity {

    EditText maNV;
    ArrayList employees = new ArrayList<String>();
    EditText tenNV;
    RadioGroup rgType;
    RadioButton rdChinhThuc;
    Button btnNhap;
    ListView lv_NhanVien;
    Employee employee;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        maNV = findViewById(R.id.txtbox_MaNV);
        tenNV = findViewById(R.id.txtbox_name);
        rgType = findViewById(R.id.rgButton);
        rdChinhThuc = findViewById(R.id.fulltime);
        btnNhap = findViewById(R.id.btnNhap);
        lv_NhanVien = findViewById(R.id.lv_person);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employees);
        lv_NhanVien.setAdapter(adapter);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee();
            }
        });

    }
    public void addNewEmployee(){
        int radID = rgType.getCheckedRadioButtonId();
        String id = maNV.getText().toString();
        String name = tenNV.getText().toString();
        if(radID == R.id.fulltime){
            employee = new EmployeeFulltime(id, name);
        }
        else{
            employee = new EmployeeParttime(id, name);
        }
        employees.add(employee.ToString());
        adapter.notifyDataSetChanged();
    }
}