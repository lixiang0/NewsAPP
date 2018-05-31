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
        Observable<BaseResponse<List<NewsData>>> observable = RetrofitClient.getApi().getNewsByType(type);
        return observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .flatMap(new Func1<BaseResponse<List<NewsData>>, Observable<List<NewsData>>>() {
                    @Override
                    public Observable<List<NewsData>> call(BaseResponse<List<NewsData>> listBaseResponse) {
                        return Observable.just(listBaseResponse.getData());
                    }
                });
    }
}

