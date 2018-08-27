package com.superwanttoborrow.ui.register;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterContract {

    interface View extends BaseView {

        void getImgCode(String imgCodeKey,String imgCodeString);
    }

    interface  Presenter extends BasePresenter<View> {

        void getImgCode(Context context);
        
    }
}
