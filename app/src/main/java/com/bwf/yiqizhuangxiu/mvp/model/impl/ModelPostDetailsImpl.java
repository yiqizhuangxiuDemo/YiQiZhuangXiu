package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelPostDetails;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/25.
 */

public class ModelPostDetailsImpl implements ModelPostDetails {
    @Override
    public void loadAllData(String id, CallBack callBack) {
        loadPostDetailsContentData(id, callBack);
        loadPostDetailsLikeData(id, callBack);
        loadPostDetailsCommentsData(id, callBack);
    }

    @Override
    public void loadPostDetailsContentData(String id, final CallBack callBack) {
        OkHttpUtils.get()
                .url(UrlHandler.handleURL(Apis.API_POSTDETAILS_CONTENT, id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("loadPostDetailsContentData", e.toString());
                        callBack.onLoadContentFailed("网络连接出现错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        PostDetailsContentDataBean data = JSON.parseObject(response, PostDetailsContentDataBean.class);
                        if (data != null && "0".equals(data.getError())) {
                            callBack.onLoadContentSuccess(data.getData());
                        } else if (data != null) {
                            callBack.onLoadContentFailed(data.getMessage());
                        } else {
                            callBack.onLoadContentFailed("网络连接出现错误");
                        }
                    }
                });
    }

    @Override
    public void loadPostDetailsLikeData(String id, final CallBack callBack) {
        OkHttpUtils.get()
                .url(UrlHandler.handleURL(Apis.API_POSTDETAILS_LIKE, id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("loadPostDetailsContentData", e.toString());
                        callBack.onLoadLikeFailed("网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        PostDetailsLikeData data = JSON.parseObject(response, PostDetailsLikeData.class);
                        if (data != null) {
                            callBack.onLoadLikeSuccess(data);
                        } else {
                            callBack.onLoadLikeFailed("网络连接失败");
                        }
                    }
                });
    }

    @Override
    public void loadPostDetailsCommentsData(String id, CallBack callBack) {

    }
}
