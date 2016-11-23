package com.bwf.yiqizhuangxiu.mvp.model;


import com.bwf.yiqizhuangxiu.entity.HomepageContentData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ModelHomepageContentData {
    void loadHomePageContentData(int page, int type, int id, CallBack callBack);

    public interface CallBack {
        void onLoadHomePageContentDataSuccess(List<HomepageContentData.DataBean> datas);

        void onLoadHomePageContentDataFaied(String info);
    }
}