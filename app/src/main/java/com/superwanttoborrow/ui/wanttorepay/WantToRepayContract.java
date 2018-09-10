package com.superwanttoborrow.ui.wanttorepay;

import android.content.Context;
import android.widget.Button;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WantToRepayContract {
    interface View extends BaseView {

        void repay();

        void getRepay(ReturnBean.DataBean dataBean);
    }

    interface  Presenter extends BasePresenter<View> {

        void repay(Context context,String checkCode);

        void getCode(Context context, Button button);

        void getRepay(Context context);


    }
}
