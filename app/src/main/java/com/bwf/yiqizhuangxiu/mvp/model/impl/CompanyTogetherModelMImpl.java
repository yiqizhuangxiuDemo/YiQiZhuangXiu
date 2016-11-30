package com.bwf.yiqizhuangxiu.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;
import com.bwf.yiqizhuangxiu.mvp.model.CompanyTogetherModel;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class CompanyTogetherModelMImpl implements CompanyTogetherModel{
    private   static  int nexPage=1;
    private static String type;

    @Override
    public void loadNewPage(int  designTag, final CompanyTogetherCallBack callBack) {
        String url = Apis.URL_SUPERVISOR;
        if (designTag ==1){
            type = "1701";
        }else if (designTag == 2){
            type = "1703";
        }else
            type= "1702";
//        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.d("CompanyTogetherModelMIm", e.toString());
//                callBack.loadDatasFail();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.d("CompanyTogetherModelMIm", response);
//                CompanyTogtherData data = JSON.parseObject(response, CompanyTogtherData.class);
//                callBack.loadDataSuccess(data.getData());
//                nexPage++;
//            }
//        });
        OkHttpUtils.post().url(url).addParams("token","DAB088BA50C9405E84C789055D657614")
                .addParams("app_version ","android_com.aiyiqi.galaxy_1.1").addParams("type",type)
                .addParams("haspermission","yes").addParams("pageNo ",nexPage+"")
                .addParams("pageSize",10+"").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("CompanyTogetherModelMIm", e.toString());
                callBack.loadDatasFail();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("CompanyTogetherModelMIm", response);
                CompanyTogtherData data = JSON.parseObject(response, CompanyTogtherData.class);
                callBack.loadDataSuccess(data.getData());
                nexPage++;
            }
        });

    }
}
