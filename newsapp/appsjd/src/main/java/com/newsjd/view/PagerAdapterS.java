package com.newsjd.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterS extends FragmentStatePagerAdapter {
    private static final String DOG_BREEDS[] = {"Pug", "Beagle", "Bulldog", "Poodle"};

    public PagerAdapterS(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new PageFragmentS();
    }

    @Override
    public int getCount() {
        return DOG_BREEDS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DOG_BREEDS[position];
    }
}
