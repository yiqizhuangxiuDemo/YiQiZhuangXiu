package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.bwf.yiqizhuangxiu.gui.activity.ActivitiesActivity;
import com.bwf.yiqizhuangxiu.gui.activity.ArticleDetailsActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BookingActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BudgetActivity;
import com.bwf.yiqizhuangxiu.gui.activity.CompanyActivity;
import com.bwf.yiqizhuangxiu.gui.activity.DesignActivity;
import com.bwf.yiqizhuangxiu.gui.activity.DetailsPageActivity;
import com.bwf.yiqizhuangxiu.gui.activity.EffectActivity;
import com.bwf.yiqizhuangxiu.gui.activity.FitmentActivity;
import com.bwf.yiqizhuangxiu.gui.activity.PostDetailsActivity;
import com.bwf.yiqizhuangxiu.gui.activity.SchoolActivity;
import com.bwf.yiqizhuangxiu.gui.activity.SearchActivity;
import com.bwf.yiqizhuangxiu.gui.activity.ZxingActivity;
import com.bwf.yiqizhuangxiu.gui.adapter.HomepageRecyclerViewAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.HomepageViewPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.RecyclerViewWithHeaderOrFooterAdapter;
import com.bwf.yiqizhuangxiu.gui.custom.AutoScrollViewPager;
import com.bwf.yiqizhuangxiu.gui.custom.CustomRefreshLayout;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterHomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterHomepageHeadData;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterHomepageContentDataImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterHomepageHeadDataImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewHomepageContentData;
import com.bwf.yiqizhuangxiu.mvp.view.ViewHomepageHeadData;
import com.bwf.yiqizhuangxiu.utils.indicator.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomePageFragment extends BaseFragment implements ViewHomepageHeadData, ViewHomepageContentData {
    @Bind(R.id.viewpager_homepage_head)
    AutoScrollViewPager viewpagerHomepageHead;
    @Bind(R.id.viewpager_homepage_head_indicator)
    LinearLayout viewpagerHomepageHeadIndicator;
    @Bind(R.id.root_hemepage_titlbar)
    LinearLayout rootHemepageTitlbar;
    @Bind(R.id.appbarlayout_appbar_fragment_homepage)
    AppBarLayout appbarlayoutAppbarFragmentHomepage;
    @Bind(R.id.recyclerview_homepage)
    RecyclerView recyclerviewHomepage;
    @Bind(R.id.go_top_homepage)
    ImageView goTopHomepage;
    @Bind(R.id.custom_RefreshLayout)
    CustomRefreshLayout customRefreshLayout;
    @Bind(R.id.location_hpmepage_titlebar)
    TextView locationHpmepageTitlebar;

    public final static String SP_CONFIG = "config";
    public final static String SP_CONFIG_CITY_KEY = "location";

    private boolean isLoading;
    private boolean isRefreshing;
    private PresenterHomepageHeadData presenterHomepageHeadData;
    private PresenterHomepageContentData presenterHomepageContentData;
    private HomepageRecyclerViewAdapter adapter;
    private HomepageViewPagerAdapter headAdapter;
    private LinearLayoutManager layoutManager;
    private float titleHeight;
    private boolean isNoMoreData;
    private PopupWindow popupWindow;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_homepage_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        locationHpmepageTitlebar.setText(getContext().getSharedPreferences(SP_CONFIG
                , Context.MODE_PRIVATE).getString(SP_CONFIG_CITY_KEY, getString(R.string.location_citiname)));

        popupWindow = new PopupWindow(getContext());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rootHemepageTitlbar.setEnabled(true);
                locationHpmepageTitlebar.setText(getContext().getSharedPreferences("config"
                        , Context.MODE_PRIVATE).getString("location", getString(R.string.location_citiname)));
            }
        });

        //presenter  head
        presenterHomepageHeadData = new PresenterHomepageHeadDataImpl(this);
        presenterHomepageHeadData.loadData();

        //presenter content
        presenterHomepageContentData = new PresenterHomepageContentDataImpl(this);

        //recyclerview adapter
        adapter = new HomepageRecyclerViewAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewHomepage.setLayoutManager(layoutManager);
        recyclerviewHomepage.setAdapter(adapter);
        initListener();
        customRefreshLayout.startRefreshOnce();
    }

    private void initListener() {
        //加载更多
        recyclerviewHomepage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isNoMoreData) {
                    if (!isLoading && layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1) {
                        loadContentData();
                    }
                }

            }
        });

        //重新加载
        adapter.setOnloadMoreDataListener(new HomepageRecyclerViewAdapter.OnloadMoreDataListener() {
            @Override
            public void onLoadMoreData() {
                loadContentData();
            }
        });

        adapter.setOnItemClickListener(new RecyclerViewWithHeaderOrFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (adapter.getItemData(position).getType() == HomepageRecyclerViewAdapter.ARTICLE_TYPE) {
                    Intent intent = new Intent(HomePageFragment.this.getContext(), ArticleDetailsActivity.class);
                    intent.putExtra(ArticleDetailsActivity.TAG_URL_EXTRA, adapter.getItemData(position).getH5Url());
                    HomePageFragment.this.startActivity(intent);
                } else if (adapter.getItemData(position).getType() == HomepageRecyclerViewAdapter.POST_TYPE) {
                    Intent intent = new Intent(HomePageFragment.this.getContext(), PostDetailsActivity.class);
                    intent.putExtra(PostDetailsActivity.TAG_ID_EXTRA, adapter.getItemData(position).getId());
                    HomePageFragment.this.startActivity(intent);
                }
            }
        });

        //下拉刷新
        customRefreshLayout.setOnRefreshListener(new CustomRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isRefreshing = true;
                    isNoMoreData = false;
                    loadContentData();
                    presenterHomepageHeadData.loadData();
                }

            }
        });

        customRefreshLayout.setOnTouchByUserListener(new CustomRefreshLayout.OnTouchByUserListener() {
            @Override
            public void onTouchByUser() {
                if (rootHemepageTitlbar.getVisibility() == View.VISIBLE) {
                    rootHemepageTitlbar.setVisibility(View.GONE);
                }
            }
        });

        customRefreshLayout.setOnRecoverListener(new CustomRefreshLayout.OnRecoverListener() {
            @Override
            public void onRecover() {
                if (rootHemepageTitlbar.getVisibility() == View.GONE) {
                    rootHemepageTitlbar.setVisibility(View.VISIBLE);
                }
            }
        });

        //设置屏蔽/打开刷新使能的监听
        appbarlayoutAppbarFragmentHomepage.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    customRefreshLayout.setEnable(true);
                } else {
                    customRefreshLayout.setEnable(false);
                }
            }
        });

        rootHemepageTitlbar.measure(0, 0);
        titleHeight = rootHemepageTitlbar.getMeasuredHeight();
        //设置标题文字随appBatLayout变化的监听
        appbarlayoutAppbarFragmentHomepage.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) <= 0) {
                    rootHemepageTitlbar.setBackgroundColor(0x0000a051);
                } else if (Math.abs(verticalOffset) < 5 * titleHeight) {
                    float alpha = Math.abs(verticalOffset) / (5 * titleHeight);
                    rootHemepageTitlbar.setBackgroundColor(0x0000a051 + ((int) (alpha * 0xff) * 0x01000000));
                } else {
                    rootHemepageTitlbar.setBackgroundColor(0xff00a051);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadContentData() {
        if (!isLoading) {
            if (isRefreshing) {
                presenterHomepageContentData.setPage(0);
            }
            isLoading = true;
            presenterHomepageContentData.loadData();
        }
    }

    @OnClick({R.id.go_top_homepage, R.id.textview_company, R.id.textview_activities, R.id.textview_school, R.id.textview_budget, R.id.textview_fitment, R.id.textview_effect, R.id.textview_booking, R.id.textview_design, R.id.zxing_hpmepage_titlebar, R.id.search_hpmepage_titlebar, R.id.location_hpmepage_titlebar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_top_homepage:
                if (layoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
                    recyclerviewHomepage.scrollToPosition(0);
                }
                appbarlayoutAppbarFragmentHomepage.setExpanded(true, false);
                break;
            case R.id.textview_company:
                jumpToActivity(CompanyActivity.class);
                break;
            case R.id.textview_activities:
                jumpToActivity(ActivitiesActivity.class);
                break;
            case R.id.textview_school:
                jumpToActivity(SchoolActivity.class);
                break;
            case R.id.textview_budget:
                jumpToActivity(BudgetActivity.class);
                break;
            case R.id.textview_fitment:
                jumpToActivity(FitmentActivity.class);
                break;
            case R.id.textview_effect:
                jumpToActivity(EffectActivity.class);
                break;
            case R.id.textview_booking:
                jumpToActivity(BookingActivity.class);
                break;
            case R.id.textview_design:
                jumpToActivity(DesignActivity.class);
                break;
            case R.id.zxing_hpmepage_titlebar:
                jumpToActivity(ZxingActivity.class);
                break;
            case R.id.search_hpmepage_titlebar:
                jumpToActivity(SearchActivity.class);
                break;
            case R.id.location_hpmepage_titlebar:
                showLocationPopupWindow();
                break;
        }
    }

    @Override
    public void onLoadHomePageHeadDataSuccess(List<HomepageHeadData.DataBean> datas) {
        customRefreshLayout.finishRefresh();
        headAdapter = new HomepageViewPagerAdapter(getContext(), datas);
        viewpagerHomepageHead.setAdapter(headAdapter);
        headAdapter.setOnItemClickListener(new HomepageViewPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HomePageFragment.this.getContext(), DetailsPageActivity.class);
                intent.putExtra(DetailsPageActivity.TAG_URL_EXTRA, (String) view.getTag());
                HomePageFragment.this.getContext().startActivity(intent);
            }
        });
        viewpagerHomepageHead.setAdapter(headAdapter);
        viewpagerHomepageHeadIndicator.removeAllViews();
        ViewPagerIndicator indicator = new ViewPagerIndicator(getContext(), viewpagerHomepageHead, viewpagerHomepageHeadIndicator, headAdapter.getCount());
        indicator.setDotWidthByDp(8);
        indicator.setMarginByDp(6);
        indicator.create();
    }

    @Override
    public void onLoadHomePageHeadDataFaied(String info) {
        customRefreshLayout.finishRefresh();
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadHomePageContentDataSuccess(List<HomepageContentData.DataBean> datas) {
        if (isRefreshing) {
            adapter.refreshDatas(datas);
            isRefreshing = false;
        } else {
            adapter.addDatas(datas);
        }
        isLoading = false;
        customRefreshLayout.finishRefresh();
        adapter.setFooterState(0);
    }

    @Override
    public void onLoadHomePageContentDataFaied(String info) {
        isLoading = false;
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
        customRefreshLayout.finishRefresh();
        adapter.setFooterState(1);
    }

    @Override
    public void isNoMoreData() {
        adapter.setFooterState(2);
        this.isNoMoreData = true;
    }

    /**
     * 页面跳转
     *
     * @param intentClass
     */
    private void jumpToActivity(Class intentClass) {
        Intent intent = new Intent(getContext(), intentClass);
        startActivity(intent);
    }

    /**
     * 显示popupwindow
     */
    private void showLocationPopupWindow() {
        rootHemepageTitlbar.setEnabled(false);
        View view = View.inflate(getContext(), R.layout.fragment_homepage_popupwindow, null);
        ListView listView = (ListView) view.findViewById(R.id.listview_popupwindow_homepage);
        final String[] locationContent = getResources().getStringArray(R.array.location);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 0; i < locationContent.length; i++) {
            map = new HashMap<>();
            map.put("location", locationContent[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(), list, R.layout.fragment_homepage_popupwindow_item
                , new String[]{"location"}, new int[]{R.id.text});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sp = getContext().getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(SP_CONFIG_CITY_KEY, locationContent[position]);
                editor.commit();
                popupWindow.dismiss();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        View textview1 = view.findViewById(R.id.textview1);
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View textview2 = view.findViewById(R.id.textview1);
        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View text = view.findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getContext().getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                TextView view = (TextView) v;
                editor.putString(SP_CONFIG_CITY_KEY, view.getText().toString());
                editor.commit();
                popupWindow.dismiss();
            }
        });
        view.setPadding(view.getPaddingLeft(), rootHemepageTitlbar.getHeight()
                , view.getPaddingRight(), view.getPaddingBottom());
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setHeight(customRefreshLayout.getHeight()-rootHemepageTitlbar.getHeight());
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindoe_homepage));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(customRefreshLayout, Gravity.TOP, 0, 0);
    }

    public boolean setPopupWindowDismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return true;
        } else {
            return false;
        }
    }
}
