package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class WorkSpacePlaying extends BaseActivity {
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

    @Override
    protected int getContentViewResId() {
        return R.layout.workspace_playing;
    }

    @Override
    protected void initViews() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titlebarContent.setText(getString(R.string.workspace_playing));
        for (int i = 0; i < 5; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.design_people, null);
            linearDesignPeople.addView(view);
        }


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
