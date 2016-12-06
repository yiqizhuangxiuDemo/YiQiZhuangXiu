package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayPeopeRecycleAdapter extends RecyclerViewWithHeaderOrFooterAdapter<WorkPlayingPeopleAndProgressData.DataBean.ProgressBean> {
    public WorkPlayPeopeRecycleAdapter(Context context) {
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
        View view = inflater.inflate(R.layout.decotae_progress_item, parent, false);
        return new MyProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyProgressViewHolder mholder = (MyProgressViewHolder) holder;
        if (position != 0) {
            mholder.decortaeName.setText("未完成");
            mholder.decortaeName.setBackgroundColor(0xff5E5C5C);
            mholder.imgaeDecortae.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.not_finish_icon));
        }
        mholder.text_progress.setText(getItemData(position).getProgressName());

    }

     class MyProgressViewHolder extends ItemViewHolder{
        @Bind(R.id.decortae_name)
        TextView decortaeName;
        @Bind(R.id.imgae_decortae)
        ImageView imgaeDecortae;
         @Bind(R.id.text_progress)
         TextView text_progress;

        MyProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
