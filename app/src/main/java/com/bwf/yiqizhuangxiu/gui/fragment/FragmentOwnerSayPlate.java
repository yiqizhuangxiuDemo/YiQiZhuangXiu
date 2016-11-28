package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPagePlateData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterOwnerSayPagePlateDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPagePlateData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class FragmentOwnerSayPlate extends BaseFragment implements ViewOwnerSayPagePlateData {
    private PresenterOwnerSayPagePlateData presenterOwnerSayPagePlateData;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_ownersay_viewpager_plate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        presenterOwnerSayPagePlateData = new PresenterOwnerSayPagePlateDataImpl(this);
        presenterOwnerSayPagePlateData.loadPresenterOwnerSayPagePlateData();
    }

    @Override
    public void onShowOwnerSayPagePlateDataSuccess(List<OwnerSayPlatePageData.DataBean> datas) {
        Log.d("FragmentOwnerSayPlate", "datas:" + datas.toString());
    }

    @Override
    public void onShowOwnerSayPagePlateDataFailed(String info) {
        Log.d("FragmentOwnerSayPlate", info);
    }
}
