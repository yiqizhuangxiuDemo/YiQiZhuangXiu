package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import android.util.Log;

import com.bwf.yiqizhuangxiu.entity.SearchData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleSearch;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModleSearchImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterSearch;
import com.bwf.yiqizhuangxiu.mvp.view.ViewSearch;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/30.
 */

public class PresenterSearchImpl implements PresenterSearch, ModleSearch.SearchDataCallBank {
    private ViewSearch viewSearch;
    private ModleSearch modleSearch;
    private int page;

    public PresenterSearchImpl(ViewSearch viewSearch) {
        this.viewSearch = viewSearch;
        modleSearch = new ModleSearchImpl();
    }
    @Override
    public void loadData(String search) {
        page++;
        modleSearch.downLoadSearchData(this,page,search);
    }

    @Override
    public void searchDataCallBankSuccess(List<SearchData.DataBean> datas) {
        if (datas != null) {
            viewSearch.showSearchDataSuccess(datas);
        } else {
            viewSearch.showSearchDataFailed("没有找到你查询的内容");
        }
    }

    @Override
    public void searchDataCallBankFailed(String info) {
        page--;
        viewSearch.showSearchDataFailed(info);
    }
}
