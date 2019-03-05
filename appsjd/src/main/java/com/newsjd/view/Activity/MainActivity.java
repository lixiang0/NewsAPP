package com.newsjd.view.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import pub.cpp.news.R;

import com.newsjd.config.Contants;
import com.newsjd.view.Adapter.AdapterMainVP;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.utils.CheckUpdate;
import com.utils.SharedPrefUtils;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private MainViewPager mViewPager;
    private AdapterMainVP mAdapterMainVP;

    private BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.main_view_pager);
        mAdapterMainVP = new AdapterMainVP(getSupportFragmentManager(), Contants.FourPart_Name);
        mViewPager.setAdapter(mAdapterMainVP);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation_view);
        initView();
        //检查更新
        int time = SharedPrefUtils.getUpdateTime();
        if (time > 0) {
            SharedPrefUtils.setUpdateTime(time - 1);
        } else {
            CheckUpdate.checkVersion(false);
        }
    }

    private void initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //去掉默认模式，但是在某些设备上 反射失败找不到该方法
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //设置左右单侧保存的 item数量
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: " + position + "|" + positionOffset + "|" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + position);
                bottomNavigationView.setSelectedItemId(position);
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: " + state);
            }
        });
    }

    /**
     * 底部导航栏 点击事件监听
     *
     * @param item 按键
     * @return 消费点击事件
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e(TAG, "onMenuItemSelected: ");
        menuItem = item;
        switch (item.getItemId()) {
            case R.id.bottom_start:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.bottom_epg:
                mViewPager.setCurrentItem(1);
                break;
//            case R.id.bottom_series_list:
//                mViewPager.setCurrentItem(2);
//                break;
            case R.id.bottom_discover:
                mViewPager.setCurrentItem(2);
                break;
            default:
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ 和 新浪分享的回调， fragment的话 需要在所依赖的activity中设置
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public interface FragmentSkipInterface {
        // ViewPager中子Fragment之间跳转的实现方法
        void gotoFragment(MainViewPager viewPager,AdapterMainVP adapterMainVP);
    }
    private FragmentSkipInterface mFragmentSkipInterface;

    public void setFragmentSkipInterface(FragmentSkipInterface fragmentSkipInterface) {
        mFragmentSkipInterface = fragmentSkipInterface;
    }

    // Fragment跳转
    public void skipToFragment(){
        if(mFragmentSkipInterface != null){
            mFragmentSkipInterface.gotoFragment(mViewPager,mAdapterMainVP);
        }
    }
}
