package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.MyFargmentAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.TogetherFragment;
import com.bwf.yiqizhuangxiu.gui.fragment.TogetherFramgentDesign;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class TogetherTream extends BaseActivity {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @Bind(android.R.id.tabhost)
    FragmentTabHost tabhost;
    @Bind(R.id.layout_frame)
    FrameLayout layoutFrame;
    @Bind(R.id.togeter_viewpager)
    ViewPager togeterViewpager;
    private TabWidget widget;
    private Class<?>[] fragments = {TogetherFragment.class, TogetherFragment.class, TogetherFragment.class};
    private String[] titles = new String[]{"设计师", "工长", "监理"};

    @Override
    protected int getContentViewResId() {
        return R.layout.together_tream;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        tabhost.setup(this, getSupportFragmentManager(), R.id.layout_frame);
        widget = tabhost.getTabWidget();
        widget.setDividerDrawable(null);
        int menCuont = fragments.length;
        for (int i = 0; i < menCuont; i++) {
            TabHost.TabSpec spec = tabhost.newTabSpec(titles[i]).setIndicator(getItemView(i));
            tabhost.addTab(spec, fragments[i], null);
        }
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < widget.getTabCount(); i++) {
                    View view = widget.getChildTabViewAt(i);
                    TextView textView = (TextView) view.findViewById(R.id.text_together_title);
                    View togView = view.findViewById(R.id.together_view);
                    if (i == tabhost.getCurrentTab()) {
                        currentCount = i;
                        tabhost.setCurrentTab(currentCount);
                        textView.setTextColor(0xff4b9f3a);
                        togView.setBackgroundColor(0xff4b9f3a);
                    } else {
                        textView.setTextColor(0xff000000);
                        togView.setBackgroundColor(0xffffffff);
                    }
                }
            }
        });
        addfragment();
        adapter = new MyFargmentAdapter(getSupportFragmentManager(),fragments_to);
        togeterViewpager.setAdapter(adapter);

    }
    private MyFargmentAdapter adapter;
    private List<Fragment> fragments_to;

    private void addfragment() {
        fragments_to = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            Fragment fragment = (Fragment) new TogetherFramgentDesign();
            Bundle bundle = new Bundle();
            bundle.putString("tag",i+"");
            fragment.setArguments(bundle);
            fragments_to.add(fragment);
        }
    }

    private int currentCount;

    private View getItemView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView textView = (TextView) view.findViewById(R.id.text_together_title);
        View togView = view.findViewById(R.id.together_view);
        textView.setText(titles[i]);
        if (i == 0) {
            textView.setTextColor(0xff4b9f3a);
            togView.setBackgroundColor(0xff4b9f3a);
        }
        return view;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
