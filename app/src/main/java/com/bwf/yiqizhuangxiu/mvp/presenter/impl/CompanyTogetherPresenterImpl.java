package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyTogetherModel;
import com.bwf.yiqizhuangxiu.mvp.model.impl.CompanyTogetherModelMImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyToagetherPresenter;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyTogetherView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class CompanyTogetherPresenterImpl implements CompanyToagetherPresenter{
    private CompanyTogetherModel model;
    private CompanyTogetherView view;
    private int  designTag ;

    public CompanyTogetherPresenterImpl(CompanyTogetherView view,int  designTag) {
        this.model = new CompanyTogetherModelMImpl();
        this.view = view;
        this.designTag = designTag;
    }

    @Override
    public void loadData() {
        model.loadNewPage(designTag, new CompanyTogetherModel.CompanyTogetherCallBack() {
            @Override
            public void loadDataSuccess(List<CompanyTogtherData.DataBean> togtherData) {
                view.showCompanyTogetherData(togtherData);
            }

            @Override
            public void loadDatasFail() {
                view.showCompanyTogetherFial();
            }
        });
    }
}
