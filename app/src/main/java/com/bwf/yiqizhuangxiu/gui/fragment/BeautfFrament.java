package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.bwf.yiqizhuangxiu.gui.adapter.EffectBeatifulAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.popwindowGridViewAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.EffectBeatifulPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.EffectBeatifulPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.EffectBeatifulView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class BeautfFrament extends BaseFragment implements EffectBeatifulView {
    @Bind(R.id.checked_textview_space)
    CheckedTextView checkedTextviewSpace;
    @Bind(R.id.checked_textview_style)
    CheckedTextView checkedTextviewStyle;
    @Bind(R.id.checked_textview_local)
    CheckedTextView checkedTextviewLocal;
    @Bind(R.id.checked_textview_color)
    CheckedTextView checkedTextviewColor;
    @Bind(R.id.linear_beautiful_container)
    LinearLayout linearBeautifulContainer;
    @Bind(R.id.relativie_space)
    RelativeLayout relativieSpace;
    @Bind(R.id.relativie_style)
    RelativeLayout relativieStyle;
    @Bind(R.id.relativie_local)
    RelativeLayout relativieLocal;
    @Bind(R.id.relativie_color)
    RelativeLayout relativieColor;
    @Bind(R.id.beautiful_grid_view)
    GridView beautifulGridView;
    private EffectBeatifulAdapter beatifulAdapter;
    private EffectBeatifulPresenter beatifulPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_beautf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        beatifulAdapter = new EffectBeatifulAdapter(getContext());
        beautifulGridView.setAdapter(beatifulAdapter);
        beatifulPresenter = new EffectBeatifulPresenterImpl(this);
        beatifulPresenter.loadData();

    }

    private WindowManager manager;
    private View view;
    private WindowManager.LayoutParams layoutParams;
    private boolean isShowpopwind;
    private popwindowGridViewAdapter adapter;
    private PopwindowViewHolder holder;

    public void showPopupWindow() {
        if (manager == null) {
            manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            view = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_space_beautiful, null);
            holder = new PopwindowViewHolder(view);
            layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.format = PixelFormat.RGBA_8888;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    isShowpopwind = true;
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    isShowpopwind = false;
                }
            });
        }
        manager.addView(view, layoutParams);
        adapter = new popwindowGridViewAdapter(getContext());
        holder.recyclerviewPopwindow.setAdapter(adapter);
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

    @OnClick({R.id.relativie_space, R.id.relativie_style, R.id.relativie_local, R.id.relativie_color})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativie_space:
                checkedTextviewSpace.setChecked(true);
                showPopupWindow();
                break;
            case R.id.relativie_style:
                checkedTextviewStyle.setChecked(true);
                break;
            case R.id.relativie_local:
                checkedTextviewLocal.setChecked(true);
                break;
            case R.id.relativie_color:
                checkedTextviewColor.setChecked(true);
                break;
        }
    }

    @Override
    public void showEffectBeatifulSuccess(List<EffectBeatifulData.DataBean.ListBean> listBeen) {
        beatifulAdapter.addData(listBeen);
    }

    @Override
    public void showEffectBeatifulFail() {

    }

    static class PopwindowViewHolder {
        @Bind(R.id.recyclerview_popwindow)
        GridView recyclerviewPopwindow;

        PopwindowViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
