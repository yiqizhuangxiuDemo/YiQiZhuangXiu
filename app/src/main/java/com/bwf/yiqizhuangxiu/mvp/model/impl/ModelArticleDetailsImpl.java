package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsTagDataBean;
import com.bwf.yiqizhuangxiu.entity.ArticlesNewsData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelArticlesDetails;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ModelArticleDetailsImpl implements ModelArticlesDetails {
    @Override
    public void loadAllData(String id, int page, CallBack callBack) {
        loadArticleDetailsNewsData(id, page, callBack);
        loadArticleDetailsCommentsData(id, page, callBack);
    }

    @Override
    public void loadArticleDetailsNewsData(String id, int page, final CallBack callBack) {
        OkHttpUtils.get()
                .url(UrlHandler.handleURL(Apis.API_ARTICLEDETAILS_CONTENT, id, page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("loadArticleDetailsNewsData", e.toString());
                        callBack.onLoadNewsFailed("网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ArticlesNewsData articles = JSON.parseObject(response, ArticlesNewsData.class);
                        if (articles != null && articles.getError() == 0) {
                            Map<String, ArticleDetailsTagDataBean> tags = new HashMap<>();
                            if (articles.getData().getTags() != null && !"".equals(articles.getData().getTags())) {
                                JSONObject jsonObject = JSONObject.parseObject(articles.getData().getTags());
                                Set set = jsonObject.keySet();
                                Iterator iterator = set.iterator();
                                while (iterator.hasNext()) {
                                    String key = "" + iterator.next();
                                    String value = jsonObject.getString(key);
                                    tags.put(key, JSON.parseObject(value, ArticleDetailsTagDataBean.class));
                                }
                            }
                            callBack.onLoadNewsSuccess(articles.getData(), tags);
                        } else if (articles != null) {
                            callBack.onLoadNewsFailed(articles.getMessage());
                        } else {
                            callBack.onLoadNewsFailed("网络连接失败");
                        }
                    }
                });
    }

    @Override
    public void loadArticleDetailsCommentsData(String id, int page, final CallBack callBack) {
        OkHttpUtils.get()
                .url(UrlHandler.handleURL(Apis.API_ARTICLEDETAILS_COMMENTS, id, page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("loadArticleDetailsCommentsData", e.toString());
                        callBack.onLoadNewsFailed("网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ArticleDetailsCommentsData commentsData = JSON.parseObject(
                                response, ArticleDetailsCommentsData.class);
                        if (commentsData != null && commentsData.getError() == 0) {
                            callBack.onLoadCommentsSuccess(commentsData.getData());
                        } else if (commentsData != null) {
                            callBack.onLoadCommentsFailed(commentsData.getMessage());
                        } else {
                            callBack.onLoadCommentsFailed("网络连接失败");
                        }
                    }
                });
    }
}
