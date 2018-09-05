package com.superwanttoborrow.ui.bindbank;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindBankContract {

    interface View extends BaseView {

        void isReal(Boolean isBank);

        void isBank(String bankName);
    }

    interface  Presenter extends BasePresenter<View> {

        void isReal(Context context);

        void isBank(Context context,String bank);
    }
}
