package com.utils;

import android.util.Log;

import com.network.RetrofitClient;
import com.network.bean.BaseResponse;
import com.network.bean.NewsData;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by SJD
 * time: 2018/5/28
 */

public class HttpUtils {
    private static final String TAG = "NET HttpUtils";

    public static Observable<List<NewsData>> getNewsByType(String type) {
        return RetrofitClient.getApi().getNewsByType(type);
    }

    public static Observable<String> login(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        return RetrofitClient.getApi().login(hashMap);
    }
}

