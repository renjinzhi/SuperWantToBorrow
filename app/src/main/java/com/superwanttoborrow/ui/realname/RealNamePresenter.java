package com.superwanttoborrow.ui.realname;

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
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RealNamePresenter extends BasePresenterImpl<RealNameContract.View> implements RealNameContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void postFront(Context context, String fileContent, String fileName, String fileType, int i) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0017");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setFileContent(fileContent);
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        String requestId = sharedPreferences.getString("requestId", null);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        dataBean.setFileName(fileName);
        dataBean.setFileType(fileType);
        dataBean.setRequestId(requestId);
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "图片上传中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())){

                            switch (i){
                                case 0:
                                    Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();
                                    mView.postFront(returnBean.getData().getName(), returnBean.getData().getIdCardNumber());
                                    break;
                                case 1:
                                    Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();
                                    mView.postFront2(1);
                                    break;
                                case 2:
                                    mView.postFront2(2);
                                    break;
                            }
                        }else if (BeanSetHelper.CODELOGIN.equals(returnBean.getMessage())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请先登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("toFirst",true);
                            context.startActivity(intent);
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
