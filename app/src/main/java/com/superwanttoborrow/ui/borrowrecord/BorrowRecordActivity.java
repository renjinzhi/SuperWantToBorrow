package com.superwanttoborrow.ui.borrowrecord;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.BrRvAdapter;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BorrowRecordActivity extends MVPBaseActivity<BorrowRecordContract.View, BorrowRecordPresenter> implements BorrowRecordContract.View {


    private ImageView borrow_record_back;
    private RecyclerView borrow_record_rv;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_record);
        initView();
        initData();
    }

    private void initView() {
        borrow_record_back = (ImageView) findViewById(R.id.borrow_record_back);
        borrow_record_back.setOnClickListener((view)->finish());
        borrow_record_rv = (RecyclerView) findViewById(R.id.borrow_record_rv);
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
        borrow_record_rv.setLayoutManager(linearLayoutManager);
        BrRvAdapter brRvAdapter = new BrRvAdapter(this, mList);
        borrow_record_rv.setAdapter(brRvAdapter);
    }
}
