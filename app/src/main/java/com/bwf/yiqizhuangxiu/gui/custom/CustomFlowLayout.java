package com.bwf.yiqizhuangxiu.gui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;

/**
 * Created by Administrator on 2016/11/16.
 * 自定义流式布局
 */

public class CustomFlowLayout extends ViewGroup {
    private int lineSpacing = 30;

    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Custom_FlowLayout_Attr);
        lineSpacing = typedArray.getDimensionPixelSize(R.styleable.Custom_FlowLayout_Attr_lineSpacing, lineSpacing);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取布局的内容宽高
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
            return;
        }
        //获取布局内边距
        int mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int mPaddingTop = getPaddingTop();
        int mPaddingBottom = getPaddingBottom();
        //设置一些初始值
        int totalHeight = mPaddingTop + mPaddingBottom;
        int usedLineWidth = mPaddingLeft + mPaddingRight;
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidthSpacing = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeightSpacing = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (usedLineWidth + childWidthSpacing > widthSize) {
                totalHeight += lineHeight + lineSpacing;
                usedLineWidth = mPaddingLeft + mPaddingRight;
                lineHeight = 0;
            }
            usedLineWidth += childWidthSpacing;
            if (childHeightSpacing > lineHeight) {
                lineHeight = childHeightSpacing;
            }
        }
        totalHeight += lineHeight;
        setMeasuredDimension(widthSize, totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取布局内边距
        int mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int mPaddingTop = getPaddingTop();
        //许可的最大宽度
        int widthSize = r - l;
        //定义子控件的位置
        int x = mPaddingLeft;
        int y = mPaddingTop;
        int usedLineWidth = mPaddingLeft + mPaddingRight;
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidthSpacing = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeightSpacing = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (usedLineWidth + childWidthSpacing > widthSize) {
                x = mPaddingLeft;
                y += lineHeight + lineSpacing;
                lineHeight = 0;
                usedLineWidth = mPaddingRight + mPaddingLeft;
            }
            child.layout(x + layoutParams.leftMargin, y + layoutParams.topMargin,
                    x + layoutParams.leftMargin + child.getMeasuredWidth(), y + layoutParams.topMargin + child.getMeasuredHeight());
            if (childHeightSpacing > lineHeight) {
                lineHeight = childHeightSpacing;
            }
            x += childWidthSpacing;
            usedLineWidth += childWidthSpacing;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(super.generateDefaultLayoutParams());
    }
}
