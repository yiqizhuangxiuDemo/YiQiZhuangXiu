package com.bwf.yiqizhuangxiu.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/21.
 */

public class AutoScrollViewPager extends ViewPager {
    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        try {
            Field scrollField = ViewPager.class.getDeclaredField("mScroller");
            scrollField.setAccessible(true);
            Scroller scroller = new Scroller(getContext()){
                @Override
                public void startScroll(int startX, int startY, int dx, int dy) {
                    super.startScroll(startX, startY, dx, dy,1200);
                }
            };
            try {
                scrollField.set(this,scroller);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAdapter(final PagerAdapter adapter) {
        super.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return adapter.isViewFromObject(view,object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return adapter.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                adapter.destroyItem(container, position, object);
            }
        });
        startScrollView();
    }
    private Timer timer = new Timer();
    private TimerTask task;//同一个TimerTaSK只能被启动一次，
    private void startScrollView() {
        if (task == null){
            task = new TimerTask() {
                @Override
                public void run() {
                    nextPager();
                }
            };
            //启动任务
            timer.schedule(task,3000,2000);
        }
    }

    private void nextPager() {
        post(new Runnable() {
            @Override
            public void run() {
                setCurrentItem(getCurrentItem()+1);
            }
        });
    }
    //停止滚动
    private void stopAutoScroll(){
        if (task != null){
            task.cancel();//取消任务
        }
        task = null;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                stopAutoScroll();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                startScrollView();
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startScrollView();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
