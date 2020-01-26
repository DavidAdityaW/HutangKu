package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutAct extends AppCompatActivity {

    ImageView imgViewLogo;
    TextView appname, appversion;
    Animation alfatogo, alfatogotwo, alfatogothree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        alfatogo = AnimationUtils.loadAnimation(this, R.anim.alfatogo);
        alfatogotwo = AnimationUtils.loadAnimation(this, R.anim.alfatogotwo);
        alfatogothree = AnimationUtils.loadAnimation(this, R.anim.alfatogothree);

        imgViewLogo = findViewById(R.id.imgViewLogo);
        appname = findViewById(R.id.appname);
        appversion = findViewById(R.id.appversion);

        // Pass an animation
        imgViewLogo.startAnimation(alfatogo);
        appname.startAnimation(alfatogotwo);
        appversion.startAnimation(alfatogothree);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(AboutAct.this,SettingsAct.class);
        startActivity(a);
        finish();
    }
}
