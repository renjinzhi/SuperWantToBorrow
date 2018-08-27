package com.superwanttoborrow.ui.register;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.ImgCodeBean;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;

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
        OkGo.<String>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        ImgCodeBean imgCodeBean = gson.fromJson(body, ImgCodeBean.class);
                        if (BeanSetHelper.CODESUCCESS.equals(imgCodeBean.getCode())) {
                            mView.getImgCode(imgCodeBean.getData().getImgCodeKey(), imgCodeBean.getData().getImgCodeString());
                        } else {
                            Toast.makeText(context, imgCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(context,"图片验证码获取失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
