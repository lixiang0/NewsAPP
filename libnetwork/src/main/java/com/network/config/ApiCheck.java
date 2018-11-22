package com.network.config;

import com.network.bean.ChatMsgBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Lulei on 2017/1/3.
 * time : 9:26
 * date : 2017/1/3
 * mail to lulei4461@gmail.com
 */
public interface ApiCheck {

//    @FormUrlEncoded
//    @POST(Constants.check_download)
//    Observable<String> check_download(@FieldMap Map<String, String> map);

    @GET(Constants.check_version)
    Observable<String> check_version();
}
