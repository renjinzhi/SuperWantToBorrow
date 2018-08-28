package com.superwanttoborrow.ui.login;

import android.content.Context;
import android.widget.TextView;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {

    interface View extends BaseView {

        //快速登录回调
        void LoginCodeSuccess(String token);

        //密码登录回调
        void LoginPassword(String phone,String token);

        void getImgCode(String imgCodeKey,String imgCodeString);
    }

    interface  Presenter extends BasePresenter<View> {


        //获取快速登录验证码
        void getLoginCode(Context context, String phone, String imgCode, String imgCodeKey, TextView tv);

        //快速登录
        void loginForCode(Context context,String phone,String code);

        //密码登录
        void loginForPassword(Context context,String phone,String code);

        void getImgCode(Context context);
    }
}
