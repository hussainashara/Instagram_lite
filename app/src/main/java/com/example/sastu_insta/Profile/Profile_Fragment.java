package com.example.sastu_insta.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sastu_insta.R;
import com.example.sastu_insta.models.User;
import com.example.sastu_insta.models.UserAccountSett;
import com.example.sastu_insta.models.UserSettings;
import com.example.sastu_insta.utils.FirebaseMethods;
import com.example.sastu_insta.utils.UniversaIimageLoader;
import com.example.sastu_insta.utils.bottomNavViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Fragment extends Fragment {

    private TextView mfollowers,mfollowing,mposts,mdisplay_name,musername,mdescription,mwebsite;
    private GridView  gridView;
    private CircleImageView mprofile_photo;
    private Toolbar toolbar;
    private ImageView profilemenu;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private FirebaseMethods mMethods;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_profile,container,false);

       mdisplay_name = view.findViewById(R.id.display_name);
       musername = view.findViewById(R.id.profilename);
       mwebsite =view.findViewById(R.id.website);
       mdescription = view.findViewById(R.id.description);
       mprofile_photo = view.findViewById(R.id.profile_image);
       mposts = view.findViewById(R.id.tvPosts);
       mfollowers = view.findViewById(R.id.tvFollowers);
       mfollowing = view.findViewById(R.id.tvFollowing);
       gridView = view.findViewById(R.id.gridlayout);
       toolbar = view.findViewById(R.id.profiletoolbar);
       profilemenu = view.findViewById(R.id.profileMenu);
       bottomNavigationViewEx = view.findViewById(R.id.bottomnavbar);
       mMethods = new FirebaseMethods(getActivity());

       TextView meditprofile = view.findViewById(R.id.textEditProfile);

       mauth = FirebaseAuth.getInstance();
       mdatabase = FirebaseDatabase.getInstance();
       mref = mdatabase.getReference();

       setupBottomNavigationView();
       setUpToolbar();



       mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                SetProfileWidgets(mMethods.userSettings(dataSnapshot));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       meditprofile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              Intent intent = new Intent(getActivity(),AccountsettingsActivity.class);
              intent.putExtra("calling",getString(R.string.edit_profile_fragment));
              startActivity(intent);
           }
       });





       return view;
    }


    private void SetProfileWidgets(UserSettings userSettings){

//        User user = userSettings.getUser();
        UserAccountSett userAccountSett = userSettings.getUserAccountSett();

        UniversaIimageLoader.setImage(userAccountSett.getProfile_photo(),mprofile_photo,null,"");
        mdisplay_name.setText(userAccountSett.getDisplay_name());
        musername.setText(userAccountSett.getUsername());
        mdescription.setText(userAccountSett.getDescription());
        mwebsite.setText(userAccountSett.getWebsite());
        mfollowers.setText(String.valueOf(userAccountSett.getFollowers()));
        mposts.setText(String.valueOf(userAccountSett.getPosts()));
        mfollowing.setText(String.valueOf(userAccountSett.getFollowing()));


    }


//    Bottom navigational view-------------------------
    private void setupBottomNavigationView(){
       // Log.d(TAG, "setupBottomNavigationView: Setting bottom navigation");
        bottomNavViewHelper.SetupBottomNavViewHelper(bottomNavigationViewEx);
        bottomNavViewHelper.enableNavigation(getActivity(),bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);


    }

    private void setUpToolbar(){

        ((ProfileActivity)getActivity()).setSupportActionBar(toolbar);
        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AccountsettingsActivity.class));
            }
        });
    }
}
