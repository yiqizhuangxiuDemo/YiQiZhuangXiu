package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPagePlateData;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class ModleOwnerSayPagePlateDataImpl implements ModleOwnerSayPagePlateData {
    @Override
    public void downLoadModleOwnerSayPagePlateData(final ModleOwnerSayPagePlateDataCallBack callBack) {
        OkHttpUtils.get()
                .url(Apis.API_OWNERSAY_PLATE)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.ModleOwnerSayPagePlateDataFailed("------------哎呀，数据下载失败啦！！！"+e.toString());
                Log.d("ModleOwnerSayPagePlateD", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                OwnerSayPlatePageData ownerSayPlatePageData = JSON.parseObject(response, OwnerSayPlatePageData.class);
                Log.d("ModleOwnerSayPagePlateD", response);
                callBack.ModleOwnerSayPagePlateDataSuccess(ownerSayPlatePageData.getData().get(id));
            }
        });
    }
}
