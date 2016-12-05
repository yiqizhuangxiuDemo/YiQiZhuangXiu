package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class EffectViewPagerPopuwindowAdatper extends PagerAdapter {
    private List<EffectBeatifulData.DataBean.ListBean> listBeen;
    private List<View> views;

    private  LayoutInflater inflater;
    public EffectViewPagerPopuwindowAdatper(Context context) {
        this.listBeen = new ArrayList<>();
        views = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<EffectBeatifulData.DataBean.ListBean> listBeen) {
        for (int i = 0; i < listBeen.size(); i++) {
            views.add(null);
        }
        this.listBeen.addAll(listBeen);
        notifyDataSetChanged();
    }

    public EffectBeatifulData.DataBean.ListBean getItemData(int position) {
        return listBeen.get(position);
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    ViewHolder holder=null;
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        if (views.get(position) == null){
//            View view = inflater.inflate(R.layout.school_viewpager_itme_cream, null);
//                holder = new ViewHolder(view);
//            EffectBeatifulData.DataBean.ListBean bean = listBeen.get(position);
//            holder.schoolViewpagerItme.setImageURI(Uri.parse(bean.getUrl()));
//            Log.d("EffectViewPagerPopuwind", "11111111"+bean.getUrl());
//            holder.textTitlePop.setText(bean.getTitle());
//            holder.textLittleTitelPop.setText("#"+bean.getTag().get(0).getName()+"#"+bean.getTag().get(1).getName()+"#"+bean.getTag().get(2).getName());
//            views.set(position,view);
//            container.addView(view);
//            return view;
//        }
//        container.addView(views.get(position));
//        return views.get(position);

        if (views.get(position) == null){
            View view = inflater.inflate(R.layout.school_viewpager_itme_cream, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
            views.set(position,view);

        }else {
            holder = (ViewHolder) views.get(position).getTag();
        }
        EffectBeatifulData.DataBean.ListBean bean = listBeen.get(position);
        holder.schoolViewpagerItme.setImageURI(Uri.parse(bean.getUrl()));
        Log.d("EffectViewPagerPopuwind", "11111111"+bean.getUrl());
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    static class ViewHolder {
        @Bind(R.id.school_viewpager_itme)
        SimpleDraweeView schoolViewpagerItme;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
