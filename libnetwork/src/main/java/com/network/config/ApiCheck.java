package com.network.config;

import io.reactivex.Observable;
import retrofit2.http.GET;

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
