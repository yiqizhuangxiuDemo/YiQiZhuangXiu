package com.bwf.yiqizhuangxiu.gui.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CustomNoScrollViewPager extends ViewPager {
    public CustomNoScrollViewPager(Context context) {
        super(context);
    }

    public CustomNoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
