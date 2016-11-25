package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPageCreamData;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${yong} on 2016/11/24.
 */

public class ModleOwnerSayPageCreamDataImpl implements ModleOwnerSayPageCreamData {
    @Override
    public void downLoadOwnerSayPageCreamData(int page,final OwnerSayPageCreamDataCallBack callBack) {
        String url = "";
        if (page != 1) {
            url = UrlHandler.handleURL(Apis.API_OWNERSAY_CREAM,page);
        } else {
            url = Apis.API_OWNERSAY_CREAM;
        }
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.OwnerSayPageCreamDataFailed("OwnerSayPageCreamDataFailed------------哎呀，数据下载失败啦！！！");
                LogUtils.e("OwnerSayPageCreamDataFailed",e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                OwnerSayCreamPageData ownerSayCreamPageData = JSON.parseObject(response, OwnerSayCreamPageData.class);
                Log.d("Modle-------------", "ownerSayCreamPageData.getData():" + ownerSayCreamPageData.getData());
                callBack.OwnerSayPageCreamDataSuccess(ownerSayCreamPageData.getData());
            }
        });

    }
}
