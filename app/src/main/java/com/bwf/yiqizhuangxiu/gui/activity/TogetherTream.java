package com.bwf.yiqizhuangxiu.gui.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.TabLayoutAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class TogetherTream extends BaseActivity {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.togeter_viewpager)
    ViewPager togeterViewpager;
    @Bind(R.id.tabLayout_main)
    TabLayout tabLayoutMain;
    private TabLayoutAdapter tabLayoutAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.together_tream;

    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        FragmentManager fm = getSupportFragmentManager();
        tabLayoutAdapter = new TabLayoutAdapter(fm);
        togeterViewpager.setAdapter(tabLayoutAdapter);
        tabLayoutMain.setupWithViewPager(togeterViewpager);
        titlebarContent.setText("一起团队");


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.titlebar_back)
    public void onClick() {
        finish();
    }
}
