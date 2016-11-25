package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface CompanyDecoratorView {
    void showDecoratroPlayingData(List<CompanyDecorateData.DataBean> decorateData);
    void showDecoratroPlayindFiel();
}
