package com.superwanttoborrow.ui.message;

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
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessagePresenter extends BasePresenterImpl<MessageContract.View> implements MessageContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;
    @Override
    public void getMessage(Context context) {
        RequestBean safetySettingRequestBean = new RequestBean();
        safetySettingRequestBean.setServiceId("JUNCAI0015");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        safetySettingRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","正在获取信息列表...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(safetySettingRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.getMessage(returnBean.getData());
                        }else if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())){
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"登录过期，请重新登录",Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())||"账号不存在".equals(returnBean.getMessage())){
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, LoginActivity.class));
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
}
