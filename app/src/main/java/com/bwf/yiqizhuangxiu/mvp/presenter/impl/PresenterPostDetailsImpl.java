package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelPostDetails;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelPostDetailsImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterPostDetails;
import com.bwf.yiqizhuangxiu.mvp.view.ViewPostDetails;

/**
 * Created by Administrator on 2016/11/25.
 */

public class PresenterPostDetailsImpl implements PresenterPostDetails, ModelPostDetails.CallBack {
    private ViewPostDetails view;
    private ModelPostDetails model;

    public PresenterPostDetailsImpl(ViewPostDetails view) {
        this.view = view;
        model = new ModelPostDetailsImpl();
    }

    @Override
    public void loadPostDetailsData(String id) {
        model.loadPostDetailsContentData(id, this);
    }

    @Override
    public void onLoadContentSuccess(PostDetailsContentDataBean.DataBean data) {
        if (data != null) {
            view.onLoadContentFailed(data);
        } else {
            view.onLoadContentFailed("网络连接失败");
        }
    }

    @Override
    public void onLoadContentFailed(String message) {
        view.onLoadContentFailed(message);
    }

    @Override
    public void onLoadLikeSuccess(PostDetailsLikeData data) {
        view.onLoadLikeSuccess(data);
    }

    @Override
    public void onLoadLikeFailed(String message) {
        view.onLoadLikeFailed(message);
    }
}
