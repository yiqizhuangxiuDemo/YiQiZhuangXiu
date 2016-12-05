package com.bwf.yiqizhuangxiu.gui.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;


/**
 * Created by Administrator on 2016/11/4.
 * 自定义下拉刷新控件
 */

public class CustomRefreshLayout extends FrameLayout {

    private View bodyView;
    private View headerView;
    private View progressView;
    private View upView;
    private TextView infoView;
    private TextView timeView;
    private int headerHeight;
    private boolean isRefreshing;
    private boolean isEnable = true;

    private View bodyChildView;

    public CustomRefreshLayout(Context context) {
        super(context);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bodyView = getChildAt(0);
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout_header, this, false);
        addView(headerView);
        progressView = headerView.findViewById(R.id.progressImage);
        upView = headerView.findViewById(R.id.upImage);
        ViewCompat.setRotation(upView, 0);
        infoView = (TextView) headerView.findViewById(R.id.infoText);
        timeView = (TextView) headerView.findViewById(R.id.timeText);
        headerView.measure(0, 0);
        headerHeight = headerView.getMeasuredHeight();
        ViewCompat.setTranslationY(headerView, -headerHeight);
    }

    private float touchY;
    private float touchX;

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = ev.getY();
                touchX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getY() - touchY) > Math.abs(ev.getX() - touchX) && ev.getY() - touchY > 50 && isEnable && isBodyTopLimit() && !isRefreshing) {
                    touchY = ev.getY();
                    if (onTouchByUserListener != null) {
                        onTouchByUserListener.onTouchByUser(timeView);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isBodyTopLimit() {
        return !ViewCompat.canScrollVertically(bodyView, -1);
    }

    private boolean isRotate;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = event.getY() - touchY;
                if (distanceY <= 0) {
                    distanceY = 0;
                    touchY = event.getY();
                }
                if (distanceY > headerHeight + 900) {
                    distanceY = headerHeight + 900;
                    touchY = event.getY() - (headerHeight + 900);
                }
                distanceY = distanceY / 3;
                if (distanceY >= headerHeight) {
                    if (!isRotate) {
                        isRotate = true;
                        upView.clearAnimation();
                        Animation myAnimation_Rotate = new RotateAnimation(0, 180,
                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        myAnimation_Rotate.setDuration(200);
                        myAnimation_Rotate.setFillAfter(true);
                        upView.startAnimation(myAnimation_Rotate);
                        infoView.setText("一起装修网，省钱有保障");
                    }
                } else {
                    if (isRotate) {
                        isRotate = false;
                        upView.clearAnimation();
                        Animation myAnimation_Rotate = new RotateAnimation(180, 0,
                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        myAnimation_Rotate.setDuration(200);
                        myAnimation_Rotate.setFillAfter(true);
                        upView.startAnimation(myAnimation_Rotate);
                        infoView.setText("下拉刷新");
                    }
                }
                ViewCompat.setTranslationY(bodyView, distanceY);
                ViewCompat.setTranslationY(headerView, distanceY - headerHeight);
                break;
            case MotionEvent.ACTION_UP:
                distanceY = (event.getY() - touchY) / 3;
                if (distanceY < headerHeight) {
                    smoothScroll(ViewCompat.getTranslationY(headerView), -headerHeight);
                    if (onRecoverListener != null) {
                        onRecoverListener.onRecover();
                    }
                } else {
                    startRefreshOnce();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private ValueAnimator valueAnimator;

    /**
     * 平滑滚动
     *
     * @param startY 起始位置
     * @param endY   结束位置
     */
    private void smoothScroll(float startY, float endY) {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(startY, endY);
            valueAnimator.setDuration(200);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    ViewCompat.setTranslationY(bodyView, value + headerHeight);
                    ViewCompat.setTranslationY(headerView, value);
                    if (isRefreshing && value == 0) {
                        RotateAnimation rotateAnimation = new RotateAnimation(0, 360
                                , RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                        rotateAnimation.setDuration(1300);
                        rotateAnimation.setRepeatCount(-1);
                        rotateAnimation.setInterpolator(new LinearInterpolator());
                        progressView.setVisibility(View.VISIBLE);
                        upView.clearAnimation();
                        upView.setVisibility(View.GONE);
                        progressView.startAnimation(rotateAnimation);
                    }
                }
            });
        } else {
            valueAnimator.setFloatValues(startY, endY);
        }
        valueAnimator.start();
    }

    private ValueAnimator valueAnimatorRotate;

    /**
     * 结束刷新状态
     */
    public void finishRefresh() {
        isRefreshing = false;
        smoothScroll(ViewCompat.getTranslationY(headerView), -headerHeight);
        if (onFinishRefreshListener != null) {
            onFinishRefreshListener.onFinishRefresh();
        }
        progressView.clearAnimation();
        progressView.setVisibility(View.GONE);
        upView.setVisibility(View.VISIBLE);
        infoView.setText("下拉刷新");
        ViewCompat.setRotation(upView, 0);
        if (onRecoverListener != null) {
            onRecoverListener.onRecover();
        }
    }

    /**
     * 供手动调用刷新
     */
    public void startRefreshOnce() {
        isRefreshing = true;
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh();
        }
        if (headerView != null)
            smoothScroll(ViewCompat.getTranslationY(headerView), 0);
    }

    /**
     * 下拉刷新开始监听接口
     */
    public interface OnRefreshListener {
        void onRefresh();
    }

    private OnRefreshListener onRefreshListener;

    /**
     * 设置下拉刷新开始监听
     *
     * @param onRefreshListener 监听器
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * 清空下拉刷新监听
     */
    public void removeOnRefreshListener() {
        this.onRefreshListener = null;
    }

    /**
     * 刷新完成监听接口
     */
    public interface OnFinishRefreshListener {
        void onFinishRefresh();
    }

    private OnFinishRefreshListener onFinishRefreshListener;

    /**
     * 设置刷新完成接口
     *
     * @param onFinishRefreshListener
     */
    public void setOnFinishRefreshListener(OnFinishRefreshListener onFinishRefreshListener) {
        this.onFinishRefreshListener = onFinishRefreshListener;
    }

    /**
     * 设置拖动接口监听
     */
    public interface OnTouchByUserListener {
        void onTouchByUser(TextView timeView);
    }

    private OnTouchByUserListener onTouchByUserListener;

    public void setOnTouchByUserListener(OnTouchByUserListener onTouchByUserListener) {
        this.onTouchByUserListener = onTouchByUserListener;
    }

    /**
     * 设置恢复正常接口监听
     */
    public interface OnRecoverListener {
        void onRecover();
    }

    private OnRecoverListener onRecoverListener;

    public void setOnRecoverListener(OnRecoverListener onRecoverListener) {
        this.onRecoverListener = onRecoverListener;
    }
}
