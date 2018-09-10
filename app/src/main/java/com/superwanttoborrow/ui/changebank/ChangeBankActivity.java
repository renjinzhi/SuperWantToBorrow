package com.superwanttoborrow.ui.changebank;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.bank.BankActivity;
import com.superwanttoborrow.utils.MyCountDownTimer;
import com.superwanttoborrow.utils.MyTextUtils;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangeBankActivity extends MVPBaseActivity<ChangeBankContract.View, ChangeBankPresenter> implements ChangeBankContract.View, View.OnClickListener {


    private ImageView change_bank_back;
    private TextView change_bank_ed_name;
    private TextView change_bank_ed_id_card;
    private TextView change_bank_bank_mess;
    private EditText change_bank_ed_bank_card;
    private TextView change_bank_tv_bank;
    private TextView change_bank_ed_phone;
    private CheckBox change_bank_check;
    private TextView change_bank_tv_deal;
    private Button change_bank_button;
    private String bankCard;
    private String name;
    private String cardId;
    private String mobile;
    private String depositBank;
    private AlertDialog dialog;
    private ImageView dialog_bc_img;
    private EditText dialog_bc_ed;
    private Button dialog_bc_button_get_code;
    private Button dialog_pg_button;
    private String bank;
    private ImageView change_bank_img_bank;
    private boolean hasBank = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bank);
        initView();
        initData();
    }

    private void initView() {
        change_bank_back = (ImageView) findViewById(R.id.change_bank_back);
        change_bank_back.setOnClickListener((view -> finish()));
        change_bank_ed_name = (TextView) findViewById(R.id.change_bank_ed_name);
        change_bank_ed_id_card = (TextView) findViewById(R.id.change_bank_ed_id_card);
        change_bank_bank_mess = (TextView) findViewById(R.id.change_bank_bank_mess);
        change_bank_bank_mess.setOnClickListener(this);
        change_bank_ed_bank_card = (EditText) findViewById(R.id.change_bank_ed_bank_card);
        change_bank_tv_bank = (TextView) findViewById(R.id.change_bank_tv_bank);
        change_bank_ed_phone = (TextView) findViewById(R.id.change_bank_ed_phone);
        change_bank_check = (CheckBox) findViewById(R.id.change_bank_check);
        change_bank_tv_deal = (TextView) findViewById(R.id.change_bank_tv_deal);
        change_bank_button = (Button) findViewById(R.id.change_bank_button);
        change_bank_button.setOnClickListener(this);
        change_bank_img_bank = (ImageView) findViewById(R.id.change_bank_img_bank);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_bank_bank_mess:
                startActivity(new Intent(this, BankActivity.class));
                break;
            case R.id.change_bank_button:
                if (hasBank) {
                    bankCard = change_bank_ed_bank_card.getText().toString();
                    depositBank = change_bank_tv_bank.getText().toString();
                    Toast.makeText(this, "请先输入银行卡号", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.getBankCode(this, name, cardId, mobile, bankCard, depositBank);
                }
                break;
        }
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        cardId = intent.getStringExtra("cardId");
        mobile = intent.getStringExtra("mobile");
        bankCard = intent.getStringExtra("bankCard");
        bank = intent.getStringExtra("bank");
        change_bank_ed_name.setText(name);
        change_bank_ed_phone.setText(mobile);
        change_bank_ed_id_card.setText(cardId);
        change_bank_ed_bank_card.setText(bankCard);
        change_bank_tv_bank.setText(bank);
        change_bank_img_bank.setImageResource(MyTextUtils.getBankCard(bank));

        change_bank_ed_bank_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 16) {
                    bankCard = editable.toString();
                    mPresenter.isBank(ChangeBankActivity.this, bank);
                }
            }
        });

    }

    @Override
    public void getBankCode(boolean isData) {
        if (isData) {
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
            dialog_bc_button_get_code.setOnClickListener(view1 -> mPresenter.getCode(this, dialog_bc_button_get_code));
            dialog_pg_button = (Button) view.findViewById(R.id.dialog_pg_button);
            dialog_pg_button.setOnClickListener(view1 -> mPresenter.checkCode(this, dialog_bc_ed.getText().toString(),cardId,bankCard,bank,name));
            dialog.show();
        } else {
            Toast.makeText(this, "更改绑定银行卡成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void isBank(String bankName) {
        hasBank = true;
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("bankCardId",bankCard);
        edit.putString("depositBank",bankName);
        bank = bankName;
        edit.apply();
        change_bank_tv_bank.setText(bankName);
        change_bank_img_bank.setImageResource(MyTextUtils.getBankCard(bankName));
    }
}
