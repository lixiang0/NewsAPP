package pub.cpp.news.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pub.cpp.news.pojo.NewsItem;

/**
 * Created by xzh on 8/18/2016.
 */
public class JsonUtil {
   static String url="http://cpp.pub:8080/webnews/news?type=";



    public static List<NewsItem> getNewsList(int type){
        ArrayList<NewsItem> result=new ArrayList<NewsItem>();
        String netUrl=url+type;
        String jsonString=NetUtils.getNetStringByGet(netUrl);
        Type collectionType2 = new TypeToken<List<NewsItem>>() {
        }.getType();
        List<NewsItem> listObj = (new Gson()).fromJson(jsonString, collectionType2);
        System.out.println("converted object representation: " + listObj.size());
        return listObj;
    }
    public static final void main(String[] args){
        List<NewsItem> result=JsonUtil.getNewsList(1);
        for (NewsItem n:result){
            System.out.println(n);
        }
    }

}
