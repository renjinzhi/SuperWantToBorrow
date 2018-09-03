package com.superwanttoborrow.ui.contacts;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.face.FaceActivity;
import com.superwanttoborrow.utils.DialogHelp;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;


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
    private boolean boo;
    private TextView contacts_name_title;
    private TextView contacts_phone_title;
    private TextView contacts_name_title_2;
    private TextView contacts_phone_title_2;
    private ArrayList<String> relations1;
    private ArrayList<String> relations2;
    private String relation1;
    private String relation2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_contacts);
        initView();
        initData();
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
        contacts_name_title = (TextView) findViewById(R.id.contacts_name_title);
        contacts_name_title.setOnClickListener(this);
        contacts_phone_title = (TextView) findViewById(R.id.contacts_phone_title);
        contacts_phone_title.setOnClickListener(this);
        contacts_name_title_2 = (TextView) findViewById(R.id.contacts_name_title_2);
        contacts_name_title_2.setOnClickListener(this);
        contacts_phone_title_2 = (TextView) findViewById(R.id.contacts_phone_title_2);
        contacts_phone_title_2.setOnClickListener(this);
    }
    
    private void initData(){
        relations1 = new ArrayList<>();
        relations2 = new ArrayList<>();
        relations1.add("");
        relations1.add("配偶");
        relations1.add("直系亲属");
        relations2.add("");
        relations2.add("配偶");
        relations2.add("直系亲属");
        relations2.add("同事");
        relations2.add("朋友");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, relations1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contacts_first_spinner.setAdapter(adapter);
        contacts_first_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                relation1 = relations1.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, relations2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contacts_second_spinner.setAdapter(adapter2);
        contacts_second_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                relation2 = relations2.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
            case R.id.contacts_name_title:
                boo = true;
                getPermission();
                break;
            case R.id.contacts_phone_title:
                boo = true;
                getPermission();
                break;
            case R.id.contacts_name_title_2:
                boo = false;
                getPermission();
                break;
            case R.id.contacts_phone_title_2:
                boo = false;
                getPermission();
                break;
        }
    }

    private void toNote() {
        //点击选择通讯录
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示：").setMessage("确定从通讯录选择联系人？").setCancelable(true)
                .setPositiveButton("是", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    startActivityForResult(new Intent(
                            Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI), 0);
                }).setNegativeButton("否", (dialogInterface, i) -> dialogInterface.dismiss()).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 400:
                // 400是上面defineSettingDialog()的第二个参数。
                // 在这里检查你需要的权限是否被允许，并做相应的操作。
                break;
            case 0:
                break;
            default:
                break;
        }
        if (resultCode == Activity.RESULT_OK) {
            ActivityCompat.requestPermissions(ContactsActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}, 101);
            if (AndPermission.hasPermission(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE})) {
                ContentResolver reContentResolverol = getContentResolver();
                Uri contactData = data.getData();
                @SuppressWarnings("deprecation")
                Cursor cursor = managedQuery(contactData, null, null, null, null);
                if (cursor != null && cursor.getCount() >= 1) {
                    if (cursor.moveToFirst()) {
                        try {
                            String username = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));
                            String contactId = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
                            Cursor phone = reContentResolverol.query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    android.provider.ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                    null,
                                    null);
                            while (phone.moveToNext()) {
                                String userNumber = phone.getString(phone.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER));
                                if (boo) {
                                    contacts_name_tv.setText(username);
                                    contacts_phone_tv.setText(userNumber);
                                } else {
                                    contacts_name_tv_2.setText(username);
                                    contacts_phone_tv_2.setText(userNumber);
                                }
                            }
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "请打开通讯录权限", Toast.LENGTH_LONG).show();
                            DialogHelp.showNormalDialog(this);
                        }
                    } else if (Build.MANUFACTURER.equals("OPPO")) {
                        DialogHelp.showNormalDialog(ContactsActivity.this);
                    }
                } else {
                    DialogHelp.showNormalDialog(ContactsActivity.this);
                }
            } else {
                DialogHelp.showNormalDialog(ContactsActivity.this);
            }
        }
    }

    //动态权限处理
    private void getPermission() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Permission.CONTACTS, Permission.PHONE)
                .rationale(rationaleListener)
                .callback(listener)
                .start();
    }

    /**
     * Rationale支持，这里自定义对话框。
     */
    private RationaleListener rationaleListener = (requestCode, rationale) -> com.yanzhenjie.alertdialog.AlertDialog.newBuilder(this)
            .setTitle("友好提醒")
            .setMessage("你已拒绝过通讯录，沒有通讯录权限无法为你添加紧急联系人！")
            .setPositiveButton("好，给你", (dialog, which) -> {
                rationale.resume();
            })
            .setNegativeButton("我拒绝", (dialog, which) -> {
                rationale.cancel();
            }).show();

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE})) {
                    toNote();
                    // TODO 执行拥有权限时的下一步。
                } else {
                    // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                    DialogHelp.showNormalDialog(ContactsActivity.this);
                }
            }

        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE})) {
                    if (AndPermission.hasAlwaysDeniedPermission(ContactsActivity.this, deniedPermissions)) {
                        toNote();
                    }
                } else {
                    // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                    DialogHelp.showNormalDialog(ContactsActivity.this);
                }

            }
        }
    };
}
