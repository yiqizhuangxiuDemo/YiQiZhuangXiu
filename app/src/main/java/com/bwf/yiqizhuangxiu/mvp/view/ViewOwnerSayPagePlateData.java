package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public interface ViewOwnerSayPagePlateData {
    void onShowOwnerSayPagePlateDataSuccess(List<OwnerSayPlatePageData.DataBean> datas);
    void onShowOwnerSayPagePlateDataFailed(String info);
}
