package com.bwf.yiqizhuangxiu.gui.activity;

import android.view.View;
import android.widget.ImageView;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class OwnerSaySubActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ownersaysubactivity_banck)
    ImageView ownersaysubactivityBanck;

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

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
