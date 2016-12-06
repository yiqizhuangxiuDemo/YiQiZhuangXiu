package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.CityActivityData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public interface ViewCityActivity {
    void onShowOwnerSayPageUpToDataFailed(String info);
    void onShowOwnerSayPageUpToDataSuccess(List<CityActivityData.DataBean.ForumlistBean> datas);
    void showFooterNoMoreData();
    void showFooterLoading();
}
