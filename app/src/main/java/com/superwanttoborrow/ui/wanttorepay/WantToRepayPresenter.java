package com.superwanttoborrow.ui.wanttorepay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnStringBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.MyCountDownTimer;
import com.superwanttoborrow.utils.RandomUtil;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WantToRepayPresenter extends BasePresenterImpl<WantToRepayContract.View> implements WantToRepayContract.Presenter {

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void repay(Context context, String checkCode) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0039");
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(user);
        dataBean.setToken(token);
        dataBean.setCurrent("0");
        dataBean.setChannel("00100");
        dataBean.setCheckCode(checkCode);
        dataBean.setRemoteNo(RandomUtil.getRandom(8));
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "还款中...", true);
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
                            mView.repay();
                        }
                        Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Response<ReturnBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    public void getCode(Context context, Button button) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0048");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setCurrent("0");
        dataBean.setMobile(user);
        dataBean.setToken(token);
        dataBean.setType("repay");
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "正在获取验证码...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnStringBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnStringBean>() {
                    @Override
                    public void onSuccess(Response<ReturnStringBean> response) {
                        progressDialog.dismiss();
                        ReturnStringBean returnStringBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnStringBean.getCode())) {
                            new Thread(new MyCountDownTimer(button)).start();
                            if ("预支付成功".equals(returnStringBean.getData())) {
                                Toast.makeText(context, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, returnStringBean.getData(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, returnStringBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnStringBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void getRepay(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0027");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setRequestType("1");
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
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())){
                            mView.getRepay(returnBean.getData());
                        }else {
                            Toast.makeText(context,returnBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
