package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;
import com.bwf.yiqizhuangxiu.mvp.model.SchoolComentModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.SchoolCommentMOdelImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.SchoolCommentPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.SchoolComentView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class SchoolCommentPresenterImpl implements SchoolCommentPresenter {
    private SchoolComentModel model;
    private SchoolComentView view;

    public SchoolCommentPresenterImpl(SchoolComentView view) {
        model = new SchoolCommentMOdelImpl();
        this.view = view;
    }

    @Override
    public void loadData(int tag) {
        model.loadData(tag, new SchoolComentModel.CallBack() {
            @Override
            public void loadDataSuccess(List<SchoolConmentData.DataBean.ListBean> listBeen) {
                view.showSchoolComment(listBeen);
            }

            @Override
            public void loadFail() {
                view.showSchoolComentFail();
            }
        });
    }
}
