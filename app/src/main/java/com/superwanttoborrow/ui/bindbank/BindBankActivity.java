package com.superwanttoborrow.ui.bindbank;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindBankActivity extends MVPBaseActivity<BindBankContract.View, BindBankPresenter> implements BindBankContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank);
    }
}
