package com.example.sastu_insta.utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.sastu_insta.Home.HomeActivity;
import com.example.sastu_insta.Likes.LikesActivity;
import com.example.sastu_insta.Profile.ProfileActivity;
import com.example.sastu_insta.R;
import com.example.sastu_insta.Search.SearchActivity;
import com.example.sastu_insta.Share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class bottomNavViewHelper {

    public static void SetupBottomNavViewHelper(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void enableNavigation(final Context context,BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_house:
                        context.startActivity(new Intent(context, HomeActivity.class));
                        break;

                    case R.id.ic_search:
                        context.startActivity(new Intent(context, SearchActivity.class));
                        break;

                    case R.id.ic_circle:
                        context.startActivity(new Intent(context, ShareActivity.class));
                        break;

                    case R.id.ic_android:
                        context.startActivity(new Intent(context, ProfileActivity.class));
                        break;

                    case R.id.ic_alert:
                        context.startActivity(new Intent(context, LikesActivity.class));
                        break;

                }



                return false;
            }
        });


    }


}
