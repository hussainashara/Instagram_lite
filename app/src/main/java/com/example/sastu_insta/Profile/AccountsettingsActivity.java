package com.example.sastu_insta.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sastu_insta.Home.HomeActivity;
import com.example.sastu_insta.R;
import com.example.sastu_insta.models.UserAccountSett;
import com.example.sastu_insta.models.UserSettings;
import com.example.sastu_insta.utils.FirebaseMethods;
import com.example.sastu_insta.utils.SectionStatePagerAdapter;
import com.example.sastu_insta.utils.UniversaIimageLoader;
import com.example.sastu_insta.utils.bottomNavViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountsettingsActivity extends AppCompatActivity {

    Context mcontext;

    private SectionStatePagerAdapter pagerAdapter;
    private ViewPager mviewpager;
    private RelativeLayout mrelativelayout;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mcontext = AccountsettingsActivity.this;
        setupBottomNavigationView();
        setUpSettingList();
        setupFragment();

        //BackArrow navigation
        ImageView backarrow = findViewById(R.id.back_arrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        mviewpager = findViewById(R.id.container);
        mrelativelayout = findViewById(R.id.relLayout1);
        getIncomingIntent();





    }






    private void getIncomingIntent(){

        Intent intent = getIntent();

        if (intent.hasExtra("calling")){
            setViewPager(pagerAdapter.getFragmnetNumber(intent.getStringExtra("calling")));
        }


    }
    public  void  setupFragment(){
        pagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfile_Fragment(),getString(R.string.edit_profile_fragment));
        pagerAdapter.addFragment(new Signout_Fragment(),getString(R.string.Signout_fragment));

    }
    public void setViewPager(int FragmnetNumber){
        mrelativelayout.setVisibility(View.GONE);
        mviewpager.setAdapter(pagerAdapter);
        mviewpager.setCurrentItem(FragmnetNumber);

    }

    private void setUpSettingList(){
        ListView listView = findViewById(R.id.listview_accountsett);
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.Signout_fragment));

        ArrayAdapter arrayAdapter = new ArrayAdapter(mcontext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setViewPager(i);
            }
        });

    }

    //Bottom navigational view-------------------------
    private void setupBottomNavigationView(){
//        Log.d(TAG, "setupBottomNavigationView: Setting bottom navigation");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomnavbar);
        bottomNavViewHelper.SetupBottomNavViewHelper(bottomNavigationViewEx);
        bottomNavViewHelper.enableNavigation(AccountsettingsActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

    }



}
