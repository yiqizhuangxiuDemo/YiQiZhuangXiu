package com.bwf.yiqizhuangxiu.mvp.model;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public interface SchoolTitleModel {
    void loadData(CallBack callBack,int tag);
    interface CallBack{
        void loadDataSuccess(Map<String,String> data);
        void loadDataFail();
    }
}
