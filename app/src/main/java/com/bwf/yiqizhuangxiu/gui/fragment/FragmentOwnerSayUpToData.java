package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPageUpToData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterOwnerSayPageUpToDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPageUpToData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class FragmentOwnerSayUpToData extends BaseFragment implements ViewOwnerSayPageUpToData{
    private PresenterOwnerSayPageUpToData presenterOwnerSayPageUpToData;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        presenterOwnerSayPageUpToData = new PresenterOwnerSayPageUpToDataImpl(this);
        presenterOwnerSayPageUpToData.loadOwnerSayPageUpToData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_ownersay_viewpager_uptodata;
    }

    @Override
    public void onShowOwnerSayPageUpToDataFailed(String info) {
        Log.d("FragmentOwnerSayUpToDat", info);
    }

    @Override
    public void onShowOwnerSayPageUpToDataSuccess(List<OwnerSayUpToDataPageData.DataBean> datas) {

    }
}
