package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.CityActivityData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleCityActivity;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModleCityActivityImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterCityActivity;
import com.bwf.yiqizhuangxiu.mvp.view.ViewCityActivity;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class PresenterCityActivityImpl implements PresenterCityActivity, ModleCityActivity.ModleCityActivityDataCallBack {
    private ModleCityActivity modleCityActivity;
    private ViewCityActivity viewCityActivity;
    private int page;
    public PresenterCityActivityImpl(ViewCityActivity viewCityActivity) {
        this.viewCityActivity = viewCityActivity;
        this.modleCityActivity = new ModleCityActivityImpl();
    }

    @Override
    public void loadDatasCityActivityDatas() {
        page ++;
        modleCityActivity.downLoadCityActivity(this,page);
    }

    @Override
    public void ModleCityActivityDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas) {
        viewCityActivity.onShowOwnerSayPageUpToDataSuccess(datas);
    }

    @Override
    public void ModleCityActivityDataFailed(String info) {
        page --;
        viewCityActivity.onShowOwnerSayPageUpToDataFailed(info);
    }
}
