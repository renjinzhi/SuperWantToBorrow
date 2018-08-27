package com.superwanttoborrow.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseFragment;
import com.superwanttoborrow.ui.progressquery.ProgressQueryActivity;
import com.superwanttoborrow.ui.realname.RealNameActivity;
import com.superwanttoborrow.widget.EasyPickerView;
import com.superwanttoborrow.widget.bannerhelper.widget.CycleView;

import java.util.ArrayList;

/**
 * @author renji
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View, View.OnClickListener {

    private ImageView activity_bar_img;
    private CycleView cycleView;
    private EasyPickerView picker_money;
    private EasyPickerView picker_time;
    private Button home_button_borrow;
    private Button home_button_select;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        activity_bar_img = (ImageView) view.findViewById(R.id.activity_bar_img);
        cycleView = (CycleView) view.findViewById(R.id.cycleView);
        picker_money = (EasyPickerView) view.findViewById(R.id.picker_money);
        picker_time = (EasyPickerView) view.findViewById(R.id.picker_time);
        home_button_borrow = (Button) view.findViewById(R.id.home_button_borrow);
        home_button_select = (Button) view.findViewById(R.id.home_button_select);

        final ArrayList<String> data = new ArrayList<String>();
        final ArrayList<String> seconds = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            data.add("￥"+i + "00");
        }
        seconds.add("7天");
        seconds.add("14天");
        seconds.add("30天");
        picker_money.setDataList(data);
        picker_time.setDataList(seconds);
        picker_money.moveBy(data.size() / 2);
        picker_time.moveBy(seconds.size() / 2);
        picker_money.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {

            }

            @Override
            public void onScrollFinished(int curIndex) {
                data.get(curIndex);
            }
        });

        picker_time.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {

            }

            @Override
            public void onScrollFinished(int curIndex) {
                seconds.get(curIndex);
            }
        });

        home_button_borrow.setOnClickListener(this);
        home_button_select.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button_borrow:
                startActivity(new Intent(getContext(), RealNameActivity.class));
                break;
            case R.id.home_button_select:
                startActivity(new Intent(getContext(), ProgressQueryActivity.class));
                break;
        }
    }
}
