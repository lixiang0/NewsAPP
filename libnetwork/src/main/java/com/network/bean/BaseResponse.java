package com.network.bean;

/**
 * Created by Lulei on 2017/1/4.
 * time : 18:17
 * date : 2017/1/4
 * mail to lulei4461@gmail.com
 */

public class BaseResponse<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
