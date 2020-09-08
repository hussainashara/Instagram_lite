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

import com.example.sastu_insta.R;
import com.example.sastu_insta.utils.FirebaseMethods;
import com.example.sastu_insta.utils.StringManipulation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {


    private static final String TAG = "RegistrationActivity";

    private TextView email,password,fullname,nav_login_text;
    private Button signup_button;
    private ProgressBar mprogressbar;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mRef;
    private String append = "";
    private String fullname2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mRef = mdatabase.getReference();


        signup_button = findViewById(R.id.signup_button);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        nav_login_text=findViewById(R.id.nav_login_text);
        fullname=findViewById(R.id.input_username);
        mprogressbar=findViewById(R.id.signupProgressbar);
        mprogressbar.setVisibility(View.GONE);

        nav_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mprogressbar.setVisibility(View.VISIBLE);
                final String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String fullname1 = fullname.getText().toString();
                if (email1.isEmpty() || password1.isEmpty() || fullname1.isEmpty()){
                    mprogressbar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this,"Please enter all details",Toast.LENGTH_SHORT).show();
                }else {
                    mauth.createUserWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in User's information
                                        if (mauth.getCurrentUser() != null) {
                                            mRef.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                                    FirebaseMethods mMethods = new FirebaseMethods(RegistrationActivity.this);
                                                    String username = fullname.getText().toString();
                                                    username = StringManipulation.condenseusername(username);

//                                                    checkIfUsernameExists(username);
                                                    Log.d(TAG, "onDataChange:-------------------------------------------------------------- "+fullname2);

                                                    mMethods.addnewUser(email1,"golu golu","","","");
                                                    mprogressbar.setVisibility(View.GONE);
                                                    mMethods.SendEmailVerif();
                                                    mauth.signOut();
                                                    startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));


                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                    } else {
                                        // If sign in fails, display a message to the User.
                                        mprogressbar.setVisibility(View.GONE);
                                        Toast.makeText(RegistrationActivity.this, "Registration failed",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                }
            }
        });
    }

    private void checkIfUsernameExists(final String username) {
        DatabaseReference databaseReference = mdatabase.getReference();

        Query query = databaseReference.child("users").orderByChild("username").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singlesnapShot:dataSnapshot.getChildren()){
                    if (singlesnapShot.exists()){
                        append = "bkbekvbekvb";
                    }
                }
                fullname2 = fullname.getText().toString();
                fullname2 += append;




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
