package com.superwanttoborrow.ui.repayplan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.RpRvAdapter;
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
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_repay_plan);
        initView();
        initData();
    }

    private void initView() {
        repayPlanBack = (ImageView) findViewById(R.id.repay_plan_back);
        repayPlanRv = (RecyclerView) findViewById(R.id.repay_plan_rv);
    }

    private void initData(){
        mList = new ArrayList<String>();
        mList.add("aaa");
        mList.add("bbb");
        mList.add("ccc");
        mList.add("ddd");
        mList.add("eee");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        repayPlanRv.setLayoutManager(linearLayoutManager);
        RpRvAdapter rpRvAdapter = new RpRvAdapter(this, mList);
        repayPlanRv.setAdapter(rpRvAdapter);
        }

}
