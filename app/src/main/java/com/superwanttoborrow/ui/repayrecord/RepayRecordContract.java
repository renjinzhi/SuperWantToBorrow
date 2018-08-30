package com.superwanttoborrow.ui.repayrecord;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepayRecordContract {
    interface View extends BaseView {
        void getBorrow(List<ReturnDataListBean.DataBean> dataList);
    }

    interface  Presenter extends BasePresenter<View> {
        void getRepaymentBorrow(Context context);
    }
}
