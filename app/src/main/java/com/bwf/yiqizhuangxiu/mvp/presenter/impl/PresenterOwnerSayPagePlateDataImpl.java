package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import android.util.Log;

import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPagePlateData;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModleOwnerSayPagePlateDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPagePlateData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class PresenterOwnerSayPagePlateDataImpl implements com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPagePlateData, ModleOwnerSayPagePlateData.ModleOwnerSayPagePlateDataCallBack {
    private ViewOwnerSayPagePlateData viewOwnerSayPagePlateData;
    private ModleOwnerSayPagePlateData modleOwnerSayPagePlateData;
    public PresenterOwnerSayPagePlateDataImpl(ViewOwnerSayPagePlateData viewOwnerSayPagePlateData) {
        this.viewOwnerSayPagePlateData = viewOwnerSayPagePlateData;
        modleOwnerSayPagePlateData = new ModleOwnerSayPagePlateDataImpl();
    }
    @Override
    public void loadPresenterOwnerSayPagePlateData() {
        modleOwnerSayPagePlateData.downLoadModleOwnerSayPagePlateData(this);
    }

    @Override
    public void ModleOwnerSayPagePlateDataSuccess(List<OwnerSayPlatePageData.DataBean> datas) {
        if (datas != null) {
            viewOwnerSayPagePlateData.onShowOwnerSayPagePlateDataSuccess(datas);
        } else {
            viewOwnerSayPagePlateData.onShowOwnerSayPagePlateDataFailed("检查网络");
        }
    }

    @Override
    public void ModleOwnerSayPagePlateDataFailed(String info) {
        viewOwnerSayPagePlateData.onShowOwnerSayPagePlateDataFailed(info);
        Log.d("PresenterOwnerSayPagePl", info);
    }
}
