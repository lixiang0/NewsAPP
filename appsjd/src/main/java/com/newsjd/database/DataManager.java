package com.newsjd.database;


import com.newsjd.base.MainApplication;
import com.newsjd.database.autocreate.DaoMaster;
import com.newsjd.database.autocreate.DaoSession;

/**
 * Created by SJD on 2017/10/19.
 *
 * @author SJD
 */

public class DataManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile DataManager mInstance = null;

    private DataManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MainApplication.context, "mdm_appinfo.db");
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                if (mInstance == null) {
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
