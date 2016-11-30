package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.bwf.yiqizhuangxiu.mvp.model.EffectBeatifulModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.EffectBeatifulModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.EffectBeatifulPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.EffectBeatifulView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class EffectBeatifulPresenterImpl implements EffectBeatifulPresenter {
    private EffectBeatifulModel model;
    private EffectBeatifulView view;

    public EffectBeatifulPresenterImpl(EffectBeatifulView view) {
        this.view = view;
        model = new EffectBeatifulModelImpl();
    }

    @Override
    public void loadData() {
        model.loadData(new EffectBeatifulModel.CallBack() {
            @Override
            public void loadBeatuifeDataSuccess(List<EffectBeatifulData.DataBean.ListBean> listBeen) {
                view.showEffectBeatifulSuccess(listBeen);
            }

            @Override
            public void loadBeatifulFail() {
                view.showEffectBeatifulFail();
            }
        });
    }
}
