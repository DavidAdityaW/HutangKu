package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage;
    Button btnAddNewHutang;

    DatabaseReference reference;
    RecyclerView ourhutang;
    ArrayList<Hutang> list;
    HutangAdapter hutangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);

        btnAddNewHutang = findViewById(R.id.btnAddNewHutang);

        // Import font
//        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
//        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        // Customize font
//        titlepage.setTypeface(MMedium);
//        subtitlepage.setTypeface(MLight);
//        endpage.setTypeface(MLight);

//        btnAddNewHutang.setTypeface(MLight);

        // Button AddNewHutang diklik
        btnAddNewHutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,NewHutangAct.class);
                startActivity(a);
                finish();
            }
        });

        // Working with data
        ourhutang = findViewById(R.id.ourhutang);
        ourhutang.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Hutang>();

        // Get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("HutangApp");
        hutangAdapter = new HutangAdapter( MainActivity.this, list);
        ourhutang.setAdapter(hutangAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Set code to retrive data and replace layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Hutang p = dataSnapshot1.getValue(Hutang.class);
                        list.add(p);
                    }
                    hutangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Set code to show an error
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(MainActivity.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
