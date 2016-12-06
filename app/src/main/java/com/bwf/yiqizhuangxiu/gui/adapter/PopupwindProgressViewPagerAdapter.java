package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class PopupwindProgressViewPagerAdapter extends PagerAdapter{
    private List<View> views;
    private Context context;
    private List<String> datas;



    public PopupwindProgressViewPagerAdapter(Context context) {
        views = new ArrayList<>();
        this.context = context;
        datas = new ArrayList<>();

    }

    public void setDatas(List<String> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        views.clear();
        for (int i = 0; i < datas.size(); i++) {
            SimpleDraweeView view = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.popupwind_image_progress,null);
            view.setImageURI(Uri.parse(datas.get(i)));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( listener != null)
                        listener.viewClicek();
                }
            });
            views.add(view);
        }
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        container.removeView(view);
    }
    private  OnViewOnClickListener listener;

    public void setOnViewOnClickListener(OnViewOnClickListener listener) {
        this.listener = listener;
    }

    interface OnViewOnClickListener{
        void viewClicek();
    }
}
