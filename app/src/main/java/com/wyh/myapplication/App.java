package com.wyh.myapplication;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        //注意这里是getWritableDb()
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
