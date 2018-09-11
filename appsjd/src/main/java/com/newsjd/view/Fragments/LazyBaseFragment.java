package com.newsjd.view.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class LazyBaseFragment extends Fragment {
    //懒加载
    public boolean canExcute = true;//为了onActivityCreated中setUserVisibleHint方法只执行一次
    public boolean isFirstExcute = true;//标记页面是否是第一次加载，为了初始化只执行一次

    //懒加载
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (canExcute) {//页面第一次加载时执行，以后不在执行
            canExcute = false;
            setUserVisibleHint(getUserVisibleHint());
        }
    }

    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (canExcute) {//onActivityCreated方法执行之前，不让此方法执行
            return;
        }
        if (isVisibleToUser && isFirstExcute) {// 页面可见并且是第一次加载
            isFirstExcute= false;//标记页面已经加载过一次，以后不需要在执行
            initOnceData();//把你只需要初始化一次的数据写在这个方法里面
        }
        if (getUserVisibleHint()) {// 页面每次对用户可见时执行
            initEveryTimeVisiable();
        } else {
            initEveryTimeUNVisiable();
        }
    }

    /**
     * 初始化加载，只加载一次
     */
    protected  abstract void initOnceData();

    /**
     * 每次页面可见时候，执行操作
     */
    protected  abstract void initEveryTimeVisiable();

    /**
     * 每次页面不可见时候，执行操作
     */
    protected  abstract void initEveryTimeUNVisiable();
}
