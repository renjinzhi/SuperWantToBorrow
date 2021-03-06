package com.superwanttoborrow.ui.wanttoextension;

import android.content.Context;
import android.widget.Button;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WantToExtensionContract {
    interface View extends BaseView {

        void extension();
    }

    interface  Presenter extends BasePresenter<View> {

        void extension(Context context,String checkCode);

        void getCode(Context context, Button button);
    }
}
