package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.BookingData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public interface ViewBooking {
    void loadBookingSuccess(List<BookingData.DataBeanA> datas);

    void loadBookingFailed(String message);
}
