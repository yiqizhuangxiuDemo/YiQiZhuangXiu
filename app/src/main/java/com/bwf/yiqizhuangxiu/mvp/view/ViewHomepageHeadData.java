package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ViewHomepageHeadData {
    void onLoadHomePageHeadDataSuccess(List<HomepageHeadData.DataBean> datas);

    void onLoadHomePageHeadDataFaied(String info);
}
