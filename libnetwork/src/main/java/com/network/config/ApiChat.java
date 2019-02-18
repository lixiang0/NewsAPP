package com.network.config;

import com.network.bean.ChatMsgBean;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
