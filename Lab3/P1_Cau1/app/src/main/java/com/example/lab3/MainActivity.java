package com.example.lab3;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private Cursor cursor;
    private List<String> users;
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

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();
        for(int i = 0; i < 10; i++){
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }
        users = getData();
        showData();

    }
    @SuppressLint("Range")
    private List<String> getData(){
        List<String> users = new ArrayList<>();
        cursor = dbAdapter.getAllUsers();
        while (cursor.moveToNext()){
            users.add(cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_NAME)));
        }
        return users;
    }
    private void showData(){
        ListView lvUser = (ListView) findViewById(R.id.lv_user);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item_user, users);
        lvUser.setAdapter(userAdapter);
    }
}