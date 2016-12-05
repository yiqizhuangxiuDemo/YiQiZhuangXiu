package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.fragment.FragmentSchoolMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class SchoolTabLayoutAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private String [] title;
    public SchoolTabLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        title = context.getResources().getStringArray(R.array.schollTitle);
        fragments = new ArrayList<>();
        for (int i = 1; i < 18; i++) {
            fragments.add(FragmentSchoolMain.newInstance(i));
        }
        Log.d("SchoolTabLayoutAdapter", "title.length:" + title.length);
        Log.d("SchoolTabLayoutAdapter", "fragments.size():" + fragments.size());
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
        Log.d("SchoolTabLayoutAdapter", title[position]);
        return title[position];
    }
}
