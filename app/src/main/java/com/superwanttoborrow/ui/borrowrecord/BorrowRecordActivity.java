package com.superwanttoborrow.ui.borrowrecord;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.BrRvAdapter;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 借款记录界面
 */

public class BorrowRecordActivity extends MVPBaseActivity<BorrowRecordContract.View, BorrowRecordPresenter> implements BorrowRecordContract.View {


    private ImageView borrow_record_back;
    private RecyclerView borrow_record_rv;
    private List<String> mList;
    private List<ReturnBean.DataBean.HistoryRecordsBean> brList = new ArrayList<>();
    private ImageView borrow_record_img;
    private BrRvAdapter brRvAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_record);
        initView();
        mPresenter.getRecordBorrow(this);
    }

    private void initView() {
        borrow_record_back = (ImageView) findViewById(R.id.borrow_record_back);
        borrow_record_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        borrow_record_rv = (RecyclerView) findViewById(R.id.borrow_record_rv);
        borrow_record_img = (ImageView) findViewById(R.id.borrow_record_img);
    }

    private void initData() {
        mList = new ArrayList<String>();
        mList.add("aaa");
        mList.add("bbb");
        mList.add("ccc");
        mList.add("ddd");
        mList.add("eee");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        borrow_record_rv.setLayoutManager(linearLayoutManager);
        if ( null!= brList) {
            brRvAdapter = new BrRvAdapter(this, brList);
        }else {
            borrow_record_rv.setVisibility(View.GONE);
            borrow_record_img.setVisibility(View.VISIBLE);
        }
        borrow_record_rv.setAdapter(brRvAdapter);
    }

    //没有记录
    @Override
    public void getNoRecordBorrow() {
        borrow_record_rv.setVisibility(View.GONE);
        borrow_record_img.setVisibility(View.VISIBLE);
    }

    @Override
    public void getRecordBorrow(ReturnBean.DataBean dataBean) {
        brList = dataBean.getHistoryRecords();
        brList.add(dataBean.getCurrentRecord());
        initData();
    }
}
