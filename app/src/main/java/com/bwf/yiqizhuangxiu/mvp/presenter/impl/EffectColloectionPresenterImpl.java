package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;
import com.bwf.yiqizhuangxiu.mvp.model.EffectColloectionModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.EffectColloectionModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.EffectColloectionPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.EffectCollectionView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class EffectColloectionPresenterImpl implements EffectColloectionPresenter {
    private EffectColloectionModel model;
    private EffectCollectionView view;

    public EffectColloectionPresenterImpl(EffectCollectionView view) {
        this.model = new EffectColloectionModelImpl();
        this.view = view;
    }

    @Override
    public void loadData() {
        loadNextPage();
    }

    @Override
    public void loadNextPage() {
        model.loadNexPageData(new EffectColloectionModel.ColloectonCallBack() {
            @Override
            public void loadColloectionSuccess(List<EffectCollectionData.DataBean.ListBean> listBeen) {
                view.showEffectionSuccess(listBeen);
            }

            @Override
            public void loadColloectionFail() {
                view.showEffectionFail();
            }

            @Override
            public void noMoreData() {
                view.noMoreData();
            }
        });
    }
}
