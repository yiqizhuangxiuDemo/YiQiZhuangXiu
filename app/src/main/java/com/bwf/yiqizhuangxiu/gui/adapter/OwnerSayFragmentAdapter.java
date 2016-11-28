package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class OwnerSayFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public OwnerSayFragmentAdapter(FragmentManager fm, List<String> mTitleList,List<Fragment> mFragmentList) {
        super(fm);
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
