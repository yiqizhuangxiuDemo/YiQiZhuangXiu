package com.bwf.yiqizhuangxiu.utils.indicator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 * ViewPager圆点指示器
 * 以一个LinearLayout为父容器，在里面加入指定个数大小和间距的CheckedTextView，指定的selector实现指示器功能
 * selector的形状决定了指示器单元的形状，不一定是小圆点，同时可以对外提供监听
 */

public class ViewPagerIndicator {
    private Context context;
    private ViewPager viewPager;
    //圆点数量
    private int dotNum;
    //圆点父容器
    private LinearLayout dotContainer;
    //统一图片选择器
    private int selectorDrawable;
    //个性化每一单云的图片选择器
    private int[] selectorDrawables;
    //宽高和间距属性
    private float dotWidth;
    private float dotHeight;
    private float margin;
    private List<IndicatorDefultView> indicatorDefultViews;
    //指示器状态改变监听实例
    private OnIndicatorSelectedChangedListener onIndicatorSelectedChangedListener;
    //设置是否需要指示器与ViewPager相互联动
    private boolean linkage;
    //如果需要的话设置文字
    private String[] titleTexts;
    //如果需要的话设置文字颜色选择器
    private int selectorTitleText;
    //设置是否宽高关联铺满父容器分散居中(一定要在设置宽高前进行设置)
    private boolean isDistributedCenter;

    /**
     * @param context
     * @param viewPager         关联的ViewPager
     * @param dotContainer      指示器的容器LinearLayout
     * @param dotNum            指示器单元数量 ViewPager页数
     * @param selectorDrawables 个性化图片选择器
     */
    public ViewPagerIndicator(Context context, ViewPager viewPager, LinearLayout dotContainer
            , int dotNum, int[] selectorDrawables) {
        this(context, viewPager, dotContainer, dotNum);
        this.selectorDrawables = selectorDrawables;
    }

    /**
     * @param context
     * @param viewPager        关联的ViewPager
     * @param dotContainer     指示器的容器LinearLayout
     * @param dotNum           指示器单元数量 ViewPager页数
     * @param selectorDrawable 图片选择器
     */
    public ViewPagerIndicator(Context context, ViewPager viewPager, LinearLayout dotContainer
            , int dotNum, int selectorDrawable) {
        this(context, viewPager, dotContainer, dotNum);
        this.selectorDrawable = selectorDrawable;
    }

