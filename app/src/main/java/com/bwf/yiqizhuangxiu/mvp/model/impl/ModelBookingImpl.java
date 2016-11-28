package com.bwf.yiqizhuangxiu.mvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.entity.BookingData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelBooking;
import com.bwf.yiqizhuangxiu.utils.Apis;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.UrlHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ModelBookingImpl implements ModelBooking {

    @Override
    public void loadBookingData(int cityId, final CallBack callBack) {
        OkHttpUtils.get()
                .url(UrlHandler.handleURL(Apis.API_BOOOKING, cityId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("loadBookingData", e.toString());
                        callBack.loadBookingFailed("网络连接出现错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BookingData bookingData = JSON.parseObject(response, BookingData.class);
                        if (bookingData != null && bookingData.getError() == 0) {
                            callBack.loadBookingSuccess(bookingData.getData());
                        } else if (bookingData != null) {
                            callBack.loadBookingFailed(bookingData.getMessage());
                        } else {
                            callBack.loadBookingFailed("网络连接出现错误");
                        }
                    }
                });
    }
}
