package com.example.sastu_insta.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sastu_insta.Home.HomeActivity;
import com.example.sastu_insta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mauth;
    private TextView email,password,nav_reg_text;
    private ProgressBar mprogressbar;
    private Button login_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth = FirebaseAuth.getInstance();

        email=findViewById(R.id.input_email);
        mprogressbar = findViewById(R.id.loginProgressbar);
        password=findViewById(R.id.input_password);
        nav_reg_text=findViewById(R.id.nav_reg_text);
        login_button=findViewById(R.id.login_button);

        mprogressbar.setVisibility(View.GONE);

        nav_reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  email1 = email.getText().toString();
                String password1 = password.getText().toString();
                mprogressbar.setVisibility(View.VISIBLE);

                if (email1.isEmpty() || password1.isEmpty()) {
                    mprogressbar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Please enter all details",Toast.LENGTH_SHORT).show();
                }
                else {
                    mauth.signInWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in User's information
                                        mprogressbar.setVisibility(View.GONE);
                                        FirebaseUser user = mauth.getCurrentUser();
                                        if (user!=null){
                                            if (user.isEmailVerified()) {
                                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                                finish();
                                            }
                                            else if (!user.isEmailVerified()){
                                                Toast.makeText(LoginActivity.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                                            }
                                        }


                                    } else {
                                        Toast.makeText(LoginActivity.this,"Login Failed,Email or Password is incorrect",Toast.LENGTH_SHORT).show();
                                        mprogressbar.setVisibility(View.GONE);
                                    }


                                }
                            });
                }
            }
        });

    }



}
