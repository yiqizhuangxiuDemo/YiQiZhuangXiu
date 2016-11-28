package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;



/**
 * Created by Administrator on 2016/10/14.
 */

public class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MainActivityFragmentPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