    /**
     * @param context
     * @param viewPager    关联的ViewPager
     * @param dotContainer 指示器的容器LinearLayout
     * @param dotNum       指示器单元数量 ViewPager页数
     */
    public ViewPagerIndicator(Context context, ViewPager viewPager, LinearLayout dotContainer
            , int dotNum) {
        this.context = context;
        this.dotNum = dotNum;
        this.viewPager = viewPager;
        this.dotContainer = dotContainer;
        dotWidth = dotContainer.getWidth() / dotNum;
        dotHeight = dotContainer.getHeight();
        dotContainer.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void create() {
        addIndicatorDefultView();
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                dotCheckedChange(position % dotNum);
                if (onIndicatorSelectedChangedListener != null) {
                    onIndicatorSelectedChangedListener.onIndicatorSelectedChanged(position % dotNum);
                }
            }
        });
        dotCheckedChange(viewPager.getCurrentItem() % dotNum);
    }

    private void addIndicatorDefultView() {
        indicatorDefultViews = new ArrayList<>();
        //建立公共的宽高间距等属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) dotWidth, (int) dotHeight);
        params.setMargins((int) (margin / 2), 0, (int) (margin / 2), 0);
        for (int i = 0; i < dotNum; i++) {
            //设置属性并添加到父容器和集合进行管理
            IndicatorDefultView indicatorDefultView = new IndicatorDefultView(context);
            indicatorDefultView.setLayoutParams(params);
            indicatorDefultView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); //22SP
            indicatorDefultView.setGravity(Gravity.CENTER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                indicatorDefultView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            dotContainer.addView(indicatorDefultView);
            indicatorDefultViews.add(indicatorDefultView);
            if (selectorDrawable != 0) {
                indicatorDefultView.setBackgroundResource(selectorDrawable);
                indicatorDefultView.setHasSelector(true);
            } else if (selectorDrawables != null) {
//                indicatorDefultView.setBackgroundResource(selectorDrawables[i]);
                Drawable drawable = context.getResources().getDrawable(selectorDrawables[i]);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                indicatorDefultView.setCompoundDrawables(null, drawable, null, null);
                indicatorDefultView.setHasSelector(true);
                if (titleTexts != null) {
                    indicatorDefultView.setText(titleTexts[i]);
                    if (selectorTitleText != 0) {
                        ColorStateList colorStateList = context.getResources()
                                .getColorStateList(selectorTitleText);
                        indicatorDefultView.setTextColor(colorStateList);
                    }
                }
            }
            //如果需要指示器与ViewPager相互关联则设置单击改变状态切换ViewPager的部分
            if (linkage) {
                indicatorDefultView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indicatorDefultViews.get(viewPager.getCurrentItem()) != v) {
                            for (int j = 0; j < dotNum; j++) {
                                if (indicatorDefultViews.get(j) == v) {
                                    indicatorDefultViews.get(j).setChecked(true);
                                    viewPager.setCurrentItem(j);
                                } else
                                    indicatorDefultViews.get(j).setChecked(false);
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * 响应指示器状态改变
     *
     * @param position
     */
    private void dotCheckedChange(int position) {
        for (int i = 0; i < indicatorDefultViews.size(); i++) {
            if (i == position)
                indicatorDefultViews.get(i).setChecked(true);
            else
                indicatorDefultViews.get(i).setChecked(false);
        }
    }

    /**
     * 设置单元指示器的高度
     *
     * @param dotHeight 单位 px 单元高度
     */
    public void setDotHeightByPx(float dotHeight) {
        this.dotHeight = dotHeight;
    }

    /**
     * 设置单元指示器的高度
     *
     * @param dotHeight 单位 dp 单元高度
     */
    public void setDotHeightByDp(int dotHeight) {
        dotHeight = dip2px(dotHeight);
        this.dotHeight = dotHeight;
    }

    /**
     * 设置指示器单元的宽度，同时根据总宽度铺满和居中原则调整间距
     *
     * @param dotWidth 单位 px 指示器单元宽度
     */
    public void setDotWidthByPx(float dotWidth) {
        this.dotWidth = dotWidth;
        if (isDistributedCenter)
            this.margin = dotContainer.getWidth() / dotNum - dotWidth;
    }

    /**
     * 设置指示器单元的宽度，同时根据总宽度铺满和居中原则调整间距
     *
     * @param dotWidth 单位 dp 指示器单元宽度
     */
    public void setDotWidthByDp(int dotWidth) {
        dotWidth = dip2px(dotWidth);
        setDotWidthByPx((float) dotWidth);
    }

    /**
     * 设置指示器单元的间距，同时根据总宽度铺满和居中原则调整宽度
     *
     * @param margin 单位 px 间距
     */
    public void setMarginByPx(float margin) {
        this.margin = margin;
        if (isDistributedCenter)
            this.dotWidth = dotContainer.getWidth() / dotNum - margin;
    }

    /**
     * 设置指示器单元的间距，同时根据总宽度铺满和居中原则调整宽度
     *
     * @param margin 单位 dp 间距
     */
    public void setMarginByDp(int margin) {
        margin = dip2px(margin);
        setMarginByPx((float) margin);
    }

    /**
     * 外部如果需要监听指示器的状态变化则可以实现此接口并设置到本指示器
     */
    public interface OnIndicatorSelectedChangedListener {
        void onIndicatorSelectedChanged(int position);
    }

    /**
     * 设置监听的方法
     *
     * @param onIndicatorSelectedChangedListener 监听器
     */
    public void setOnIndicatorSelectedChangedListener(OnIndicatorSelectedChangedListener
                                                              onIndicatorSelectedChangedListener) {
        this.onIndicatorSelectedChangedListener = onIndicatorSelectedChangedListener;
    }

    /**
     * 设置指示器是否与ViewPager联动
     *
     * @param linkage
     */
    public void setLinkage(boolean linkage) {
        this.linkage = linkage;
    }

    /**
     * 本方法可以设置文字
     *
     * @param titleTexts
     */
    public void setTitle(String[] titleTexts, int selectorTitleText) {
        this.titleTexts = titleTexts;
        this.selectorTitleText = selectorTitleText;
    }

    /**
     * 设置是否宽高关联铺满父容器分散居中(一定要在设置宽高前进行设置)
     *
     * @param distributedCenter
     */
    public void setDistributedCenter(boolean distributedCenter) {
        isDistributedCenter = distributedCenter;
    }

    /**
     * dip转px
     */
    private int dip2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dip
     */
    private int px2dip(float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
