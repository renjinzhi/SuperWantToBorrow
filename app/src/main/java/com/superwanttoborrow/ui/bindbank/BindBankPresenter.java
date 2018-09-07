package com.superwanttoborrow.ui.bindbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Toast;

import com.bqs.risk.df.android.BqsDF;
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
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.utils.BeanSetHelper;
import com.superwanttoborrow.utils.JsonCallback;
import com.superwanttoborrow.utils.JsonEncryption;
import com.superwanttoborrow.utils.MyCountDownTimer;

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
    public void repayment(Context context) {
        RequestEncryotionBean requestBean = new RequestEncryotionBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
        String applicantName = sharedPreferences.getString("applicantName", null);
        String applyMoney = sharedPreferences.getString("applyMoney", null);
        String bankCardId = sharedPreferences.getString("bankCardId", null);
        String bankName = sharedPreferences.getString("bankName", null);
//        String bizType = sharedPreferences.getString("bizType", null);
//        String income = sharedPreferences.getString("income", null);
//        String bizWorkfor = sharedPreferences.getString("bizWorkfor", null);
        String cardId = sharedPreferences.getString("cardId", null);
        String linkman1Cell = sharedPreferences.getString("linkman1Cell", null).replace(" ", "");
        String linkman2Cell = sharedPreferences.getString("linkman2Cell", null).replace(" ", "");
        String linkman1Name = sharedPreferences.getString("linkman1Name", null).replace(" ", "");
        String linkman2Name = sharedPreferences.getString("linkman2Name", null).replace(" ", "");
        String linkman1Relationship = sharedPreferences.getString("linkman1Relationship", null).replace(" ", "");
        String linkman2Relationship = sharedPreferences.getString("linkman2Relationship", null).replace(" ", "");
        String incomeRange = sharedPreferences.getString("incomeRange", null).replace(" ", "");
//        String loanReason = sharedPreferences.getString("loanReason", null);
//        String house = sharedPreferences.getString("house", null);
//        String mail = sharedPreferences.getString("mail", null);
        String perAddr = sharedPreferences.getString("perAddr", null);
        String refundPeriods = sharedPreferences.getString("refundPeriods", null);
        String addres_code = sharedPreferences.getString("addres_code", null);
        String user = sharedPreferences.getString("user", null);
        String token = sharedPreferences.getString("token", null);
        String requestId = sharedPreferences.getString("requestID", null);
        String qq = sharedPreferences.getString("qq", null);
        String weiXin = sharedPreferences.getString("weiXin", null);
        requestBean.setServiceId("JUNCAI0029");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        dataBean.setToken(token);
        dataBean.setMobile(user);
        dataBean.setApplicantName(applicantName);
        dataBean.setApplyMoney(applyMoney);
        dataBean.setRequestId(requestId);
        dataBean.setBankCardId(bankCardId);
        dataBean.setDepositBank(bankName);
//        dataBean.setBizType(bizType);
//        dataBean.setMonthlyIncome(income);
//        dataBean.setBizWorkfor(bizWorkfor);
        dataBean.setCardId(cardId);
        dataBean.setLinkman1Cell(linkman1Cell);
        dataBean.setLinkman2Cell(linkman2Cell);
        dataBean.setLinkman1Name(linkman1Name);
        dataBean.setLinkman2Name(linkman2Name);
//        dataBean.setLoanReason(loanReason);
//        dataBean.setRentalSituation(house);
//        dataBean.setMail(mail);
        dataBean.setBizAddr(perAddr);
        dataBean.setRefundPeriods(refundPeriods);
        dataBean.setAddresCode(addres_code);
        dataBean.setLinkman1Relationship(linkman1Relationship);
        dataBean.setLinkman2Relationship(linkman2Relationship);
        dataBean.setIncomeRange(incomeRange);
        dataBean.setCustomId(qq);
        dataBean.setCustomName(weiXin);
        dataBean.setBqsTokenKey(BqsDF.getInstance().getTokenKey());
        dataBean.setProductionCode("00101");
        try {
            Map<String, String> addkey = JsonEncryption.addkey(context, gson.toJson(dataBean));
            String data = addkey.get("data");
            requestBean.setData(data);
            String encryptkey = addkey.get("encryptkey");
            requestBean.setDataKey(encryptkey);
        } catch (Exception e) {
        }
        String s = gson.toJson(requestBean);
        progressDialog = ProgressDialog.show(context, "请稍等...", "提交中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        OkGo.<ReturnBean>post(Constants.HOST_URL)
                .upJson(s)
                .execute(new JsonCallback<ReturnBean>() {
                    @Override
                    public void onSuccess(Response<ReturnBean> response) {
                        progressDialog.dismiss();
                        ReturnBean requestIDBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(requestIDBean.getCode())) {
                            mView.repayment();
                        } else if (BeanSetHelper.CODELOGIN.equals(requestIDBean.getCode())) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("User", 0);
                            sharedPreferences.edit().clear().apply();
                            Toast.makeText(context, "登录过期,请先登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("toFirst", true);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, requestIDBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<ReturnBean> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                    }

                });
    }

    @Override
    public void checkCode(Context context, String code) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0050");
        RequestBean.DataBean dataBean = new RequestBean.DataBean();
        SharedPreferences user = context.getSharedPreferences("User", 0);
        dataBean.setMobile(user.getString("user",null));
        dataBean.setToken(user.getString("token",null));
        dataBean.setCardno(user.getString("bankCardId",null));
        dataBean.setValidatecode(code);
        requestBean.setData(dataBean);
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
                            if ("成功".equals(returnStringBean.getData())){
                                repayment(context);
                            }else {
                                Toast.makeText(context,returnStringBean.getData(),Toast.LENGTH_SHORT).show();
                            }
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
}
