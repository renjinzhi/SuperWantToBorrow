package com.superwanttoborrow.ui.face;

import android.content.Context;

import com.superwanttoborrow.mvp.BasePresenter;
import com.superwanttoborrow.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FaceContract {
    interface View extends BaseView {

        void postPace();
    }

    interface  Presenter extends BasePresenter<View> {
        void postFace(Context context,String fileContent, String fileName, String fileType,String delta);
    }
}
