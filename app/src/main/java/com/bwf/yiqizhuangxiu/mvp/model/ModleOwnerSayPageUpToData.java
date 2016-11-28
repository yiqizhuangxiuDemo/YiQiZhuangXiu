package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public interface ModleOwnerSayPageUpToData {
    void downLoadOwnerSayPageUpToData(int page, ModleOwnerSayPageUpToDataCallBack callBack);
    interface ModleOwnerSayPageUpToDataCallBack{
        void ModleOwnerSayPageUpToDataSuccess(List<OwnerSayUpToDataPageData.DataBean> datas);
        void ModleOwnerSayPageUpToDataFailed(String info);
    }
}
