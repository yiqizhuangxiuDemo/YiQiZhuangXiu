package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class TogetherPeople extends BaseActivity {
    @Bind(R.id.item_image_together)
    SimpleDraweeView itemImageTogether;
    @Bind(R.id.desin_text_name)
    TextView desinTextName;
    @Bind(R.id.text_design_num)
    TextView textDesignNum;
    @Bind(R.id.score)
    TextView score;
    @Bind(R.id.text_person_anli)
    TextView textPersonAnli;
    @Bind(R.id.together_recycle_view)
    RecyclerView togetherRecycleView;
    @Bind(R.id.text_news)
    TextView textNews;
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.titlebar_back)
    ImageView titlebarBack;

    @Override
    protected int getContentViewResId() {
        return R.layout.together_people;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titlebarContent.setText("一起人");
        Intent intent = getIntent();
        if ( null != intent.getStringExtra("avatar"))
            itemImageTogether.setImageURI(Uri.parse(intent.getStringExtra("avatar")));
        desinTextName.setText(intent.getStringExtra("vendorName"));
        textDesignNum.setText(getString(R.string.caseNumber, intent.getStringExtra("caseNumber")));
        textNews.setText(getString(R.string.worknews, intent.getStringExtra("commentCount")));
        textPersonAnli.setText(getString(R.string.personanli, intent.getStringExtra("caseNumber")));
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
}
