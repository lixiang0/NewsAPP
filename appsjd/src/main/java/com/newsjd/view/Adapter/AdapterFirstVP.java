package com.newsjd.view.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import com.newsjd.view.Fragments.FirstFragment_Item;

import java.util.ArrayList;

public class AdapterFirstVP extends FragmentStatePagerAdapter {
    private int length = 0;

    private ArrayList<String> mItems = new ArrayList<>();

    private ArrayList<FirstFragment_Item> firstFragmentItemArrayList = new ArrayList<>(length);

    public AdapterFirstVP(FragmentManager fm, int[] data, String[] data_Name) {
        super(fm);
        addData(data, data_Name);
    }

    private void addData(int[] data, String[] data_Name) {
        if (data != null) {
            length = data.length;
            for (int i = 0; i < length; i++) {
                mItems.add(data_Name[i]);
                firstFragmentItemArrayList.add(new FirstFragment_Item().setPosition(i));
            }
        }
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public Fragment getItem(int i) {
        return firstFragmentItemArrayList.get(i);
    }

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }


    public void loadInitData(int position) {
        firstFragmentItemArrayList.get(position).loadInitData();
    }
}
