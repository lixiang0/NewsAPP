package com.newsjd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public class Data {
    private static Data mData;
    private SQLiteDatabase mSqLiteDatabase;

    private static final String TABLE_NAME = "Table_News";
    private static final String TABLE_NEWS = "news";
    private static final String TABLE_URL = "url";

    public static Data getInstance() {
        if (mData == null) {
            mData = new Data();
        }
        return mData;
    }

    private SQLiteDatabase.CursorFactory mFactory;
    private DatabaseErrorHandler mErrorHandler;

    private Data() {
        mFactory = new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String s, SQLiteQuery sqLiteQuery) {
                return null;
            }
        };
        mErrorHandler = new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase sqLiteDatabase) {

            }
        };
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase("news.db", mFactory, mErrorHandler);
        createTable(mSqLiteDatabase);
    }

    private void createTable(SQLiteDatabase db) {
        //创建表SQL语句
        String stu_table = "create table if not exists " + TABLE_NAME + " (_id integer primary key autoincrement," + TABLE_NEWS + " text," + TABLE_URL + " text)";
        //执行SQL语句
        db.execSQL(stu_table);
    }

    public void quary(String url){

    }

    private void insert(SQLiteDatabase db, String text, String url) {
        //实例化常量值
        ContentValues cValue = new ContentValues();
        //添加用户名
        cValue.put(TABLE_NEWS, text);
        //添加密码
        cValue.put(TABLE_URL, url);
        //调用insert()方法插入数据
        db.insert(TABLE_NAME, null, cValue);
    }
}
