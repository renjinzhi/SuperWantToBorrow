package com.superwanttoborrow.mvp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.bqs.risk.df.android.BqsDF;
import com.bqs.risk.df.android.BqsParams;
import com.superwanttoborrow.utils.Global;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView{
    public T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Global.authRuntimePermissions && BqsDF.getInstance().canInitBqsSDK()){
            initBqsDFSDK();
        }
    }

    protected void initBqsDFSDK(){

        //2、配置初始化参数
        BqsParams params = new BqsParams();
        params.setPartnerId("jxd");//商户编号
        params.setTestingEnv(true);//false是生产环境 true是测试环境
        params.setGatherGps(false);
        params.setGatherContact(false);
        params.setGatherCallRecord(false);
        params.setGatherInstalledApp(true);
        params.setGatherSMSCount(true);

        //3、执行初始化
        BqsDF.getInstance().initialize(this, params);

        ////采集通讯录 通过SDK采集并上传,第一个参数：是否采集通讯录，第二个参数：是否采集通话记录
        BqsDF.getInstance().commitContactsAndCallRecords(false, false);
        //贵公司采集通讯录 通过SDK上传
//        BqsDF.getInstance().commitContacts(List<ContactsBean> contactsBeanList);

        //采集经纬度 通过SDK采集并上传
        BqsDF.getInstance().commitLocation();
        //贵司采集经纬度 通过SDK上传,第一个参数：经度，第二个参数：纬度
        //BqsDF.getInstance().commitLocation(longitude, latitude);

        //4、提交tokenkey到贵公司服务器
        String tokenkey = BqsDF.getInstance().getTokenKey();

        //注意：上传tokenkey时最好再停留几百毫秒的时间（给SDK预留上传设备信息的时间）
        new CountDownTimer(500, 500){
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
//                submitTokenkey();
            }
        }.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BqsDF.getInstance().release();
        if (mPresenter!=null)
        mPresenter.detachView();
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }
}
