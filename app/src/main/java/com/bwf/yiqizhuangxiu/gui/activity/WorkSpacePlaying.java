package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;
import com.bwf.yiqizhuangxiu.gui.adapter.RecyclerViewWithHeaderOrFooterAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.WorkPlayPeopeRecycleAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.WorkPlayProgressFragment;
import com.bwf.yiqizhuangxiu.mvp.presenter.WorkPlayingPeoplePresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.WorkPlayingPeoplePresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.WorkPlayingView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class WorkSpacePlaying extends BaseActivity implements WorkPlayingView {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;
    @Bind(R.id.image_decorator)
    SimpleDraweeView imageDecorator;
    @Bind(R.id.text_title_big)
    TextView textTitleBig;
    @Bind(R.id.text_title_little)
    TextView textTitleLittle;
    @Bind(R.id.linear_design_people)
    LinearLayout linearDesignPeople;
    @Bind(R.id.decortae_progress_listview)
    RecyclerView decortaeProgressListview;
    @Bind(R.id.fragmelayout_workpaly)
    FrameLayout fragmelayoutWorkpaly;
    private String buildingId;
    private LinearLayoutManager manager;
    private String[] nameTag = {"设计师", "监理", "工长", "材料顾问"};

    private WorkPlayingPeoplePresenter peoplePresenter;
    private WorkPlayPeopeRecycleAdapter recycleAdatper;


    @Override
    protected int getContentViewResId() {
        return R.layout.workspace_playing;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titlebarContent.setText(getString(R.string.workspace_playing));
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.design_people, null);
            linearDesignPeople.addView(view);
        }
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        decortaeProgressListview.setLayoutManager(manager);
        recycleAdatper = new WorkPlayPeopeRecycleAdapter(this);
        decortaeProgressListview.setAdapter(recycleAdatper);
        recycleAdatper.setOnItemClickListener(new RecyclerViewWithHeaderOrFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showFragment(position);
            }
        });

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        buildingId = intent.getStringExtra("buildingId");
        Log.d("WorkSpacePlaying", "id+  " + buildingId);

        peoplePresenter = new WorkPlayingPeoplePresenterImpl(this);
        peoplePresenter.loadData(buildingId);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showWorkPeopleAndProgress(WorkPlayingPeopleAndProgressData data) {
        WorkPlayingPeopleAndProgressData.DataBean bean = data.getData();
        setPeopleInfo(bean);
        setDecortaeProgress(bean);
        peoplePresenter.loadProgressData(buildingId);



    }
    private WorkPlayingPeopleAndProgressData.DataBean bean;
    private void setDecortaeProgress(WorkPlayingPeopleAndProgressData.DataBean bean) {
        this.bean = bean;
        recycleAdatper.addDatas(bean.getProgress());
    }

    private void setPeopleInfo(WorkPlayingPeopleAndProgressData.DataBean bean) {
        imageDecorator.setImageURI(Uri.parse(bean.getImageUrl()));
        textTitleBig.setText(bean.getOrderHouse().getCommunity());
        textTitleLittle.setText(bean.getOrderHouse().getLayout());
        linearDesignPeople.removeAllViews();
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.design_people, null);
            PeopleViewHolder holder = new PeopleViewHolder(view);
            String avatar = bean.getMembers().get(i).getAvatar();
            if (null != avatar) {
                holder.imageDesign.setImageURI(Uri.parse(avatar));
            }
            holder.textDesign.setText(bean.getMembers().get(i).getVendorName());
            holder.textDesignTag.setText(nameTag[i]);
            linearDesignPeople.addView(view);
        }
    }

    @Override
    public void showorkPeopleFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    private List<Fragment> fragments;
    @Override
    public void showProgressDataSuccess(WorkPlayProgressData data) {
        fragments = new ArrayList<>();
        for (int i = 0; i < bean.getProgress().size(); i++) {
            List<WorkPlayProgressData.DataBean> data1 = data.getData();
            Fragment fragment = WorkPlayProgressFragment.newInstance(data);
            fragments.add(fragment);
        }
        showFragment(0);
    }
    private int crunment = -1;
    private void showFragment(int positon) {
        if ( crunment == positon){
            return;
        }
        Fragment fragment = fragments.get(positon);
        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction transaction = f.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.fragmelayout_workpaly,fragment);
        }else {
            transaction.show(fragment);
        }
        if (crunment != -1)
            transaction.hide(fragments.get(crunment));
        transaction.commit();
        crunment = positon;
    }

    @Override
    public void showProgressFail(String error) {

    }

    @OnClick(R.id.titlebar_back)
    public void onClick() {
        finish();
    }

    static class PeopleViewHolder {
        @Bind(R.id.image_design)
        SimpleDraweeView imageDesign;
        @Bind(R.id.text_design)
        TextView textDesign;
        @Bind(R.id.text_design_tag)
        TextView textDesignTag;

        PeopleViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
