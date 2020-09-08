package com.example.sastu_insta.Share;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sastu_insta.R;
import com.example.sastu_insta.utils.bottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();


    }

    //Bottom navigational view-------------------------
    private void setupBottomNavigationView(){
       // Log.d(TAG, "setupBottomNavigationView: Setting bottom navigation");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomnavbar);
        bottomNavViewHelper.SetupBottomNavViewHelper(bottomNavigationViewEx);
        bottomNavViewHelper.enableNavigation(ShareActivity.this,bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


    }


}
