package com.newsjd.view.Fragments.First;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.network.bean.NewsData;
import com.network.config.Constants;
import com.newsjd.R;
import com.newsjd.config.Contants;
import com.newsjd.view.RecycleView.RecycleListAdapter;
import com.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PageFragmentS extends Fragment {
    private static final String TAG = "NEWS PageFragmentS";

    private RecyclerView mRecyclerView;
    private List<NewsData> mDatas = new ArrayList<>();
    private RecycleListAdapter mAdapter;

    private int position = -1;

    public PageFragmentS() {
//        Log.e(TAG, "PageFragmentS: ");
//        initDatas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initViews(view, view.getContext());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + position);
        HttpUtils.getNewsByType(Constants.getNewsByType + Contants.AllItem[position] + "")
                .subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<List<NewsData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(List<NewsData> newsData) {
                        if (newsData == null) {
                            mDatas.clear();
                        } else {
                            int min = Math.min(newsData.size(), mDatas.size());
                            for (int i = 0; i < min; i++) {
                                mDatas.add(i, newsData.get(i));
                            }
                            if (min < newsData.size()){
                                for (int i = min; i < newsData.size(); i++) {
                                    mDatas.add(newsData.get(i));
                                }
                            }else {
                                for (int i = min; i < mDatas.size(); i++) {
                                    mDatas.remove(i);
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void initDatas() {
        Log.e(TAG, "initDatas: ");
        for (int i = 'A'; i <= 'A'; i++) {
            mDatas.add(new NewsData("Fri, 08 Dec 2017 06:13:00 GMT",
                    "1",
                    "aaaaaaaaaaaaa",
                    "t0.gstatic.com/images?q\\u003dtbn:ANd9GcRJc023FTqF3wtzBsmcYiikyr7kKQeY9L7FPu7CUO-WcIU6f9aH7_dO-z5LX-hJgD700LZz86QF",
                    1,
                    "  aaa " + i,
                    "http://news.sina.com.cn/c/2017-12-08/doc-ifypnqvn1474616.shtml"));
        }
    }

    private void initViews(View view, final Context context) {
        Log.e(TAG, "initViews: ");
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new RecycleListAdapter(context, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView的布局管理
        LinearLayoutManager linearLaoutMnager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLaoutMnager);

        //设置RecyclerView的Item之间分割线
        RecyclerView.ItemDecoration itemDecor = new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        };
        mRecyclerView.addItemDecoration(itemDecor);//设置分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画

        mAdapter.setOnItemClickListener(new RecycleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, position + " longclick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public PageFragmentS setPosition(int position) {
        this.position = position;
        return this;
    }
}