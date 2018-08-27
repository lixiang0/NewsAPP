package com.newsjd.config;

import pub.cpp.news.R;

import static com.newsjd.base.MainApplication.*;

public class Contants {
    public static final int[] FourPart = {0, 1, 2, 3};
    public static final String[] FourPart_Name = {context.getString(R.string.news),
            context.getString(R.string.video),
            context.getString(R.string.Contacts),
            context.getString(R.string.setting)
    };


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
}
