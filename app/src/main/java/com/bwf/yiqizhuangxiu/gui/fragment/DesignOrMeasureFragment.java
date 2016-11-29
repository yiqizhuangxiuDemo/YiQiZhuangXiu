package com.bwf.yiqizhuangxiu.gui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/28.
 */

public class DesignOrMeasureFragment extends BaseFragment {
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.root)
    FrameLayout root;

    public final static String TAG_KEY = "KEY";
    private String url;
    private PopupWindow popupWindow;

    public static DesignOrMeasureFragment newInstance(String url) {
        DesignOrMeasureFragment fragment = new DesignOrMeasureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_KEY, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_design_viewpager_item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString(TAG_KEY);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
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
        webview.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.redPacket)
    public void onClick() {
        showPopupwindow();
    }

    private void showPopupwindow() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(getContext());
            View view = View.inflate(getContext(), R.layout.activity_design_popupwondow, null);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setContentView(view);
            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindoe_homepage));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            TextView textView = (TextView) view.findViewById(R.id.design_popupwindow_textview_content);
            String str = getString(R.string.design_popupwindow_text_main);
            SpannableStringBuilder builder = new SpannableStringBuilder(str);
            ForegroundColorSpan colorSpanGreen1 = new ForegroundColorSpan(getContext().getResources().getColor(R.color.mainGreen));
            ForegroundColorSpan colorSpanGreen2 = new ForegroundColorSpan(getContext().getResources().getColor(R.color.mainGreen));
            builder.setSpan(colorSpanGreen1, 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(colorSpanGreen2, 18, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new StyleSpan(Typeface.BOLD), 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new StyleSpan(Typeface.BOLD), 18, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(builder);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return false;
                }
            });
            LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
            ll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
        }
    }

    public boolean setPopupWindowDismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return true;
        } else {
            return false;
        }
    }

}
