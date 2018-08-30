package com.superwanttoborrow.ui.repayment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseFragment;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.ui.repaydetail.RepayDetailActivity;
import com.superwanttoborrow.ui.repayplan.RepayPlanActivity;
import com.superwanttoborrow.ui.repayrecord.RepayRecordActivity;
import com.superwanttoborrow.ui.wanttoextension.WantToExtensionActivity;
import com.superwanttoborrow.ui.wanttorepay.WantToRepayActivity;
import com.superwanttoborrow.utils.MyTextUtils;

import java.text.DecimalFormat;

/**
 * @author renji
 */

public class RepaymentFragment extends MVPBaseFragment<RepaymentContract.View, RepaymentPresenter> implements RepaymentContract.View, View.OnClickListener {

    private TextView repaymentDetail;
    private TextView repayShouldMoney;
    private TextView repay_tv_state;
    private TextView repayTvWantToRepay;
    private TextView repayTvWantExtension;
    private TextView repayTvMyPlan;
    private TextView repayTvMyRecord;
    private boolean boo = true;
    private String presentDueTot;
    private String presentAmtForSettled;
    private Group repay_group;
    private TextView repay_tv_date;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getRepayment(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mPresenter.getRepayment(getContext());
        }
    }

    private void initView(View view) {
        repaymentDetail = (TextView) view.findViewById(R.id.repayment_detail);
        repaymentDetail.setOnClickListener(this);
        repayShouldMoney = (TextView) view.findViewById(R.id.repay_should_money);
        repay_tv_state = (TextView) view.findViewById(R.id.repay_tv_state);
        repayTvWantToRepay = (TextView) view.findViewById(R.id.repay_tv_want_to_repay);
        repayTvWantToRepay.setOnClickListener(this);
        repayTvWantExtension = (TextView) view.findViewById(R.id.repay_tv_want_extension);
        repayTvWantExtension.setOnClickListener(this);
        repayTvMyPlan = (TextView) view.findViewById(R.id.repay_tv_my_plan);
        repayTvMyPlan.setOnClickListener(this);
        repayTvMyRecord = (TextView) view.findViewById(R.id.repay_tv_my_record);
        repayTvMyRecord.setOnClickListener(this);
        repay_group = (Group) view.findViewById(R.id.repay_group);
        repay_tv_date = (TextView) view.findViewById(R.id.repay_tv_date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repayment_detail:
                startActivity(new Intent(getContext(), RepayDetailActivity.class));
                break;
                //我要还款
            case R.id.repay_tv_want_to_repay:
                mPresenter.getRepayment(getContext());
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                    if (sharedPreferences.getBoolean("isLogin", false)) {
                        if ("暂无借款".equals(repay_tv_state.getText().toString()) || "已结清".equals(repay_tv_state.getText().toString())) {
                            Toast.makeText(getContext(), "您暂时还没有借款，不需要进行还款", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getActivity(), WantToRepayActivity.class);
                            intent.putExtra("presentDueTot", presentDueTot);
                            intent.putExtra("presentAmtForSettled", presentAmtForSettled);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                break;
                    //我要展期
            case R.id.repay_tv_want_extension:
                startActivity(new Intent(getContext(), WantToExtensionActivity.class));
                break;
                //还款计划
            case R.id.repay_tv_my_plan:
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    if ("暂无借款".equals(repay_tv_state.getText().toString())) {
                        Toast.makeText(getContext(), "您暂时还没有借款，暂未生成还款计划", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getActivity(), RepayPlanActivity.class));
                    }
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
                //还款记录
            case R.id.repay_tv_my_record:
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    startActivity(new Intent(getActivity(), RepayRecordActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }

    @Override
    public void getRepayment(ReturnBean.DataBean dataBean) {
        DecimalFormat df = new DecimalFormat("0.00");
        repayShouldMoney.setText("￥" + df.format(dataBean.getPresentTotDue()));
        presentDueTot = df.format(dataBean.getPresentTotDue()) + "";
        presentAmtForSettled = df.format(dataBean.getPresentAmtForSettled() + dataBean.getPresentPenalty()) + "";
        switch (Integer.parseInt(dataBean.getStatus())) {
            //未申请放款
            case -1:
                repay_tv_state.setText("未申请放款");
                repay_group.setVisibility(View.GONE);
                break;
            //已还款
            case 0:
                repay_tv_state.setText("已还款");
                repay_group.setVisibility(View.GONE);
                break;
            //逾期
            case 1:
                repay_tv_state.setText("逾期");
                repay_group.setVisibility(View.VISIBLE);
                repay_tv_date.setText(MyTextUtils.getStrData(dataBean.getRepaidDeadline()+""));
                break;
            //已结清
            case 2:
                repay_tv_state.setText("已结清");
                repay_group.setVisibility(View.GONE);
                break;
            //一次结清
            case 3:
                repay_tv_state.setText("一次结清");
                repay_group.setVisibility(View.GONE);
                break;
            //取消借款
            case 4:
                repay_tv_state.setText("取消借款");
                repay_group.setVisibility(View.GONE);
                break;
            //已提交
            case 5:
                repay_tv_state.setText("已提交");
                repay_group.setVisibility(View.GONE);
                break;
            //提交失败
            case 6:
                repay_tv_state.setText("提交失败");
                repay_group.setVisibility(View.GONE);
                break;
            //待还款
            case 7:
                repay_tv_state.setText("待还款");
                repay_group.setVisibility(View.VISIBLE);
                repay_tv_date.setText(MyTextUtils.getStrData(dataBean.getRepaidDeadline()+""));
                break;
            //放款成功
            case 8:
                repay_tv_state.setText("放款成功");
                repay_group.setVisibility(View.GONE);
                break;
            //放款失败
            case 9:
                repay_tv_state.setText("放款失败");
                repay_group.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void getNoRepayment() {
        repayShouldMoney.setText("0");
        repay_tv_state.setText("暂无借款");
        repay_group.setVisibility(View.GONE);
    }
}
