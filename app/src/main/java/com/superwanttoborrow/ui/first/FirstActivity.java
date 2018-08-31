package com.superwanttoborrow.ui.first;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hjm.bottomtabbar.BottomTabBar;
import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.home.HomeFragment;
import com.superwanttoborrow.ui.my.MyFragment;
import com.superwanttoborrow.ui.repayment.RepaymentFragment;


/**
 * @author renji
 */

public class FirstActivity extends MVPBaseActivity<FirstContract.View, FirstPresenter> implements FirstContract.View {

    private BottomTabBar bottom_tab_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView() {
        bottom_tab_bar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        bottom_tab_bar.init(getSupportFragmentManager())
                .setFontSize(10)
                .addTabItem("首页", R.mipmap.homepage, R.mipmap.homepage_1, HomeFragment.class)
                .addTabItem("还款", R.mipmap.repayment, R.mipmap.repayment_1, RepaymentFragment.class)
                .addTabItem("我的", R.mipmap.my, R.mipmap.my_1, MyFragment.class);
    }
}
