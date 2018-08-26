package com.superwanttoborrow.ui.safesetting;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.changepassword.ChangePasswordActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SafeSettingActivity extends MVPBaseActivity<SafeSettingContract.View, SafeSettingPresenter> implements SafeSettingContract.View, View.OnClickListener {


    private ImageView safeBack;
    private TextView safeTvPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_setting);
        initView();
    }

    private void initView() {
        safeBack = (ImageView) findViewById(R.id.safe_back);
        safeBack.setOnClickListener(this);
        safeTvPass = (TextView) findViewById(R.id.safe_tv_pass);
        safeTvPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.safe_back:
                finish();
                break;
            case R.id.safe_tv_pass:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
        }
    }
}
