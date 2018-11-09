package com.network;

import com.network.config.Api;
import com.network.config.ApiChat;
import com.network.config.ApiCheck;
import com.network.config.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lulei on 2016/12/6.
 * time : 9:40
 * date : 2016/12/6
 * mail to lulei4461@gmail.com
 */

public class RetrofitClient {
    private static Api api;
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static ApiChat apiChat;
    private static ApiCheck apiCheck;

    public static Api getApi() {
        if (null == okHttpClient) {
            initOkhttpClient();
        }
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.getHost())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

    public static ApiChat getApiChat() {
        if (null == okHttpClient) {
            initOkhttpClient();
        }
        if (apiChat == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.getChatHost())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            apiChat = retrofit.create(ApiChat.class);
        }
        return apiChat;
    }

    public static ApiCheck getApiCheck() {
        if (null == okHttpClient) {
            initOkhttpClient();
        }
        if (apiCheck == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.getCheckHost())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            apiCheck = retrofit.create(ApiCheck.class);
        }
        return apiCheck;
    }

    private static void initOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
//                .addInterceptor(new UserAgentIntercepter())
                .build();
    }
}
