package com.network.config;

import com.network.BuildConfig;
import com.network.bean.BaseResponse;
import com.network.bean.NewsData;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
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
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lulei on 2017/1/3.
 * time : 9:26
 * date : 2017/1/3
 * mail to lulei4461@gmail.com
 */
public interface Api {
    //============= API =================//
    @GET
    Observable<List<NewsData>> getNewsByType(@Url String type);

    @FormUrlEncoded
    @POST(Constants.login)
    Observable<String> login(@FieldMap Map<String, String> map);
//    http://121.42.138.77:8081/login username password


    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<NewsData>> postTokenOLD(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<String>> postToken(@FieldMap Map<String, Object> map);

    @GET(Constants.getNewsByType)
    Observable<BaseResponse<String>> getToken(@Query("serialNumber") String serialNumber);

    @POST(Constants.getNewsByType)
    Observable<BaseResponse> gateway(@Header("token") String token, @Body RequestBody requestBody);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<Boolean>> locationupload(@Header("token") String token,
                                                     @FieldMap Map<String, Double> map);


    @GET(Constants.getNewsByType)
    Observable<BaseResponse<List<NewsData>>> firmware(@Query("flag") String flag,
                                                      @Query("version") String version,
                                                      @Header("token") String token);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadsyslog(@Header("token") String token,
                                          @Path("type") Integer type,
                                          @Field("ossFile") String ossFile);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadMsgState(@Header("token") String token,
                                            @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadDetailDeviceInfo(@Header("token") String token,
                                                    @FieldMap Map<String, String> map);
}
