package com.superwanttoborrow.ui.safesetting;

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

public class SafeSettingPresenter extends BasePresenterImpl<SafeSettingContract.View> implements SafeSettingContract.Presenter{

    Gson gson = new Gson();
    @Override
    public void exit(Context context, String phone) {
        RequestBean loginForCodeRequestBean = new RequestBean();
        loginForCodeRequestBean.setServiceId("JUNCAI0009");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        loginForCodeRequestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(loginForCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        String message = returnBean.getMessage();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            Toast.makeText(context, "成功退出", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);
                            sharedPreferences.edit().clear().apply();
                            mView.exit();
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
