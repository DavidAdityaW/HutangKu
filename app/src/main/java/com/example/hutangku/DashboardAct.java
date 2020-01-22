package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DashboardAct extends AppCompatActivity {

    TextView mDay, mWelcome, mDate, hutang, totalhutang, piutang, pengaturan, teamapps,
            pagetitle, pagesubtitle;

    Button btnguide;
    Animation alfatogo, alfatogotwo, alfatogothree;
    ImageView imageViewHutang, imageViewPiutang, imageViewPengaturan, imageViewTeamApps, imageViewGuide;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        alfatogo = AnimationUtils.loadAnimation(this, R.anim.alfatogo);
        alfatogotwo = AnimationUtils.loadAnimation(this, R.anim.alfatogotwo);
        alfatogothree = AnimationUtils.loadAnimation(this, R.anim.alfatogothree);

        hutang = findViewById(R.id.hutang);
        totalhutang = findViewById(R.id.totalhutang);
        piutang = findViewById(R.id.piutang);
        pengaturan = findViewById(R.id.pengaturan);
        teamapps = findViewById(R.id.teamapps);

        imageViewHutang = findViewById(R.id.imageViewHutang);
        imageViewPiutang = findViewById(R.id.imageViewPiutang);
        imageViewPengaturan = findViewById(R.id.imageViewPengaturan);
        imageViewTeamApps = findViewById(R.id.imageViewTeamApps);

        pagetitle = findViewById(R.id.pagetitile);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        imageViewGuide = findViewById(R.id.imageViewGuide);
        btnguide = findViewById(R.id.btnguide);

        // Pass an animation
        imageViewGuide.startAnimation(alfatogo);
        pagetitle.startAnimation(alfatogotwo);
        pagesubtitle.startAnimation(alfatogotwo);
        btnguide.startAnimation(alfatogothree);

        // Time now in Dashboard
        mDay = findViewById(R.id.mDay);
        mWelcome = findViewById(R.id.mWelcome);
        mDate = findViewById(R.id.mDate);

        final Date date;
        Calendar cal = Calendar.getInstance();

        Log.d("date", String.valueOf(new Date()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");

        date = cal.getTime();

        String formattedDate = dateFormat.format(date);
        String hour = sdf.format(new Date());

        int hourNow = Integer.valueOf(hour);

//        Log.d("date", String.valueOf(hourNow));
        mDay.setText(date.toString().substring(0,3));
        mDate.setText(formattedDate);

        if (hourNow >= 3 && hourNow <= 11) {
            mWelcome.setText("Good Morning");
        }
        if (hourNow >= 12 && hourNow <= 17) {
            mWelcome.setText("Good Afternoon");
        }
        if (hourNow >= 18 && hourNow <= 23) {
            mWelcome.setText("Good Evening");
        }
        if (hourNow >= 0 && hourNow <= 2) {
            mWelcome.setText("Good Night");
        }

        // Get total hutang in Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("HutangApp");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
                    Object jumlah = map.get("jumlah");
                    int pValue = Integer.parseInt(String.valueOf(jumlah));
                    sum += pValue;

                    totalhutang.setText("Rp. " + String.valueOf(sum));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Import font
//        Typeface MLight = Typeface.createFromAsset(getAssets(), "font/ml.ttf");
//        Typeface MMedium = Typeface.createFromAsset(getAssets(), "font/mm.ttf");
//        Typeface MRegular = Typeface.createFromAsset(getAssets(), "font/mr.ttf");

        // Customize font
//        nameuser.setTypeface(MMedium);
//        walletuser.setTypeface(MLight);

//        mainmenus.setTypeface(MRegular);

//        hutang.setTypeface(MMedium);
//        piutang.setTypeface(MMedium);
//        pengaturan.setTypeface(MMedium);
//        teamapps.setTypeface(MMedium);

//        btnguide.setTypeface(MMedium);

//        pagetitle.setTypeface(MRegular);
//        pagesubtitle.setTypeface(MLight);

        // Klik ke Hutang
        imageViewHutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardAct.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });

        // Klik ke Piutang
        imageViewPiutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardAct.this, MainActivity2.class);
                startActivity(a);
                finish();
            }
        });

        // Klik ke Pengaturan
        imageViewPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardAct.this, SettingsAct.class);
                startActivity(a);
                finish();
            }
        });

        // Klik ke Profile
        imageViewTeamApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardAct.this, AppsTeamAct.class);
                startActivity(a);
                finish();
            }
        });

        // Klik ke Guide
        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardAct.this, PackageAct.class);
                startActivity(a);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
