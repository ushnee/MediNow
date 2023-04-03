package com.example.naqvipc.medinow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category_Select_Activity extends AppCompatActivity {
    private Button buttonF;
    private Button buttonV;
    private Button buttonA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__select);

        buttonF = (Button) findViewById(R.id.buttonF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ActF();
            }
        });

        buttonV = (Button) findViewById(R.id.buttonV);
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ActV();
            }
        });

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ActA();
            }
        });

    }
    public void open_ActF(){
        Intent intentf = new Intent(this,Finder_Login_Activity.class);
        startActivity(intentf);
    }

    public void open_ActV(){
        Intent intentv = new Intent(this,Vendor_Activity.class);
        startActivity(intentv);
    }

    public void open_ActA(){
        Intent intenta = new Intent(this,Admin_Login_Activity.class);
        startActivity(intenta);
    }
}
