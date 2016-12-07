package com.bwf.yiqizhuangxiu.gui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.SchoolPopupwindowGridViewAdatper;
import com.bwf.yiqizhuangxiu.gui.adapter.SchoolTabLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class SchoolActivity extends BaseActivity {
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.effcet_title_tablayout)
    TabLayout schoolTabLayout;
    @Bind(R.id.image_school_bar)
    Button imageSchoolBar;
    @Bind(R.id.view_pager_school)
    ViewPager viewPagerSchool;
    SchoolTabLayoutAdapter tabLayoutAdapter;
    @Bind(R.id.linear_parent)
    LinearLayout linearParent;
    private PopupWindow popupWindow;
    private List<SchoolPopupwindowGridViewAdatper> adatpers;
    private List<String> dataBefore;
    private List<String> dataIng;
    private List<String> dataFish;
    private String[] before = {"验房收房", "装修公司", "量房设计", "辅材选购", "主材选购", "家具选购", "装修合同"};
    private String[] ing = {"主体拆迁", "水电改造", "防水处理", "木工工程", "瓦工工程", "油工工程"};
    private String[] fish = {"主材安装", "竣工验收", "软装配饰", "居家生活"};

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        intidata();
        schoolTabLayout.setupWithViewPager(viewPagerSchool);
        tabLayoutAdapter = new SchoolTabLayoutAdapter(getSupportFragmentManager(), this);
        viewPagerSchool.setAdapter(tabLayoutAdapter);
        viewPagerSchool.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setPopupwindowFlag(position);
            }
        });
        viewPagerSchool.setOffscreenPageLimit(20);
        initPopWindow();
        setPopupwindowFlag(0);
    }

    private void setPopupwindowFlag(int position) {
        if (position < dataBefore.size()) {
            adatpers.get(0).setFlag(position);
            adatpers.get(1).setFlag(-1);
            adatpers.get(2).setFlag(-1);
        } else if (position < dataBefore.size() + dataIng.size()) {
            adatpers.get(0).setFlag(-1);
            adatpers.get(1).setFlag(position % dataBefore.size());
            adatpers.get(2).setFlag(-1);
        } else {
            adatpers.get(0).setFlag(-1);
            adatpers.get(1).setFlag(-1);
            adatpers.get(2).setFlag(position % (dataBefore.size() + dataIng.size()));
        }
    }

    private SchoolViewHolder viewHolder;

    private void initPopWindow() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.school_titel_popwindow, null);
            viewHolder = new SchoolViewHolder(view);
            popupWindow = new PopupWindow(this);
            popupWindow.setContentView(view);
            popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return false;
                }
            });
            popupWindow.setAnimationStyle(R.style.popupwindow_school);
//            ColorDrawable colorDrawable = new ColorDrawable(0xff000000);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            adatpers = new ArrayList<>();
            adatpers.add(new SchoolPopupwindowGridViewAdatper(this));
            adatpers.add(new SchoolPopupwindowGridViewAdatper(this));
            adatpers.add(new SchoolPopupwindowGridViewAdatper(this));
            viewHolder.schoolGridviewBefore.setAdapter(adatpers.get(0));
            viewHolder.schoolGridviewIng.setAdapter(adatpers.get(1));
            viewHolder.schoolGridviewFish.setAdapter(adatpers.get(2));
            adatpers.get(0).setDatas(dataBefore);
            adatpers.get(1).setDatas(dataIng);
            adatpers.get(2).setDatas(dataFish);
            popupWindowGreidviewListener();


        }
    }

    private Handler handler = new Handler(Looper.myLooper()) {
    };

    private void popupWindowGreidviewListener() {
        viewHolder.schoolGridviewBefore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (viewPagerSchool.getCurrentItem() != position) {

                    setPopupwindowFlag(position);
                    adatpers.get(0).notifyDataSetChanged();
                    adatpers.get(1).notifyDataSetChanged();
                    adatpers.get(2).notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                                viewPagerSchool.setCurrentItem(position);
                            }
                        }
                    }, 100);
                }else {
                    popupWindow.dismiss();
                }
            }
        });
        viewHolder.schoolGridviewIng.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (viewPagerSchool.getCurrentItem() != position+dataBefore.size()) {

                    setPopupwindowFlag(position+dataBefore.size());
                    adatpers.get(0).notifyDataSetChanged();
                    adatpers.get(1).notifyDataSetChanged();
                    adatpers.get(2).notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                                viewPagerSchool.setCurrentItem(position+dataBefore.size());
                            }
                        }
                    }, 100);
                }else {
                    popupWindow.dismiss();
                }
            }
        });
        viewHolder.schoolGridviewFish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (viewPagerSchool.getCurrentItem() != position+dataBefore.size()+dataIng.size()) {

                    setPopupwindowFlag(position+dataBefore.size()+dataIng.size());
                    adatpers.get(0).notifyDataSetChanged();
                    adatpers.get(1).notifyDataSetChanged();
                    adatpers.get(2).notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                                viewPagerSchool.setCurrentItem(position+dataBefore.size()+dataIng.size());
                            }
                        }
                    }, 100);
                }else {
                    popupWindow.dismiss();
                }
            }
        });

    }


    public void intidata() {
        dataBefore = new ArrayList<>();
        dataIng = new ArrayList<>();
        dataFish = new ArrayList<>();
        for (int i = 0; i < before.length; i++) {
            dataBefore.add(before[i]);
        }
        for (int i = 0; i < ing.length; i++) {
            dataIng.add(ing[i]);
        }
        for (int i = 0; i < fish.length; i++) {
            dataFish.add(fish[i]);
        }
    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.titlebar_back, R.id.image_school_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.image_school_bar:
                //TODO 弹popupwindow
                adatpers.get(0).notifyDataSetChanged();
                adatpers.get(1).notifyDataSetChanged();
                adatpers.get(2).notifyDataSetChanged();
                popupWindow.showAtLocation(linearParent, Gravity.TOP, 0, 0);
                break;
        }
    }

    static class SchoolViewHolder {
        @Bind(R.id.image_popupwindow_back)
        ImageView imagePopupwindowBack;
        @Bind(R.id.school_gridview_before)
        GridView schoolGridviewBefore;
        @Bind(R.id.school_gridview_ing)
        GridView schoolGridviewIng;
        @Bind(R.id.school_gridview_fish)
        GridView schoolGridviewFish;

        SchoolViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
