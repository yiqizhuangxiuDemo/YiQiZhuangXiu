package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;
import com.bwf.yiqizhuangxiu.mvp.model.WorkPlayingModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayingModelImpl implements WorkPlayingModel {
    private int nextPage = 1;
    @Override
    public void loadPeopleAndProgressData(String buildingId, final CallBack callBack) {
        String url = Apis.URL_WOEKPLAY_PEOPLE;
        OkHttpUtils.post().url(url).addParams("token","DAB088BA50C9405E84C789055D657614")
                .addParams("app_version","android_com.aiyiqi.galaxy_1.1")
                .addParams("buildingId",buildingId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.loadPeopleFail(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                WorkPlayingPeopleAndProgressData data = JSON.parseObject(response, WorkPlayingPeopleAndProgressData.class);
                callBack.loadPeopleSuccess(data);
            }
        });
    }

    @Override
    public void loadProgress(String buildingId, final ProgressCallBack callBack) {
        String  url = Apis.URL_WORK_PROGRESS;
        OkHttpUtils.post().url(url).addParams("progressId","1").addParams("app_version","android_com.aiyiqi.galaxy_1.1")
                .addParams("buildingId",buildingId).addParams("page",nextPage+"").addParams("pageSize","10")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.loadProgressFaild(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("WorkPlayingModelImpl","progress   :"+ response);
                WorkPlayProgressData data = JSON.parseObject(response, WorkPlayProgressData.class);
                callBack.loadProgressSuccess(data);
            }
        });
    }
}
