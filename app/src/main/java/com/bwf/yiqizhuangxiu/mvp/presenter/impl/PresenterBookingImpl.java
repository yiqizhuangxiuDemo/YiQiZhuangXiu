package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.BookingData;
import com.bwf.yiqizhuangxiu.mvp.model.ModelBooking;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModelBookingImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterBooking;
import com.bwf.yiqizhuangxiu.mvp.view.ViewBooking;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PresenterBookingImpl implements PresenterBooking, ModelBooking.CallBack {

    private ViewBooking view;
    private ModelBooking model;

    public PresenterBookingImpl(ViewBooking view) {
        this.view = view;
        model = new ModelBookingImpl();
    }

    @Override
    public void loadBookingData(int cityId) {
        model.loadBookingData(cityId, this);
    }

    @Override
    public void loadBookingSuccess(List<BookingData.DataBeanA> datas) {
        if (datas != null && datas.size() > 0) {
            view.loadBookingSuccess(datas);
        } else {
            view.loadBookingFailed("网络连接错误");
        }
    }

    @Override
    public void loadBookingFailed(String message) {
        view.loadBookingFailed(message);
    }
}
