package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyAdvertingModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.CompanyAdvertingModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyAdvertingPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyAdevertingView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyAdvertingPresenterImpl implements CompanyAdvertingPresenter, CompanyAdvertingModel.AdvertingCallBack {
    private CompanyAdvertingModel model;
    private CompanyAdevertingView view;

    public CompanyAdvertingPresenterImpl(CompanyAdevertingView view) {
        this.view = view;
        model = new CompanyAdvertingModelImpl();
    }

    @Override
    public void loadAdvertingData() {
        model.load(this);
    }

    @Override
    public void loadCompanySuccess(List<CompanyAdvertingData.DataBean> advertingData) {
        view.showAdvertingSuccess(advertingData);
    }

    @Override
    public void loadFial(String error) {
        view.showAdaertingFail(error);
    }
}
