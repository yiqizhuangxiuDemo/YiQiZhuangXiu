package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelHomepageContentData;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23.
 */

public class ModelHomepageContentDataImpl implements ModelHomepageContentData {

    @Override
    public void loadHomePageContentData(int page, int type, String id, final CallBack callBack) {
        String url = "";
        if (page == 1) {
            url = Apis.API_HOMEPAGE_CONTENT_FIRST_PAGE;
        } else {
            url = UrlHandler.handleURL(Apis.API_HOMEPAGE_CONTENT, page, type, id);
        }
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onLoadHomePageContentDataFaied("网络连接出现错误");
                        LogUtils.e("onLoadHomePageHeadDataError", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        HomepageContentData data = JSON.parseObject(response, HomepageContentData.class);
                        if (data != null && "0".equals(data.getError())) {
                            callBack.onLoadHomePageContentDataSuccess(data.getData());
                        } else {
                            callBack.onLoadHomePageContentDataFaied("网络连接出现错误");
                        }
                    }
                });
    }
}
