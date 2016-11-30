package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.ArticleDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsTagDataBean;
import com.bwf.yiqizhuangxiu.entity.ArticlesNewsData;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ModelArticlesDetails {
    void loadAllData(String id, int page, CallBack callBack);

    void loadArticleDetailsNewsData(String id, int page, CallBack callBack);

    void loadArticleDetailsCommentsData(String id, int page, CallBack callBack);

    public interface CallBack {
        void onLoadNewsSuccess(ArticlesNewsData.DataBean data, Map<String, ArticleDetailsTagDataBean> tags);

        void onLoadNewsFailed(String message);

        void onLoadCommentsSuccess(ArticleDetailsCommentsData.DataBeanA commentsData);

        void onLoadCommentsFailed(String message);
    }
}