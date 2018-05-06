package com.mylibs.okhttp;

/**
 * Created by sjd on 2017/2/10.
 */

public class OkHttpContent {
    public static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD9i2guZO9ZJNNZjQl2ZB+2G++uEUl+k\n" +
            "2GLkLxXr6Wlokf35uBTK7Qr1slVxt8zgXxIgCgc8zoQL/r2YzMHJmObF/8Q6lkEYY3XCi\n" +
            "LZuZHQB783G27MQh+/UxKEi662t5UyLFNOSm+f0t975jRPLVIoY6UmAUhsFhW5EYfPS9a\n" +
            "fpQIDAQAB";

    public static String TempTestLockId = "123456789000003";

    public static String DoorServiceUrl = "http://120.76.195.87:8080/lockapi/lock/";

    public static String lockStatus = "4001?"; //推送门锁状态
    //请求Request  ：lockId / masterLock / lock
    //响应Response ：result / msg

    public static String openDoorRecord = "4002?";//推送智能锁开门记录
    //请求Request  ：lockId / userName / type / time      (type类型：app 0 ; 密码 1 ； 临时密码 2 ；门卡 3 ； 钥匙 4)
    //响应Response ：result / msg

    public static String tempPassword = "4003?";//推送临时密码
    //请求Request  ：lockId / password
    //响应Response ：result / msg


    public static String addUser = "4005?";//添加成员
    //请求Request  ：lockId / userName
    //响应Response ：result / msg

    public static String deleteUser = "4006?";//删除成员
    //请求Request  ：lockId
    //响应Response ：result / msg / userNames

    public static String deleteUserConfirm = "4009?";//删除成员确认
    //请求Request  ：lockId / userNames
    //响应Response ：result / msg


    public static String addCard = "4007?";//添加门卡
    //请求Request  ：lockId / cardId / cardName
    //响应Response ：result / msg

    public static String deleteCard = "4008?";//删除门卡
    //请求Request  ：lockId
    //响应Response ：result / msg / cards

    public static String deleteCardConfirm = "4010?";//删除门卡确认
    //请求Request  ：lockId / cardIds
    //响应Response ：result / msg


    public static String activationDoorManager = "4011?";//激活门锁管理员
    //请求Request  ：lockId / userName
    //响应Response ：result / msg

    public static String resetDoorAllInfo = "4012?";//重置智能锁用户信息
    //请求Request  ：lockId
    //响应Response ：result / msg


    public static String lockId = "lockId";  //智能锁 id
    public static String masterLock = "masterLock";//主锁状态  int
    public static String lock = "lock";//反锁状态  int
    public static String result = "result";//响应状态  int
    public static String msg = "msg";//响应描述
    public static String userName = "userName";//成员用户名
    public static String type = "type";//开门方式 int  app 0 ; 密码 1 ； 临时密码 2 ；门卡 3 ； 钥匙 4 ；
    public static String time = "time";//开门时间
    public static String password = "password";//访客模式临时密码
    public static String userNames = "userNames";// JSON 数组
    public static String cardId = "cardId"; //门卡 Id
    public static String cardName = "cardName";//门卡名称
    public static String cards = "cards"; //JSON 数组
    public static String cardIds = "cardIds";//JSON 数组
}
