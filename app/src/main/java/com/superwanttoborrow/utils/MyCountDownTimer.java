package com.superwanttoborrow.utils;

import android.os.Handler;
import android.widget.TextView;

import com.superwanttoborrow.App;
import com.superwanttoborrow.R;

/**
 * @author renji
 * @date 2017/12/18
 */

public class MyCountDownTimer implements Runnable {


    private TextView tv;
    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();
    private volatile boolean isStopped = false;

    public MyCountDownTimer(TextView tv) {
        this.tv = tv;
    }

    public MyCountDownTimer() {
    }

    @Override
    public void run() {
        //倒计时开始，循环
        while (T > 0 && !isStopped) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    tv.setClickable(false);
//                    tv.setBackgroundResource(R.mipmap.verificationcodebutton1);
                    tv.setTextColor(App.getApplication().getResources().getColor(R.color.hui));
                    tv.setText(T + "s");
                }
            });
            try {
                Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            T--;
        }

        //倒计时结束，也就是循环结束
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                tv.setClickable(true);
                tv.setTextColor(App.getApplication().getResources().getColor(R.color.red));
//                tv.setBackgroundResource(R.mipmap.identifyingcode);
                tv.setText("获取验证码");
            }
        });
        T = 60; //最后再恢复倒计时时长
    }

    public void stop() {
        isStopped = true;
    }
}
