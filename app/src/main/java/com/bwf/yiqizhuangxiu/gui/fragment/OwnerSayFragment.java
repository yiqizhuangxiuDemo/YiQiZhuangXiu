package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.activity.SearchActivity;
import com.bwf.yiqizhuangxiu.gui.adapter.OwnerSayFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class OwnerSayFragment extends BaseFragment {
    @Bind(R.id.ownersay_fragment_tab_image)
    ImageView ownersayFragmentTabImage;
    private TabLayout mTabLayoout;
    private ViewPager mViewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        OwnerSayFragmentAdapter adapter = new OwnerSayFragmentAdapter(getFragmentManager(), mTitleList, mFragmentList);
        Log.d("OwnerSayFragment", "mViewPager:" + mViewPager);
        mViewPager.setAdapter(adapter);
        mTabLayoout.setupWithViewPager(mViewPager);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_ownersay;
    }

    private void init() {
        mTabLayoout = (TabLayout) getActivity().findViewById(R.id.ownersay_fragment_tab_tablayout);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.ownersay_fragment_viewpager);
        mTitleList.add("精华");
        mTitleList.add("最新");
        mTitleList.add("板块");

        mFragmentList.add(new FragmentOwnerSayCream());
        mFragmentList.add(new FragmentOwnerSayUpToData());
        mFragmentList.add(new FragmentOwnerSayPlate());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ownersay_fragment_tab_image)
    public void onClick() {
        startActivity(new Intent(getActivity(),SearchActivity.class));
    }
}
