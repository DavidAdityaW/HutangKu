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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NewPiutangAct extends AppCompatActivity {

    TextView titlepage, addnamepiutang, addamountpiutang, adddescpiutang, adddatepiutang;
    EditText namapiutang, jumlahpiutang, deskripsipiutang, tanggalpiutang;
    Button btnSaveCredit, btnCancelCredit;
    DatabaseReference reference;
    Integer piutangNum = new Random().nextInt(); // Id piutang
    String keypiutang = Integer.toString(piutangNum);
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_piutang);

        titlepage = findViewById(R.id.titlepage);

        addnamepiutang = findViewById(R.id.addnamepiutang);
        addamountpiutang = findViewById(R.id.addamountpiutang);
        adddescpiutang = findViewById(R.id.adddescpiutang);
        adddatepiutang = findViewById(R.id.adddatepiutang);

        namapiutang = findViewById(R.id.namapiutang);
        jumlahpiutang = findViewById(R.id.jumlahpiutang);
        deskripsipiutang = findViewById(R.id.deskripsipiutang);
        tanggalpiutang = findViewById(R.id.tanggalpiutang);

        btnSaveCredit = findViewById(R.id.btnSaveCredit);
        btnCancelCredit = findViewById(R.id.btnCancelCredit);

        // Datetimepicker
        tanggalpiutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewPiutangAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM yy", Locale.US);
                        tanggalpiutang.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnSaveCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert data to firebase
                reference = FirebaseDatabase.getInstance().getReference().child("PiutangApp").
                        child("piutang" + piutangNum);
                reference.addListenerForSingleValueEvent(new ValueEventListener() { // Ketika ada perubahan data dibaca 1x
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Set and put the data in MainActivity2
                        dataSnapshot.getRef().child("namapiutang").setValue(namapiutang.getText().toString());
                        dataSnapshot.getRef().child("jumlahpiutang").setValue(jumlahpiutang.getText().toString());
                        dataSnapshot.getRef().child("deskripsipiutang").setValue(deskripsipiutang.getText().toString());
                        dataSnapshot.getRef().child("tanggalpiutang").setValue(tanggalpiutang.getText().toString());
                        dataSnapshot.getRef().child("keypiutang").setValue(keypiutang);

                        // Show the data in MainActivity2
                        Toast.makeText(getApplicationContext(), "Data successfully added", Toast.LENGTH_SHORT).show();

                        Intent a = new Intent(NewPiutangAct.this, MainActivity2.class);
                        startActivity(a);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        // Import font
//        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
//        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        // Customize font
//        titlepage.setTypeface(MMedium);

//        addnamepiutang.setTypeface(MLight);
//        namapiutang.setTypeface(MMedium);

//        addamountpiutang.setTypeface(MLight);
//        jumlahpiutang.setTypeface(MMedium);

//        adddescpiutang.setTypeface(MLight);
//        deskripsipiutang.setTypeface(MMedium);

//        adddatepiutang.setTypeface(MLight);
//        tanggalpiutang.setTypeface(MMedium);

//        btnSaveCredit.setTypeface(MMedium);
//        btnCancelCredit.setTypeface(MLight);
    }
}
