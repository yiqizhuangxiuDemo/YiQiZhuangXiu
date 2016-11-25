package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class SearchActivity extends BaseActivity {
    @Bind(R.id.inner_search_eduittext)
    EditText innerSearchEduittext;
    @Bind(R.id.cancel_action)
    TextView cancelAction;
    @Bind(R.id.inner_search_delete)
    ImageView innerSearchDelete;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @OnClick({R.id.inner_search_eduittext, R.id.inner_search_delete, R.id.cancel_action})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inner_search_eduittext:
                innerSearchDelete.setVisibility(View.VISIBLE);
                break;
            case R.id.inner_search_delete:
                innerSearchEduittext.setText("");
                innerSearchDelete.setVisibility(View.GONE);
                break;
            case R.id.cancel_action:
                finish();
                break;
        }
    }
}
