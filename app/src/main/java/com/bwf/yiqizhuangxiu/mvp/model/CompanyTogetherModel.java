package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public interface CompanyTogetherModel {
    void loadNewPage(int  designTag,CompanyTogetherCallBack callBack);
    interface  CompanyTogetherCallBack{
        void loadDataSuccess(List<CompanyTogtherData.DataBean> togtherData);
        void loadDatasFail();
    }
}
