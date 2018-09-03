package com.megvii.meglive_still.demo;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.megvii.meglive_sdk.listener.DetectCallback;
import com.megvii.meglive_sdk.listener.PreCallback;
import com.megvii.meglive_sdk.manager.MegLiveManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static android.os.Build.VERSION_CODES.M;

/**
 * 使用说明
 *   使用前请商家申请自己的apiKey 和 secret
 *   在使用无源对比是需要补全比对所需要的底库图片
 *   在使用有源比对时需要补全身份号和姓名
 *   demo中所包含的两个网络请求和签名建议商家放在自己服务器端去做，确保自己的apikey和secret安全
 *   网络请求中只包含了必选字段 商家需要根据自己需要完善 详细说明参考API文档
 */

public class MainActivity extends Activity implements View.OnClickListener, DetectCallback, PreCallback {
    private static final int ACTION_YY = 1;
    private static final int ACTION_WY = 2;
    private static final int FMP_YY = 3;
    private static final int FMP_WY = 4;
    private Button bt_action_yy, bt_action_wy, bt_fmp_yy, bt_fmp_wy;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE = 101;
    private ProgressDialog mProgressDialog;
    private MegLiveManager megLiveManager;
    private static final String GET_BIZTOKEN_URL = "";
    private static final String VERIFY_URL = "";
    private static final String API_KEY = "";
    private static final String SECRET = "";
    private String sign = "";
    private static final String SIGN_VERSION = "hmac_sha1";
    private byte[] imageRef;//底库图片
    private int buttonType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        megLiveManager=MegLiveManager.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);


        bt_action_yy = findViewById(R.id.bt_action_yy);
        bt_action_yy.setOnClickListener(this);
        bt_action_wy = findViewById(R.id.bt_action_wy);
        bt_action_wy.setOnClickListener(this);
        bt_fmp_yy = findViewById(R.id.bt_fmp_yy);
        bt_fmp_yy.setOnClickListener(this);
        bt_fmp_wy = findViewById(R.id.bt_fmp_wy);
        bt_fmp_wy.setOnClickListener(this);

        long currtTime = System.currentTimeMillis() / 1000;
        long expireTime = (System.currentTimeMillis() + 60 * 60 * 100) / 1000;
        sign = GenerateSign.appSign(API_KEY, SECRET, currtTime, expireTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_action_yy:
                buttonType = ACTION_YY;
                requestCameraPerm();
                break;
            case R.id.bt_action_wy:
                buttonType = ACTION_WY;
                requestCameraPerm();
                break;
            case R.id.bt_fmp_yy:
                buttonType = FMP_YY;
                requestCameraPerm();
                break;
            case R.id.bt_fmp_wy:
                buttonType = FMP_WY;
                requestCameraPerm();
                break;

        }
    }

    private void requestCameraPerm() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                requestWriteExternalPerm();
            }
        } else {
            beginDetect(buttonType);
        }
    }

    private void requestWriteExternalPerm() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE);
            } else {
                beginDetect(buttonType);
            }
        } else {
            beginDetect(buttonType);
        }
    }


    private void beginDetect(int type) {
        if (type == ACTION_YY) {
            getBizToken("meglive", 1, "XXX", "xxxxxxxxxxxxxxxx", "", null);
        } else if (type == ACTION_WY) {
            getBizToken("meglive", 0, "", "", UUID.randomUUID().toString(), imageRef);
        } else if (type == FMP_YY) {
            getBizToken("still", 1, "XXX", "xxxxxxxxxxxxxxxx", "", null);
        } else if (type == FMP_WY) {
            getBizToken("still", 0, "", "", UUID.randomUUID().toString(), imageRef);
        }
    }

    private static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imgBytes = baos.toByteArray();
        return imgBytes;
    }

    private void getBizToken(String livenessType, int comparisonType, String idcardName, String idcardNum, String uuid, byte[] image) {
        mProgressDialog.show();
        HttpRequestManager.getInstance().getBizToken(this, GET_BIZTOKEN_URL, sign, SIGN_VERSION, livenessType, comparisonType, idcardName, idcardNum, uuid, image, new HttpRequestCallBack() {

            @Override
            public void onSuccess(String responseBody) {
                try {
                    JSONObject json = new JSONObject(responseBody);
                    String bizToken = json.optString("biz_token");
                    megLiveManager.preDetect(MainActivity.this, bizToken, MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {

            }
        });
    }

    @Override
    public void onDetectFinish(String token, int errorCode, String errorMessage, String data) {
        if (errorCode == 1000) {
            verify(token, data.getBytes());
        }
    }

    @Override
    public void onPreStart() {
        showDialogDismiss();
    }

    @Override
    public void onPreFinish(String token, int errorCode, String errorMessage) {
        progressDialogDismiss();
        if (errorCode == 1000) {
            megLiveManager.startDetect(this);
        }
    }

    private void verify(String token, byte[] data) {
        showDialogDismiss();
        HttpRequestManager.getInstance().verify(this, VERIFY_URL, sign, SIGN_VERSION, token, data, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                Log.w("result", responseBody);
                progressDialogDismiss();
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                Log.w("result", new String(responseBody));
                progressDialogDismiss();
            }
        });
    }

    private void progressDialogDismiss() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    private void showDialogDismiss(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog!=null){
                    mProgressDialog.show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //拒绝了权限申请
            } else {
                requestWriteExternalPerm();
            }
        } else if (requestCode == EXTERNAL_STORAGE_REQ_WRITE_EXTERNAL_STORAGE_CODE) {
            if (grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //拒绝了权限申请
            } else {
                beginDetect(buttonType);
            }
        }
    }
}
