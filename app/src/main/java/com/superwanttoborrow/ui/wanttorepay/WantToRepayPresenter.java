package com.superwanttoborrow.ui.wanttorepay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnStringBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WantToRepayPresenter extends BasePresenterImpl<WantToRepayContract.View> implements WantToRepayContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void repay(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0039");
        SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);

    }


    @Override
    public void getCode(Context context) {
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
                        ReturnStringBean returnStringBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnStringBean.getCode())) {

                        }else {
                            Toast.makeText(context,returnStringBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
