package com.utils;

import com.network.RetrofitClient;
import com.network.bean.ChatMsgBean;
import com.network.bean.NewsBean;
import com.network.config.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by SJD
 * time: 2018/5/28
 */

public class HttpUtils {
    private static final String TAG = "NET HttpUtils";

    public static Observable<ArrayList<NewsBean>> getNewsByType(int type, int page) {
        return RetrofitClient.getApi().getNewsByType(type, page);
    }

    public static Observable<String> login(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.Login_username, username);
        hashMap.put(Constants.Login_password, password);
        return RetrofitClient.getApi().login(hashMap);
    }

    public static Observable<ChatMsgBean> chatRobot(String msg) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.chatContants, msg);
        return RetrofitClient.getApiChat().chatRobot(hashMap);
    }

    public static String getDownloadUri() {
        return Constants.getCheckHost() + Constants.check_download;
    }
//    public static Observable<String> checkDownload(Map<String, String> map) {
//        return RetrofitClient.getApiCheck().check_download(map);
//    }

    public static Observable<String> checkVersion() {
        return RetrofitClient.getApiCheck().check_version();
    }
}

