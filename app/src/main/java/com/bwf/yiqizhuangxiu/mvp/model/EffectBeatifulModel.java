package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public interface EffectBeatifulModel {
    void loadData(CallBack callBack);
    interface  CallBack{
        void loadBeatuifeDataSuccess(List<EffectBeatifulData.DataBean.ListBean> listBeen);
        void loadBeatifulFail();
    }
}
