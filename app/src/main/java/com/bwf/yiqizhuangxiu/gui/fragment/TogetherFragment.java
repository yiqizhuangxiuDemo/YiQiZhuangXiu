package com.bwf.yiqizhuangxiu.gui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;
import com.bwf.yiqizhuangxiu.gui.activity.TogetherPeople;
import com.bwf.yiqizhuangxiu.gui.adapter.CompanyGridAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.CompanyToagetherPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.CompanyTogetherPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.CompanyTogetherView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class TogetherFragment extends BaseFragment implements CompanyTogetherView {
    @Bind(R.id.togeter_gridview)
    GridView togeterGridview;
    private int designTag;
    private String urlTag;
    private CompanyToagetherPresenter presenter;
    private CompanyGridAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.together_fragment;
    }

    public static TogetherFragment newInstance(int designTag, String urlTag) {
        TogetherFragment fragment = new TogetherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("designTag", designTag);
        bundle.putString("urlTag", urlTag);
        fragment.setArguments(bundle);
        return fragment;
    }
    private  boolean isLoading;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Bundle bundle = getArguments();
        designTag = bundle.getInt("designTag");
        urlTag = bundle.getString("urlTag");
        presenter = new CompanyTogetherPresenterImpl(this, designTag);
        adapter = new CompanyGridAdapter(getContext());
        togeterGridview.setAdapter(adapter);
        presenter.loadData();
        isLoading=true;
        togeterGridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(!isLoading &&firstVisibleItem + visibleItemCount == totalItemCount -1){
//                    isLoading = true;
//                    presenter.loadData();
////                }
//                if (!isLoading && togeterGridview.getLastVisiblePosition()>=togeterGridview.getChildCount()-2){
//                    LogUtils.d("isLoading",isLoading+"");
//                    isLoading=true;
//                    presenter.loadData();
//                }
            }
        });

        togeterGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyTogtherData.DataBean bean = adapter.getItem(position);
                Intent intent = new Intent(getContext(), TogetherPeople.class);
                intent.putExtra("avatar",bean.getAvatar());
                intent.putExtra("vendorName",bean.getVendorName());
                intent.putExtra("caseNumber",bean.getCaseNumber());
                intent.putExtra("commentCount",bean.getCommentCount());
                startActivity(intent);

            }
        });
    }

    @Override
    public void showCompanyTogetherData(List<CompanyTogtherData.DataBean> dataBeen) {
        adapter.addData(dataBeen);
        isLoading =false;
    }

    @Override
    public void showCompanyTogetherFial() {
        isLoading =false;
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
