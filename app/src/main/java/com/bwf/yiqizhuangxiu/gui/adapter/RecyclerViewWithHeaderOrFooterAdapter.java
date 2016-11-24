package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public abstract class RecyclerViewWithHeaderOrFooterAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int TYPE_CONTENT = 0;
    public final static int TYPE_FOOTER = 1;
    public final static int TYPE_HEADER = 2;

    private List<T> datas;
    protected LayoutInflater inflater;
    private Context context;

    public RecyclerViewWithHeaderOrFooterAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 向Adapter添加数据
     *
     * @param datas
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * Adapter刷新数据
     *
     * @param datas
     */
    public void refreshDatas(List<T> datas) {
        this.datas.clear();
        addDatas(datas);
    }

    @Override
    public int getItemCount() {
        return datas.size() + getHeaderCount() + getFooterCount();
    }

    protected abstract int getFooterCount();

    protected abstract int getHeaderCount();

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderCount()) {
            return TYPE_HEADER;
        }
        if (position >= getItemCount() - getFooterCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    /**
     * 获取指定位置的数据
     *
     * @param position
     * @return
     */
    public T getItemData(int position) {
        return datas.get(position - getHeaderCount());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition() - getHeaderCount());
            }
        }
    }
}
