package com.superwanttoborrow.ui.basicinformation;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.contacts.ContactsActivity;
import com.superwanttoborrow.utils.BdLocationUtil;
import com.superwanttoborrow.utils.DialogHelp;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BasicInformationActivity extends MVPBaseActivity<BasicInformationContract.View, BasicInformationPresenter> implements BasicInformationContract.View, View.OnClickListener {


    private ImageView basic_information_back;
    private TextView basic_information_name_tv;
    private TextView basic_information_id_tv;
    private TextView basic_information_phone_tv;
    private TextView basic_information_city_tv;
    private Spinner basic_information_spinner_money;
    private Button basic_information_button_next;
    private boolean b;
    private String incomeRange;
    private EditText bi_ed_adder;
    private EditText basic_information_ed_weixin;
    private EditText basic_information_ed_qq;
    private String customName;
    private String customId;
    private String adder;
    private ImageView basic_information_location;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        initView();
        initData();
    }

    private void initView() {
        basic_information_back = (ImageView) findViewById(R.id.basic_information_back);
        basic_information_back.setOnClickListener(this);
        basic_information_name_tv = (TextView) findViewById(R.id.basic_information_name_tv);
        basic_information_id_tv = (TextView) findViewById(R.id.basic_information_id_tv);
        basic_information_phone_tv = (TextView) findViewById(R.id.basic_information_phone_tv);
        basic_information_city_tv = (TextView) findViewById(R.id.basic_information_city_tv);
        basic_information_spinner_money = (Spinner) findViewById(R.id.basic_information_spinner_money);
        basic_information_button_next = (Button) findViewById(R.id.basic_information_button_next);
        basic_information_button_next.setOnClickListener(this);
        bi_ed_adder = (EditText) findViewById(R.id.bi_ed_adder);
        basic_information_ed_weixin = (EditText) findViewById(R.id.basic_information_ed_weixin);
        basic_information_ed_qq = (EditText) findViewById(R.id.basic_information_ed_qq);
        basic_information_location = (ImageView) findViewById(R.id.basic_information_location);
        basic_information_location.setOnClickListener(this::onClick);
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        basic_information_name_tv.setText(sharedPreferences.getString("name", null));
        basic_information_id_tv.setText(sharedPreferences.getString("idCard", null));
        basic_information_phone_tv.setText(sharedPreferences.getString("user", null));
        ArrayList<String> moneys = new ArrayList<>();
        moneys.add("2000以下");
        moneys.add("2000-5000");
        moneys.add("5000以上");
//        moneys.add("");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moneys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        basic_information_spinner_money.setAdapter(adapter);
        basic_information_spinner_money.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        incomeRange = "1001";
                        break;
                    case 1:
                        incomeRange = "1002";
                        break;
                    case 2:
                        incomeRange = "1003";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        basic_information_spinner_money.setSelection(3);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_information_back:
                finish();
                break;
            case R.id.basic_information_button_next:
                adder = bi_ed_adder.getText().toString();
                customId = basic_information_ed_qq.getText().toString();
                customName = basic_information_ed_weixin.getText().toString();

                startActivity(new Intent(this, ContactsActivity.class));

                SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("incomeRange", incomeRange);
                edit.putString("customId", customId);
                edit.putString("customName", customName);
                break;
            case R.id.basic_information_location:
                getPermission();
                break;
        }
    }


    private void getPermission() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Permission.LOCATION, Permission.CONTACTS)
                .callback(listener)
                .start();
    }

    private PermissionListener listener = new PermissionListener() {

        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(BasicInformationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
                    // TODO 执行拥有权限时的下一步。
                    if (AndPermission.hasPermission(BasicInformationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
                        getLocation();
                    }
                } else {
                    // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                    DialogHelp.showNormalDialog(BasicInformationActivity.this);
                }
            }

        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(BasicInformationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
                    if (AndPermission.hasAlwaysDeniedPermission(BasicInformationActivity.this, deniedPermissions)) {
                        if (AndPermission.hasPermission(BasicInformationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
                            getLocation();
                        }
                    } else {
                        // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                        DialogHelp.showNormalDialog(BasicInformationActivity.this);
                    }
                }
            }
        }
    };

    private void getLocation() {
        progressDialog = ProgressDialog.show(this,"请稍等...","正在获取定位信息...",true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        BdLocationUtil.getInstance().requestLocation(location -> {
            progressDialog.dismiss();
            if (location == null) {
                return;
            }
            if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                String mCounty = location.getCountry();        //获取国家
                String mProvince = location.getProvince();     //获取省份
                String mCity = location.getCity();             //获取城市
                String mDistrict = location.getDistrict();     //获取区
                basic_information_city_tv.setText(mProvince +"   "+ mCity);
            }
        });
    }

}
