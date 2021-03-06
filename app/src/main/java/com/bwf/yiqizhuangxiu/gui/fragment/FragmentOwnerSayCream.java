package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.bwf.yiqizhuangxiu.gui.activity.LoginActivity;
import com.bwf.yiqizhuangxiu.gui.activity.OwnerSaySubActivity;
import com.bwf.yiqizhuangxiu.gui.activity.PopWindowUtils;
import com.bwf.yiqizhuangxiu.gui.activity.PostDetailsActivity;
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

public class FragmentOwnerSayCream extends BaseFragment implements ViewOwnerSayPageCreamData{
    @Bind(R.id.root)
    LinearLayout root;
    private  PopWindowUtils popWindowUtils;
    private PresenterOwnerSayPageCreamData presenterOwnerSayPageCreamData;
    private Handler handler = new Handler();
    private FragmentOwnerSayCreamAdapter adapter;
    @Bind(R.id.fragment_ownersay_viewpager_cream_recyclerview)
    RecyclerView fragmentOwnersayViewpagerCreamRecyclerview;

    public void initDatas() {
        presenterOwnerSayPageCreamData = new PresenterOwnerSayPageCreamDataImpl(this);
        presenterOwnerSayPageCreamData.loadOwnerSayPageCreamData();
        adapter = new FragmentOwnerSayCreamAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentOwnersayViewpagerCreamRecyclerview.setLayoutManager(manager);
        fragmentOwnersayViewpagerCreamRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new FragmentOwnerSayCreamAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemclick(View view, OwnerSayCreamPageData.DataBean Datas) {
                switch (view.getId()) {
                    case R.id.item_ownersaypagecream:
                        String tid = Datas.getTid();
                        Intent intent1 = new Intent(getActivity(), PostDetailsActivity.class);
                        intent1.putExtra(PostDetailsActivity.TAG_ID_EXTRA, tid);
                        startActivity(intent1);
                        break;
                    case R.id.ownersaypagecream_title_img:
                        String author = Datas.getAuthor();
                        String avtUrl = Datas.getAvtUrl();
                        Intent intent2 = new Intent(getActivity(), OwnerSaySubActivity.class);
                        intent2.putExtra("author", author);
                        intent2.putExtra("avtUrl", avtUrl);
                        startActivity(intent2);
                        break;
                    case R.id.item_ownersaypagecream_bottom_zan_img:
                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_ownersaypagecream_bottom_comment_img:
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_ownersaypagecream_bottom_share:
                        if (popWindowUtils == null) {
                            popWindowUtils = new PopWindowUtils(getActivity(),root);
                        }
                        popWindowUtils.showPopWindow();
                        break;
                }
            }
        });
        adapter.setLLoadMoreCallBack(new FragmentOwnerSayCreamAdapter.LoadMoreCallBack() {
            @Override
            public void loadMoreData() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenterOwnerSayPageCreamData.loadOwnerSayPageCreamData();
                    }
                }, 2000);
            }
        });
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
    public void onShowOwnerSayPageCreamDataSuccess(final List<OwnerSayCreamPageData.DataBean> datas) {
        adapter.addData(datas);
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
