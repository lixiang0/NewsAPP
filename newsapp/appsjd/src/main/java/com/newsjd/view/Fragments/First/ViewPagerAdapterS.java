package com.newsjd.view.Fragments.First;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;

public class ViewPagerAdapterS extends FragmentStatePagerAdapter {
    private int length = 0;

    private ArrayList<String> mItems = new ArrayList<>();

    private ArrayList<PageFragmentS> pageFragmentSArrayList = new ArrayList<>(length);

    public ViewPagerAdapterS(FragmentManager fm, int[] data, String[] data_Name) {
        super(fm);
        addData(data, data_Name);
    }

    private void addData(int[] data, String[] data_Name) {
        if (data != null) {
            length = data.length;
            for (int i = 0; i < length; i++) {
                mItems.add(data_Name[i]);
                pageFragmentSArrayList.add(new PageFragmentS().setPosition(i));
            }
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
}
