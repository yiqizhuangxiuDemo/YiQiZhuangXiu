package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.ArticleDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsTagDataBean;
import com.bwf.yiqizhuangxiu.entity.ArticlesNewsData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelArticlesDetails;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelArticleDetailsImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterArticleDetails;
import com.bwf.yiqizhuangxiu.mvp.view.ViewArticleDetails;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public class PresenterArticleDetailsImpl implements PresenterArticleDetails, ModelArticlesDetails.CallBack {

    private int page = 1;
    private ViewArticleDetails view;
    private ModelArticlesDetails medel;

    public PresenterArticleDetailsImpl(ViewArticleDetails view) {
        this.view = view;
        medel = new ModelArticleDetailsImpl();
    }

    @Override
    public void loadArticleDetailsData(String id) {
        medel.loadAllData(id, page, this);
    }

    @Override
    public void onLoadNewsSuccess(ArticlesNewsData.DataBean data, Map<String, ArticleDetailsTagDataBean> tags) {
        if (data != null && tags.size() > 0) {
            view.onLoadNewsSuccess(data, tags);
        } else {
            view.onLoadNewsFailed("网络连接失败");
        }
    }

    @Override
    public void onLoadNewsFailed(String message) {
        view.onLoadNewsFailed(message);
    }

    @Override
    public void onLoadCommentsSuccess(ArticleDetailsCommentsData.DataBeanA commentsData) {
        if (commentsData != null) {
            view.onLoadCommentsSuccess(commentsData);
        } else {
            view.onLoadCommentsFailed("网络连接失败");
        }
    }

    @Override
    public void onLoadCommentsFailed(String message) {
        view.onLoadCommentsFailed(message);
    }
}
