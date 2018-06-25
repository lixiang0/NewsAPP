package com.newsjd.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.network.bean.NewsBean;
import com.network.config.Constants;
import com.newsjd.R;
import com.newsjd.config.Contants;
import com.newsjd.config.LoadingFooter;
import com.newsjd.view.Adapter.AdapterFirstRecycleList;
import com.newsjd.view.Adapter.EndlessRecyclerOnScrollListener;
import com.newsjd.view.webview.WebActivity;
import com.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FirstFragment_Item extends Fragment {
    private static final String TAG = "NEWS FirstFragment_Item";

    private RecyclerView mRecyclerView;
    private List<NewsBean> mDatas = new ArrayList<>();
    private AdapterFirstRecycleList mAdapter;

    private int position = -1;

    public FirstFragment_Item() {
//        Log.e(TAG, "FirstFragment_Item: ");
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
        initData();
    }

    private void initData() {
        HttpUtils.getNewsByType(Constants.getNewsByType + Contants.AllItem[position] + "")
                .subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<List<NewsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(List<NewsBean> newsData) {
                        // 添加数据
                        boolean hasNewData = false;
                        for (NewsBean bean : newsData) {
                            if (!mDatas.contains(bean)) {
                                mDatas.add(bean);
                                hasNewData = true;
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        if (hasNewData) {
                            setState(LoadingFooter.FooterState.Normal);
                        } else {
                            setState(LoadingFooter.FooterState.TheEnd);
                        }
                    }
                });
    }

    private void initViews(View view, final Context context) {
        Log.e(TAG, "initViews: ");
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new AdapterFirstRecycleList(context, mDatas);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        //滑动暂停加载网络图片,而且可以监听recycler是否滑动到底部
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置RecyclerView的布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                //如果是最后一个item，则设置占据3列，否则占据1列
//                boolean isFooter = position == mAdapter.getItemCount() - 1;
//                return isFooter ? 3 : 1;
//            }
//        });
        mRecyclerView.setLayoutManager(layoutManager);

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

        mAdapter.setOnItemClickListener(new AdapterFirstRecycleList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, WebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("link", mDatas.get(position).getLink());
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(context, position + " longclick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public FirstFragment_Item setPosition(int position) {
        this.position = position;
        return this;
    }


    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            if (mState == LoadingFooter.FooterState.Loading) {
                Log.d("TAG", "the state is Loading, just wait..");
                Log.d("TAG", "the state is Loading, just wait..");
                return;
            }

            if (mState == LoadingFooter.FooterState.Normal || mState != LoadingFooter.FooterState.NetWorkError) {
                // loading more
                requestData();
                Log.d("TAG", "请求数据");
            } else {
                //the end
                Log.d("TAG", "默认请求结束");
//                setState(LoadingFooter.FooterState.TheEnd);
            }
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {
        setState(LoadingFooter.FooterState.Loading);
        initData();
    }


    protected LoadingFooter.FooterState mState = LoadingFooter.FooterState.Normal;

    protected void setState(LoadingFooter.FooterState mState) {
        this.mState = mState;
        changeAdaperState();
//        ((FirstFragment_Item.this)).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//changeAdaperState();
//            }
//        });
    }

    //改变底部bottom的样式
    protected void changeAdaperState() {
        if (mAdapter != null && mAdapter.mFooterHolder != null) {
            mAdapter.mFooterHolder.setData(mState);
        }
    }
}