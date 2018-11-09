package com.newsjd.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.maxi.chatdemo.db.base.BaseManager;

public class MainApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化chatdemo的数据库
        BaseManager.initOpenHelper(this);
    }
}
