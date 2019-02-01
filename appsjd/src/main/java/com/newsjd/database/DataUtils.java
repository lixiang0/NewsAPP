package com.newsjd.database;

import android.util.Log;

import com.newsjd.database.autocreate.DataBeanDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;


/**
 * Created by SJD on 2017/10/19.
 *
 * @author SJD
 */

public class DataUtils {
    private static String TAG = "MDM MessageDataUtils";

    private static DataBeanDao databean() {
        return DataManager.getInstance().getNewSession().getDataBeanDao();
    }

    public static List<DataBean> queryAll() {
        List<DataBean> data = databean().queryBuilder().list();
        for (DataBean d : data) {
            log("queryAll", d);
        }
        return data;
    }


    /**
     * 增加
     *
     * @param databean 基础数据
     * @return 添加结果
     */
    public static boolean add(DataBean databean) {
        log("add", databean);
        try {
            databean().insert(databean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
//        insert(T entity) 当指定主键在表中存在时会发生异常
//        insertOrReplace(T entity) 当指定主键在表中存在时会覆盖数据
//        insertInTx(Iterable < T > entities) 批量插入数据
    }

    /**
     * 删除
     *
     * @param dataBean 数据
     */
    public static void del(DataBean dataBean) {
        log("del", dataBean);
        if (dataBean != null) {
            databean().delete(dataBean);
        }
//        delete(T entity) 删除数据
//        deleteByKey(K key) 指定主键删除数据
//        deleteInTx(Iterable < T > entities) 批量删除数据
//        deleteByKeyInTx(Iterable < K > keys) 批量按数据删除数据
//        deleteAll() 删除所有数据
    }

    public static void delKey(long key) {
        Log.v(TAG, "delKey:  key = " + key);
        databean().deleteByKey(key);
    }

    /**
     * 改：
     *
     * @param databean 数据
     */
    public static void update(DataBean databean) {
        log("update", databean);
        databean().update(databean);
//        update(T entity) 修改数据，
//        主键需相同
//        updateInTx(Iterable < T > entities) 批量更新数据
    }

    /**
     * 查：
     *
     * @param id id序列号
     */
    public static void find(long id) {
        Log.v(TAG, "find: id = " + id);
        databean().load(id);
//        load(K key) 根据id查找数据
//        loadByRowId( long rowId)根据行号查找数据
//        loadAll() 查找全部数据
    }

    public static List<DataBean> loadAll() {
        List<DataBean> data = databean().loadAll();
        for (DataBean d : data) {
            log("loadAll", d);
        }
        return data;
    }

    public static void log(String mothed, DataBean dataBean) {
        if (dataBean == null) {
            Log.v(TAG, mothed + ":  dataBean is null");
        } else {
            Log.v(TAG, mothed + ": " + dataBean.toString());
        }
    }

    private static void delAndCreatTable() {
        //  删表和建表
        Database database = DataManager.getInstance().getNewSession().getDatabase();
        DataBeanDao.dropTable(database, true);
        DataBeanDao.createTable(DataManager.getInstance().getNewSession().getDatabase(), true);

    }
}
