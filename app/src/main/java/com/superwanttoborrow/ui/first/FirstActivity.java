package com.superwanttoborrow.ui.first;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.mvp.MVPBaseActivity;
import com.superwanttoborrow.ui.home.HomeFragment;
import com.superwanttoborrow.ui.my.MyFragment;
import com.superwanttoborrow.ui.repayment.RepaymentFragment;
import com.superwanttoborrow.utils.ActivityCollector;
import com.superwanttoborrow.utils.DialogHelp;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;


/**
 * @author renji
 */

public class FirstActivity extends MVPBaseActivity<FirstContract.View, FirstPresenter> implements FirstContract.View {

    private BottomTabBar bottom_tab_bar;
    //退出时的时间
    private long mExitTime;

    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    private boolean boo = false;
    private boolean boo2 = false;
    private ReturnBean.DataBean dataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        powerManager = (PowerManager) this.getSystemService(this.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        initView();
    }

    private void initView() {
        bottom_tab_bar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        bottom_tab_bar.init(getSupportFragmentManager())
                .setFontSize(10)
                .addTabItem("首页", R.mipmap.homepage, R.mipmap.homepage_1, HomeFragment.class)
                .addTabItem("还款", R.mipmap.repayment, R.mipmap.repayment_1, RepaymentFragment.class)
                .addTabItem("我的", R.mipmap.my, R.mipmap.my_1, MyFragment.class);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(FirstActivity.this, "再按一次退出超享借", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }

    @Override
    public void showUpdateDialog(ReturnBean.DataBean dataBean) {
        getPermission();
        this.dataBean = dataBean;
    }

    @Override
    public void apkFinish() {
        finish();
    }

    private void myDownLoadApk(ReturnBean.DataBean dataBean) {
        boo = true;
        boo2 = true;
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        sharedPreferences.edit().clear().apply();
        wakeLock.acquire();
        final ProgressDialog pd;
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.setCanceledOnTouchOutside(false);
        pd.setCancelable(false);
        pd.show();
        mPresenter.downLoadApk(this, dataBean.getDownload(), pd);
    }

    //动态权限处理
    private void getPermission() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(listener)
                .start();
    }

    private RationaleListener rationaleListener = (requestCode, rationale) -> com.yanzhenjie.alertdialog.AlertDialog.newBuilder(this)
            .setTitle("友好提醒")
            .setMessage("你已拒绝过文件写入权限，沒有写入权限无法为你更新应用！")
            .setPositiveButton("好，给你", (dialog, which) -> rationale.resume())
            .setNegativeButton("我拒绝", (dialog, which) -> rationale.cancel()).show();

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(FirstActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    // TODO 执行拥有权限时的下一步。
                    if (AndPermission.hasPermission(FirstActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                        String forceUpdate = dataBean.getForceUpdate();
                        AlertDialog.Builder builer = new AlertDialog.Builder(FirstActivity.this);
                        builer.setTitle("版本升级");
                        builer.setMessage("软件更新");
                        builer.setCancelable(false);
                        builer.setOnKeyListener((dialog, keyCode, event) -> {
                                    {
                                        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                            return true;
                                        } else {
                                            return false; //默认返回 false
                                        }
                                    }
                                }
                        );
                        //当点确定按钮时从服务器上下载 新的apk 然后安装
                        builer.setPositiveButton("确定", (dialog, which) -> myDownLoadApk(dataBean));
                        //当点取消按钮时不做任何举动
                        builer.setNegativeButton("取消", (dialogInterface, i) -> {
                                    if ("0".equals(forceUpdate)) {
                                    } else {
                                        AlertDialog.Builder builer2 = new AlertDialog.Builder(FirstActivity.this);
                                        builer2.setTitle("不更新将无法继续使用");
                                        builer2.setMessage("确定退出？");
                                        builer2.setCancelable(false);
                                        builer2.setOnKeyListener((dialog, keyCode, event) -> {
                                                    {
                                                        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                                            return true;
                                                        } else {
                                                            return false; //默认返回 false
                                                        }
                                                    }
                                                }
                                        );
                                        builer2.setPositiveButton("确定", (dialog, which) -> finish());
                                        builer2.setNegativeButton("取消", (dialogInterface2, i2) -> {
                                            builer.show();
                                        });
                                        builer2.show();
                                    }
                                }
                        );
                        AlertDialog dialog = builer.create();
                        dialog.show();
                    }
                }
            } else {
                // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                DialogHelp.showNormalDialog(FirstActivity.this);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                if (AndPermission.hasPermission(FirstActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    if (AndPermission.hasAlwaysDeniedPermission(FirstActivity.this, deniedPermissions)) {
                        if (AndPermission.hasPermission(FirstActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                            String forceUpdate = dataBean.getForceUpdate();
                            AlertDialog.Builder builer = new AlertDialog.Builder(FirstActivity.this);
                            builer.setTitle("版本升级");
                            builer.setMessage("软件更新");
                            builer.setCancelable(false);
                            builer.setOnKeyListener((dialog, keyCode, event) -> {
                                        {
                                            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                                return true;
                                            } else {
                                                return false; //默认返回 false
                                            }
                                        }
                                    }
                            );
                            //当点确定按钮时从服务器上下载 新的apk 然后安装
                            builer.setPositiveButton("确定", (dialog, which) -> myDownLoadApk(dataBean));
                            //当点取消按钮时不做任何举动
                            builer.setNegativeButton("取消", (dialogInterface, i) -> {
                                        if ("0".equals(forceUpdate)) {
                                        } else {
                                            AlertDialog.Builder builer2 = new AlertDialog.Builder(FirstActivity.this);
                                            builer2.setTitle("不更新将无法继续使用");
                                            builer2.setMessage("确定退出？");
                                            builer2.setCancelable(false);
                                            builer2.setOnKeyListener((dialog, keyCode, event) -> {
                                                        {
                                                            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                                                return true;
                                                            } else {
                                                                return false; //默认返回 false
                                                            }
                                                        }
                                                    }
                                            );
                                            builer2.setPositiveButton("确定", (dialog, which) -> finish());
                                            builer2.setNegativeButton("取消", (dialogInterface2, i2) -> {
                                                builer.show();
                                            });
                                            builer2.show();
                                        }
                                    }
                            );
                            AlertDialog dialog = builer.create();
                            dialog.show();
                        }
                    } else {
                        // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                        DialogHelp.showNormalDialog(FirstActivity.this);
                    }
                }
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getUserDetauls(this);
        if (!boo2) {
            mPresenter.getVersion(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (boo) {
            boo = false;
            wakeLock.release();
        }
    }
}
