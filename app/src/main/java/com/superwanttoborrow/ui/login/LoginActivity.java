package com.superwanttoborrow.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.forgetpassword.ForgetPasswordActivity;
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
    private ImageView login_img_password_vis;
    private Group login_group_code;
    private Group login_group_password;
    private EditText login_ed_code;
    private TextView login_tv_get_code;
    private Boolean isPassword = true;

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
        login_img_password_vis = (ImageView) findViewById(R.id.login_img_password_vis);
        login_img_password_vis.setOnClickListener(this);
        login_group_code = (Group) findViewById(R.id.login_group_code);
        login_group_password = (Group) findViewById(R.id.login_group_password);
        login_ed_code = (EditText) findViewById(R.id.login_ed_code);
        login_tv_get_code = (TextView) findViewById(R.id.login_tv_get_code);
        login_tv_get_code.setOnClickListener(this);
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
                switch (login_tv_quick.getText().toString()){
                    case "快速登录":
                        isPassword = false;
                        login_tv_quick.setText("密码登录");
                        login_group_password.setVisibility(View.INVISIBLE);
                        login_group_code.setVisibility(View.VISIBLE);
                        break;
                    case "密码登录":
                        isPassword = true;
                        login_tv_quick.setText("快速登录");
                        login_group_password.setVisibility(View.VISIBLE);
                        login_group_code.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case R.id.login_tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
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

        String code = login_ed_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
