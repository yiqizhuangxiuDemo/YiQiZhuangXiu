package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.SearchData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/30.
 */

public interface ModleSearch {
    void downLoadSearchData(SearchDataCallBank callBank,int page,String search);
    interface SearchDataCallBank {
        void searchDataCallBankSuccess(List<SearchData.DataBean> datas);
        void searchDataCallBankFailed(String info);
    }
}
