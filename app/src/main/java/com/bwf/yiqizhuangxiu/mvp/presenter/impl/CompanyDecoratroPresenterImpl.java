package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyDecoratorSettingModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.CompanyDecoratorSettingModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyDecoratorPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyDecoratorView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyDecoratroPresenterImpl implements CompanyDecoratorPresenter {
    private CompanyDecoratorSettingModel model;
    private CompanyDecoratorView view;

    public CompanyDecoratroPresenterImpl(CompanyDecoratorView view) {
        this.view = view;
        model = new CompanyDecoratorSettingModelImpl();
    }

    @Override
    public void loadData() {
        model.loadDecoratorPlayingData(new CompanyDecoratorSettingModel.DecoratorPlayingCallBack() {
            @Override
            public void loadSuccess(List<CompanyDecorateData.DataBean> decorateData) {
                view.showDecoratroPlayingData(decorateData);
            }

            @Override
            public void loadFail(String error) {
                view.showDecoratroPlayindFiel();
            }
        });
    }
}
