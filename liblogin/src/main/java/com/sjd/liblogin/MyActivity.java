package com.sjd.liblogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by jzl on 2016/11/24.
 */

public class MyActivity extends BaseActivity {
    TextView pulse;
    TextView go_web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getSupportActionBar().hide();

        pulse = (TextView) findViewById(R.id.player);
        go_web = (TextView) findViewById(R.id.go_web);


    }

    //设置再按一次结束程序
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

