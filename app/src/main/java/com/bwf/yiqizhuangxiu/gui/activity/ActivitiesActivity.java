package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CityActivityData;
import com.bwf.yiqizhuangxiu.gui.adapter.CityActivityAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterCityActivity;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterCityActivityImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewCityActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class ActivitiesActivity extends BaseActivity implements ViewCityActivity, View.OnClickListener, CityActivityAdapter.LoadMoreDatasCallBack {
    @Bind(R.id.city_activity_recycleview)
    RecyclerView cityActivityRecycleview;
    private PresenterCityActivity presenterCityActivity;
    private ImageView imageView;
    private CityActivityAdapter activityAdapter;
    private LinearLayoutManager manager;
    private Intent intent;
    private Bundle bundle;
    private boolean isNoMoreData;
    private boolean isLoadingMore;

    @Override
    protected int getContentViewResId() {
        return R.layout.city_activity;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.city_activity_back);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        presenterCityActivity = new PresenterCityActivityImpl(this);
        activityAdapter = new CityActivityAdapter(this);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        cityActivityRecycleview.setLayoutManager(manager);
        cityActivityRecycleview.setAdapter(activityAdapter);
        activityAdapter.setLoadMoreDatasCallBack(this);
        activityAdapter.setOnItemClickListener(new CityActivityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(CityActivityData.DataBean.ForumlistBean datas) {
                intent = new Intent(ActivitiesActivity.this, ActivityDetails.class);
                bundle = new Bundle();
                if (datas.getUrls() != null && !"".equals(datas.getUrls())) {
                    Log.d("ActivitiesActivity", datas.getUrls().toString());
                    bundle.putString("URL",datas.getUrls().toString());
                } else {
                    bundle.putString("URL",datas.getGroupon_urls().toString());
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cityActivityRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isNoMoreData) {
                    return;
                }
                if (manager.findLastVisibleItemPosition() == activityAdapter.getItemCount() - 1) {
                    if (isLoadingMore) {
                        Log.d("ActivitiesActivity", "正在加载中");
                    } else {
                        loadNextPageData();
                    }
                }
            }
        });
    }

    @Override
    public void onShowOwnerSayPageUpToDataFailed(String info) {
        isLoadingMore = false;
        activityAdapter.updataFooterState(CityActivityAdapter.STATE_LOAD_FAILED);
    }

    @Override
    public void onShowOwnerSayPageUpToDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas) {
        if (!isNoMoreData) {
            activityAdapter.addDatas(datas);
            isLoadingMore = false;
        }
    }

    @Override
    public void showFooterNoMoreData() {
        isNoMoreData = true;
        activityAdapter.updataFooterState(CityActivityAdapter.STATE_NO_MORE_DATA);
    }

    @Override
    public void showFooterLoading() {
        activityAdapter.updataFooterState(CityActivityAdapter.STATE_LOADING);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void loadMore() {
        isLoadingMore = true;
        loadNextPageData();
    }

    private void loadNextPageData() {
        if (!isLoadingMore) {
            isLoadingMore = true;
            presenterCityActivity.loadDatasCityActivityDatas();
        }
    }

    private void refreshData() {
        presenterCityActivity.refreshData();
    }
}
