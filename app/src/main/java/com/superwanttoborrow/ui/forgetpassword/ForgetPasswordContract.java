package com.superwanttoborrow.ui.forgetpassword;

import android.content.Context;
import android.widget.TextView;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ForgetPasswordContract {

    interface View extends BaseView {

        void getImgCode(String imgCodeKey,String imgCodeString);
    }

    interface  Presenter extends BasePresenter<View> {

        //发送验证码
        void getCode(Context context, String phone, String imgCode, String imgCodeKey, TextView tv);

        //下一步
        void next(Context context,String phone,String code);

        void getImgCode(Context context);
    }
}
