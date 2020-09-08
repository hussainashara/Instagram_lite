package com.example.sastu_insta.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sastu_insta.R;
import com.example.sastu_insta.models.User;
import com.example.sastu_insta.models.UserAccountSett;
import com.example.sastu_insta.models.UserSettings;
import com.example.sastu_insta.utils.FirebaseMethods;
import com.example.sastu_insta.utils.UniversaIimageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfile_Fragment extends Fragment {


    private EditText musername,mdisplay_name,mdescription,mwebsite,memail,mphone_no;
    private TextView changeProfilephoto;
    private ImageView mprofilephoto,backarrow,checkmark;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private FirebaseMethods mMethods;
    private String userId;
    private UserSettings musersettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile,container,false);


        mprofilephoto = view.findViewById(R.id.profile_photo);
        backarrow = view.findViewById(R.id.back_arrow);
        mdisplay_name = view.findViewById(R.id.Display_name);
        musername = view.findViewById(R.id.username);
        mdescription = view.findViewById(R.id.description);
        mwebsite = view.findViewById(R.id.website);
        memail  = view.findViewById(R.id.email);
        mphone_no = view.findViewById(R.id.phone_no);
        changeProfilephoto = view.findViewById(R.id.changeProfile_photo);



        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mref = mdatabase.getReference();
        mMethods = new FirebaseMethods(getActivity());
        userId = mauth.getCurrentUser().getUid();



        //backarrow click listener
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                SetProfileWidgets(mMethods.userSettings(dataSnapshot));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void SaveProfileChanges(){
        final  String username = musername.getText().toString();
        final  String  display_name = mdisplay_name.getText().toString();
        final  String website = mwebsite.getText().toString();
        final  String description = mdescription.getText().toString();
        final  String email = memail.getText().toString();
        final  String phone_no = mphone_no.getText().toString();

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (musersettings.getUser().getUsername().equals(username)){

                    checkIfUsernameExists(username);
                }else {


                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void checkIfUsernameExists(final String username) {
        DatabaseReference databaseReference = mdatabase.getReference();

        Query query = databaseReference.child("users").orderByChild("username").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    mMethods.updateUsername(username);

                }
                for (DataSnapshot singlesnapShot:dataSnapshot.getChildren()){
                    if (singlesnapShot.exists()){
                        Toast.makeText(getActivity(), "The username already exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SetProfileWidgets(UserSettings userSettings){

        musersettings = userSettings;
        User user = userSettings.getUser();
        UserAccountSett userAccountSett = userSettings.getUserAccountSett();

        UniversaIimageLoader.setImage(userAccountSett.getProfile_photo(),mprofilephoto,null,"");
        mdisplay_name.setText(userAccountSett.getDisplay_name());
        musername.setText(userAccountSett.getUsername());
        mdescription.setText(userAccountSett.getDescription());
        mwebsite.setText(userAccountSett.getWebsite());
        memail.setText(user.getEmail());
        mphone_no.setText(String.valueOf(user.getPhone_number()));

    }






}
