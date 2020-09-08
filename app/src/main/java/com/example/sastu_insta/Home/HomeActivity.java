package com.example.sastu_insta.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sastu_insta.Login.LoginActivity;
import com.example.sastu_insta.R;
import com.example.sastu_insta.utils.SectionsPagerAdapter;
import com.example.sastu_insta.utils.UniversaIimageLoader;
import com.example.sastu_insta.utils.bottomNavViewHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "Home";
    private FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        setupBottomNavigationView();
        setUpViewPager();
        mauth = FirebaseAuth.getInstance();




    }

    //Adding camera,instagram,message to HomeActivity
    private void setUpViewPager(){

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new camera_frag());
        adapter.addFragment(new home_frag());
        adapter.addFragment(new message_frag());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.insta);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

    }

    //Bottom navigational view-------------------------
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: Setting bottom navigation");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomnavbar);
        bottomNavViewHelper.SetupBottomNavViewHelper(bottomNavigationViewEx);
        bottomNavViewHelper.enableNavigation(HomeActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

    }

    private void initImageLoader(){
        UniversaIimageLoader universaIimageLoader = new UniversaIimageLoader(HomeActivity.this);
        ImageLoader.getInstance().init(universaIimageLoader.getConfig());

    }

    //--------firebase-------------
    @Override
    public void onStart() {
        super.onStart();
        // Check if User is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mauth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
        if (currentUser != null){
//            Toast.makeText(HomeActivity.this,"User found",Toast.LENGTH_SHORT).show();
        }
    }

}
