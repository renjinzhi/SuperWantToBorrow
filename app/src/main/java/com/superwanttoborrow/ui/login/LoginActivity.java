package com.superwanttoborrow.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.register.RegisterActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private ImageView login_back;
    private EditText login_ed_phone;
    private EditText login_ed_password;
    private Button login_button_login;
    private TextView login_tv_register;
    private TextView login_tv_quick;
    private TextView login_tv_forget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        login_back = (ImageView) findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        login_ed_phone = (EditText) findViewById(R.id.login_ed_phone);
        login_ed_password = (EditText) findViewById(R.id.login_ed_password);
        login_button_login = (Button) findViewById(R.id.login_button_login);
        login_button_login.setOnClickListener(this);
        login_tv_register = (TextView) findViewById(R.id.login_tv_register);
        login_tv_register.setOnClickListener(this);
        login_tv_quick = (TextView) findViewById(R.id.login_tv_quick);
        login_tv_quick.setOnClickListener(this);
        login_tv_forget = (TextView) findViewById(R.id.login_tv_forget);
        login_tv_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_tv_quick:
                break;
            case R.id.login_tv_forget:
                break;
            case R.id.login_button_login:

                break;
        }
    }

    private void submit() {
        // validate
        String phone = login_ed_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = login_ed_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
