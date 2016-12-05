package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.SearchData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/30.
 */

public interface ViewSearch {
    void showSearchDataSuccess(List<SearchData.DataBean> datas);
    void showSearchDataFailed(String info);
}
