package com.newsjd;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.newsjd.config.Contants;
import com.newsjd.data.NewsData;
import com.newsjd.http.NetUtils;
import com.newsjd.view.Fragments.PagerAdapterS;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.newsjd.base.MainApplication.context;


public class FirstActivty extends AppCompatActivity {

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

//        viewPager.setOffscreenPageLimit(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = NetUtils.getNetStringByGet(Contants.url + Contants.AllItem[position]);
                        if (!TextUtils.isEmpty(s)) {
                            Type type = new TypeToken<List<NewsData>>() {
                            }.getType();
                            final ArrayList<NewsData> data = new Gson().fromJson(s, type);
                            (FirstActivty.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //此时已在主线程中，可以更新UI了
                                    pagerAdapterS.getItem(position).setmDatas(data);
                                }
                            });
                        } else {
                            Toast.makeText(FirstActivty.this, "服务器返回数据为空", Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
