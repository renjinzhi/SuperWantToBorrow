package com.superwanttoborrow.ui.wanttorepay;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WantToRepayActivity extends MVPBaseActivity<WantToRepayContract.View, WantToRepayPresenter> implements WantToRepayContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_repay);
    }
}
