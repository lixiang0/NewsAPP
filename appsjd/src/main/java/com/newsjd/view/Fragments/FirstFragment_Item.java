package com.newsjd.view.Fragments;

import android.app.Activity;
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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.cpp.news.R;

import com.newsjd.config.Contants;
import com.newsjd.config.LoadingFooter;
import com.newsjd.view.Adapter.AdapterFirstRecycleList;
import com.newsjd.view.Adapter.EndlessRecyclerOnScrollListener;
import com.newsjd.view.Adapter.FirstItemUtils;
import com.newsjd.view.webview.WebActivity;
import com.newsjd.Share.umeng.ShareUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.utils.Base64BitmapUtil;

import java.util.ArrayList;
import java.util.List;

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
            public void onItemLongClick(final View view, int position, NewsBean newsBean) {
                //TODO 长按分享
                ShareAction shareAction = ShareUtils.getShareAction((Activity) view.getContext());
                shareAction = ShareUtils.setPlatform(shareAction);
                UMImage umImage = ShareUtils.getUMImage((Activity) view.getContext(), Base64BitmapUtil.base64ToBitmap(newsBean.getImg()));
                shareAction = ShareUtils.setWeb(shareAction, ShareUtils.getUMWeb(newsBean.getLink(), newsBean.getTitle(), umImage, newsBean.getText()));
                shareAction.setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onStart: share" + share_media.getName());
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onResult: 已分享");
                        Toast.makeText(view.getContext(), "已分享", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.e(TAG, "onError: share", throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onCancel: share");
                    }
                });
                shareAction.open();
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

        FirstItemUtils.getData(Contants.AllItem[position], pageNum)
                .subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<List<NewsBean>>() {
                    // 添加数据
                    int dataState = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: initData");

                    }

                    @Override
                    public void onNext(List<NewsBean> newsBeans) {
                        Log.e(TAG, "onNext: initData");
                        dataState = updateData(clean, newsBeans);
                        if (clean) {
                            pageNum = 0;
                        }
                        if (dataState != 1) {
                            pageNum--;
                        }
                        //延续rxjava1的流程， rxjava2的流程不一样
                        onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        dataState = 2;
                        pageNum--;
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: initData");
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

    public int updateData(boolean clean, List<NewsBean> newsData) {
        int dataState = 0;
        if (clean) {
            mDatas.clear();
        }
        for (NewsBean bean : newsData) {
            if (!mDatas.contains(bean)) {
                mDatas.add(bean);
                dataState = 1;
            }
        }
        mAdapter.notifyDataSetChanged();
        return dataState;
    }
}