package com.superwanttoborrow.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {

    Gson gson = new Gson();
    private List<ReturnDataListBean.DataBean> list;
    private double rate;
    private ProgressDialog progressDialog;


    @Override
    public void getBanner(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0007");
        OkGo.<ReturnDataListBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnDataListBean>() {
                    @Override
                    public void onSuccess(Response<ReturnDataListBean> response) {
                        ReturnDataListBean returnDataListBean = response.body();
                        String message = returnDataListBean.getMessage();
                        if (BeanSetHelper.CODESUCCESS.equals(returnDataListBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("banner", 0);
                            sharedPreferences.edit().putBoolean("banner", false).apply();
                            list = new ArrayList<>();
                            list = returnDataListBean.getData();
                            mView.getBanner(list);
                        } else {
                            Toast.makeText(context, "首页banner==>" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getOther(Context context) {
        RequestBean getHomeOtherBean = new RequestBean();
        getHomeOtherBean.setServiceId("JUNCAI0022");
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getHomeOtherBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            ArrayList<String> money = returnBean.getData().getMaxMin();
                            ArrayList<ReturnBean.DataBean.SupportPeriodBean> supportPeriod = returnBean.getData().getSupportPeriod();
                            mView.getOther(money, supportPeriod);
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getRequestID(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0036");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "请求提交中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            if ("1".equals(returnBean.getData().getStatus())) {
                                mView.getRequestID(returnBean.getData());
                            } else if ("2".equals(returnBean.getData().getStatus())) {
                                Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请先登录", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, LoginActivity.class));
                        } else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())) {
                            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, LoginActivity.class));
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }
}
