package com.newsjd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.network.bean.NewsData;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test() {
        String s = "[{\"time\": \"Fri, 08 Dec 2017 06:13:00 GMT\", \"category\": \"1\", \"title\": \"\\u5185\\u8499\\u53e424\\u4e2a\\u9879\\u76ee\\u5165\\u9009\\u201c2017\\u5168\\u56fd\\u4f18\\u9009\\u65c5\\u6e38\\u9879\\u76ee\\u201d - \\u65b0\\u6d6a\\u7f51\", \"img\": \"t0.gstatic.com/images?q\\\\u003dtbn:ANd9GcRJc023FTqF3wtzBsmcYiikyr7kKQeY9L7FPu7CUO-WcIU6f9aH7_dO-z5LX-hJgD700LZz86QF\", \"id\": 1, \"text\": \"\\u5728\\u8fd1\\u65e5\\u53ec\\u5f00\\u76842017\\u5e74\\u5168\\u56fd\\u65c5\\u6e38\\u6295\\u878d\\u8d44\\u4fc3\\u8fdb\\u5927\\u4f1a\\u4e0a\\uff0c\\u56fd\\u5bb6\\u65c5\\u6e38\\u5c40\\u4f1a\\u540c\\u56fd\\u5bb6\\u5f00\\u53d1\\u94f6\\u884c\\u7b4912\\u5bb6\\u91d1\\u878d\\u673a\\u6784\\u7efc\\u5408\\u8003\\u8651\\u9879\\u76ee\\u6210\\u719f\\u5ea6\\u3001\\u5f00\\u5de5\\u6761\\u4ef6\\u3001\\u5e02\\u573a\\u524d\\u666f\\u3001\\u793a\\u8303\\u5f15\\u9886\\u548c\\u5e26\\u52a8\\u4f5c\\u7528\\uff0c\\u5171\\u540c\\u9074\\u9009\\u63a8\\u51fa\\u4e86\\u5168\\u56fd680\\u4e2a\\u4f18\\u9009\\u65c5\\u6e38\\u9879\\u76ee\\uff0c\\u4e3b\\u8981\\u5305\\u62ec\\u666f\\u533a\\u63d0\\u5347\\u6539\\u9020 ...\", \"link\": \"http://news.sina.com.cn/c/2017-12-08/doc-ifypnqvn1474616.shtml\"}]";
        Type t = new TypeToken<LinkedList<NewsData>>() {
        }.getType();
        List<NewsData> newsData = new Gson().fromJson(s, t);

    }

}