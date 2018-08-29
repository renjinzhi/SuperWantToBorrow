package com.superwanttoborrow.ui.safesetting;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.changepassword.ChangePasswordActivity;
import com.superwanttoborrow.widget.ExitDialog;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SafeSettingActivity extends MVPBaseActivity<SafeSettingContract.View, SafeSettingPresenter> implements SafeSettingContract.View, View.OnClickListener {


    private ImageView safeBack;
    private TextView safeTvPass;
    private Button safe_exit;
    private ExitDialog mExitDialog;
    private String user;

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
        safe_exit = (Button) findViewById(R.id.safe_exit);
        safe_exit.setOnClickListener(this);
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
            case R.id.safe_exit:
                SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
                user = sharedPreferences.getString("user", null);
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(this, "您还没有登录", Toast.LENGTH_SHORT).show();
                } else {
                    mExitDialog = new ExitDialog(this);
                    mExitDialog.show();
                    Button exit = (Button) mExitDialog.findViewById(R.id.dialog_confirm);
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.exit(SafeSettingActivity.this, user);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void exit() {
        mExitDialog.dismiss();
        SharedPreferences sp = getSharedPreferences("User", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        finish();
    }
}
