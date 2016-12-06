package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.BookingData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public interface ModelBooking {
    void loadBookingData(int cityId, CallBack callBack);

    interface CallBack {
        void loadBookingSuccess(List<BookingData.DataBeanA> datas);

        void loadBookingFailed(String message);
    }
}
