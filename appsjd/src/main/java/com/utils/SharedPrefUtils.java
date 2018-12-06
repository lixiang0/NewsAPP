package com.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.newsjd.base.MainApplication;

public class SharedPrefUtils {
    private static final String SharePrefUtils = "SharedPrefUtils";


    //提醒次数
    private static final String Check_Update_Time = "share_date";
    public static final int Check_Update_DETTime = 5;

    public static int getUpdateTime() {
        SharedPreferences sp = MainApplication.context.getSharedPreferences(SharePrefUtils, Context.MODE_PRIVATE);
        return sp.getInt(Check_Update_Time, 0);

    }

    public static void setUpdateTime(int updateTime) {
        SharedPreferences sp = MainApplication.context.getSharedPreferences(SharePrefUtils, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Check_Update_Time, updateTime);
        editor.commit();
    }
}
