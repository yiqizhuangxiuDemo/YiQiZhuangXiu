package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;
import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;
import com.bwf.yiqizhuangxiu.gui.adapter.CompanyDecoratorRecycleViewAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.CompanyViewpagerAdater;
import com.bwf.yiqizhuangxiu.gui.adapter.RecyclerViewWithHeaderOrFooterAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyAdvertingPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyDecoratorPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.CompanyAdvertingPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.CompanyDecoratroPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyAdevertingView;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyDecoratorView;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.widget.IndicatorContainer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class CompanyActivity extends BaseActivity implements CompanyAdevertingView, CompanyDecoratorView {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.company_viewPager)
    ViewPager companyViewPager;
    @Bind(R.id.linear_indicator)
    LinearLayout linearIndicator;
    @Bind(R.id.new_house_decorate)
    LinearLayout newHouseDecorate;
    @Bind(R.id.old_house_decorate)
    LinearLayout oldHouseDecorate;
    @Bind(R.id.linear_look_up_work)
    LinearLayout linearLookUpWork;
    @Bind(R.id.image_site_playing)
    LinearLayout imageSitePlaying;
    @Bind(R.id.linear_yiqi_team)
    LinearLayout linearYiqiTeam;
    @Bind(R.id.decortae_playing_recycle_view)
    RecyclerView decortaePlayingRecycleView;
    RecyclerViewWithHeaderOrFooterAdapter recycleAdapter;
    private CompanyAdvertingPresenter advertingPresenter;
    private CompanyDecoratorPresenter decoratorPresenter;
    private CompanyViewpagerAdater advertingAdapter;
    private CompanyDecoratorRecycleViewAdapter decoratorRecycleViewAdapter;
    private IndicatorContainer indicatorContainer;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_company;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titlebarContent.setText(R.string.compan_decorator);
        decoratorRecycleViewAdapter = new CompanyDecoratorRecycleViewAdapter(this);
        decortaePlayingRecycleView.setAdapter(decoratorRecycleViewAdapter);
        decoratorRecycleViewAdapter.setOnItemClickListener(new RecyclerViewWithHeaderOrFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CompanyDecorateData.DataBean itemData = decoratorRecycleViewAdapter.getItemData(position);
                Intent intent = new Intent(CompanyActivity.this,WorkSpacePlaying.class);
                Log.d("CompanyActivity", "itemData.getBuildingSite().getBuildingId():" + itemData.getBuildingSite().getBuildingId()+"");
                intent.putExtra("buildingId",itemData.getBuildingSite().getBuildingId()+"");
                CompanyActivity.this.startActivity(intent);

            }
        });
    }

    @Override
    protected void initDatas() {
        advertingPresenter = new CompanyAdvertingPresenterImpl(this);
        advertingPresenter.loadAdvertingData();
        indicatorContainer = new IndicatorContainer(this,linearIndicator,companyViewPager);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        decortaePlayingRecycleView.setHasFixedSize(true);
        decortaePlayingRecycleView.setNestedScrollingEnabled(false);
        decortaePlayingRecycleView.setLayoutManager(manager);
        decoratorPresenter = new CompanyDecoratroPresenterImpl(this);
        decoratorPresenter.loadData();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @OnClick({R.id.titlebar_back, R.id.new_house_decorate, R.id.old_house_decorate, R.id.linear_look_up_work, R.id.image_site_playing, R.id.linear_yiqi_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.new_house_decorate:
                Intent intent = new Intent(this,HouseOfNewActivity.class);
                intent.putExtra("url", Apis.URL_NEW_HOUSER);
                intent.putExtra("title",getString(R.string.new_house));
                startActivity(intent);
                break;
            case R.id.old_house_decorate:
                Intent intent1 = new Intent(this,HouseOfNewActivity.class);
                intent1.putExtra("url", Apis.URL_OLD_HOUSER);
                intent1.putExtra("title",getString(R.string.old_house));
                startActivity(intent1);
                break;
            case R.id.linear_look_up_work:
                break;
            case R.id.image_site_playing:
                Intent intent2 = new Intent(this,WorkSpacePlaying.class);
                intent2.putExtra("buildingId",buildId);
                startActivity(intent2);
                break;
            case R.id.linear_yiqi_team:
                Intent intent3 = new Intent(this,TogetherTream.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void showAdvertingSuccess(List<CompanyAdvertingData.DataBean> advertingData) {
        advertingAdapter = new CompanyViewpagerAdater(CompanyActivity.this,advertingData);
        companyViewPager.setAdapter(advertingAdapter);
        indicatorContainer.setDotNum(advertingData.size());
    }

    @Override
    public void showAdaertingFail(String error) {

    }
    private String buildId = "0";
    @Override
    public void showDecoratroPlayingData(List<CompanyDecorateData.DataBean> decorateData) {
        decoratorRecycleViewAdapter.addDatas(decorateData);
        CompanyDecorateData.DataBean itemData = decoratorRecycleViewAdapter.getItemData(0);
        buildId = itemData.getBuildingSite().getBuildingId()+"";
    }

    @Override
    public void showDecoratroPlayindFiel() {

    }
}
