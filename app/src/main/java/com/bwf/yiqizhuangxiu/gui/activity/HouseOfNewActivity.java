package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class HouseOfNewActivity extends BaseActivity {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.text_appointment)
    TextView textAppointment;
    @Bind(R.id.linear_appointment)
    LinearLayout linearAppointment;
    @Bind(R.id.text_online_answer)
    TextView textOnlineAnswer;
    @Bind(R.id.linear_online_answer)
    LinearLayout linearOnlineAnswer;
    @Bind(R.id.new_old_house_webview)
    WebView newOldHouseWebview;
    private String url;
    private String title;

    @Override
    protected int getContentViewResId() {
        return R.layout.new_and_old_house_decorate;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        this.url = getIntent().getStringExtra("url");
        this.title = getIntent().getStringExtra("title");
    }

    @Override
    protected void initDatas() {
        titlebarContent.setText(title);
        newOldHouseWebview.loadUrl(url);
        newOldHouseWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                newOldHouseWebview.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        WebSettings settings = newOldHouseWebview.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.titlebar_content, R.id.titlebar_back, R.id.linear_appointment, R.id.linear_online_answer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_content:
                break;
            case R.id.titlebar_back:
                if (newOldHouseWebview.canGoBack())
                    newOldHouseWebview.goBack();
                else
                    finish();
                break;
            case R.id.linear_appointment:
                break;
            case R.id.linear_online_answer:
                break;
        }
    }
}
