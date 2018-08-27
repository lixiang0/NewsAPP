package com.newsjd.view.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import pub.cpp.news.R;
import com.newsjd.config.Contants;
import com.newsjd.view.Adapter.AdapterMainVP;

import net.cachapa.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnMenuItemClickListener {
    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private AdapterMainVP mAdapterMainVP;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.main_view_pager);

        mAdapterMainVP = new AdapterMainVP(getSupportFragmentManager(), Contants.FourPart, Contants.FourPart_Name);
        mViewPager.setAdapter(mAdapterMainVP);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation_view);

        initView();
    }

    @Override
    public void onMenuItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_start:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.bottom_epg:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.bottom_series_list:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.bottom_discover:
                mViewPager.setCurrentItem(3);
                break;
            default:
        }
//        mViewPager.setCurrentItem();
    }

    @Override
    public void onMenuItemReselected() {
        Log.e(TAG, "onMenuItemSelected: ");
//        FirstFragment fragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT);
//        fragment.onReselected();
    }

    private void initView() {
        bottomNavigationView.setOnMenuItemClickListener(this);
        //设置左右单侧保存的 item数量
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setSelectedIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

   /* public static final String CURRENT_FRAGMENT = "current_fragment";

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.fade_out)
                .replace(R.id.container, fragment, CURRENT_FRAGMENT)
                .commit();
    }
    */
}
