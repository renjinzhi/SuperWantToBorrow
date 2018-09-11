package com.superwanttoborrow.ui.first;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.VersionHelper;

import java.io.File;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FirstPresenter extends BasePresenterImpl<FirstContract.View> implements FirstContract.Presenter {

    Gson gson = new Gson();

    @Override
    public void getVersion(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0010");
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            if (("V" + VersionHelper.getVersionName()).equals(returnBean.getData().getVersionNum())) {
                            } else {
                                mView.showUpdateDialog(returnBean.getData());
                            }
                        }
                    }
                });
    }

    @Override
    public void downLoadApk(Context context, String filePath, ProgressDialog pd) {
        OkGo.<File>get(filePath)
                .execute(new FileCallback("超享借.apk") {
                    @Override
                    public void onSuccess(Response<File> response) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(context, "com.superwanttoborrow.fileprovider", response.body());
                            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                        } else {
                            intent.setDataAndType(Uri.fromFile(response.body()), "application/vnd.android.package-archive");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        context.startActivity(intent);
                        mView.apkFinish();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        pd.setMax((int) progress.totalSize);
                        pd.setProgress((int) progress.currentSize);
                    }
                });
    }

    @Override
    public void getUserDetauls(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0012");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        requestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())) {
                            sharedPreferences.edit().clear().apply();
                        } else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())) {
                            sharedPreferences.edit().clear().apply();
                        }
                    }
                });
    }
}
