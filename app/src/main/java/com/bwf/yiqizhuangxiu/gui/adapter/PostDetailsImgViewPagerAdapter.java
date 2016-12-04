package com.bwf.yiqizhuangxiu.gui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.activity.BaseActivity;
import com.bwf.yiqizhuangxiu.utils.FrescoImageUtils;

import java.util.ArrayList;
import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by Administrator on 2016/11/30.
 */

public class PostDetailsImgViewPagerAdapter extends PagerAdapter {

    private List<String> imgUrls;
    private LayoutInflater inflater;
    private BaseActivity context;
    private List<View> views;

    public PostDetailsImgViewPagerAdapter(List<String> imgUrls, BaseActivity context) {
        this.imgUrls = imgUrls;
        this.inflater = LayoutInflater.from(context);
        views = new ArrayList<>();
        for (String imgurl : imgUrls) {
            View view = inflater.inflate(R.layout.img_popupwindow_postdetails_item, null);
            PhotoDraweeView pdv = (PhotoDraweeView) view.findViewById(R.id.simpleDraweeView);
            FrescoImageUtils.setZoomableControllerListener(pdv, imgurl, context.getScreenWidth(context), context.getScreenHeight(context));
            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
