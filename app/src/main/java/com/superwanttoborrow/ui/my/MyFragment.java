package com.superwanttoborrow.ui.my;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView my_img_title;
    private SharedPreferences sharedPreferences;

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
        my_img_title = (ImageView) view.findViewById(R.id.my_img_title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //登录注册
            case R.id.my_tv_login:
                if ("请登录/注册".equals(my_tv_login.getText().toString())) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                } else {

                }
                break;
            //借款记录
            case R.id.repay_tv_record:
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    startActivity(new Intent(getContext(), BorrowRecordActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //改绑银行卡
            case R.id.my_tv_bank:
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    startActivity(new Intent(getContext(), ChangeBankActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //帮助中心
            case R.id.my_tv_help:
                startActivity(new Intent(getContext(), HelpActivity.class));
                break;
            //安全设置
            case R.id.my_tv_set:
                sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    startActivity(new Intent(getContext(), SafeSettingActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }


    public void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("User", 0);
        String user = sp.getString("user", null);
        if (!TextUtils.isEmpty(user)) {
            my_tv_login.setText(user);
//            my_img_title.setImageResource(R.mipmap.touxiang);
        } else {
            my_tv_login.setText("请登录/注册");
//            my_img_title.setImageResource(R.mipmap.login_nor);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = getActivity().getSharedPreferences("User", 0);
        String user = sharedPreferences.getString("user", null);
        mPresenter.getUserDetails(getContext(),user);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            sharedPreferences = getActivity().getSharedPreferences("User", 0);
            String user = sharedPreferences.getString("user", null);
            mPresenter.getUserDetails(getContext(),user);
        }
    }
}
