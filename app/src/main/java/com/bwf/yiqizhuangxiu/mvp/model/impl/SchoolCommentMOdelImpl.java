package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;
import com.bwf.yiqizhuangxiu.mvp.model.SchoolComentModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class SchoolCommentMOdelImpl implements SchoolComentModel {
    private  int nextPage =1;
    @Override
    public void loadData(int tag, final CallBack callBack) {
        String url = UrlHandler.handleURL(Apis.URL_SCHOOL_COMMENT,tag,nextPage);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("SchoolCommentMOdelImpl", e.toString());
                callBack.loadFail();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("SchoolCommentMOdelImpl", response);
                SchoolConmentData schoolConmentData = JSON.parseObject(response, SchoolConmentData.class);
                callBack.loadDataSuccess(schoolConmentData.getData().getList());
            }
        });
    }
}
