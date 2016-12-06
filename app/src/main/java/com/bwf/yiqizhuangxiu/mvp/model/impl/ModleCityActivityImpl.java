package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.CityActivityData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleCityActivity;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class ModleCityActivityImpl implements ModleCityActivity {

    @Override
    public void downLoadCityActivity(final ModleCityActivityDataCallBack callBack, int page) {
        String url = UrlHandler.handleURL(Apis.API_CITY_ACTIVITY,page);
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.ModleCityActivityDataFailed(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("ModleCityActivityImpl", response);
                CityActivityData data = JSON.parseObject(response, CityActivityData.class);
                if (data.toString() != null) {
                    callBack.ModleCityActivityDataSuccess(data.getData().getForumlist());
                }
            }
        });
    }
}
