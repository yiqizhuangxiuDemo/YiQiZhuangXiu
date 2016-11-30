package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public interface EffectBeatifulView {
    void showEffectBeatifulSuccess(List<EffectBeatifulData.DataBean.ListBean> listBeen);
    void showEffectBeatifulFail();
}
