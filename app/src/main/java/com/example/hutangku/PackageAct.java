package com.example.hutangku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PackageAct extends AppCompatActivity {

    TextView pagetitlepackage, pagesubtitlepackage;
    ImageView packagePlace;
    SeekBar packageRange;
    Animation alfatogo, packageimg;
    Button btntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        alfatogo = AnimationUtils.loadAnimation(this, R.anim.alfatogo);
        packageimg = AnimationUtils.loadAnimation(this, R.anim.packageimg);

        pagetitlepackage = findViewById(R.id.pagetitlepackage);
        pagesubtitlepackage = findViewById(R.id.pagesubtitlepackage);

        packagePlace = findViewById(R.id.packagePlace);

        packageRange = findViewById(R.id.packageRange);

        btntake = findViewById(R.id.btntake);

        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 15) {
                    pagetitlepackage.setText("Debt Features");
                    pagesubtitlepackage.setText("This feature makes it easy to record debts");
                    packagePlace.setImageResource(R.drawable.icstarter);

                    // Pass an animation
                    packagePlace.startAnimation(packageimg);
                    pagetitlepackage.startAnimation(alfatogo);
                    pagesubtitlepackage.startAnimation(alfatogo);
                }
                else if (progress == 50) {
                    pagetitlepackage.setText("Calculation Features");
                    pagesubtitlepackage.setText("This feature makes it easy to calculate debt");
                    packagePlace.setImageResource(R.drawable.icbusinessplayer);

                    // Pass an animation
                    packagePlace.startAnimation(packageimg);
                    pagetitlepackage.startAnimation(alfatogo);
                    pagesubtitlepackage.startAnimation(alfatogo);
                }
                else if (progress == 85) {
                    pagetitlepackage.setText("Reminder Features");
                    pagesubtitlepackage.setText("This feature makes it easy for you to remember debt with notifications");
                    packagePlace.setImageResource(R.drawable.icvip);

                    // Pass an animation
                    packagePlace.startAnimation(packageimg);
                    pagetitlepackage.startAnimation(alfatogo);
                    pagesubtitlepackage.startAnimation(alfatogo);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Klik ke DashboardAct
        btntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(PackageAct.this, DashboardAct.class);
                startActivity(a);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(PackageAct.this,DashboardAct.class);
        startActivity(a);
        finish();
    }
}
