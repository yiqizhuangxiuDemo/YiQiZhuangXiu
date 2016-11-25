package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public interface ModleOwnerSayPageCreamData {
    void downLoadOwnerSayPageCreamData(int page,OwnerSayPageCreamDataCallBack callBack);
    interface OwnerSayPageCreamDataCallBack{
        void OwnerSayPageCreamDataSuccess(List<OwnerSayCreamPageData.DataBean> datas);
        void OwnerSayPageCreamDataFailed(String info);
    }
}
