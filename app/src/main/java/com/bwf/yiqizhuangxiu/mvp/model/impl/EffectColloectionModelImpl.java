package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

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
    private int newPage=1;
    @Override
    public void loadNexPageData(final ColloectonCallBack callBack) {
        String url = UrlHandler.handleURL(Apis.URL_COLLECTION,newPage);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("EffectColloectionModelI", e.toString());
                callBack.loadColloectionFail();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("EffectColloectionModelI", response);
                EffectCollectionData data = JSON.parseObject(response, EffectCollectionData.class);
                if (data.getData().getTotalCount().equals(newPage+"")){
                    callBack.noMoreData();
                }
                callBack.loadColloectionSuccess(data.getData().getList());
                newPage++;
            }
        });
    }
}
