package com.bwf.yiqizhuangxiu.gui.activity;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;
import com.bwf.yiqizhuangxiu.gui.custom.CustomFlowLayout;
import com.bwf.yiqizhuangxiu.gui.custom.CustomRefreshLayout;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterPostDetails;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterPostDetailsImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewPostDetails;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PostDetailsActivity extends BaseActivity implements ViewPostDetails {
    @Bind(R.id.titlebar_content)
    TextView titlebarContent;
    @Bind(R.id.headimag_simpleDraweeView)
    SimpleDraweeView headimagSimpleDraweeView;
    @Bind(R.id.name_textview)
    TextView nameTextview;
    @Bind(R.id.houseinfo_textview)
    TextView houseinfoTextview;
    @Bind(R.id.title_postdetails)
    TextView titlePostdetails;
    @Bind(R.id.from_postdetails)
    TextView fromPostdetails;
    @Bind(R.id.time_postdetails)
    TextView timePostdetails;
    @Bind(R.id.content_container_postdetails)
    LinearLayout contentContainerPostdetails;
    @Bind(R.id.tag_container_postdetails)
    CustomFlowLayout tagContainerPostdetails;
    @Bind(R.id.likeimg_container)
    CustomFlowLayout likeimgContainer;
    @Bind(R.id.refresh_postdetails)
    CustomRefreshLayout refreshPostdetails;

    public final static String TAG_ID_EXTRA = "ID";
    @Bind(R.id.likenum_postdetails)
    TextView likenumPostdetails;

    private PresenterPostDetails presenter;
    private boolean isRefreshing;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_postdetails;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        titlebarContent.setText("帖子详情");
        presenter = new PresenterPostDetailsImpl(this);
        initListener();
    }

    private void initListener() {
        refreshPostdetails.setOnRefreshListener(new CustomRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

        });
    }

    private void loadData() {
        if (!isRefreshing) {
            isRefreshing = true;
            presenter.loadPostDetailsData(getIntent().getStringExtra(TAG_ID_EXTRA));
        }
    }

    @Override
    protected void initDatas() {
        refreshPostdetails.startRefreshOnce();
    }

    @OnClick(R.id.titlebar_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onLoadContentSuccess(PostDetailsContentDataBean.DataBean data) {
        LogUtils.e("onLoadContentSuccess");
        refreshPostdetails.finishRefresh();
        isRefreshing = false;
        setAuthorInfo(data);
        setPostInfo(data);
        setContentInfo(data);
        setTagInfo(data);
    }

    private void setContentInfo(PostDetailsContentDataBean.DataBean data) {
        List<PostDetailsContentDataBean.DataBean.MessageBean> messages = data.getMessage();
        if (messages != null && messages.size() > 0) {
            for (PostDetailsContentDataBean.DataBean.MessageBean message : messages) {
                if (message.getMsgType() == 0) {
                    LogUtils.e("msgtype==0");
                    WebView webView = new WebView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 60, 0, 0);
                    webView.setLayoutParams(params);
                    webView.loadDataWithBaseURL(null, message.getMsg(), "text/html", "utf-8", null);
                    contentContainerPostdetails.addView(webView);
                } else if (message.getMsgType() == 1) {
                    LogUtils.e("msgtype==1");
                    SimpleDraweeView sdv = new SimpleDraweeView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 60, 0, 0);
                    sdv.setLayoutParams(params);
                    sdv.setAspectRatio(message.getWidth() / (float) message.getHeight());
                    sdv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    sdv.setImageURI(Uri.parse(message.getMsg()));
                    contentContainerPostdetails.addView(sdv);
                }
            }
        }
    }

    @Override
    public void onLoadContentFailed(String message) {
        LogUtils.e("onLoadContentFailed");
        refreshPostdetails.finishRefresh();
        isRefreshing = false;
    }

    @Override
    public void onLoadLikeSuccess(PostDetailsLikeData data) {
        LogUtils.e("onLoadLikeSuccess");
        refreshPostdetails.finishRefresh();
        isRefreshing = false;
        likenumPostdetails.setText(getString(R.string.likeinfi_postdetails, data.getTotalCount()));
        if (data.getData() != null && data.getData().size() > 0) {
            likeimgContainer.removeAllViews();
            for (PostDetailsLikeData.DataBean like : data.getData()) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) View.inflate(this
                        , R.layout.postdetails_likeimg_item, null);
                simpleDraweeView.setImageURI(Uri.parse(like.getAvtUrl()));
                likeimgContainer.addView(simpleDraweeView);
            }
        }
    }

    @Override
    public void onLoadLikeFailed(String message) {
        LogUtils.e("onLoadLikeFailed");
        refreshPostdetails.finishRefresh();
        isRefreshing = false;
    }

    private void setTagInfo(PostDetailsContentDataBean.DataBean data) {
        List<PostDetailsContentDataBean.DataBean.TagsBean> tags = data.getTags();
        if (tags == null || tags.size() < 1)
            return;
        for (PostDetailsContentDataBean.DataBean.TagsBean tag : tags) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.postdetails_tag_item
                    , tagContainerPostdetails, false);
            tv.setText(tag.getTagname());
            tagContainerPostdetails.addView(tv);
        }
    }

    private void setPostInfo(PostDetailsContentDataBean.DataBean data) {
        titlePostdetails.setText(data.getTitle());
        fromPostdetails.setText(getString(R.string.homepage_post, data.getFname()));
        timePostdetails.setText(data.getDateline());
    }

    private void setAuthorInfo(PostDetailsContentDataBean.DataBean data) {
        headimagSimpleDraweeView.setImageURI(Uri.parse(data.getAvtUrl()));
        if (data.getHouseInfo() != null) {
            PostDetailsContentDataBean.DataBean.HouseInfoBean houseInfo = data.getHouseInfo();
            String classify = getString(R.string.house_classify
                    , getResources().getStringArray(R.array.numberToLetter)[houseInfo.getLayout()]
                    , getResources().getStringArray(R.array.numberToLetter)[houseInfo.getStyle()]);
            String area = getString(R.string.house_area, houseInfo.getArea());
            String budget = getString(R.string.house_budget, houseInfo.getBudget());
            houseinfoTextview.setText(classify + "、" + area + "、" + budget);
        } else {
            houseinfoTextview.setVisibility(View.GONE);
        }
    }
}
