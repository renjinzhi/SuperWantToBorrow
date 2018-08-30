package com.superwanttoborrow.ui.forgetpassword;


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
import com.superwanttoborrow.utils.PhoneNumberCheck;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForgetPasswordActivity extends MVPBaseActivity<ForgetPasswordContract.View, ForgetPasswordPresenter> implements ForgetPasswordContract.View, View.OnClickListener {


    private ImageView forget_back;
    private EditText forget_ed_phone;
    private EditText forget_ed_img_code;
    private ImageView forget_img_img_code;
    private EditText forget_ed_code;
    private TextView forget_tv_get_code;
    private EditText forget_ed_password;
    private Button forget_button_forget;
    private String imgCodeKey;
    private String phone;
    private String imgCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        forget_back = (ImageView) findViewById(R.id.forget_back);
        forget_back.setOnClickListener(view -> finish());
        forget_ed_phone = (EditText) findViewById(R.id.forget_ed_phone);
        forget_ed_img_code = (EditText) findViewById(R.id.forget_ed_img_code);
        forget_img_img_code = (ImageView) findViewById(R.id.forget_img_img_code);
        forget_img_img_code.setOnClickListener(view -> mPresenter.getImgCode(this));
        forget_ed_code = (EditText) findViewById(R.id.forget_ed_code);
        forget_tv_get_code = (TextView) findViewById(R.id.forget_tv_get_code);
        forget_tv_get_code.setOnClickListener(this::onClick);
        forget_ed_password = (EditText) findViewById(R.id.forget_ed_password);
        forget_button_forget = (Button) findViewById(R.id.forget_button_forget);
        forget_button_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_button_forget:

                break;
            case R.id.forget_tv_get_code:
                phone = forget_ed_phone.getText().toString();
                imgCode = forget_ed_img_code.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumberCheck.checkCellphone(phone)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(imgCode)) {
                    Toast.makeText(this, "请输入图片中的验证码", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.getCode(this, phone, imgCode, imgCodeKey, forget_tv_get_code);
                }
                break;
        }
    }

    @Override
    public void getImgCode(String imgCodeKey, String imgCodeString) {
        Bitmap bitmap = Bitmap2Base64.stringToBitmap(imgCodeString);
        forget_img_img_code.setImageBitmap(bitmap);
        this.imgCodeKey = imgCodeKey;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getImgCode(this);
    }

}
