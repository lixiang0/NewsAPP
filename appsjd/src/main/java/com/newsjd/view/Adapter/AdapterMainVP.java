package com.newsjd.view.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.network.config.Constants;
import com.newsjd.config.Contants;
import com.newsjd.view.Fragments.FirstFragment;
import com.newsjd.view.Fragments.FourFragment;
import com.newsjd.view.Fragments.SecondFragment;
import com.newsjd.view.Fragments.ThirdFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class AdapterMainVP extends FragmentStatePagerAdapter {
    private int length = 0;

    private ArrayList<String> mItems = new ArrayList<>();

    private ArrayList<? super Fragment> pageFragmentSArrayList = new ArrayList<>(length);

    public AdapterMainVP(FragmentManager fm, String[] data_Name) {
        super(fm);
        addData(data_Name);
    }

    private void addData(String[] data_Name) {
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
