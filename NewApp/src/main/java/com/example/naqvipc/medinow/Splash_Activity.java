package com.example.naqvipc.medinow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        Thread td = new Thread(){


            public void run()
            {
                try {
                    sleep(3000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }finally {
                    Intent it = new Intent(Splash_Activity.this, Category_Select_Activity.class);
                    startActivity(it);
                }
            }

        };
        td.start();
    }
}
