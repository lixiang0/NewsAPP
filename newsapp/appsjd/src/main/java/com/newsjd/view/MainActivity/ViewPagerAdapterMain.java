package com.newsjd.view.MainActivity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewPagerAdapterMain extends FragmentStatePagerAdapter {
    private int length = 0;

    private ArrayList<String> mItems = new ArrayList<>();

    private ArrayList<Fragment> pageFragmentSArrayList = new ArrayList<>(length);

    public ViewPagerAdapterMain(FragmentManager fm, int[] data, String[] data_Name) {
        super(fm);
        addData(data, data_Name);
    }

    private void addData(int[] data, String[] data_Name) {
        if (data != null) {
            length = data.length;
            mItems.addAll(Arrays.asList(data_Name).subList(0, length));
            pageFragmentSArrayList.add(new FirstFragment());
            pageFragmentSArrayList.add(new SecondFragment());
            pageFragmentSArrayList.add(new SecondFragment());
            pageFragmentSArrayList.add(new SecondFragment());
        }
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public Fragment getItem(int i) {
        return pageFragmentSArrayList.get(i);
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
