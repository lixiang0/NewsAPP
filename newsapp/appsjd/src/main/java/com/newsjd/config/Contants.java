package com.newsjd.config;

import android.content.Context;

import com.newsjd.R;
import com.newsjd.base.MainApplication;

import static com.newsjd.base.MainApplication.*;

public class Contants {

    //	焦点 国际  内地 财经 娱乐 科技 体育 本地
    public static final int Focus = 0, World = 1, Mainland = 2, Finance = 3, Entainment = 4, Technology = 5, Sport = 6, Native = 7;

    public static int[] AllItem ={0,1,2,3,4,5,6,7};

    public static String url = "http://121.42.138.77:8081/query?type=";

    public static String getItemName( int i) {
        String name;
        switch (i) {
            case 0:
                name = context.getString(R.string.focus);
                break;
            case 1:
                name = context.getString(R.string.international);
                break;
            case 2:
                name = context.getString(R.string.Inland);
                break;
            case 3:
                name = context.getString(R.string.FinanceAndEconomics);
                break;
            case 4:
                name = context.getString(R.string.entertainment);
                break;
            case 5:
                name = context.getString(R.string.technology);
                break;
            case 6:
                name = context.getString(R.string.sports);
                break;
            case 7:
                name = context.getString(R.string.local);
                break;
            default:
                name = context.getString(R.string.undefined);
        }
        return name;
    }

}
