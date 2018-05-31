package com.newsjd;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.network.bean.NewsData;
import com.network.config.Constants;
import com.newsjd.config.Contants;
import com.newsjd.view.Fragments.PagerAdapterS;
import com.utils.HttpUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FirstActivty extends AppCompatActivity {
    private static final String TAG = "NEWS FirstAty";

    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;

    private PagerAdapterS pagerAdapterS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapterS = new PagerAdapterS(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterS);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
