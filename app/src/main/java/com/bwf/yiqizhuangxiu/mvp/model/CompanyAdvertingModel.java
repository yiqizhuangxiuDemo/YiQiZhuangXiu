package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface CompanyAdvertingModel {
    void load(AdvertingCallBack callBack);
    interface  AdvertingCallBack{
        void loadCompanySuccess(List<CompanyAdvertingData.DataBean> advertingData);
        void loadFial(String error);
    }
}
