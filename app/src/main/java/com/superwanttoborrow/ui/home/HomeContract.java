package com.superwanttoborrow.ui.home;

import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeContract {

    interface View extends BaseView {
        void getBanner(List<ReturnDataListBean.DataBean> list);

        void getOther(ArrayList<String> money, ArrayList<ReturnBean.DataBean.SupportPeriodBean> supportPeriodList);

        void getRequestID(ReturnBean.DataBean dataBean);

        void setNullBanner();
    }

    interface  Presenter extends BasePresenter<View> {


        void getBanner(Context context);

        void getOther(Context context);

        void getRequestID(Context context);
    }
}
