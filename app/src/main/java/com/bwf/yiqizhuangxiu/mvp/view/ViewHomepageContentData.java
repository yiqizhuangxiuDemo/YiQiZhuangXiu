package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.HomepageContentData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ViewHomepageContentData {
    void onLoadHomePageContentDataSuccess(List<HomepageContentData.DataBean> datas);

    void onLoadHomePageContentDataFaied(String info);

    void isNoMoreData();
}
