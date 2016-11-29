package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public interface CompanyTogetherView {
    void showCompanyTogetherData(List<CompanyTogtherData.DataBean> dataBeen);
    void showCompanyTogetherFial();
}
