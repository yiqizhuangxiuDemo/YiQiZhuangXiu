package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragment1s, List<String> titles) {
        super(fm);
        mFragments=fragment1s;
        mTitles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
