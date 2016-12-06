package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.gui.custom.MyGridView;
import com.bwf.yiqizhuangxiu.utils.indicator.TimestampUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class ProgressAdatper extends RecyclerViewWithHeaderOrFooterAdapter<WorkPlayProgressData.DataBean> {
    public ProgressAdatper(Context context) {
        super(context);
    }

    private ProgressGridAdapter adapter;

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
        mholder.textName.setText(data.getCreatorName() + "(" + data.getCreatorRole() + ")");
        mholder.textCommnet.setText(data.getMessage());
        if (null != data.getAvatar()){
            mholder.progressImageAnthor.setImageURI(Uri.parse(data.getAvatar()));
        }
        Log.d("ProgressAdatper", data.getCreateTime());
        mholder.textCreatTime.setText(TimestampUtils.miilisecondToTimes(data.getCreateTime() + ""));
        String imgSrc = data.getImgSrc();
        if (imgSrc != null && 0 != imgSrc.length()) {
            String[] strings = imgSrc.split(",");
            adapter = new ProgressGridAdapter(getContext(), strings);
            mholder.progressGridview.setAdapter(adapter);
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
            mholder.progressGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    popupWindow = new PopupWindow();
                    View view1 = inflater.inflate(R.layout.popup_progress, null);
                    popHolder = new PopupViewHolder(view1);
                    popupWindow.setContentView(view1);
                    popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
                    popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
                    popupWindow.setFocusable(true);
                    popupWindow.setTouchable(true);
                    ColorDrawable drawable = new ColorDrawable(0xff000000);
                    popupWindow.setBackgroundDrawable(drawable);
                    popupWindow.setAnimationStyle(R.style.popupwindow_anim_scale);
                    pagerAdapter = new PopupwindProgressViewPagerAdapter(getContext());
                    popHolder.popupwindowViewpager.setAdapter(pagerAdapter);
                    popHolder.popupwindowViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {
                            popHolder.textPopupwindowTotal.setText((position + 1) + "/" + list.size());
                        }
                    });
                    pagerAdapter.setDatas(list);
                    popHolder.textPopupwindowTotal.setText((position + 1) + "/" + list.size());
                    popHolder.popupwindowViewpager.setCurrentItem(position);
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    pagerAdapter.setOnViewOnClickListener(new PopupwindProgressViewPagerAdapter.OnViewOnClickListener() {
                        @Override
                        public void viewClicek() {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    });


                }
            });
        }


    }

    private PopupViewHolder popHolder;
    private PopupWindow popupWindow;
    private PopupwindProgressViewPagerAdapter pagerAdapter;
    private List<String> list;


    static class ViewHolder extends RecyclerView.ViewHolder {
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

    class PopupViewHolder {
        @Bind(R.id.popupwindow_viewpager)
        ViewPager popupwindowViewpager;
        @Bind(R.id.text_popupwindow_total)
        TextView textPopupwindowTotal;

        PopupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
