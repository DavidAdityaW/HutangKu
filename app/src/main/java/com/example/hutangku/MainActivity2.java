package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class MainActivity2 extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage;
    Button btnAddNewPiutang;

    DatabaseReference reference;
    RecyclerView ourpiutang;
    ArrayList<Piutang> listpiutang;
    PiutangAdapter piutangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);

        btnAddNewPiutang = findViewById(R.id.btnAddNewPiutang);

        // Import font
//        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
//        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        // Customize font
//        titlepage.setTypeface(MMedium);
//        subtitlepage.setTypeface(MLight);
//        endpage.setTypeface(MLight);

//        btnAddNewPiutang.setTypeface(MLight);

        // Button AddNewPiutang diklik
        btnAddNewPiutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity2.this,NewPiutangAct.class);
                startActivity(a);
                finish();
            }
        });

        // Working with data
        ourpiutang = findViewById(R.id.ourpiutang);
        ourpiutang.setLayoutManager(new LinearLayoutManager(this));
        listpiutang = new ArrayList<Piutang>();

        // Get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("PiutangApp");
        piutangAdapter = new PiutangAdapter( MainActivity2.this, listpiutang);
        ourpiutang.setAdapter(piutangAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Set code to retrive data and replace layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Piutang p = dataSnapshot1.getValue(Piutang.class);
                    listpiutang.add(p);
                }
                piutangAdapter.notifyDataSetChanged();
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
        Intent a = new Intent(MainActivity2.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
