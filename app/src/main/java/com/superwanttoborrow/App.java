package com.superwanttoborrow;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.rong360.app.crawler.CrawlerManager;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        OkGo.getInstance().init(this);
        CrawlerManager.initSDK(this);
    }

    public static Context getApplication() {
        return context;
    }

    @Override
    public String getPackageName() {
        return super.getPackageName();
    }

}
