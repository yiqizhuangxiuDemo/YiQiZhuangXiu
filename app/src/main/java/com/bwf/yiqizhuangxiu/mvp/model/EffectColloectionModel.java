package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public interface EffectColloectionModel {
    void loadNexPageData(ColloectonCallBack callBack);
    interface  ColloectonCallBack{
        void loadColloectionSuccess(List<EffectCollectionData.DataBean.ListBean> listBeen);
        void loadColloectionFail();
        void noMoreData();
    }
}
