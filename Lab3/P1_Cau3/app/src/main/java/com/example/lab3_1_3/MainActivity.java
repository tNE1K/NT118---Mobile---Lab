package com.example.lab3_1_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Student> mStudents;
    private RecyclerView mRecyclerStudent;
    private StudentAdapter mStudentAdapter;
    private Button btnThem;
    private Button btnXoa;
    private Button btnSua;
    private Button btnTruyVan;
    private EditText etID;
    private EditText etName;
    private EditText etClass;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHandler(this);
        db.deleteAllStudent();
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnCapNhat);
        btnTruyVan = findViewById(R.id.btnTruyVan);
        etID = findViewById(R.id.etMSSV);
        etName = findViewById(R.id.etName);
        etClass = findViewById(R.id.etClass);
        etID.setText("");
        etName.setText("");
        etClass.setText("");
        mRecyclerStudent = findViewById(R.id.rvStudent);
        mStudents = new ArrayList<>();
        mStudentAdapter = new StudentAdapter(this, mStudents);
        mRecyclerStudent.setAdapter(mStudentAdapter);
        mRecyclerStudent.setLayoutManager(new LinearLayoutManager(this));

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewStudent();
                etID.setText("");
                etName.setText("");
                etClass.setText("");
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                if(etID.getText().toString().equals("") || etID.getText().toString().equals(null)) {
                    db.deleteAllStudent();
                }
                else{
                    for(Student st : db.getAllStudent()) {
                        if (etID.getText().toString().equals(st.getMssv()) ) {
                            deleteStudent(etID.getText().toString());
                            count += 1;
                            break;
                        }
                    }
                    if(count == 0)
                        Toast.makeText(MainActivity.this, "MSSV không hợp lệ", Toast.LENGTH_LONG).show();
                }
                etID.setText("");
                etName.setText("");
                etClass.setText("");

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for(Student st : db.getAllStudent()) {
                    if(etID.getText().toString().equals(st.getMssv())){
                        updateStudent(st);
                        count += 1;
                        break;
                    }
                }
                if(count == 0)
                    Toast.makeText(MainActivity.this, "MSSV không hợp lệ", Toast.LENGTH_LONG).show();
                etID.setText("");
                etName.setText("");
                etClass.setText("");
            }
        });

        btnTruyVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudents.clear();
                mStudentAdapter.notifyDataSetChanged();
                if(etID.getText().toString().equals("") || etID.getText().toString().equals(null)) {
                    for(Student st : db.getAllStudent())
                        mStudents.add(st);
                    mStudentAdapter.notifyDataSetChanged();
                }
                else{
                    int count = 0;
                    for(Student st : db.getAllStudent()) {
                        if (etID.getText().toString().equals(st.getMssv())) {
                            queryStudent(etID.getText().toString());
                            mStudentAdapter.notifyDataSetChanged();
                            count +=1;
                            break;
                        }
                    }
                    if(count == 0)
                        Toast.makeText(MainActivity.this, "MSSV không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                etID.setText("");
                etName.setText("");
                etClass.setText("");
            }
        });

    }
    public void addNewStudent(){
        String id = etID.getText().toString();
        String name = etName.getText().toString();
        String sClass = etClass.getText().toString();
        Student student = new Student(id, name, sClass);
        db.addStudent(student);
    }
    public void deleteStudent(String id){
        db.deleteStudent(id);
    }
    public void updateStudent(Student student){
        String name = etName.getText().toString();
        String sClass = etClass.getText().toString();
        student.setFullName(name);
        student.setMaLop(sClass);
        db.updateStudent(student);
    }
    public void queryStudent(String id){
        mStudents.add(db.getStudent(id));
    }
}