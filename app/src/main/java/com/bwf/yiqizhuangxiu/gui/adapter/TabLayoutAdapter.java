package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.yiqizhuangxiu.gui.fragment.TogetherFragment;
import com.bwf.yiqizhuangxiu.utils.Apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(TogetherFragment.newInstance(1, Apis.URL_DESING_TEHEER));
        fragments.add(TogetherFragment.newInstance(2,Apis.URL_FOREMAN));
        fragments.add(TogetherFragment.newInstance(3,Apis.URL_SUPERVISOR));

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
        if (0==position){
            return "设计师";
        }else  if (1==position){
            return "工长";
        }
        return "监理";
    }
}
