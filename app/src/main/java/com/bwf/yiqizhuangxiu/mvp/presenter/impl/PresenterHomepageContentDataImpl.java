package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelHomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelHomepageContentDataImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterHomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.view.ViewHomepageContentData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PresenterHomepageContentDataImpl implements PresenterHomepageContentData, ModelHomepageContentData.CallBack {
    private ViewHomepageContentData view;
    private ModelHomepageContentData model;
    private int page;
    private int type;
    private int id;

    public PresenterHomepageContentDataImpl(ViewHomepageContentData view) {
        this.view = view;
        model = new ModelHomepageContentDataImpl();
    }

    @Override
    public void loadData() {
        page++;
        model.loadHomePageContentData(page, type, id, this);
    }

    @Override
    public void onLoadHomePageContentDataSuccess(List<HomepageContentData.DataBean> datas) {
        if (datas != null && datas.size() > 0) {
            view.onLoadHomePageContentDataSuccess(datas);
        } else {
            onLoadHomePageContentDataFaied("意外错误，获取到的内容为空");
        }
    }

    @Override
    public void onLoadHomePageContentDataFaied(String info) {
        page--;
        view.onLoadHomePageContentDataFaied(info);
    }
}
