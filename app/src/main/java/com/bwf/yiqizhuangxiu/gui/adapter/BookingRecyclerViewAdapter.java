package com.bwf.yiqizhuangxiu.gui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.BookingData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/28.
 */

public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<BookingData.DataBeanA.DataBean> business;
    private LayoutInflater inflater;

    public BookingRecyclerViewAdapter(List<BookingData.DataBeanA.DataBean> business, LayoutInflater inflater) {
        this.business = business;
        this.inflater = inflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.booking_viewpager_recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.data = business.get(position);
        itemViewHolder.simpleDraweeView.setImageURI(Uri.parse(business.get(position).getLogo()));
        itemViewHolder.textview.setText(business.get(position).getBusiness_name());
        if ("0".equals(business.get(position).getIsPublish())) {
            itemViewHolder.scrim.setVisibility(View.VISIBLE);
            itemViewHolder.scrimText.setVisibility(View.VISIBLE);
        } else if ("1".equals(business.get(position).getIsPublish())) {
            itemViewHolder.scrim.setVisibility(View.GONE);
            itemViewHolder.scrimText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return business.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.simpleDraweeView)
        SimpleDraweeView simpleDraweeView;
        @Bind(R.id.scrim)
        View scrim;
        @Bind(R.id.scrim_text)
        TextView scrimText;
        @Bind(R.id.textview)
        TextView textview;

        BookingData.DataBeanA.DataBean data;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, data);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, BookingData.DataBeanA.DataBean data);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
