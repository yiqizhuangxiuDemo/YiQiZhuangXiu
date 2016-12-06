package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.bwf.yiqizhuangxiu.BuildConfig;
import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.CityActivityAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class ActivityDetails extends BaseActivity {
    @Bind(R.id.details_top_back)
    ImageView detailsTopBack;
    @Bind(R.id.webview_articledetails)
    WebView webviewArticledetails;
    private CityActivityAdapter adapter;
    private Intent intent;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initDatas() {
        adapter = new CityActivityAdapter(this);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("URL");
        if (BuildConfig.DEBUG) Log.d("ActivityDetails", url);
        if (url != null) {
            webviewArticledetails.loadUrl(url);
        }
        WebSettings settings = webviewArticledetails.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.supportZoom();
        settings.getBuiltInZoomControls();
        settings.getDisplayZoomControls();
        webviewArticledetails.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webviewArticledetails.canGoBack()) {
            webviewArticledetails.goBack();
            return true;
        }
        return false;
    }

    @OnClick(R.id.details_top_back)
    public void onClick() {
        finish();
    }
}
