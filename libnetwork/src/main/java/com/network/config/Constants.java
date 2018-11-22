package com.network.config;

import com.network.BuildConfig;

import okhttp3.MediaType;

public class Constants {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    //======================== 按类型获取新闻数据  =================//
    public static final String getNewsByType = BuildConfig.news_quary;//?type=1&page=1

    //======================== 用户登陆接口  =================//
    public static final String login = BuildConfig.login;
    public static final String Login_username = "username";
    public static final String Login_password = "password";

    //========================   向机器人发送聊天消息  =================//
    public static final String chatRobot = BuildConfig.chatbot_chat;
    public static final String chatContants = "msg";

    //============================ 检查更新接口 ================================//
    public static final String check_download = BuildConfig.check_download;
    public static final String check_version = BuildConfig.check_version;

    //============================ 后台地址 ================================//
    // Get url.
    public static String getHost() {
        return BuildConfig.news_url;
    }

    public static String getChatHost() {
        return BuildConfig.chatbot_url;
    }

    public static String getCheckHost() {
        return BuildConfig.check_url;
    }
}
