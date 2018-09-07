package com.superwanttoborrow.ui.changepassword;


import android.content.SharedPreferences;
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
import com.superwanttoborrow.utils.MyTextUtils;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangePasswordActivity extends MVPBaseActivity<ChangePasswordContract.View, ChangePasswordPresenter> implements ChangePasswordContract.View, View.OnClickListener {


    private ImageView cp_back;
    private TextView cp_tv_phone;
    private EditText cp_ed_pass_old;
    private EditText cp_ed_pass_new;
    private EditText cp_ed_pass_new_again;
    private Button cp_button;
    private String phone;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        initData();
    }


    private void initView() {
        cp_back = (ImageView) findViewById(R.id.cp_back);
        cp_back.setOnClickListener(view -> finish());
        cp_tv_phone = (TextView) findViewById(R.id.cp_tv_phone);
        cp_ed_pass_old = (EditText) findViewById(R.id.cp_ed_pass_old);
        cp_ed_pass_new = (EditText) findViewById(R.id.cp_ed_pass_new);
        cp_ed_pass_new_again = (EditText) findViewById(R.id.cp_ed_pass_new_again);
        cp_button = (Button) findViewById(R.id.cp_button);
        cp_button.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        phone = sharedPreferences.getString("user", null);
        token = sharedPreferences.getString("token", null);
        if (!TextUtils.isEmpty(phone)) {
            cp_tv_phone.setText(phone);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cp_button:
                String pass_old = cp_ed_pass_old.getText().toString();
                String pass_new = cp_ed_pass_new.getText().toString();
                String pass_new2 = cp_ed_pass_new_again.getText().toString();
                if (TextUtils.isEmpty(pass_old)) {
                    Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show();
                } else if (pass_old.length() < 6) {
                    Toast.makeText(this, "密码长度不得小于6", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass_new)) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                } else if (pass_new.length() < 6) {
                    Toast.makeText(this, "密码长度不得小于6", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass_new2)) {
                    Toast.makeText(this, "请重复新密码", Toast.LENGTH_SHORT).show();
                } else if (!pass_new.equals(pass_new2)) {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                } else if (!MyTextUtils.isPassword(pass_new2)) {
                    Toast.makeText(this, "密码请输入数字及字母组合", Toast.LENGTH_SHORT).show();
                } else if (pass_old.equals(pass_new)) {
                    Toast.makeText(this, "新密码不能与旧密码一致", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.change(this, phone, pass_old, pass_new, token);
                }
                break;
        }
    }

    @Override
    public void change() {
        finish();
    }
}
