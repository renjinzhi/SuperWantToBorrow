package com.superwanttoborrow.ui.progressquery;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.utils.MyTextUtils;

import java.text.DecimalFormat;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProgressQueryActivity extends MVPBaseActivity<ProgressQueryContract.View, ProgressQueryPresenter> implements ProgressQueryContract.View, View.OnClickListener {

    private AlertDialog dialog;
    private TextView dialog_pg_tv_money;
    private TextView dialog_pg_tv_deadline;
    private Button dialog_pg_button;
    private ImageView dialog_pg_img;
    private int node = 0;
    private int periods;
    private double loanAmount;
    private double approvalIFloanAmount;
    private int approvalIFperiods;
    private String format;
    private String mFormat;
    private ImageView progress_query_back;
    private TextView progress_query_mess;
    private ImageView pq_img_5;
    private TextView pq_tv_title_5;
    private TextView pg_tv_date_5;
    private TextView pg_tv_time_5;
    private View pg_view_4;
    private ImageView pq_img_4;
    private TextView pq_tv_title_4;
    private TextView pg_tv_date_4;
    private TextView pg_tv_time_4;
    private View pg_view_3;
    private ImageView pq_img_3;
    private TextView pq_tv_title_3;
    private TextView pg_tv_date_3;
    private TextView pg_tv_time_3;
    private View pg_view_2;
    private ImageView pq_img_2;
    private TextView pq_tv_title_2;
    private TextView pg_tv_date_2;
    private TextView pg_tv_time_2;
    private View pg_view_1;
    private ImageView pq_img_1;
    private TextView pq_tv_title_1;
    private TextView pg_tv_date_1;
    private TextView pg_tv_time_1;
    private Button pq_button;
    private String status = "0";
    private ReturnBean.DataBean dataBean = null;
    private TextView dialog_pq_mess;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_query);
        initView();
    }

    private void initData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_progress_query, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        dialog.setCanceledOnTouchOutside(false);
        dialog_pg_tv_money = (TextView) view.findViewById(R.id.dialog_pg_tv_money);
        dialog_pg_tv_deadline = (TextView) view.findViewById(R.id.dialog_pg_tv_deadline);
        dialog_pq_mess = (TextView) view.findViewById(R.id.dialog_pq_mess);
        dialog_pq_mess.setText("您申请的" + new DecimalFormat("0.00").format(dataBean.getApplyMoney()) + "元" + dataBean.getRefundPeriods() + "期最终审核额度确认");
        dialog_pg_tv_money.setText("￥" + format);
        dialog_pg_tv_deadline.setText("￥" + periods);
        dialog_pg_button = (Button) view.findViewById(R.id.dialog_pg_button);
        dialog_pg_img = (ImageView) view.findViewById(R.id.dialog_pg_img);
        dialog_pg_button.setOnClickListener((view1 -> dialog.dismiss()));
        dialog_pg_img.setOnClickListener((view1 -> dialog.dismiss()));
    }


    @Override
    public void getContract(ReturnBean.DataBean dataBean) {
        node = dataBean.getNode();
        periods = dataBean.getApprovedPeriods();
        loanAmount = dataBean.getApprovedMoney();
        approvalIFloanAmount = dataBean.getApplyMoney();
        approvalIFperiods = dataBean.getRefundPeriods();
        DecimalFormat df = new DecimalFormat("0.00");
        format = df.format(loanAmount);
        mFormat = df.format(approvalIFloanAmount);
        switch (node) {
            case 1:
//                textView5.setText("系统审核中");
                progress_query_mess.setText("您申请的" + mFormat + "元" + approvalIFperiods + "期正在进行系统审核");
                pg_tv_date_1.setText(MyTextUtils.getStrData(dataBean.getState().get(0).getTime() + ""));
                pg_tv_time_1.setText(MyTextUtils.getStrHour(dataBean.getState().get(0).getTime() + ""));
                pq_img_1.setImageResource(R.mipmap.xuanxiang);
                pq_button.setVisibility(View.GONE);
                break;
            case 2:
//                textView5.setText("人工审核中");
                progress_query_mess.setText("您申请的" + mFormat + "元" + approvalIFperiods + "期正在进行人工审核");
                pq_img_1.setImageResource(R.mipmap.xuanxiang);
                pq_img_2.setImageResource(R.mipmap.xuanxiang);
                pg_view_1.setBackgroundResource(R.color.red);
                pg_tv_date_1.setText(MyTextUtils.getStrData(dataBean.getState().get(0).getTime() + ""));
                pg_tv_time_1.setText(MyTextUtils.getStrHour(dataBean.getState().get(0).getTime() + ""));
                pg_tv_date_2.setText(MyTextUtils.getStrData(dataBean.getState().get(1).getTime() + ""));
                pg_tv_time_2.setText(MyTextUtils.getStrHour(dataBean.getState().get(1).getTime() + ""));
                pq_button.setVisibility(View.GONE);
                break;
            case 3:
                status = dataBean.getState().get(2).getStatus();
                pq_img_1.setImageResource(R.mipmap.xuanxiang);
                pq_img_2.setImageResource(R.mipmap.xuanxiang);
                pq_img_3.setImageResource(R.mipmap.xuanxiang);
                pg_view_1.setBackgroundResource(R.color.red);
                pg_view_2.setBackgroundResource(R.color.red);
                pg_tv_date_1.setText(MyTextUtils.getStrData(dataBean.getState().get(0).getTime() + ""));
                pg_tv_date_2.setText(MyTextUtils.getStrData(dataBean.getState().get(1).getTime() + ""));
                pg_tv_date_3.setText(MyTextUtils.getStrData(dataBean.getState().get(2).getTime() + ""));
                pg_tv_time_1.setText(MyTextUtils.getStrHour(dataBean.getState().get(0).getTime() + ""));
                pg_tv_time_2.setText(MyTextUtils.getStrHour(dataBean.getState().get(1).getTime() + ""));
                pg_tv_time_3.setText(MyTextUtils.getStrHour(dataBean.getState().get(2).getTime() + ""));

                pq_button.setVisibility(View.VISIBLE);
                if (!"3".equals(status)) {
                    progress_query_mess.setText("您申请的" + mFormat + "元" + approvalIFperiods + "期已经" + dataBean.getState().get(2).getValue());
//                    textView5.setText(dataBean.getState().get(2).getValue());
                    pq_tv_title_3.setText(dataBean.getState().get(2).getValue());
                    pq_button.setBackgroundResource(R.mipmap.button);
                } else {
                    progress_query_mess.setText("您申请的" + this.format + "元" + periods + "期已经" + dataBean.getState().get(2).getValue());
                    initData();
//                    textView5.setText("申请放款");
                    dialog.show();
                    pq_tv_title_3.setText("申请放款");
                }
                break;
            case 4:
//                textView5.setText("正在放款");
                progress_query_mess.setText("您申请的" + this.format + "元" + periods + "期正在放款");
                pq_img_1.setImageResource(R.mipmap.xuanxiang);
                pq_img_2.setImageResource(R.mipmap.xuanxiang);
                pq_img_3.setImageResource(R.mipmap.xuanxiang);
                pq_img_4.setImageResource(R.mipmap.xuanxiang);
                pg_view_1.setBackgroundResource(R.color.red);
                pg_view_2.setBackgroundResource(R.color.red);
                pg_view_3.setBackgroundResource(R.color.red);
                pg_tv_date_1.setText(MyTextUtils.getStrData(dataBean.getState().get(0).getTime() + ""));
                pg_tv_date_2.setText(MyTextUtils.getStrData(dataBean.getState().get(1).getTime() + ""));
                pg_tv_date_3.setText(MyTextUtils.getStrData(dataBean.getState().get(2).getTime() + ""));
                pg_tv_date_4.setText(MyTextUtils.getStrData(dataBean.getState().get(3).getTime() + ""));
                pg_tv_time_1.setText(MyTextUtils.getStrHour(dataBean.getState().get(0).getTime() + ""));
                pg_tv_time_2.setText(MyTextUtils.getStrHour(dataBean.getState().get(1).getTime() + ""));
                pg_tv_time_3.setText(MyTextUtils.getStrHour(dataBean.getState().get(2).getTime() + ""));
                pg_tv_time_4.setText(MyTextUtils.getStrHour(dataBean.getState().get(3).getTime() + ""));
                pq_button.setVisibility(View.GONE);
                break;
            case 5:
                status = dataBean.getState().get(4).getStatus();
                if ("8".equals(status)) {
                    progress_query_mess.setText("您申请的" + this.format + "元" + periods + "期放款成功");
//                    textView5.setText("放款成功");
                } else {
                    pq_tv_title_5.setText(dataBean.getState().get(4).getValue());
                    progress_query_mess.setText("您申请的" + this.format + "元" + periods + "期" + dataBean.getState().get(3).getValue());
//                    textView5.setText(dataBean.getState().get(4).getValue());
                }
                pq_img_1.setImageResource(R.mipmap.xuanxiang);
                pq_img_2.setImageResource(R.mipmap.xuanxiang);
                pq_img_3.setImageResource(R.mipmap.xuanxiang);
                pq_img_4.setImageResource(R.mipmap.xuanxiang);
                pq_img_5.setImageResource(R.mipmap.xuanxiang);
                pg_view_1.setBackgroundResource(R.color.red);
                pg_view_2.setBackgroundResource(R.color.red);
                pg_view_3.setBackgroundResource(R.color.red);
                pg_view_4.setBackgroundResource(R.color.red);
                pg_tv_date_1.setText(MyTextUtils.getStrData(dataBean.getState().get(0).getTime() + ""));
                pg_tv_date_2.setText(MyTextUtils.getStrData(dataBean.getState().get(1).getTime() + ""));
                pg_tv_date_3.setText(MyTextUtils.getStrData(dataBean.getState().get(2).getTime() + ""));
                pg_tv_date_4.setText(MyTextUtils.getStrData(dataBean.getState().get(3).getTime() + ""));
                pg_tv_date_5.setText(MyTextUtils.getStrData(dataBean.getState().get(4).getTime() + ""));
                pg_tv_time_1.setText(MyTextUtils.getStrHour(dataBean.getState().get(0).getTime() + ""));
                pg_tv_time_2.setText(MyTextUtils.getStrHour(dataBean.getState().get(1).getTime() + ""));
                pg_tv_time_3.setText(MyTextUtils.getStrHour(dataBean.getState().get(2).getTime() + ""));
                pg_tv_time_4.setText(MyTextUtils.getStrHour(dataBean.getState().get(3).getTime() + ""));
                pg_tv_time_5.setText(MyTextUtils.getStrHour(dataBean.getState().get(4).getTime() + ""));

                pq_button.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void initView() {
        progress_query_back = (ImageView) findViewById(R.id.progress_query_back);
        progress_query_back.setOnClickListener(view -> finish());
        progress_query_mess = (TextView) findViewById(R.id.progress_query_mess);
        pq_img_5 = (ImageView) findViewById(R.id.pq_img_5);
        pq_tv_title_5 = (TextView) findViewById(R.id.pq_tv_title_5);
        pg_tv_date_5 = (TextView) findViewById(R.id.pg_tv_date_5);
        pg_tv_time_5 = (TextView) findViewById(R.id.pg_tv_time_5);
        pg_view_4 = (View) findViewById(R.id.pg_view_4);
        pq_img_4 = (ImageView) findViewById(R.id.pq_img_4);
        pq_tv_title_4 = (TextView) findViewById(R.id.pq_tv_title_4);
        pg_tv_date_4 = (TextView) findViewById(R.id.pg_tv_date_4);
        pg_tv_time_4 = (TextView) findViewById(R.id.pg_tv_time_4);
        pg_view_3 = (View) findViewById(R.id.pg_view_3);
        pq_img_3 = (ImageView) findViewById(R.id.pq_img_3);
        pq_tv_title_3 = (TextView) findViewById(R.id.pq_tv_title_3);
        pg_tv_date_3 = (TextView) findViewById(R.id.pg_tv_date_3);
        pg_tv_time_3 = (TextView) findViewById(R.id.pg_tv_time_3);
        pg_view_2 = (View) findViewById(R.id.pg_view_2);
        pq_img_2 = (ImageView) findViewById(R.id.pq_img_2);
        pq_tv_title_2 = (TextView) findViewById(R.id.pq_tv_title_2);
        pg_tv_date_2 = (TextView) findViewById(R.id.pg_tv_date_2);
        pg_tv_time_2 = (TextView) findViewById(R.id.pg_tv_time_2);
        pg_view_1 = (View) findViewById(R.id.pg_view_1);
        pq_img_1 = (ImageView) findViewById(R.id.pq_img_1);
        pq_tv_title_1 = (TextView) findViewById(R.id.pq_tv_title_1);
        pg_tv_date_1 = (TextView) findViewById(R.id.pg_tv_date_1);
        pg_tv_time_1 = (TextView) findViewById(R.id.pg_tv_time_1);
        pq_button = (Button) findViewById(R.id.pq_button);
        pq_button.setOnClickListener(this);
        Intent intent = getIntent();
        dataBean = (ReturnBean.DataBean) intent.getSerializableExtra("dataBean");
        getContract(dataBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pq_button:
                mPresenter.borrow(this);
                break;
        }
    }

    @Override
    public void borrow() {
        mPresenter.getContract(this);
    }
}
