package com.bwf.yiqizhuangxiu.gui.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.BookingData;
import com.bwf.yiqizhuangxiu.gui.adapter.BookingViewPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.custom.CustomRefreshLayout;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterBooking;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterBookingImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewBooking;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class BookingActivity extends BaseActivity implements ViewBooking, BookingViewPagerAdapter.OnRecyclerViewItemClickListener {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.refresh)
    CustomRefreshLayout refresh;
    @Bind(R.id.root)
    LinearLayout root;

    private boolean isRefreshing;
    private PresenterBooking presenter;
    private PopupWindow popupWindow;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_booking;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        titlebarContent.setText(getString(R.string.booking_title_text));
        presenter = new PresenterBookingImpl(this);
        initListener();
    }

    private void initListener() {

        refresh.setOnRefreshListener(new CustomRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBookingData(2);
            }
        });

        //设置屏蔽/打开刷新使能的监听
        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    refresh.setEnable(true);
                } else {
                    refresh.setEnable(false);
                }
            }
        });
    }

    @Override
    protected void initDatas() {
        refresh.startRefreshOnce();
    }

    private void loadBookingData(int cityId) {
        if (!isRefreshing) {
            isRefreshing = true;
            presenter.loadBookingData(cityId);
        }
    }

    @Override
    public void loadBookingSuccess(List<BookingData.DataBeanA> datas) {
        refresh.finishRefresh();
        isRefreshing = false;
        BookingViewPagerAdapter adapter = new BookingViewPagerAdapter(datas, this);
        adapter.setOnRecyclerViewItemClickListener(this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.removeAllTabs();
        for (int i = 0; i < datas.size() + 1; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.booking_tablayout_item, null);
            TextView textview = (TextView) view.findViewById(R.id.textview);
            if (i == 0) {
                textview.setText(getString(R.string.booking_tablayout_firsttext));
                textview.setSelected(true);
            } else {
                textview.setText(datas.get(i - 1).getName());
            }
            TabLayout.Tab tab = tabLayout.newTab().setCustomView(view);
            tabLayout.addTab(tab);
        }
    }

    @Override
    public void loadBookingFailed(String message) {
        refresh.finishRefresh();
        isRefreshing = false;
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "网络连接出现错误", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.titlebar_back, R.id.discription})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.discription:
                showPopupwindow();
                break;
        }
    }

    private void showPopupwindow() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.booking_popupwindow, null);
            popupWindow = new PopupWindow(view
                    , WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.popupwindow_bg));
            popupWindow.setBackgroundDrawable(colorDrawable);
            popupWindow.setAnimationStyle(R.style.popupwindow_anim);
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
            popupWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void onRecyclerViewItemClick(View view, BookingData.DataBeanA.DataBean data) {
        if (data != null)
            Toast.makeText(this, data.getBusiness_name(), Toast.LENGTH_SHORT).show();
    }
}
