package com.bwf.yiqizhuangxiu.gui.custom;

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
 * 自定义可定时自动滑动的ViewPager
 * 同时满足手动滑动的要求
 */

public class AutoScrollViewPager extends ViewPager {

    private Timer timer;
    private TimerTask timerTask;
    private int time;
    private static final int DURATION = 800;
    private PagerAdapter baseAdapter;

    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 重写setAdapter让使用时只需要向正常使用PagerAdapter
     * （不能是FragmentPagerAdapter等）那样进行就可以实现自动滚动
     *
     * @param adapter
     */
    @Override
    public void setAdapter(final PagerAdapter adapter) {
        this.baseAdapter = adapter;

        if (timer == null)
            timer = new Timer();
        if (time <= 0)
            time = 3000;
        super.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return baseAdapter.isViewFromObject(view, object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return baseAdapter.instantiateItem(container, position % adapter.getCount());
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                baseAdapter.destroyItem(container, position % adapter.getCount(), object);
            }
        });
        startAutoScroll();

    }

    /**
     * 设置自动滚动的周期时间
     * 需要在设置adapter之前使用才有效
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 开始自动滚动
     */
    public void startAutoScroll() {
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    //这是将要通过定时器运行在子线程的任务主体
                    startNextPage();
                }
            };
            timer.schedule(timerTask, time, time);
        }
    }

    /**
     * 停止自动滚动
     */
    public void stopAutoScroll() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 滑动到下一页
     */
    private void startNextPage() {
        post(new Runnable() {
            @Override
            public void run() {
                setCurrentItem(getCurrentItem() + 1);
            }
        });
    }

    private void init() {
        //用反射修改ViewPager切换时的默认滚动时间
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(this, new Scroller(getContext()) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, DURATION);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加防止自动滚动与手动控制的冲突
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startAutoScroll();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

//    //所以进行了重写的更改
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                stopAutoScroll();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                startAutoScroll();
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }

}
