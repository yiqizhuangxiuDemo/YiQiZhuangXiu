package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.EffectViewPagerAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.BeautfFrament;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class EffectActivity extends BaseActivity implements BeautfFrament.BackHandlerInterface {
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.effcet_title_tablayout)
    TabLayout effcetTitleTablayout;
    @Bind(R.id.effect_viewpager)
    ViewPager effectViewpager;
    EffectViewPagerAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_effect;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        effcetTitleTablayout.setupWithViewPager(effectViewpager);
        adapter = new EffectViewPagerAdapter(getSupportFragmentManager());
        effectViewpager.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.titlebar_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onBackPressed() {

//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (int i = 0; i < fragments.size(); i++) {
//            Log.d("EffectActivity", "onBackPressed  1");
//            if (fragments.get(i) instanceof BeautfFrament){
//                Log.d("EffectActivity", "onBackPressed  2");
//               if( ((BeautfFrament) fragments.get(i)).disMissPopupWindow()){
//                   Log.d("EffectActivity", "onBackPressed  3");
//                   return;
//               }
//            }
//        }
        if (frament.disMissPopupWindow()){
            return;
        }
        super.onBackPressed();
    }


    private BeautfFrament frament;


    @Override
    public void setSelectFragment(BeautfFrament fragment) {
        this.frament = fragment;
    }
}
