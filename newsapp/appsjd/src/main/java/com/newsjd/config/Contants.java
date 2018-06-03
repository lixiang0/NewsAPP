package com.newsjd.config;

import android.content.Context;

import com.newsjd.R;
import com.newsjd.base.MainApplication;

import static com.newsjd.base.MainApplication.*;

public class Contants {
    public static final int[] FourPart = {0, 1, 2, 3};
    public static final String[] FourPart_Name = {"新闻", "视频", "联系人", "设置"};


    //	焦点 国际  内地 财经 娱乐 科技 体育 本地
    public static int[] AllItem = {0, 1, 2, 3, 4, 5, 6, 7};
    public static String[] AllItem_Name = {context.getString(R.string.focus),
            context.getString(R.string.international),
            context.getString(R.string.Inland),
            context.getString(R.string.FinanceAndEconomics),
            context.getString(R.string.entertainment),
            context.getString(R.string.technology),
            context.getString(R.string.sports),
            context.getString(R.string.local)
    };

    public static String url = "http://121.42.138.77:8081/query?type=";
}
