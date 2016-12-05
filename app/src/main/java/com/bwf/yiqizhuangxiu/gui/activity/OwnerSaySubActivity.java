package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class OwnerSaySubActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ownersaysubactivity_banck)
    ImageView ownersaysubactivityBanck;
    @Bind(R.id.ownersaysubactivity_banck2)
    ImageView ownersaysubactivityBanck2;
    @Bind(R.id.personal_homepage_top_name2)
    TextView personalHomepageTopName2;
    @Bind(R.id.personal_homepage)
    TextView personalHomepage;
    @Bind(R.id.personal_homepage_headpic)
    ImageView personalHomepageHeadpic;
    @Bind(R.id.personal_homepage_addcare)
    ImageView personalHomepageAddcare;
    @Bind(R.id.personal_homepage_follow_text)
    TextView personalHomepageFollowText;
    @Bind(R.id.personal_homepage_follow)
    LinearLayout personalHomepageFollow;
    @Bind(R.id.personal_homepage_fans_text)
    TextView personalHomepageFansText;
    @Bind(R.id.personal_homepage_fans)
    LinearLayout personalHomepageFans;
    @Bind(R.id.personal_homepage_postnum)
    TextView personalHomepagePostnum;
    @Bind(R.id.personal_homepage_more)
    TextView personalHomepageMore;
    @Bind(R.id.personal_homepage_recycleview)
    RecyclerView personalHomepageRecycleview;

    @Override
    protected int getContentViewResId() {
        return R.layout.ownersay_personal_homepage;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        ownersaysubactivityBanck.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String author = intent.getStringExtra("author");
        String avtUrl = intent.getStringExtra("avtUrl");
        personalHomepage.setText(author);
        personalHomepageHeadpic.setImageURI(Uri.parse(avtUrl));
    }

    @Override
    public void onClick(View v) {
        finish();
    }


}
