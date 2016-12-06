package com.bwf.yiqizhuangxiu.gui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class MyRectcleView extends RecyclerView {
    public MyRectcleView(Context context) {
        super(context);
    }

    public MyRectcleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRectcleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
