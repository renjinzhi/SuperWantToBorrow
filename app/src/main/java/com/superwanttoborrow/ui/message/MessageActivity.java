package com.superwanttoborrow.ui.message;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.systemmessage.SystemMessageActivity;

import java.io.Serializable;


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
        message_tv_mess.setOnClickListener(view -> mPresenter.getMessage(this));
    }

    @Override
    public void getMessage(ReturnBean.DataBean dataBean) {
        if (null != dataBean.getSystemSms() && dataBean.getSystemSms().size() != 0) {
            Intent intent = new Intent(this, SystemMessageActivity.class);
            intent.putExtra("systemMessList",(Serializable) dataBean.getSystemSms());
            startActivity(intent);
        }else {
            Toast.makeText(this,"暂时没有消息",Toast.LENGTH_SHORT).show();
        }
    }
}
