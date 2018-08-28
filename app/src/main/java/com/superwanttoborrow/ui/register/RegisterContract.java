package com.superwanttoborrow.ui.register;

import android.content.Context;
import android.widget.TextView;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterContract {

    interface View extends BaseView {

        //图片验证码
        void getImgCode(String imgCodeKey,String imgCodeString);

        //注册成功的回调
        void registerSuccess(String name,String token);
    }

    interface  Presenter extends BasePresenter<View> {

        //获取图片验证码
        void getImgCode(Context context);

        //获取注册验证码
        void getCodeRegister(Context context, String phone, String imgCode, String imgCodeKey, TextView tv);

        //注册
        void register(Context context,String phone,String code,String password);
    }
}
