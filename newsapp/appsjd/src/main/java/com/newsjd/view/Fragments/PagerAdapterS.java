package com.newsjd.view.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.newsjd.config.Contants;

import java.util.ArrayList;

public class PagerAdapterS extends FragmentStatePagerAdapter {
    private int length = Contants.AllItem.length;

    private ArrayList<String> mItems = null;

    private ArrayList<PageFragmentS> pageFragmentSArrayList = new ArrayList<>(length);

    public PagerAdapterS(FragmentManager fm) {
        super(fm);
        mItems = new ArrayList<>();
        for (int i : Contants.AllItem) {
            mItems.add(Contants.getItemName(i));
            pageFragmentSArrayList.add(new PageFragmentS());
        }
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public PageFragmentS getItem(int i) {
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

    public PageFragmentS getPageFragments(int postion) {
        if (pageFragmentSArrayList == null || postion < 0 || postion >= pageFragmentSArrayList.size()) {
            return null;
        }
        return pageFragmentSArrayList.get(postion);
    }
}
