package com.newsjd.view.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.newsjd.R;
import com.newsjd.config.Contants;
import com.newsjd.view.Adapter.AdapterFirstVP;

public class FirstFragment extends Fragment {
    private static final String TAG = "NEWS FirstAty";

    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;

    private AdapterFirstVP adapterFirstVP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.activity_first, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        viewPager = view.findViewById(R.id.view_pager);
        adapterFirstVP = new AdapterFirstVP(getFragmentManager(), Contants.AllItem,Contants.AllItem_Name);
        viewPager.setAdapter(adapterFirstVP);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
