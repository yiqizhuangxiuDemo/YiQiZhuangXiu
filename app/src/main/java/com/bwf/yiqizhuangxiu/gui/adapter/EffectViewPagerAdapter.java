package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.yiqizhuangxiu.gui.fragment.ArticleFragment;
import com.bwf.yiqizhuangxiu.gui.fragment.CollectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class EffectViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public EffectViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new CollectionFragment());
        fragments.add(new ArticleFragment());
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
        if (position == 0){
            return "专题";
        }else
            return "美图";
    }
}
