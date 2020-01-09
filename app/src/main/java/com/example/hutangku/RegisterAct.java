package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegisterAct extends AppCompatActivity {

    RelativeLayout rellay3;
    Handler h = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            rellay3.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rellay3 = (RelativeLayout) findViewById(R.id.rellay3);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(RegisterAct.this,LoginAct.class);
        startActivity(a);
        finish();
    }
}
