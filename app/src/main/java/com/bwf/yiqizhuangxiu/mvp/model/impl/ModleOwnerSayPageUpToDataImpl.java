package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPageUpToData;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class ModleOwnerSayPageUpToDataImpl implements ModleOwnerSayPageUpToData {

    @Override
    public void downLoadOwnerSayPageUpToData(int page, final ModleOwnerSayPageUpToDataCallBack callBack) {
        String url = "";
        if (page != 1) {
            url = UrlHandler.handleURL(Apis.API_OWNERSAY_UPTODATA,page);
        } else {
            url = Apis.API_OWNERSAY_UPTODATA;
        }

        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.ModleOwnerSayPageUpToDataFailed("ModleOwnerSayPageUpToDataFailed------------哎呀，数据下载失败啦！！！");
                Log.d("ModleOwnerSayPageUpToDa", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("ModleOwnerSayPageUpToDa", response);
                OwnerSayUpToDataPageData ownerSayUpToDataPageData = JSON.parseObject(response, OwnerSayUpToDataPageData.class);
                Log.d("ModleOwnerSayPageUpToDa", ownerSayUpToDataPageData.getData().toString());
                callBack.ModleOwnerSayPageUpToDataSuccess(ownerSayUpToDataPageData.getData());
            }
        });
    }
}
