package com.superwanttoborrow.ui.changebank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeBankPresenter extends BasePresenterImpl<ChangeBankContract.View> implements ChangeBankContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;
    @Override
    public void getBankCode(Context context,String name, String cardId, String mobile, String bankCard) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0041");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(mobile);
        dataBean.setToken(token);
        dataBean.setApplicantName(name);
        dataBean.setIdCard(cardId);
        dataBean.setBankNum(bankCard);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","正在获取验证码...",true);
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
                            Toast.makeText(context,"短信验证码发送成功",Toast.LENGTH_SHORT).show();
                        } else if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"登录过期，请重新登录",Toast.LENGTH_SHORT).show();
                        } else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
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
