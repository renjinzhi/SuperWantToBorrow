package com.megvii.meglive_still.demo;

/**
 * Created by zhangwenjun on 2018/6/29.
 */

public interface HttpRequestCallBack {
    void onSuccess(String responseBody);

    void onFailure(int statusCode, byte[] responseBody);
}
