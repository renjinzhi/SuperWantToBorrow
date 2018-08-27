package com.superwanttoborrow.ui.forgetpassword;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ForgetPasswordActivity extends MVPBaseActivity<ForgetPasswordContract.View, ForgetPasswordPresenter> implements ForgetPasswordContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }
}
