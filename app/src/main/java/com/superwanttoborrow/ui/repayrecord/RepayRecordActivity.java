package com.superwanttoborrow.ui.repayrecord;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.RrRvAdapter;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RepayRecordActivity extends MVPBaseActivity<RepayRecordContract.View, RepayRecordPresenter> implements RepayRecordContract.View {


    private ImageView repayRecordBack;
    private RecyclerView repayRecordRv;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_record);
        initView();
        initData();
    }

    private void initView() {
        repayRecordBack = (ImageView) findViewById(R.id.repay_record_back);
        repayRecordRv = (RecyclerView) findViewById(R.id.repay_record_rv);
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
        repayRecordRv.setLayoutManager(linearLayoutManager);
        RrRvAdapter rrRvAdapter = new RrRvAdapter(this, mList);
        repayRecordRv.setAdapter(rrRvAdapter);
        }
}
