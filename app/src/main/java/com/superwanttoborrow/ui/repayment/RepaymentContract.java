package com.superwanttoborrow.ui.repayment;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean.DataBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepaymentContract {

    interface View extends BaseView {

        void getRepayment(DataBean dataBean);

        void getNoRepayment();
    }

    interface  Presenter extends BasePresenter<View> {

        void getRepayment(Context context);
    }
}
