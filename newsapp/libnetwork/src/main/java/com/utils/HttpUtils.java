package com.utils;

import com.network.RetrofitClient;
import com.network.bean.BaseResponse;
import com.network.bean.NewsData;

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
        Observable<List<NewsData>> observable = RetrofitClient.getApi().getNewsByType(type);
        return observable;
    }
}

