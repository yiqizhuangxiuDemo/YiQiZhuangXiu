package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.BookingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class BookingViewPagerAdapter extends PagerAdapter implements BookingRecyclerViewAdapter.OnItemClickListener {

    private List<BookingData.DataBeanA> datas;
    private LayoutInflater inflater;
    private List<View> views;

    public BookingViewPagerAdapter(List<BookingData.DataBeanA> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        views = new ArrayList<>();
        for (int i = 0; i < datas.size() + 1; i++) {
            View view = inflater.inflate(R.layout.booking_viewpager_item, null);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        if (recyclerView.getAdapter() == null) {
            if (position == 0) {
                List<BookingData.DataBeanA.DataBean> business = new ArrayList<>();
                for (BookingData.DataBeanA data : datas) {
                    business.addAll(data.getData());
                }
                BookingRecyclerViewAdapter adapter = new BookingRecyclerViewAdapter(business, inflater);
                adapter.setOnItemClickListener(this);
                recyclerView.setAdapter(adapter);
            } else {
                BookingRecyclerViewAdapter adapter = new BookingRecyclerViewAdapter(datas.get(position - 1).getData(), inflater);
                adapter.setOnItemClickListener(this);
                recyclerView.setAdapter(adapter);
            }
        }
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void onItemClick(View view, BookingData.DataBeanA.DataBean data) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onRecyclerViewItemClick(view, data);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(View view, BookingData.DataBeanA.DataBean data);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
