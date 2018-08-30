package com.superwanttoborrow.ui.forgetpassword;

import android.content.Context;
import android.widget.TextView;
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
import com.superwanttoborrow.utils.MyCountDownTimer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ForgetPasswordPresenter extends BasePresenterImpl<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter{

    Gson gson = new Gson();
    @Override
    public void getCode(Context context, String phone, String imgCode, String imgCodeKey, TextView tv) {
        RequestBean getCodeRequestBean = new RequestBean();
        getCodeRequestBean.setServiceId("JUNCAI0001");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setRequestType("3");
        dataBean.setImgCode(imgCode);
        dataBean.setImgCodeKey(imgCodeKey);
        getCodeRequestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            Toast.makeText(context, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                            new Thread(new MyCountDownTimer(tv)).start();
                        }else {
                            Toast.makeText(context,returnBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void next(Context context, String phone, String code) {
        RequestBean loginForCodeRequestBean = new RequestBean();
        loginForCodeRequestBean.setServiceId("JUNCAI0006");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setCheckCode(code);
        loginForCodeRequestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(loginForCodeRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            //todo
                        }else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
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
