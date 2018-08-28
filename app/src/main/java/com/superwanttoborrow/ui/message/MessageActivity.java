package com.superwanttoborrow.ui.message;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.systemmessage.SystemMessageActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageActivity extends MVPBaseActivity<MessageContract.View, MessagePresenter> implements MessageContract.View {

    private ImageView message_back;
    private TextView message_tv_mess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }

    private void initView() {
        message_back = (ImageView) findViewById(R.id.message_back);
        message_back.setOnClickListener(view -> finish());
        message_tv_mess = (TextView) findViewById(R.id.message_tv_mess);
        message_tv_mess.setOnClickListener(view -> startActivity(new Intent(this,SystemMessageActivity.class )));
    }
}
