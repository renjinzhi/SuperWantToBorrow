package com.superwanttoborrow.ui.face;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.megvii.idcardlib.util.Util;
import com.megvii.licensemanager.Manager;
import com.megvii.livenessdetection.LivenessLicenseManager;
import com.megvii.livenesslib.LivenessActivity;
import com.rong360.app.crawler.CrawlerManager;
import com.rong360.app.crawler.CrawlerStatus;
import com.rong360.app.crawler.Util.CommonUtil;
import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.bindbank.BindBankActivity;
import com.superwanttoborrow.utils.Bitmap2Base64;
import com.superwanttoborrow.utils.DialogHelp;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;
import java.util.Map;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FaceActivity extends MVPBaseActivity<FaceContract.View, FacePresenter> implements FaceContract.View, View.OnClickListener {

    private ImageView face_back;
    private Button face_button;
    private ProgressDialog progressDialog;
    private String uuid;
    private String fileContent;
    private String mPrivateKey;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        initView();
        netWorkWarranty();
    }

    private void initView() {
        face_back = (ImageView) findViewById(R.id.face_back);
        face_back.setOnClickListener(view -> finish());
        face_button = (Button) findViewById(R.id.face_button);
        face_button.setOnClickListener(this);
        uuid = Util.getUUIDString(this);
        mPrivateKey = CommonUtil.getFromAssets("private_key.pem");
        CrawlerManager.getInstance().setDebug(true);//打开debug
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.face_button:
//                getPermission();
                startActivity(new Intent(this,BindBankActivity.class));
                break;
        }
    }

    private void netWorkWarranty() {
        progressDialog = ProgressDialog.show(this, "请稍等...", "正在联网授权中...", true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        new Thread(() -> {
            Manager manager = new Manager(FaceActivity.this);
            LivenessLicenseManager licenseManager = new LivenessLicenseManager(FaceActivity.this);
            manager.registerLicenseManager(licenseManager);
            manager.takeLicenseFromNetwork(uuid);
            if (licenseManager.checkCachedLicense() > 0) {
                //授权成功
                progressDialog.dismiss();
            } else {
                //授权失败
                progressDialog.dismiss();
                Looper.prepare();
                Toast.makeText(FaceActivity.this, "联网授权失败！请检查网络或找服务商", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }


    private static final int PAGE_INTO_LIVENESS = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAGE_INTO_LIVENESS && resultCode == RESULT_OK) {
            String delta = data.getStringExtra("delta");
            Map<String, byte[]> images = (Map<String, byte[]>) data.getSerializableExtra("images");
            if (images.containsKey("image_best")) {
                byte[] bestImg = images.get("image_best");
                if (bestImg != null && bestImg.length > 0) {
                    fileContent = Bitmap2Base64.byte2Base64StringFun(bestImg);
                }
            }
            mPresenter.postFace(FaceActivity.this, fileContent, "_face.png", "A03", delta);

        }
    }


    private void enterNextPage() {
        startActivityForResult(new Intent(this, LivenessActivity.class), PAGE_INTO_LIVENESS);
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

    private RationaleListener rationaleListener = (requestCode, rationale) -> {
        com.yanzhenjie.alertdialog.AlertDialog.newBuilder(FaceActivity.this)
                .setTitle("友好提醒")
                .setMessage("你已拒绝过打开相机，沒有相机权限无法为你实名认证！")
                .setPositiveButton("好，给你", (dialog, which) -> {
                    rationale.resume();
                })
                .setNegativeButton("我拒绝", (dialog, which) -> {
                    rationale.cancel();
                }).show();
    };

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(FaceActivity.this, new String[]{Manifest.permission.CAMERA})) {
                    // TODO 执行拥有权限时的下一步。
                    if (AndPermission.hasPermission(FaceActivity.this, new String[]{Manifest.permission.CAMERA})) {
                        enterNextPage();
                    }
                } else {
                    // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                    DialogHelp.showNormalDialog(FaceActivity.this);
                }
            }

        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(FaceActivity.this, new String[]{Manifest.permission.CAMERA})) {
                    if (AndPermission.hasAlwaysDeniedPermission(FaceActivity.this, deniedPermissions)) {
                        if (AndPermission.hasPermission(FaceActivity.this, new String[]{Manifest.permission.CAMERA})) {
                            enterNextPage();
                        }
                    } else {
                        // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                        DialogHelp.showNormalDialog(FaceActivity.this);
                    }
                }
            }
        }
    };


    private void startCrawlerTask(String module) {
        CrawlerStatus crawlerStatus = new CrawlerStatus();
        crawlerStatus.type = module;
        crawlerStatus.taskid = String.valueOf(System.currentTimeMillis());
        crawlerStatus.appname = "com.superwanttoborrow";//自定义name，可以传包名
        crawlerStatus.privatekey = mPrivateKey;
        crawlerStatus.merchant_id = "2010757";//添加分配的appid
        crawlerStatus.bPhoneSupportChange = false;//机构设置CrawlerStatus的属性变量bPhoneSupportChange为false即用户手机号不可变更

        //以下为身份信息三要素，必传
        crawlerStatus.real_name = "小兵";//姓名
        crawlerStatus.id_card = "231121198602286438";//身份证
        crawlerStatus.cellphone = "13512345678";//手机

        CrawlerManager.getInstance().startCrawlerByType(
                crawlerStatus1 -> {
                    switch (crawlerStatus1.status) {
                        case 2:
                            startActivity(new Intent(FaceActivity.this, BindBankActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(FaceActivity.this, BindBankActivity.class));
                            break;
                        default:
                            Toast.makeText(FaceActivity.this, getStringStatus(crawlerStatus1.status), Toast.LENGTH_SHORT)
                                    .show();
                            break;
                    }
                }, crawlerStatus);

    }


    private String getStringStatus(int status) {
        String text = null;
        switch (status) {
            case 0:
                text = "未初始化";
                break;
            case 1:
                text = "抓取开始";
                break;
            case 2:
                text = "登录成功";
                break;
            case 3:
                text = "抓取成功";
                break;
            case 4:
                text = "授权失败";
                break;
            case 5:
//                text = "用户返回界面";
                text = "取消授权";
                break;
            case 6:
                text = "打开登陆页面,支付宝和淘宝的登陆页面";
                break;
            default:
                break;
        }
        return text;
    }


    @Override
    public void postPace() {
        startCrawlerTask("mobile");
    }
}
