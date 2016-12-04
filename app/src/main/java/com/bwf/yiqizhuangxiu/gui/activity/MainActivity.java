package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.application.App;
import com.bwf.yiqizhuangxiu.gui.adapter.MainActivityFragmentPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.HomePageFragment;
import com.bwf.yiqizhuangxiu.gui.fragment.MessageFragment;
import com.bwf.yiqizhuangxiu.gui.fragment.OwnerSayFragment;
import com.bwf.yiqizhuangxiu.gui.fragment.PersonageFragment;
import com.bwf.yiqizhuangxiu.utils.indicator.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpager_main)
    ViewPager viewpagerMain;
    @Bind(R.id.tab_linearlayout_main)

    LinearLayout tabLinearlayoutMain;
    ViewPagerIndicator indicator;
    private List<Fragment> fragments;
    private MainActivityFragmentPagerAdapter mAdapter;
    private boolean isExit;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    isExit = false;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences(App.SP_CONFIG, MODE_PRIVATE);
        Toast.makeText(this, sp.getString("user_sex", "aa") + "---" + sp.getString("user_birthday", "aa")
                + "---" + sp.getInt("user_progress", 0), Toast.LENGTH_LONG).show();
        fragments = getFragmentsList();
        mAdapter = new MainActivityFragmentPagerAdapter(fragments, getSupportFragmentManager());
        viewpagerMain.setAdapter(mAdapter);
        viewpagerMain.setOffscreenPageLimit(3);
    }

    private List<Fragment> getFragmentsList() {
        fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new OwnerSayFragment());
        fragments.add(new MessageFragment());
        fragments.add(new PersonageFragment());
        return fragments;
    }

    @Override
    public void onBackPressed() {
        if (fragments.get(0) instanceof HomePageFragment) {
            HomePageFragment fragment = (HomePageFragment) fragments.get(0);
            if (fragment.setPopupWindowDismiss()) {
                return;
            }
        }
        if (isExit) {
            super.onBackPressed();
        } else {
            isExit = true;
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
            return;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && indicator == null) {
            tabLinearlayoutMain.removeAllViews();
            int[] tabRes = new int[]{R.drawable.homepage_tab_selector, R.drawable.ownersay_tab_selector
                    , R.drawable.message_tab_selector, R.drawable.personaage_tab_selector};
            ViewPagerIndicator indicator = new ViewPagerIndicator(this, viewpagerMain
                    , tabLinearlayoutMain, mAdapter.getCount(), tabRes);
            indicator.setDistributedCenter(true);
            indicator.setLinkage(true);
            indicator.setTitle(new String[]{getString(R.string.homepage_text_tab)
                    , getString(R.string.ownersay_text_tab), getString(R.string.message_text_tab)
                    , getString(R.string.personagw_text_tab)}, R.drawable.textcolor_ta_selector);
            indicator.setDotHeightByDp(40);
            indicator.setDotWidthByDp(60);
            indicator.create();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
