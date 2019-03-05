package com.maxi.chatdemo.widget.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Mao Jiqing on 2016/9/27.
 */
public class PullToRefreshRecyclerView extends RecyclerView {
    private static final String TAG = "SJD";
    boolean allowDragBottom = true;
    float downY = 0;
    boolean needConsumeTouch = true;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    float startX = 0;
    float startY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logTouch("pull dispatchTouchEvent:", ev);

        boolean isIntercept = false;
        float nowX = 0;
        float nowY = 0;

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getRawY();
            startX = ev.getX();
            startY = ev.getY();
            nowX = startX;
            nowY = startY;
            needConsumeTouch = true;
            if (getMyScrollY() == 0) {
                allowDragBottom = true;
            } else {
                allowDragBottom = false;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            nowX = ev.getX();
            nowY = ev.getY();
            isIntercept = Math.abs(nowX - startX) >= Math.abs(nowY - startY);

            if (isIntercept || !needConsumeTouch) {
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            } else if (allowDragBottom) {
                if (downY - ev.getRawY() < -2) {
                    needConsumeTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }
        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        logTouch("pull ontouch", e);
        return super.onTouchEvent(e);
    }

    public static void logTouch(String c, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("SJD", c + ": ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("SJD", c + ": ACTION_UP");
                break;
            default:
        }
    }

    public int getMyScrollY() {
        View c = getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
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
                isIntercept = Math.abs(nowX - startX) >= Math.abs(nowY - startY);
                Log.e(TAG, "onTouchEvent: down " + nowX + "|" + nowY);
                break;
            case MotionEvent.ACTION_UP:
                nowX = ev.getX();
                nowY = ev.getY();
                isIntercept = Math.abs(nowX - startX) >= Math.abs(nowY - startY);
                Log.e(TAG, "onTouchEvent: down " + nowX + "|" + nowY);
                break;
            default:
        }

        return isIntercept;
    }
}