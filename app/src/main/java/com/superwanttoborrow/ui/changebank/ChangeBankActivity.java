package com.superwanttoborrow.ui.changebank;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeBankActivity extends MVPBaseActivity<ChangeBankContract.View, ChangeBankPresenter> implements ChangeBankContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bank);
    }
}
