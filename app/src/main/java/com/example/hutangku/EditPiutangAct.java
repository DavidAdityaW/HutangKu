package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditPiutangAct extends AppCompatActivity {

    EditText namaPiutang, jumlahPiutang, deskripsiPiutang, tanggalPiutang;
    Button btnSaveUpdateCredit, btnDeleteCredit;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_piutang);

        namaPiutang = findViewById(R.id.namapiutang);
        jumlahPiutang = findViewById(R.id.jumlahpiutang);
        deskripsiPiutang = findViewById(R.id.deskripsipiutang);
        tanggalPiutang = findViewById(R.id.tanggalpiutang);

        btnSaveUpdateCredit = findViewById(R.id.btnSaveUpdateCredit);
        btnDeleteCredit = findViewById(R.id.btnDeleteCredit);

        // Get a value from previous page
        namaPiutang.setText(getIntent().getStringExtra("namapiutang"));
        jumlahPiutang.setText(getIntent().getStringExtra("jumlahpiutang"));
        deskripsiPiutang.setText(getIntent().getStringExtra("deskripsipiutang"));
        tanggalPiutang.setText(getIntent().getStringExtra("tanggalpiutang"));

        final String keykeypiutang = getIntent().getStringExtra("keypiutang");

        reference = FirebaseDatabase.getInstance().getReference().child("PiutangApp").
                child("piutang" + keykeypiutang);

        // Make an event for btnSaveUpdateCredit
        btnSaveUpdateCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("HutangKu").child("Piutang" + keykeypiutang);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // Set and put the data after edit in MainActivity
                        dataSnapshot.getRef().child("namapiutang").setValue(namaPiutang.getText().toString());
                        dataSnapshot.getRef().child("jumlahpiutang").setValue(jumlahPiutang.getText().toString());
                        dataSnapshot.getRef().child("deskripsipiutang").setValue(deskripsiPiutang.getText().toString());
                        dataSnapshot.getRef().child("tanggalpiutang").setValue(tanggalPiutang.getText().toString());
                        dataSnapshot.getRef().child("keypiutang").setValue(keykeypiutang);

                        // Show the data in MainActivity2
                        Toast.makeText(getApplicationContext(), "Data successfully update", Toast.LENGTH_SHORT).show();

                        Intent a = new Intent(EditPiutangAct.this, MainActivity2.class);
                        startActivity(a);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDeleteCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent a = new Intent(EditPiutangAct.this,MainActivity2.class);
                            startActivity(a);
                        } else {
                            Toast.makeText(getApplicationContext(), "Deleting has failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Datetimepicker
        tanggalPiutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPiutangAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM yy", Locale.US);
                        tanggalPiutang.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(EditPiutangAct.this,MainActivity2.class);
        startActivity(a);
        finish();
    }
}
