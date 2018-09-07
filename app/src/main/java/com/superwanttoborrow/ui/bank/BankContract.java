package com.superwanttoborrow.ui.bank;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BankContract {
    interface View extends BaseView {

        void getBank(List<ReturnDataListBean.DataBean> dataList);
    }

    interface  Presenter extends BasePresenter<View> {

        void getBank(Context context);
    }
}
