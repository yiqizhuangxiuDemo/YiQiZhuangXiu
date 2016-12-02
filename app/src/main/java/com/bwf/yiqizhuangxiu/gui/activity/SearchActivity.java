package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.SearchData;
import com.bwf.yiqizhuangxiu.gui.adapter.SearchContentAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterSearch;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterSearchImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewSearch;
import com.bwf.yiqizhuangxiu.utils.KeyBoardUtils;

import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class SearchActivity extends BaseActivity implements ViewSearch, SearchContentAdapter.LoadMoreDataCallBack, SearchContentAdapter.OnItemClickListener {
    @Bind(R.id.inner_search_eduittext)
    EditText innerSearchEduittext;
    @Bind(R.id.cancel_action)
    TextView cancelAction;
    @Bind(R.id.inner_search_delete)
    ImageView innerSearchDelete;
    @Bind(R.id.search_nothing)
    FrameLayout searchNothing;
    @Bind(R.id.search_recycleview_up)
    RecyclerView searchRecycleviewUp;
    @Bind(R.id.search_recycleview_content)
    RecyclerView searchRecycleviewContent;
    @Bind(R.id.search_content)
    FrameLayout searchContent;
    @Bind(R.id.inner_search_img)
    ImageView innerSearchImg;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    private PresenterSearch presenterSearch;
    private String search;
    private SearchContentAdapter adapter;

    @Override
    protected void initDatas() {
        presenterSearch = new PresenterSearchImpl(this);
        adapter = new SearchContentAdapter(this);
        adapter.setLoadMoreDataCallBack(this);
        adapter.setOnItemClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecycleviewContent.setLayoutManager(manager);
        searchRecycleviewContent.setAdapter(adapter);
        innerSearchEduittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search = innerSearchEduittext.getText().toString();
                String encode = URLEncoder.encode(search);
                adapter.sendText(search);
                presenterSearch.loadData(encode);
            }
        });
    }




    @OnClick({R.id.inner_search_eduittext, R.id.inner_search_delete, R.id.cancel_action})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inner_search_eduittext:
                KeyBoardUtils.openKeybord(innerSearchEduittext, this);
                if (innerSearchEduittext.getText().length() != 0 || innerSearchEduittext.getText().toString() != null) {
                    innerSearchDelete.setVisibility(View.VISIBLE);
                } else {
                    innerSearchDelete.setVisibility(View.GONE);
                }
                break;
            case R.id.inner_search_delete:
                innerSearchEduittext.getText().clear();
                KeyBoardUtils.closeKeybord(innerSearchEduittext, this);
                innerSearchDelete.setVisibility(View.GONE);
                break;
            case R.id.cancel_action:
                finish();
                break;
        }
    }

    @Override
    public void showSearchDataSuccess(List<SearchData.DataBean> datas) {
        Toast.makeText(this, "-----------------datas:" + datas, Toast.LENGTH_SHORT).show();
        if (!datas.isEmpty() || datas.toString() != null) {
            innerSearchImg.setVisibility(View.GONE);
            searchContent.setVisibility(View.VISIBLE);
            searchNothing.setVisibility(View.GONE);
            adapter.addDatas(datas);
        }
    }

    @Override
    public void showSearchDataFailed(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
        innerSearchImg.setVisibility(View.GONE);
        searchContent.setVisibility(View.GONE);
        searchNothing.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadMore() {
        presenterSearch.loadData(search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View v, SearchData.DataBean bean) {
        switch (v.getId()) {
            case R.id.item_ownersaypagecream_bottom_share:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_ownersaypagecream_bottom_comment_img:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_ownersaypagecream_bottom_zan_img:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ownersaypagecream_content_bottomtext:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ownersaypagecream_title_img:
                startActivity(new Intent(this,OwnerSaySubActivity.class));
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_ownersaypagecream:
                String tid = bean.getTid();
                Intent intent = new Intent(this,PostDetailsActivity.class);
                intent.putExtra("tid",tid);
                startActivity(intent);
                Toast.makeText(this, "6" + "---tid="+tid, Toast.LENGTH_SHORT).show();
        }
    }
}
