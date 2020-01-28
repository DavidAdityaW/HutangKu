package com.example.hutangku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAct extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnLogin, btnSignUp, btnForgot;
    RelativeLayout rellay1, rellay2;
    private EditText etUsername, etPassword;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        handler.postDelayed(runnable, 2000); // 2000 is the timeout for the splash

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnForgot = findViewById(R.id.btnForgot);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(LoginAct.this, "Silahkan isi username Anda",
                            Toast.LENGTH_SHORT).show();
                    etUsername.requestFocus();

                }else if (password.equals("")) {
                    Toast.makeText(LoginAct.this, "Silahkan isi password Anda",
                            Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();

                }else {
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginAct.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Toast.makeText(LoginAct.this, "Welcome " +user.getEmail(),
                                                Toast.LENGTH_SHORT).show();

                                        Intent a = new Intent(LoginAct.this,DashboardAct.class);
                                        startActivity(a);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginAct.this, "Login failed",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

        // Button btnSignUp diklik
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(LoginAct.this, RegisterAct.class);
                startActivity(b);
                finish();
            }
        });

        // Button btnForgot diklik
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });
    }

    private void showRecoverPasswordDialog() {
        // AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");

        // Set layout linear layout
        LinearLayout linearLayout = new LinearLayout(this);
        // View to set in dialog
        final EditText etEmail = new EditText(this);
        etEmail.setHint("Email");
        etEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etEmail.setMinEms(16);

        linearLayout.addView(etEmail);
        linearLayout.setPadding(20, 10, 20, 10);

        builder.setView(linearLayout);

        // Buttons recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Input email
                String email = etEmail.getText().toString().trim();
                beginRecovery(email);
            }
        });
//        // Buttons cancel
//        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Dismiss dialog
//                dialog.dismiss();
//            }
//        });

        // Show dialog
        builder.create().show();
    }

    private void beginRecovery(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginAct.this, "Email send", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginAct.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Get and show error message
                Toast.makeText(LoginAct.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
