package com.network.inface;

import com.network.bean.BaseResponse;
import com.network.bean.NewsData;

import java.util.List;

import rx.Observable;

/**
 * Created by SJD
 * time: 2018/5/29
 */

public class Action {
    public interface ActionID {
        void doAction(List<NewsData> newsData);
    }

}
