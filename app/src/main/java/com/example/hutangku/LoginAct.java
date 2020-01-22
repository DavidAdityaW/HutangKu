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

public class LoginAct extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnLogin, btnSignUp;
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

        mAuth = FirebaseAuth.getInstance();
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        handler.postDelayed(runnable, 2000); // 2000 is the timeout for the splash

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Button btnLogin diklik
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent a = new Intent(LoginAct.this,DashboardAct.class);
                startActivity(a);
                finish();
           }
        });

//        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(LoginAct.this, "Silahkan input username Anda",
                            Toast.LENGTH_SHORT).show();

                }else if (password.equals("")) {
                    Toast.makeText(LoginAct.this, "Silahkan input password Anda",
                            Toast.LENGTH_SHORT).show();

                }else {

                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginAct.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Toast.makeText(LoginAct.this, "Authentication success.",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginAct.this, "Authentication failed.",
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

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
