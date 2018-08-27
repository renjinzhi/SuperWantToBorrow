package com.superwanttoborrow.ui.changebank;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.bank.BankActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangeBankActivity extends MVPBaseActivity<ChangeBankContract.View, ChangeBankPresenter> implements ChangeBankContract.View, View.OnClickListener {


    private ImageView change_bank_back;
    private EditText change_bank_ed_name;
    private ImageView change_bank_ed_name_show;
    private EditText change_bank_ed_id_card;
    private TextView change_bank_bank_mess;
    private EditText change_bank_ed_bank_card;
    private TextView change_bank_tv_bank;
    private EditText change_bank_ed_phone;
    private EditText change_bank_ed_code;
    private CheckBox change_bank_check;
    private TextView change_bank_tv_deal;
    private Button change_bank_button;
    private TextView change_bank_get_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bank);
        initView();
    }

    private void initView() {
        change_bank_back = (ImageView) findViewById(R.id.change_bank_back);
        change_bank_back.setOnClickListener((view -> finish()));
        change_bank_ed_name = (EditText) findViewById(R.id.change_bank_ed_name);
        change_bank_ed_name_show = (ImageView) findViewById(R.id.change_bank_ed_name_show);
        change_bank_ed_id_card = (EditText) findViewById(R.id.change_bank_ed_id_card);
        change_bank_bank_mess = (TextView) findViewById(R.id.change_bank_bank_mess);
        change_bank_bank_mess.setOnClickListener(this);
        change_bank_ed_bank_card = (EditText) findViewById(R.id.change_bank_ed_bank_card);
        change_bank_tv_bank = (TextView) findViewById(R.id.change_bank_tv_bank);
        change_bank_ed_phone = (EditText) findViewById(R.id.change_bank_ed_phone);
        change_bank_ed_code = (EditText) findViewById(R.id.change_bank_ed_code);
        change_bank_check = (CheckBox) findViewById(R.id.change_bank_check);
        change_bank_tv_deal = (TextView) findViewById(R.id.change_bank_tv_deal);
        change_bank_button = (Button) findViewById(R.id.change_bank_button);

        change_bank_button.setOnClickListener(this);
        change_bank_get_code = (TextView) findViewById(R.id.change_bank_get_code);
        change_bank_get_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_bank_bank_mess:
                startActivity(new Intent(this, BankActivity.class));
                break;
            case R.id.change_bank_button:
                break;
        }
    }

}
