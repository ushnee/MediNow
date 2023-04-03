package com.example.naqvipc.medinow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.R.attr.id;


public class FindMed_Activity extends AppCompatActivity {
    ListView listView;

    Context context;
    TextView totalamnt,textView2,textView3,textView4 , textView5 , textView6,textView7,textView8;

    Button btn;
    ArrayList<Items > list;
    ItemListAdapter itemListadapter;
    //ArrayAdapter<String> adapter;
    Items items;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_med_);
        listView = (ListView) findViewById(R.id.listview1);
        list=new ArrayList<>();
        // context=




        myRef= FirebaseDatabase.getInstance().getReference().child("products");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    //Toast.makeText(getApplicationContext(),ds.getValue().toString(),Toast.LENGTH_LONG).show();


                    items = ds.getValue(Items.class);

                    list.add(items);
                    itemListadapter = new ItemListAdapter(list, getApplicationContext());
                    listView.setAdapter(itemListadapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            ;})
    ;}
}
