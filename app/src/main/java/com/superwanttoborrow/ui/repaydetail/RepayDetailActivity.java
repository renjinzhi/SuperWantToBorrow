package com.superwanttoborrow.ui.repaydetail;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepayDetailActivity extends MVPBaseActivity<RepayDetailContract.View, RepayDetailPresenter> implements RepayDetailContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_detail);
    }
}
