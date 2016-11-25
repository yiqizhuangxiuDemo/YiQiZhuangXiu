package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelHomepageHeadData;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23.
 */

public class ModelHomepageHeadDataImpl implements ModelHomepageHeadData {

    @Override
    public void loadHomePageHeadData(final CallBack callBack) {
        OkHttpUtils.get()
                .url(Apis.API_HOMEPAGE_HEAD)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onLoadHomePageHeadDataFaied("网络连接出现错误");
                        LogUtils.e("onLoadHomePageHeadDataError",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        HomepageHeadData data = JSON.parseObject(response, HomepageHeadData.class);
                        if (data != null && data.getError() == 0){
                            callBack.onLoadHomePageHeadDataSuccess(data.getData());
                        }else {
                            callBack.onLoadHomePageHeadDataFaied(data.getMessage());
                        }
                    }
                });
    }
}
