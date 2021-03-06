package com.superwanttoborrow.ui.borrowrecord;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BorrowRecordContract {

    interface View extends BaseView {

        void getNoRecordBorrow();

        void getRecordBorrow(ReturnBean.DataBean dataBean);

    }

    interface  Presenter extends BasePresenter<View> {

        void getRecordBorrow(Context context);
    }
}
