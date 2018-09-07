package com.superwanttoborrow.ui.progressquery;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ProgressQueryContract {

    interface View extends BaseView {

        void borrow();

        void getContract(ReturnBean.DataBean dataBean);

    }

    interface  Presenter extends BasePresenter<View> {

        void getContract(Context context);

        void borrow(Context context);

    }
}
