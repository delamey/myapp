package com.example.delamey.myapplication5.greendao;

import android.content.Context;

public class DaoManager {
    private static DaoManager daoManager;
    private final DaoSession daoSession;

    public DaoManager(Context context) {
        this.daoSession = DaoMaster.newDevSession(context,"young_db");
    }
    public static DaoManager instance(Context context){
        if (daoManager==null){
            synchronized (DaoManager.class){
                if (daoManager==null){
                    daoManager=new DaoManager(context);
                }
            }
        }
        return daoManager;

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
