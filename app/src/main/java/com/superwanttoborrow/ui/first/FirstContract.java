package com.superwanttoborrow.ui.first;

import android.app.ProgressDialog;
import android.content.Context;

import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FirstContract {

    interface View extends BaseView {

        void showUpdataDialog(ReturnBean.DataBean dataBean);

        void apkFinish();
    }

    interface  Presenter extends BasePresenter<View> {

        void getVersion(Context context);

        void downLoadApk(Context context, String filePath, ProgressDialog pd);

        void getUserDetauls(final Context context);
    }
}
