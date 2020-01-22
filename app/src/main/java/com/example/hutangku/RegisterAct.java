package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterAct extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnRegister;
    private EditText etEmail, etPassword, etName;

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

        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String nama = etName.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(RegisterAct.this, "Silahkan isi username Anda",
                            Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(RegisterAct.this, "Silahkan isi password Anda",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(RegisterAct.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(RegisterAct.this, "Authentication success.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterAct.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent b = new Intent(RegisterAct.this, LoginAct.class);
                    startActivity(b);
                    finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(RegisterAct.this,LoginAct.class);
        startActivity(a);
        finish();
    }




}
