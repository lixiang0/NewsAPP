package com.network.config;

import com.network.BuildConfig;

import okhttp3.MediaType;

public class Constants {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    //======================== 按类型获取新闻数据  =================//
    public static final String getNewsByType = "/query?type=";


    //======================== 按类型获取新闻数据  =================//
    public static final String login = "/login";


    //============================ 后台地址 ================================//
    // Get url.
    public static String getHost() {
        if (BuildConfig.DEBUG) {
            return BuildConfig.API_HOST_DEBUG;
        } else {
            return BuildConfig.API_HOST_RELEASE;
        }
    }
}
