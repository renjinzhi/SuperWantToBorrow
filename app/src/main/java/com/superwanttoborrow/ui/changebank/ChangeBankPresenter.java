package com.superwanttoborrow.ui.changebank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.RequestEncryotionBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnBooBean;
import com.superwanttoborrow.bean.ReturnStringBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.JsonEncryption;
import com.superwanttoborrow.utils.MyCountDownTimer;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeBankPresenter extends BasePresenterImpl<ChangeBankContract.View> implements ChangeBankContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;
    @Override
    public void getBankCode(Context context,String name, String cardId, String mobile, String bankCard,String depositBank) {
        RequestEncryotionBean requestBean = new RequestEncryotionBean();
        requestBean.setServiceId("JUNCAI0041");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String token = sharedPreferences.getString("token", null);
        dataBean.setMobile(mobile);
        dataBean.setToken(token);
        dataBean.setApplicantName(name);
        dataBean.setIdCard(cardId);
        dataBean.setBankNum(bankCard);
        dataBean.setDepositBank(depositBank);
        String bankProvince = sharedPreferences.getString("bankProvince", null);
        String bankCity = sharedPreferences.getString("bankCity", null);
        String requestId = sharedPreferences.getString("requestId", null);
        dataBean.setRequestId(requestId);
        dataBean.setBankProvince(bankProvince);
        dataBean.setBankCity(bankCity);
        try {
            Map<String, String> addkey = JsonEncryption.addkey(context, gson.toJson(dataBean));
            String data = addkey.get("data");
            requestBean.setData(data);
            String encryptkey = addkey.get("encryptkey");
            requestBean.setDataKey(encryptkey);
        } catch (Exception e) {
        }
        progressDialog = ProgressDialog.show(context, "请稍等...", "提交中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBooBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBooBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBooBean> response) {
                        progressDialog.dismiss();
                        ReturnBooBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean.getCode())) {
                            mView.getBankCode(returnBean.isData());
                        }else {
                            Toast.makeText(context,returnBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnBooBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void getCode(Context context, Button tv) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0054");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        dataBean.setMobile(sharedPreferences.getString("user",null));
        dataBean.setToken(sharedPreferences.getString("token",null));
        dataBean.setCardno(sharedPreferences.getString("bankCardId",null));
        requestBean.setData(dataBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "正在获取验证码...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnStringBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnStringBean>() {
                    @Override
                    public void onSuccess(Response<ReturnStringBean> response) {
                        progressDialog.dismiss();
                        ReturnStringBean returnStringBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnStringBean.getCode())){
                            new Thread(new MyCountDownTimer(tv)).start();
                            Toast.makeText(context,returnStringBean.getData(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,returnStringBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnStringBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    public void checkCode(Context context, String code,String idCard,String bankNum,String bankName,String name) {
        RequestEncryotionBean requestBean = new RequestEncryotionBean();
        requestBean.setServiceId("JUNCAI0038");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences user = context.getSharedPreferences("User", 0);
        dataBean.setMobile(user.getString("user",null));
        dataBean.setToken(user.getString("token",null));
        dataBean.setCardNumber(bankNum);
        dataBean.setCheckCode(code);
        dataBean.setBankName(bankName);
        dataBean.setIdCard(idCard);
        dataBean.setRealName(name);
        try {
            Map<String, String> addkey = JsonEncryption.addkey(context, gson.toJson(dataBean));
            String data = addkey.get("data");
            requestBean.setData(data);
            String encryptkey = addkey.get("encryptkey");
            requestBean.setDataKey(encryptkey);
        } catch (Exception e) {
        }
        progressDialog = ProgressDialog.show(context, "请稍等...", "提交中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnStringBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnStringBean>() {
                    @Override
                    public void onSuccess(Response<ReturnStringBean> response) {
                        progressDialog.dismiss();
                        ReturnStringBean returnStringBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnStringBean.getCode())){
                            mView.getBankCode(false);
                        }else {
                            Toast.makeText(context,returnStringBean.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnStringBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    public void isBank(Context context, String bank) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0020");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences user = context.getSharedPreferences("User", 0);
        dataBean.setMobile(user.getString("user",null));
        dataBean.setToken(user.getString("token",null));
        dataBean.setCardNumber(bank);
        requestBean.setData(dataBean);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        ReturnBean returnBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnBean)){
                            mView.isBank(returnBean.getData().getBankName());
                        } else if (BeanSetHelper.CODELOGIN.equals(returnBean.getMessage())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请先登录", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
