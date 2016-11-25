package com.bwf.yiqizhuangxiu.gui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK;

/**
 * Created by Administrator on 2016/11/24.
 */

public class DetailsPageActivity extends BaseActivity {
    @Bind(R.id.webview_detailspage)
    WebView webviewDetailspage;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;

    private String url;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_detailspage;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        WebSettings settings = webviewDetailspage.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(LOAD_CACHE_ELSE_NETWORK);
        webviewDetailspage.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                DetailsPageActivity.this.url = url;
                webviewDetailspage.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webviewDetailspage.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressbar.setProgress(newProgress);
                if (newProgress < 100) {
                    progressbar.setVisibility(View.VISIBLE);
                } else {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
        url = getIntent().getStringExtra("url");
        webviewDetailspage.loadUrl(url);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.titlebar_back, R.id.titlebar_back_textview, R.id.titlebar_share, R.id.buttombar_back, R.id.buttombar_forword, R.id.buttombar_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.titlebar_back_textview:
                finish();
                break;
            case R.id.titlebar_share:
                break;
            case R.id.buttombar_back:
                if (webviewDetailspage.canGoBack()) {
                    webviewDetailspage.goBack();
                }
                break;
            case R.id.buttombar_forword:
                if (webviewDetailspage.canGoForward()) {
                    webviewDetailspage.goForward();
                }
                break;
            case R.id.buttombar_refresh:
                webviewDetailspage.loadUrl(url);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (webviewDetailspage.canGoBack()) {
            webviewDetailspage.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
