package com.superwanttoborrow.ui.realname;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RealNameContract {

    interface View extends BaseView {
        void postFront(String name,String idCard);

        void postFront2(int i);
    }

    interface  Presenter extends BasePresenter<View> {

        void postFront(Context context, String fileContent, String fileName, String fileType, int i);

    }
}
