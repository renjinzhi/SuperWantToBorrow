package com.superwanttoborrow.ui.repayplan;

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
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepayPlanPresenter extends BasePresenterImpl<RepayPlanContract.View> implements RepayPlanContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;
    @Override
    public void getRepaymentPlan(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0024");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","正在生成还款计划...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnDataListBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnDataListBean>() {
                    @Override
                    public void onSuccess(Response<ReturnDataListBean> response) {
                        progressDialog.dismiss();
                        ReturnDataListBean returnDataListBean = response.body();
                        String message = returnDataListBean.getMessage();
                        if (BeanSetHelper.CODENULL.equals(message)){
                            mView.getNoPlan();
                        }else if (BeanSetHelper.CODELOGIN.equals(message)){
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"登录过期,请先登录",Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }else if (BeanSetHelper.CODESUCCESS.equals(returnDataListBean.getCode())){
                            mView.getPlan(returnDataListBean.getData());
                        }
                    }

                    @Override
                    public void onError(Response<ReturnDataListBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }
}
