package com.newsjd.config;

import android.support.v4.app.Fragment;

import com.newsjd.view.Fragments.FirstFragment;
import com.newsjd.view.Fragments.FourFragment;
import com.newsjd.view.Fragments.SecondFragment;
import com.newsjd.view.Fragments.ThirdFragment;

import java.util.ArrayList;

import pub.cpp.news.R;

import static com.newsjd.base.MainApplication.*;

public class Contants {
    public static final String[] FourPart_Name = {
            context.getString(R.string.news),
            context.getString(R.string.chat),
//            context.getString(R.string.advance),
            context.getString(R.string.setting)
    };

    public static ArrayList<? super Fragment> getFragment() {
        ArrayList<? super Fragment> arrayList = new ArrayList<>();
        arrayList.add(new FirstFragment());
        arrayList.add(new SecondFragment());
//        arrayList.add(new ThirdFragment());
        arrayList.add(new FourFragment());
        return arrayList;
    }


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
