package com.utils;

import com.network.RetrofitClient;
import com.network.bean.NewsBean;
import com.network.config.Constants;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by SJD
 * time: 2018/5/28
 */

public class HttpUtils {
    private static final String TAG = "NET HttpUtils";

    public static Observable<List<NewsBean>> getNewsByType(String type) {
        return RetrofitClient.getApi().getNewsByType(type);
    }

    public static Observable<String> login(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.Login_username, username);
        hashMap.put(Constants.Login_password, password);
        return RetrofitClient.getApi().login(hashMap);
    }
}

