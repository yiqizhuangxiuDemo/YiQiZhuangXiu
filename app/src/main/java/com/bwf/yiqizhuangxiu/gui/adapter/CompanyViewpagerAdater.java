package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CompanyAdvertingData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyViewpagerAdater extends PagerAdapter{
    private Context context;
    private List<CompanyAdvertingData.DataBean> advertingData;
    private List<View> views;

    public CompanyViewpagerAdater(Context context,List<CompanyAdvertingData.DataBean> advertingData ) {
        this.advertingData = advertingData;
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        views = new ArrayList<>();
        for (int i = 0; i < advertingData.size(); i++) {
            View view = inflater.inflate(R.layout.company_pager_imag, null);
            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return advertingData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position % advertingData.size());
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.viewpager_image);
        Uri uri =Uri.parse(advertingData.get(position%advertingData.size()).getImagesrc2());
        simpleDraweeView.setImageURI(uri);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position % advertingData.size());
        container.removeView(view);
    }
}
