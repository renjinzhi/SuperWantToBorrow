package com.superwanttoborrow.ui.wanttoextension;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class WantToExtensionActivity extends MVPBaseActivity<WantToExtensionContract.View, WantToExtensionPresenter> implements WantToExtensionContract.View, View.OnClickListener {


    private ImageView wte_back;
    private TextView wte_tv_extension_time_set;
    private TextView wte_tv_extension_date_set;
    private TextView wte_tv_expire_repay_money_set;
    private TextView wte_tv_extension_money_set;
    private Button wte_button_extension;
    private AlertDialog dialog;
    private EditText dialog_repay_et;
    private Button dialog_repay_get;
    private Button dialog_repay_button;
    private ImageView dialog_repay_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_extension);
        initView();
    }

    private void initView() {
        wte_back = (ImageView) findViewById(R.id.wte_back);
        wte_back.setOnClickListener(view -> finish());
        wte_tv_extension_time_set = (TextView) findViewById(R.id.wte_tv_extension_time_set);
        wte_tv_extension_date_set = (TextView) findViewById(R.id.wte_tv_extension_date_set);
        wte_tv_expire_repay_money_set = (TextView) findViewById(R.id.wte_tv_expire_repay_money_set);
        wte_tv_extension_money_set = (TextView) findViewById(R.id.wte_tv_extension_money_set);
        wte_button_extension = (Button) findViewById(R.id.wte_button_extension);

        wte_button_extension.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wte_button_extension:
                initData();
                break;
        }
    }

    private void initData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_repay, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        dialog.setCanceledOnTouchOutside(false);
        dialog_repay_img = (ImageView) view.findViewById(R.id.dialog_repay_img);
        dialog_repay_img.setOnClickListener(view1 -> dialog.dismiss());
        dialog_repay_et = (EditText) view.findViewById(R.id.dialog_repay_et);
        dialog_repay_get = (Button) view.findViewById(R.id.dialog_repay_get);
        dialog_repay_get.setOnClickListener(view1 -> mPresenter.getCode(this, dialog_repay_button));
        dialog_repay_button = (Button) view.findViewById(R.id.dialog_repay_button);
        dialog_repay_button.setOnClickListener(view1 -> {
            if (dialog_repay_et.getText().length() == 0) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            } else {
                mPresenter.extension(this, dialog_repay_et.getText().toString());
            }
        });
        dialog.show();
    }


    @Override
    public void extension() {
        finish();
    }
}
