package com.superwanttoborrow.ui.contacts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.face.FaceActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ContactsActivity extends MVPBaseActivity<ContactsContract.View, ContactsPresenter> implements ContactsContract.View, View.OnClickListener {


    private ImageView contacts_back;
    private Spinner contacts_first_spinner;
    private TextView contacts_name_tv;
    private TextView contacts_phone_tv;
    private Spinner contacts_second_spinner;
    private TextView contacts_name_tv_2;
    private TextView contacts_phone_tv_2;
    private Button contacts_button_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_contacts);
        initView();
    }

    private void initView() {
        contacts_back = (ImageView) findViewById(R.id.contacts_back);
        contacts_back.setOnClickListener(this);
        contacts_first_spinner = (Spinner) findViewById(R.id.contacts_first_spinner);
        contacts_name_tv = (TextView) findViewById(R.id.contacts_name_tv);
        contacts_phone_tv = (TextView) findViewById(R.id.contacts_phone_tv);
        contacts_second_spinner = (Spinner) findViewById(R.id.contacts_second_spinner);
        contacts_name_tv_2 = (TextView) findViewById(R.id.contacts_name_tv_2);
        contacts_phone_tv_2 = (TextView) findViewById(R.id.contacts_phone_tv_2);
        contacts_button_next = (Button) findViewById(R.id.contacts_button_next);

        contacts_button_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contacts_back:
                finish();
                break;
            case R.id.contacts_button_next:
                startActivity(new Intent(this, FaceActivity.class));
                break;
        }
    }
}
