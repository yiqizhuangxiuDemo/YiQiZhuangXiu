package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.bwf.yiqizhuangxiu.R;

import static com.bwf.yiqizhuangxiu.R.id.webview;

/**
 * Created by Administrator on 2016/12/5.
 */

public class BudgetFragment extends Fragment {
    WebView webView;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item1,null);
        webView= (WebView) view.findViewById(webview);

//
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                webView.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
        webView.loadUrl("http://m.beijing.17house.com/baojia/?sem=android&model=android");
        webView.clearCache(true);
        return view;
    }
}
