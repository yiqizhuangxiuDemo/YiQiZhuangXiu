package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.bwf.yiqizhuangxiu.gui.activity.ActivitiesActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BookingActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BudgetActivity;
import com.bwf.yiqizhuangxiu.gui.activity.CompanyActivity;
import com.bwf.yiqizhuangxiu.gui.activity.DesignActivity;
import com.bwf.yiqizhuangxiu.gui.activity.EffectActivity;
import com.bwf.yiqizhuangxiu.gui.activity.FitmentActivity;
import com.bwf.yiqizhuangxiu.gui.activity.SchoolActivity;
import com.bwf.yiqizhuangxiu.gui.adapter.HomepageViewPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.custom.AutoScrollViewPager;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterHomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterHomepageHeadDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewHomepageHeadData;
import com.bwf.yiqizhuangxiu.utils.indicator.ViewPagerIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomePageFragment extends BaseFragment implements ViewHomepageHeadData {

    @Bind(R.id.viewpager_homepage_head)
    AutoScrollViewPager viewpagerHomepageHead;
    @Bind(R.id.viewpager_homepage_head_indicator)
    LinearLayout viewpagerHomepageHeadIndicator;
    private PresenterHomepageHeadData presenterHomepageHeadData;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterHomepageHeadData = new PresenterHomepageHeadDataImpl(this);
        presenterHomepageHeadData.loadData();
    }

    @Override
    public void onLoadHomePageHeadDataSuccess(List<HomepageHeadData.DataBean> datas) {
        HomepageViewPagerAdapter adapter = new HomepageViewPagerAdapter(getContext(), datas);
        viewpagerHomepageHead.setAdapter(adapter);
        ViewPagerIndicator viewPagerIndicator = new ViewPagerIndicator(getContext()
                , viewpagerHomepageHead, viewpagerHomepageHeadIndicator, datas.size());
        viewPagerIndicator.setDotHeightByDp(4);
        viewPagerIndicator.setDotWidthByDp(4);
        viewPagerIndicator.setMarginByDp(4);
        viewPagerIndicator.create();
    }

    @Override
    public void onLoadHomePageHeadDataFaied(String info) {

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

    @OnClick({R.id.textview_company, R.id.textview_activities, R.id.textview_school, R.id.textview_budget, R.id.textview_fitment, R.id.textview_effect, R.id.textview_booking, R.id.textview_design})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_company:
                jumpToActivity(CompanyActivity.class);
                break;
            case R.id.textview_activities:
                jumpToActivity(ActivitiesActivity.class);
                break;
            case R.id.textview_school:
                jumpToActivity(SchoolActivity.class);
                break;
            case R.id.textview_budget:
                jumpToActivity(BudgetActivity.class);
                break;
            case R.id.textview_fitment:
                jumpToActivity(FitmentActivity.class);
                break;
            case R.id.textview_effect:
                jumpToActivity(EffectActivity.class);
                break;
            case R.id.textview_booking:
                jumpToActivity(BookingActivity.class);
                break;
            case R.id.textview_design:
                jumpToActivity(DesignActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class intentClass) {
        Intent intent = new Intent(getContext(), intentClass);
        startActivity(intent);
    }
}
