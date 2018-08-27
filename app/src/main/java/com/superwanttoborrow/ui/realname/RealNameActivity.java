package com.superwanttoborrow.ui.realname;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.basicinformation.BasicInformationActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RealNameActivity extends MVPBaseActivity<RealNameContract.View, RealNamePresenter> implements RealNameContract.View, View.OnClickListener {

    private ImageView realname_back;
    private ImageView real_img_front;
    private ImageView real_img_verso;
    private ImageView real_img_head;
    private TextView real_name_tv;
    private TextView real_id_tv;
    private TextView real_phone_tv;
    private TextView real_bank_tv;
    private EditText real_edit_code;
    private TextView real_tv_get_code;
    private Button real_button_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realname);
        initView();
    }

    private void initView() {
        realname_back = (ImageView) findViewById(R.id.realname_back);
        realname_back.setOnClickListener(this);
        real_img_front = (ImageView) findViewById(R.id.real_img_front);
        real_img_verso = (ImageView) findViewById(R.id.real_img_verso);
        real_img_head = (ImageView) findViewById(R.id.real_img_head);
        real_name_tv = (TextView) findViewById(R.id.real_name_tv);
        real_id_tv = (TextView) findViewById(R.id.real_id_tv);
        real_phone_tv = (TextView) findViewById(R.id.real_phone_tv);
        real_bank_tv = (TextView) findViewById(R.id.real_bank_tv);
        real_edit_code = (EditText) findViewById(R.id.real_edit_code);
        real_tv_get_code = (TextView) findViewById(R.id.real_tv_get_code);
        real_button_next = (Button) findViewById(R.id.real_button_next);

        real_button_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.realname_back:
                finish();
                break;
            case R.id.real_button_next:
                startActivity(new Intent(this, BasicInformationActivity.class));
                break;
        }
    }

}
