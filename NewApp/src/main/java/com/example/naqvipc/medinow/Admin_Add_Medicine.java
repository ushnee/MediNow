package com.example.naqvipc.medinow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Admin_Add_Medicine extends AppCompatActivity {

    private Button btnAddMedicine;
    private ImageButton imgMedi;
    private EditText inputMediName;
    private EditText inputMediDesc;
    private EditText inputMediPrice;
    private static final int Gallerypick = 1;
    private Uri imgUri;
    private String MediRandomKey, downloadImgUrl;

    private ProgressDialog loadingBar;

    private StorageReference MediImgRef;
    private DatabaseReference MediRef;

    private String Name, Description, Price, SaveCurrentDate, SaveCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__medicine);

        MediImgRef = FirebaseStorage.getInstance().getReference().child("Medicine Images");
        MediRef = FirebaseDatabase.getInstance().getReference().child("Medicines");

        btnAddMedicine = (Button) findViewById(R.id.btnAddMedi);
        imgMedi = (ImageButton) findViewById(R.id.imageButtonMedi);
        inputMediName = (EditText) findViewById(R.id.eTxtMediName);
        inputMediDesc = (EditText) findViewById(R.id.eTxtMediDescription);
        inputMediPrice = (EditText) findViewById(R.id.eTxtMediPrice);

        loadingBar = new ProgressDialog(this);

        imgMedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateMediData();
            }
        });

    }


    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallerypick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallerypick && resultCode==RESULT_OK && data!= null){
            imgUri = data.getData();
            imgMedi.setImageURI(imgUri);
        }
    }

    private void ValidateMediData(){
        Name=inputMediName.getText().toString();
        Description=inputMediDesc.getText().toString();
        Price=inputMediPrice.getText().toString();

        if(imgUri == null){
            Toast.makeText(this, "Medicine Image is Mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description)){
            Toast.makeText(this, "Description is Mandatory..", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price)){
            Toast.makeText(this, "Enter Price in Rs", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Name)){
            Toast.makeText(this, "Enter the Name of Medicine..", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreMediInformation();
        }

    }

    private void StoreMediInformation() {

        loadingBar.setTitle("Adding new Medicine");
        loadingBar.setMessage("Please wait while we are Adding....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("mm dd,yyyy");
        SaveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = currentTime.format(calendar.getTime());

        MediRandomKey = SaveCurrentDate+SaveCurrentTime;

        final StorageReference filePath = MediImgRef.child(imgUri.getLastPathSegment()+MediRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(imgUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(Admin_Add_Medicine.this, "Error: " +message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Admin_Add_Medicine.this, "Image Added Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImgUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){

                            downloadImgUrl = task.getResult().toString();

                            Toast.makeText(Admin_Add_Medicine.this, "Got the Medicine Image Url Successfully...", Toast.LENGTH_SHORT).show();
                            SaveMediInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveMediInfoToDatabase() {
        HashMap<String, Object> MediMap=new HashMap<>();
        MediMap.put("mdid", MediRandomKey);
        MediMap.put("date", SaveCurrentDate);
        MediMap.put("time", SaveCurrentTime);
        MediMap.put("description", Description);
        MediMap.put("image", downloadImgUrl);
        MediMap.put("price", Price);
        MediMap.put("mname", Name);

        MediRef.child(MediRandomKey).updateChildren(MediMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){

                           loadingBar.dismiss();
                           Toast.makeText(Admin_Add_Medicine.this, "Medicine is Added Successfully...", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           loadingBar.dismiss();
                           String message = task.getException().toString();
                           Toast.makeText(Admin_Add_Medicine.this, "Error: " +message, Toast.LENGTH_SHORT).show();

                       }
                    }
                });
    }
}
