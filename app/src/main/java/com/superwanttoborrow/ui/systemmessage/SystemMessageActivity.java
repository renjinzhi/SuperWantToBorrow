package com.superwanttoborrow.ui.systemmessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.adapters.SmRvAdapter;
import com.superwanttoborrow.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SystemMessageActivity extends MVPBaseActivity<SystemMessageContract.View, SystemMessagePresenter> implements SystemMessageContract.View {


    private ImageView system_message_back;
    private RecyclerView system_message_rv;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        initView();
        initData();
    }

    private void initView() {
        system_message_back = (ImageView) findViewById(R.id.system_message_back);
        system_message_back.setOnClickListener(view -> finish());
        system_message_rv = (RecyclerView) findViewById(R.id.system_message_rv);
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
        system_message_rv.setLayoutManager(linearLayoutManager);
        SmRvAdapter smRvAdapter = new SmRvAdapter(this, mList);
        system_message_rv.setAdapter(smRvAdapter);
        }
}
