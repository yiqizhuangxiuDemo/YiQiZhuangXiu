package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public interface ViewOwnerSayPageUpToData {
    void onShowOwnerSayPageUpToDataFailed(String info);
    void onShowOwnerSayPageUpToDataSuccess(List<OwnerSayUpToDataPageData.DataBean> datas);
}
