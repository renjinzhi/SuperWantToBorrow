package com.superwanttoborrow.ui.changepassword;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangePasswordContract {

    interface View extends BaseView {
        void change();
    }

    interface Presenter extends BasePresenter<View> {
        //修改密码
        void change(Context context, String phone, String password_old, String password_new, String token);
    }
}
