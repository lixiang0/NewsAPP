package com.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.maxi.chatdemo.utils.SharedPreferencesUtils;
import com.network.DownloadUtil;
import com.newsjd.base.MainApplication;

import java.io.File;

import pub.cpp.news.BuildConfig;
import pub.cpp.news.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CheckUpdate {
    private static final String TAG = "sjd CheckUpdate";

    public static void checkVersion(final Context context) {
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
                        Toast.makeText(context, "已是最新版本", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Float f) {
                        Log.e(TAG, "onNext: " + f);
                        Observable.just(f)
                                .observeOn(AndroidSchedulers.mainThread()) // 指定 doOnSubscribe 在主线程
                                .subscribe(new Action1<Float>() {
                                    @Override
                                    public void call(Float aFloat) {
                                        initDialog(context, aFloat);
                                    }
                                });
                    }
                });

//                .observeOn(AndroidSchedulers.mainThread()) // 指定 doOnSubscribe 在主线程，若没有 finallyDo 可不加，否则必须加上
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                })
    }

    /*
        初始化AlertDialog
         */
    public static void initDialog(Context context, Float aFloat) {
        //创建AlertDialog的构造器的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置构造器标题
        builder.setTitle("有新版本(" + aFloat + ")可以更新");
        //构造器对应的图标
        builder.setIcon(R.mipmap.ic_launcher);
        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
        builder.setMessage("需要体验新版本吗？");
        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
        builder.setPositiveButton("下载并安装", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloadApk();
                    }
                }).start();
            }
        });
        //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
        builder.setNegativeButton("下次再说", null);
        //为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
        builder.setNeutralButton("最近不要提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPrefUtils.setUpdateTime(SharedPrefUtils.Check_Update_DETTime);
            }
        });
        //利用构造器创建AlertDialog的对象,实现实例化
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void downloadApk() {
        Log.e(TAG, "downloadApk: " + "开始下载 " + Environment.getExternalStorageDirectory().getAbsolutePath());
        DownloadUtil downloadUtil = new DownloadUtil() {
            @Override
            public void onDownloadSuccess(File file) {
//                                Utils.showToast(FourFragment.this, "下载完成");
                Log.e(TAG, "onDownloadSuccess: ");
                install(MainApplication.context, file);
            }

            @Override
            public void onDownloading(int progress) {
//                                progressBar.setProgress(progress);
                Log.e(TAG, "onDownloading: " + progress);
            }

            @Override
            public void onDownloadFailed(File file, Exception e) {
//                                Utils.showToast(FourFragment .this, "下载失败");
                Log.e(TAG, "onDownloadFailed: ");
                if (file != null) {
                    file.delete();
                }
            }
        };

        downloadUtil.download(HttpUtils.getDownloadUri(), Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private static void install(Context mContext, File apkFile) {
        Log.i(TAG, "开始执行安装: " + apkFile.getAbsolutePath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    mContext
                    , "pub.cpp.news.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w(TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }
}
