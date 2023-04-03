package com.example.naqvipc.medinow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Admin_Login_Activity extends AppCompatActivity {


    private EditText AdminUserName;
    private EditText AdminPassword;
    private ImageButton LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login_);

        AdminUserName = (EditText) findViewById(R.id.editTextUser);
        AdminPassword = (EditText) findViewById(R.id.editTextPass);
        LoginBtn = (ImageButton) findViewById(R.id.adminButtonSignIn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(AdminUserName.getText().toString(), AdminPassword.getText().toString());
            }
        });
    }

    private void validate(String AdminuserName, String AdminPassword){

        if((AdminuserName.equals("AdminMedi")) && (AdminPassword.equals("medinow"))){
            Toast.makeText(this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Admin_Login_Activity.this,Admin_Activity.class );
            startActivity(intent);
        }else {
            Toast.makeText(this, "Username/Password is INCORRECT", Toast.LENGTH_SHORT).show();
        }
    }
}
