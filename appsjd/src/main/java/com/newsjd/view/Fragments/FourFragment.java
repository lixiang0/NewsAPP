package com.newsjd.view.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sjd.liblogin.MainActivity_login;
import com.utils.DownloadUtil;
import com.utils.HttpUtils;

import pub.cpp.news.BuildConfig;
import pub.cpp.news.R;
import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.ActionObserver;
import rx.schedulers.Schedulers;

public class FourFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "sjd fourfrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_four, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button login = view.findViewById(R.id.four_login);
        Button pay = view.findViewById(R.id.four_pay);
        Button check = view.findViewById(R.id.four_check);
        Button aboutapp = view.findViewById(R.id.four_aboutapp);
        login.setOnClickListener(this);
        pay.setOnClickListener(this);
        check.setOnClickListener(this);
        aboutapp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.four_login:
                Log.e(TAG, "onClick: button2");
                Intent intent = new Intent(getContext(), MainActivity_login.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.four_pay:
                Toast.makeText(v.getContext(), "正在开发，敬请期待", Toast.LENGTH_LONG).show();
                break;
            case R.id.four_check:
                checkVersion();
                break;
            case R.id.four_aboutapp:
                Toast.makeText(v.getContext(), "正在开发，敬请期待", Toast.LENGTH_LONG).show();
                break;
            default:
                Log.e(TAG, "onClick: " + v.getId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                break;
            default:
        }
    }

    public void checkVersion() {
        HttpUtils.checkVersion()
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Float>>() {
                    @Override
                    public Observable<Float> call(final String s) {
                        Log.d(TAG, "call: response = " + s);
                        return Observable.unsafeCreate(new Observable.OnSubscribe<Float>() {
                            @Override
                            public void call(Subscriber<? super Float> subscriber) {
                                float f = 0;
                                try {
                                    f = Float.valueOf(s);
                                } catch (Exception e) {
                                    Log.e(TAG, "call: response is error");
                                    subscriber.onError(e);
                                }
                                if (BuildConfig.VERSION_CODE < f) {
                                    subscriber.onNext(f);
                                } else {
                                    subscriber.onCompleted();
                                }
                            }
                        });
                    }
                })
                .subscribe(new Observer<Float>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                        Toast.makeText(FourFragment.this.getContext(), "已是最新版本", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Float f) {
                        Log.e(TAG, "onNext: " + f);
                        DownloadUtil.get().download(HttpUtils.getDownloadUri(), "download", new DownloadUtil.OnDownloadListener() {
                            @Override
                            public void onDownloadSuccess() {
//                                Utils.showToast(FourFragment.this, "下载完成");
                                Log.d(TAG, "onDownloadSuccess: ");
                            }

                            @Override
                            public void onDownloading(int progress) {
//                                progressBar.setProgress(progress);
                                Log.d(TAG, "onDownloading: " + progress);
                            }

                            @Override
                            public void onDownloadFailed() {
//                                Utils.showToast(FourFragment .this, "下载失败");
                                Log.d(TAG, "onDownloadFailed: ");
                            }
                        });
                    }
                })

//                .observeOn(AndroidSchedulers.mainThread()) // 指定 doOnSubscribe 在主线程，若没有 finallyDo 可不加，否则必须加上
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                })
        ;
    }
}
