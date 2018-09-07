package com.superwanttoborrow.ui.wanttorepay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WantToRepayActivity extends MVPBaseActivity<WantToRepayContract.View, WantToRepayPresenter> implements WantToRepayContract.View, View.OnClickListener {


    private ImageView wtr_back;
    private TextView wtr_tv_mess;
    private TextView wtr_tv_repay_money;
    private TextView wtr_tv_borrow_money;
    private TextView wtr_tv_expire_repay_time;
    private TextView wtr_tv_repay_time;
    private Button wtr_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_repay);
        initView();
    }

    private void initView() {
        wtr_back = (ImageView) findViewById(R.id.wtr_back);
        wtr_back.setOnClickListener(view -> finish());
        wtr_tv_mess = (TextView) findViewById(R.id.wtr_tv_mess);
        wtr_tv_repay_money = (TextView) findViewById(R.id.wtr_tv_repay_money);
        wtr_tv_borrow_money = (TextView) findViewById(R.id.wtr_tv_borrow_money);
        wtr_tv_expire_repay_time = (TextView) findViewById(R.id.wtr_tv_expire_repay_time);
        wtr_tv_repay_time = (TextView) findViewById(R.id.wtr_tv_repay_time);
        wtr_button = (Button) findViewById(R.id.wtr_button);

        wtr_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wtr_button:

                break;
        }
    }
}
