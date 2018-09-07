package com.superwanttoborrow.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bqs.risk.df.android.BqsDF;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.RequestBean.DataBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.MyCountDownTimer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void getLoginCode(Context context, String phone, String imgCode, String imgCodeKey, TextView tv) {
        RequestBean getCodeRequestBean = new RequestBean();
        getCodeRequestBean.setServiceId("JUNCAI0001");
        DataBean dataBean = new DataBean();
        dataBean.setMobile(phone);
        dataBean.setRequestType("2");
        dataBean.setImgCode(imgCode);
        dataBean.setImgCodeKey(imgCodeKey);
        getCodeRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","正在发送验证码...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                            new Thread(new MyCountDownTimer(tv)).start();
                        }else {
                            Toast.makeText(context,returnBean.getMessage(),Toast.LENGTH_SHORT).show();
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
    public void loginForCode(Context context, String phone, String code) {
        RequestBean loginForCodeRequestBean = new RequestBean();
        loginForCodeRequestBean.setServiceId("JUNCAI0002");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        String bqsTokenKey = BqsDF.getInstance().getTokenKey();
        dataBean.setBqsTokenKey(bqsTokenKey);
        dataBean.setMobile(phone);
        dataBean.setCheckCode(code);
        loginForCodeRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","登录中...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(loginForCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.LoginCodeSuccess(returnBean.getData().getToken());
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
    public void loginForPassword(Context context, String phone, String code) {
        RequestBean loginForCodeRequestBean = new RequestBean();
        loginForCodeRequestBean.setServiceId("JUNCAI0003");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        String bqsTokenKey = BqsDF.getInstance().getTokenKey();
        dataBean.setBqsTokenKey(bqsTokenKey);
        dataBean.setMobile(phone);
        dataBean.setPassword(code);
        loginForCodeRequestBean.setData(dataBean);
        Log.i("bqsTokenKey",dataBean.getBqsTokenKey()+"...");
        progressDialog = ProgressDialog.show(context,"请稍等...","登录中...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(loginForCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        String message = returnBean.getMessage();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.LoginPassword(returnBean.getData().getMobile(), returnBean.getData().getToken());
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
    public void getImgCode(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0046");
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())){
                            mView.getImgCode(returnBean.getData().getImgCodeKey(),returnBean.getData().getImgCodeString());
                        }else {
                            Toast.makeText(context,returnBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
