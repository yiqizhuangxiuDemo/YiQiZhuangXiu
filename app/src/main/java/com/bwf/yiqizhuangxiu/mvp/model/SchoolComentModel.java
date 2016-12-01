package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public interface SchoolComentModel {
    void loadData(int tag,CallBack callBack);
    interface  CallBack{
        void loadDataSuccess(List<SchoolConmentData.DataBean.ListBean> listBeen);
        void loadFail();
    }
}
