package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.gui.adapter.ProgressAdatper;
import com.bwf.yiqizhuangxiu.gui.custom.CustomLayoutManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayProgressFragment extends BaseFragment {
    private WorkPlayProgressData playProgressData;
    public static final String TAG = "WorkPlayProgressData";
    private LinearLayoutManager manager;
    private ProgressAdatper progressAdatper;

    public static WorkPlayProgressFragment newInstance(WorkPlayProgressData playProgressData){
        WorkPlayProgressFragment progressFrogress = new WorkPlayProgressFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG,playProgressData);
        progressFrogress.setArguments(bundle);
        return  progressFrogress;
    }

    @Bind(R.id.progress_work_recycleview)
    RecyclerView progressWorkRecycleview;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_work_progress;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        WorkPlayProgressData data = (WorkPlayProgressData) bundle.getSerializable(TAG);
        List<WorkPlayProgressData.DataBean> data1 = data.getData();
        manager = new CustomLayoutManager(getContext());
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        progressWorkRecycleview.setHasFixedSize(true);
        progressWorkRecycleview.setNestedScrollingEnabled(false);
        progressWorkRecycleview.setLayoutManager(manager);
        progressAdatper = new ProgressAdatper(getContext());
        progressWorkRecycleview.setAdapter(progressAdatper);
        progressAdatper.addDatas(data1);


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
