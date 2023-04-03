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
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Finder_Login_Activity extends AppCompatActivity implements View.OnClickListener {
    Window window;

    private ImageButton buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder__login_);

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

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (ImageButton) findViewById(R.id.imageButtonSignIn);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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
        //if OKAY
        //Show ProgressBar
        progressDialog.setMessage("Login");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //Start the profile Activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Finder_Activity.class));
                        }else {
                            Toast.makeText(Finder_Login_Activity.this, "Email/Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }
        if(view == buttonRegister){
            finish();
            startActivity(new Intent(this, Finder_Register_Activity.class));
        }

    }
}
