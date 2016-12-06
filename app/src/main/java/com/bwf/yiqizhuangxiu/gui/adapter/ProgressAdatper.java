package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.gui.custom.MyGridView;
import com.bwf.yiqizhuangxiu.utils.indicator.TimestampUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class ProgressAdatper extends RecyclerViewWithHeaderOrFooterAdapter<WorkPlayProgressData.DataBean> {
    public ProgressAdatper(Context context) {
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
        View view = inflater.inflate(R.layout.work_progress_recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mholder = (ViewHolder) holder;
        WorkPlayProgressData.DataBean data = getItemData(position);
//        mholder.progressImageAnthor.setImageURI(Uri.parse(data.get));
            mholder.textName.setText(data.getCreatorName()+"("+data.getCreatorRole()+")");
        mholder.textCommnet.setText(data.getMessage());
        mholder.textCreatTime.setText(TimestampUtils.miilisecondToTimes(data.getCreateTime()+""));

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.progress_image_anthor)
        SimpleDraweeView progressImageAnthor;
        @Bind(R.id.text_name)
        TextView textName;
        @Bind(R.id.text_commnet)
        TextView textCommnet;
        @Bind(R.id.progress_gridview)
        MyGridView progressGridview;
        @Bind(R.id.text_creat_time)
        TextView textCreatTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
