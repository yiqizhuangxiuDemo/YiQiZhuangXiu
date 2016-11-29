package com.bwf.yiqizhuangxiu.gui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.DesignActivityViewPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.DesignOrMeasureFragment;
import com.bwf.yiqizhuangxiu.utils.Apis;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/11/23.
 */

public class DesignActivity extends BaseActivity {
    @Bind(R.id.titlebar_tablayout)
    TabLayout titlebarTablayout;
    @Bind(R.id.viewpager_design)
    ViewPager viewpagerDesign;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> fragments;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_design;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initDatas() {
        fragments = getFragmentsList();
        mAdapter = new DesignActivityViewPagerAdapter(fragments, getSupportFragmentManager());
        viewpagerDesign.setAdapter(mAdapter);
        titlebarTablayout.setupWithViewPager(viewpagerDesign);
    }

    private List<Fragment> getFragmentsList() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            if (i == 0) {
                fragment = DesignOrMeasureFragment.newInstance(Apis.API_FREE_DESIGN);
            } else if (i == 1) {
                fragment = DesignOrMeasureFragment.newInstance(Apis.API_FREE_MEASURE);
            }
            fragments.add(fragment);
        }
        return fragments;
    }

    @OnClick({R.id.titlebar_back, R.id.titlebar_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.titlebar_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "4006891717"));//直接拨打电话
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "您还没有赋予应用可以拨打电话的权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(callIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i < 2; i++) {
            DesignOrMeasureFragment fragment = (DesignOrMeasureFragment) fragments.get(0);
            if (fragment.setPopupWindowDismiss()) {
                return;
            }
        }
        super.onBackPressed();
    }

}
