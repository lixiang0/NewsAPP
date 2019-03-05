package com.newsjd.view.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static com.maxi.chatdemo.widget.pulltorefresh.PullToRefreshRecyclerView.logTouch;

public class MainViewPager extends ViewPager {
    private static final String TAG = "SJD";

    public MainViewPager(@NonNull Context context) {
        super(context);
    }

    public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float startX = 0;
    float startY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        logTouch("main dispatchTouchEvent:", ev);
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        logTouch("main onTouchEvent:", ev);
        return super.onTouchEvent(ev);
    }


    public boolean myOnTouch(MotionEvent ev) {
        boolean isIntercept = false;
        float nowX = 0;
        float nowY = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                nowX = startX;
                nowY = startY;
                Log.e(TAG, "onTouchEvent: down " + startX + "|" + startY);
                break;
            case MotionEvent.ACTION_MOVE:
                nowX = ev.getX();
                nowY = ev.getY();
                Log.e(TAG, "onTouchEvent: down " + nowX + "|" + nowY);
                isIntercept = Math.abs(nowX - startX) >= Math.abs(nowY - startY);
                break;
            case MotionEvent.ACTION_UP:
                nowX = ev.getX();
                nowY = ev.getY();
                Log.e(TAG, "onTouchEvent: down " + nowX + "|" + nowY);
                isIntercept = Math.abs(nowX - startX) >= Math.abs(nowY - startY);
                break;
            default:
        }

        return isIntercept;
    }
}
