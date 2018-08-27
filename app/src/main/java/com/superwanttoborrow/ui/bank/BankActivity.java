package com.superwanttoborrow.ui.bank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.BankRvAdapter;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BankActivity extends MVPBaseActivity<BankContract.View, BankPresenter> implements BankContract.View {


    private ImageView basic_information_back;
    private RecyclerView bank_rv;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        initView();
        initData();
    }

    private void initView() {
        basic_information_back = (ImageView) findViewById(R.id.basic_information_back);
        basic_information_back.setOnClickListener((view -> finish()));
        bank_rv = (RecyclerView) findViewById(R.id.bank_rv);
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
        bank_rv.setLayoutManager(linearLayoutManager);
        BankRvAdapter bankRvAdapter = new BankRvAdapter(this, mList);
        bank_rv.setAdapter(bankRvAdapter);
        }
}
