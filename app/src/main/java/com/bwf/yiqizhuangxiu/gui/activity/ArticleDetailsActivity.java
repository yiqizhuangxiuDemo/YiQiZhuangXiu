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

/**
 * Created by Administrator on 2016/11/23.
 */

public class ArticleDetailsActivity extends BaseActivity {
    @Bind(R.id.webview_articledetails)
    WebView webviewArticledetails;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;

    public final static String TAG_URL_EXTRA = "URL";

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_articledetails;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        WebSettings settings = webviewArticledetails.getSettings();
        settings.setJavaScriptEnabled(true);
        webviewArticledetails.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webviewArticledetails.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webviewArticledetails.setWebChromeClient(new WebChromeClient() {
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
    }

    @Override
    protected void initDatas() {
        if (getIntent().getStringExtra(TAG_URL_EXTRA) != null) {
            webviewArticledetails.loadUrl(getIntent().getStringExtra(TAG_URL_EXTRA));
        }
    }

    @OnClick(R.id.titlebar_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (webviewArticledetails.canGoBack()) {
            webviewArticledetails.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
