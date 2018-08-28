package com.superwanttoborrow.ui.login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.first.FirstActivity;
import com.superwanttoborrow.ui.forgetpassword.ForgetPasswordActivity;
import com.superwanttoborrow.ui.register.RegisterActivity;
import com.superwanttoborrow.utils.Bitmap2Base64;
import com.superwanttoborrow.utils.MyTextUtils;
import com.superwanttoborrow.utils.PhoneNumberCheck;


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
    private CheckBox login_img_password_vis;
    private Group login_group_code;
    private Group login_group_password;
    private EditText login_ed_code;
    private TextView login_tv_get_code;
    private Boolean isPassword = true;
    private Button login_button_login_query;
    private TextView login_tv_register_query;
    private TextView login_tv_query;

    private boolean toFirst;
    private String phone;
    private String code;
    private String imgCode;
    private SharedPreferences sp;
    private ImageView login_img_img_code;
    private String imgCodeKey;
    private EditText login_ed_img_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        toFirst = intent.getBooleanExtra("toFirst", false);

        login_back = (ImageView) findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        login_ed_phone = (EditText) findViewById(R.id.login_ed_phone);
        login_ed_password = (EditText) findViewById(R.id.login_ed_password);
        login_button_login = (Button) findViewById(R.id.login_button_login_pass);
        login_button_login.setOnClickListener(this);
        login_tv_register = (TextView) findViewById(R.id.login_tv_register_pass);
        login_tv_register.setOnClickListener(this);
        login_tv_quick = (TextView) findViewById(R.id.login_tv_pass);
        login_tv_quick.setOnClickListener(this);
        login_tv_forget = (TextView) findViewById(R.id.login_tv_forget);
        login_tv_forget.setOnClickListener(this);
        login_img_password_vis = (CheckBox) findViewById(R.id.login_img_password_vis);
        login_img_password_vis.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                login_ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                login_ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        login_img_password_vis.setOnClickListener(this);
        login_group_code = (Group) findViewById(R.id.login_group_code);
        login_group_password = (Group) findViewById(R.id.login_group_password);
        login_ed_code = (EditText) findViewById(R.id.login_ed_code);
        login_tv_get_code = (TextView) findViewById(R.id.login_tv_get_code);
        login_tv_get_code.setOnClickListener(this);
        login_button_login_query = (Button) findViewById(R.id.login_button_login_query);
        login_button_login_query.setOnClickListener(this);
        login_tv_register_query = (TextView) findViewById(R.id.login_tv_register_query);
        login_tv_register_query.setOnClickListener(this);
        login_tv_query = (TextView) findViewById(R.id.login_tv_query);
        login_tv_query.setOnClickListener(this);
        login_img_img_code = (ImageView) findViewById(R.id.login_img_img_code);
        login_img_img_code.setOnClickListener(this);
        login_ed_img_code = (EditText) findViewById(R.id.login_ed_img_code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            //注册
            case R.id.login_tv_register_pass:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            //注册
            case R.id.login_tv_register_query:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            //切换为快速登陆
            case R.id.login_tv_pass:
                isPassword = false;
                mPresenter.getImgCode(this);
                login_group_password.setVisibility(View.INVISIBLE);
                login_group_code.setVisibility(View.VISIBLE);
                break;
            //切换为密码登陆
            case R.id.login_tv_query:
                isPassword = true;
                login_group_password.setVisibility(View.VISIBLE);
                login_group_code.setVisibility(View.INVISIBLE);
                break;
            //忘记密码
            case R.id.login_tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            //密码登陆按钮
            case R.id.login_button_login_pass:
                loginP();
                break;
            //验证码登陆按钮
            case R.id.login_button_login_query:
                loginQ();
                break;
            //图片验证码
            case R.id.login_img_img_code:
                mPresenter.getImgCode(this);
                break;
            //获取登陆验证码
            case R.id.login_tv_get_code:
                getCode();
                break;
        }
    }

    //密码登录
    private void loginP() {
        phone = login_ed_phone.getText().toString();
        code = login_ed_password.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumberCheck.checkCellphone(phone)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.loginForPassword(this, phone, code);
        }
    }

    //快速登录
    private void loginQ() {
        phone = login_ed_phone.getText().toString();
        code = login_ed_code.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumberCheck.checkCellphone(phone)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (code.length() < 4) {
            Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.loginForCode(this, phone, code);
        }
    }

    //获取登录验证码
    private void getCode() {
        MyTextUtils.hideSoftKeyboard(login_ed_img_code, this);
        phone = login_ed_phone.getText().toString();
        imgCode = login_ed_img_code.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (PhoneNumberCheck.checkCellphone(phone)) {
            if (TextUtils.isEmpty(imgCode)) {
                Toast.makeText(this, "请输入图片中的验证码", Toast.LENGTH_SHORT).show();
            } else {
                mPresenter.getLoginCode(this, phone, imgCode, imgCodeKey, login_tv_get_code);
            }
        } else {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    //快速登陆成功
    @Override
    public void LoginCodeSuccess(String token) {
        phone = login_ed_phone.getText().toString();
        sp = getSharedPreferences("User", 0);
        Editor editor = sp.edit();
        editor.clear();
        editor.putString("user", phone);
        editor.putString("token", token);
        editor.putBoolean("first", true);
        editor.putBoolean("isLogin", true);
        editor.apply();
        setResult(200);
        if (toFirst) {
            startActivity(new Intent(this, FirstActivity.class));
        }
        finish();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    //密码登陆成功
    @Override
    public void LoginPassword(String phone, String token) {
        this.phone = phone;
        sp = getSharedPreferences("User", 0);
        Editor editor = sp.edit();
        editor.clear();
        editor.putString("user", phone);
        editor.putString("token", token);
        editor.putBoolean("isLogin", true);
        editor.putBoolean("first", true);
        editor.apply();
        setResult(200);
        if (toFirst) {
            startActivity(new Intent(this, FirstActivity.class));
        }
        finish();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    //获取图片验证码成功
    @Override
    public void getImgCode(String imgCodeKey, String imgCodeString) {
        Bitmap bitmap = Bitmap2Base64.stringToBitmap(imgCodeString);
        login_img_img_code.setImageBitmap(bitmap);
        this.imgCodeKey = imgCodeKey;
    }

}
