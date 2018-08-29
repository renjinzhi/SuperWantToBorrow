package com.superwanttoborrow.ui.borrowrecord;

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

public class BorrowRecordPresenter extends BasePresenterImpl<BorrowRecordContract.View> implements BorrowRecordContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;


    @Override
    public void getRecordBorrow(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0021");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String token = sharedPreferences.getString("token", null);
        String phone = sharedPreferences.getString("user", null);
        dataBean.setToken(token);
        dataBean.setMobile(phone);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","查询中...",true);
        progressDialog.setCancelable(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODENULL.equals(returnBean.getCode())) {
                            mView.getNoRecordBorrow();
                        } else if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.getRecordBorrow(returnBean.getData());
                        } else if (BeanSetHelper.CODELOGIN.equals(returnBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"登录过期,请先登录",Toast.LENGTH_SHORT).show();
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
