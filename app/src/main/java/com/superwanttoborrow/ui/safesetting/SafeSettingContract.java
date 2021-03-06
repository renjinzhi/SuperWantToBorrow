package com.superwanttoborrow.ui.safesetting;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SafeSettingContract {

    interface View extends BaseView {

        void exit();
    }

    interface  Presenter extends BasePresenter<View> {

        void exit(Context context, String phone);
    }
}
