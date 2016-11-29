package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyDecoratorSettingModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyDecoratorSettingModelImpl implements CompanyDecoratorSettingModel{
    @Override
    public void loadDecoratorPlayingData(final DecoratorPlayingCallBack callBack) {
        String url = Apis.URL_COMPANT_DECORTAR;
        OkHttpUtils.post().url(url).addParams("token","DAB088BA50C9405E84C789055D657614")
                .addParams("city_id","2").addParams("cityName","成都")
                .addParams("app_version","app_version")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.loadFail(e.toString());
                Log.d("CompanyDecoratorSetting", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("CompanyDecoratorSetting", response.toString());
                CompanyDecorateData decorateData = JSON.parseObject(response, CompanyDecorateData.class);
                callBack.loadSuccess(decorateData.getData());
            }
        });
    }
}
