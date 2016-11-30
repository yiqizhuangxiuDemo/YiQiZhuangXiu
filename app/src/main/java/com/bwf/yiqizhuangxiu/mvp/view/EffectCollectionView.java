package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public interface EffectCollectionView {
    void showEffectionSuccess(List<EffectCollectionData.DataBean.ListBean> listBeen);
    void showEffectionFail();
    void noMoreData();
}
