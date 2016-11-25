package com.bwf.yiqizhuangxiu.gui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onCustomScrollChangedListenner != null) {
            onCustomScrollChangedListenner.onCustomScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface OnCustomScrollChangedListenner {
        void onCustomScrollChanged(int x, int y, int oldX, int oldY);
    }

    private OnCustomScrollChangedListenner onCustomScrollChangedListenner;

    public void setOnCustomScrollChangedListenner(OnCustomScrollChangedListenner onCustomScrollChangedListenner) {
        this.onCustomScrollChangedListenner = onCustomScrollChangedListenner;
    }
}
