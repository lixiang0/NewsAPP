package com.newsjd.view.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.newsjd.config.Contants;

import java.util.ArrayList;
import java.util.Arrays;

public class AdapterMainVP extends FragmentStatePagerAdapter {
    private int length = 0;

    private ArrayList<String> mItems = new ArrayList<>();

    private ArrayList<? super Fragment> pageFragmentSArrayList = new ArrayList<>(length);

    public AdapterMainVP(FragmentManager fm, String[] data_Name) {
        super(fm);
        initFragment(data_Name);
    }

    private void initFragment(String[] data_Name) {
        if (data_Name != null) {
            length = data_Name.length;
            mItems.addAll(Arrays.asList(data_Name).subList(0, length));
            pageFragmentSArrayList = Contants.getFragment();
        }
    }

    @Override
    public Fragment getItem(int i) {
        return (Fragment) pageFragmentSArrayList.get(i);
    }

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
