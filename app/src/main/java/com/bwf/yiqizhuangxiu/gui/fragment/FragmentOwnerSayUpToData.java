package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.bwf.yiqizhuangxiu.gui.adapter.FragmentOwnerSayUpToDataAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPageUpToData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterOwnerSayPageUpToDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPageUpToData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class FragmentOwnerSayUpToData extends BaseFragment implements ViewOwnerSayPageUpToData {
    @Bind(R.id.fragment_ownersay_uptodata_recyclerview)
    RecyclerView fragmentOwnersayUptodataRecyclerview;
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
        FragmentOwnerSayUpToDataAdapter adapter = new FragmentOwnerSayUpToDataAdapter(getActivity(), datas);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentOwnersayUptodataRecyclerview.setLayoutManager(manager);
        fragmentOwnersayUptodataRecyclerview.setAdapter(adapter);

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
