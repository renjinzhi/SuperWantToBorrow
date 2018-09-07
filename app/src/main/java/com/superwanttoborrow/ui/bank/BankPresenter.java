package com.superwanttoborrow.ui.bank;

import android.content.Context;

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

public class BankPresenter extends BasePresenterImpl<BankContract.View> implements BankContract.Presenter{

    Gson gson = new Gson();

    @Override
    public void getBank(Context context) {
        RequestBean requestBean = new RequestBean();
        requestBean.setServiceId("JUNCAI0055");
        OkGo.<ReturnDataListBean>post(Constants.HOST_URL)
                .upJson(gson.toJson(requestBean))
                .execute(new JsonCallback<ReturnDataListBean>() {
                    @Override
                    public void onSuccess(Response<ReturnDataListBean> response) {
                        ReturnDataListBean returnDataListBean = response.body();
                        if (BeanSetHelper.CODESUCCESS.equals(returnDataListBean.getCode())){
                            mView.getBank(returnDataListBean.getData());
                        }
                    }
                });
    }
}
