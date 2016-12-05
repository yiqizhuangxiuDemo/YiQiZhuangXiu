package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public interface ModleOwnerSayPagePlateData {
    void downLoadModleOwnerSayPagePlateData(ModleOwnerSayPagePlateDataCallBack callBack);
    interface ModleOwnerSayPagePlateDataCallBack {
        void ModleOwnerSayPagePlateDataSuccess(List<List<OwnerSayPlatePageData.DataBean>> datas);
        void ModleOwnerSayPagePlateDataFailed(String info);
    }
}
