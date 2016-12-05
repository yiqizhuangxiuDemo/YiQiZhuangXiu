package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.bwf.yiqizhuangxiu.gui.activity.LoginActivity;
import com.bwf.yiqizhuangxiu.gui.activity.OwnerSaySubActivity;
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

public class FragmentOwnerSayCream extends BaseFragment implements ViewOwnerSayPageCreamData, View.OnClickListener {
    @Bind(R.id.root)
    LinearLayout root;
    private PresenterOwnerSayPageCreamData presenterOwnerSayPageCreamData;
    private Handler handler = new Handler();
    private FragmentOwnerSayCreamAdapter adapter;
    //private List<OwnerSayCreamPageData.DataBean> mdatas = new ArrayList<>();
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
                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_ownersaypagecream_bottom_zan_img:
                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_ownersaypagecream_bottom_comment_img:
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_ownersaypagecream_bottom_share:
                        showPopWindow();
                        Toast.makeText(getActivity(), "5", Toast.LENGTH_SHORT).show();
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

    private PopupWindow pop;

    private void showPopWindow() {
        if (pop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_share_layout, null);
            ViewHolder holder = new ViewHolder(view);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (pop.isShowing()){
                        pop.dismiss();
                    }
                    return false;
                }
            });
            pop = new PopupWindow(getActivity());
            pop.setContentView(view);
            pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            holder.itemShareLayoutQq.setOnClickListener(this);
            holder.itemShareLayoutWechat.setOnClickListener(this);
            holder.itemShareLayoutWechatmoments.setOnClickListener(this);
            holder.itemShareLayoutQzone.setOnClickListener(this);
            holder.itemShareLayoutSinaweibo.setOnClickListener(this);
            holder.itemShareLayoutBtn.setOnClickListener(this);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            pop.setAnimationStyle(R.style.popupwindow_anim);
        }
        if (pop.isShowing()) {
            pop.dismiss();
        } else {
            pop.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        }
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
        Log.d("FragmentOwnerSayCream", "datas:" + datas.toString());
        // mdatas.addAll(datas);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_share_layout_qq:
                Toast.makeText(getActivity(), "QQ", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
            case R.id.item_share_layout_wechat:
                Toast.makeText(getActivity(), "微信", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
            case R.id.item_share_layout_wechatmoments:
                Toast.makeText(getActivity(), "朋友圈", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
            case R.id.item_share_layout_qzone:
                Toast.makeText(getActivity(), "QQ空间", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
            case R.id.item_share_layout_sinaweibo:
                Toast.makeText(getActivity(), "新浪微博", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
            case R.id.item_share_layout_btn:
                Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                break;
        }
    }

    static class ViewHolder {
        @Bind(R.id.item_share_layout_qq)
        LinearLayout itemShareLayoutQq;
        @Bind(R.id.item_share_layout_wechat)
        LinearLayout itemShareLayoutWechat;
        @Bind(R.id.item_share_layout_wechatmoments)
        LinearLayout itemShareLayoutWechatmoments;
        @Bind(R.id.item_share_layout_qzone)
        LinearLayout itemShareLayoutQzone;
        @Bind(R.id.item_share_layout_sinaweibo)
        LinearLayout itemShareLayoutSinaweibo;
        @Bind(R.id.item_share_layout_btn)
        TextView itemShareLayoutBtn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
