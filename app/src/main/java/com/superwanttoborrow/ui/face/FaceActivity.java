package com.superwanttoborrow.ui.face;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FaceActivity extends MVPBaseActivity<FaceContract.View, FacePresenter> implements FaceContract.View, View.OnClickListener {

    private ImageView face_back;
    private Button face_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        initView();
    }

    private void initView() {
        face_back = (ImageView) findViewById(R.id.face_back);
        face_back.setOnClickListener(view -> finish());
        face_button = (Button) findViewById(R.id.face_button);
        face_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.face_button:
                break;
        }
    }
}
