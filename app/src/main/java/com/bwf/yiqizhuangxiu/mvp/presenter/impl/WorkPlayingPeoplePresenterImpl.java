package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;
import com.bwf.yiqizhuangxiu.mvp.model.WorkPlayingModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.WorkPlayingModelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.WorkPlayingPeoplePresenter;
import com.bwf.yiqizhuangxiu.mvp.view.WorkPlayingView;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayingPeoplePresenterImpl implements WorkPlayingPeoplePresenter {
    private WorkPlayingModel model;
    private WorkPlayingView view;

    public WorkPlayingPeoplePresenterImpl(WorkPlayingView view) {
        model = new WorkPlayingModelImpl();
        this.view = view;
    }

    @Override
    public void loadData(String buildingId) {
        model.loadPeopleAndProgressData(buildingId, new WorkPlayingModel.CallBack() {
            @Override
            public void loadPeopleSuccess(WorkPlayingPeopleAndProgressData data) {
                view.showWorkPeopleAndProgress(data);
            }

            @Override
            public void loadPeopleFail(String e) {
                view.showorkPeopleFail(e);
            }
        });
    }

    @Override
    public void loadProgressData(String buildingId) {
        model.loadProgress(buildingId, new WorkPlayingModel.ProgressCallBack() {
            @Override
            public void loadProgressSuccess(WorkPlayProgressData data) {
                view.showProgressDataSuccess(data);
            }

            @Override
            public void loadProgressFaild(String error) {
                view.showProgressFail(error);
            }
        });
    }
}
