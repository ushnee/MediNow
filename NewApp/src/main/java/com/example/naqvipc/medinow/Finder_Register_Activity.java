package com.example.naqvipc.medinow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Finder_Register_Activity extends AppCompatActivity implements View.OnClickListener{
    Window window;

    String codeSent;

    private Button buttonVerifySubmit;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder__register_);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }



        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //Profile Activity here
            finish();
            startActivity(new Intent(getApplicationContext(), Finder_Activity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonVerifySubmit = (Button) findViewById(R.id.buttonVerifySubmit);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);



        buttonVerifySubmit.setOnClickListener(this);

    }



    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword .getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }
        if(password.length()<=5){
            //password is empty
            Toast.makeText(this, "Password should more than 6", Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }

        //if OKAY
        //Show ProgressBar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            // we will start the profile activity here
                            //lets display toast only
                            progressDialog.cancel();
                            Toast.makeText(Finder_Register_Activity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(), Finder_Activity.class));

                        }else{
                            progressDialog.cancel();
                            Toast.makeText(Finder_Register_Activity.this, "Not Registered, Try Again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonVerifySubmit){
            registerUser();
        }


    }
}
