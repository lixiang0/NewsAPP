package com.network.config;

import com.network.bean.BaseResponse;
import com.network.bean.ChatMsgBean;
import com.network.bean.NewsBean;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lulei on 2017/1/3.
 * time : 9:26
 * date : 2017/1/3
 * mail to lulei4461@gmail.com
 */
public interface ApiChat {

    @FormUrlEncoded
    @POST(Constants.chatRobot)
    Observable<ChatMsgBean> chatRobot(@FieldMap Map<String, String> map);
}
