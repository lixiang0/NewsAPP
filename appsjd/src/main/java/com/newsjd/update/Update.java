package com.newsjd.update;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

//import com.allenliu.versionchecklib.core.http.HttpHeaders;
//import com.allenliu.versionchecklib.core.http.HttpParams;
//import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
//import com.allenliu.versionchecklib.v2.AllenVersionChecker;
//import com.allenliu.versionchecklib.v2.builder.UIData;
//import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
//import com.google.gson.Gson;

public class Update {
    public static final String TAG = "Update";
//
//    public static void aaa(Context context, String url) {
//        AllenVersionChecker.getInstance()
//                .downloadOnly(UIData.create().setDownloadUrl(url))
//                .excuteMission(context);
//    }
//
//    public static void bbb(final Context context, HttpHeaders httpHeader, HttpParams httpParam, String requestUrl) {
//        AllenVersionChecker
//                .getInstance()
//                .requestVersion()
//                .setHttpHeaders(httpHeader)
//                .setRequestMethod(HttpRequestMethod.POSTJSON)
//                .setRequestParams(httpParam)
//                .setRequestUrl(requestUrl)
//                .request(new RequestVersionListener() {
//                    @Nullable
//                    @Override
//                    public UIData onRequestVersionSuccess(String result) {
//                        //拿到服务器返回的数据，解析， 拿到downloadUrl和一些其他的UI数据
//                        UpdBean updBean = new Gson().fromJson(result, UpdBean.class);
//                        if (TextUtils.isEmpty(updBean.url)) {
//                            return null;
//                        } else {
//                            UIData uiData = UIData
//                                    .create()
//                                    .setDownloadUrl(updBean.url)
//                                    .setTitle("检测到新版本")
//                                    .setContent(updBean.forece ? "有重要版本需要升级，原版本无法再用" : "有优化版本提供，请进行更新");
//                            //放一些其他的UI参数，拿到后面自定义界面使用
//                            uiData.getVersionBundle().putString("key", "your value");
//                            return uiData;
//                        }
//                    }
//
//                    @Override
//                    public void onRequestVersionFailure(String message) {
//                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//                        Log.e(TAG, "onRequestVersionFailure: " + message);
//                    }
//                })
//                .excuteMission(context);
//    }

    class UpdBean {
        boolean forece;
        String md5;
        String url;
    }
}