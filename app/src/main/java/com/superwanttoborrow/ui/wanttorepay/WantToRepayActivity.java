package com.superwanttoborrow.ui.wanttorepay;


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
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.utils.MyTextUtils;

import java.text.DecimalFormat;


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
    private AlertDialog dialog;
    private EditText dialog_repay_et;
    private Button dialog_repay_get;
    private Button dialog_repay_button;
    private ImageView dialog_repay_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_repay);
        initView();
        mPresenter.getRepay(this);
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
                mPresenter.repay(this, dialog_repay_et.getText().toString());
            }
        });
        dialog.show();
    }

    @Override
    public void repay() {
        finish();
    }

    @Override
    public void getRepay(ReturnBean.DataBean dataBean) {
        DecimalFormat df = new DecimalFormat("0.00");
        double presentDueTot = dataBean.getPresentDueTot();
        double presentTotDue = dataBean.getPresentTotDue();
        long repaidDeadline = dataBean.getRepaidDeadline();
//        wtr_tv_mess.setText("借款时间"+);
        wtr_tv_repay_money.setText("￥"+ df.format(presentTotDue));
//        wtr_tv_borrow_money.setText("￥"+ df.format());
        wtr_tv_expire_repay_time.setText(MyTextUtils.getStrData(repaidDeadline+""));
//        wtr_tv_repay_time.setText("天");
    }
}
