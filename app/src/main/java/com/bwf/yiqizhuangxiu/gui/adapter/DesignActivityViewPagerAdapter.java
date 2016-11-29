package com.bwf.yiqizhuangxiu.gui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class DesignActivityViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public DesignActivityViewPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "免费设计";
        }
        return "免费量房";
    }
}
