package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;
import com.bwf.yiqizhuangxiu.mvp.model.EffectColloectionModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class EffectColloectionModelImpl implements EffectColloectionModel{
    private int newPage;
    @Override
    public void loadNexPageData(final ColloectonCallBack callBack) {
        String url = UrlHandler.handleURL(Apis.URL_COLLECTION,newPage);
        OkHttpUtils.get().url(url).addParams("version","1").addParams("page",newPage+"")
                .addParams("pagesize",10+"").addParams("action","ablumList2")
                .addParams("tagid ",1+"").addParams("model","android")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.loadColloectionFail();
            }

            @Override
            public void onResponse(String response, int id) {
                EffectCollectionData data = JSON.parseObject(response, EffectCollectionData.class);
                callBack.loadColloectionSuccess(data.getData().getList());
            }
        });
    }
}
