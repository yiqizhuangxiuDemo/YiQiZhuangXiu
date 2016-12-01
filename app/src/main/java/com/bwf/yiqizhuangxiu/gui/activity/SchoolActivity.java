package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.SchoolTabLayoutAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class SchoolActivity extends BaseActivity {
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.effcet_title_tablayout)
    TabLayout schoolTabLayout;
    @Bind(R.id.image_school_bar)
    Button imageSchoolBar;
    @Bind(R.id.view_pager_school)
    ViewPager viewPagerSchool;
    SchoolTabLayoutAdapter tabLayoutAdapter;
    @Bind(R.id.linear_parent)
    LinearLayout linearParent;
    private PopupWindow popupWindow;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        schoolTabLayout.setupWithViewPager(viewPagerSchool);
        tabLayoutAdapter = new SchoolTabLayoutAdapter(getSupportFragmentManager(), this);
        viewPagerSchool.setAdapter(tabLayoutAdapter);
        initPopWindow();
    }

    private void initPopWindow() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.school_titel_popwindow, null);
            popupWindow = new PopupWindow(this);
            popupWindow.setContentView(view);
            popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
        }
    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.titlebar_back, R.id.image_school_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.image_school_bar:
                //TODO 弹popupwindow
                popupWindow.showAtLocation(linearParent, Gravity.TOP,0,0);
                break;
        }
    }

}
