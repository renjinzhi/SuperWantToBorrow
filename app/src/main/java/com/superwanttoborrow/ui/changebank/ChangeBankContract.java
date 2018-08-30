package com.superwanttoborrow.ui.changebank;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeBankContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {

        void getBankCode(Context context, String name, String cardId, String mobile, String bankCard);
    }
}
