package com.utils;

import com.network.bean.NewsBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String dateFormat(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat outputFormat = new SimpleDateFormat("MM月dd日HH时mm分");
        Date date1 = null;
        try {
            date1 = inputFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outputFormat.format(date1);
    }

    //此方法仅用于参考，直接调用 无法起到替换数据的效果，必须实际执行
    public static void replace(List<NewsBean> oldData, List<NewsBean> newsData) {
        if (newsData == null) {
            oldData.clear();
        } else {
            int min = Math.min(newsData.size(), oldData.size());
            for (int i = 0; i < min; i++) {
                oldData.add(i, newsData.get(i));
            }
            if (min < newsData.size()) {
                for (int i = min; i < newsData.size(); i++) {
                    oldData.add(newsData.get(i));
                }
            } else {
                for (int i = min; i < oldData.size(); i++) {
                    oldData.remove(i);
                }
            }
        }
    }
}
