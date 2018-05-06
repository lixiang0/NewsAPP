package com.mylibs.okhttp;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sjd on 2016/8/31.
 */
public class OkHttpResponseJsonStructure {
    public OkHttpResponseJsonStructure() {
    }

    public OkHttpResponseJsonStructure(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("result")) {
                result = jsonObject.optInt("result");
            }
            if (jsonObject.has("msg")) {
                msg = jsonObject.optString("msg");
            }
            if (jsonObject.has("cards")) {
                cards = jsonObject.optString("cards");
            }
            if (jsonObject.has("userNames")) {
                userNames = jsonObject.optString("userNames");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public OkHttpResponseJsonStructure(int result, String msg, String userNames, String cards) {
//        this.result = result;
//        this.msg = msg;
//        this.userNames = userNames;
//        this.cards = cards;
//    }

    private int result;
    private String msg;
    private String userNames;
    private String cards;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getResultType() {
        String s = "";
        switch (result) {
            case 0:
                s = "成功";
                break;
            case 1:
                s = "未知错误";
                break;
            case 2:
                s = "服务端异常";
                break;
            case 3:
                s = "数据格式错误";
                break;
        }
        return s;
    }
}
