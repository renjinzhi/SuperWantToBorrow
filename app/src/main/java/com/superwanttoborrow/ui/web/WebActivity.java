package com.superwanttoborrow.ui.web;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WebActivity extends MVPBaseActivity<WebContract.View, WebPresenter> implements WebContract.View {


    private TextView web_tv_title;
    private ImageView web_back;
    private WebView web_wv;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initData();
    }

    private void initView() {
        web_tv_title = (TextView) findViewById(R.id.web_tv_title);
        web_back = (ImageView) findViewById(R.id.web_back);
        web_wv = (WebView) findViewById(R.id.web_wv);
    }

    private void initData(){
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        web_tv_title.setText(name);
        WebSettings webSettings = web_wv.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
//        String[] whiteList = {"taobao://", "alipayqr://", "alipays://", "wechat://", "weixin://", "mqq://", "mqqwpa://", "openApp.jdMobile://"};
        web_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        web_wv.loadUrl(url);
        }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web_wv.canGoBack()) {
            web_wv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
