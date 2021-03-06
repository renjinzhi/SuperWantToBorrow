package com.superwanttoborrow.ui.my;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyContract {
    interface View extends BaseView {

       void initData();
    }

    interface  Presenter extends BasePresenter<View> {

        void getUserDetails(Context context, String phone);
    }
}
