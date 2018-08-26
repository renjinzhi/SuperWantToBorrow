package com.superwanttoborrow.ui.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseFragment;
import com.superwanttoborrow.ui.borrowrecord.BorrowRecordActivity;
import com.superwanttoborrow.ui.changebank.ChangeBankActivity;
import com.superwanttoborrow.ui.help.HelpActivity;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.ui.safesetting.SafeSettingActivity;

/**
 * @author renji
 */

public class MyFragment extends MVPBaseFragment<MyContract.View, MyPresenter> implements MyContract.View, View.OnClickListener {


    private TextView my_tv_login;
    private TextView my_tv_record;
    private TextView my_tv_bank;
    private TextView my_tv_help;
    private TextView my_tv_set;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        my_tv_login = (TextView) view.findViewById(R.id.my_tv_login);
        my_tv_login.setOnClickListener(this);
        my_tv_record = (TextView) view.findViewById(R.id.repay_tv_record);
        my_tv_record.setOnClickListener(this);
        my_tv_bank = (TextView) view.findViewById(R.id.my_tv_bank);
        my_tv_bank.setOnClickListener(this);
        my_tv_help = (TextView) view.findViewById(R.id.my_tv_help);
        my_tv_help.setOnClickListener(this);
        my_tv_set = (TextView) view.findViewById(R.id.my_tv_set);
        my_tv_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_tv_login:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.repay_tv_record:
                startActivity(new Intent(getContext(), BorrowRecordActivity.class));
                break;
            case R.id.my_tv_bank:
                startActivity(new Intent(getContext(), ChangeBankActivity.class));
                break;
            case R.id.my_tv_help:
                startActivity(new Intent(getContext(), HelpActivity.class));
                break;
            case R.id.my_tv_set:
                startActivity(new Intent(getContext(), SafeSettingActivity.class));
                break;
        }
    }
}
