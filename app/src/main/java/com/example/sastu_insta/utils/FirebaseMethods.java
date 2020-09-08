package com.example.sastu_insta.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sastu_insta.models.User;
import com.example.sastu_insta.models.UserAccountSett;
import com.example.sastu_insta.models.UserSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseMethods {

    private FirebaseAuth mauth;
    private Context mcontext;
    private String UserId;
    private FirebaseDatabase mdatabase;
   private DatabaseReference mref;

    public FirebaseMethods(Context mcontext) {
        this.mcontext = mcontext;
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mref = mdatabase.getReference();

        if (mauth.getCurrentUser() != null){
            UserId = mauth.getCurrentUser().getUid();
        }

    }

//    public  boolean checkUsername(String username, DataSnapshot dataSnapshot){
//        User user = new User();
//
//        for (DataSnapshot ds:dataSnapshot.child(UserId).getChildren()){
//            user.setUsername(ds.getValue(User.class).getUsername());
//            String usernamedata = ds.child("username").getValue(String.class);
//
//            if (StringManipulation.expandusername(usernamedata).equals(username)){
//                return true;
//
//            }
//        }
//        return false;
//
//
//    }

    public void addnewUser(String email,String username,String descripition,String website,String profile_photo_url){
        User user = new User(UserId,0,email,StringManipulation.condenseusername(username));
        mref.child("users").child(UserId).setValue(user);

        UserAccountSett userAccountSett = new UserAccountSett(
                descripition,username,0,0,0,profile_photo_url,StringManipulation.condenseusername(username),website
        );

        mref.child("user_accountsetting").child(UserId).setValue(userAccountSett);

    }

    public void SendEmailVerif(){
        FirebaseUser user = mauth.getCurrentUser();

        if (user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(mcontext, "Registered Successfully,Email verification sent", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mcontext, "Couldn't send email verification",Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }

    }

    public UserSettings userSettings(DataSnapshot dataSnapshot){

        UserAccountSett userAccountSett = new UserAccountSett();
        User user = new User();
            for (DataSnapshot ds:dataSnapshot.getChildren()){
                if (ds.getKey().equals("user_accountsetting")){

                    try {


                    userAccountSett.setDisplay_name(ds.child(UserId).getValue(UserAccountSett.class).getDisplay_name());
                    userAccountSett.setUsername(ds.child(UserId).getValue(UserAccountSett.class).getUsername());
                    userAccountSett.setDescription(ds.child(UserId).getValue(UserAccountSett.class).getDescription());
                    userAccountSett.setFollowers(ds.child(UserId).getValue(UserAccountSett.class).getFollowers());
                    userAccountSett.setFollowing(ds.child(UserId).getValue(UserAccountSett.class).getFollowing());
                    userAccountSett.setPosts(ds.child(UserId).getValue(UserAccountSett.class).getPosts());
                    userAccountSett.setWebsite(ds.child(UserId).getValue(UserAccountSett.class).getWebsite());
                    userAccountSett.setProfile_photo(ds.child(UserId).getValue(UserAccountSett.class).getProfile_photo());

                    }catch (NullPointerException e){
                        Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                if (ds.getKey().equals("users")) {

                    user.setUsername(ds.child(UserId).getValue(User.class).getUsername());
                    user.setEmail(ds.child(UserId).getValue(User.class).getEmail());
                    user.setPhone_number(ds.child(UserId).getValue(User.class).getPhone_number());
                    user.setUser_id(ds.child(UserId).getValue(User.class).getUser_id());
                }

            }

            return new UserSettings(user,userAccountSett);
    }

    public void updateUsername(String username){
        mref.child("users").child(UserId).child("username").setValue(username);

        mref.child("user_accountsetting").child(UserId).child("username").setValue(username);
    }


}
