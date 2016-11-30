package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.bwf.yiqizhuangxiu.mvp.model.EffectBeatifulModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class EffectBeatifulModelImpl implements EffectBeatifulModel {
    private  int nextPage = 1;
    @Override
    public void loadData(final CallBack callBack) {
        String url = UrlHandler.handleURL(Apis.URL_EFFECT_BEATIFUL,nextPage);
        OkHttpUtils.get().url(url).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.loadBeatifulFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        EffectBeatifulData data = JSON.parseObject(response, EffectBeatifulData.class);
                        callBack.loadBeatuifeDataSuccess(data.getData().getList());
                    }
                });
    }
}
