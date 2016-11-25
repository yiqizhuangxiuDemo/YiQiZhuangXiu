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
import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.bwf.yiqizhuangxiu.gui.adapter.FragmentOwnerSayCreamAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterOwnerSayPageCreamData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterOwnerSayPageCreamDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewOwnerSayPageCreamData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/23.
 */

public class FragmentOwnerSayCream extends BaseFragment implements ViewOwnerSayPageCreamData {
    private PresenterOwnerSayPageCreamData presenterOwnerSayPageCreamData;
    @Bind(R.id.fragment_ownersay_viewpager_cream_recyclerview)
    RecyclerView fragmentOwnersayViewpagerCreamRecyclerview;

    public void initDatas() {
        presenterOwnerSayPageCreamData = new PresenterOwnerSayPageCreamDataImpl(this);
        presenterOwnerSayPageCreamData.loadOwnerSayPageCreamData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_ownersay_viewpager_cream;
    }

    @Override
    public void onShowOwnerSayPageCreamDataFailed(String info) {
        Log.d("FragmentOwnerSayCream", info);
    }

    @Override
    public void onShowOwnerSayPageCreamDataSuccess(List<OwnerSayCreamPageData.DataBean> datas) {
        Log.d("FragmentOwnerSayCream", "datas:" + datas.toString());
        FragmentOwnerSayCreamAdapter adapter = new FragmentOwnerSayCreamAdapter(LayoutInflater.from(getActivity()), getActivity(), datas);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentOwnersayViewpagerCreamRecyclerview.setLayoutManager(manager);
        fragmentOwnersayViewpagerCreamRecyclerview.setAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
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
