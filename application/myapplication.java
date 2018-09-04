package com.example.delamey.myapplication5.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.delamey.myapplication5.MyEventBusIndex;
import com.example.delamey.myapplication5.greendao.DaoMaster;
import com.example.delamey.myapplication5.greendao.DaoSession;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.database.Database;

import java.sql.DatabaseMetaData;

import okhttp3.OkHttpClient;

public class myapplication extends Application {
    private  static Context context;
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        initgreendao();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        Stetho.initializeWithDefaults(this);
                            new OkHttpClient.Builder()
                                    .addInterceptor(new StethoInterceptor())
                                    .build();
        Logger.addLogAdapter(new AndroidLogAdapter());

    }
    public  void  initgreendao(){
        DaoMaster.DevOpenHelper openHelper=new DaoMaster.DevOpenHelper(context,"young_db");
        Database db=openHelper.getWritableDb();
        DaoMaster daoMaster=new DaoMaster(db);
        mDaoSession=daoMaster.newSession();

    }
    public static DaoSession getDaosession(){
        return mDaoSession;
    }
    public static Context getContext(){
        return context;
    }


}
