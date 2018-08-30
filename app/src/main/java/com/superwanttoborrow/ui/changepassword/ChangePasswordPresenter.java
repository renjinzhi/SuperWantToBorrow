package com.superwanttoborrow.ui.changepassword;

import android.app.ProgressDialog;
import android.content.Context;
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
 * 邮箱 784787081@qq.com
 */

public class ChangePasswordPresenter extends BasePresenterImpl<ChangePasswordContract.View> implements ChangePasswordContract.Presenter {

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void change(Context context, String phone, String password_old, String password_new, String token) {
        RequestBean safetySettingRequestBean = new RequestBean();
        safetySettingRequestBean.setServiceId("JUNCAI0014");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setMobile(phone);
        dataBean.setNewpassword(password_new);
        dataBean.setOldpassword(password_old);
        dataBean.setToken(token);
        safetySettingRequestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","正在修改密码...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(safetySettingRequestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            Toast.makeText(context,"修改密码成功",Toast.LENGTH_SHORT).show();
                            mView.change();
                        }else {
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
