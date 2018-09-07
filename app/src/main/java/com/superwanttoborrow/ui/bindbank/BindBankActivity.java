package com.superwanttoborrow.ui.bindbank;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.first.FirstActivity;
import com.superwanttoborrow.utils.MyCountDownTimer;
import com.superwanttoborrow.utils.MyTextUtils;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindBankActivity extends MVPBaseActivity<BindBankContract.View, BindBankPresenter> implements BindBankContract.View, View.OnClickListener {


    private ImageView bind_bank_back;
    private EditText bind_bank_ed_bank_card;
    private TextView bind_bank_tv_bank;
    private ImageView bind_bank_img_bank;
    private TextView bind_bank_ed_phone;
    private Button bind_bank_button;
    private AlertDialog dialog;
    private ImageView dialog_bc_img;
    private EditText dialog_bc_ed;
    private Button dialog_bc_button_get_code;
    private Button dialog_pg_button;
    private String bank;
    private boolean hasBank = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank);
        initView();
        initData();
        initBqsDFSDK();
    }

    private void initView() {
        bind_bank_back = (ImageView) findViewById(R.id.bind_bank_back);
        bind_bank_back.setOnClickListener(view -> finish());
        bind_bank_ed_bank_card = (EditText) findViewById(R.id.bind_bank_ed_bank_card);
        bind_bank_tv_bank = (TextView) findViewById(R.id.bind_bank_tv_bank);
        bind_bank_img_bank = (ImageView) findViewById(R.id.bind_bank_img_bank);
        bind_bank_ed_phone = (TextView) findViewById(R.id.bind_bank_ed_phone);
        bind_bank_button = (Button) findViewById(R.id.bind_bank_button);
        bind_bank_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_bank_button:
                if (hasBank) {
                    mPresenter.isReal(this);
                }else {
                    Toast.makeText(this,"请先输入银行卡号",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initData() {
        bind_bank_ed_bank_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 16) {
                    bank = editable.toString();
                    mPresenter.isBank(BindBankActivity.this, bank);
                }
            }
        });
        SharedPreferences user = getSharedPreferences("User", 0);
        bind_bank_ed_phone.setText(user.getString("user", null));
        bind_bank_ed_bank_card.setText(user.getString("bankCardId", null));
        String depositBank2 = user.getString("depositBank", null);
        bind_bank_tv_bank.setText(depositBank2);
        if (!TextUtils.isEmpty(depositBank2)){
            bind_bank_img_bank.setImageResource(MyTextUtils.getBankCard(user.getString("depositBank", null)));
        }
    }

    @Override
    public void isReal(Boolean isBank) {
        if (isBank) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            dialog = builder.create();
            View view = View.inflate(this, R.layout.dialog_bank_code, null);
            dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
            dialog.setCanceledOnTouchOutside(false);
            dialog_bc_img = (ImageView) view.findViewById(R.id.dialog_bc_img);
            dialog_bc_img.setOnClickListener(view1 -> dialog.dismiss());
            dialog_bc_ed = (EditText) view.findViewById(R.id.dialog_bc_ed);
            dialog_bc_button_get_code = (Button) view.findViewById(R.id.dialog_bc_button_get_code);
            new Thread(new MyCountDownTimer(dialog_bc_button_get_code)).start();
            dialog_bc_button_get_code.setOnClickListener(view1 -> mPresenter.getCode(this,dialog_bc_button_get_code));
            dialog_pg_button = (Button) view.findViewById(R.id.dialog_pg_button);
            dialog_pg_button.setOnClickListener(view1 -> mPresenter.checkCode(this,dialog_bc_ed.getText().toString()));
            dialog.show();
        } else {
            //不需要验证码
            mPresenter.repayment(this);
        }

    }

    @Override
    public void isBank(String bankName) {
        hasBank = true;
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("bankCardId",bank);
        edit.putString("depositBank",bankName);
        edit.apply();
        bind_bank_tv_bank.setText(bankName);
        bind_bank_img_bank.setImageResource(MyTextUtils.getBankCard(bankName));
    }

    @Override
    public void repayment() {
        Toast.makeText(this, "申请已提交，请前往进度查询查询当前状态", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }
}
