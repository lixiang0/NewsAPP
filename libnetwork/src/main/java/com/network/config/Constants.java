package com.network.config;

import com.network.BuildConfig;

import okhttp3.MediaType;

public class Constants {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    //======================== 按类型获取新闻数据  =================//
    public static final String getNewsByType = "/query";//?type=1&page=1


    //======================== 按类型获取新闻数据  =================//
    public static final String login = "/login";
    public static final String Login_username = "username";
    public static final String Login_password = "password";

    //============================ 后台地址 ================================//
    // Get url.
    public static String getHost() {
        return "http://121.42.138.77:8081/";
    }
}
