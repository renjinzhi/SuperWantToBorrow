package com.superwanttoborrow.ui.register;


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


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View, View.OnClickListener {


    private ImageView register_back;
    private TextView register_choose_title;
    private TextView register_tv_work;
    private TextView register_tv_alone;
    private TextView register_tv_free;
    private EditText register_ed_money;
    private EditText register_ed_phone;
    private EditText register_ed_code;
    private TextView register_tv_get_code;
    private EditText register_ed_password;
    private Button register_button_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_back = (ImageView) findViewById(R.id.register_back);
        register_choose_title = (TextView) findViewById(R.id.register_choose_title);
        register_tv_work = (TextView) findViewById(R.id.register_tv_work);
        register_tv_alone = (TextView) findViewById(R.id.register_tv_alone);
        register_tv_free = (TextView) findViewById(R.id.register_tv_free);
        register_ed_money = (EditText) findViewById(R.id.register_ed_money);
        register_ed_phone = (EditText) findViewById(R.id.register_ed_phone);
        register_ed_code = (EditText) findViewById(R.id.register_ed_code);
        register_tv_get_code = (TextView) findViewById(R.id.register_tv_get_code);
        register_ed_password = (EditText) findViewById(R.id.register_ed_password);
        register_button_register = (Button) findViewById(R.id.register_button_register);
        register_button_register.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String money = register_ed_money.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = register_ed_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = register_ed_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = register_ed_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码为6-12位的数字和字母组成", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button_register:

                break;
        }
    }
}
