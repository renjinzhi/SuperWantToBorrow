package com.superwanttoborrow.ui.changebank;

import android.content.Context;
import android.widget.Button;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeBankContract {

    interface View extends BaseView {

        void getBankCode(boolean isData);

        void isBank(String bankName);

    }

    interface  Presenter extends BasePresenter<View> {

        void getBankCode(Context context, String name, String cardId, String mobile, String bankCard,String depositBank);

        void getCode(Context context, Button tv);

        void checkCode(Context context,String code,String idCard,String bankNum,String bankName,String name);

        void isBank(Context context,String bank);
    }
}
