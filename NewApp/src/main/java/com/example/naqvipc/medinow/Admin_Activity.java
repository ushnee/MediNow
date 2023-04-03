package com.example.naqvipc.medinow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_Activity extends AppCompatActivity {

    private Button AddMedicine;
    private Button AddVendor;
    //private Button RecentOrder;
    private Button LogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);

        LogoutButton = (Button)findViewById(R.id.buttonLogout);
        AddMedicine = (Button)findViewById(R.id.btnAddMed);
        AddVendor = (Button)findViewById(R.id.btnAddVendor);

        AddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,Admin_Add_Medicine.class);
                startActivity(intent);
            }
        });

        AddVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,Vendor_Activity.class);
                startActivity(intent);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin_Activity.this, "LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin_Activity.this,Admin_Login_Activity.class);
                startActivity(intent);
            }
        });

    }
}
