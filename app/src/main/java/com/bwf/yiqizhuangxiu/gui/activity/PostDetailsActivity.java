package com.bwf.yiqizhuangxiu.gui.activity;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.PostDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;
import com.bwf.yiqizhuangxiu.gui.custom.CustomFlowLayout;
import com.bwf.yiqizhuangxiu.gui.custom.CustomRefreshLayout;
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
    @Bind(R.id.likenum_postdetails)
    TextView likenumPostdetails;
    @Bind(R.id.commentnum_container_postdetails)
    LinearLayout commentnumContainerPostdetails;
    @Bind(R.id.commentnum_postdetails)
    TextView commentnumPostdetails;
    @Bind(R.id.no_comments)
    TextView noComments;

    public final static String TAG_ID_EXTRA = "tID";
    private PresenterPostDetailsImpl presenter;
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
            presenter.setPage(0);
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
                    TextView textView = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, dip2px(12), 0, 0);
                    textView.setLayoutParams(params);
                    textView.setText(message.getMsg());
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                    contentContainerPostdetails.addView(textView);
                } else if (message.getMsgType() == 1) {
                    SimpleDraweeView sdv = new SimpleDraweeView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, dip2px(12), 0, 0);
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
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
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
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) LayoutInflater.from(this).inflate(R.layout.postdetails_likeimg_item, likeimgContainer, false);
                likeimgContainer.addView(simpleDraweeView);
                simpleDraweeView.setImageURI(Uri.parse(like.getAvtUrl()));
            }
        }
    }

    @Override
    public void onLoadLikeFailed(String message) {
        refreshPostdetails.finishRefresh();
        isRefreshing = false;
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadCommentsSuccess(List<PostDetailsCommentsData.DataBean> datas, int commentNum) {
        commentnumContainerPostdetails.removeAllViews();
        commentnumPostdetails.setText(getString(R.string.comments_postdetails, commentNum));
        if (datas.size() > 0) {
            noComments.setVisibility(View.GONE);
            for (PostDetailsCommentsData.DataBean data : datas) {
                View view = LayoutInflater.from(this).inflate(R.layout.postdetails_comments_item, commentnumContainerPostdetails, false);
                ViewHolder holder = new ViewHolder(view);
                holder.showReply.setTag(holder);
                holder.headimagSimpleDraweeView.setImageURI(Uri.parse(data.getAvtUrl()));
                holder.nameTextview.setText(data.getAuthor());
                holder.timeTextview.setText(data.getDateline());
                List<PostDetailsCommentsData.DataBean.MessageBean> messages = data.getMessage();
                for (PostDetailsCommentsData.DataBean.MessageBean message : messages) {
                    if (message.getMsgType() == 0) {
                        TextView textview = new TextView(this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textview.setLayoutParams(params);
                        textview.setText(message.getMsg());
                        holder.replyObjBean.addView(textview);
                    } else if (message.getMsgType() == 1) {
                        SimpleDraweeView sdv = new SimpleDraweeView(this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(message.getWidth()), dip2px(message.getHeight()));
                        sdv.setLayoutParams(params);
                        sdv.setImageURI(Uri.parse(message.getMsg()));
                        holder.replyObjBean.addView(sdv);
                    }
                }
                if (data.getBlock() != null && !"".equals(data.getBlock())) {
                    String str = data.getBlock();
                    str = str.replaceAll("(?<=(\\d{2}:\\d{2}))[\\s]+(?=([\\S\\s]))", "\n");
                    holder.replyChildContent.setPadding(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
                    holder.replyChildContent.setText(str);
                } else {
                    holder.replyObjBeanChild.setVisibility(View.GONE);
                }
                commentnumContainerPostdetails.addView(view);
            }
        }
    }

    @Override
    public void onLoadCommentsFailed(String message) {
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
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

    public class ViewHolder implements View.OnClickListener {
        @Bind(R.id.headimag_simpleDraweeView)
        SimpleDraweeView headimagSimpleDraweeView;
        @Bind(R.id.name_textview)
        TextView nameTextview;
        @Bind(R.id.conten_textview)
        TextView contenTextview;
        @Bind(R.id.reply_obj_bean)
        LinearLayout replyObjBean;
        @Bind(R.id.time_textview)
        TextView timeTextview;
        @Bind(R.id.show_reply)
        ImageView showReply;
        @Bind(R.id.bean)
        RelativeLayout bean;
        @Bind(R.id.reply_child_content)
        TextView replyChildContent;
        @Bind(R.id.reply_obj_bean_child)
        LinearLayout replyObjBeanChild;
        @Bind(R.id.reply_button)
        TextView replyButton;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            showReply.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            setReplyButtonIsVisible(v);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            setReplyButtonIsVisible(null);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void setReplyButtonIsVisible(View v) {
        for (int i = 0; i < commentnumContainerPostdetails.getChildCount(); i++) {
            View view = commentnumContainerPostdetails.getChildAt(i);
            View holdView = view.findViewById(R.id.show_reply);
            if (v != null && v == holdView) {
                ViewHolder holder = (ViewHolder) holdView.getTag();
                if (holder.replyButton.getVisibility() == View.GONE) {
                    holder.replyButton.setVisibility(View.VISIBLE);
                } else if (holder.replyButton.getVisibility() == View.VISIBLE) {
                    holder.replyButton.setVisibility(View.GONE);
                }
            } else {
                ViewHolder holder = (ViewHolder) holdView.getTag();
                if (holder.replyButton.getVisibility() == View.VISIBLE) {
                    holder.replyButton.setVisibility(View.GONE);
                }
            }
        }
    }
}
