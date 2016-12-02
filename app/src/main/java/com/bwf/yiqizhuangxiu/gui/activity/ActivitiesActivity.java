package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
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

public class ActivitiesActivity extends BaseActivity implements ViewCityActivity, View.OnClickListener {
    @Bind(R.id.city_activity_recycleview)
    RecyclerView cityActivityRecycleview;
    private PresenterCityActivity presenterCityActivity;
    private ImageView imageView;

    @Override
    protected int getContentViewResId() {
        return R.layout.city_activity;
    }

    @Override
    protected void initViews() {
        imageView = (ImageView) findViewById(R.id.city_activity_back);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        presenterCityActivity = new PresenterCityActivityImpl(this);
        presenterCityActivity.loadDatasCityActivityDatas();
    }

    @Override
    public void onShowOwnerSayPageUpToDataFailed(String info) {
        Log.d("ActivitiesActivity", "|||||||||||");

    }

    @Override
    public void onShowOwnerSayPageUpToDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas) {
        Log.d("ActivitiesActivity", "8888888888888");
        Log.d("ActivitiesActivity", datas.toString());
        CityActivityAdapter activityAdapter = new CityActivityAdapter(this, datas);
        activityAdapter.setOnItemClickListener(new CityActivityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, CityActivityData.DataBean.ForumlistBean datas) {
                boolean b = Looper.getMainLooper() != Looper.myLooper();
                Log.d("A", "b:" + b);
                Log.d("ActivitiesActivity", datas.toString());
                startActivity(new Intent(ActivitiesActivity.this,ActivityDetails.class));
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        cityActivityRecycleview.setLayoutManager(manager);
        cityActivityRecycleview.setAdapter(activityAdapter);

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
