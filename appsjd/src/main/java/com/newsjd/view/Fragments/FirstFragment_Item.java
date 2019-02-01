package com.newsjd.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.network.bean.NewsBean;

import pub.cpp.news.R;

import com.newsjd.config.Contants;
import com.newsjd.config.LoadingFooter;
import com.newsjd.database.Data;
import com.newsjd.database.DataUtils;
import com.newsjd.view.Adapter.AdapterFirstRecycleList;
import com.newsjd.view.Adapter.EndlessRecyclerOnScrollListener;
import com.newsjd.view.webview.WebActivity;
import com.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FirstFragment_Item extends LazyBaseFragment {
    private static final String TAG = "NEWS FirstFragment_Item";

    private List<NewsBean> mDatas = new ArrayList<>();
    private AdapterFirstRecycleList mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    //新闻分类
    private int position = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initViews(view);
        return view;
    }

    @Override
    protected void initOnceData() {
        loadInitData();
    }

    @Override
    protected void initEveryTimeVisiable() {
    }

    @Override
    protected void initEveryTimeUNVisiable() {

    }

    private void initViews(View view) {
        Log.e(TAG, "initViews: ");
        Context context = view.getContext();
        mAdapter = new AdapterFirstRecycleList(getContext(), mDatas);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.fragment_first_srl);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        //滑动暂停加载网络图片,而且可以监听recycler是否滑动到底部
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置RecyclerView的布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //此处是为瀑布流准备
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
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        };
        mRecyclerView.addItemDecoration(itemDecor);//设置分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画

        mAdapter.setOnItemClickListener(new AdapterFirstRecycleList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FirstFragment_Item.this.getContext(), WebActivity.class);
                intent.putExtra("link", mDatas.get(position).getLink());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, NewsBean newsBean) {
                //TODO 收藏
//                DataUtils.
//                Toast.makeText(view.getContext(), position + " longclick", Toast.LENGTH_SHORT).show();
            }
        });

        //初始化下拉刷新
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //清除数据
                initData(true, new InitDataCallBack() {
                    @Override
                    public void onCallBack() {
                        // 取消SwipeRefreshLayout的刷新状态
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    public void loadInitData() {
        if (mDatas.size() == 0) {
            Log.e(TAG, "loadInitData: ");
            initData();
        }
    }


    private void initData() {
        initData(false, null);
    }

    int pageNum = 0;

    private void initData(final boolean clean, final InitDataCallBack initDataCallBack) {
        if (clean || mDatas.size() == 0) {
            pageNum = 0;
        } else {
            pageNum++;
        }
        Log.e(TAG, "initData:  pageNum = " + pageNum);
        HttpUtils.getNewsByType(Contants.AllItem[position], pageNum)
                .subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<List<NewsBean>>() {
                    // 添加数据
                    int dataState = 0;

                    @Override
                    public void onCompleted() {
                        if (initDataCallBack != null) {
                            initDataCallBack.onCallBack();
                        } else {
                            switch (dataState) {
                                case 0:
//                                    Toast.makeText(getContext(), R.string.latestdata, Toast.LENGTH_SHORT).show();
                                    setState(LoadingFooter.FooterState.TheEnd);
                                    break;
                                case 1:
//                                    Toast.makeText(getContext(), R.string.loaded, Toast.LENGTH_SHORT).show();
                                    setState(LoadingFooter.FooterState.Normal);
                                    break;
                                case 2:
//                                    Toast.makeText(getContext(), R.string.error_nonetwork, Toast.LENGTH_SHORT).show();
                                    setState(LoadingFooter.FooterState.NetWorkError);
                                    break;
                                default:
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        dataState = 2;
                    }

                    @Override
                    public void onNext(List<NewsBean> newsData) {
                        if (clean) {
                            mDatas.clear();
                        }
                        for (NewsBean bean : newsData) {
//                            Log.e(TAG, "onNext:  NewsBean 是否存在：" + mDatas.contains(bean));
                            if (!mDatas.contains(bean)) {
                                mDatas.add(bean);
                                dataState = 1;
                            }
                        }
                        mAdapter.notifyDataSetChanged();
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
        Log.e(TAG, "requestData: ");
        initData();
    }


    protected LoadingFooter.FooterState mState = LoadingFooter.FooterState.Normal;

    protected void setState(LoadingFooter.FooterState mState) {
        this.mState = mState;
        changeAdaperState();
    }

    //改变底部bottom的样式
    protected void changeAdaperState() {
        if (mAdapter != null && mAdapter.mFooterHolder != null) {
            mAdapter.mFooterHolder.setData(mState);
        }
    }

    private interface InitDataCallBack {
        void onCallBack();
    }
}