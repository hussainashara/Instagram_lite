package com.example.sastu_insta.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.sastu_insta.Home.HomeActivity;
import com.example.sastu_insta.Login.LoginActivity;
import com.example.sastu_insta.R;
import com.example.sastu_insta.utils.GridImageAdapter;
import com.example.sastu_insta.utils.UniversaIimageLoader;
import com.example.sastu_insta.utils.bottomNavViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mauth;

    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mauth = FirebaseAuth.getInstance();
        profilePhoto = findViewById(R.id.profile_image);

        init();
//        setProfilephoto();
//        tempGirdview();
    }

    private void init( ){

        Profile_Fragment profile_fragment = new Profile_Fragment();
        FragmentTransaction transaction  = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,profile_fragment);
        transaction.addToBackStack("Profile");
        transaction.commit();
    }


//    private void tempGirdview(){
//        ArrayList<String> imgurls = new ArrayList<>();
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-kitchen-ideas-designed-by-velinda-hellen-for-ehd-photo-by-sara-tramp-7-1568915517.jpg?crop=0.835xw:1.00xh;0.134xw,0&resize=480:*");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-dog-breeds-bichon-frise-1583341809.jpg?crop=0.668xw:1.00xh;0.114xw,0&resize=480:*");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST0Qae6Cph9UEWcCsVUSI0SIoF1x6hkCCNXAko5udxlECs_bmB&usqp=CAU");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-kitchen-ideas-designed-by-velinda-hellen-for-ehd-photo-by-sara-tramp-7-1568915517.jpg?crop=0.835xw:1.00xh;0.134xw,0&resize=480:*");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-dog-breeds-bichon-frise-1583341809.jpg?crop=0.668xw:1.00xh;0.114xw,0&resize=480:*");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST0Qae6Cph9UEWcCsVUSI0SIoF1x6hkCCNXAko5udxlECs_bmB&usqp=CAU");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-dog-breeds-bichon-frise-1583341809.jpg?crop=0.668xw:1.00xh;0.114xw,0&resize=480:*");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST0Qae6Cph9UEWcCsVUSI0SIoF1x6hkCCNXAko5udxlECs_bmB&usqp=CAU");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSPoUyAiGMYa00rhdNMA8H_95MfMDQD3In8KtRHtYI7dQzSuxJp&usqp=CAU");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSPoUyAiGMYa00rhdNMA8H_95MfMDQD3In8KtRHtYI7dQzSuxJp&usqp=CAU");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-kitchen-ideas-designed-by-velinda-hellen-for-ehd-photo-by-sara-tramp-7-1568915517.jpg?crop=0.835xw:1.00xh;0.134xw,0&resize=480:*");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-dog-breeds-bichon-frise-1583341809.jpg?crop=0.668xw:1.00xh;0.114xw,0&resize=480:*");
//        imgurls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST0Qae6Cph9UEWcCsVUSI0SIoF1x6hkCCNXAko5udxlECs_bmB&usqp=CAU");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-dog-breeds-bichon-frise-1583341809.jpg?crop=0.668xw:1.00xh;0.114xw,0&resize=480:*");
//        imgurls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-kitchen-ideas-designed-by-velinda-hellen-for-ehd-photo-by-sara-tramp-7-1568915517.jpg?crop=0.835xw:1.00xh;0.134xw,0&resize=480:*");
//
//        setGridImage(imgurls);
//    }
//    private void setGridImage(ArrayList<String> imagUrls){
//
//        GridView gridView = findViewById(R.id.gridlayout);
//        int gridwidth = getResources().getDisplayMetrics().widthPixels;
//        int gridimagewidth = gridwidth/3;
//        GridImageAdapter adapter = new GridImageAdapter(ProfileActivity.this,R.layout.layout_grid_imageview,"",imagUrls);
//        gridView.setAdapter(adapter);
//    }
//
//
//
//    private void setProfilephoto(){
//
//        String imgurl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSPoUyAiGMYa00rhdNMA8H_95MfMDQD3In8KtRHtYI7dQzSuxJp&usqp=CAU";
//        UniversaIimageLoader.setImage(imgurl,profilePhoto,null,"");
//
//    }
//
//
//
//
//



}
