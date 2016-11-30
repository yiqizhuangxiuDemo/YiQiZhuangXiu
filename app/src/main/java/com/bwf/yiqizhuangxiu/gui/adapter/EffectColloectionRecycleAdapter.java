package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class EffectColloectionRecycleAdapter extends RecyclerViewWithHeaderOrFooterAdapter{

    public EffectColloectionRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getFooterCount() {
        return 0;
    }

    @Override
    protected int getHeaderCount() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
