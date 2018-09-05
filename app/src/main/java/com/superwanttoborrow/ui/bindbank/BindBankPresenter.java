package com.superwanttoborrow.ui.bindbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.superwanttoborrow.api.Constants;
import com.superwanttoborrow.bean.RequestBean;
import com.superwanttoborrow.bean.RequestEncryotionBean;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnBooBean;
import com.superwanttoborrow.mvp.BasePresenterImpl;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.JsonEncryption;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindBankPresenter extends BasePresenterImpl<BindBankContract.View> implements BindBankContract.Presenter{

    Gson gson = new Gson();
    private ProgressDialog progressDialog;

    @Override
    public void isReal(Context context) {
        RequestEncryotionBean requestBean = new RequestEncryotionBean();
        requestBean.setServiceId("JUNCAI0041");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String applicantName = sharedPreferences.getString("applicantName", null);
        String cardId = sharedPreferences.getString("cardId", null);
        String bankCardId = sharedPreferences.getString("bankCardId", null).replace(" ", "");
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        String depositBank = sharedPreferences.getString("depositBank", null);
        String requestId = sharedPreferences.getString("requestId", null);
        String bankProvince = sharedPreferences.getString("bankProvince", null);
        String bankCity = sharedPreferences.getString("bankCity", null);
        dataBean.setBankProvince(bankProvince);
        dataBean.setBankCity(bankCity);
        dataBean.setDepositBank(depositBank);
        dataBean.setRequestId(requestId);
        dataBean.setMobile(user);
        dataBean.setToken(token);
        dataBean.setApplicantName(applicantName);
        dataBean.setCardId(cardId);
        dataBean.setBankCardId(bankCardId);
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
                            mView.isReal(returnBean.isData());
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, returnBean.getMessage(), Toast.LENGTH_SHORT).show();
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
