package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class cau5 extends AppCompatActivity {

    Spinner hSpinner;
    ThumbnailAdapter t_adapter;
    Dish dish;
    ArrayList<Thumbnail> thumbArr;
    GridView gv_Dish;
    EditText et_DishName;
    CheckBox isPromo;
    Button btn_AddDish;
    ArrayList<Dish> dishes;
    DishAdapter d_adapter;
    int imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_DishName = findViewById(R.id.et_DishName);
        isPromo = findViewById(R.id.cb_isPromo);
        gv_Dish = findViewById(R.id.gv_Dish);
        btn_AddDish = findViewById(R.id.btnAddDish);
        dish = new Dish();
        thumbArr = new ArrayList<>();
        thumbArr.add(Thumbnail.Thumbnail1);
        thumbArr.add(Thumbnail.Thumbnail2);
        thumbArr.add(Thumbnail.Thumbnail3);
        thumbArr.add(Thumbnail.Thumbnail4);
        hSpinner = findViewById(R.id.hSpinner);
        t_adapter = new ThumbnailAdapter(this, R.layout.item_selected_thumbnail, thumbArr);
        hSpinner.setAdapter(t_adapter);
        dishes = new ArrayList<Dish>();
        d_adapter = new DishAdapter(this, R.layout.item_dish, dishes);
        gv_Dish.setAdapter(d_adapter);
        hSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSelected = thumbArr.get(position).getImg();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageSelected = thumbArr.get(0).getImg();
            }
        });
        btn_AddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDish();
            }
        });

    }

    public void addNewDish(){
        String name = et_DishName.getText().toString();
        if(isPromo.isChecked() == true){
            dish = new Dish(name, imageSelected,isPromo.isChecked());
        }
        else{
            dish = new Dish(name, imageSelected,isPromo.isChecked());
        }
        dishes.add(dish);
        d_adapter.notifyDataSetChanged();
    }
}