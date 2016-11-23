package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class HomepageViewPagerAdapter extends PagerAdapter {
    private List<HomepageHeadData.DataBean> datas;
    private List<View> views;

    public HomepageViewPagerAdapter(Context context, List<HomepageHeadData.DataBean> datas) {
        this.datas = datas;
        views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < datas.size(); i++) {
            View view = inflater.inflate(R.layout.fragmen_homepage_viewpager_item, null);
            views.add(view);
        }
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
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = views.get(position);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(
                R.id.simpleDraweeView_homepage_viewpager_item);
        simpleDraweeView.setImageURI(Uri.parse(datas.get(position).getBanner_url()));
        simpleDraweeView.setTag(datas.get(position).getImagesrc2());
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    //Item监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
