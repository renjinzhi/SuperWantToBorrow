package com.superwanttoborrow;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }

    public static Context getApplication() {
        return context;
    }

    @Override
    public String getPackageName() {
        return super.getPackageName();
    }

}
