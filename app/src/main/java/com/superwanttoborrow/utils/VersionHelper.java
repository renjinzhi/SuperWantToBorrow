package com.superwanttoborrow.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.superwanttoborrow.App;

/**
 * @author renji
 * @date 2017/12/28
 */

public class VersionHelper {

    public static String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = App.getApplication().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(App.getApplication().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }
}
