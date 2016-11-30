package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.PostDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelPostDetails;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelPostDetailsImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterPostDetails;
import com.bwf.yiqizhuangxiu.mvp.view.ViewPostDetails;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public class PresenterPostDetailsImpl implements PresenterPostDetails, ModelPostDetails.CallBack {
    private ViewPostDetails view;
    private ModelPostDetails model;

    private int page;

    public PresenterPostDetailsImpl(ViewPostDetails view) {
        this.view = view;
        model = new ModelPostDetailsImpl();
    }

    @Override
    public void loadPostDetailsData(String id) {
        page++;
        model.loadAllData(id, page, this);
    }

    @Override
    public void onLoadContentSuccess(PostDetailsContentDataBean.DataBean data) {
        if (data != null) {
            view.onLoadContentSuccess(data);
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

    @Override
    public void onLoadCommentsSuccess(List<PostDetailsCommentsData.DataBean> datas, int commentNum) {
        if (datas != null) {
            view.onLoadCommentsSuccess(datas, commentNum);
        } else {
            view.onLoadLikeFailed("网络连接失败");
        }
    }

    @Override
    public void onLoadCommentsFailed(String message) {
        page--;
        view.onLoadLikeFailed(message);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
