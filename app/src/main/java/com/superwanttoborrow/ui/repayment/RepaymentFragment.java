package com.superwanttoborrow.ui.repayment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseFragment;
import com.superwanttoborrow.ui.repaydetail.RepayDetailActivity;
import com.superwanttoborrow.ui.repayplan.RepayPlanActivity;
import com.superwanttoborrow.ui.repayrecord.RepayRecordActivity;
import com.superwanttoborrow.ui.wanttoextension.WantToExtensionActivity;
import com.superwanttoborrow.ui.wanttorepay.WantToRepayActivity;

import java.text.DecimalFormat;

/**
 * @author renji
 */

public class RepaymentFragment extends MVPBaseFragment<RepaymentContract.View, RepaymentPresenter> implements RepaymentContract.View, View.OnClickListener {

    private TextView repaymentDetail;
    private TextView repayShouldMoney;
    private TextView repayTvExtension;
    private TextView repayTvOverdue;
    private TextView repayTvNormal;
    private TextView repayTvWantToRepay;
    private TextView repayTvWantExtension;
    private TextView repayTvMyPlan;
    private TextView repayTvMyRecord;
    private boolean boo = true;
    private String presentDueTot;
    private String presentAmtForSettled;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        repaymentDetail = (TextView) view.findViewById(R.id.repayment_detail);
        repaymentDetail.setOnClickListener(this);
        repayShouldMoney = (TextView) view.findViewById(R.id.repay_should_money);
        repayTvExtension = (TextView) view.findViewById(R.id.repay_tv_extension);
        repayTvOverdue = (TextView) view.findViewById(R.id.repay_tv_overdue);
        repayTvNormal = (TextView) view.findViewById(R.id.repay_tv_normal);
        repayTvWantToRepay = (TextView) view.findViewById(R.id.repay_tv_want_to_repay);
        repayTvWantToRepay.setOnClickListener(this);
        repayTvWantExtension = (TextView) view.findViewById(R.id.repay_tv_want_extension);
        repayTvWantExtension.setOnClickListener(this);
        repayTvMyPlan = (TextView) view.findViewById(R.id.repay_tv_my_plan);
        repayTvMyPlan.setOnClickListener(this);
        repayTvMyRecord = (TextView) view.findViewById(R.id.repay_tv_my_record);
        repayTvMyRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repayment_detail:
                startActivity(new Intent(getContext(), RepayDetailActivity.class));
                break;
            case R.id.repay_tv_want_to_repay:
                startActivity(new Intent(getContext(), WantToRepayActivity.class));
                break;
            case R.id.repay_tv_want_extension:
                startActivity(new Intent(getContext(), WantToExtensionActivity.class));
                break;
            case R.id.repay_tv_my_plan:
                startActivity(new Intent(getContext(), RepayPlanActivity.class));
                break;
            case R.id.repay_tv_my_record:
                startActivity(new Intent(getContext(), RepayRecordActivity.class));
                break;
        }
    }

    @Override
    public void getRepayment(ReturnBean.DataBean dataBean) {
        if (dataBean.getRepaymentStatus().equals("1")){
            boo = false;
        }else {
            boo = true;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        repayShouldMoney.setText("￥" + df.format(dataBean.getPresentTotDue()));
        presentDueTot = df.format(dataBean.getPresentTotDue()) + "";
        presentAmtForSettled = df.format(dataBean.getPresentAmtForSettled() + dataBean.getPresentPenalty()) + "";
        switch (Integer.parseInt(dataBean.getStatus())) {
            //未申请放款
            case -1:
                break;
                //已还款
            case 0:
                repayTvOverdue.setVisibility(View.VISIBLE);
                break;
                //逾期
            case 1:
                break;
                //已结清
            case 2:
                break;
                //一次结清
            case 3:
                break;
                //取消借款
            case 4:
                break;
                //已提交
            case 5:
                break;
                //提交失败
            case 6:
                break;
                //待还款
            case 7:
                break;
                //放款成功
            case 8:
                break;
                //放款失败
            case 9:
                break;
            default:
                break;
        }
    }

    @Override
    public void getNoRepayment() {
        repayShouldMoney.setText("0");
        repayTvExtension.setVisibility(View.GONE);
        repayTvOverdue.setVisibility(View.GONE);
        repayTvNormal.setVisibility(View.GONE);
    }
}
