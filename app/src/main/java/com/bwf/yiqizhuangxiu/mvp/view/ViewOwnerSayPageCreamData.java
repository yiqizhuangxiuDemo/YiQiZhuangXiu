package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public interface ViewOwnerSayPageCreamData {
    void onShowOwnerSayPageCreamDataFailed(String info);
    void onShowOwnerSayPageCreamDataSuccess(List<OwnerSayCreamPageData.DataBean> datas);
}
