package com.superwanttoborrow.ui.repayplan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.RpRvAdapter;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RepayPlanActivity extends MVPBaseActivity<RepayPlanContract.View, RepayPlanPresenter> implements RepayPlanContract.View {


    private ImageView repayPlanBack;
    private RecyclerView repayPlanRv;
    private List<ReturnDataListBean.DataBean> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_plan);
        initView();
        mPresenter.getRepaymentPlan(this);
    }

    private void initView() {
        repayPlanBack = (ImageView) findViewById(R.id.repay_plan_back);
        repayPlanRv = (RecyclerView) findViewById(R.id.repay_plan_rv);
    }


    @Override
    public void getNoPlan() {
        mList = new ArrayList<ReturnDataListBean.DataBean>();
        ReturnDataListBean.DataBean dataBean = new ReturnDataListBean.DataBean();
        dataBean.setBillPeriods(0);
        dataBean.setPresentTotDue(0);
        dataBean.setStatus("暂无借款");
        dataBean.setRepaidDeadline(0);
        mList.add(dataBean);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        repayPlanRv.setLayoutManager(linearLayoutManager);
        RpRvAdapter rpRvAdapter = new RpRvAdapter(this, mList);
        repayPlanRv.setAdapter(rpRvAdapter);
    }

    @Override
    public void getPlan(List<ReturnDataListBean.DataBean> dataList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        repayPlanRv.setLayoutManager(linearLayoutManager);
        RpRvAdapter rpRvAdapter = new RpRvAdapter(this, dataList);
        repayPlanRv.setAdapter(rpRvAdapter);
    }
}
