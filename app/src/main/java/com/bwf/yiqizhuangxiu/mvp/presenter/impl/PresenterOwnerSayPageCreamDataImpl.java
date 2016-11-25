package com.bwf.yiqizhuangxiu.mvp.presenter.impl;

import android.util.Log;

import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.bwf.yiqizhuangxiu.mvp.model.ModleOwnerSayPageCreamData;
import com.bwf.yiqizhuangxiu.mvp.model.impl.ModleOwnerSayPageCreamDataImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPageCreamData;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPageCreamData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/24.
 */

public class PresenterOwnerSayPageCreamDataImpl implements PresenterOwnerSayPageCreamData, ModleOwnerSayPageCreamData.OwnerSayPageCreamDataCallBack {
    private ModleOwnerSayPageCreamData modle;
    private ViewOwnerSayPageCreamData view;
    private  int page;
    private List<OwnerSayCreamPageData.DataBean> datas;
    public PresenterOwnerSayPageCreamDataImpl(ViewOwnerSayPageCreamData view) {
        modle = new ModleOwnerSayPageCreamDataImpl();
        this.view = view;
    }

    @Override
    public void loadOwnerSayPageCreamData() {
        page++;
        modle.downLoadOwnerSayPageCreamData(page,this);
    }

    @Override
    public void OwnerSayPageCreamDataSuccess(List<OwnerSayCreamPageData.DataBean> datas) {
        if (datas != null) {
            view.onShowOwnerSayPageCreamDataSuccess(datas);
        } else {
            OwnerSayPageCreamDataFailed("检查网络");
        }
    }

    @Override
    public void OwnerSayPageCreamDataFailed(String info) {
        page--;
        Log.d("PresenterOwnerSayPageCr", info);
        view.onShowOwnerSayPageCreamDataFailed(info);
    }
}
