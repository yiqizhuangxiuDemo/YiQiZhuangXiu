package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.ArticleDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsTagDataBean;
import com.bwf.yiqizhuangxiu.entity.ArticlesNewsData;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public interface ViewArticleDetails {
    void onLoadNewsSuccess(ArticlesNewsData.DataBean data, Map<String, ArticleDetailsTagDataBean> tags);

    void onLoadNewsFailed(String message);

    void onLoadCommentsSuccess(ArticleDetailsCommentsData.DataBeanA commentsData);

    void onLoadCommentsFailed(String message);
}
