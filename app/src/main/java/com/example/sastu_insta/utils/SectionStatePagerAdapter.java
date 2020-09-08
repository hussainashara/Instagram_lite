package com.example.sastu_insta.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionStatePagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mfragmentlist = new ArrayList<>();
    private final HashMap<String,Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer,String> mFragmentNames = new HashMap<>();
    private final HashMap<Fragment,Integer> mFragments = new HashMap<>();


    public SectionStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentlist.size();

    }

    public void addFragment(Fragment fragment,String FragmentName){
        mfragmentlist.add(fragment);
        mFragments.put(fragment,mfragmentlist.size()-1);
        mFragmentNames.put(mfragmentlist.size()-1,FragmentName);
        mFragmentNumbers.put(FragmentName,mfragmentlist.size()-1);
    }

    public Integer getFragmnetNumber(String FragmentName){
        if (mFragmentNumbers.containsKey(FragmentName)){
            return mFragmentNumbers.get(FragmentName);

        }else {
            return null;
        }

    }

    public Integer getFragmnetNumber(Fragment fragment){
        if (mFragmentNumbers.containsKey(fragment)){
            return mFragmentNumbers.get(fragment);

        }else {
            return null;
        }

    }

    public String getFragmnetName(Integer FragmentNumber){
        if (mFragmentNumbers.containsKey(FragmentNumber)){
            return mFragmentNames.get(FragmentNumber);

         }else {
            return null;
        }

    }


}

