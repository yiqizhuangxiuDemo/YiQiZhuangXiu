package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Looper;
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
            public void onItemClickListener(View view, CityActivityData.DataBean.ForumlistBean datas) {
                boolean b = Looper.getMainLooper() != Looper.myLooper();
                startActivity(new Intent(ActivitiesActivity.this, ActivityDetails.class));
            }
        });
        cityActivityRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (manager.findLastVisibleItemPosition() == activityAdapter.getItemCount() - 1) {
                    loadNextPageData();
                }
            }
        });
    }

    @Override
    public void onShowOwnerSayPageUpToDataFailed(String info) {
        Log.d("ActivitiesActivity", info);

    }

    @Override
    public void onShowOwnerSayPageUpToDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas) {
        if (datas.size() > 0) {
            activityAdapter.addDatas(datas);
            isLoading = false;
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void loadMore() {
        loadNextPageData();
    }

    private void loadNextPageData() {
        if (!isLoading) {
            presenterCityActivity.loadDatasCityActivityDatas();
        }
    }

    private boolean isLoading;
}
