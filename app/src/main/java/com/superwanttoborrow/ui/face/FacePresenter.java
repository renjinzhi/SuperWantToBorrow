package com.superwanttoborrow.ui.face;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
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

public class FacePresenter extends BasePresenterImpl<FaceContract.View> implements FaceContract.Presenter{


    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void postFace(Context context, String fileContent, String fileName, String fileType, String delta) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0017");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setFileContent(fileContent);
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        String requestID = sharedPreferences.getString("requestId", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        dataBean.setDelta(delta);
        dataBean.setFileName(fileName);
        dataBean.setFileType(fileType);
        dataBean.setRequestId(requestID);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context,"请稍等...","图片上传中...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean idCardBean = response.body();
                        String message = idCardBean.getMessage();
                        if (BeanSetHelper.CODESUCCESS.equals(idCardBean.getCode())) {
                            mView.postPace();
                        } else if (BeanSetHelper.CODELOGIN.equals(message)) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请先登录", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<ReturnBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }

                });
    }
}
