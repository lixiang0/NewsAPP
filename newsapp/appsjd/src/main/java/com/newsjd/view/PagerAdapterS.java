package com.newsjd.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.newsjd.config.Contants;

import java.util.ArrayList;

public class PagerAdapterS extends FragmentStatePagerAdapter {
    private ArrayList<String> mItems = null;

    public PagerAdapterS(FragmentManager fm) {
        super(fm);
        mItems = new ArrayList<>();
        for (int i : Contants.AllItem) {
            mItems.add(Contants.getItemName(i));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return new PageFragmentS();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
