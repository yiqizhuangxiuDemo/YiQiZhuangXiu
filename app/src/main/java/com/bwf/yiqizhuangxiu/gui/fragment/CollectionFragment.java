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
import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;
import com.bwf.yiqizhuangxiu.gui.adapter.EffectColloectionRecycleAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.RecyclerViewWithHeaderOrFooterAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.EffectColloectionPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.EffectColloectionPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.EffectCollectionView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class CollectionFragment extends BaseFragment implements EffectCollectionView, RecyclerViewWithHeaderOrFooterAdapter.OnItemClickListener {
    @Bind(R.id.effect_recycle_view)
    RecyclerView effectRecycleView;
    private EffectColloectionPresenter colloectionPresenter;
    private EffectColloectionRecycleAdapter colloectionRecycleAdapter;
    private GridLayoutManager manager;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_collection;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        colloectionRecycleAdapter = new EffectColloectionRecycleAdapter(getContext());
        //管理者
        manager = new GridLayoutManager(getContext(),1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        effectRecycleView.setLayoutManager(manager);
        //设置adapter
        effectRecycleView.setAdapter(colloectionRecycleAdapter);
        colloectionPresenter = new EffectColloectionPresenterImpl(this);
        colloectionPresenter.loadNextPage();
        isLoading =true;
        setRecycleScollListenter();
        colloectionRecycleAdapter.setOnItemClickListener(this);
    }
    public void setRecycleScollListenter(){
        effectRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (noMoreData)
                    return;
                if (!isLoading && manager.findLastVisibleItemPosition() == manager.getItemCount() -1){
                    colloectionPresenter.loadNextPage();
                }

            }
        });
    }
    private boolean noMoreData ;
    private  boolean isLoading;

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

    @Override
    public void showEffectionSuccess(List<EffectCollectionData.DataBean.ListBean> listBeen) {
        isLoading = false;
        Log.d("CollectionFragment", listBeen.get(0).getUrl());
        colloectionRecycleAdapter.addDatas(listBeen);

    }

    @Override
    public void showEffectionFail() {
        isLoading =false;
        Log.d("CollectionFragment", "EffectColloection数据获取失败");
    }

    @Override
    public void noMoreData() {
        noMoreData = true;
    }

    @Override
    public void onItemClick(View view, int position) {
        EffectCollectionData.DataBean.ListBean bean = colloectionRecycleAdapter.getItemData(position);
        
    }
}
