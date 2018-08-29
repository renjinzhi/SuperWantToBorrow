package com.superwanttoborrow.ui.my;

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

public class MyPresenter extends BasePresenterImpl<MyContract.View> implements MyContract.Presenter{


    Gson gson = new Gson();
    @Override
    public void getUserDetails(Context context, String phone) {
        RequestBean getCodeRequestBean = new RequestBean();
        getCodeRequestBean.setServiceId("JUNCAI0012");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String token = sharedPreferences.getString("token", null);
        dataBean.setToken(token);
        getCodeRequestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                        } else if ("952".equals(returnBean.getCode())) {
                        } else if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                        } else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        mView.initData();
                    }
                });
    }
}
