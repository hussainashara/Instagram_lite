package com.example.sastu_insta.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sastu_insta.Login.LoginActivity;
import com.example.sastu_insta.R;
import com.google.firebase.auth.FirebaseAuth;

public class Signout_Fragment extends Fragment {

    private FirebaseAuth mauth;
    private Button signout_button;
    private ProgressBar mprogressbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout,container,false);


        mauth = FirebaseAuth.getInstance();
        signout_button = view.findViewById(R.id.Signout_activity_button);
        mprogressbar = view.findViewById(R.id.signout_progressbar);
        mprogressbar.setVisibility(View.GONE);


        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mprogressbar.setVisibility(View.VISIBLE);
                mauth.signOut();
                mprogressbar.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}
