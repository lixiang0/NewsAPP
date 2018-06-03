package com.newsjd.view.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import com.newsjd.R;
import com.newsjd.config.Contants;

import net.cachapa.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnMenuItemClickListener {
    private static final  String TAG = "MainActivity";

    private ViewPager mViewPager;
    private ViewPagerAdapterMain mViewPagerAdapterMain;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.main_view_pager);

        mViewPagerAdapterMain = new ViewPagerAdapterMain(getSupportFragmentManager(), Contants.FourPart, Contants.FourPart_Name);
        mViewPager.setAdapter(mViewPagerAdapterMain);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation_view);

//        mViewPager.setOnPageChangeListener();
    }

    @Override
    public void onMenuItemSelected(MenuItem item) {
        showFragment(FirstFragment.create(item.getTitle().toString()));
        Log.e(TAG, "onMenuItemSelected: "+item.getItemId() );
//        mViewPager.setCurrentItem();
    }

    @Override
    public void onMenuItemReselected() {
        FirstFragment fragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT);
        fragment.onReselected();
    }

    public static final String CURRENT_FRAGMENT = "current_fragment";

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.fade_out)
                .replace(R.id.container, fragment, CURRENT_FRAGMENT)
                .commit();
    }
}
