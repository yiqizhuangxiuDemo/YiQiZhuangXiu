package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ModelHomepageHeadData {
    void loadHomePageHeadData(CallBack callBack);
    interface  CallBack{
        void onLoadHomePageHeadDataSuccess(List<HomepageHeadData.DataBean> datas);
        void onLoadHomePageHeadDataFaied(String info);
    }
}
