package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface CompanyDecoratorSettingModel {
    void loadDecoratorPlayingData(DecoratorPlayingCallBack callBack);
    interface  DecoratorPlayingCallBack{
        void loadSuccess(List<CompanyDecorateData.DataBean> decorateData);
        void loadFail(String error);
    }
}
