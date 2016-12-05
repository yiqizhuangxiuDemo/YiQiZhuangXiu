package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwf.yiqizhuangxiu.mvp.model.SchoolTitleModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class SchoolTitleModelImpl implements SchoolTitleModel {
    @Override
    public void loadData(final CallBack callBack, int tag) {
        String url = UrlHandler.handleURL(Apis.URL_SCHOOL_TITLE,tag);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("SchoolTitleModelImpl  1", e.toString());
                callBack.loadDataFail();
            }

            @Override
            public void onResponse(String response, int id) {
                Map<String,String> stringMap = new HashMap<String, String>();
                JSONObject jsonObject = JSON.parseObject(response);
                Set<String> keySet = jsonObject.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()){
                    String key = iterator.next();
                    Log.d("SchoolTitleModelImpl  2", key);
                    stringMap.put(key,jsonObject.getString(key));
                }
                String data = stringMap.get("data");
                Map<String,String> databen = new HashMap<String, String>();
                JSONObject jsonObject1 = JSON.parseObject(data);
                Set<String> keySet1 = jsonObject1.keySet();
                Iterator<String> iterator1 = keySet1.iterator();
                while (iterator1.hasNext()){
                    String   key1 = iterator1.next();
                    databen.put(key1,jsonObject1.getString(key1));
                }
                callBack.loadDataSuccess(databen);
            }
        });
    }
}
