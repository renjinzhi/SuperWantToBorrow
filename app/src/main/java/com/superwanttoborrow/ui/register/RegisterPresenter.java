package com.superwanttoborrow.ui.register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.MyCountDownTimer;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter {

    private Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void getImgCode(Context context) {

        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0046");
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.getImgCode(returnBean.getData().getImgCodeKey(), returnBean.getData().getImgCodeString());
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getCodeRegister(Context context, String phone, String imgCode, String imgCodeKey, TextView tv) {

        RequestBean getCodeRequestBean = new RequestBean();
        getCodeRequestBean.setServiceId("JUNCAI0001");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setImgCode(imgCode);
        dataBean.setImgCodeKey(imgCodeKey);
        dataBean.setRequestType("1");
        getCodeRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "正在获取验证码...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            Toast.makeText(context, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                            new Thread(new MyCountDownTimer(tv)).start();
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

    @Override
    public void register(Context context, String phone, String code, String password) {
        RequestBean registerRequestBean = new RequestBean();
        registerRequestBean.setServiceId("JUNCAI0008");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setCheckCode(code);
        dataBean.setPassword(password);
        registerRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "正在注册...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(registerRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                             @Override
                             public void onSuccess(Response<ReturnBean> response) {
                                 progressDialog.dismiss();
                                 ReturnBean returnBean = response.body();
                                 String message = returnBean.getMessage();
                                 if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                                     SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                                     sharedPreferences.edit().putBoolean("first", true).apply();
                                     sharedPreferences.edit().putBoolean("isLogin", true).apply();
                                     mView.registerSuccess(returnBean.getData().getMobile(), returnBean.getData().getToken());
                                 } else {
                                     Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onError(Response<ReturnBean> response) {
                                 super.onError(response);
                                 progressDialog.dismiss();
                             }
                         }
                );
    }
}
