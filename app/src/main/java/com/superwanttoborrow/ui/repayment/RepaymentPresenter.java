package com.superwanttoborrow.ui.repayment;

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

public class RepaymentPresenter extends BasePresenterImpl<RepaymentContract.View> implements RepaymentContract.Presenter{

    Gson gson = new Gson();
    @Override
    public void getRepayment(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0027");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
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
                        String message = returnBean.getCode();
                        if (BeanSetHelper.CODELOGIN.equals(message)) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请重新登录", Toast.LENGTH_SHORT).show();
                        } else if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.getRepayment(returnBean.getData());
                        } else if (BeanSetHelper.CODENULL.equals(returnBean.getCode())) {
                            mView.getNoRepayment();
                        } else if ("010".equals(returnBean.getCode())) {
                            mView.getNoRepayment();
                        } else if (BeanSetHelper.CODENEEDLOGIN.equals(returnBean.getCode())) {
//                            Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
                            mView.getNoRepayment();
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
