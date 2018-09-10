package com.superwanttoborrow.ui.realname;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.megvii.idcardlib.IDCardScanActivity;
import com.megvii.idcardlib.util.Util;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.idcardquality.bean.IDCardAttr;
import com.megvii.licensemanager.Manager;
import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.basicinformation.BasicInformationActivity;
import com.superwanttoborrow.utils.DialogHelp;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.superwanttoborrow.utils.Bitmap2Base64.bitmapToBase64NONseal;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RealNameActivity extends MVPBaseActivity<RealNameContract.View, RealNamePresenter> implements RealNameContract.View, View.OnClickListener {

    private ImageView realname_back;
    private ImageView real_img_front;
    private ImageView real_img_verso;
    private ImageView real_img_head;
    private TextView real_name_tv;
    private TextView real_id_tv;
    private TextView real_phone_tv;
    private Button real_button_next;
    private ProgressDialog progressDialog;
    private String uuid;
    private int i;
    public static final int EXTERNAL_STORAGE_REQ_CAMERA_CODE = 10;
    private static final int INTO_IDCARDSCAN_PAGE = 100;
    private static final int REQUEST_CAMERA = 1;
    IDCardAttr.IDCardSide mIDCardSide;
    int mSide = 0;
    private Bitmap idcardBmp;
    private boolean front = false;
    private boolean verso = false;
    private boolean head = false;
    private String name;
    private String idCard;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realname);
        initView();
        initData();
        network();
    }

    private void initView() {
        realname_back = (ImageView) findViewById(R.id.realname_back);
        realname_back.setOnClickListener(this);
        real_img_front = (ImageView) findViewById(R.id.real_img_front);
        real_img_front.setOnClickListener(this::onClick);
        real_img_verso = (ImageView) findViewById(R.id.real_img_verso);
        real_img_verso.setOnClickListener(this::onClick);
        real_img_head = (ImageView) findViewById(R.id.real_img_head);
        real_img_head.setOnClickListener(this::onClick);
        real_name_tv = (TextView) findViewById(R.id.real_name_tv);
        real_id_tv = (TextView) findViewById(R.id.real_id_tv);
        real_phone_tv = (TextView) findViewById(R.id.real_phone_tv);
        real_button_next = (Button) findViewById(R.id.real_button_next);
        real_button_next.setOnClickListener(this);
        uuid = Util.getUUIDString(this);
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        real_phone_tv.setText(sharedPreferences.getString("user", null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.realname_back:
                finish();
                break;
            case R.id.real_button_next:
                if (!front) {
                    Toast.makeText(this, "请先扫描身份证正面", Toast.LENGTH_SHORT).show();
                } else if (!verso) {
                    Toast.makeText(this, "请先扫描身份证反面", Toast.LENGTH_SHORT).show();
                } else if (!head) {
                    Toast.makeText(this, "请先拍摄手持身份证正面照片", Toast.LENGTH_SHORT).show();
                } else
                    startActivity(new Intent(this, BasicInformationActivity.class));
                break;
            case R.id.real_img_front:
                i = 0;
                getPermission();
                break;
            case R.id.real_img_verso:
                if (front) {
                    i = 1;
                    getPermission();
                } else {
                    Toast.makeText(this, "请先扫描身份证正面", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.real_img_head:
                if (front && verso) {
                    i = 2;
                    getPermission();
                } else {
                    Toast.makeText(this, "请先扫描身份证正反面", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    //动态权限处理
    private void getPermission() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Permission.CONTACTS, Permission.CAMERA)
                .rationale(rationaleListener)
                .callback(listener)
                .start();
    }

    private RationaleListener rationaleListener = (requestCode, rationale) -> AlertDialog.newBuilder(this)
            .setTitle("友好提醒")
            .setMessage("你已拒绝过打开相机，沒有相机权限无法为你实名认证！")
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
                if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.CAMERA})) {
                    // TODO 执行拥有权限时的下一步。
                    if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.CAMERA})) {
                        switch (i) {
                            case 0:
                                requestCameraPerm(0);
                                break;
                            case 1:
                                requestCameraPerm(1);
                                break;
                            case 2:
                                getPermission2();
                                break;
                        }
                    }
                }
            } else {
                // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                DialogHelp.showNormalDialog(RealNameActivity.this);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.CAMERA})) {
                    if (AndPermission.hasAlwaysDeniedPermission(RealNameActivity.this, deniedPermissions)) {
                        if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.CAMERA})) {
                            switch (i) {
                                case 0:
                                    requestCameraPerm(0);
                                    break;
                                case 1:
                                    requestCameraPerm(1);
                                    break;
                                case 2:
                                    getPermission2();
                                    break;
                            }
                        }
                    } else {
                        // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                        DialogHelp.showNormalDialog(RealNameActivity.this);
                    }
                }
            }
        }
    };


    /**
     * 联网授权
     */
    private void network() {
        progressDialog = ProgressDialog.show(this, "请稍等...", "正在联网授权中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Manager manager = new Manager(RealNameActivity.this);
                IDCardQualityLicenseManager idCardLicenseManager = new IDCardQualityLicenseManager(
                        RealNameActivity.this);
                manager.registerLicenseManager(idCardLicenseManager);
                manager.takeLicenseFromNetwork(uuid);
                if (idCardLicenseManager.checkCachedLicense() > 0) {
                    //授权成功
                    progressDialog.dismiss();
                } else {
                    //授权失败
                    progressDialog.dismiss();
                    Looper.prepare();
                    Toast.makeText(RealNameActivity.this, "联网授权失败！请检查网络或找服务商", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }


    private void requestCameraPerm(int side) {
        mSide = side;
        if (Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        EXTERNAL_STORAGE_REQ_CAMERA_CODE);
            } else {
                enterNextPage(side);
            }
        } else {
            enterNextPage(side);
        }
    }

    private void enterNextPage(int side) {
        Intent intent = new Intent(this, IDCardScanActivity.class);
        intent.putExtra("side", side);
        intent.putExtra("isvertical", true);
        startActivityForResult(intent, INTO_IDCARDSCAN_PAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTO_IDCARDSCAN_PAGE && resultCode == RESULT_OK) {
            mIDCardSide = data.getIntExtra("side", 0) == 0 ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT
                    : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK;
            byte[] idcardImgData = data.getByteArrayExtra("idcardImg");
            idcardBmp = BitmapFactory.decodeByteArray(idcardImgData, 0,
                    idcardImgData.length);
            if (mIDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                String bitmapFront = bitmapToBase64NONseal(idcardBmp);
                real_img_front.setImageBitmap(idcardBmp);
                mPresenter.postFront(this, bitmapFront, "_front.png", "A01", 0);
            } else {
                String bitmapVerso = bitmapToBase64NONseal(idcardBmp);
                real_img_verso.setImageBitmap(idcardBmp);
                mPresenter.postFront(this, bitmapVerso, "_verso.png", "A02", 1);
            }
        } else if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            real_img_head.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            mPresenter.postFront(this, bitmapToBase64NONseal(BitmapFactory.decodeFile(file.getAbsolutePath())), "_head", "A06", 2);
        }
    }

    @Override
    public void postFront(String name, String idCard) {
        this.name = name;
        this.idCard = idCard;
        front = true;
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("applicantName", name);
        edit.putString("cardId", idCard);
        edit.apply();
        real_name_tv.setText(name);
        real_id_tv.setText(idCard);
    }


    @Override
    public void postFront2(int i) {
        switch (i) {
            case 1:
                verso = true;
                break;
            case 2:
                head = true;
                break;
        }

    }

    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.superwanttoborrow.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    //动态权限处理
    private void getPermission2() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Permission.CONTACTS, Permission.CAMERA)
                .rationale(rationaleListener2)
                .callback(listener2)
                .start();
    }

    private RationaleListener rationaleListener2 = (requestCode, rationale) -> {
        AlertDialog.newBuilder(this)
                .setTitle("友好提醒")
                .setMessage("你已拒绝过存储图片，沒有存储权限无法为你实名认证！")
                .setPositiveButton("好，给你", (dialog, which) -> {
                    rationale.resume();
                })
                .setNegativeButton("我拒绝", (dialog, which) -> {
                    rationale.cancel();
                }).show();
    };

    private PermissionListener listener2 = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    // TODO 执行拥有权限时的下一步。
                    if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                        useCamera();
                    }
                }
            } else {
                // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                DialogHelp.showNormalDialog(RealNameActivity.this);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    if (AndPermission.hasAlwaysDeniedPermission(RealNameActivity.this, deniedPermissions)) {
                        if (AndPermission.hasPermission(RealNameActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                            useCamera();
                        }
                    } else {
                        // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                        DialogHelp.showNormalDialog(RealNameActivity.this);
                    }
                }
            }
        }
    };


}
