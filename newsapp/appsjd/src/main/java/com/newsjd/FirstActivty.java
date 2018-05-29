package com.newsjd;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.newsjd.view.PagerAdapterS;


public class FirstActivty extends AppCompatActivity {

    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapterS(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
