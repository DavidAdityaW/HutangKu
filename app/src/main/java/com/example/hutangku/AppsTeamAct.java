package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AppsTeamAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_team);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(AppsTeamAct.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
