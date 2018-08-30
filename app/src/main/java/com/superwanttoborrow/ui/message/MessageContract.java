package com.superwanttoborrow.ui.message;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessageContract {
    interface View extends BaseView {

        void getMessage(ReturnBean.DataBean dataBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void getMessage(Context context);
    }
}
