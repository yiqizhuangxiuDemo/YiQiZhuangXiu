package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class FragmentViewPageAdapter extends FragmentStatePagerAdapter {
    private List<String > mStringListList;
    private List<Fragment> mFragments;

    public FragmentViewPageAdapter(FragmentManager fm, List<String> stringListList, List<Fragment> fragments) {
        super(fm);
        mStringListList = stringListList;
        mFragments = fragments;
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
        return mStringListList.get(position);
    }
}
