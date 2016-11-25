package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyAdvertingModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyAdvertingModelImpl implements CompanyAdvertingModel {
    @Override
    public void load(final AdvertingCallBack callBack) {
        OkHttpUtils.get().url(Apis.URL_COMPANT_ADVERTING).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.loadFial(e.toString());
                Log.d("CompanyAdvertingModelIm", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                CompanyAdvertingData advertingData = JSON.parseObject(response, CompanyAdvertingData.class);
                callBack.loadCompanySuccess(advertingData.getData());
                Log.d("CompanyAdvertingModelIm", response.toString());

            }
        });
    }
}
