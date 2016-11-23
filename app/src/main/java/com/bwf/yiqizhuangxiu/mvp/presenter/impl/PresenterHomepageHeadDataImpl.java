package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelHomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelHomepageHeadDataImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterHomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.view.ViewHomepageHeadData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PresenterHomepageHeadDataImpl implements PresenterHomepageHeadData, ModelHomepageHeadData.CallBack {

    private ViewHomepageHeadData view;
    private ModelHomepageHeadData model;

    public PresenterHomepageHeadDataImpl(ViewHomepageHeadData view) {
        this.view = view;
        model = new ModelHomepageHeadDataImpl();
    }

    @Override
    public void loadData() {
        model.loadHomePageHeadData(this);
    }

    @Override
    public void onLoadHomePageHeadDataSuccess(List<HomepageHeadData.DataBean> datas) {
        if (datas != null && datas.size() > 0) {
            view.onLoadHomePageHeadDataSuccess(datas);
        } else {
            onLoadHomePageHeadDataFaied("意外错误，获取到的内容为空");
        }
    }

    @Override
    public void onLoadHomePageHeadDataFaied(String info) {
        view.onLoadHomePageHeadDataFaied(info);
    }
}
