package com.superwanttoborrow.ui.register;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.superwanttoborrow.utils.Bitmap2Base64;
import com.superwanttoborrow.utils.MyTextUtils;
import com.superwanttoborrow.utils.PhoneNumberCheck;


/**
 * 注册界面
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View, View.OnClickListener {


    private ImageView register_back;
    private TextView register_choose_title;
    private TextView register_tv_work;
    private TextView register_tv_alone;
    private TextView register_tv_free;
    private EditText register_ed_phone;
    private EditText register_ed_code;
    private TextView register_tv_get_code;
    private EditText register_ed_password;
    private Button register_button_register;
    private ImageView register_img_img_code;
    private String imgCodeKey;
    private TextView register_tv_unemployed;
    private String phone;
    private EditText register_ed_img_code;
    private String imgCode;
    private String code;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //获取图片验证码
        mPresenter.getImgCode(this);
    }

    private void initView() {
        register_back = (ImageView) findViewById(R.id.register_back);
        register_back.setOnClickListener(view -> finish());
        register_choose_title = (TextView) findViewById(R.id.register_choose_title);
        register_tv_work = (TextView) findViewById(R.id.register_tv_work);
        register_tv_work.setOnClickListener(this);
        register_tv_alone = (TextView) findViewById(R.id.register_tv_alone);
        register_tv_alone.setOnClickListener(this::onClick);
        register_tv_free = (TextView) findViewById(R.id.register_tv_free);
        register_tv_free.setOnClickListener(this::onClick);
        register_ed_phone = (EditText) findViewById(R.id.register_ed_phone);
        register_ed_code = (EditText) findViewById(R.id.register_ed_code);
        register_tv_get_code = (TextView) findViewById(R.id.register_tv_get_code);
        register_tv_get_code.setOnClickListener(this::onClick);
        register_ed_password = (EditText) findViewById(R.id.register_ed_password);
        register_button_register = (Button) findViewById(R.id.register_button_register);
        register_button_register.setOnClickListener(this);
        register_img_img_code = (ImageView) findViewById(R.id.register_img_img_code);
        register_img_img_code.setOnClickListener(this);
        register_tv_unemployed = (TextView) findViewById(R.id.register_tv_unemployed);
        register_tv_unemployed.setOnClickListener(this);
        register_ed_img_code = (EditText) findViewById(R.id.register_ed_img_code);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册
            case R.id.register_button_register:
                register();
                break;
            //图片验证码
            case R.id.register_img_img_code:
                mPresenter.getImgCode(this);
                break;
            //上班族
            case R.id.register_tv_work:
                setChooseBg(register_tv_work, register_tv_alone, register_tv_free, register_tv_unemployed);
                break;
            //个体
            case R.id.register_tv_alone:
                setChooseBg(register_tv_alone, register_tv_work, register_tv_free, register_tv_unemployed);
                break;
            //自由职业
            case R.id.register_tv_free:
                setChooseBg(register_tv_free, register_tv_alone, register_tv_work, register_tv_unemployed);
                break;
            //无业
            case R.id.register_tv_unemployed:
                setChooseBg(register_tv_unemployed, register_tv_alone, register_tv_free, register_tv_work);
                break;
            //获取验证码
            case R.id.register_tv_get_code:
                getCode();
                break;
        }
    }


    private void register() {
        MyTextUtils.hideSoftKeyboard(register_ed_password, this);
        phone = register_ed_phone.getText().toString();
        code = register_ed_code.getText().toString();
        password = register_ed_password.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumberCheck.checkCellphone(phone)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入收到的短信验证码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请设置您的密码", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(this, "密码长度不得少于6位", Toast.LENGTH_SHORT).show();
        } else if (!MyTextUtils.isPassword(password)) {
            Toast.makeText(this, "密码只能由数字和字母组成", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.register(this, phone, code, password);
        }
    }

    //获取验证码
    private void getCode() {
        MyTextUtils.hideSoftKeyboard(register_ed_img_code, this);
        phone = register_ed_phone.getText().toString();
        imgCode = register_ed_img_code.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumberCheck.checkCellphone(phone)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(imgCode)) {
            Toast.makeText(this, "请输入图片中的验证码", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.getCodeRegister(this, phone, imgCode, imgCodeKey, register_tv_get_code);
        }
    }

    //身份选择
    public void setChooseBg(TextView isChoose, TextView tv1, TextView tv2, TextView tv3) {
        isChoose.setBackgroundColor(getResources().getColor(R.color.red));
        isChoose.setTextColor(getResources().getColor(R.color.white));
        tv1.setBackgroundColor(getResources().getColor(R.color.white));
        tv1.setTextColor(getResources().getColor(R.color.hui));
        tv2.setBackgroundColor(getResources().getColor(R.color.white));
        tv2.setTextColor(getResources().getColor(R.color.hui));
        tv3.setBackgroundColor(getResources().getColor(R.color.white));
        tv3.setTextColor(getResources().getColor(R.color.hui));
    }

    @Override
    public void getImgCode(String imgCodeKey, String imgCodeString) {
        Bitmap bitmap = Bitmap2Base64.stringToBitmap(imgCodeString);
        register_img_img_code.setImageBitmap(bitmap);
        this.imgCodeKey = imgCodeKey;
    }

    //注册成功
    @Override
    public void registerSuccess(String name, String token) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("User", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString("user", name);
        editor.putString("token", token);
        editor.apply();
//        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
