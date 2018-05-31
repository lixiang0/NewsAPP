package com.newsjd.http;

import com.network.bean.NewsData;
import com.network.inface.Action;
import com.utils.HttpUtils;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

public class ResportUtils {

   /* public static Subscription getNewsByType(String type, final Action.ActionID actionID) {
        return HttpUtils.getNewsByType(type).
                subscribe(new Subscriber<List<NewsData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<NewsData> newsData) {
                        actionID.doAction(newsData);
                    }
                });
    }*/

}
