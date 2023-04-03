package com.example.naqvipc.medinow;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Finder_Activity extends AppCompatActivity implements View.OnClickListener {
    Window window;


    private FirebaseAuth firebaseAuth;
    private ImageButton buttonN;
    private TextView textViewEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder_);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() ==null){
            finish();
            startActivity(new Intent(this, Finder_Login_Activity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonN = (ImageButton) findViewById(R.id.imageButtonNest);
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ActMainFinder_Opt();
            }
        });
        textViewEmail = (TextView) findViewById(R.id.textViewEmailAddress);
        textViewEmail.setText(user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
    }

    public void open_ActMainFinder_Opt(){
        Intent intentn = new Intent(this,MainFinder_Opt_Activity.class);
        startActivity(intentn);
    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){
            Toast.makeText(Finder_Activity.this, "LOGOUT Successfully", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Finder_Login_Activity.class));
        }

    }
}
