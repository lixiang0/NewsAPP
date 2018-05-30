package com.newsjd.view.Fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.newsjd.R;
import com.newsjd.config.Contants;
import com.newsjd.data.NewsData;
import com.newsjd.http.NetUtils;
import com.newsjd.view.RecycleView.RecycleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageFragmentS extends Fragment {
    private static  final String TAG = "NEWS PageFragmentS";

    private RecyclerView mRecyclerView;
    private List<NewsData> mDatas;
    private RecycleListAdapter mAdapter;

    public PageFragmentS() {
        Log.e(TAG, "PageFragmentS: " );
        initDatas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initViews(view, view.getContext());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    private void initDatas() {
        Log.e(TAG, "initDatas: " );
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'F'; i++) {
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
        Log.e(TAG, "initViews: " );
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

    public RecycleListAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecycleListAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public List<NewsData> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<NewsData> mDatas) {
        Log.e(TAG, "setmDatas: " );
        this.mDatas = mDatas;
        mAdapter.notifyDataSetChanged();
    }

    public void setmDatas(int i, NewsData newsData) {
        int size = mDatas.size();
        if (i < 0 || i >= size) {
            mDatas.add(newsData);
            i = size;
        } else {
            mDatas.set(i, newsData);
        }
        mAdapter.notifyItemChanged(i);
    }
}