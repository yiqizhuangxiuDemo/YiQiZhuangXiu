package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface CompanyAdevertingView {
    void showAdvertingSuccess(List<CompanyAdvertingData.DataBean> advertingData);
    void showAdaertingFail(String error);
}