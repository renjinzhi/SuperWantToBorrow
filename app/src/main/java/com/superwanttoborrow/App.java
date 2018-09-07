package com.superwanttoborrow;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.rong360.app.crawler.CrawlerManager;
import com.umeng.commonsdk.UMConfigure;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        OkGo.getInstance().init(this);
        CrawlerManager.initSDK(this);
        UMConfigure.init(context,UMConfigure.DEVICE_TYPE_PHONE,null);
    }

    public static Context getApplication() {
        return context;
    }

    @Override
    public String getPackageName() {
        return super.getPackageName();
    }

}
