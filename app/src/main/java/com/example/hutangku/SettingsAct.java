package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SettingsAct extends AppCompatActivity {

    TextView notifikasi, help, about, profile, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notifikasi = findViewById(R.id.notifikasi);
        help = findViewById(R.id.help);
        about = findViewById(R.id.about);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);

        // About
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SettingsAct.this, AboutAct.class);
                startActivity(a);
                finish();
            }
        });

        // Profile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SettingsAct.this, AppsTeamAct.class);
                startActivity(a);
                finish();
            }
        });

        // Logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SettingsAct.this, LoginAct.class);
                startActivity(a);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(SettingsAct.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
