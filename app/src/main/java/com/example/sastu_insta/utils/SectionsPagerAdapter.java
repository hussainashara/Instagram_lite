package com.example.sastu_insta.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment>mfragmentlist = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fn){
        super(fn);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return  mfragmentlist.size();
    }

    public void addFragment(Fragment fragment){
    mfragmentlist.add(fragment);

    }
}
