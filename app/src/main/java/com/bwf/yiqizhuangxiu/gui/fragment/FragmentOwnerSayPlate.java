package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;
import com.bwf.yiqizhuangxiu.gui.adapter.FragmentOwnerSayPlateAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPagePlateData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterOwnerSayPagePlateDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPagePlateData;
import com.bwf.yiqizhuangxiu.utils.RecycleViewDivider;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class FragmentOwnerSayPlate extends BaseFragment implements ViewOwnerSayPagePlateData {
    @Bind(R.id.fragment_ownersay_plate_recyclerview)
    RecyclerView fragmentOwnersayPlateRecyclerview;
    @Bind(R.id.fragment_ownersay_plate_recyclerview2)
    RecyclerView fragmentOwnersayPlateRecyclerview2;
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
    public void onShowOwnerSayPagePlateDataSuccess(List<List<OwnerSayPlatePageData.DataBean>> datas) {
        Log.d("FragmentOwnerSayPlate", "datas:" + datas.toString());
        List<OwnerSayPlatePageData.DataBean> dataBeenOne = datas.get(0);
        List<OwnerSayPlatePageData.DataBean> dataBeenTwo = datas.get(1);
        FragmentOwnerSayPlateAdapter adapterOne = new FragmentOwnerSayPlateAdapter(getActivity(), dataBeenOne);
        FragmentOwnerSayPlateAdapter adapterTwo = new FragmentOwnerSayPlateAdapter(getActivity(), dataBeenTwo);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 2);
        fragmentOwnersayPlateRecyclerview.setLayoutManager(manager);
        fragmentOwnersayPlateRecyclerview2.setLayoutManager(manager2);
        fragmentOwnersayPlateRecyclerview2.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL,R.drawable.recycleview_divider_h));
        fragmentOwnersayPlateRecyclerview.setAdapter(adapterOne);
        fragmentOwnersayPlateRecyclerview2.setAdapter(adapterTwo);

    }

    @Override
    public void onShowOwnerSayPagePlateDataFailed(String info) {
        Log.d("FragmentOwnerSayPlate", info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
