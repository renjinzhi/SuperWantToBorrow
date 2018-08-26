package com.superwanttoborrow.ui.wanttoextension;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WantToExtensionActivity extends MVPBaseActivity<WantToExtensionContract.View, WantToExtensionPresenter> implements WantToExtensionContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_want_to_extension);
    }
}
