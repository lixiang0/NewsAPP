package com.newsjd.base;

import android.app.Application;
import android.content.Context;

import com.newsjd.config.Contants;

public class MainApplication extends Application {
    public static Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
