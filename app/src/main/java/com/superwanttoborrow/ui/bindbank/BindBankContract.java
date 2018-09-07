package com.superwanttoborrow.ui.bindbank;

import android.content.Context;
import android.widget.Button;

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

        void repayment();
    }

    interface  Presenter extends BasePresenter<View> {

        void isReal(Context context);

        void isBank(Context context,String bank);

        void getCode(Context context, Button tv);

        void repayment(Context context);

        void checkCode(Context context,String code);
    }
}
