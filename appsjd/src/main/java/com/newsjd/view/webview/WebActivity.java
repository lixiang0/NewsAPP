package com.newsjd.view.webview;

import android.content.Intent;
import android.util.Log;

/**
 * Created by cenxiaozhong on 2017/5/22.
 * <p>
 */

public class WebActivity extends BaseWebActivity {
    private static final String TAG = "WebActivity";

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent() == null) {
            return;
        }
        String url = getIntent().getStringExtra("link");
        setUrl(url);
        Log.e(TAG, "onStart: " + getUrl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + getUrl());
        //测试Cookies
        /*try {

            String targetUrl="";
            Log.i("Info","cookies:"+ AgentWebConfig.getCookiesByUrl(targetUrl="http://www.jd.com"));
            AgentWebConfig.removeAllCookies(new ValueCallback<Boolean>() {
                @Override
                public void onReceiveValue(Boolean value) {
                    Log.i("Info","onResume():"+value);
                }
            });

            String tagInfo=AgentWebConfig.getCookiesByUrl(targetUrl);
            Log.i("Info","tag:"+tagInfo);
            AgentWebConfig.syncCookie("http://www.jd.com","ID=IDHl3NVU0N3ltZm9OWHhubHVQZW1BRThLdGhLaFc5TnVtQWd1S2g1REcwNVhTS3RXQVFBQEBFDA984906B62C444931EA0");
            String tag=AgentWebConfig.getCookiesByUrl(targetUrl);
            Log.i("Info","tag:"+tag);
            AgentWebConfig.removeSessionCookies();
            Log.i("Info","removeSessionCookies:"+AgentWebConfig.getCookiesByUrl(targetUrl));
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }
}
