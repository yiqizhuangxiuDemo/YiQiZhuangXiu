package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.mvp.model.SchoolTitleModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.SchoolTitleModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.SchoolTitlePresenter;
import com.bwf.yiqizhuangxiu.mvp.view.SchoolTitleView;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class SchoolTitlePresenterImpl implements SchoolTitlePresenter, SchoolTitleModel.CallBack {
    private SchoolTitleModel model;
    private SchoolTitleView view;

    public SchoolTitlePresenterImpl(SchoolTitleView view) {
        model = new SchoolTitleModelImpl();
        this.view = view;
    }

    @Override
    public void loadData(int tag) {
        model.loadData(this,tag);
    }

    @Override
    public void loadDataSuccess(Map<String,String> data) {
        view.showSchoolTitelDtas(data);
    }

    @Override
    public void loadDataFail() {
        view.showSchoolTitelFial();
    }
}
