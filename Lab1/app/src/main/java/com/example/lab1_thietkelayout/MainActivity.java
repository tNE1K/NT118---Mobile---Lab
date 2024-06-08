package com.example.lab1_thietkelayout;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear_layout2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        createNameContainer();
//        createAddressContainer();
//        createParentContainer();
//        setContentView(llParentContainer);
    }
    private LinearLayout llNameContainer, llAddressContainer, llParentContainer;

    private void createNameContainer(){
        llNameContainer = new LinearLayout(this);
        llNameContainer.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        llNameContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvName = new TextView(this);
        tvName.setText("Name: ");
        llNameContainer.addView(tvName);

        TextView tvNameValue = new TextView(this);
        tvNameValue.setText("John Doe");
        llNameContainer.addView(tvNameValue);
    }
    private void createAddressContainer(){
        llAddressContainer = new LinearLayout(this);
        llAddressContainer.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        llAddressContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvAdrress = new TextView(this);
        tvAdrress.setText("Address:");
        llAddressContainer.addView(tvAdrress);

        TextView tvAddressValue = new TextView(this);
        tvAddressValue.setText("911 Hollywood Blvd");
        llAddressContainer.addView(tvAddressValue);
    }
    private void createParentContainer(){
        llParentContainer = new LinearLayout(this);
        llParentContainer.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        llParentContainer.setOrientation(LinearLayout.VERTICAL);
        llParentContainer.addView(llNameContainer);
        llParentContainer.addView(llAddressContainer);
    }
}