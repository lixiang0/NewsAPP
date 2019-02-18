package com.newsjd.view.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.network.bean.NewsBean;
import com.newsjd.config.Contants;
import com.newsjd.database.DataBean;
import com.newsjd.database.DataUtils;
import com.newsjd.view.Fragments.FirstFragment_Item;
import com.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FirstItemUtils {
    private static final String TAG = "FirstItemUtils";


    public static Observable<ArrayList<NewsBean>> getData(final int position, final int pageNum) {
        if (position == 7) {
            return dataFromDataBase(pageNum);
        } else {
            return dataFromNet(position, pageNum);
        }
    }

    /**
     * 本地数据 查询
     */
    @SuppressLint("CheckResult")
    private static Observable<ArrayList<NewsBean>> dataFromDataBase(final int pageNum) {
        return Observable.create(new ObservableOnSubscribe<ArrayList<NewsBean>>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<ArrayList<NewsBean>> emitter) throws Exception {
                                         List<DataBean> dataBeans = DataUtils.queryPage(pageNum);
                                         ArrayList<NewsBean> newsBeans = new ArrayList<>();
                                         for (DataBean dataBean : dataBeans) {
                                             newsBeans.add(new Gson().fromJson(dataBean.getNew_item(), NewsBean.class));
                                         }
                                         emitter.onNext(newsBeans);
//                                         emitter.onComplete();
                                     }
                                 }
        );
    }

    /**
     * 网上数据请求
     *
     * @param position 请求类型
     * @param pageNum  请求页数
     */
    private static Observable<ArrayList<NewsBean>> dataFromNet(final int position,
                                                               final int pageNum) {
        return Observable.create(new ObservableOnSubscribe<ArrayList<NewsBean>>() {
            @Override
            public void subscribe(final ObservableEmitter<ArrayList<NewsBean>> emitter) throws Exception {
                Observable<ArrayList<NewsBean>> newsByType = HttpUtils.getNewsByType(Contants.AllItem[position], pageNum);
                newsByType.subscribe(new Observer<ArrayList<NewsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        emitter.onComplete();
                    }

                    @Override
                    public void onNext(ArrayList<NewsBean> newsBeans) {
                        emitter.onNext(newsBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }
}
