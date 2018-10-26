package com.newsjd.view.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;

import pub.cpp.news.R;

import com.newsjd.config.Contants;
import com.newsjd.view.Adapter.AdapterFirstVP;

public class FirstFragment extends Fragment {
    private static final String TAG = "NEWS FirstAty";

    private AdapterFirstVP adapterFirstVP;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view;
        view = inflater.inflate(R.layout.activity_first, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        adapterFirstVP = new AdapterFirstVP(getFragmentManager(), Contants.AllItem, Contants.AllItem_Name);
        viewPager.setAdapter(adapterFirstVP);
        DachshundTabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        //添加监听，当切换页面的时候， 如果没有数据，那么就加载数据
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapterFirstVP.loadInitData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
