package com.bwf.yiqizhuangxiu.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwf.yiqizhuangxiu.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class IndicatorContainer {
    private LinearLayout mIndicatorContainer;
    private ViewPager viewPager;
    private LayoutInflater inflater;
    private int normalDotRes = R.mipmap.pic;
    private int selectedDotRes = R.mipmap.pig;

    public IndicatorContainer(Context context, final LinearLayout mIndicatorContainer, ViewPager viewPager) {
        this.mIndicatorContainer = mIndicatorContainer;
        this.viewPager = viewPager;
        this.inflater = LayoutInflater.from(context);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < mIndicatorContainer.getChildCount(); i++) {
                   ImageView img = (ImageView) mIndicatorContainer.getChildAt(i).findViewById(R.id.imageView_indicator_dot);
                    if (i == position % mIndicatorContainer.getChildCount()){
                        img.setImageResource(selectedDotRes);
                    }else {
                        img.setImageResource(normalDotRes);
                    }
                }

            }
        });
    }
    public void setDorRes(int selectedDotRes,int normalDotRes){
        this.normalDotRes = normalDotRes;
        this.selectedDotRes = selectedDotRes;
    }

    public void setDotNum(int size){
        mIndicatorContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            View view = inflater.inflate(R.layout.subview_indicator_dots,null);
            ImageView img = (ImageView) view.findViewById(R.id.imageView_indicator_dot);
            if (i==viewPager.getCurrentItem()%size){
                img.setImageResource(selectedDotRes);
            }else{
                img.setImageResource(normalDotRes);
            }
            mIndicatorContainer.addView(view);
        }
    }
}
