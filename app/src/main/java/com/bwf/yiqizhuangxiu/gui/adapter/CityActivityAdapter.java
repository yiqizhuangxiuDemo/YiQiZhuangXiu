package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CityActivityData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class CityActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<CityActivityData.DataBean.ForumlistBean> datas;
    private LayoutInflater inflater;

    public CityActivityAdapter(Context context, List<CityActivityData.DataBean.ForumlistBean> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClickListener(v, (CityActivityData.DataBean.ForumlistBean) v.getTag());
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClickListener(View view,CityActivityData.DataBean.ForumlistBean datas);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_city_activity, parent, false);
        view.setOnClickListener(this);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        CityActivityData.DataBean.ForumlistBean bean = datas.get(position);
        myViewHolder.itemView.setTag(bean);
        Log.d("CityActivityAdapter", bean.getAttachments().toString());
        myViewHolder.cityActivityPic.setImageURI(Uri.parse(bean.getAttachments()));
        //myViewHolder.cityActivityNoticePic.setImageResource();
        //myViewHolder.cityActivityNoticeText.setText();
        if (bean.getLastpost() != null) {
            myViewHolder.activityEndTime.setText("报名截止日期:" + bean.getLastpost());
        } else {
            myViewHolder.activityEndTime.setText("报名截止日期:");

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MyViewHolder extends  RecyclerView.ViewHolder {
        @Bind(R.id.city_activity_pic)
        ImageView cityActivityPic;
        @Bind(R.id.city_activity_notice_pic)
        ImageView cityActivityNoticePic;
        @Bind(R.id.city_activity_notice_text)
        TextView cityActivityNoticeText;
        @Bind(R.id.activity_end_time)
        TextView activityEndTime;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
