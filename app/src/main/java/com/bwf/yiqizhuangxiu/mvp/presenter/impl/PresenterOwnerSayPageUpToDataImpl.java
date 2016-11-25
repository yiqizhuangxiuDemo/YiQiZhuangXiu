package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPageUpToData;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModleOwnerSayPageUpToDataImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPageUpToData;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPageUpToData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class PresenterOwnerSayPageUpToDataImpl implements PresenterOwnerSayPageUpToData, ModleOwnerSayPageUpToData.ModleOwnerSayPageUpToDataCallBack {
    private int page;
    private ModleOwnerSayPageUpToData modleOwnerSayPageUpToData;
    private ViewOwnerSayPageUpToData viewOwnerSayPageUpToData;

    public PresenterOwnerSayPageUpToDataImpl(ViewOwnerSayPageUpToData viewOwnerSayPageUpToData) {
        this.viewOwnerSayPageUpToData = viewOwnerSayPageUpToData;
        modleOwnerSayPageUpToData = new ModleOwnerSayPageUpToDataImpl();
    }

    @Override
    public void loadOwnerSayPageUpToData() {
        page++;
        modleOwnerSayPageUpToData.downLoadOwnerSayPageUpToData(page,this);
    }

    @Override
    public void ModleOwnerSayPageUpToDataSuccess(List<OwnerSayUpToDataPageData.DataBean> datas) {
        if (datas != null) {
            viewOwnerSayPageUpToData.onShowOwnerSayPageUpToDataSuccess(datas);
        } else {
            ModleOwnerSayPageUpToDataFailed("检查网络");
        }
    }

    @Override
    public void ModleOwnerSayPageUpToDataFailed(String info) {
        page--;
        viewOwnerSayPageUpToData.onShowOwnerSayPageUpToDataFailed(info);
    }
}
