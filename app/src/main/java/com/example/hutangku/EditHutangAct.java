package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditHutangAct extends AppCompatActivity {

    EditText namaHutang, jumlahHutang, deskripsiHutang, tanggalHutang;
    Button btnSaveUpdateDebt, btnDeleteDebt;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hutang);

        namaHutang = findViewById(R.id.namahutang);
        jumlahHutang = findViewById(R.id.jumlahhutang);
        deskripsiHutang = findViewById(R.id.deskripsihutang);
        tanggalHutang = findViewById(R.id.tanggalhutang);

        btnSaveUpdateDebt = findViewById(R.id.btnSaveUpdateDebt);
        btnDeleteDebt = findViewById(R.id.btnDeleteDebt);

        // Get a value from previous page
        namaHutang.setText(getIntent().getStringExtra("nama"));
        jumlahHutang.setText(getIntent().getStringExtra("jumlah"));
        deskripsiHutang.setText(getIntent().getStringExtra("deskripsi"));
        tanggalHutang.setText(getIntent().getStringExtra("tanggal"));

        final String keykeyHutang = getIntent().getStringExtra("keyhutang");

        reference = FirebaseDatabase.getInstance().getReference().child("HutangApp").
                child("hutang" + keykeyHutang);

        // Make an event for btnDeleteDebt
        btnDeleteDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Data successfully delete", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(EditHutangAct.this,MainActivity.class);
                            startActivity(a);
                        } else {
                            Toast.makeText(getApplicationContext(),"Failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Make an event for btnSaveUpdateDebt
        btnSaveUpdateDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Set and put the data after edit in MainActivity
                        dataSnapshot.getRef().child("nama").setValue(namaHutang.getText().toString());
                        dataSnapshot.getRef().child("jumlah").setValue(jumlahHutang.getText().toString());
                        dataSnapshot.getRef().child("deskripsi").setValue(deskripsiHutang.getText().toString());
                        dataSnapshot.getRef().child("tanggal").setValue(tanggalHutang.getText().toString());
                        dataSnapshot.getRef().child("keyhutang").setValue(keykeyHutang);

                        // Show the data in MainActivity
                        Toast.makeText(getApplicationContext(), "Data successfully update", Toast.LENGTH_SHORT).show();

                        Intent a = new Intent(EditHutangAct.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
