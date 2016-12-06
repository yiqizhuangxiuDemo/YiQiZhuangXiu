package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.SearchData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleSearch;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${yong} on 2016/11/30.
 */

public class ModleSearchImpl implements ModleSearch {
    private String url;
    @Override
    public void downLoadSearchData(final SearchDataCallBank callBank, int page, String search) {
        url = UrlHandler.handleURL(Apis.API_SEARCH,page,search);
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBank.searchDataCallBankFailed(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                SearchData datas = JSON.parseObject(response, SearchData.class);
                callBank.searchDataCallBankSuccess(datas.getData());
            }
        });
    }
}
