package com.superwanttoborrow.ui.repayrecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepayRecordPresenter extends BasePresenterImpl<RepayRecordContract.View> implements RepayRecordContract.Presenter{

    Gson gson = new Gson();
    @Override
    public void getRepaymentBorrow(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0026");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        requestBean.setData(dataBean);
        OkGo.<ReturnDataListBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnDataListBean>() {
                    @Override
                    public void onSuccess(Response<ReturnDataListBean> response) {
                        ReturnDataListBean recordRepaymentBean = response.body();
                        String message = recordRepaymentBean.getCode();
                        if (BeanSetHelper.CODESUCCESS.equals(message)) {
                            mView.getBorrow(recordRepaymentBean.getData());
                        }else if (BeanSetHelper.CODELOGIN.equals(message)){
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User",0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context,"登录过期,请先登录",Toast.LENGTH_SHORT).show();
                        }else if (BeanSetHelper.CODENULL.equals(message)){
                            Toast.makeText(context,"暂时还没有还款记录",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
