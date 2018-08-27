package com.superwanttoborrow.ui.basicinformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.contacts.ContactsActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BasicInformationActivity extends MVPBaseActivity<BasicInformationContract.View, BasicInformationPresenter> implements BasicInformationContract.View, View.OnClickListener {


    private ImageView basic_information_back;
    private TextView basic_information_name_tv;
    private TextView basic_information_id_tv;
    private TextView basic_information_phone_tv;
    private TextView basic_information_city_tv;
    private Spinner basic_information_spinner_culture;
    private Spinner basic_information_spinner_work;
    private Spinner basic_information_spinner_money;
    private Button basic_information_button_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        initView();
    }

    private void initView() {
        basic_information_back = (ImageView) findViewById(R.id.basic_information_back);
        basic_information_back.setOnClickListener(this);
        basic_information_name_tv = (TextView) findViewById(R.id.basic_information_name_tv);
        basic_information_id_tv = (TextView) findViewById(R.id.basic_information_id_tv);
        basic_information_phone_tv = (TextView) findViewById(R.id.basic_information_phone_tv);
        basic_information_city_tv = (TextView) findViewById(R.id.basic_information_city_tv);
        basic_information_spinner_culture = (Spinner) findViewById(R.id.basic_information_spinner_culture);
        basic_information_spinner_work = (Spinner) findViewById(R.id.basic_information_spinner_work);
        basic_information_spinner_money = (Spinner) findViewById(R.id.basic_information_spinner_money);
        basic_information_button_next = (Button) findViewById(R.id.basic_information_button_next);
        basic_information_button_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_information_back:
                finish();
                break;
            case R.id.basic_information_button_next:
                startActivity(new Intent(this, ContactsActivity.class));
                break;
        }
    }
}
