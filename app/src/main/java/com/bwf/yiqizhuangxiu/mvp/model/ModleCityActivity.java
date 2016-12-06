package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.CityActivityData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public interface ModleCityActivity {
    void downLoadCityActivity(ModleCityActivityDataCallBack callBack,int page);
    interface ModleCityActivityDataCallBack {
        void ModleCityActivityDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas);
        void ModleCityActivityDataFailed(String info);
        void noMoreData();
    }
}
