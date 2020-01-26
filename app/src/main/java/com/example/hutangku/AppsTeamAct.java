package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppsTeamAct extends AppCompatActivity {

    Button btnLogout;
    TextView myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_team);

        btnLogout = findViewById(R.id.btnLogout);
        myName = findViewById(R.id.myName);

        // Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AppsTeamAct.this, LoginAct.class);
                startActivity(a);
                finish();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myName.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(AppsTeamAct.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
