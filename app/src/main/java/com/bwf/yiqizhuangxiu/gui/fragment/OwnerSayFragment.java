package com.bwf.yiqizhuangxiu.gui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.activity.SearchActivity;
import com.bwf.yiqizhuangxiu.gui.adapter.OwnerSayFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class OwnerSayFragment extends BaseFragment {
    @Bind(R.id.ownersay_fragment_tab_image)
    ImageView ownersayFragmentTabImage;
    @Bind(R.id.feed)
    ImageView feed;
    @Bind(R.id.order)
    ImageView order;
    @Bind(R.id.note)
    ImageView note;
    @Bind(R.id.complain)
    ImageView complain;
    @Bind(R.id.ownersay_fragment_floating)
    ImageView ownersayFragmentFloating;
    @Bind(R.id.floatting_layout)
    RelativeLayout floattingLayout;
    private TabLayout mTabLayoout;
    private ViewPager mViewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Handler handler = new Handler();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        mViewPager.setOffscreenPageLimit(2);
        OwnerSayFragmentAdapter adapter = new OwnerSayFragmentAdapter(getFragmentManager(), mTitleList, mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayoout.setupWithViewPager(mViewPager);

        floattingLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isCanSee) {
                    isCanSee = false;
                    ObjectAnimator.ofFloat(ownersayFragmentFloating, "rotation", 45, 0).setDuration(300).start();
                    animationEnd();
                }
                return true;
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_ownersay;
    }

    private void init() {
        mTabLayoout = (TabLayout) getActivity().findViewById(R.id.ownersay_fragment_tab_tablayout);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.ownersay_fragment_viewpager);
        mTitleList.add("精华");
        mTitleList.add("最新");
        mTitleList.add("板块");

        mFragmentList.add(new FragmentOwnerSayCream());
        mFragmentList.add(new FragmentOwnerSayUpToData());
        mFragmentList.add(new FragmentOwnerSayPlate());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private boolean isCanSee;

    @OnClick({R.id.feed, R.id.order, R.id.note, R.id.complain, R.id.ownersay_fragment_floating, R.id.ownersay_fragment_tab_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feed:
                Toast.makeText(getActivity(), "feed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.order:
                Toast.makeText(getActivity(), "order", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note:
                Toast.makeText(getActivity(), "note", Toast.LENGTH_SHORT).show();
                break;
            case R.id.complain:
                Toast.makeText(getActivity(), "complain", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ownersay_fragment_floating:
                ownersayFragmentFloating.setPivotX(ownersayFragmentFloating.getWidth()/2);
                ownersayFragmentFloating.setPivotY(ownersayFragmentFloating.getHeight()/2);
                if (isCanSee) {
                    isCanSee = false;
                    ObjectAnimator.ofFloat(ownersayFragmentFloating, "rotation", 45, 0).setDuration(300).start();
                    animationEnd();
                } else {
                    ObjectAnimator.ofFloat(ownersayFragmentFloating, "rotation", 0, 45).setDuration(300).start();
                    isCanSee = true;
                    floattingLayout.setVisibility(View.VISIBLE);
                    animationStart();
                }

                break;
            case R.id.ownersay_fragment_tab_image:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    private void animationStart() {
        ObjectAnimator a = ObjectAnimator.ofFloat(feed, "translationY", 0, -1000);
        a.setInterpolator(new OvershootInterpolator());
        a.setDuration(600);
        a.setStartDelay(50);
        a.start();
        ObjectAnimator b = ObjectAnimator.ofFloat(order, "translationY", 0, -1000);
        b.setInterpolator(new OvershootInterpolator());
        b.setDuration(600);
        b.setStartDelay(80);
        b.start();
        ObjectAnimator c = ObjectAnimator.ofFloat(note, "translationY", 0, -600);
        c.setInterpolator(new OvershootInterpolator());
        c.setDuration(600);
        c.setStartDelay(110);
        c.start();
        ObjectAnimator d = ObjectAnimator.ofFloat(complain, "translationY", 0, -600);
        d.setInterpolator(new OvershootInterpolator());
        d.setDuration(600);
        d.setStartDelay(140);
        d.start();
    }

    private void animationEnd() {
        ObjectAnimator a = ObjectAnimator.ofFloat(feed, "translationY", -1000, 0).setDuration(500);
        a.setStartDelay(140);
        a.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                floattingLayout.setVisibility(View.GONE);
            }
        });
        a.start();
        ObjectAnimator b = ObjectAnimator.ofFloat(order, "translationY", -1000, 0).setDuration(500);
        b.setStartDelay(110);
        b.start();
        ObjectAnimator c = ObjectAnimator.ofFloat(note, "translationY", -600, 0).setDuration(300);
        c.setStartDelay(90);
        c.start();
        ObjectAnimator d = ObjectAnimator.ofFloat(complain, "translationY", -600, 0).setDuration(300);
        d.setStartDelay(60);
        d.start();
    }
}
