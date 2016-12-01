package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;
import com.bwf.yiqizhuangxiu.gui.adapter.SchoolComentListAdatper;
import com.bwf.yiqizhuangxiu.mvp.presenter.SchoolCommentPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.SchoolCommentPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.SchoolComentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class SchoolFragmentContent extends BaseFragment implements SchoolComentView {
    @Bind(R.id.school_listview_content)
    ListView schoolListviewContent;
    private int tag;
    private SchoolCommentPresenter commentPresenter;
    private SchoolComentListAdatper adatper;


    public static SchoolFragmentContent newInsatance(int tag){
        SchoolFragmentContent fragment= new SchoolFragmentContent();
        Bundle bundle = new Bundle();
        bundle.putInt("tag",tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.school_content_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        tag = bundle.getInt("tag");
        commentPresenter = new SchoolCommentPresenterImpl(this);
        commentPresenter.loadData(tag);
        isLoading = true;
        adatper = new SchoolComentListAdatper(getContext());
        schoolListviewContent.addFooterView(LayoutInflater.from(getContext()).inflate(R.layout.footer_loading_data,null));
        schoolListviewContent.setAdapter(adatper);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());

        schoolListviewContent.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ( !isLoading && manager.findLastVisibleItemPosition() == manager.getItemCount() -2){
                    commentPresenter.loadData(tag);
                    isLoading = true;
                }
            }
        });
    }
    private boolean isLoading;

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
    public void showSchoolComment(List<SchoolConmentData.DataBean.ListBean> listBeen) {
        adatper.addData(listBeen);
        isLoading = false;
    }

    @Override
    public void showSchoolComentFail() {
        isLoading = false;
    }
}
