package com.superwanttoborrow.ui.register;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.ImgCodeBean;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.MyCountDownTimer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{

    private Gson gson = new Gson();

    @Override
    public void getImgCode(Context context) {

        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0046");
        OkGo.<ImgCodeBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ImgCodeBean>() {
                    @Override
                    public void onSuccess(Response<ImgCodeBean> response) {
                        ImgCodeBean imgCodeBean  = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(imgCodeBean.getCode())) {
                            mView.getImgCode(imgCodeBean.getData().getImgCodeKey(), imgCodeBean.getData().getImgCodeString());
                        } else {
                            Toast.makeText(context, imgCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getCodeRegister(Context context, String phone, String imgCode, String imgCodeKey, TextView tv) {

        RequestBean getCodeRequestBean = new RequestBean();
        getCodeRequestBean.setServiceId("JUNCAI0001");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setImgCode(imgCode);
        dataBean.setImgCodeKey(imgCodeKey);
        dataBean.setRequestType("1");
        getCodeRequestBean.setData(dataBean);
        OkGo.<ImgCodeBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(getCodeRequestBean))
                .execute(new JsonCallback<ImgCodeBean>() {
                    @Override
                    public void onSuccess(Response<ImgCodeBean> response) {
                        ImgCodeBean imgCodeBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(imgCodeBean.getCode())) {
                            Toast.makeText(context, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                            new Thread(new MyCountDownTimer(tv)).start();
                        }else {
                            Toast.makeText(context,imgCodeBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void register(Context context, String phone, String code, String password) {
        RequestBean registerRequestBean = new RequestBean();
        registerRequestBean.setServiceId("JUNCAI0008");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setCheckCode(code);
        dataBean.setPassword(password);
        registerRequestBean.setData(dataBean);

    }
}
